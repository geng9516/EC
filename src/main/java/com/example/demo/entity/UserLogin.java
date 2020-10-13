package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_userlogin")
public class UserLogin {

	private Integer id;
	private Userinfo userinfo;
	private String loginType;
	private String loginName;
	private String pass;

	public UserLogin(Integer id, Userinfo userinfo, String loginType, String loginName, String pass) {
		super();
		this.id = id;
		this.userinfo = userinfo;
		this.loginType = loginType;
		this.loginName = loginName;
		this.pass = pass;
	}

	public UserLogin() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "login_type")
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "UserLogin [id=" + id + " , loginType=" + loginType + ", loginName="
				+ loginName + ", pass=" + pass + "]";
	}



}
