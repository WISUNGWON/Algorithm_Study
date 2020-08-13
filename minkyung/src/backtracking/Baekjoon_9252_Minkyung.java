package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_9252_Minkyung {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		int[][] dp = new int[s1.length+1][s2.length+1];
		
		for (int i = 1; i <= s1.length; i++) {
			for (int j = 1; j <= s2.length; j++) {
				if(s1[i-1] == s2[j-1]) dp[i][j] = dp[i-1][j-1]+1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		int x = s1.length;
		int y = s2.length;
		StringBuilder sb = new StringBuilder();
		while(x!=0 && y!=0) {
			if(s1[x-1] == s2[y-1]) {
				sb.append(s1[x-1]);
				x--;
				y--;
			}
			else if(dp[x][y] == dp[x-1][y]) x--;
			else if(dp[x][y] == dp[x][y-1]) y--;
		}
		
		System.out.println(sb.reverse().toString());
	}
	
}
