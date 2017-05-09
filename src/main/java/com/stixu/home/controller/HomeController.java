/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.home.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

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
	public Map<String, String> index() {
		Map<String, String> result = Maps.newHashMap();
		result.put("username", "甘焕");
		return result;
	}

}
