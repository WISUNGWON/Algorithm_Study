package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_14502_Minkyung {

	static int[][] map, dfs_map;
	static boolean[][] flag;
	static int max = 0, count0 = 0, cnt, N, M;
	static int[] dir_row = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dir_col = {0, 0, -1, 1}; // 상 하 좌 우
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dfs_map = new int[N][M];
		ArrayList<int[]> virus = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) count0++;
				else if(map[i][j] == 2) virus.add(new int[] {i, j});
			}
		}
		
		// 3개의 벽을 세워주기
		for (int row1 = 0; row1 < N; row1++) {
			for (int col1 = 0; col1 < M; col1++) {
				if(map[row1][col1] == 0) {
					map[row1][col1] = 1;
					
					for (int row2 = row1; row2 < N; row2++) {
						for (int col2 = 0; col2 < M; col2++) {
							if(row2 == row1 && col2 <= col1) continue;
							if(map[row2][col2] == 0) {
								map[row2][col2] = 1;
								
								for(int row3 = row2; row3 < N; row3++) {
									for (int col3 = 0; col3 < M; col3++) {
										if(row3 == row2 && col3 <= col2) continue;
										if(map[row3][col3] == 0) {
											map[row3][col3] = 1;
											
											for (int i = 0; i < N; i++) {
												for (int j = 0; j < M; j++) {
													dfs_map[i][j] = map[i][j];
												}
											}
											cnt = count0-3;
											flag = new boolean[N][M];
											for (int[] v : virus) {
												dfs(v[0], v[1]);
											}
											
											if(cnt > max) max = cnt;
											map[row3][col3] = 0;
										}
									}
								}
								
								map[row2][col2] = 0;
							}
						}
					}
					
					map[row1][col1] = 0;
				}
			}
		}
		
		System.out.println(max);
	}
	
	// 바이러스 퍼뜨리기 - dfs_map, cnt
	public static void dfs(int row, int col) {
		if(cnt == 0) return;
		for (int i = 0; i < 4; i++) {
			int nrow = row + dir_row[i];
			int ncol = col + dir_col[i];
			if(0<=nrow && nrow<N && 0<=ncol && ncol<M && !flag[nrow][ncol] && dfs_map[nrow][ncol] == 0) {
				dfs_map[nrow][ncol] = 2;
				cnt--;
				flag[nrow][ncol] = true;
				dfs(nrow, ncol);
			}
		}
	}

}
