/*
 * Copyright (c) 2016, Tsoft and/or its affiliates. All rights reserved.
 * FileName: BussinessHandler.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package io.github.ningyu.redis.client.impl;

import io.codis.jodis.JedisResourcePool;
import io.github.ningyu.redis.client.CallBack;
import io.github.ningyu.redis.utils.ReadMethodTags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 处理一个分片中的所有操作的类<br>
 * 
 */
public class BussinessHandler {
	/**
	 * 日志记录
	 */
	private static Logger logger = LoggerFactory.getLogger(BussinessHandler.class);
	
	/**
	 * 
	 * 功能描述：invoke
	 * 
	 * @param jedisPool
	 *            参数说明 返回值: 类型 <说明>
	 * @param callBack
	 *            参数说明 返回值: 类型 <说明>
	 * @param <R>
	 *            泛型对象爱内阁
	 * @return R 返回值
	 */
	private <R> R invoke(JedisResourcePool jedisPool, CallBack<R> callBack) {
		boolean isClosed = false;
		Jedis jedis = null;
		try {
			// 获取连接
			jedis = jedisPool.getResource();
			return callBack.invoke(jedis);
		} catch (Exception e) {
			if (e instanceof JedisConnectionException) {
				if (jedis != null) {
					try {
						// 处理无效连接
						jedis.close();
						isClosed = true;
					} catch (JedisException e1) {
						logger.error(e1.getMessage(), e1);
					}
				}
			}
		} finally {
			if (!isClosed) {
				if (jedis != null) {
					try {
						// 归还连接
						jedis.close();
					} catch (JedisException e1) {
						logger.error(e1.getMessage(), e1);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 功能描述：<br>
	 * execute
	 * 
	 * @param callBack
	 *            参数说明
	 * @param <R>
	 *            结果泛型对象
	 * @return R 返回值
	 */
	public <R> R execute(JedisResourcePool jedisPool, CallBack<R> callBack) {
		// 获取上一方法名
		String preMethodName = new Exception().getStackTrace()[2]
				.getMethodName();
		// 若是读方法
		if (ReadMethodTags.isReadMethod(preMethodName)) {
			try {
				return invoke(jedisPool, callBack);
			} catch (Exception e) {
				throw e;
			}
		} else {
			R r = null;
			try {
				// 执行
				r = invoke(jedisPool, callBack);
			} catch (Exception e) {
			}
			return r;
		}
	}
	
	
}
