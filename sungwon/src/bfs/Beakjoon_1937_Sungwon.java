package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ������� �Ǵ�
/** 
 *  �� Ǯ����
 *  ù �õ� �޸� �ʰ�, �� ��° Ʋ�Ƚ��ϴ�.
 *  2�� for���� ������� ���ƾ� �ϳ�?
 *  
 *  idea 
 *  2�� for������ ���� bfs����
 *  �ð��� ���̱� ���� int���� isVisited �迭�� ����
 *  isVisited �迭�� �湮�ߴ� ���� �Ǵٰ� �� �� �ִ� �ִ� ���� ����
 *  
 * */

public class Beakjoon_1937_Sungwon {

	private static int[][] map;
	private static int N;
	private static int max;
	private static int[][] isVisited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isVisited = new int[N][N];
		max = -1;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // ------------- �Էº� ---------------
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isVisited[i][j] == 0) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(max);
	} // end of main
	// ���� ������ �ٲٸ鼭 �ѹ� ����� ������ �ٲ��ֱ� / ����Ƽ�� �Ἥ �ٲ��ֱ�
	static int[] dr = {-1, 1, 0, 0}; // �����¿�
	static int[] dc = {0, 0, -1, 1};
	private static void bfs(int row, int col) {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {row, col, map[row][col]});
		isVisited[row][col] = cnt;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < dc.length; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > cur[2]) {
					if (isVisited[nr][nc] == 0) {
						q.add(new int[] {nr, nc, map[nr][nc]});
						isVisited[nr][nc] = isVisited[cur[0]][cur[1]] + 1;
					} else if (isVisited[nr][nc] != 0) {
						isVisited[nr][nc] += isVisited[cur[0]][cur[1]];
					}
					if (max < isVisited[nr][nc]) {
						max = isVisited[nr][nc];
					}
				}
			}
		}
	}
} // end of class
