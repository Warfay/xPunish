package me.muffin.xpunish;

import me.muffin.xpunish.Miscs.TimeUnits;

public class Test {

	public static void main(String[] args) {
		
		int max = 6;
		int min = 1;
		
		int random = (int) (Math.random() * (max - min)) + min;
		
		System.out.println(random);

	}

}
