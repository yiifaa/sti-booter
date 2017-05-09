/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 权限与菜单管理
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月4日 下午4:41:25
 */
@Entity
@Table(name="T_SECURITY_MENU")
public class Menu {

	@Id@Column(name="PK", length=40)
	@GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="system-uuid")
	private String id;
	
	//菜单\按钮简称,将显示在菜单或按钮上,最多只能5个字
	@Column(name="SHORT_NAME", length=20)
	private String shortName;
	
	//标题,在滑过菜单或按钮时,将会显示此信息
	@Column(name="MENU_TITLE",length=100)
	private String title;
	
	//	访问地址
	@Column(name="MENU_URL",length=128)
	private String url;
	
	//	匹配地址，用于控制权限资源
	@Column(name="MENU_PATTERN",length=128)
	private String pattern;
	
	//	相关的后端访问地址
	@Column(name="BACK_URL",length=256)
	private String backUrls;
	
	//	JS引用地址
	@Column(name="MENU_REQUIRE",length=128)
	private String require;
	
	//父节点主键
	@Column(name="PARENT_ID", length=40)
	private String parentId;
	
	//优先级 0:高,1:中,2:低
	@Column(name="MENU_ORDER")
	private int order = 0;
	
	//	是否作为菜单显示
	@Column(name="MENU_SHOWN")
	private boolean shown;
	
	//图标样式
	@Column(name="ICON_CSS", length=40)
	private String iconCss;
	
	@Column(name="MENU_REMARKS", length=1000)
	private String remarks;
	
	/**
	 * 
	 * @return
	 */
	public String getText() {
		return this.title;
	}
	
	public String getParent() {
		return this.parentId;
	}

	/* (non-Javadoc)
	 * @see com.stixu.commons.persistence.Identifiable#getId()
	 */
	public String getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.stixu.commons.persistence.Identifiable#setId(java.io.Serializable)
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getIconCss() {
		return iconCss;
	}

	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getBackUrls() {
		return backUrls;
	}

	public void setBackUrls(String backUrls) {
		this.backUrls = backUrls;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public boolean isShown() {
		return shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
	}

	/* (non-Javadoc)
	 * @see com.stixu.commons.persistence.Domain#equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see com.stixu.commons.persistence.Domain#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.stixu.commons.persistence.Domain#toString()
	 */
	@Override
	public String toString() {
		return "MenuResource [id=" + id + ", shortName=" + shortName + ", title=" + title + ", url=" + url
				+ ", parentId=" + parentId + ", order=" + order + ", iconCss=" + iconCss + ", remarks=" + remarks + "]";
	}

}
