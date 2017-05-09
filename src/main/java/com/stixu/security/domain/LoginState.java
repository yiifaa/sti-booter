/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.domain;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 下午2:29:55 
 */
public class LoginState {
	
	private int status = 403;
	
	private boolean logon = false;
	
	private String desc = "请登录";

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the logon
	 */
	public boolean isLogon() {
		return logon;
	}

	/**
	 * @param logon the logon to set
	 */
	public void setLogon(boolean logon) {
		this.logon = logon;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}	
