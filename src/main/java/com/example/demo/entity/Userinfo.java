package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
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
	private Set<UserLogin> userLogin;

}
