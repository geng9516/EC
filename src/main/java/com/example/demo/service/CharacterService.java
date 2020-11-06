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

	//すべてのデータを取得
	public List<Characters> findAllCharacter() {
		return characterRepository.findAll();
	}

	//IDでデータを削除
	/*public String deleteCharacterByCharacterName(Integer characterId) {
		characterRepository.deleteCharacterByCharacterId(characterId);
		return "成功";
	}*/

	//データ保存
	public String saveCharacter(Characters character) {
		characterRepository.save(character);
		return "";
	}

	//データ編集
	public String editChatacter(String characterName, String status, Timestamp timestamp) {
		characterRepository.setCharacterById(characterName, status, timestamp);
		return "";
	}

	//IDで検索
	public Characters findbyCharacterId(Integer characterId) {
		return characterRepository.findbyCharacterId(characterId);
	}

}