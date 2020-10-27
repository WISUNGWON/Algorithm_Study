package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Beakjoon_4485_Sungwon_Dijk {

	private static int N; 
	private static int[][] map;
	private static int[][] dijk; 
	private static int[] dr = { 0, 1, -1, 0 }; 
	private static int[] dc = { 1, 0, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int test_case = 0; 
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break; 
			}
			map = new int[N][N];
			dijk = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dijk[i][j] = Integer.MAX_VALUE;
				}
			} // --------------- 입력부 ------------------
			test_case++; 
			sb.append("Problem ").append(test_case).append(": ").append(dijkstra()).append("\n"); // 출력문
		}
		System.out.println(sb); // 출력
	}// end of main
	
	public static int dijkstra() {
		PriorityQueue<Link> pq = new PriorityQueue<Link>();
		dijk[0][0] = map[0][0]; 
		pq.offer(new Link(0, 0, map[0][0])); 

		while (!pq.isEmpty()) {
			Link link = pq.poll();

			for (int k = 0; k < 4; k++) {
				int nr = link.row + dr[k];
				int nc = link.col + dc[k];

				if (isValid(nr, nc)) {
					if (dijk[nr][nc] > dijk[link.row][link.col] + map[nr][nc]) { 
						dijk[nr][nc] = dijk[link.row][link.col] + map[nr][nc]; 
						pq.offer(new Link(nr, nc, dijk[nr][nc]));
					}
				}
			}
		}
		return dijk[N - 1][N - 1];
	}
	
	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}
	
	static class Link implements Comparable<Link> {

		int row, col, sum;
		public Link(int row, int col, int sum) {
			this.row = row;
			this.col = col;
			this.sum = sum;
		}

		public int compareTo(Link o) {
			return this.sum - o.sum; 
		}

	}
}