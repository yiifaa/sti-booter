/*
 * (c) Copyright 2016 STI技术小组
 * http://www.stixu.com
 */
package com.stixu.security;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.stixu.security.service.MenuService;
import com.stixu.security.service.RoleService;

/**
 * @since 1.0 2016年11月5日,下午5:49:47
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private RoleService roleService;
	
	private MenuService menuService;
	
	/**
	 * @param roleService
	 * @param menuService
	 */
	public UrlFilterInvocationSecurityMetadataSource(RoleService roleService, MenuService menuService) {
		super();
		this.roleService = roleService;
		this.menuService = menuService;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return roleService.findAllConfigs();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		List<Pair<AntPathRequestMatcher, List<ConfigAttribute>>> configs = menuService.findAllConfigAttributes();
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		for(Pair<AntPathRequestMatcher, List<ConfigAttribute>> config : configs) {
			if(config.getLeft().matches(request)) {
				return config.getRight();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
