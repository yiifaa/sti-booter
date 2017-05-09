/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.query;

import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 对Spring的默认分页实现进行包装
 * 
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月14日 下午7:42:51
 */
public class PageWrapper<T> implements Page<T> {

	private PageImpl<T> pager;
	
	private PageWrapper(PageImpl<T> pager) {
		this.pager = pager;
	}
	
	public List<T> getContent() {
		return pager.getContent();
	}
	
	public boolean isLast() {
		return pager.isLast();
	}
	
	public boolean isFirst() {
		return pager.isFirst();
	}
	
	public int getTotalPages() {
		return pager.getTotalPages();
	}
	
	public long getTotalElements() {
		return pager.getTotalElements();
	}
	
	public int getNumber() {
		return pager.getNumber() + 1;
	}
	
	public int getSize() {
		return pager.getSize();
	}
	
	public int getNumberOfElements() {
		return pager.getNumberOfElements();
	}
	
	public Sort getSort() {
		return pager.getSort();
	}
	
	public static <S> PageWrapper<S> of(Page<S> pager) {
		if(pager instanceof PageImpl) {
			PageImpl<S> result = (PageImpl<S>)pager;
			return new PageWrapper<S>(result);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasContent()
	 */
	@Override
	public boolean hasContent() {
		return this.pager.hasNext();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return this.pager.hasNext();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasPrevious()
	 */
	@Override
	public boolean hasPrevious() {
		return this.pager.hasPrevious();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#nextPageable()
	 */
	@Override
	public Pageable nextPageable() {
		return this.pager.nextPageable();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#previousPageable()
	 */
	@Override
	public Pageable previousPageable() {
		return this.pager.previousPageable();
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return this.pager.iterator();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Page#map(org.springframework.core.convert.converter.Converter)
	 */
	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
		return this.pager.map(converter);
	}
	
	public Pagination<T> toPagination() {
		Pagination<T> pages = new Pagination<>();
		//	初始化分页对象
		pages.setCount(this.getNumberOfElements());
		pages.setCurrPage(this.getNumber());
		pages.setPageSize(this.getSize());
		pages.setResults(this.getContent());
		return pages;
	}

}
