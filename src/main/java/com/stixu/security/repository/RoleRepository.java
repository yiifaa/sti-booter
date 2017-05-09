/*
 * (c) Copyright 2016 STI技术小组
 * http://www.stixu.com
 */
package com.stixu.security.repository;

import org.springframework.stereotype.Repository;

import com.stixu.persistence.GenericDao;
import com.stixu.security.domain.Role;

/**
 * @since 1.0 2016年11月5日,上午11:41:46
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
@Repository
public interface RoleRepository extends GenericDao<Role, String> {

}
