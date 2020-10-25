package com.example.demo.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Characters;

@Repository
public interface CharacterRepository extends JpaRepository<Characters, Integer> {

	@Modifying
	@Query("update Characters c set c.statusByCharacter=?2,c.dateModified=?3 where c.characterName=?1")
	void setCharacterById(String characterName, String status, Timestamp timestamp);

	@Query("select c from Characters c where c.id=?1")
	Characters findbyCharacterId(Integer characterId);

	@Modifying
	@Query("delete from Characters c where c.id=?1")
	void deleteCharacterByCharacterName(Integer characterName);
}
