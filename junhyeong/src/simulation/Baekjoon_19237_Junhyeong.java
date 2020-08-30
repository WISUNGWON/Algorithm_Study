package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_19237_Junhyeong {

	final static int[] dr = {-1, 1, 0, 0};
	final static int[] dc = {0, 0, -1, 1};
	
	static int N, M, K;
	
	static int[] sd;
	static int[][][] priorityD;
	static int[][] map;
	static int[][][] odor;
	
//	static final char[] dir = {'↑', '↓', '←', '→'};
	
	static void printMap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(odor[i][j][0]).append(' ');
//				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static int solve() {
		int time = 0;
		boolean[] moved = new boolean[M+1];
		int sharkLeft = M;
		while(time < 1000 && sharkLeft > 1) {
//			System.out.println(time + 1);
//			printMap();
			for (int i = 1; i <= M; i++) {
				moved[i] = false;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > 0 && !moved[map[i][j]]) { // there is not moved shark
						int shark = map[i][j];
						moved[shark] = true;
						int direction = 5;
						int nr, nc;
						for (int d = 0; d < 4; d++) {
							int tmp = priorityD[shark][sd[shark]][d];
							nr = i + dr[tmp];
							nc = j + dc[tmp];
							if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
								continue;
							}
							if(odor[nr][nc][0] == 0) {
								direction = tmp;
								break;
							}
							if(odor[nr][nc][0] == shark) {
								if(direction == 5) {
									direction = tmp;
								}
							}
						}
						nr = i + dr[direction];
						nc = j + dc[direction];
						sd[shark] = direction;
						if(map[nr][nc] > 0) {
							if(map[nr][nc] > shark) {
								map[nr][nc] = shark;
							}
							sharkLeft --;
						}
						else {
							map[nr][nc] = shark;
						}
						map[i][j] = 0;
					} // one shark move end
				} // column for end
			} // row for end ( shark move )
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > 0) {
						odor[i][j][0] = map[i][j];
						odor[i][j][1] = K;
					}
					else if(odor[i][j][0] > 0) {
						if(--odor[i][j][1] == 0) {
							odor[i][j][0] = 0;
						}
					}
				}
			}
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			time ++;
		} // while 1000 sec end
		if(sharkLeft > 1) {
			return -1;
		}
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		sd = new int[M+1];
		priorityD = new int[M+1][4][4];
		odor = new int[N][N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					odor[i][j][0] = map[i][j];
					odor[i][j][1] = K;
				}
			}
		}
		String line = br.readLine();
		for (int i = 1, idx = 0; i <= M; i++, idx+=2) {
			sd[i] = line.charAt(idx) - '1';
		}
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				line = br.readLine();
				for (int k = 0, idx = 0; k < 4; k++, idx += 2) {
					priorityD[i][j][k] = line.charAt(idx) - '1';
				}
			}
		}
		
		System.out.println(solve());
		
	}
	
}
