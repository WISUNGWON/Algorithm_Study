import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1958_Minkyung {

	static String s1, s2, s3;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		s3 = br.readLine();
		dp = new int[s1.length()][s2.length()][s3.length()];
		
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				for (int k = 0; k < s3.length(); k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		
		System.out.println(dfs(0, 0, 0));
	}

	private static int dfs(int i1, int i2, int i3) {
		if(i1 == s1.length() || i2 == s2.length() || i3 == s3.length()) return 0;
		
		if(dp[i1][i2][i3] != -1) return dp[i1][i2][i3];
		dp[i1][i2][i3] = 0;
		
		if(s1.charAt(i1) == s2.charAt(i2) && s2.charAt(i2) == s3.charAt(i3))
			return dp[i1][i2][i3] += dfs(i1+1, i2+1, i3+1)+1;
		else if(s1.charAt(i1) == s2.charAt(i2) && s1.charAt(i1) != s3.charAt(i3)) 
			return dp[i1][i2][i3] += Math.max(dfs(i1+1, i2+1, i3), dfs(i1, i2, i3+1));
		else if(s1.charAt(i1) != s2.charAt(i2) && s2.charAt(i2) == s3.charAt(i3))
			return dp[i1][i2][i3] += Math.max(dfs(i1, i2+1, i3+1), dfs(i1+1, i2, i3));
		else if(s1.charAt(i1) == s3.charAt(i3) && s1.charAt(i1) != s2.charAt(i2)) {
			return dp[i1][i2][i3] += Math.max(dfs(i1+1, i2, i3+1), dfs(i1, i2+1, i3));
		}
		else {
			int temp = Math.max(dfs(i1+1, i2, i3), dfs(i1, i2+1, i3));
			return dp[i1][i2][i3] += Math.max(temp, dfs(i1, i2, i3+1));
		}
	}

}
