package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_control")
public class Control {
	@Id
	private String control_name;
	private String statusbycontrol;
	private String charachter_name;
	private char sex;
	private Integer tel;
	private Integer login_times;
	private Timestamp last_login;
	private Timestamp date_created;
	public String getControl_name() {
		return control_name;
	}
	public void setControl_name(String control_name) {
		this.control_name = control_name;
	}
	public String getStatusbycontrol() {
		return statusbycontrol;
	}
	public void setStatusbycontrol(String statusbycontrol) {
		this.statusbycontrol = statusbycontrol;
	}
	public String getCharachter_name() {
		return charachter_name;
	}
	public void setCharachter_name(String charachter_name) {
		this.charachter_name = charachter_name;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public Integer getTel() {
		return tel;
	}
	public void setTel(Integer tel) {
		this.tel = tel;
	}
	public Integer getLogin_times() {
		return login_times;
	}
	public void setLogin_times(Integer login_times) {
		this.login_times = login_times;
	}
	public Timestamp getLast_login() {
		return last_login;
	}
	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public Control(String control_name, String statusbycontrol, String charachter_name, char sex, Integer tel,
			Integer login_times, Timestamp last_login, Timestamp date_created) {
		super();
		this.control_name = control_name;
		this.statusbycontrol = statusbycontrol;
		this.charachter_name = charachter_name;
		this.sex = sex;
		this.tel = tel;
		this.login_times = login_times;
		this.last_login = last_login;
		this.date_created = date_created;
	}
	public Control() {
		super();
	}


}
