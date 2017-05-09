/*
 * (c) Copyright 2016 STI技术小组
 * http://www.stixu.com
 */
package com.stixu.security.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.stixu.AppConstants;
import com.stixu.utils.MessageContextUtils;
import com.stixu.persistence.GenericDao;
import com.stixu.persistence.impl.GenericServiceImpl;
import com.stixu.security.DefaultSecurity;
import com.stixu.security.domain.Menu;
import com.stixu.security.repository.MenuRepository;
import com.stixu.security.service.MenuService;

/**
 * @since 1.0 2016年11月5日,下午6:10:50
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
@Service("menuService")
public class MenuServiceImpl extends GenericServiceImpl<Menu, String> implements MenuService {

	private MenuRepository menuDao;
	
	private DefaultSecurity defaultMenus;
	
	/**
	 * @param menuDao
	 */
	@Inject
	public MenuServiceImpl(MenuRepository menuDao) {
		super();
		this.menuDao = menuDao;
		//	this.defaultMenus = defaultMenus;
	}


	/* (non-Javadoc)
	 * @see com.stixu.commons.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Menu, String> getDao() {
		return menuDao;
	}


	/* (non-Javadoc)
	 * @see com.stixu.commons.security.service.MenuService#findAllConfigAttributes()
	 */
	@Override
	public List<Pair<AntPathRequestMatcher, List<ConfigAttribute>>> findAllConfigAttributes() {
		List<Pair<AntPathRequestMatcher, List<ConfigAttribute>>> configs = Lists.newArrayList(); 
		//
		AntPathRequestMatcher matcher = new AntPathRequestMatcher("/app/home");
		List<ConfigAttribute> attributes  = Lists.newArrayList((ConfigAttribute)new SecurityConfig("ROLE_ADMIN"));
		configs.add(Pair.of(matcher, attributes));
		//
		return configs;
	}


	/* (non-Javadoc)
	 * @see com.stixu.commons.security.service.MenuService#findAllMenus()
	 */
	@Override
	public List<Menu> findTreeMenu() {
		List<Menu> allMenus = menuDao.findAllMenus();
		if(allMenus == null) {
			allMenus = Lists.newArrayList();
		}
		//	添加根节点
		Menu rootMenu = new Menu();
		rootMenu.setId(AppConstants.ROOT_MENU_ID);
		rootMenu.setTitle(MessageContextUtils.getMessage("stixu.menu.rootText"));
		rootMenu.setParentId(MessageContextUtils.getMessage("stixu.menu.rootParentId"));
		allMenus.add(0, rootMenu);
		return allMenus;
	}


	/* (non-Javadoc)
	 * @see com.stixu.commons.security.service.MenuService#findRegisterMenu()
	 */
	@Override
	public List<List<Menu>> findRegisterMenu() {
		List<Menu> defaultMenus = this.defaultMenus.getDefaultMenus();
		List<Menu> sysMenus = menuDao.findAllMenus();
		List<List<Menu>> allMenus = Lists.newArrayList();
		allMenus.add(defaultMenus);
		allMenus.add(sysMenus);
		return allMenus;
	}

}
