/*
 * Copyright (c) 2016, Tsoft and/or its affiliates. All rights reserved.
 * FileName: ValidateFilterChainImpl.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.client.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.tsoft.framework.redis.client.IValidateFilterChain;
import cn.tsoft.framework.redis.client.IValidateMethod;

@Component
public class ValidateFilterChainImpl implements IValidateFilterChain {
	
	private List<IValidateMethod> vm;

	@Resource(name = "RedisValidateMethodKey")
	private IValidateMethod key;

	@Resource(name = "RedisValidateMethodSize")
	private IValidateMethod size;

	@Override
	public boolean doFilter(String key,String nameSpace,String type,Object... context) {
		if (null == vm) {
			init();
		}
		Iterator<IValidateMethod> it = vm.iterator();
		while (it.hasNext()) {
			IValidateMethod ivm = (IValidateMethod) it.next();
			if (ivm.isRight(type)) {
				ivm.doValidate(key,nameSpace,type,context);
			}
		}
		return false;
	}

	private void init() {
		vm = new ArrayList<IValidateMethod>();
		if (null == key || null == size)
			System.out.println("VM init failure");
		vm.add(key);
		vm.add(size);
	}

}
