package com.example.demo.util;

import java.util.Random;

public class RandomOrderNumber {

	private Random random = new Random();

	public String random() {
		Random rand = new Random();
		String s = "";
		for(int i = 0 ; i < 6 ; i++) {
			int num2 = rand.nextInt(10);
			s+=num2;
		}
		return s;
	}

}
