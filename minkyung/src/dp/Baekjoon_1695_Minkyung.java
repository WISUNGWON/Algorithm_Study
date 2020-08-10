package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1695_Minkyung {
	
	static int[] arr;
	static int[][] memo;
	static int min = 5000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		memo = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < N; j++) {
				memo[i][j] = -1;
			}
		}
		
		System.out.println(check(0, N-1));
	}

	
	// 재귀를 하다보면 같은 계산을 반복적으로 하게 됨
	// 이것을 방지하기 위해 계산 결과를 기록해놓고 다시 불러온다
	private static int check(int start, int end) {
		if(start>=end) {
			return 0;
		}
		
		// 이미 그 전에 계산을 해놨던 것을 불러오기
		if(memo[start][end] != -1) return memo[start][end];
		memo[start][end] = 0;
		
		if(arr[start] == arr[end]) return memo[start][end] += check(start+1, end-1);
		else {
			// 왼쪽에서 한 칸 가는 것과 오른쪽에서 한칸 가서 나온 결과 중 최소 개수가 나온 것으로 채택
			return memo[start][end] += Math.min(check(start+1, end) + 1, check(start, end-1) + 1);
		}
	}

}

