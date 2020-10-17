package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_19238_Junhyeong {
	
	final static int[] dr = {-1, 0, 0, 1};
	final static int[] dc = {0, -1, 1, 0};
	
	static int N, M, fuel;
	
	static boolean[][] isWall;
	
	static int taxiR, taxiC;
	static int[][] customer;
	static int[][] visited;
	static int vlim;
	static int customerLeft;
	
	static int destR, destC;
	
	static Queue<int[]> q = new ArrayDeque<>();
	static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
		public int compare(int[] o1, int[] o2) {
			if(o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}
			return o1[0] - o2[0];
		};
	});
	
	
	static boolean findCustomer() {
		if(customer[taxiR][taxiC] > 0) {
			destR = customer[taxiR][taxiC] >> 5;
			destC = customer[taxiR][taxiC] & 0b11111;
			return true;
		}
		q.clear();
		pq.clear();
		q.offer(new int[] {taxiR, taxiC});
		visited[taxiR][taxiC] = ++vlim;
		while(q.size() > 0 && pq.size() == 0) { // 큐가 다 비거나 손님을 찾을때까지
			int qsize = q.size();
			fuel--;
			for (int qs = 0; qs < qsize; qs++) {
				int[] pos = q.poll();
				for (int d = 0; d < dr.length; d++) {
					int nr = pos[0] + dr[d];
					int nc = pos[1] + dc[d];
					if(nr < 1 || nr > N || nc < 1 || nc > N || isWall[nr][nc] || visited[nr][nc] == vlim) {
						continue;
					}
					visited[nr][nc] = vlim;
					if(customer[nr][nc] > 0) { // 간 곳에 손님이 있다
						pq.offer(new int[] {nr, nc});
						continue;
					}
					q.offer(new int[] {nr, nc});
				}
			}
		} // 손님 찾기 끝
		if(fuel < 0 || pq.size() == 0) { // 손님 못찾음
			return false;
		}
		int[] custPos = pq.poll(); // 손님 좌표
		taxiR = custPos[0];
		taxiC = custPos[1];
		destR = customer[taxiR][taxiC] >> 5;
		destC = customer[taxiR][taxiC] & 0b11111;
		return true;
	}
	
	static boolean goDestination() {
		// 택시 위치와 목적지를 이미 가지고 있는 상황
		q.clear();
		q.offer(new int[] {taxiR, taxiC});
		visited[taxiR][taxiC] = ++vlim;
		int moveCount = 0;
		while(q.size() > 0 && fuel > moveCount) {
			int qsize = q.size();
			moveCount ++;
			for (int qs = 0; qs < qsize; qs++) {
				int[] pos = q.poll();
				for (int d = 0; d < dr.length; d++) {
					int nr = pos[0] + dr[d];
					int nc = pos[1] + dc[d];
					if(nr < 1 || nr > N || nc < 1 || nc > N || isWall[nr][nc] || visited[nr][nc] == vlim) {
						continue;
					}
					if(nr == destR && nc == destC) { // 도착
						customer[taxiR][taxiC] = 0; // 손님이 있던 자리에 이제 아무도 없다
						taxiR = nr; // 택시 이동
						taxiC = nc; // 택시 이동
						fuel += moveCount; // 연료 채우기
						customerLeft--; // 한 명 해결 
						return true;
					}
					visited[nr][nc] = vlim;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		// 도달할 수 없거나 연료를 모두 소진
		return false;
	}
	
	static int solve() {
		customerLeft = M;
		while(customerLeft > 0) {
			if(!findCustomer()) {
				return -1;
			}
			if(!goDestination()) {
				return -1;
			}
		}
		return fuel;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		isWall = new boolean[N+1][N+1];
		visited = new int[N+1][N+1];
		customer = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1, idx = 0; j <= N; j++, idx += 2) {
				isWall[i][j] = (line.charAt(idx) == '1');
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		taxiR = Integer.parseInt(st.nextToken());
		taxiC = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			customer[r1][c1] = (r2 << 5) + c2;
		}
		
		System.out.println(solve());
		
	}
	
}
