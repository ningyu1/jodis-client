/*
 * Copyright (c) 2016, Tsoft and/or its affiliates. All rights reserved.
 * FileName: RedisNode.java
 * Author:   ningyu
 * Date:     2016年12月30日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package io.github.ningyu.redis.utils;

public class RedisNode {
	public String type;
	public String group_id;
	public String addr;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
