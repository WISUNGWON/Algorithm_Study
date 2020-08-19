package algorithm_study_jiun.dfs;
// https://www.acmicpc.net/problem/16637
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_16637_jiun {
	static int ans, n;
	static int[] operand;
	static char[] operator;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		operand = new int[n / 2 + 1]; // 피연산자(숫자)
		operator = new char[n / 2]; // 연산자(+,-,*)

		int operand_idx = 0;
		int operator_idx = 0;
		String expression = br.readLine();
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
				operator[operator_idx++] = expression.charAt(i);
			} else {
				operand[operand_idx++] = Integer.parseInt(expression.charAt(i) + "");
			}
		} // 연산자, 피연산자 구분해 저장

		ans = Integer.MIN_VALUE;
		dfs(0, operand[0]);
		System.out.println(ans);
	}

	private static void dfs(int idx, int res) {
		if (idx >= n / 2) {// 모든 연산을 다 한 경우: 인덱스가 n/2가  되는 경우
			if (ans < res)
				ans = res;
			return;
		}
		int ret = cal(res, operator[idx], operand[idx + 1]); // 경우1. 연산+연산자+연산
		dfs(idx + 1, ret);

		if (idx + 1 < n / 2) {
			ret = cal(res, operator[idx], cal(operand[idx + 1], operator[idx + 1], operand[idx + 2])); // 경우2. 연산+(연산+연산자+연산) 
			dfs(idx + 2, ret);
		}

	}

	private static int cal(int a, char op, int b) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}
		return -1;
	}

}
