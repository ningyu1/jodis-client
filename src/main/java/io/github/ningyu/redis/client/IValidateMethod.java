/*
 * Copyright (c) 2016, Tsoft and/or its affiliates. All rights reserved.
 * FileName: IValidateMethod.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package io.github.ningyu.redis.client;

public interface IValidateMethod {
	
	public boolean isRight(String type);
	
	public boolean doValidate(String key,String nameSpace,String type,Object... context);

}
