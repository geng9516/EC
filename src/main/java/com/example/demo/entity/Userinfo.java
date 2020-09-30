package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_userinfo")
public class Userinfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	private String photo;
	private String user_name;
	private String user_nickname;
	private String tel;
	private String email;
	private char sex;
	private String address;
	private Timestamp birth;
	private Timestamp date_created;
	private Timestamp date_modified;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getBirth() {
		return birth;
	}
	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public Timestamp getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(Timestamp date_modified) {
		this.date_modified = date_modified;
	}
	public Userinfo(Integer user_id, String photo, String user_name, String user_nickname, String tel, String email,
			char sex,String address, Timestamp birth, Timestamp date_created,
			Timestamp date_modified) {
		super();
		this.user_id = user_id;
		this.photo = photo;
		this.user_name = user_name;
		this.user_nickname = user_nickname;
		this.tel = tel;
		this.email = email;
		this.sex = sex;
		this.address = address;
		this.birth = birth;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}
	public Userinfo() {
		super();
	}



}
