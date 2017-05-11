/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.security;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.stixu.security.domain.Account;

/**
 * 
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月9日 下午3:20:28
 */
public class SecurityContextUtils {
	/**
	 * 阻止实例化
	 */
	private SecurityContextUtils() {
		
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public final static Account getCurrentAccount() {
		if(SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
			return null;
		}
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(obj instanceof Account) {
			return (Account)obj;
		}
		return null;
	}
	
	/**
	 * 通过Session获取当前用户
	 * @param session
	 * @return
	 */
	public final static Account getCurrentAccount(HttpSession session) {
	    Object obj = session.getAttribute(SecurityConstants.SPRING_SECURITY_CONTEXT);
	    if(obj instanceof SecurityContext) {
	        SecurityContext context = (SecurityContext)obj;
	        Object userDetail = context.getAuthentication().getPrincipal();
	    	if(userDetail instanceof Account) {
				return (Account)userDetail;
			}
        }
        return null;
	}
	
	/**
	 * 通过Request获取当前用户
	 * @param request
	 * @return
	 */
	public final static Account getCurrentAccount(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    return getCurrentAccount(session);
	}
}
