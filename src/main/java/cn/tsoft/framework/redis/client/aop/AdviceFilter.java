/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: AdviceFilter.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.client.aop;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.tsoft.framework.redis.client.IValidateFilterChain;
import cn.tsoft.framework.redis.monitor.RedisLog4Monitor;
import cn.tsoft.framework.redis.utils.CacheUtils;
import cn.tsoft.framework.redis.utils.ValueMethodTags;

@Component
@Aspect
public class AdviceFilter {
	
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AdviceFilter.class);
    
    private static String TAB = "\t";
    
    private static String RN = "\r\n";
	
    /**
     * 业务处理器
     */
	@Autowired
    protected IValidateFilterChain validateFilterChain;
	
    public IValidateFilterChain getValidateFilterChain() {
		return validateFilterChain;
	}

	public void setValidateFilterChain(IValidateFilterChain validateFilterChain) {
		this.validateFilterChain = validateFilterChain;
	}

	@Around("execution(* cn.tsoft.framework.redis.client.impl.*RedisClientImpl.*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        //访问目标方法的参数：
        Object[] args = point.getArgs();
       	//获取KEY
/*        String bizKey = "";
        String[] bizKeys = null;
        Map bizKeysMap = null;*/
        String key = "";
        String[] keys = null;
        String nameSpace = "";
        StringBuffer flumeLog = new StringBuffer();
        if(null!=args && args.length>0 &&null!=args[0]){
        	if(args[0] instanceof String){
        		if(null!=args[1] && args[1] instanceof String){
        			key = CacheUtils.getKeyByNamespace((String)args[0], (String)args[1]);
        			nameSpace = (String)args[1];
        		}
        	}else if(args[0] instanceof String[]){
        		keys = CacheUtils.getKeyByNamespace((String[])args[0], (String)args[1]);
        		nameSpace = (String)args[1];
        	}else if(args[0] instanceof Map){
        		keys = CacheUtils.getKeyByNamespace((Map)args[0], (String)args[1]);
        		nameSpace = (String)args[1];
        	}
        }
        
       	//validateFilterChain.doFilter(bizKey,nameSpace,point.getSignature().toString(), args);
        long begin = System.nanoTime();
        Object returnValue = point.proceed(args);
        //logger.debug("venus traceId:"+traceId+",function:"+point.getSignature().getName().toString()+","+CacheUtils.getSizeType(returnValue));
        long time = System.nanoTime() - begin;
        if(!StringUtils.isBlank(key)){
        	logger.debug("key:"+key+",function:"+point.getSignature()+",last:"+time/1000000+"ms");
        }else if (null!=keys){
        	logger.debug("keys:"+Arrays.toString(keys)+",function:"+point.getSignature()+",last:"+time/1000000+"ms");
        }else {
        	logger.debug("function:"+point.getSignature()+",last:"+time/1000000+"ms");
        }
        
        //key的大小监控日志
        //非业务监控方法不需要输出日志
        String methodName = point.getSignature().toString();
        if(ValueMethodTags.isContainsValueMethod(methodName)){
            if(!StringUtils.isBlank(key)){
            	//输出方法
            	flumeLog.append(point.getSignature().getName().toString()).append(TAB);
            	//输出key
            	flumeLog.append(key).append(TAB);
            	Object valueObject = null;
            	//输出value type和size
            	if(ValueMethodTags.isSetValueMethod(methodName)){
            		valueObject = (args[ValueMethodTags.isValueMethod(methodName)]);
            	}else if(ValueMethodTags.isGetValueMethod(methodName)){
            		valueObject = returnValue;
            	}
            	flumeLog.append(CacheUtils.getObjectType(valueObject)).append(TAB);
            	flumeLog.append(CacheUtils.getObjectSize(valueObject)).append(TAB);
            	//最后输出日志,由于需要独立,所以分别输出
            	RedisLog4Monitor.outPut(flumeLog.toString());
            }else if (null!=keys){
            	//循环输出keys
            	int index = 0;
            	for(String keySplit:keys){
                	flumeLog = new StringBuffer();
            		//输出方法
            		flumeLog.append(point.getSignature().getName().toString()).append(TAB);
                	flumeLog.append(keySplit).append(TAB);
                	Object valueObject = null;
                	//输出value type和size
                	if(ValueMethodTags.isSetValueMethod(methodName)){
                		//如果是Map类型，则需要取出类型，同时去除nameSpace，还原bizkey
                		if(args[0] instanceof Map){
                			valueObject = ((Map)args[0]).get(CacheUtils.getBizKeyWithNS(keySplit,nameSpace));
                		}else{
                			valueObject = (args[ValueMethodTags.isValueMethod(methodName)]);
                		}
                	}else if(ValueMethodTags.isGetValueMethod(methodName)){
                		//如果是String[]类型，则需要取出类型
                		if(args[0] instanceof String[] && returnValue instanceof List){
                			valueObject = ((List)returnValue).get(index++);
                		}else{
                			valueObject = returnValue;
                		}
                	}
                	flumeLog.append(CacheUtils.getObjectType(valueObject)).append(TAB);
                	flumeLog.append(CacheUtils.getObjectSize(valueObject)).append(TAB);
                	//最后输出日志,由于需要独立,所以分别输出
                	RedisLog4Monitor.outPut(flumeLog.toString());
            	}
            }
        }
        return returnValue;
    }
	
}