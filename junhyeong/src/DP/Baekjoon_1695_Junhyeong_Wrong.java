package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Junhyeong
 * 팰린드롬 만들기
 * 최장 공통 문자열 문제의 응용.
 * 어떤 Pivot P를 잡고 양쪽을 비교해가면서 최장 공통 문자열이 가장 긴 것을 찾으면 되는 문제.
 * 주의할 점은 P가 수열의 인덱스만 있는게 아니라 인덱스 사이에 있을 수 있다는 점
 * ex) 1, 2, 1의 P는 1번 인덱스지만 1, 2, 2, 1은 1번과 2번 사이이다.
 * 
 * 
 */


public class Baekjoon_1695_Junhyeong_Wrong {

	static int N;
	static int[] seq;
	
	static int[][] lcs;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		seq = new int[N];
		lcs = new int[N+1][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int i;
		for (i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		answer = N;
		int startIdx1, startIdx2, idx1, idx2;
		for (i = 1; i < N - 1; i++) {
			startIdx1 = i - 1;
			startIdx2 = i;
			idx1 = i - 1;
			idx2 = i;
			while(true) {
				//System.out.println(idx1 + "(" + seq[idx1] + ") " + idx2 + "(" + seq[idx2] + ")");
				if(seq[idx1] == seq[idx2]) {
					// System.out.println((startIdx1 - idx1 + 1) + " " + (idx2 - startIdx2 + 1));
					lcs[startIdx1 - idx1 + 1][idx2 - startIdx2 + 1] = lcs[startIdx1 - idx1][idx2 - startIdx2] + 1;
				}
				else {
					lcs[startIdx1 - idx1 + 1][idx2 - startIdx2 + 1] = Math.max(lcs[startIdx1 - idx1 + 1][idx2 - startIdx2], lcs[startIdx1 - idx1][idx2 - startIdx2 + 1]);
				}
				if(++idx2 == N) {
					if(--idx1 < 0) {
						answer = Math.min(answer, N - 2 * lcs[startIdx1 - idx1][idx2 - startIdx2]);
						break;
					}
					idx2 = startIdx2;
				}
			}
			for (int r = 0; r < startIdx1 + 2; r++) {
				for (int c = 0; c < N-startIdx2+1; c++) {
					System.out.print(lcs[r][c] + " ");
				}
				System.out.println();
			}
			System.out.println();
			startIdx1 = i - 1;
			startIdx2 = i + 1;
			idx1 = i - 1;
			idx2 = i + 1;
			while(true) {
				if(seq[idx1] == seq[idx2]) {
					lcs[startIdx1 - idx1 + 1][idx2 - startIdx2 + 1] = lcs[startIdx1 - idx1][idx2 - startIdx2] + 1;
				}
				else {
					lcs[startIdx1 - idx1 + 1][idx2 - startIdx2 + 1] = Math.max(lcs[startIdx1 - idx1 + 1][idx2 - startIdx2], lcs[startIdx1 - idx1][idx2 - startIdx2 + 1]);
				}
				if(--idx1 < 0) {
					if(++idx2 >= N) {
						answer = Math.min(answer, N - 1 - 2 * lcs[startIdx1 - idx1][idx2 - startIdx2]);
						break;
					}
					idx1 = i - 1;
				}
			}
		}
		System.out.println(answer);
	}
	
}
