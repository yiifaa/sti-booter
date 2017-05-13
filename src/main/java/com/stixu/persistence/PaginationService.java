/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.persistence;

import com.stixu.query.PageQuery;
import com.stixu.query.Pagination;
import com.stixu.query.PaginationBuilder;
import com.stixu.query.QueryType;

/**
 * 
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月13日 ： 下午6:45:28 
 */
public interface PaginationService<E, PK> {
	
	  /**
     * 根据分页对象进行分页查询,仅支持HQL\JQL查询
     * @param qlType 查询语言的类型
     * @param pageQuery 分页对象
     * @param builder 分页查询辅助对象
     * @return 业务实体的分页结果
     */
    <S, P extends PageQuery<S>> Pagination<E> findDomainPage(QueryType qlType, PageQuery<S> pageQuery, PaginationBuilder<S, P> builder);

}
