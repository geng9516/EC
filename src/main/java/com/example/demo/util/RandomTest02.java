package com.example.demo.util;

import java.util.Random;

/*
编写程序，生成5个不重复的随机数[0-100]。重复的话重新生成。
最终生成的5个随机数放到数组中，要求数组中这5个随机数不重复。
 */
public class RandomTest02 {
	public static void main(String[] args) {
		Random rand = new Random();
		String s = "";
		for(int i = 0 ; i < 6 ; i++) {
			int num2 = rand.nextInt(10);
			s+=num2;
		}
		System.out.println(s);
	}
}
