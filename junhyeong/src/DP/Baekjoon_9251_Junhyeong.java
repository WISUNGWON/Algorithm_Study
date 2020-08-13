package DP;

import java.util.Scanner;

/**
 * 
 * @author Junhyeong
 *
 * LCS 1
 * 
 * 152ms
 * 
 */

public class Baekjoon_9251_Junhyeong {

	static int lcs(String s1, String s2) {

		int n1 = s1.length(), n2 = s2.length();
		int[][] dp = new int[n1 + 1][n2 + 1];
		
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				if(s1.charAt(i) == s2.charAt(j)) {
					dp[i+1][j+1] = dp[i][j] + 1;
				}
				else {
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
				}
			}
		}
		return dp[n1][n2];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		sc.close();
		System.out.println(lcs(s1, s2));
	}

}
