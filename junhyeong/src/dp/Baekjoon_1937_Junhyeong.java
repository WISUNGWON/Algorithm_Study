package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1937_Junhyeong {

	static int N;
	static int[][] forest;
	static int[][] pathLen;
	
	static int getPath(int r, int c) {
		if(pathLen[r][c] > 0) {
			return pathLen[r][c];
		}
		int maxPath = 1;
		if(r + 1 < N && forest[r+1][c] > forest[r][c]) {
			maxPath = Math.max(maxPath, getPath(r+1, c) + 1);
		}
		if(r - 1 >= 0 && forest[r-1][c] > forest[r][c]) {
			maxPath = Math.max(maxPath, getPath(r-1, c) + 1);
		}
		if(c + 1 < N && forest[r][c+1] > forest[r][c]) {
			maxPath = Math.max(maxPath, getPath(r, c+1) + 1);
		}
		if(c - 1 >= 0 && forest[r][c-1] > forest[r][c]) {
			maxPath = Math.max(maxPath, getPath(r, c-1) + 1);
		}
		return pathLen[r][c] = maxPath;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		forest = new int[N][N];
		pathLen = new int[N][N];
		StringTokenizer st;
		int i, j;
		for (i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < N; j++) {
				forest[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				answer = Math.max(answer, getPath(i, j));
			}
		}
		System.out.println(answer);
	}
	
}
