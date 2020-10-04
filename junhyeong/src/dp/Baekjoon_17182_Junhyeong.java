package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 17182 우주 탐사선
 * @author Junhyeong
 * 
 * Floyd-Warshall로 모든 최단거리 구하고 나서
 * TSP 알고리즘 적용
 *
 */
public class Baekjoon_17182_Junhyeong {

	static int N, K;
	static int[][] T;
	static int[][] minTime;
	
	static int fullPath;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = new int[N][N];
		minTime = new int[N][1<<N];
		fullPath = (1 << N) - 1;
		int i, j, k, tmp;
		for (i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (j = 0; j < N; j++) {
				T[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (i = 0; i < N; i++) {
			for (j = fullPath; j >= 0; j--) {
				minTime[i][j] = -1;
			}
		}
		for (k = 0; k < N; k++) {
			for (i = 0; i < N; i++) {
				for (j = 0; j < N; j++) {
					tmp = T[i][k] + T[k][j];
					if(tmp < T[i][j]) {
						T[i][j] = tmp;
					}
				}
			}
		}
		
		System.out.println(tsp(K, 1<<K));
		
	}
	
	/**
	 * @param cur 지금 있는 위치
	 * @param path 지금까지 거쳐온 행성의 Bitmask
	 * @return 모든 행성 거치는 최단 거리
	 */
	static int tsp(int cur, int path) {
		if(minTime[cur][path] >= 0) {
			return minTime[cur][path];
		}
		if(path == fullPath) {
			return minTime[cur][path] = 0;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if(((path >> i) & 1) == 0) { // i번째 행성에 안가봤다면
				answer = Math.min(answer, T[cur][i] + tsp(i, path | (1 << i)));
			}
		}
		return minTime[cur][path] = answer;
	}
	
}
