/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: CodisTest.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jiuyescm.codis.test;

import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tsoft.framework.redis.client.IRedisClient;

/**
 * <功能描述>
 * @author ningyu
 * @date 2016年12月30日 下午1:54:29
 */
public class CodisTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
		IRedisClient redis = (IRedisClient) ctx.getBean("springRedisClient");
		int n = 0;
		while(n<100) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String str = redis.set(uuid, "WMS", uuid, 600);
			System.out.println(str);
			n++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


