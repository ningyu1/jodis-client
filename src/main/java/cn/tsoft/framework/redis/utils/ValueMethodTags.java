/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: ValueMethodTags.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.utils;

import java.util.HashMap;

/**
 * 
 * 功能描述： 根据方法名判断是否是读操作
 * 
 * @version 1.0.0
 */

public class ValueMethodTags {
    /**
     * setValueTags
     */
    private static HashMap<String,String> valueTags = new HashMap<String,String>();

    /**
     * returnValueTags
     */
    private static HashMap<String,String> returnValueTags = new HashMap<String,String>();
    
    
    static {
    	valueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.set(String,String,String,int)","2");
    	valueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.set(String,String,String,String,String,long)","2");
    	valueTags.put("Boolean cn.tsoft.framework.redis.client.IRedisClient.setbit(String,String,long,boolean)","3");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.setrange(String,String,long,String)","3");
    	valueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.getSet(String,String,String)","2");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.setnx(String,String,String)","2");
    	valueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.setex(String,String,int,String)","3");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.hset(String,String,String,String)","3");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.hsetnx(String,String,String,String)","3");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.hsetnx(String,String,String,String)","3");
    	valueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.hmset(String,String,Map)","2");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.zadd(String,String,double,String)","3");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.hincrBy(String,String,String,long)","3");
    	valueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.lset(String,String,long,String)","3");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.lrem(String,String,long,String)","3");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.linsert(String,String,LIST_POSITION,String,String)","4");
    	valueTags.put("Boolean cn.tsoft.framework.redis.client.IRedisClient.setbit(String,String,long,String)","3");
    	valueTags.put("void cn.tsoft.framework.redis.client.IRedisClient.set(String,String,Object,int)","2");
    	valueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.mset(Map,String)","0");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IRedisClient.hsetObject(String,String,String,Object)","3");
    	valueTags.put("Long cn.tsoft.framework.redis.client.IBinaryRedisClient.hsetObject(String,String,String,Object)","3");
    	
    	//返回方法值
    	String value = "1";
    	returnValueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.get(String,String,GetDataCallBack)", value);
    	returnValueTags.put("Boolean cn.tsoft.framework.redis.client.IRedisClient.getbit(String,String,long)", value);
    	returnValueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.getrange(String,String,long,long)", value);
    	returnValueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.substr(String,String,int,int)", value);
    	returnValueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.hget(String,String,String,GetDataCallBack)", value);
    	returnValueTags.put("List cn.tsoft.framework.redis.client.IRedisClient.hmget(String,String,String[])", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.hkeys(String,String)", value);
    	returnValueTags.put("List cn.tsoft.framework.redis.client.IRedisClient.hvals(String,String)", value);
    	returnValueTags.put("Map cn.tsoft.framework.redis.client.IRedisClient.hgetAll(String,String)", value);
    	returnValueTags.put("List cn.tsoft.framework.redis.client.IRedisClient.lrange(String,String,long,long)", value);
    	returnValueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.ltrim(String,String,long,long)", value);
    	returnValueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.lindex(String,String,long)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.smembers(String,String)", value);
    	returnValueTags.put("String cn.tsoft.framework.redis.client.IRedisClient.srandmember(String,String)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrange(String,String,long,long)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrevrange(String,String,long,long)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrangeWithScores(String,String,long,long)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrevrangeWithScores(String,String,long,long)", value);
    	returnValueTags.put("List cn.tsoft.framework.redis.client.IRedisClient.sort(String,String)", value);
    	returnValueTags.put("List cn.tsoft.framework.redis.client.IRedisClient.sort(String,String,SortingParams)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrangeByScore(String,String,double,double)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrevrangeByScore(String,String,double,double)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrangeByScore(String,String,double,double,int,int)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrevrangeByScore(String,String,double,double,int,int)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrangeByScoreWithScores(String,String,double,double)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrevrangeByScoreWithScores(String,String,double,double)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrangeByScoreWithScores(String,String,double,double)", value);
    	returnValueTags.put("Set cn.tsoft.framework.redis.client.IRedisClient.zrevrangeByScoreWithScores(String,String,double,double,int,int)", value);
    	returnValueTags.put("Object cn.tsoft.framework.redis.client.IRedisClient.get(String,String,Class,GetDataCallBack)", value);
    	returnValueTags.put("Object cn.tsoft.framework.redis.client.IRedisClient.get(String,String,TypeReference,GetDataCallBack)", value);
    	returnValueTags.put("List cn.tsoft.framework.redis.client.IRedisClient.mget(String[],String)", value);
    	returnValueTags.put("Object cn.tsoft.framework.redis.client.IRedisClient.hgetObject(String,String,String,Class,GetDataCallBack)", value);
    	returnValueTags.put("Object cn.tsoft.framework.redis.client.IRedisClient.hgetObject(String,String,String,TypeReference,GetDataCallBack)", value);
    	returnValueTags.put("List cn.tsoft.framework.redis.client.IRedisClient.hvalsObject(String,String,Class)", value);
    	returnValueTags.put("List cn.tsoft.framework.redis.client.IRedisClient.hvalsObject(String,String,TypeReference)", value);
    	returnValueTags.put("Map cn.tsoft.framework.redis.client.IBinaryRedisClient.hgetAllObjects(String,String)", value);
    	returnValueTags.put("Object cn.tsoft.framework.redis.client.IBinaryRedisClient.hgetObject(String,String,String)", value);

    }

    /**
     * 
     * 功能描述：判断是否是读方法
     * 
     * @param methodName 参数说明 返回值: 类型 <说明>
     * @return boolean 返回值
     */
    public static Integer isValueMethod(String methodName) {
    	String num = valueTags.get(methodName);
    	Integer res = null;
    	try{
    		if(null!=num){
    			res = Integer.parseInt(num);
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
        return res;
    }
    
    /**
     * 
     * 判断是否设置值方法
     * */
    public static boolean isSetValueMethod(String methodName){
    	return valueTags.containsKey(methodName);
    }
    
    /**
     * 
     * 判断是否返回值方法
     * */
    public static boolean isGetValueMethod(String methodName){
    	return returnValueTags.containsKey(methodName);
    }
    
    /**
     * 
     * 判断是否能够属于值业务操作方法
     * */
    public static boolean isContainsValueMethod(String methodName){
    	return returnValueTags.containsKey(methodName)||valueTags.containsKey(methodName);
    }       
}
