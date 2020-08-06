package algorithm_study_jiun.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/14502
public class Baekjoon_14502_jiun {
	private static int m;
	private static int n;
	private static int[][] map;
	private static int max = Integer.MIN_VALUE;
	private static int[][] copyMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copyMap = new int[n][m];
				for (int k = 0; k < n; k++) {
					System.arraycopy(map[k], 0, copyMap[k], 0, m);
				}
				if (copyMap[i][j] == 0) {
					copyMap[i][j] = 1;
					makewalls(copyMap, i, j, 1);// copy맵에 벽 3개 세우기
					copyMap[i][j] = 0;
				}
			}
		}
		System.out.println(max);
	}// end of main

	private static void makewalls(int[][] copyMap, int row, int col, int cnt) {
		if (cnt == 3) { // 종료조건
			spreadVirus();
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copyMap[i][j] == 0) {
					copyMap[i][j] = 1;
					makewalls(copyMap, i, j, cnt + 1);
					copyMap[i][j] = 0;
				}
			}
		}
	}

	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };
	private static int[][] spreadMap;

	private static void spreadVirus() {
		spreadMap = new int[n][m];
//		spreadMap = copyMap;
		for (int k = 0; k < n; k++) {
			System.arraycopy(copyMap[k], 0, spreadMap[k], 0, m);
		}
		Queue<Virus> q = new LinkedList<Virus>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (spreadMap[i][j] == 2)
					q.add(new Virus(i, j));
			}
		}

		while (!q.isEmpty()) {
			Virus v = q.poll();
			int r = v.row;
			int c = v.col;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && spreadMap[nr][nc] == 0) {
					spreadMap[nr][nc] = 2;
					q.add(new Virus(nr, nc));
				}
			}
		}// spread 완료
		int ret = 0;
		for (int i = 0; i < n; i++) { // 안전영역 갯수세기
			for (int j = 0; j < m; j++) {
				if (spreadMap[i][j] == 0)
					ret++;
			}
		}
		if (ret > max)
			max = ret;
		max = Math.max(max, ret);

	}

	static class Virus {
		int row;
		int col;

		public Virus(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}// end of class
