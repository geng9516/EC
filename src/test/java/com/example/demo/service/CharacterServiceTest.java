package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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

	@Test
	void testCase1001() {
		List<Characters> characterList = characterService.findAllCharacter();
		assertThat(characterList.size(),is(0));
	}

	@Test
	void testSaveCharacter() {
		fail("まだ実装されていません");
	}

	@Test
	void testEditChatacter() {
		fail("まだ実装されていません");
	}

	@Test
	void testFindbyCharacterId() {
		fail("まだ実装されていません");
	}

}
