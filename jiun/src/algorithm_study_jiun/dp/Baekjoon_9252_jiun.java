package src.algorithm_study_jiun.dp;

import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/9251
// LCS 2
public class Baekjoon_9252_jiun {
	private static String s1;
	private static String s2;
	private static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s1 = sc.next();
		s2 = sc.next();
		map = new int[s1.length() + 1][s2.length() + 1];
		int i, j = 0;
		for (i = 1; i < map.length; i++) {
			for (j = 1; j < map[0].length; j++) {
				if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
					map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);
				} else {
					map[i][j] = map[i - 1][j - 1] + 1;
				}
			}
		}
		System.out.println(map[i - 1][j - 1]);
		System.out.println(getWords(s1.length(), s2.length()));
	}// end of main

	private static String getWords(int l1, int l2) {

		if (l1 == 0 || l2 == 0)
			return "";
		else if (s1.charAt(l1 - 1) == s2.charAt(l2 - 1)) // 왼쪽, 위 값이 같으면 같은 문자가 새로 추가된 것이므로 해당 열의 값 추가.
			return getWords(l1 - 1, l2 - 1) + s1.charAt(l1 - 1); 
		else { // 다르면  i-1,j-1 기준이 같은 곳을 찾아야 함.
			if (map[l1][l2 - 1] > map[l1 - 1][l2]) //  더 큰 값으로 재귀
				return getWords(l1, l2 - 1);
			else
				return getWords(l1 - 1, l2);
		}
	}
}// end of class
