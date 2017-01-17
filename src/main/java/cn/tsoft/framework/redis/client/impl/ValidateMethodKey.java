/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: ValidateMethodKey.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.client.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.tsoft.framework.redis.client.IValidateMethod;
import cn.tsoft.framework.redis.utils.CacheUtils;

@Service("RedisValidateMethodKey")
public class ValidateMethodKey implements IValidateMethod {

	/**
	 * LOGGER
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ValidateMethodKey.class);

	@Override
	public boolean isRight(String validateId) {
		return true;
	}

	@Override
	public boolean doValidate(String bizkey,String nameSpace,String type,Object... context) {
//		Pattern pattern = Pattern.compile(".*\\..*");
//		if(!pattern.matcher(key).matches()){
//			String traceId = CacheUtils.getVenusTraceId();
//			StringBuffer logSb = new StringBuffer();
//			logSb.append("venus traceId:").append(traceId).append(",").append("key:").append(key).append(" Illegal name!");
//			logger.error(logSb.toString());
//		}
		if(StringUtils.isBlank(nameSpace)){
			StringBuffer logSb = new StringBuffer();
			logSb.append("bizkey:").append(bizkey).append(" Illegal name! No nameSpace !");
			logger.error(logSb.toString());
		}
		return true;
	}


}
