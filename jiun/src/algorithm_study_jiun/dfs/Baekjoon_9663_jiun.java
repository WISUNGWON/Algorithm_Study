import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon_9663_jiun {
	private static int n;
	private static boolean[][] visit;
	private static int[][] map;
	private static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		cnt = 0;
		map = new int[n][n];
		visit = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			findQueen(i, 0, 0);
		}
		System.out.println(cnt);
	}

	static int[] dr = { 1, -1, 0, 0, 1, 1, -1, -1 };
	static int[] dc = { 0, 0, 1, -1, -1, 1, -1, 1 };
	static Queue<int[]> q = new LinkedList<>();

	private static void findQueen(int col, int row, int k) {
		if (k >= n) {
			cnt++;
//			visit = new booleㄴan[n][n];
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visit[row][i]) {
				visit[row][i] = true;
				q.add(new int[] { row, i });
				while (!q.isEmpty()) {
					int[] loc = q.poll();
					for (int j = 0; j < 8; j++) {
						int nr = loc[0] + dr[j];
						int nc = loc[1] + dc[j];
						if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visit[nr][nc]) {
							visit[nr][nc] = true;
							q.offer(new int[] { nr, nc });
						}
					}
				}
				// 앞,뒤, 양옆,대각선 방문처리
				
				findQueen(0,row + 1, k + 1); // 다음 자리 찾기
				visit[row][i] = false;
				
				
				
				q.add(new int[] { row, i });
				while (!q.isEmpty()) {
					int[] loc = q.poll();
					for (int j = 0; j < 8; j++) {
						int nr = loc[0] + dr[j];
						int nc = loc[1] + dc[j];
						if (nr >= 0 && nr < n && nc >= 0 && nc < n && visit[nr][nc]) {
							visit[nr][nc] = false;
							q.offer(new int[] { nr, nc });
						}
					}
				}
			}
		}
	}
}
