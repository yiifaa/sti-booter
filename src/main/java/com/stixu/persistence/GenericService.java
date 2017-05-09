/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.persistence;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 通用的服务提供类
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月2日 下午2:57:32
 */
@NoRepositoryBean
public interface GenericService<E, ID extends Serializable> {
	
	/**
	 * 返回所有的对象数量
	 */
	long count();
	
	/**
	 * 根据主键删除对象
	 */
	void delete(ID id);
	
	/**
	 * 删除对象
	 */
	void delete(E entity);
	
	/**
	 * 批量删除对象
	 */
	void delete(Iterable<? extends E> entities);
	
	/**
	 * 删除所有的对象实例
	 */
	void deleteAll();
	
	/**
	 * 判断是否存在此id的对象
	 * 
	 */
	boolean exists(ID id);
	
	/**
	 * 通过ID查找数据
	 */
	E findById(ID id);
	
	/**
	 * 通过所有的ID查找相关的对象
	 * @param ids
	 * @return
	 */
	Iterable<E> findAll(Iterable<ID> ids);
	
	/**
	 * 查找所有的对象
	 */
	Iterable<E> findAll();
	
	/**
	 * 查找所有的对象
	 */
	Iterable<E> findAll(Sort sort);
	
	/**
	 * 通过分页查找所有的对象
	 */
	Page<E> findAll(Pageable pageable);
	
	/**
	 * 根据主键查找对象
	 */
	E findOne(ID id);
	
	/**
	 * 保存对象实例
	 */
	<S extends E> S save(S entity);
	
	/**
	 * 批量保存对象实例
	 */
	<S extends E> Iterable<S> save(Iterable<S> entities);
}
