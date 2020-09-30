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
@Table(name="t_control")
public class Control {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String control_name;
	private String statusbycontrol;
	private String charachter_name;
	private char sex;
	private Integer tel;
	private Integer login_times;
	private Timestamp last_login;
	private Timestamp date_created;
	private Character character;
	private Set<ControlLogin> ControlLogin;

}
