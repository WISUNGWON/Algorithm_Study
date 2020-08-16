package backtracking;

import java.util.Scanner;
// BackTracking 예시 NQueen 문제
/**
 * 
 * N퀸 문제는 각 조건을 만족할 경우 각행에 퀸이 1개씩 온다
 *
 */


public class NQueen {
	static int N; // 체스판 크기
	static int[] rows; // 퀸의 위치를 저장할 배열. 인덱스는 행을 값은 열을 나타낸다
	static class N_Queens {
		
		private int N;
		public N_Queens(int N) {
			this.N = N;
			rows = new int[N];
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		N_Queens nQ = new N_Queens(N);
		back_tracking(0);
		
		
	}
	
	public static void back_tracking(int row) {
		if (row == N) { // 현재 위치가 N이라는 것은 N - 1까지 퀸이 다 배치되어 있다는 뜻이므로 정답 배열 출력
			for (int i = 0; i < N; i++) {
				System.out.print(rows[i]);
			}
			System.out.println("");
		} else {
			for (int i = 0; i < N; i++) {
				rows[row] = i;
				if (isPossible(row)) {
					back_tracking(row + 1);
				}
			}
		}
	}

	private static boolean isPossible(int row) {
		for (int i = 0; i < row; i++) { // 현재 row에 퀸을 놓았다고 가정하고 N - 1에 있는 퀸과 문제가 없는지 확인
			if (rows[i] == rows[row] || Math.abs(row - i) == Math.abs(rows[row] - rows[i])) {
				// i번째 행의 퀸과 row의 위치의 퀸이 같은 열에(행은 고려안해도됨) 있거나 대각선상에 있는 경우
				return false;
			}
		}
		return true;
	}

} // end of class
