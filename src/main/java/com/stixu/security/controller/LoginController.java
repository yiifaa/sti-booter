/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stixu.security.domain.LoginState;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 下午2:28:46 
 */
@RestController
public class LoginController {
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/notLogon")
	public LoginState login() {
		LoginState state = new LoginState();
		return state;
	}
	
}
