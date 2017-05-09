/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.persistence.impl;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.stixu.persistence.GenericDao;
import com.stixu.persistence.GenericService;

/**
 * 通用数据存储服务的实现类
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月2日 下午2:59:15
 */
public abstract class GenericServiceImpl<E, ID extends Serializable> implements GenericService<E, ID> {

	/**
	 * 等待注入的数据查询对象
	 * @return
	 */
	protected abstract GenericDao<E, ID> getDao();

	@Override
	public E findById(ID id) {
		return getDao().findById(id);
	}

	@Override
	public Iterable<E> findAll(Sort sort) {
		return getDao().findAll(sort);
	}

	@Override
	public Page<E> findAll(Pageable pageable) {
		return getDao().findAll(pageable);
	}

	@Override
	public <S extends E> S save(S entity) {
		return getDao().save(entity);
	}

	@Override
	public <S extends E> Iterable<S> save(Iterable<S> entities) {
		return getDao().save(entities);
	}

	@Override
	public E findOne(ID id) {
		return getDao().findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return getDao().exists(id);
	}

	@Override
	public Iterable<E> findAll() {
		return getDao().findAll();
	}

	@Override
	public Iterable<E> findAll(Iterable<ID> ids) {
		return this.getDao().findAll(ids);
	}

	@Override
	public long count() {
		return this.getDao().count();
	}

	@Override
	public void delete(ID id) {
		this.getDao().delete(id);
	}

	@Override
	public void delete(E entity) {
		this.getDao().delete(entity);
	}

	@Override
	public void delete(Iterable<? extends E> entities) {
		this.getDao().delete(entities);
	}


	@Override
	public void deleteAll() {
		this.getDao().deleteAll();	
	}
	
}
