/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: DefaultClientImpl.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.client.impl;

import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import cn.tsoft.framework.redis.client.CallBack;
import cn.tsoft.framework.redis.config.PoolConfig;
import cn.tsoft.framework.redis.utils.RedisNode;


/**
 * 默认的客户端实现
 * 
 * @author rocky hsu
 * 
 */
public class DefaultClientImpl {

    /**
     * 字符串常量
     */
    public static final String UNSUPPORT = "Current configuration does not support this operation";

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(DefaultClientImpl.class);

    /**
     * zk地址
     */
    private String zkAddress;
    
    /**
     * codis项目名称
     * */
    private String codisProjectName;
    
    /**
     * jedisPool
     * */
    protected JedisResourcePool jedisPool;
    
    /**
     * zk中存储proxy的地址
     * */
    private String codisZkContent = "/jodis/xxx";
    
    private Integer zkSessionTimeoutMs = 30000;
    
//    private ScheduledExecutorService executorService;    
    
    //没有数据上传报警时间间隔
//    private final  int noPoolAlarmInterval = 60;
    
    /**
     * pool配置
     * */    
    private PoolConfig poolConfig;    

	/**
     * 默认使用codis，服务端分片
     * */
    protected boolean isSharding() {
		return true;
	}

	public String getZkAddress() {
		return zkAddress;
	}

	public void setZkAddress(String zkAddress) {
		this.zkAddress = zkAddress;
	}


	public String getCodisProjectName() {
		return codisProjectName;
	}

	public void setCodisProjectName(String codisProjectName) {
		this.codisProjectName = codisProjectName;
	}

	public Integer getZkSessionTimeoutMs() {
		return zkSessionTimeoutMs;
	}

	public void setZkSessionTimeoutMs(Integer zkSessionTimeoutMs) {
		this.zkSessionTimeoutMs = zkSessionTimeoutMs;
	}
	
	public DefaultClientImpl(){
		//init();
	}

	public DefaultClientImpl(JedisResourcePool jedisPool){
		this.jedisPool = jedisPool;
	}	

    public PoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(PoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}	
	
	public void setCodisZkContent(String codisZkContent) {
		this.codisZkContent = codisZkContent;
	}

	/**
     * 初始化
     */
    public synchronized void init() {
    	JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    	if(null!=poolConfig){
    		this.setPoolParameters(poolConfig, jedisPoolConfig);
    	}
    	jedisPool = RoundRobinJedisPool.create().poolConfig(jedisPoolConfig)
		        .curatorClient(this.getZkAddress(), this.getZkSessionTimeoutMs()).zkProxyDir(this.getCodisZkContent()).build();
//    	jedisPool = new RoundRobinJedisPool(this.getZkAddress(),
//    			this.getZkSessionTimeoutMs(), this.getCodisZkContent(), jedisPoolConfig);
        
    	//TODO 定时任务是否有必要？
//    	if (executorService == null) {
//            executorService = Executors.newScheduledThreadPool(1);
//            // 定时检查，每分钟检查一次zk的watch点是否异常。
//            executorService.scheduleAtFixedRate(checkPoolEmptyAlarmEvent,
//                    CacheUtils.nextDelayTime(noPoolAlarmInterval), noPoolAlarmInterval,TimeUnit.SECONDS);
//        }
    }

    public JedisResourcePool getJedisPool() {
		return jedisPool;
	}

	/**
     * 获取codis中zk位置
     * */
    private String getCodisZkContent() {
		return codisZkContent.replace("xxx", this.getCodisProjectName());
	}

