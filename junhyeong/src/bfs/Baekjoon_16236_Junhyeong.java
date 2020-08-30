package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Baekjoon_16236_Junhyeong {

	static int N;
	
	/** Baby Shark Position */
	static int sr, sc;
	static int[][] map;
	static boolean[][] visited;
	static int sharkLevel = 2;
	static int fishEaten = 0;
	
	static Queue<int[]> q = new ArrayDeque<>();
	
	static final int[] dr = {0, 0, 1, -1};
	static final int[] dc = {1, -1, 0, 0};
	
	static int[][] ableFish = new int[40][2];
	
	static int bfs() { // 아기 상어가 한 마리 먹는 동안의 BFS
		// 자원 초기화
		resetVisited();
		q.clear();
		// 자원 초기화 끝
		
		// 초기 큐 상태
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		int qsize, fishCount, stage = 0;
		// BFS 시작
		while(!q.isEmpty()) {
			qsize = q.size();
			stage++;
			fishCount = 0;
			for (int i = 0; i < qsize; i++) {
				int[] pos = q.poll();
				for (int d = 0; d < dr.length; d++) {
					int nr = pos[0] + dr[d];
					int nc = pos[1] + dc[d];
					if(0 <= nr && nr < N && 0 <= nc && nc < N 
							&& !visited[nr][nc] && map[nr][nc] <= sharkLevel){ // 지나갈 수 있는 곳이면 확인
						visited[nr][nc] = true;
						if(0 < map[nr][nc] && map[nr][nc] < sharkLevel) { // 먹을 수 있는 물고기면 먹을수있는 물고기 배열에 저장
							ableFish[fishCount][0] = nr;
							ableFish[fishCount][1] = nc;
							fishCount++;
						}
						else if(fishCount == 0){ // 먹을 수 있는 물고기가 없을때만 큐에 넣는게 유효하다.
							q.offer(new int[] {nr, nc});
						}
					}
				}
			}
			if(fishCount > 0) { // 만약 먹을 수 있는 물고기가 있다면
				int minr = Integer.MAX_VALUE;
				int minc = Integer.MAX_VALUE;
				for (int i = 0; i < fishCount; i++) {
					if(ableFish[i][0] < minr) {
						minr = ableFish[i][0];
						minc = ableFish[i][1];
					}
					else if(ableFish[i][0] == minr && ableFish[i][1] < minc) {
						minc = ableFish[i][1];
					}
				}
				map[minr][minc] = 0;
				sr = minr;
				sc = minc;
				return stage;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0, index = 0; j < N; j++, index += 2) {
				map[i][j] = line.charAt(index) - '0';
				if(map[i][j] == 9) {
					sr = i;
					sc = j;
					map[i][j] = 0;
				}
			}
		}
		int totalTime = 0;
		while(true) {
			int result = bfs();
			if(result == -1) {
				break;
			}
			else {
				if(++fishEaten == sharkLevel) {
					sharkLevel ++;
					fishEaten = 0;
				}
				totalTime += result;
			}
		}
		System.out.println(totalTime);
		
	}
	
	static void resetVisited() {
		int i, j;
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
	}
	
}
