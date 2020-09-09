package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2117_Sungwon {

	private static int maxK;
	private static int N;
	private static int M;
	private static char[][] map;
	private static boolean[][] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // (5 ≤ N ≤ 20)
			M = Integer.parseInt(st.nextToken()); //  (1 ≤ M ≤ 10)
			
			map = new char[N][N];
			int houseNum = 0;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0, z = 0; j < N; j++, z += 2) {
					map[i][j] = str.charAt(z);
					if (map[i][j] == '1') {
						houseNum++;
					}
				}
			} // --------------------- 입력부 ---------------------------
			
			/** 최대 서비스 할 수 있는 영역구하기 */
			maxK = 0; // 최대 서비스 할 수 있는 영역
			for (int k = 1; k <= 45; k++) {
				if (k * k + (k - 1) * (k - 1) > houseNum * M) {
					maxK = k - 1;
					break;
				}
			}
			
			/** 서비스하면서 집 수 카운트하기 */
			isVisited = new boolean[N][N];
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int max = service(i, j);
					if (max > ans) {
						ans = max;
					}
					visitedReset();
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		} // end of TC
		System.out.println(sb);
	} // end of main
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
 	private static int service(int i, int j) {
 		Queue<int[]> q = new LinkedList<int[]>();
 		q.add(new int[] {i, j});
 		isVisited[i][j] = true;
 		int hNum = 0, max = 0;
 		if (map[i][j] == '1') {
 			max++;
 			hNum++;
 		}
 		
 		int k = 2;
 		while(k <= maxK) {
 			int size = q.size();
 			while (size > 0) { 
 				int[] cur = q.poll();
 				int r = cur[0];
 				int c = cur[1];
 				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !isVisited[nr][nc]) {
						isVisited[nr][nc] = true;
						if (map[nr][nc] == '1') {
							hNum++;
						}
						q.add(new int[] {nr, nc});
					}
				}
 				size--;
 			}
 			if (k * k + (k - 1) * (k - 1) <= hNum * M) {
 				max = hNum;
 			}
 			k++;
 		} 
 		return max;
 	}
 	
 	private static void visitedReset() {
 		for (int i = 0; i < N; i++) {
 			for (int j = 0; j < N; j++) {
 				isVisited[i][j] = false;
 			}
 		}
 	}		
} // end of class
