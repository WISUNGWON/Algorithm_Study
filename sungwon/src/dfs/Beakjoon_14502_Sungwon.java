package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beakjoon_14502_Sungwon {
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int N, M, ans;
	static int[][] map, copy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N * M; i++) {
			int nx = i / M;
			int ny = i % M;
			
			if (map[nx][ny] == 0) { // 벽일 경우
				map[nx][ny] = 1;
				wallMaking(i, 1);
				map[nx][ny] = 0; 
			}
		}
		System.out.println(ans);

	} // end of main

	private static void wallMaking(int space, int wallN) {
		if (wallN == 3) { 
			copy = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[i][j] = map[i][j];
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copy[i][j] == 2) {
						virusSpread(i, j);
					}
				}
			}
			safetyZoneCounting();
		} else { 
			for (int i = space + 1; i < N * M; i++) {
				int nx = i / M; 
				int ny = i % M;
				if (map[nx][ny] == 0) {
					map[nx][ny] = 1;
					wallMaking(i, wallN + 1);
					map[nx][ny] = 0;
				}
			}
		}
		
	}

	private static void safetyZoneCounting() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0) {
					++cnt;
				}
			}
		}
		if (cnt > ans) {
			ans = cnt;
		}
	}

	private static void virusSpread(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && copy[nx][ny] == 0) {
				copy[nx][ny] = 2;
				virusSpread(nx, ny);
			}
		}
		
		
	}

} // end of class
