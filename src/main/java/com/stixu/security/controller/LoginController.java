/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.controller;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping(value="/login")
public class LoginController {
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/index")
	public LoginState login(HttpServletRequest request) {
		LoginState state = new LoginState(request.getContextPath());
		return state;
	}
	
	/**
	 * 登陆成功
	 * @return
	 */
	@RequestMapping(value="/success")
	public LoginState success(HttpServletRequest request) {
		LoginState state = new LoginState(request.getContextPath());
		state.setLogon(true);
		return state;
	}
	
	/**
	 * 登陆成功
	 * @return
	 */
	@RequestMapping(value="/fail")
	public LoginState fail(HttpServletRequest request) {
		LoginState state = new LoginState(request.getContextPath());
		state.setLogon(false);
		return state;
	}
	
}
