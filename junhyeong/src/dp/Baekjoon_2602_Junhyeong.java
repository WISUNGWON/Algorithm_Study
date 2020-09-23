package dp;

import java.util.Scanner;

public class Baekjoon_2602_Junhyeong {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] parch = sc.nextLine().toCharArray();
		int M = parch.length;
		char[][] bridge = new char[2][];
		bridge[0] = sc.nextLine().toCharArray();
		bridge[1] = sc.nextLine().toCharArray();
		int N = bridge[0].length;
		int[][][] dp = new int[N][M][2];
		if(bridge[0][0] == parch[0]) {
			dp[0][0][0] = 1;
		}
		if(bridge[1][0] == parch[0]) {
			dp[0][0][1] = 1;
		}
		for (int i = 1; i < N; i++) {
			dp[i][0][0] += dp[i-1][0][0];
			if(bridge[0][i] == parch[0]) {
				dp[i][0][0] ++;
			}
			dp[i][0][1] += dp[i-1][0][1];
			if(bridge[1][i] == parch[0]) {
				dp[i][0][1] ++;
			}
		}
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < N; j++) {
				dp[j][i][1] += dp[j-1][i][1];
				if(bridge[1][j] == parch[i]) {
					dp[j][i][1] += dp[j-1][i-1][0];
				}
				dp[j][i][0] += dp[j-1][i][0];
				if(bridge[0][j] == parch[i]) {
					dp[j][i][0] += dp[j-1][i-1][1];
				}
			}
		}
		System.out.println(dp[N-1][M-1][0] + dp[N-1][M-1][1]);
	}
	
}