    /**
     * 处理连接池信息
     * 
     * @param poolConfig 参数说明
     * @param config 参数说明
     */
    private void setPoolParameters(PoolConfig poolConfig, JedisPoolConfig config) {
    	
        if (!StringUtils.isBlank(poolConfig.getMaxIdle())) {
        	// 获取最大空闲连接数
            config.setMaxIdle(Integer.valueOf(poolConfig.getMaxIdle()));
        }    
        
        if (!StringUtils.isBlank(poolConfig.getMinIdle())) {
        	// 获取最小空闲连接数
            config.setMinIdle(Integer.valueOf(poolConfig.getMinIdle()));
        }           
        
        if (!StringUtils.isBlank(poolConfig.getMaxTotal())) {
        	// 获取最大连接数
            config.setMaxTotal(Integer.valueOf(poolConfig.getMaxTotal()));
        }  
        
        if (!StringUtils.isBlank(poolConfig.getTestOnBorrow())) {
            // 获取连接池是否检测可用性
            config.setTestOnBorrow(Boolean.valueOf(poolConfig.getTestOnBorrow()));
        }

        if (!StringUtils.isBlank(poolConfig.getTestOnReturn())) {
            // 归还时是否检测可用性
            config.setTestOnReturn(Boolean.valueOf(poolConfig.getTestOnReturn()));
        }
        if (!StringUtils.isBlank(poolConfig.getTestWhileIdle())) {
            // 空闲时是否检测可用性
            config.setTestWhileIdle(Boolean.valueOf(poolConfig.getTestWhileIdle()));
        } else {
        	config.setTestWhileIdle(true);
        }
        if (!StringUtils.isBlank(poolConfig.getBlockWhenExhausted())) {
            config.setBlockWhenExhausted(Boolean.valueOf(poolConfig.getBlockWhenExhausted()));
        }
        if (!StringUtils.isBlank(poolConfig.getTimeBetweenEvictionRunsMillis())) {
            config.setTimeBetweenEvictionRunsMillis(Long.valueOf(poolConfig.getTimeBetweenEvictionRunsMillis()));
        } else {
        	config.setTimeBetweenEvictionRunsMillis(30000L);
        }
        if (!StringUtils.isBlank(poolConfig.getNumTestsPerEvictionRun())) {
            config.setNumTestsPerEvictionRun(Integer.valueOf(poolConfig.getNumTestsPerEvictionRun()));
        } else {
        	config.setNumTestsPerEvictionRun(-1);
        }
        if (!StringUtils.isBlank(poolConfig.getMinEvictableIdleTimeMillis())) {
            config.setMinEvictableIdleTimeMillis(Integer.valueOf(poolConfig.getMinEvictableIdleTimeMillis()));
        } else {
            config.setMinEvictableIdleTimeMillis(60000L);
        }
        if (!StringUtils.isBlank(poolConfig.getSoftMinEvictableIdleTimeMillis())) {
            config.setSoftMinEvictableIdleTimeMillis(Integer.valueOf(poolConfig.getSoftMinEvictableIdleTimeMillis()));
        }
    }    
    
	/**
     * 刷新配置
     * 
     * @param config 配置参数
     */
    public synchronized void refresh(String config) {
    	
    }

    /**
     * flush数据
     * 
     * @return 返回"ok"表示成功
     */
    public String flushDB() {
        return "OK";
    }

