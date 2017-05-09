/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stixu.persistence.GenericDao;
import com.stixu.persistence.impl.GenericServiceImpl;
import com.stixu.security.domain.Account;
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
	
	private PasswordEncoder encoder;
	
	/**
	 * @param dao
	 */
	@Inject
	public AccountServiceImpl(AccountRepository dao, PasswordEncoder encoder) {
		super();
		this.dao = dao;
		this.encoder = encoder;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Account> accounts = dao.findByUsername(username);
		return accounts.isEmpty() ? null : accounts.get(0);
	}
	
	

	/* (non-Javadoc)
	 * @see com.stixu.persistence.impl.GenericServiceImpl#save(java.lang.Object)
	 */
	@Override
	public <S extends Account> S save(S entity) {
		String password = entity.getPassword();
		password = encoder.encode(password);
		entity.setPassword(password);
		return super.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.stixu.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Account, String> getDao() {
		return dao;
	}	

}
