/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.controller;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stixu.query.Pagination;
import com.stixu.security.domain.Account;
import com.stixu.security.query.AccountQuery;
import com.stixu.security.service.AccountService;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 上午10:01:32 
 */
@RestController
@RequestMapping("/security/account")
public class AccountController {
	
	private AccountService accountService;
	
	@Inject
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@RequestMapping("query")
	public Pagination<Account> query(@ModelAttribute AccountQuery query){
		return accountService.search(query);
	}
	
	/**
	 * 获取明细信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public Account detail(@PathVariable("id") String id) {
		return accountService.findById(id);
	}
	
	/**
	 * 保存对象
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Account save(@ModelAttribute Account account) {
		//	设置创建时间
		//account.setCreateTime(new Date());
		return accountService.save(account);
	}
	
}
