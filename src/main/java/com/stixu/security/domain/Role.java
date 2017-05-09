/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.security.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.stixu.security.SecurityConstants;


/**
 * 系统角色
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月4日 下午4:28:16
 */
@Entity
@Table(name="T_SECURITY_ROLE")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 2218352565358766989L;

	@Id@Column(name="PK", length=40)
	@GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="system-uuid")
	private String id;
	
	//角色名称
	@Column(name="ROLE_NAME", length=40)
	private String name;
	
	//角色顺序
	@Column(name="ROLE_ORDER")
	private int order = 0;
	
	//对应的资源，认证成功后，再获取相关的菜单
	@ManyToMany(fetch = FetchType.LAZY, targetEntity=Menu.class)
	@JoinTable(name = "T_SECURITY_RM_CONN", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = @JoinColumn(name = "MENU_ID"))
	@Fetch(FetchMode.SELECT)
	@BatchSize(size=50)
	@JsonIgnore
	private List<Menu> menus;
	
	//角色描述信息
	@Column(name="ROLE_REMARKS", length=1000)
	private String remarks;
	
	/**
	 * 添加资源信息
	 * @param menu
	 */
	public void addMenuResource(Menu menu) {
		if(menus == null) {
			menus = Lists.newArrayList();
		}
		menus.add(menu);
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public int getOrder() {
		return order;
	}



	public void setOrder(int order) {
		this.order = order;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}


	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		//以id作为标示
		return name;
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.stixu.commons.persistence.Domain#toString()
	 */
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
