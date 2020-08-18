package dp;

import java.util.Scanner;

/**
 * 
 * @author Junhyeong
 *
 * LCS 2
 * 
 * 160ms
 * 
 */

public class Baekjoon_9252_Junhyeong {

	static void lcs(String s1, String s2) {

		int n1 = s1.length(), n2 = s2.length();
		int[][] dp = new int[n1 + 1][n2 + 1];
		int i, j;
		for (i = 0; i < n1; i++) {
			char c = s1.charAt(i);
			for (j = 0; j < n2; j++) {
				if(c == s2.charAt(j)) {
					dp[i+1][j+1] = dp[i][j] + 1;
				}
				else {
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
				}
			}
		}
		System.out.println(dp[n1][n2]);
		if(dp[n1][n2] == 0) {
			return;
		}
		char[] ch = new char[dp[n1][n2]];
		int idx = dp[n1][n2] - 1;
		i = n1;
		j = n2;
		while(idx >= 0) {
			if(dp[i-1][j] == dp[i][j]){
				i --;
			}
			else if(dp[i][j-1] == dp[i][j]) {
				j --;
			}
			else if(dp[i-1][j-1] == dp[i][j] - 1) {
				i --;
				j --;
				ch[idx--] = s1.charAt(i);
			}
		}
		System.out.println(String.valueOf(ch));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		sc.close();
		lcs(s1, s2);
	}

}
