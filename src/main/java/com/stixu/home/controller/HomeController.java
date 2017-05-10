/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.home.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stixu.security.SecurityContextUtils;
import com.stixu.security.domain.Account;
import com.stixu.security.domain.LoginState;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 下午2:47:02 
 */
@RestController
public class HomeController {
	
	@RequestMapping(value="/home")
	public LoginState index() {
		LoginState state = new LoginState("app/home");
		//state.setUsername(SecurityContextUtils.getCurrentAccount(request));
		Account current = SecurityContextUtils.getCurrentAccount();
		if(current != null) {
			state.setUsername(current.getUsername());
		}
		//
		state.setStatus(1);
		state.setLogon(true);
		state.setDesc("登陆成功");
		return state;
	}

}
