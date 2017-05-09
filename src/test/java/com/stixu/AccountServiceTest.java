/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stixu.security.service.AccountService;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 上午10:51:34 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/META-INF/springContext/application-global-context.xml"
})
public class AccountServiceTest {
	
	@Resource
	private AccountService accountService;

	@Test
	public void test() {
		assertThat(accountService, IsNull.notNullValue());
	}

}
