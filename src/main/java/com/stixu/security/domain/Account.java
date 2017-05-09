/*
 * (c) Copyright 2016 STI eXtrem Using技术小组
 * http://www.stixu.com
 */
package com.stixu.security.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

/**
 * 账户信息
 * @author <a href="mailto:yiifaa@163.com>甘焕</a> 
 * @since 1.0
 * 2016年11月1日 下午4:19:26
 */
@Entity
@Table(name="T_SECURITY_ACCOUNT")
@NamedQueries({
	//	名称必须与方法名相同
	@NamedQuery(name="Account.statByUsername", query = "select acc.username, count(acc.id) from Account acc group by acc.username")
})
public class Account implements UserDetails {
	
	private static final long serialVersionUID = -1093044397312199016L;

	@Id@Column(name="PK", length=40)
	@GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="system-uuid")
	private String id;
	
	@Column(name="USER_NAME", length=40, unique=true)
	private String username;
	
	//密码
	@Column(name="ACCOUNT_PASS", length=128)
	@JsonIgnore
	private String password;
	
	//确认密码
	@Transient
	@JsonIgnore
	private String confirmPassword;
	
	/*创建时间*/
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/* 帐户是否已激活 */
	@Column(name="ACCOUNT_ENABLED")
	private boolean enabled = true;
	
	/* 帐户是否已过期 */
	@Column(name="ACCOUNT_EXPIRED")
	private boolean accountExpired = false;

	/* 帐户是否已锁定 */
	@Column(name="ACCOUNT_LOCKED")
	private boolean accountLocked = false;

	/* 密码是否已过期 */
	@Column(name="CREDENTIALS_EXPIRED")
	private boolean credentialsExpired = false;
	
	/* 帐户所拥有的角色 */
	@ManyToMany(fetch = FetchType.EAGER, targetEntity=Role.class)
	@JoinTable(name = "T_SECURITY_AR_CONN", joinColumns = { @JoinColumn(name = "ACCOUNT_ID") }, inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@OrderBy("order asc")
	@Fetch(FetchMode.SELECT)
	@BatchSize(size=15)
	@JsonIgnore
	private List<Role> roles;
	
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role) {
		if(roles == null) {
			roles = Lists.newArrayList();
		}
		roles.add(role);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return this.password;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return !this.accountExpired;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return !this.accountLocked;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return !this.credentialsExpired;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + "]";
	}

}
