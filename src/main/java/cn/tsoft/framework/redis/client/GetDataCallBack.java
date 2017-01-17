/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: GetDataCallBack.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.client;



/**
 * 
 * @author rocky hsu
 * 
 * @param <R> 泛型对象
 */
public interface GetDataCallBack<R> {
	
	/**
	 * 获取Jedis超时时间
	 * */
	int getExpiredTime();
	
    /**
     * 执行回调方法
     */
    R invoke();
}
