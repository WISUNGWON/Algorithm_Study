import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Baekjoon_16236_jiun {
	public static void main(String[] args) throws NumberFormatException, IOException {
		final int INF = 99999;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		Shark dist = null;
		int sr = 0, sc = 0;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0, index = 0; j < n; j++, index += 2) {
				map[i][j] = s.charAt(index) - '0'; // 문자형 배열.
				if (map[i][j] == 9) { // 아기상어의 위치
					sr = i;
					sc = j;
					map[i][j] = 0;
				}
			}
		}

		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, -1, 0, 1 }; // 상좌하우 우선순위
		int size = 2;
		int cnt = 2;
		int time = 0;

		dist = new Shark(sr, sc, 0);
		do { // 엄마상어에게 도움을 요청할 때까지 지속
			boolean[][] visit = new boolean[n][n];
			Queue<Shark> q = new LinkedList<>();
			visit[dist.r][dist.c] = true;
			q.offer(new Shark(dist.r, dist.c, 0));
			dist.d = INF;

			while (!q.isEmpty()) {
				Shark s = q.poll();
				if (s.d > dist.d)
					break;// 이미 찾은 최단거리보다 길어지면 탐색 종료
				if (map[s.r][s.c] > size)
					continue; // 이동못함

				if (map[s.r][s.c] != 0 && map[s.r][s.c] < size) { // 물고기가 있으면
					if (s.d < dist.d)
						dist = s;
					else if (s.d == dist.d) {// 거리가 같은 물고기들
						if (s.r < dist.r)// 가장 위쪽 물고기
							dist = s;
						else if (s.r == dist.r && s.c < dist.c)// 더 왼쪽에 있는 물고기
							dist = s;
					}
					continue;// 가장 가까운 물고기를 찾음
				}

				for (int i = 0; i < 4; i++) {// 아기상어 상하좌우 이동,
					int nr = s.r + dr[i];
					int nc = s.c + dc[i];
					if (nr < 0 || nr >= n || nc < 0 || nc >= n)
						continue;
					if (!visit[nr][nc]) {
						visit[nr][nc] = true;
						q.offer(new Shark(nr, nc, s.d + 1));
					}
				} // end of for
			} // end of q

				if (dist.d != INF) { // 먹은 물고기가 있는경우
					time += dist.d;
					cnt--;
					if (cnt == 0) {
						size++;
						cnt = size;
					}
					map[dist.r][dist.c]=0;
				}
				
		} while (dist.d != INF);// end of eat
		System.out.println(time);
	} // end of main

	static class Shark {
		int r, c, d;

		public Shark(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
} // end of class
