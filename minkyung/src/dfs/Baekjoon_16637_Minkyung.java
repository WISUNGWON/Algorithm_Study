package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_16637_Minkyung {

	static int max = Integer.MIN_VALUE;
	static char[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new char[N*2+1];
		String s = br.readLine();
		
		for (int i = 0; i < arr.length; i++) {
			if(i%2 == 0) arr[i] = ' ';
			else arr[i] = s.charAt(i/2);
		}
		
		calculate(); // 괄호 0개
		// 괄호 넣기
		for (int start = 0; start+6 < arr.length; start = start+4) {
			arr[start] = '(';
			arr[start+6] = ')';
			calculate();
			make(start+8);
			arr[start] = ' ';
			arr[start+6] = ' ';
		}
		
		System.out.println(max);
	}

	private static void make(int index) {
		for (int start = index; start+6 < arr.length; start = start+4) {
			arr[start] = '(';
			arr[start+6] = ')';
			calculate();
			make(start+8);
			arr[start] = ' ';
			arr[start+6] = ' ';
		}
	}

	public static void calculate() {
		int[] nums = new int[arr.length];
		char[] ops = new char[arr.length];
		int rearNum = -1, rearOps = -1;
		int frontNum = -1, frontOps = -1;
		
		for (int i = 0; i < arr.length; i++) {
			if('0' <= arr[i] && arr[i] <= '9') nums[++rearNum] = arr[i] - '0';
			else if(arr[i] == ' ' || arr[i] == '(') continue;
			else if(arr[i] == ')') {
				switch(ops[rearOps--]) {
				case '+':
					nums[rearNum-1] = nums[rearNum-1] + nums[rearNum];
					rearNum--;
					break;
				case '-':
					nums[rearNum-1] = nums[rearNum-1] - nums[rearNum];
					rearNum--;
					break;
				case '*':
					nums[rearNum-1] = nums[rearNum-1] * nums[rearNum];
					rearNum--;
					break;
				}
			}
			else ops[++rearOps] = arr[i];
		}
		while(frontOps<rearOps) {
			switch(ops[++frontOps]) {
			case '+':
				++frontNum;
				nums[frontNum+1] = nums[frontNum] + nums[frontNum+1];
				break;
			case '-':
				++frontNum;
				nums[frontNum+1] = nums[frontNum] - nums[frontNum+1];
				break;
			case '*':
				++frontNum;
				nums[frontNum+1] = nums[frontNum] * nums[frontNum+1];
			}
		}
		int result = nums[++frontNum];
		if(max<result) max = result;
	}
}
