/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.security.service;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.stixu.persistence.GenericService;
import com.stixu.query.Pagination;
import com.stixu.security.domain.Account;
import com.stixu.security.query.AccountQuery;

/**
 * 
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月1日 下午4:30:14
 */
@NoRepositoryBean
public interface AccountService extends GenericService<Account, String>, UserDetailsService {
	
	/**
	 * 查询分页对象
	 * @param accountQuery
	 * @return
	 */
	Pagination<Account> query(AccountQuery accountQuery);
	
	/**
	 * 
	 * @param accountQuery
	 * @return
	 */
	Pagination<Account> search(AccountQuery accountQuery);
}
