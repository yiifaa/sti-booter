/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stixu.persistence.GenericDao;
import com.stixu.security.domain.Account;

/**
 * 
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月1日 下午4:26:37
 */
@Repository("accountRepository")
public interface AccountRepository extends GenericDao<Account, String> {

	//@Query("select acc.username, count(acc.id) from Account acc group by acc.username")
	List<Object[]> statByUsername();
	
	@Query("select acc from Account acc where acc.username = ?1")
	List<Account> findByUsername(String username);
		
}
