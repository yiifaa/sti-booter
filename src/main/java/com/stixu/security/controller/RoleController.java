/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stixu.query.Pagination;
import com.stixu.security.domain.Role;
import com.stixu.security.query.RoleQuery;
import com.stixu.security.service.RoleService;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 下午10:24:45 
 */
@RestController
@RequestMapping("/security/role")
public class RoleController {

	private RoleService roleService;

	/**
	 * @param roleService
	 */
	@Inject
	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}
	
	@RequestMapping("query")
	public Pagination<Role> query(@ModelAttribute RoleQuery query){
		return roleService.query(query);
	}
	
	
}
