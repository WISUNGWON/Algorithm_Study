import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953_jiun {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine()); // tc갯수
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 }; // 상:0,하:1,좌:2,우:3,
		int[][] ternal = { { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 }, { 0, 2 } };// 0부터 6까지. 터널모양
																										// // 저장
		int n = 0, m = 0;// n:세로, m:가로 (5이상 50이하)
		int r = 0, c = 0;// 맨홀의 위치 (행,열)
		int l = 0; // 1이상 20이하

		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());// 터널 크기
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());// 맨홀 위치
			l = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간.
			int[][] map = new int[n][m];
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0, idx = 0; j < m; j++, idx += 2) {
					map[i][j] = s.charAt(idx) - '0';
				}
			} // input end
			int numOfPossible = 1, time = 1;
			if (l == time) //l이 1인 경우
				return;
			
			boolean[][] visit = new boolean[n][m];
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] { r, c });
			visit[r][c] = true;
			
			while (q.size() > 0) {
				int size = q.size();
				for (int k = 0; k < size; k++) {
					int cr = q.peek()[0];
					int cc = q.poll()[1];
					int kind = map[cr][cc] - 1; // 터널종류
					for (int i = 0; i < ternal[kind].length; i++) {
						int dir = ternal[kind][i];
						int nr = cr + dr[dir];
						int nc = cc + dc[dir];
						if (nr < 0 || nr >= n || nc < 0 || nc >= m || visit[nr][nc]|| map[nr][nc]<=0)
							continue;

						//if (!visit[nr][nc] && map[nr][nc] > 0) {
							if (dir == 0 && (map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 7))
								continue;
							if (dir == 1 && (map[nr][nc] == 3 || map[nr][nc] == 5 || map[nr][nc] == 6))
								continue;
							if (dir == 2 && (map[nr][nc] == 2 || map[nr][nc] == 6 || map[nr][nc] == 7))
								continue;
							if (dir == 3 && (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 5))
								continue;

							visit[nr][nc] = true;
							numOfPossible++;
							q.offer(new int[] { nr, nc });
						//}
//								}
					}

				}
				time++;
				if (time >= l)
					break;
			}

			//System.out.println("#"+tc+" "+numOfPossible);
			sb.append("#").append(tc).append(" ").append(numOfPossible).append("\n");
		}// end of tc

		//System.out.println(sb);
	}// end of main
}// end of class
