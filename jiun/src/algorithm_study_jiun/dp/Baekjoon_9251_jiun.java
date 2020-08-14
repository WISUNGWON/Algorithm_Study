package src.algorithm_study_jiun.dp;

import java.util.Scanner;

// https://www.acmicpc.net/problem/9251
// LCS 1
public class Baekjoon_9251_jiun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		int[][] map = new int[s1.length()+1][s2.length()+1];
		int max = Integer.MIN_VALUE;
		int i,j =0;
		for (i = 1; i < map.length; i++) {
			for (j = 1; j < map[0].length; j++) {
				if(s1.charAt(i-1) != s2.charAt(j-1)) {
					map[i][j]=Math.max(map[i-1][j], map[i][j-1]);
				}else {
					map[i][j] = map[i-1][j-1]+1;
				}
			}
		}
		System.out.println(map[i-1][j-1]);
	}// end of main
}// end of class
