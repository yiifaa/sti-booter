/*
 * (c) Copyright 2017 STI
 * http://stixu.com
 */
package com.stixu.query;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页输出数据
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年5月9日 ： 上午9:36:01 
 */
public class Pagination<T> {
	
	public static final int PAGE_SIZE = 15;
    
    private int count;
    
    private int currPage = 1;
    
    private int pageSize = PAGE_SIZE;
    
    private List<T> results;
    
    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }
    
    /**
     * @return the currPage
     */
    public int getCurrPage() {
    	//	如果删除的记录刚好在分页边界上
    	int pages = this.getPages();
		if(pages < this.currPage) {
    		this.currPage = pages; 
    	}
		if(this.currPage < 1) {
			this.currPage = 1; 
		}
        return this.currPage;
    }
    
    /**
     * 计算共有多少页
     * 
     * @return 页数
     */
    public int getPages() {
        if(this.count % this.pageSize == 0) {
            return this.count / this.pageSize;
        }
        return this.count / this.pageSize + 1;
    }
    
    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
    
    /**
     * @return the results
     */
    public List<T> getResults() {
        return results;
    }
    
    /**
     * @param count
     *            the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
    
    /**
     * @param currPage
     *            the currPage to set
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    
    /**
     * @param pageSize  the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * @param results
     *            the results to set
     */
    public void setResults(List<T> results) {
        this.results = results;
    }
    
    @Override
    public String toString() {
    	return new ToStringBuilder(this)
    			      .append("count", this.count)
    			      .append("currPage", this.getCurrPage())
    			      .append("pageSize", this.pageSize)
    			      .append("pages", this.getPages())
    			      .toString();
    }
}
