package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_control")
public class Control {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String control_name;
	private String statusbycontrol;
	private String character_name;
	private Character sex;
	private String tel;
	private Integer login_times;
	private Timestamp last_login;
	private Timestamp date_created;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "controllogin_id",unique = true)
	private ControlLogin controlLogin;

	@Override
	public String toString() {
		return "Control [id=" + id + ", control_name=" + control_name + ", statusbycontrol=" + statusbycontrol
				+ ", character_name=" + character_name + ", sex=" + sex + ", tel=" + tel + ", login_times="
				+ login_times + ", last_login=" + last_login + ", date_created=" + date_created + "]";
	}

}
