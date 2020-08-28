package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Beakjoon_17086_Sungwon {
	
	static int R, C;
	static int map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int dist[][] = new int[R][C];
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dist[i][j] = 51;
				if (map[i][j] == 1 ) {
					q.add(new int[] {i, j});
					dist[i][j] = 0;
				}
			}
		}
		int[] dr = {-1, 1, 0, 0, 1, 1, -1, -1};
		int[] dc = {0, 0, 1, -1, 1, -1, 1, -1};
		int max = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			for (int i = 0; i < 8; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr>=0 && nc>=0 && nr< R && nc< C) {
					if (dist[nr][nc] > dist[r][c] + 1) {
						dist[nr][nc] = dist[r][c] + 1;
						q.add(new int[] {nr, nc});
						max = Math.max(dist[nr][nc], max);
					}
				}
		   }
		}
		System.out.println(max);
	} // end of main
} // end of class
