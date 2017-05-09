/*
 * (c) Copyright 2016 STI技术小组
 * http://www.stixu.com
 */
package com.stixu.security;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.stixu.AppConstants;
import com.stixu.security.domain.Menu;

/**
 * @since 1.0 2016年11月12日,下午8:04:22
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
public class DefaultSecurity {

	/**
	 * 默认用户具有的菜单
	 */
	private List<Menu> defaultMenus;
	
	private List<String> superMenus;

	/**
	 * @param defaultMenus
	 * @param superMenus
	 */
	public DefaultSecurity(List<String> defaultMenus, List<String> superMenus) {
		this.defaultMenus = Lists.newArrayList();
		this.superMenus = superMenus;
		//添加默认菜单
		for(int i = 0; i< defaultMenus.size(); i ++) {
			String menu = defaultMenus.get(i);
			String[] urls = StringUtils.split(menu, ",");
			Menu defaultMenu = new Menu();
			defaultMenu.setId(AppConstants.ROOT_MENU_ID + i);
			defaultMenu.setUrl(urls[0]);
			defaultMenu.setRequire(urls[1]);
			//	默认匹配路径
			defaultMenu.setPattern(urls[0]);
			this.defaultMenus.add(defaultMenu);
		}
		for(String menu: superMenus) {
			System.out.println(menu);
		}
	}

	/**
	 * @return the defaultMenus
	 */
	public List<Menu> getDefaultMenus() {
		return defaultMenus;
	}
	
}