	/**
	 * 
	 * 功能描述：invoke
	 * 
	 * @param jedisPool
	 *            参数说明 返回值: 类型 <说明>
	 * @param callBack
	 *            参数说明 返回值: 类型 <说明>
	 * @param <R>
	 *            泛型对象爱内阁
	 * @return R 返回值
	 */
	private <R> R invoke(Jedis jedis, CallBack<R> callBack) {
		boolean isClosed = false;
		try {
			// 获取连接
			return callBack.invoke(jedis);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof JedisConnectionException) {
				if (jedis != null) {
					try {
						// 处理无效连接
						jedis.close();
						isClosed = true;
					} catch (JedisException e1) {
						logger.error(e1.getMessage(), e1);
					}
				}
			}
		} finally {
			if (!isClosed) {
				if (jedis != null) {
					try {
						// 归还连接
						jedis.close();
					} catch (JedisException e1) {
						logger.error(e1.getMessage(), e1);
					}
				}
			}
		}
		return null;
	}    
    
	/**
	 * 
	 * 功能描述：invoke
	 * 
	 * @param jedisPool
	 *            参数说明 返回值: 类型 <说明>
	 * @param callBack
	 *            参数说明 返回值: 类型 <说明>
	 * @param <R>
	 *            泛型对象爱内阁
	 * @return R 返回值
	 */
	private <R> R invoke(JedisResourcePool jedisPool, CallBack<R> callBack) {
		boolean isClosed = false;
		Jedis jedis = null;
		try {
			// 获取连接
			jedis = jedisPool.getResource();
			return callBack.invoke(jedis);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof JedisConnectionException) {
				if (jedis != null) {
					try {
						// 处理无效连接
						jedis.close();
						isClosed = true;
					} catch (JedisException e1) {
						logger.error(e1.getMessage(), e1);
					}
				}
			}
		} finally {
			if (!isClosed) {
				if (jedis != null) {
					try {
						// 归还连接
						jedis.close();
					} catch (JedisException e1) {
						logger.error(e1.getMessage(), e1);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 功能描述：<br>
	 * execute
	 * 
	 * @param callBack
	 *            参数说明
	 * @param <R>
	 *            结果泛型对象
	 * @return R 返回值
	 */
	public <R> R execute(JedisResourcePool jedisPool, CallBack<R> callBack,boolean proxyFlag) {
		return invoke(jedisPool, callBack); 
/*		// 获取上一方法名
		String preMethodName = new Exception().getStackTrace()[2]
				.getMethodName();
		// 若是读方法
		if (ReadMethodTags.isReadMethod(preMethodName)) {
			try {
				return invoke(jedisPool, callBack);
			} catch (Exception e) {
				throw e;
			}
		} else {
			R r = null;
			try {
				// 执行
				r = invoke(jedisPool, callBack);
			} catch (Exception e) {
			}
			return r;
		}*/
//		if(proxyFlag){
//			List<RedisNode> redisNodes;
//			if(!(jedisPool instanceof RoundRobinJedisPool)){
//				logger.error("type of jedisPool Exception");
//				return null;
//			}
//			redisNodes = ((RoundRobinJedisPool) jedisPool).getAllGroupMasterInfo(this.getCodisProjectName());
//			if(null==redisNodes){
//				logger.error("cant't get redisNodes");
//				return null;				
//			}
//			ExecutorService service = Executors.newFixedThreadPool(redisNodes.size());
//			try {
//				for(RedisNode redisNode:redisNodes){
//					service.submit(new RedisCallable(redisNode,callBack));
//				}
//				service.shutdown();
//				while(!service.awaitTermination(1, TimeUnit.MILLISECONDS)){
//				}
//			} catch (Exception e) {
//				logger.error("executor do redis error:",e);
//			}
//			return null;
//		}else{
//			return invoke(jedisPool, callBack); 
//		}
	}
	
    // 定时检查
//    Runnable checkPoolEmptyAlarmEvent = new Runnable() {
//        @Override
//        public void run() {
//            try {
//            	((RoundRobinJedisPool)jedisPool).checkPoolSize();
//            } catch (Exception e) {
//                logger.error("checkPoolEmptyAlarmEvent Error:"+e.toString());
//            }
//        }
//    };
    
    class RedisCallable<R> implements Callable<R> {
    	
    	public RedisNode redisNode;
    	
    	public CallBack<R> callBack;
    	
    	public RedisCallable(RedisNode redisNode,CallBack<R> callBack){
    		this.redisNode = redisNode;
    		this.callBack = callBack;
    	}
    	
		@Override
		public R call() throws Exception {
			String[] address = redisNode.getAddr().split(":");
			Jedis jedis = new Jedis(address[0],Integer.parseInt(address[1]));
			return invoke(jedis,callBack);
		}

    	
    }
}
