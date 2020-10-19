package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "t_control")
public class Control {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String controlName;
	private String statusByControl;
	private String characterName;
	private Character sex;
	private String tel;
	private Integer loginTimes=0;
	private Timestamp lastLogin;
	private Timestamp dateCreated;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "controllogin_id",unique = true)
	private ControlLogin controlLogin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name ="control_name")
	public String getControlName() {
		return controlName;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	@Column(name="statusByControl")
	public String getStatusByControl() {
		return statusByControl;
	}

	public void setStatusByControl(String statusByControl) {
		this.statusByControl = statusByControl;
	}

	@Column(name="character_name")
	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name="longin_times")
	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	@Column(name="last_login")
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name="date_created")
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public ControlLogin getControlLogin() {
		return controlLogin;
	}

	public void setControlLogin(ControlLogin controlLogin) {
		this.controlLogin = controlLogin;
	}

	public Control(Integer id, String controlName, String statusByControl, String characterName, Character sex,
			String tel, Integer loginTimes, Timestamp lastLogin, Timestamp dateCreated, ControlLogin controlLogin) {
		super();
		this.id = id;
		this.controlName = controlName;
		this.statusByControl = statusByControl;
		this.characterName = characterName;
		this.sex = sex;
		this.tel = tel;
		this.loginTimes = loginTimes;
		this.lastLogin = lastLogin;
		this.dateCreated = dateCreated;
		this.controlLogin = controlLogin;
	}

	public Control() {
		super();
	}

	@Override
	public String toString() {
		return "Control [id=" + id + ", controlName=" + controlName + ", statusByControl=" + statusByControl
				+ ", characterName=" + characterName + ", sex=" + sex + ", tel=" + tel + ", loginTimes=" + loginTimes
				+ ", lastLogin=" + lastLogin + ", dateCreated=" + dateCreated + ", controlLogin=" + controlLogin + "]";
	}



}
