package Backtracking;

import java.util.Scanner;

/**
 * 
 * @author Junhyeong
 *
 * N-Queen 문제
 * 
 * 6020ms
 * 
 */

public class Baekjoon_9663_Junhyeong {
	
	static int N;
	
	static int answer = 0;
	
	static int[] pointC; // 인덱스 r인 Row에서 몇 번째 Col에 퀸을 놓았는지
	
	static void dfs(int r) {
		if(r == N) { // 마지막 Row까지 채웠으면
			answer ++; // answer + 1
			return;
		}
		for (int i = 0; i < N; i++) {
			if(canPutQueen(r, i)) { // 놓을 수 있으면
				pointC[r] = i; // 놓고
				dfs(r + 1); // 다음 Row로 넘어간다
			}
		}
	}
	
	static boolean canPutQueen(int r, int c) {
		for (int i = 0; i < r; i++) {
			if(c == pointC[i] || Math.abs(r-i) == Math.abs(c-pointC[i])) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		pointC = new int[N];
		dfs(0);
		System.out.println(answer);
	}
	
}
