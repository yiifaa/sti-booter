/*
 * (c) Copyright 2016 STI技术小组
 * http://www.stixu.com
 */
package com.stixu.security.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.stixu.persistence.GenericDao;
import com.stixu.persistence.impl.GenericServiceImpl;
import com.stixu.security.domain.Role;
import com.stixu.security.repository.RoleRepository;
import com.stixu.security.service.RoleService;

/**
 * @since 1.0 2016年11月5日,上午11:43:51
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role, String> implements RoleService {

	private RoleRepository roleDao;
	
	/**
	 * @param roleDao
	 */
	@Inject
	public RoleServiceImpl(RoleRepository roleDao) {
		super();
		this.roleDao = roleDao;
	}

	/* (non-Javadoc)
	 * @see com.stixu.commons.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Role, String> getDao() {
		return roleDao;
	}

	/* (non-Javadoc)
	 * @see com.stixu.commons.security.service.RoleService#findAllConfigs()
	 */
	@Override
	public List<ConfigAttribute> findAllConfigs() {
		Iterable<Role> allRoles = roleDao.findAll();
		List<ConfigAttribute> attributes = Lists.newArrayList();
		for(Role role : allRoles) {
			attributes.add(new SecurityConfig(role.getAuthority()));
		}
		return attributes;
	}

}
