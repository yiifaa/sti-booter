/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stixu.persistence.GenericDao;
import com.stixu.persistence.impl.GenericServiceImpl;
import com.stixu.query.PageWrapper;
import com.stixu.query.Pagination;
import com.stixu.security.domain.Account;
import com.stixu.security.query.AccountQuery;
import com.stixu.security.repository.AccountRepository;
import com.stixu.security.service.AccountService;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 上午10:05:45 
 */
@Service("accountService")
public class AccountServiceImpl extends GenericServiceImpl<Account, String> implements AccountService {

	private AccountRepository dao;
	
	/**
	 * @param dao
	 */
	@Inject
	public AccountServiceImpl(AccountRepository dao) {
		super();
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.stixu.security.service.AccountService#statByUsername()
	 */
	@Override
	public List<Object[]> statByUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.stixu.security.service.AccountService#query(com.stixu.security.query.AccountQuery)
	 */
	@Override
	public Pagination<Account> query(AccountQuery accountQuery) {
		Pageable pageQuery = accountQuery.toPager();
		Page<Account> result = findAll(pageQuery);
		PageWrapper<Account> wrapper = PageWrapper.of(result);
		if(wrapper !=null) {
			return wrapper.toPagination();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.stixu.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Account, String> getDao() {
		return dao;
	}	

}
