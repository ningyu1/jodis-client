/*
 * Copyright (c) 2016, Tsoft and/or its affiliates. All rights reserved.
 * FileName: ReadMethodTags.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 功能描述： 根据方法名判断是否是读操作
 * 
 * @version 1.0.0
 */

public class ReadMethodTags {
    /**
     * setReadTags
     */
    private static Set<String> setReadTags = new HashSet<String>();

    static {
        setReadTags.add("sort");
        setReadTags.add("get");
        setReadTags.add("exists");
        setReadTags.add("type");
        setReadTags.add("ttl");
        setReadTags.add("mget");
        setReadTags.add("keys");
        setReadTags.add("getbit");
        setReadTags.add("getrange");
        setReadTags.add("substr");
        setReadTags.add("hget");
        setReadTags.add("hmget");
        setReadTags.add("hexists");
        setReadTags.add("hlen");
        setReadTags.add("hkeys");
        setReadTags.add("hvals");
        setReadTags.add("hgetAll");
        setReadTags.add("llen");
        setReadTags.add("lrange");
        setReadTags.add("lindex");
        setReadTags.add("smembers");
        setReadTags.add("sismember");
        setReadTags.add("scard");
        setReadTags.add("srandmember");
        setReadTags.add("zrank");
        setReadTags.add("zrevrank");
        setReadTags.add("zrange");
        setReadTags.add("zrevrange");
        setReadTags.add("zcard");
        setReadTags.add("zscore");
        setReadTags.add("zcount");
        setReadTags.add("zrangeByScore");
        setReadTags.add("zrevrangeByScore");
        setReadTags.add("zrangeWithScores");
        setReadTags.add("zrevrangeWithScores");
        setReadTags.add("zrangeByScoreWithScores");
        setReadTags.add("zrevrangeByScoreWithScores");
    }

    /**
     * 
     * 功能描述：判断是否是读方法
     * 
     * @param methodName 参数说明 返回值: 类型 <说明>
     * @return boolean 返回值
     */
    public static boolean isReadMethod(String methodName) {
        return setReadTags.contains(methodName);
    }
}
