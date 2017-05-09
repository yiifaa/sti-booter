/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

/**
 * 开发日期：2017年5月9日 ： 上午8:19:17
 * 
 * 分页查询 域对象
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0 
 */
public abstract class PageQuery<T> {
	
	/**
	 * 
	 */
	private static final String ID = "id";

	/**
	 * 每页的数量
	 */
	private int pageSize = 0;
	
	/**
	 * 当前页数
	 */
	private int currPage = 1;
	
	/**
	 * 排序方向
	 */
	private String order = "DESC";
	
	/**
	 * 排序字段
	 */
	private String sortColumn = ID;
	
	/**
	 * 查询对象
	 */
	private T searchForm;
	
	
	/**
     * 初始化默认的查询表单
     */
    protected abstract T initSearchForm();
    
    /**
     * 
     */
    public PageQuery() {
        this.searchForm = initSearchForm();
    }
	
    /**
     * @return the currPage
     */
    public int getCurrPage() {
        return currPage;
    }
    
    /**
     * 计算需要获取的第一条记录
     * 
     * @return 需要获取的第一条记录
     */
    public int getFirstResult() {
        return (currPage - 1) * pageSize;
    }
    
    /**
     * 计算需要获取的最后一条记录
     * 
     * @return 需要获取的最后一条记录
     */
    public int getMaxResults() {
        return pageSize;
    }
    
    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }
    
    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
    
    /**
     * @return the searchForm
     */
    public T getSearchForm() {
        return searchForm;
    }
    
    /**
     * @return the sortColumn
     */
    public String getSortColumn() {
    	if(StringUtils.isBlank(sortColumn)) {
			return ID;
		}
        return sortColumn;
    }
    
    /**
     * @param currPage
     *            the currPage to set
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    
    /**
     * @param order
     *            the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }
    
    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * @param searchForm
     *            the searchForm to set
     */
    public void setSearchForm(T searchForm) {
        this.searchForm = searchForm;
    }
    
    /**
     * @param sortColumn
     *            the sortColumn to set
     */
    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }
    
    /**
     * 转换为排序方向
     * @return
     */
    public Direction getDirection() {
		if("DESC".equals(StringUtils.capitalize(this.order))) {
			return Direction.DESC;
		}
		return Direction.ASC;
	}
    
    /**
     * 转换为Pager对象
     * @return
     */
    public Pageable toPager() {
    	return new PageRequest(currPage - 1, pageSize, this.getDirection(), this.getSortColumn());
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return 	new ToStringBuilder(this)
				  .append("currPage", currPage)
				  .append("pageSize", pageSize)
				  .append("sortColumn", sortColumn)
				  .append("order", order)
				  .append("searchForm", searchForm)
				  .toString();
	}

}
