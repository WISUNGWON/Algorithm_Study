package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Beakjoon_4485_Jiseon {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		int p = 1;
		
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) break;

			int[][] map = new int[N][N];
			int[][] distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					distance[i][j] = Integer.MAX_VALUE;
				}
			}

			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
			distance[0][0] = map[0][0];
			pq.offer(new int[] { 0, 0, distance[0][0] }); // 행, 열, 출발지에서 현재까지 거리

			while (!pq.isEmpty()) {
				int[] current = pq.poll();
				int r = current[0];
				int c = current[1];
				int dis = current[2];

				for (int j = 0; j < dr.length; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];

					if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						int cost = dis + map[nr][nc];
						if (distance[nr][nc] > cost) {
							distance[nr][nc] = cost;
							pq.add(new int[] { nr, nc, distance[nr][nc] });
						}
					}
				}

			}
			sb.append("Problem ").append(p++).append(": ").append(distance[N - 1][N - 1]).append('\n');
		}
		System.out.print(sb);
	}
}
