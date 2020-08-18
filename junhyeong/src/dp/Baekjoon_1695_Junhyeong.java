package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Junhyeong
 * 팰린드롬 만들기
 * 최장 공통 문자열 문제의 응용.
 * Pivot 별로 할 필요 없이 그냥
 * 원래 수열이랑 뒤집은 수열이랑 비교해서 수열의 크기 N에서 빼면 된다.
 * 
 */

public class Baekjoon_1695_Junhyeong {

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
		int idx1 = 0, idx2 = N-1;
		while(true) {
			if(seq[idx1] == seq[idx2]) {
				lcs[idx1 + 1][N - idx2] = lcs[idx1][N-idx2-1] + 1;
			}
			else {
				lcs[idx1 + 1][N - idx2] = Math.max(lcs[idx1][N-idx2], lcs[idx1+1][N-idx2-1]);
			}
			if(--idx2 < 0) {
				if(++idx1 == N) {
					answer = N - lcs[N][N];
					break;
				}
				idx2 = N-1;
			}
		}
		System.out.println(answer);
		
	}
	
}
