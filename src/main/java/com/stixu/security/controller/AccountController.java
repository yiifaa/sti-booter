/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return accountService.query(query);
	}
	
}
