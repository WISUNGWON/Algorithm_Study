package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_16987_Junhyeong {

	static int N;
	
	static int[] solid;
	static int[] weight;
	
	static int maxEgg = 0;
	
	static int checkBroken() {
		int broken = 0;
		for (int i = 0; i < N; i++) {
			if(solid[i] <= 0) {
				broken++;
			}
		}
		return broken;
	}
	
	static void solve(int egg) {
		if(egg == N) {
			maxEgg = Math.max(maxEgg, checkBroken());
			return;
		}
		if(solid[egg] <= 0) {
			solve(egg + 1);
			return;
		}
		boolean isOkay = false;
		for (int i = 0; i < N; i++) {
			if(i != egg && solid[i] > 0) {
				isOkay = true;
				solid[i] -= weight[egg];
				solid[egg] -= weight[i];
				solve(egg + 1);
				solid[i] += weight[egg];
				solid[egg] += weight[i];
			}
		}
		if(!isOkay) {
			solve(egg + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		solid = new int[N];
		weight = new int[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			solid[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
		solve(0);
		
		System.out.println(maxEgg);
		
	}
	
}
