/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package com.stixu.query.templates;

import java.util.Map;

import com.stixu.query.QueryTemplate;

/**
 * @since 1.0 2014年8月29日,下午4:43:19
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class NamedQueryTemplate extends QueryTemplate {

	/**
	 * 
	 */
	public NamedQueryTemplate() {
		super();
	}

	/**
	 * @param query
	 * @param parameters
	 */
	public NamedQueryTemplate(String query, Map<String, Object> parameters) {
		super(query, parameters);
	}

	/**
	 * @param query
	 */
	public NamedQueryTemplate(String query) {
		super(query);
	}
	
}
