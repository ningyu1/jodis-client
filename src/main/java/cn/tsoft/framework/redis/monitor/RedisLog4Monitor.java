/*
 * Copyright (c) 2016, Jiuye SCM and/or its affiliates. All rights reserved.
 * FileName: RedisLog4Monitor.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.redis.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisLog4Monitor {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RedisLog4Monitor.class);
    
    public static void outPut(String logInfo){
    	logger.debug(logInfo);
    }
    
}
