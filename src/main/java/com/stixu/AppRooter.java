/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

import com.stixu.security.service.AccountService;
import com.stixu.utils.ApplicationContextUtils;


/**
 * 开发日期：2017年5月8日 ： 下午9:49:51
 *
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
@SpringBootApplication
@ImportResource({"classpath:/META-INF/springContext/application-global-context.xml"})
public class AppRooter extends SpringBootServletInitializer {
	
	/**
	 * 添加自身的配置信息
     * @param application
     * @return 
	 */
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(AppRooter.class);
	}
    
    /**
   	 * 启动应用程序
   	 * @param args
   	 */
   	public static void main(String[] args) {
   		SpringApplication.run(AppRooter.class, args);
   		AccountService accountService = ApplicationContextUtils.getBean(AccountService.class);
   		//	System.out.println(accountService == null);
   	}

}
