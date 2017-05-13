/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.persistence.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stixu.persistence.PaginationDao;
import com.stixu.query.PageQuery;
import com.stixu.query.Pagination;
import com.stixu.query.PaginationBuilder;
import com.stixu.query.QueryConstants;
import com.stixu.query.QueryTemplate;
import com.stixu.query.QueryType;
import com.stixu.query.templates.JqlQueryTemplate;
import com.stixu.query.templates.NamedQueryTemplate;
import com.stixu.query.templates.SqlQueryTemplate;
import com.stixu.query.templates.SqlResultMappingQueryTemplate;

/**
 * 
 * 基于Hibernate的JPA实现
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月13日 ： 下午6:13:46 
 */
public class PaginationDaoImpl<E, PK extends Serializable> implements PaginationDao<E, PK> {

	private final Class<E> persistentClass;
	
	private final Logger log = LoggerFactory.getLogger(PaginationDaoImpl.class);
	    
    @PersistenceContext(name = QueryConstants.ENTITY_MANAGER_NAME,
    					unitName = QueryConstants.PERSIST_UNIT_NAME,
    					type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;
    
    /**
     * @param persistentClass
     */
    public PaginationDaoImpl(final Class<E> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }
    
    /* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#find(secfox.soc.melon.framework.persistence.QueryTemplate)
	 */
	@Override
	public List<Object[]> find(QueryTemplate qt) {
		Preconditions.checkArgument(!(qt instanceof SqlResultMappingQueryTemplate), "SqlResultMappingQueryTemplate can't return object[]");
		Query query = createQuery(qt, Object[].class);
		//获取返回的结果
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		//返回的结果
		List<Object[]> returnVals = Lists.newArrayListWithCapacity(results.size());
		//统一查询结果，都封装为List<Object[]>形式
		for(Object row : results) {
			if(row.getClass().isArray()) {
				returnVals.add((Object[])row);
			} else {
				//将返回的结果转换为数组
				returnVals.add(new Object[]{row});
			}
		}
		return returnVals;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findDomains(secfox.soc.melon.framework.persistence.QueryTemplate)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> findDomains(QueryTemplate qt) {
		Preconditions.checkArgument(!(qt instanceof SqlQueryTemplate), "SqlgQueryTemplate can't find Generic domain");
		Query query = createQuery(qt, persistentClass);
		return query.getResultList();
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#find(secfox.soc.melon.framework.persistence.QueryTemplate, java.lang.String[])
	 */
	@Override
	public List<Map<String, Object>> find(QueryTemplate qt, final String... fileds) {
		List<Object[]> results = find(qt);
		//将数组转换为Map
		return Lists.transform(results, new Function<Object[], Map<String, Object>>() {
			
			/**
			 * 将数组转换为Map
			 */
			@Override
			public Map<String, Object> apply(Object[] arg0) {
				Map<String, Object> item = Maps.newHashMap();
				for(int i = 0; i < arg0.length; i++) {
					item.put(fileds[i], arg0[i]);
				}
				return item;
			}
			
		});
	}
    
    /* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findCount(secfox.soc.melon.framework.persistence.QueryTemplate)
	 */
	@Override
	public BigInteger findCount(QueryTemplate qt) {
		//	Preconditions.checkArgument(!(qt instanceof SqlResultMappingQueryTemplate), "SqlResultMappingQueryTemplate can't use for count");
		List<Object[]> results = find(qt);
		if(results.size() > 0) {
			Object count = results.get(0)[0];
			//JPA查询一般返回此结果
			if(count instanceof Long) {
				return BigInteger.valueOf((Long)count);
			}
			//原生SQL查询一般返回此结果
			if(count instanceof BigInteger) {
				return (BigInteger)count;
			}
		}
		return BigInteger.ZERO;
	}
    
    /**
     * 根据查询模板创建查询对象
     * @param qt
     * @param clazz
     * @return
     */
    protected <R> Query createQuery(QueryTemplate qt, Class<R> clazz) {
    	Query query = null;
    	if(qt instanceof JqlQueryTemplate) {
    		if(clazz == null) {
    			query = entityManager.createQuery(qt.getQuery());
    		} else {
    			query = entityManager.createQuery(qt.getQuery(), clazz);
    		}
    	}
    	if(qt instanceof NamedQueryTemplate) {
    		if(clazz == null) {
    			query = entityManager.createNamedQuery(qt.getQuery());
    		} else {
    			query = entityManager.createNamedQuery(qt.getQuery(), clazz);
    		}
    	}
    	if(qt instanceof SqlQueryTemplate) {
    		query = entityManager.createNativeQuery(qt.getQuery());
    	}
    	if(qt instanceof SqlResultMappingQueryTemplate) {
    		SqlResultMappingQueryTemplate sqt = (SqlResultMappingQueryTemplate)qt;
    		query = entityManager.createNativeQuery(qt.getQuery(), sqt.getSqlResultMapping());
    	}
    	//拼接查询条件
        if(qt.getParameters() != null) {
            for(Entry<String, ?> paramEntry : qt.getParameters().entrySet()) {
                query.setParameter(paramEntry.getKey(), paramEntry.getValue());
            }
        }
        //构建查询缓存
        if(qt.isCachable()) {
            query.setHint("org.hibernate.cacheable", true);
            if(StringUtils.isNotBlank(qt.getCacheRegion())) {
                query.setHint("org.hibernate.cacheRegion", qt.getCacheRegion());
            } else {
                query.setHint("org.hibernate.cacheRegion", clazz.getName());
            }
        }
        //构建分页
        query.setFirstResult(qt.getFirstResult());
        query.setMaxResults(qt.getMaxResults());
     	return query;
    }
    

    /* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findDomainPage(secfox.soc.melon.framework.persistence.QueryType, secfox.soc.melon.framework.PageQuery, secfox.soc.melon.framework.persistence.PaginationBuilder)
	 */
	@Override
	public <S, P extends PageQuery<S>> Pagination<E> findDomainPage(QueryType qlType, PageQuery<S> queryPage, PaginationBuilder<S, P> builder) {
		Pagination<E> pagination = new Pagination<E>();
		//创建分页的总记录数
		pagination.setCount(findCountOfPages(qlType, queryPage, builder));
		QueryTemplate resultQt = buildResultOfPages(qlType, queryPage, builder);
		if(log.isDebugEnabled()) {
			log.debug("HQL： {}", resultQt.getQuery());
		}
		//设置分页返回结果
		pagination.setResults(findDomains(resultQt));
		pagination.setCurrPage(queryPage.getCurrPage());
		//设置页容量
		pagination.setPageSize(queryPage.getPageSize());
		return pagination;
	}

	/**
	 * 构建分页查询的结果查询
	 * @param qlType
	 * @param queryPage
	 * @param builder
	 * @return
	 */
	private <S, P extends PageQuery<S>> QueryTemplate buildResultOfPages(
			QueryType qlType, PageQuery<S> queryPage,
			PaginationBuilder<S, P> builder) {
		//设置结果查询模板
		QueryTemplate resultQt = QueryTemplate.create(qlType);
		//设置分页信息
		resultQt.setFirstResult(queryPage.getFirstResult());
		resultQt.setMaxResults(queryPage.getMaxResults());
		//编译查询语句
		builder.buildSelect(resultQt);
		resultQt.append(QueryConstants.WHERE);
		builder.buildWhere(queryPage.getSearchForm(), resultQt);
		builder.buildBys(queryPage.getSortColumn(), queryPage.getOrder(), resultQt);
		return resultQt;
	}

	/**
	 * 构建分页计数的查询部分
	 * @param qlType
	 * @param queryPage
	 * @param builder
	 * @param pagination
	 */
	private <S, P extends PageQuery<S>> int findCountOfPages(
			QueryType qlType, PageQuery<S> queryPage,
			PaginationBuilder<S, P> builder) {
		//设置计数查询模板
		QueryTemplate countQt = QueryTemplate.create(qlType);
		builder.buildCount(countQt);
		//编译查询语句
		if(countQt.isEmpty()) {
			return 0;
		}
		countQt.append(QueryConstants.WHERE);
		builder.buildWhere(queryPage.getSearchForm(), countQt);
		builder.buildBys(queryPage.getSortColumn(), queryPage.getOrder(), countQt);
		//设置计数结果
		return findCount(countQt).intValue();
	}

	
    
}
