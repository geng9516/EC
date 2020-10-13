package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_controllogin")
public class ControlLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String Login_id;
	private String pass;
	@OneToOne(mappedBy="controlLogin",cascade = CascadeType.REMOVE)
	public Control control;
	public ControlLogin() {
		super();
	}

	public ControlLogin(Integer id, String login_id, String pass ) {
		super();
		this.id = id;
		Login_id = login_id;
		this.pass = pass;
	}


//		private Control control;



}
