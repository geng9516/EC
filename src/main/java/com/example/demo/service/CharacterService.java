package com.example.demo.service;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Characters;
import com.example.demo.repository.CharacterRepository;

@Service
@Transactional
public class CharacterService {

	@Autowired
	CharacterRepository characterRepository;

	public List<Characters> findAllCharacter(){
		return characterRepository.findAll();
	}

	public String deleteCharacterByCharacterName(Integer characterId) {
		characterRepository.deleteCharacterByCharacterName(characterId);
		return "成功";
	}

	public String saveCharacter(Characters character) {
		characterRepository.save(character);
		return "";
	}

	public String editChatacter(String characterName,String status,Timestamp timestamp) {
		characterRepository.setCharacterById(characterName,status,timestamp);
		return "";
	}

	public Characters findCharacterByCharacterName(String characterName) {
		return characterRepository.findCharacterByCharacterName(characterName);
	}
}