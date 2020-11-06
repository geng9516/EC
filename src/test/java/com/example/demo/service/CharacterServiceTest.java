package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Characters;

@Transactional
@SpringBootTest
class CharacterServiceTest {

	@Autowired
	private CharacterService characterService;

	//データ取得
	@Test
	void testFindAllCharacter() {
		List<Characters> list = characterService.findAllCharacter();
		assertNotNull(list);
	}

	//@Transactional
	//@Rollback回滚

	/*@Test
	void testDeleteCharacterByCharacterName() {
		characterService.deleteCharacterByCharacterName(characterId)
	}*/

	//保存
	@Test
	void testSaveCharacter() {
		Characters characters = new Characters();
		characters.setCharacterName("課長");
		characters.setStatusByCharacter("使用中");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		characters.setDateCreated(timestamp);
		characterService.saveCharacter(characters);
		List<Characters> list = characterService.findAllCharacter();
		assertEquals(3, list.size());
	}

	//編集
	@Test
	void testEditChatacter() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		characterService.editChatacter("部長", "使用停止", timestamp);
		Characters c = characterService.findbyCharacterId(2);
		assertEquals("使用停止", c.getStatusByCharacter());
		assertNotNull(c.getDateModified());
	}

	@Test
	void testFindbyCharacterId() {
		Characters character = characterService.findbyCharacterId(1);
		assertEquals("管理者", character.getCharacterName());
	}

}
