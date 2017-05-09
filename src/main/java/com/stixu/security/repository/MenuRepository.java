/*
 * (c) Copyright 2016 STI技术小组
 * http://www.stixu.com
 */
package com.stixu.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stixu.persistence.GenericDao;
import com.stixu.security.domain.Menu;

/**
 * @since 1.0 2016年11月5日,下午6:08:59
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
@Repository
public interface MenuRepository extends GenericDao<Menu, String> {

	@Transactional(readOnly = true)
	@Query("select menu from Menu menu order by menu.order asc")
	List<Menu> findAllMenus();
	
}
