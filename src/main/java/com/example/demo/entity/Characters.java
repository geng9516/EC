package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_characters")
public class Characters {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String character_name;
	private String statusbycharacter;
	private Timestamp date_created;
	private Timestamp date_modified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCharacter_name() {
		return character_name;
	}

	public void setCharacter_name(String character_name) {
		this.character_name = character_name;
	}

	public String getStatusbycharacter() {
		return statusbycharacter;
	}

	public void setStatusbycharacter(String statusbycharacter) {
		this.statusbycharacter = statusbycharacter;
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

}
