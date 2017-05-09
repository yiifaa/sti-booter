/*
 * (c) Copyright 2016 STI技术小组
 * http://www.stixu.com
 */
package com.stixu.security.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.stixu.persistence.GenericService;
import com.stixu.security.domain.Menu;

/**
 * @since 1.0 2016年11月5日,下午6:10:04
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
public interface MenuService extends GenericService<Menu, String> {
	
	/**
	 * 获取树形菜单，用于菜单维护功能
	 * @return
	 */
	List<Menu> findTreeMenu();
	
	/**
	 * 获取所有需要路由注册的菜单，由两部分组成
	 * 1. 系统通用菜单，不在菜单上显示
	 * 2. 系统功能菜单，用于系统的菜单显示，支持三级菜单
	 * @return
	 */
	List<List<Menu>> findRegisterMenu();

	/**
	 * 
	 * @return
	 */
	List<Pair<AntPathRequestMatcher, List<ConfigAttribute>>> findAllConfigAttributes();
	
}
