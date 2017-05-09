/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.persistence;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 下午6:03:32 
 */
@NoRepositoryBean
public interface GenericService<E, ID extends Serializable> extends GenericDao<E, ID>{

}
