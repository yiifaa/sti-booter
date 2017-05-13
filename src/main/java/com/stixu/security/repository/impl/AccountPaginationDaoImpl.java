/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.repository.impl;

import org.springframework.stereotype.Repository;

import com.stixu.persistence.impl.PaginationDaoImpl;
import com.stixu.security.domain.Account;
import com.stixu.security.repository.AccountPaginationDao;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月13日 ： 下午6:54:49 
 */
@Repository
public class AccountPaginationDaoImpl extends PaginationDaoImpl<Account, String> implements AccountPaginationDao{

	/**
	 * @param persistentClass
	 */
	public AccountPaginationDaoImpl() {
		super(Account.class);
	}

}
