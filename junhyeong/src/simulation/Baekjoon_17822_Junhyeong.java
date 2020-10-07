package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Baekjoon_17822_Junhyeong {
	
	static class Point{
		int i, j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public int hashCode() {
			return (i + " " + j).hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Point) {
				Point p = (Point)obj;
				if(p.i == this.i && p.j == this.j) {
					return true;
				}
			}
			return false;
		}
		@Override
		public String toString() {
			return "(" + i + ", " + j + ")";
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] board = new int[N+1][M];
		int[] rotation = new int[N+1];
		HashSet<Point> toRemove = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			// 원판 돌리기
			if(d == 0) { // 시계방향
				int num = x;
				while(num <= N) {
					rotation[num] = (rotation[num] + k) % M;
					num += x;
				}
			}
			else {
				int num = x;
				while(num <= N) {
					rotation[num] = (rotation[num] + M - k) % M;
					num += x;
				}
			}
			// 인접하면서 수가 같은것 찾기
			int count = 0;
			int total = 0;
			for (int i = 1; i <= N; i++) {
				int diff = M - rotation[i];
				for (int j = 0; j < M; j++) {
					int realIdx = (j + diff) % M;
					if(board[i][realIdx] == 0) {
						continue;
					}
					count ++;
					total += board[i][realIdx];
					// 인접한게 있는지 확인
					int tmpIdx;
					if(i - 1 >= 1) {
						tmpIdx = (j + M - rotation[i-1]) % M;
						if(board[i][realIdx] == board[i-1][tmpIdx]) {
							toRemove.add(new Point(i, realIdx));
							toRemove.add(new Point(i-1, tmpIdx));
						}
					}
					if(i + 1 <= N) {
						tmpIdx = (j + M - rotation[i+1]) % M;
						if(board[i][realIdx] == board[i+1][tmpIdx]) {
							toRemove.add(new Point(i, realIdx));
							toRemove.add(new Point(i+1, tmpIdx));
						}
					}
					tmpIdx = (realIdx + 1) % M;
					if(board[i][realIdx] == board[i][tmpIdx]) {
						toRemove.add(new Point(i, realIdx));
						toRemove.add(new Point(i, tmpIdx));
					}
					tmpIdx = (realIdx + M - 1) % M;
					if(board[i][realIdx] == board[i][tmpIdx]) {
						toRemove.add(new Point(i, realIdx));
						toRemove.add(new Point(i, tmpIdx));
					}
				}
			}
			if(toRemove.size() > 0) { // 인접한게 있었다면
				for (Point point : toRemove) {
					board[point.i][point.j] = 0;
				}
				toRemove.clear();
			}
			else { // 없었다면 (2-2. 평균을 구하고, 평균보다 크면 1 빼고 작으면 1 더한다)
				double average = 1.0D * total / count;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if(board[i][j] == 0) {
							continue;
						}
						if(board[i][j] > average) {
							board[i][j] --;
						}
						else if(board[i][j] < average) {
							board[i][j] ++;
						}
					}
				}
			}
		} // T번 회전 및 동작 끝
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				answer += board[i][j];
			}
		}
		System.out.println(answer);
	}
	
}
