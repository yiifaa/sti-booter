/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stixu.persistence.GenericDao;
import com.stixu.persistence.impl.GenericServiceImpl;
import com.stixu.query.PageWrapper;
import com.stixu.query.Pagination;
import com.stixu.query.PaginationBuilder;
import com.stixu.query.QueryTemplate;
import com.stixu.query.QueryType;
import com.stixu.security.domain.Account;
import com.stixu.security.query.AccountQuery;
import com.stixu.security.repository.AccountPaginationDao;
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
	
	private AccountPaginationDao accountDao;
	
	private PasswordEncoder encoder;
	
	/**
	 * @param dao
	 */
	@Inject
	public AccountServiceImpl(AccountRepository dao, AccountPaginationDao accountDao, PasswordEncoder encoder) {
		super();
		this.dao = dao;
		this.accountDao = accountDao;
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
		entity.setCreateTime(new Date());
		return super.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.stixu.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Account, String> getDao() {
		return dao;
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
	 * @see com.stixu.security.service.AccountService#search(com.stixu.security.query.AccountQuery)
	 */
	@Override
	@Transactional(readOnly=true)
	public Pagination<Account> search(AccountQuery accountQuery) {
		return accountDao.findDomainPage(QueryType.JQL, accountQuery, new PaginationBuilder<Account, AccountQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select acc from Account acc ");
			}

			@Override
			public void buildWhere(Account s, QueryTemplate qt) {
				//
				String username = s.getUsername();
				if(StringUtils.isNoneBlank(username)) {
					qt.append(" and acc.username like :username");
					qt.addParameter("username", QueryTemplate.buildAllLike(StringUtils.trim(username)));
				}
			}

			@Override
			public void buildBys(String column, String order, QueryTemplate qt) {
				qt.append(QueryTemplate.buildOrderBy("acc", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(acc) from Account acc");
			}

		});
	}

	/* (non-Javadoc)
	 * @see com.stixu.security.service.AccountService#persist(com.stixu.security.domain.Account)
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public Account persist(Account account) {
		if(account.isNew()) {
			this.accountDao.persist(account);
		} else {
			this.accountDao.merge(account);
		}
		//this.dao.save(account);
		return account;
	}
	
	

}
