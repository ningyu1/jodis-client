/*
 * Copyright (c) 2016, Tsoft and/or its affiliates. All rights reserved.
 * FileName: RedisCmdFilter.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.client.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Protocol.Command;

import cn.tsoft.framework.redis.utils.CacheUtils;

@Component
@Aspect
public class RedisCmdFilter {
	
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RedisCmdFilter.class);	
	

	@Around("execution(* redis.clients.jedis.Protocol.*sendCommand(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
       	//进行业务验证
        long begin = System.nanoTime();
        if(null!=args && null!=args[1] && args[1] instanceof Command){
        	logger.info("command:"+((Command)args[1]).name()+","+CacheUtils.getSendCommondSizeType(args));
        }else if (null!=args && null!=args[1] && args[1] instanceof byte[]){
        	logger.info("command:"+new String((byte[])args[1])+","+CacheUtils.getSendCommondSizeType(args));
        }else{
        	logger.info(CacheUtils.getSendCommondSizeType(args));
        }
        Object returnValue = point.proceed(args);
        long time = System.nanoTime() - begin;
        logger.info(point.getSignature()+",last "+time/1000000+"ms");
        System.out.println("@Around：执行目标方法之后...");
        return returnValue;
    }
	
}