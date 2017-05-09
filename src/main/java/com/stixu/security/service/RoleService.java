/*
 * (c) Copyright 2016 STI技术小组
 * http://www.stixu.com
 */
package com.stixu.security.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.access.ConfigAttribute;

import com.stixu.persistence.GenericService;
import com.stixu.security.domain.Role;

/**
 * @since 1.0 2016年11月5日,上午11:42:47
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
@NoRepositoryBean
public interface RoleService extends GenericService<Role, String> {
	
	/**
	 * 获取获取的角色
	 * @return
	 */
	List<ConfigAttribute> findAllConfigs();
	
}
