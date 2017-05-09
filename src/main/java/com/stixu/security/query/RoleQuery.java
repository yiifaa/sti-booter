/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.query;

import com.stixu.query.PageQuery;
import com.stixu.security.domain.Role;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 下午10:24:04 
 */
public class RoleQuery extends PageQuery<Role> {

	/* (non-Javadoc)
	 * @see com.stixu.query.PageQuery#initSearchForm()
	 */
	@Override
	protected Role initSearchForm() {
		return new Role();
	}

}
