/*
 * Copyright (c) 2016, Tsoft and/or its affiliates. All rights reserved.
 * FileName: CallBack.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.client;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author v_pinzhao
 * 
 * @param <R> 泛型对象
 */
public interface CallBack<R> {

    /**
     * 执行回调方法，调用jedis的实例，返回相应操作的结果。 R为返回值，可能为String, Object等
     * 
     * @param jedis cache object
     * @return result
     */
    R invoke(Jedis jedis);
    
}
