package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeakJoon_1965_Sungwon {

	private static int[][] memo; // 최대 N이 5000 이상이기 때문에 메모이제이션 사용  (펠린드롬연산의 최소값 저장)
	private static int[] arr; // 입력받은 수열 저장 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수열의 길이 입력받기
		arr = new int[N];
		memo = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // --------------- 입력부 -----------------
		
		System.out.println(palindrome(0, N - 1)); // 결과 출력
		

	} // end of main
	/** 펠린드롬 알고리즘 
	 * 1. 시작(start)과 끝(end)값이 같으면 
	 * 시작과 끝값을 한칸 앞쪽으로 이동
	 * 2. 시작(start)과 끝(end)값이 다르면
	 * 펠린드롬을 만들기 위한 숫자가 1개 필요(+1) 또한 시작값과 끝값을 안쪽으로 이동하여 나온 결과들 중 최소값을 저장
	 * */
	public static int palindrome(int start, int end) { // 재귀를 이용하여 현재 자리에서 펠린드롬을 만들기 위한 최소값들을 저장하면서 결과값을 도출
		if (start > end) {
			return 0;
		}
		if (memo[start][end] != 0) { // 해당 시작과 끝값에서 이미 펠린드롬의 최소개수를 구했으면 연산한 결과를 리턴
			return memo[start][end];
		}
		if (arr[start] == arr[end]) { 
			memo[start][end] = palindrome(start + 1, end - 1);
		} else {
			memo[start][end] = Math.min(1 + palindrome(start + 1, end), 1 + palindrome(start, end - 1));
		}
		return memo[start][end];
	}
} // end of class
