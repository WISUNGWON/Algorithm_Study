package backtracking;

import java.util.Arrays;
import java.util.Scanner;
/** 7936ms/1000ms */
public class Baekjoon_9663_jiun {
	private static int n;
	private static boolean[][] visit;
	private static int[][] map;
	private static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		cnt = 0;
		map = new int[n][n];
		visit = new boolean[n][n];

//		for (int i = 0; i < n; i++) {
		placeQueen(0);//0번째 열에 퀸을 배치시키는 경우
//		}
		System.out.println(cnt);
	}

	/** row번째 행에서 퀸이 위치할 수 있는 경우의 수를 구하는 메서드 
	 * - 각 행에 1개의 퀸만 위치할 수 있으므로, 행을 고정시켜 경우의 수를 구한다. */
	private static void placeQueen(int row) {
		if (row >= n) { // 마지막 row까지 검사가 끝나면 종료한다.
			cnt++;
			return;
		}
		for (int i = 0; i < n; i++) {//row의  i번째 행 중 퀸을 놓을 수있는 경우를 찾는다.
			if (!visit[row][i] && isValidSpace(row, i)) {// i번째 열에 놓을 수 있는지 검사
				visit[row][i] = true; // i번째 열에 퀸을 놓는다.
				placeQueen(row + 1); // 다음 행의 퀸 자리 찾기
				visit[row][i] = false;// 다음 검사를 위해 i번째 열에 퀸을 뺀다. 
			}
		}
	}

	/** 상,좌우대각선에 다른 퀸이 존재하는 확인하는 메서드
	 * - 존재하면 그 자리에 퀸을 놓을 수 없으므로 false를 리턴한다. 
	 * - 위에서 부터 퀸을 놓으므로 row 윗 부분만 검사하면 된다. */
	private static boolean isValidSpace(int row, int col) {
		for (int i = 0; i < row; i++) { // col행의 윗부분(직선) 검사
			if (visit[i][col])
				return false;
		}

		int i = row - 1;// 왼쪽 대각선 검사
		int j = col - 1;
		while (i >= 0 && j >= 0) {
			if (visit[i][j])
				return false;
			i -= 1;
			j -= 1;
		}

		i = row - 1;// 오른쪽 위 대각선 검사
		j = col + 1;
		while (i >= 0 && j < n) {
			if (visit[i][j])
				return false;
			i -= 1;
			j += 1;
		}

		return true; //현재 col에 놓을 수 있는 경우(= 미리 배치된 queen에게 공격당하지 않는 경우) true리턴
	}
}
