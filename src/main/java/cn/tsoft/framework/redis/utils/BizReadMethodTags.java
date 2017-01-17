/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: BizReadMethodTags.java
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

public class BizReadMethodTags {
    /**
     * setReadTags
     */
    private static Set<String> setReadTags = new HashSet<String>();
    /**
     * setWriteTags
     */
    private static Set<String> setWriteTags = new HashSet<String>();    

    static {
        //read命令
    	setReadTags.add("get");
        setReadTags.add("mget");
        setReadTags.add("hget");
        setReadTags.add("hmget");
        setReadTags.add("hvals");
        setReadTags.add("hgetAll");
        setReadTags.add("getset");
        setReadTags.add("scard");

      //set命令
        setWriteTags.add("mset");
        setWriteTags.add("msetnx");
        setWriteTags.add("psetex");
        setWriteTags.add("setbit");
        setWriteTags.add("setex");
        setWriteTags.add("setnx");
        setWriteTags.add("setrange");
        setWriteTags.add("hmset");
        setWriteTags.add("hset");
        setWriteTags.add("hsetnx");
        setWriteTags.add("lset");
        setWriteTags.add("sadd");
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
    
    /**
     * 
     * 功能描述：判断是否是读方法
     * 
     * @param methodName 参数说明 返回值: 类型 <说明>
     * @return boolean 返回值
     */
    public static String getBizType(String methodName) {
    	String res = "other";
    	if(setReadTags.contains(methodName)){
    		res = "get";
    	}else if(setWriteTags.contains(methodName)){
    		res = "set";
    	}
    	return res;
    }
    
}
