/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.persistence;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.stixu.query.PageQuery;
import com.stixu.query.Pagination;
import com.stixu.query.PaginationBuilder;
import com.stixu.query.QueryTemplate;
import com.stixu.query.QueryType;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月13日 ： 下午6:09:49 
 */
public interface PaginationDao<E, PK> {
	
	/**
     * 根据查询模板进行查询,SqlResultMappingQueryTemplate不支持此操作
     * @param qt 查询模板
     * @return 以Object[]表示的List对象
     */
    List<Object[]> find(QueryTemplate qt);
    
    /**
     * 根据查询模板进行查询
     * @param qt 查询模板
     * @return 以Map<String, Object>表示的List对象
     */
    List<Map<String, Object>> find(QueryTemplate qt, String... fileds);
	
    /**
     * 根据查询模板进行统计查询，SqlResultMappingQueryTemplate不支持此操作
     * @param qt 查询模板
     * @return 以Map<String, Object>表示的List对象
     */
    BigInteger findCount(QueryTemplate qt);
    
    /**
     * 根据查询模板进行查询
     * @param clazz
     * @param qt
     * @return 查询结果
     */
    List<E> findDomains(QueryTemplate qt);
	
	 /**
     * 根据分页对象进行分页查询,仅支持HQL\JQL查询
     * @param qlType 查询语言的类型
     * @param queryPage 分页对象
     * @param builder 分页查询辅助对象
     * @return 业务实体的分页结果
     */
    <S, P extends PageQuery<S>> Pagination<E> findDomainPage(QueryType qlType, PageQuery<S> queryPage, PaginationBuilder<S, P> builder);
    
    

	/**
     * 状态合并与更新
     * @param entity 需要更新的实体类
     * @return entity 更新的实体类
     */
    E merge(E entity);
    
    /**
     * 持久化当前对象
     * @param entity 实体
     * @return 
     */
    void persist(E entity);
	
  }
