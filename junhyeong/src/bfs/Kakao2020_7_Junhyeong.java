package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class Kakao2020_7_Junhyeong {
	
	static boolean visited[][][] = new boolean[100][100][2];
	
	final static int[] dr = {0, 0, 1, -1};
	final static int[] dc = {1, -1, 0, 0};
	
	static int N;
	
	static boolean isGoal(int r, int c, int s) {
		if(r == N-1 && c == N-2 && s == 0) {
			return true;
		}
		if(r == N-2 && c == N-1 && s == 1) {
			return true; 
		}
		return false;
	}
	
	public static int solution(int[][] board) {
		int answer = 0;
		N = board.length;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 0});
		visited[0][0][0] = true;
		int qsize = 0;
		while(q.size() > 0) {
			qsize = q.size();
			answer++;
			for (int qs = 0; qs < qsize; qs++) {
				int[] cur = q.poll();
				int r = cur[0], c = cur[1], s = cur[2];
				if(s == 0) { // 가로상태
					// 위 아래 왼쪽 오른쪽
					for (int d = 0; d < dr.length; d++) {
						int nr = r + dr[d], nc = c + dc[d];
						if(0 <= nr && nr < N && 0 <= nc && nc < N-1 && // 범위 내에 있고
								board[nr][nc] == 0 && board[nr][nc+1] == 0 && // 갈 수 있고
								!visited[nr][nc][s]) { // 안가본 곳이면
							if(isGoal(nr, nc, s)) {
								return answer;
							}
							visited[nr][nc][s] = true;
							q.offer(new int[] {nr, nc, s});
						}
					}
					// 위로 회전
					if(r - 1 >= 0 && board[r - 1][c] == 0 && board[r - 1][c + 1] == 0) { // 위로 회전 가능
						if(!visited[r-1][c][1]) { // 왼쪽 위로 회전이 안가본 곳이면
							// 위로 회전해서 골일 경우는 없다
							visited[r-1][c][1] = true;
							q.offer(new int[] {r-1, c, 1});
						}
						if(!visited[r-1][c+1][1]) { // 오른쪽 위로 회전이 안가본 곳이면
							// 위로 회전해서 골일 경우는 없다
							visited[r-1][c+1][1] = true;
							q.offer(new int[] {r-1, c+1, 1});
						}
					}
					// 아래로 회전
					if(r + 1 < N && board[r+1][c] == 0 && board[r+1][c+1] == 0) { // 아래로 회전 가능
						if(!visited[r][c][1]) { // 왼쪽 아래로 회전이 안가본 곳이면
							visited[r][c][1] = true;
							q.offer(new int[] {r, c, 1});
						}
						if(!visited[r][c+1][1]) { // 오른쪽 아래로 회전이 안가본 곳이면
							if(isGoal(r, c+1, s)) { // 가로->세로 회전으로는 유일하게 골일 수 있다
								return answer;
							}
							visited[r][c+1][1] = true;
							q.offer(new int[] {r, c+1, 1});
						}
					}
				}
				else { // 세로상태
					// 위 아래 왼쪽 오른쪽
					for (int d = 0; d < dr.length; d++) {
						int nr = r + dr[d], nc = c + dc[d];
						if(0 <= nr && nr < N-1 && 0 <= nc && nc < N && // 범위 내에 있고
								board[nr][nc] == 0 && board[nr+1][nc] == 0 && // 갈 수 있고
								!visited[nr][nc][s]) { // 안가본 곳이면
							if(isGoal(nr, nc, s)) {
								return answer;
							}
							visited[nr][nc][s] = true;
							q.offer(new int[] {nr, nc, s});
						}
					}
					// 왼쪽으로 회전
					if(c - 1 >= 0 && board[r][c-1] == 0 && board[r+1][c-1] == 0) { // 회전 가능
						if(!visited[r][c-1][0]) { // 왼쪽 위로 회전
							visited[r][c-1][0] = true;
							q.offer(new int[] {r, c-1, 0});
						}
						if(!visited[r+1][c-1][0]) { // 왼쪽 아래로 회전
							visited[r+1][c-1][0] = true;
							q.offer(new int[] {r+1, c-1, 0});
						}
					}
					// 오른쪽으로 회전
					if(c + 1 < N && board[r][c+1] == 0 && board[r+1][c+1] == 0) { // 가능
						if(!visited[r][c][0]) { // 오른쪽 위로 회전
							visited[r][c][0] = true;
							q.offer(new int[] {r, c, 0});
						}
						if(!visited[r+1][c][0]) { // 오른쪽 아래로 회전
							if(isGoal(r+1, c, 0)) {
								return answer;
							}
							visited[r+1][c][0] = true;
							q.offer(new int[] {r+1, c, 0});
						}
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
		
	}
	
}
