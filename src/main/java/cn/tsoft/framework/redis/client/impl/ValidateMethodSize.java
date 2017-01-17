/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: ValidateMethodSize.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.tsoft.framework.redis.client.IValidateMethod;
import cn.tsoft.framework.redis.utils.CacheUtils;
import cn.tsoft.framework.redis.utils.ValueMethodTags;

@Service("RedisValidateMethodSize")
public class ValidateMethodSize implements IValidateMethod {

	/**
	 * LOGGER
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ValidateMethodSize.class);

	@Override
	public boolean isRight(String validateId) {
		return true;
	}

	@Override
	public boolean doValidate(String bizkey,String nameSpace,String type, Object... context) {
		StringBuffer logSb = new StringBuffer();
		logSb.append("key:").append(CacheUtils.getKeyByNamespace(bizkey, nameSpace))
				.append(",function:").append(type);
		Integer valueNum = ValueMethodTags.isValueMethod(type); 
		if(null!=valueNum){
			//输出value日志
			appendValue2log(context[valueNum],logSb);
		}
		//输出参数日志
		for (Object object :context) {
			CacheUtils.appendStr2log(object,logSb," param");
		}
		logger.debug(logSb.toString());
		return true;
	}

	private String getMethodName(String type) {
		return type.substring(type.lastIndexOf(".")+1,type.length());
	}

	private void appendValue2log(Object object, StringBuffer logSb) {
		CacheUtils.appendStr2log(object,logSb," value");
	}
}
