package com.stixu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ganhuan
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.stixu",
	includeFilters = {@Filter(Controller.class), @Filter(RestController.class)}
)
public class AppBooter extends SpringBootServletInitializer {
    
    /**
	 * 添加自身的配置信息
     * @param application
     * @return 
	 */
        @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(AppBooter.class);
	}
        
    /**
	 * 启动应用程序
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppBooter.class, args);
	}
}
