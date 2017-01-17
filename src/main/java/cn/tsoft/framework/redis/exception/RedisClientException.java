/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: RedisClientException.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.exception;

/**
 * Redis客户端异常
 * 
 * @author ningyu
 * 
 */
public class RedisClientException extends RuntimeException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7460934076911268418L;

    /**
     * 构造方法
     * 
     * @param msg 异常信息
     */
    public RedisClientException(String msg) {
        super(msg);
    }

    /**
     * 构造方法
     * 
     * @param exception 异常原因
     */
    public RedisClientException(Throwable exception) {
        super(exception);
    }

    /**
     * 构造方法
     * 
     * @param mag 异常信息
     * @param exception 异常原因
     */
    public RedisClientException(String mag, Exception exception) {
        super(mag, exception);
    }
}
