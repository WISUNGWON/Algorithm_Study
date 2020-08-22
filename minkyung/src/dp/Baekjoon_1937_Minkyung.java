package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1937_Minkyung {

	static int[][] map, dp;
	static int max = 0, N;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(dp[i][j] == -1) max = Math.max(max, dfs(i, j));
			}
		}
		
		System.out.println(max);
	}

	private static int dfs(int row, int col) {
		if(dp[row][col] != -1) {
			return dp[row][col];
		}
		dp[row][col] = 1;
		int nr, nc;
		int[] dps = new int[4];
		for (int i = 0; i < 4; i++) {
			nr = row+dr[i];
			nc = col+dc[i];
			if(0<=nr && nr<N && 0<=nc && nc<N && map[row][col]<map[nr][nc]) {
				dps[i] = dfs(nr, nc);
			}
		}
		return dp[row][col] += Math.max(dps[0], Math.max(dps[1], Math.max(dps[2], dps[3])));
	}
}
