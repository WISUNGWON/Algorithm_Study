package dp;

import java.util.Scanner;

/**
 * 
 * @author Junhyeong
 *
 * LCS 3
 * 
 * 172ms
 * 
 */

public class Baekjoon_1958_Junhyeong {

	static int lcs(String s1, String s2, String s3) {

		int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
		int[][][] dp = new int[n1 + 1][n2 + 1][n3 + 1];

		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				for (int k = 0; k < n3; k++) {
					if(s1.charAt(i) == s2.charAt(j) && s2.charAt(j) == s3.charAt(k)) {
						dp[i+1][j+1][k+1] = dp[i][j][k]+1;
					}
					else {
						dp[i+1][j+1][k+1] = Math.max(Math.max(dp[i][j+1][k+1], dp[i+1][j+1][k]), dp[i+1][j][k+1]);
					}
				}
			}
		}
		return dp[n1][n2][n3];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		String s3 = sc.nextLine();
		sc.close();
		System.out.println(lcs(s1, s2, s3));
	}

}
