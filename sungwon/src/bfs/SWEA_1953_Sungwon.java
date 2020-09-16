package swea.level.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 핵심 : 이동하려면, 범위안에 있는지만 파악하는 것이 아닌 파이프가 '연결' 되어 있는지 파악해야 한다.*/
/** 118 ms */
public class SWEA_1953_Sungwon {
	
	private static int N, M, R, C, L;
	private static char[][] map;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0, z = 0; j < M; j++, z += 2) {
					map[i][j] = str.charAt(z);
				}
			}
			
			int ans = 0;
			visited = new boolean[N][M];
			Queue<int[]> q = new LinkedList<int[]>();
			q.add(new int[] {R, C});
			visited[R][C] = true;
			ans++;
			while(--L > 0) {
				int size = q.size();
				while (size-- > 0) {
					int[] cur = q.poll();
					int r = cur[0];
					int c = cur[1];
					char pipe = map[r][c];
					switch (pipe) {
					case '1': // 상하좌우 (1, 2, 3, 4)
						// 상 
						if (r - 1 >= 0 && isConnected(r, c, 1) && !visited[r - 1][c] ) {
							ans++;
							q.add(new int[] {r - 1, c});
							visited[r - 1][c] = true;
						}
						// 하
						if (r + 1 < N && isConnected(r, c, 2) && !visited[r + 1][c] ) {
							ans++;
							q.add(new int[] {r + 1, c});
							visited[r + 1][c] = true;
						}
						// 좌
						if (c - 1 >= 0 && isConnected(r, c, 3)&& !visited[r][c - 1] ) {
							ans++;
							q.add(new int[] {r, c - 1});
							visited[r][c - 1] = true;
						}
						// 우
						if (c + 1 < M && isConnected(r, c, 4)&& !visited[r][c + 1] ) {
							ans++;
							q.add(new int[] {r, c + 1});
							visited[r][c + 1] = true;
						}
						break;
					case '2': // 상하
						// 상 
						if (r - 1 >= 0 && isConnected(r, c, 1)&& !visited[r - 1][c] ) {
							ans++;
							q.add(new int[] {r - 1, c});
							visited[r - 1][c] = true;
						}
						// 하
						if (r + 1 < N && isConnected(r, c, 2)&& !visited[r + 1][c] ) {
							ans++;
							q.add(new int[] {r + 1, c});
							visited[r + 1][c] = true;
						}
						break;
					case '3': // 좌우
						// 좌
						if (c - 1 >= 0 && isConnected(r, c, 3)&& !visited[r][c - 1] ) {
							ans++;
							q.add(new int[] {r, c - 1});
							visited[r][c - 1] = true;
						}
						// 우
						if (c + 1 < M && isConnected(r, c, 4)&& !visited[r][c + 1] ) {
							ans++;
							q.add(new int[] {r, c + 1});
							visited[r][c + 1] = true;
						}
						break;
					case '4': // 상우
						// 상 
						if (r - 1 >= 0 && isConnected(r, c, 1)&& !visited[r - 1][c] ) {
							ans++;
							q.add(new int[] {r - 1, c});
							visited[r - 1][c] = true;
						}
						// 우
						if (c + 1 < M && isConnected(r, c, 4)&& !visited[r][c + 1] ) {
							ans++;
							q.add(new int[] {r, c + 1});
							visited[r][c + 1] = true;
						}
						break;
					case '5': // 하우
						// 하
						if (r + 1 < N && isConnected(r, c, 2)&& !visited[r + 1][c] ) {
							ans++;
							q.add(new int[] {r + 1, c});
							visited[r + 1][c] = true;
						}
						// 우
						if (c + 1 < M && isConnected(r, c, 4)&& !visited[r][c + 1] ) {
							ans++;
							q.add(new int[] {r, c + 1});
							visited[r][c + 1] = true;
						}
						break;
					case '6': // 하좌
						// 하
						if (r + 1 < N && isConnected(r, c, 2)&& !visited[r + 1][c] ) {
							ans++;
							q.add(new int[] {r + 1, c});
							visited[r + 1][c] = true;
						}
						// 좌
						if (c - 1 >= 0 && isConnected(r, c, 3)&& !visited[r][c - 1] ) {
							ans++;
							q.add(new int[] {r, c - 1});
							visited[r][c - 1] = true;
						}
						break;
					case '7': // 상좌
						// 상 
						if (r - 1 >= 0 && isConnected(r, c, 1)&& !visited[r - 1][c] ) {
							ans++;
							q.add(new int[] {r - 1, c});
							visited[r - 1][c] = true;
						}
						// 좌
						if (c - 1 >= 0 && isConnected(r, c, 3)&& !visited[r][c - 1] ) {
							ans++;
							q.add(new int[] {r, c - 1});
							visited[r][c - 1] = true;
						}
						break;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		} // end of TC
//		for (int i = 0; i < N; i++) {
//		for (int j = 0; j < M; j++) {
//			System.out.print(map[i][j] + " ");
//		}
//		System.out.println();
//	}
		System.out.println(sb);
	} // end of main
	
	private static char[] upCheckArr= {'1', '2', '5', '6'};
	private static char[] downCheckArr= {'1', '2', '4', '7'};
	private static char[] leftCheckArr= {'1', '3', '4', '5'};
	private static char[] rightCheckArr= {'1', '3', '6', '7'};
	/** 파이프가 연결되어 있는지 확인하는 함수 */
	private static boolean isConnected(int r, int c, int dir) {
		switch (dir) {
		case 1: // 상 (1, 2, 5, 6 파이프와 연결 가능)
			for (int d = 0; d < 4; d++) {
				if (map[r - 1][c] == upCheckArr[d]) {
					return true;
				}
			}
			return false;
		case 2: // 하 (1, 2, 4, 7)
			for (int d = 0; d < 4; d++) {
				if (map[r + 1][c] == downCheckArr[d]) {
					return true;
				}
			}
			return false;			
		case 3: // 좌 (1, 3, 4, 5)
			for (int d = 0; d < 4; d++) {
				if (map[r][c - 1] == leftCheckArr[d]) {
					return true;
				}
			}
			return false;			
		case 4: // 우 (1, 3, 6, 7)
			for (int d = 0; d < 4; d++) {
				if (map[r][c + 1] == rightCheckArr[d]) {
					return true;
				}
			}
			return false;			
		}
		
		return false;
	}
} // end of class
