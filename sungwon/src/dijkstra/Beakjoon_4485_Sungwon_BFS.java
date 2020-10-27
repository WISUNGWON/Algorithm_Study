package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Beakjoon_4485_Sungwon_BFS {

	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int test_case = 0;
ex:		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break ex; 
			}
			test_case++;
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // ------------ 입력부 ----------------
			
			int min = Integer.MAX_VALUE;
			Queue<Link2> q = new LinkedList<Link2>();
			q.add(new Link2(0, 0, map[0][0]));
			int[] dr = {1, 0, 0, -1}; // 하우좌상
			int[] dc = {0, 1, -1, 0};
			while(!q.isEmpty()) {
				Link2 link = q.poll();
				int r = link.r;
				int c = link.c;
				int sum = link.sum;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr == N - 1 && nc == N - 1) {
						int nSum = sum + map[nr][nc];
						if (min > nSum) {
							min = nSum;
						}
					} else if (nr >= 0 && nr < N && nc >= 0 && nc < N && sum + map[nr][nc] < min) {
						q.add(new Link2(nr, nc, sum + map[nr][nc]));
					  }
				}
			}
			sb.append("Problem ").append(test_case).append(": ").append(min).append("\n");
		} // end of while
		System.out.println(sb);
	}
	
} // end of class

class Link2 {
	int r;
	int c;
	int sum;
	public Link2(int r, int c, int sum) {
		this.r = r;
		this.c = c;
		this.sum = sum;
	}
}