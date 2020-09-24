import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2602_jiun {
	static char[] find;
	static char[] devil;
	static char[] angel;
	static int[][][] memo = new int[100][20][2]; // 현재 밟은 곳, 다음 밟을 곳, 악마인지 천사인지.
	static int ans;

	public static int move(int c, int n, int r) {
		if (n == find.length) { // 모두 건넌 경우
			return 1;
		}

		if (memo[c][n][r] >0) { 
			return memo[c][n][r]; //memo 값 리턴
		}

		int total = 0;
		if (r == 0) { 
			for (int i = c; i < devil.length; i++) {// 악마 돌다리 차례
				if (find[n] == devil[i]) {
					total += move(i + 1, n + 1, 1);
				}
			}
		} else { 
			for (int i = c; i < angel.length; i++) {//천사 돌다리 차례
				if (find[n] == angel[i]) {
					total += move(i + 1, n + 1, 0);
				}
			}
		}
		return memo[c][n][r] = total;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		find = br.readLine().toCharArray();
		devil = br.readLine().toCharArray();
		angel = br.readLine().toCharArray();

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 20; j++) {
				for (int k = 0; k < 2; k++) {
					memo[i][j][k] = 0;
				}
			}
		}
		int a = move(0, 0, 0); //천사 돌다리부터 선택
		int b = move(0, 0, 1); // 악마 돌다리부터 선택

		System.out.println(a + b);
	}
}
