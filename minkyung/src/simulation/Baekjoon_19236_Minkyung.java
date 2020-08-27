package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_19236_Minkyung {
	// 1상 2우상 3우 4우하 5하 6좌하 7좌 8좌상
	static int[][][] map = new int[4][4][2];
	static boolean[] visited = new boolean[17];
	static int max = 0;
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] br = {0, 1, 1, 0, -1, -1, -1, 0, 1};
	static int[] bc = {0, 0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				map[i][j] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
		}
		
		visited[map[0][0][0]] = true;
		go(0, 0, map[0][0][0]);
		
		System.out.println(max);
	}

	private static void go(int r, int c, int sum) {
		int nr, nc;
		int[] preDir = new int[17];
		int[] pre = new int[3], cur = new int[] {r, c, map[r][c][1]};
		// 물고기 이동 for 돌때 visited면 continue
		for (int num = 1; num <= 16; num++) { // 찾으려는 물고기 번호
			if(visited[num]) continue;
f:			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(map[i][j][0] == num) {
						// 방향 탐색 후 바꾸기
						int dir = map[i][j][1];
						preDir[num] = dir;
						int cnt = 0;
						while(cnt<8) {
							nr = i+dr[dir]; nc = j+dc[dir];
							if(0<=nr&&nr<4&&0<=nc&&nc<4&&(cur[0]!=nr||cur[1]!=nc)) {
								map[i][j][1] = dir;
								swap(i, j, nr, nc);
								break f;
							}
							dir = ++dir==9?1:dir;
							cnt++;
						}
					}
				}
			}
		}
		// 상어 이동 갈수 있는 곳 모두
		boolean went = false;
		for (int d = 1; d <= 4; d++) {
			nr = cur[0]+d*dr[cur[2]]; nc = cur[1]+d*dc[cur[2]];
			if(0<=nr&&nr<4&&0<=nc&&nc<4) {
				if(!visited[map[nr][nc][0]]) {
					went = true;
					pre[0] = cur[0]; pre[1] = cur[1]; pre[2] = cur[2];
					cur[0] = nr; cur[1] = nc; cur[2] = map[nr][nc][1];
					visited[map[nr][nc][0]] = true;
					go(nr, nc, sum+map[nr][nc][0]);
					visited[map[nr][nc][0]] = false;
					cur[0] = pre[0]; cur[1] = pre[1]; cur[2] = pre[2];
				}				
			} else break;
		}
		if(!went) {
			if(max<sum) max = sum;
		}
		// back (물고기)
		for (int num = 16; num > 0; num--) { // 찾으려는 물고기 번호
			if(visited[num]) continue;
f:			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(map[i][j][0] == num) {
						nr= i+br[map[i][j][1]]; nc = j+bc[map[i][j][1]];
						map[i][j][1] = preDir[num];
						swap(i, j, nr, nc);
						break f;
					}
				}
			}
		}
	}

	private static void swap(int i, int j, int i2, int j2) {
		int[] temp = map[i][j];
		map[i][j] = map[i2][j2];
		map[i2][j2] = temp;
	}

}
