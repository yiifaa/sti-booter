/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu;

import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月13日 ： 下午5:46:41 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/META-INF/springContext/application-global-context.xml"
})

public class StixuTest {
	
	@Resource
	private ApplicationContext applicationContext;

	@Test
	public void test() {
		assertThat(applicationContext, IsNull.notNullValue());
	}

}
