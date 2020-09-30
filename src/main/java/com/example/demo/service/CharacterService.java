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
}