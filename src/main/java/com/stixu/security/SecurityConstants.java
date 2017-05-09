/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.security;

/**
 * 
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月4日 下午4:31:57
 */
public interface SecurityConstants {
	
	/**
	 * 角色的前缀名称
	 */
	static final String ROLE_PREFIX = "ROLE_";

	/**
	 * Spring的安全上下文常量
	 */
	static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
	
	
	/**
	 * 超级用户
	 */
	static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
	
	/**
	 * 默认角色，每个用户都有
	 */
	static final String ROLE_DEFAULT = "ROLE_DEFAULT";
	
}
