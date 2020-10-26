package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_4485_Junhyeong {

	final static int[] dr = {0, 0, 1, -1};
	final static int[] dc = {1, -1, 0, 0};
	
	static int N;
	static int[][] map = new int[125][125];
	static int[][] total = new int[125][125];
	
	static void initTotal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				total[i][j] = Integer.MAX_VALUE;
			}
		}
	}
	
	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {0, 0});
		total[0][0] = map[0][0];
		while(q.size() > 0) {
			int[] pos = q.poll();
			for (int d = 0; d < dr.length; d++) {
				int nr = pos[0] + dr[d];
				int nc = pos[1] + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				int val = total[pos[0]][pos[1]] + map[nr][nc];
				if(val < total[nr][nc]) {
					total[nr][nc] = val;
					q.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int problemCount = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) {
				break;
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			initTotal();
			bfs();
			sb.append("Problem ").append(problemCount++).append(": ").append(total[N-1][N-1]).append('\n');
		}
		System.out.print(sb.toString());
		
		
	}
	
}
