/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.security.query;

import com.stixu.query.PageQuery;
import com.stixu.security.domain.Account;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 上午9:59:29 
 */
public class AccountQuery extends PageQuery<Account> {

	/* (non-Javadoc)
	 * @see com.stixu.query.PageQuery#initSearchForm()
	 */
	@Override
	protected Account initSearchForm() {
		return new Account();
	}

}
