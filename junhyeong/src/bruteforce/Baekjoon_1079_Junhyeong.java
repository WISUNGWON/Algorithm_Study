package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1079_Junhyeong {
	
	static int N;
	static int[] guilt;
	static int[][] R;
	static int EJ;
	
	static int maxNights;

	static void dfs(int depth, int alive, int state, int died) {
		if(alive == 1) { // 마피아 빼고 다 죽었으면 볼것도 없다.
//			System.out.println("올킬");
			maxNights = depth;
			return;
		}
		if(alive % 2 == 0) { // 짝수명이면 밤
			for (int i = 0; i < N; i++) {
				if(i != EJ && (state >> i & 1) == 0) { // 은진이 말고 살아있는 사람을 죽여본다
					dfs(depth + 1, alive - 1, state | (1 << i), i);
				}
			}
		}
		else { // 홀수명이면 낮
			int maxGuilt = Integer.MIN_VALUE;
			int toDie = 0;
			for (int i = 0; i < N; i++) {
				guilt[i] += R[died][i]; // 방금 죽은 사람이 died이므로 그 상황에 맞게 유죄지수 조정
				if((state >> i & 1) == 0 && guilt[i] > maxGuilt) { // 산 사람 중 죽을 사람 고르기
					maxGuilt = guilt[i];
					toDie = i;
				}
			}
//			System.out.println(depth + "번째 밤에 " + died + "가 죽어서 낮에 " + toDie + "를 죽임");
			if(toDie == EJ) { // 낮에 죽을 사람이 은진이면
				maxNights = Math.max(maxNights, depth);
			}
			else {
				dfs(depth, alive - 1, state | (1 << toDie), toDie);
			}
			for (int i = 0; i < N; i++) {
				guilt[i] -= R[died][i]; // 유죄지수 원상복귀
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine().trim());
		guilt = new int[N];
		R = new int[N+1][N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			guilt[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		EJ = Integer.parseInt(br.readLine().trim());
		dfs(0, N, 0, N);
		System.out.println(maxNights);
	}
	
}
