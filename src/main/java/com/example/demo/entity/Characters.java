package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
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
	private String characterName;
	private String statusByCharacter;
	private Timestamp dateCreated;
	private Timestamp dateModified;
	public Characters(Integer id, String characterName, String statusByCharacter, Timestamp dateCreated,
			Timestamp dateModified) {
		super();
		this.id = id;
		this.characterName = characterName;
		this.statusByCharacter = statusByCharacter;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	public Characters() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="character_name",unique = true)
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	@Column(name="statusbycharacter")
	public String getStatusByCharacter() {
		return statusByCharacter;
	}
	public void setStatusByCharacter(String statusByCharacter) {
		this.statusByCharacter = statusByCharacter;
	}

	@Column(name="date_created")
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name="date_modified")
	public Timestamp getDateModified() {
		return dateModified;
	}
	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}
	@Override
	public String toString() {
		return "Characters [id=" + id + ", characterName=" + characterName + ", statusByCharacter=" + statusByCharacter
				+ ", dateCreated=" + dateCreated + ", dateModified=" + dateModified + "]";
	}


}
