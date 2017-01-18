/*
 * Copyright (c) 2016, Tsoft and/or its affiliates. All rights reserved.
 * FileName: PoolConfig.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.config;

/**
 * 池配置
 * 
 * @author ningyu
 * 
 */
public class PoolConfig {

    /**
     * maxIdle
     */
    private String maxIdle;
    /**
     * minIdle
     */
    private String minIdle;
    /**
     * maxActive
     */
    private String maxActive;
    
    /**
     * maxTotal
     * */
    private String maxTotal;    
    
    /**
     * maxWait
     */
    private String maxWait;
    /**
     * blockWhenExhausted
     */
    private String blockWhenExhausted;
    /**
     * testOnBorrow
     */
    private String testOnBorrow;
    /**
     * testOnReturn
     */
    private String testOnReturn;
    /**
     * testWhileIdle
     */
    private String testWhileIdle;
    /**
     * timeBetweenEvictionRunsMillis
     */
    private String timeBetweenEvictionRunsMillis;
    /**
     * numTestsPerEvictionRun
     */
    private String numTestsPerEvictionRun;
    /**
     * minEvictableIdleTimeMillis
     */
    private String minEvictableIdleTimeMillis;
    /**
     * softMinEvictableIdleTimeMillis
     */
    private String softMinEvictableIdleTimeMillis;
    /**
     * lifo
     */
    private String lifo;

    /**
     * 获取最大空闲连接数
     * 
     * @return String 最大空闲连接数
     */
    public String getMaxIdle() {
        return maxIdle;
    }

    /**
     * 设置最大空闲连接数
     * 
     * @param maxIdle 最大空闲连接数
     */
    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    /**
     * 返回最小空闲连接数
     * 
     * @return String 最小空闲链接数
     */
    public String getMinIdle() {
        return minIdle;
    }

    /**
     * 设置最小空闲链接数
     * 
     * @param minIdle 最小空闲链接数
     */
    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    /**
     * 获取最大活动连接数
     * 
     * @return String 最大活动连接数
     */
    public String getMaxActive() {
        return maxActive;
    }

    /**
     * 设置最大活动连接数
     * 
     * @param maxActive 最大活动连接数
     */
    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    /**
     * 获取最大等待时间
     * 
     * @return String 最大等待时间
     */
    public String getMaxWait() {
        return maxWait;
    }

    /**
     * 设置最大等待时间
     * 
     * @param maxWait 最大等待时间
     */
    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }
    
    /**
     * 返回数据库忙时的动作
     * 
     * @return String 数据库忙时的动作
     */   
    public String getBlockWhenExhausted() {
		return blockWhenExhausted;
	}
    /**
     * 设置数据库忙时的动作
     * 
     * @param whenExhaustedAction 数据库忙时的动作
     */
	public void setBlockWhenExhausted(String blockWhenExhausted) {
		this.blockWhenExhausted = blockWhenExhausted;
	}

	/**
     * 测试连接
     * 
     * @return String 测试连接结果
     */
    public String getTestOnBorrow() {
        return testOnBorrow;
    }

    /**
     * 设置测试连接
     * 
     * @param testOnBorrow 是否测试连接
     */
    public void setTestOnBorrow(String testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    /**
     * 测试连接后是否返回一个连接
     * 
     * @return String 数据库连接
     */
    public String getTestOnReturn() {
        return testOnReturn;
    }

    /**
     * 设置测试连接后是否返回一个连接
     * 
     * @param testOnReturn 测试连接后是否返回一个连接
     */
    public void setTestOnReturn(String testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    /**
     * 返回空闲时检测连接结果
     * 
     * @return String 空闲时检测连接结果
     */
    public String getTestWhileIdle() {
        return testWhileIdle;
    }

    /**
     * 设置是否空闲时检测连接
     * 
     * @param testWhileIdle 是否空闲时检测连接
     */
    public void setTestWhileIdle(String testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    /**
     * 获取闲置收回间隔
     * 
     * @return String 闲置收回间隔
     */
    public String getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    /**
     * 设置闲置收回间隔
     * 
     * @param timeBetweenEvictionRunsMillis 闲置收回间隔
     */
    public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    /**
     * 返回每次检查链接的数量
     * 
     * @return String 每次检查链接的数量
     */
    public String getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    /**
     * 设置每次检查链接的数量
     * 
     * @param numTestsPerEvictionRun 每次检查链接的数量
     */
    public void setNumTestsPerEvictionRun(String numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    /**
     * 获取最小连接闲置时间
     * 
     * @return String 最小连接闲置时间
     */
    public String getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    /**
     * 设置最小连接闲置时间
     * 
     * @param minEvictableIdleTimeMillis 最小连接闲置时间
     */
    public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    /**
     * 获取redis最小连接闲置时间
     * 
     * @return String redis最小连接闲置时间
     */
    public String getSoftMinEvictableIdleTimeMillis() {
        return softMinEvictableIdleTimeMillis;
    }

    /**
     * 设置redis最小连接闲置时间
     * 
     * @param softMinEvictableIdleTimeMillis 最小连接闲置时间
     */
    public void setSoftMinEvictableIdleTimeMillis(String softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    /**
     * 返回是否后进先出
     * 
     * @return String 是否后进先出
     */
    public String getLifo() {
        return lifo;
    }

    /**
     * 设置是否后进先出
     * 
     * @param lifo 后进先出
     */
    public void setLifo(String lifo) {
        this.lifo = lifo;
    }

	public String getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(String maxTotal) {
		this.maxTotal = maxTotal;
	}

}
