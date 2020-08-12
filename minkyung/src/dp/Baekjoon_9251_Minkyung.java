package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_9251_Minkyung {

	static String s1, s2;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		dp = new int[s1.length()][s2.length()];
		
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				dp[i][j] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));
	}

	private static int dfs(int i1, int i2) {
		if(i1 == s1.length() || i2 == s2.length()) return 0;
		
		if(dp[i1][i2] != -1) return dp[i1][i2];
		dp[i1][i2] = 0;
		
		if(s1.charAt(i1) == s2.charAt(i2)) return dp[i1][i2] += dfs(i1+1, i2+1)+1;
		else return dp[i1][i2] += Math.max(dfs(i1+1, i2), dfs(i1, i2+1));
	}

}
