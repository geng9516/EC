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
@Table(name="t_character")
public class Characters {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String character_name;
	private String statusbycharacter;
	private Timestamp date_created;
	private Timestamp date_modified;
	private Set<Control> control;


}
