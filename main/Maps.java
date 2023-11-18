package main;

import java.util.Random;

public class Maps {

	public static int[][] getLevelData() {
		int[][] lvl = {
		{  4,  0,  0,  0,  0,  4,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0 },
		{  0,  0,  0,  0,  0,  0,  0,  8, 17, 17, 17, 17,  9,  0,  0,  0,  0,  0,  0,  0 },
		{  0,  0,  4,  0,  0,  0,  0, 14,  7,  7,  7,  7, 13,  4,  0,  0,  4,  0,  0,  0 },
		{  0,  0,  0,  0,  0,  0,  0, 14,  7, 22, 22,  7, 15,  9,  0,  0,  0,  0,  0,  0 },
		{  0,  4,  0,  0,  0,  0,  0, 14,  7, 22, 22,  7,  7, 13,  4,  0,  0,  0,  4,  0 },
		{  0,  0,  0,  0,  0,  0,  0, 14,  7, 22, 22, 22,  7, 13,  0,  4,  0,  0,  0,  0 },
		{  0,  0,  0,  4,  0,  0,  0, 14,  7,  7,  7, 22,  7, 13,  0,  0,  0,  0,  0,  4 },
		{ 17, 17, 17, 17, 17, 17, 17, 15, 22, 22,  7, 22,  7, 15, 17, 17, 17, 17, 18, 19 },
		{  7,  7,  7,  7,  7,  7,  7,  7,  7, 22,  7, 22,  7,  7,  7,  7,  7,  7, 21, 20 },
		{ 16, 16, 16, 16, 16, 16, 16, 15,  7, 22,  7, 15, 16, 16, 16, 16, 16, 16, 16, 16 },
		{  0,  0,  0,  0,  4,  0,  0, 14,  7,  7,  7, 13,  0,  0,  0,  4,  0,  0,  0,  0 },
		{  0,  4,  0,  0,  4,  0,  0, 10,  16, 16, 16, 11,  0,  4,  0,  0,  0,  0,  4, 0 }
		
		};

		Random random = new Random();
		replaceZeroWithRandomTile(lvl, random);
		replaceZeroWithRandomTile2(lvl, random);
		return lvl;
	}

	private static void replaceZeroWithRandomTile(int[][] lvl, Random random) {
		for (int row = 0; row < lvl.length; row++) {
			for (int col = 0; col < lvl[row].length; col++) {
				if (lvl[row][col] == 0) {
					lvl[row][col] = random.nextInt(4); // generate id between 0 and 3
				}
			}
		}
	}
	private static void replaceZeroWithRandomTile2(int[][] lvl, Random random) {
		for (int row = 0; row < lvl.length; row++) {
			for (int col = 0; col < lvl[row].length; col++) {
				if (lvl[row][col] == 4) {
					lvl[row][col] = random.nextInt(3) + 4; // generate id between 4 and 7
				}
			}
		}
	}
}