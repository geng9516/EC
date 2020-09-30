package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_character")
public class Characters {
	@Id
	private String character_name;
	private String statusbycharacter;
	private Timestamp date_created;
	private Timestamp date_modified;
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
	public Characters(String character_name, String statusbycharacter, Timestamp date_created,
			Timestamp date_modified) {
		super();
		this.character_name = character_name;
		this.statusbycharacter = statusbycharacter;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}
	public Characters() {
		super();
	}



}
