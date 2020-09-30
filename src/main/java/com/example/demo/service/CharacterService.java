package com.example.demo.service;


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

	public String deleteCharacterById(String character_name) {
		characterRepository.deleteById(character_name);
		return "成功";
	}

	public String saveCharacter(Characters character) {
		characterRepository.save(character);
		return "";
	}

	public String editChatacter(Characters character) {
		characterRepository.save(character);
		return "";
	}
}