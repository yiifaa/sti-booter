/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.persistence;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月2日 上午10:57:59
 */
@NoRepositoryBean
public interface GenericDao<E, ID extends Serializable> extends PagingAndSortingRepository<E, ID> {
	
	/**
     * 根据主键获取对象
     * @param pk
     * @return pk
     */
	E findById(ID id);
	
}
