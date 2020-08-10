package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baeckjoon_9663_Minkyung {

	static int[][] map;
	static int[] dc = {1, 0, -1}; // 우하 하 좌하
	static int N, cnt = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int col = 0; col < N; col++) {
			moveQueen(0, col);
			removeQueen(0, col);
		}
		
		System.out.println(cnt);
	}

	public static void moveQueen(int row, int col) {
		if(row == N-1) {
			cnt++;
			return;
		}
		
		int nr, nc;
		for (int i = 0; i < 3; i++) {
			for (int n = 1; n < N; n++) {
				nr = row + n;
				nc = col + n * dc[i];
				
				if(nr<N && 0<=nc && nc<N) map[nr][nc]++;
				else break;
			}
		}
		
		for (int c = 0; c < N; c++) {
			if(map[row+1][c] == 0) {
				moveQueen(row+1, c);
				removeQueen(row+1, c);
			}
		}
	}
	
	public static void removeQueen(int row, int col) {
		int nr, nc;
		for (int i = 0; i < 3; i++) {
			for (int n = 1; n < N; n++) {
				nr = row + n;
				nc = col + n * dc[i];
				
				if(nr<N && 0<=nc && nc<N) {
					if (map[nr][nc] > 0) map[nr][nc]--;
					
				} else break;
			}
		}
	}
}
