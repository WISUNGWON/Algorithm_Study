package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Baekjoon_16637_Junhyeong {
	
	static int N, totalOper;
	static String line;
	static int answer = Integer.MIN_VALUE;
	static int[] num;
	static char[] opers;
	static boolean[] priority;
	
	static int calcSingle(int a, int b, char oper) {
		switch(oper) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		}
		return 0;
	}
	
	static Queue<Integer> numq = new ArrayDeque<>();
	static Queue<Character> operq = new ArrayDeque<>();
	
	static void calcAll() {
		numq.clear();
		operq.clear();
		for (int i = 0; i < totalOper; i++) {
			if(priority[i]) {
				numq.offer(calcSingle(num[i], num[i+1], opers[i]));
				if(i != totalOper-1) {
					operq.offer(opers[++i]);
				}
			}
			else {
				numq.offer(num[i]);
				operq.offer(opers[i]);
			}
		}
		if(!priority[totalOper-1]) {
			numq.offer(num[totalOper]);
		}
//		System.out.println(numq);
//		System.out.println(operq);
		int result = numq.poll();
		while(!operq.isEmpty()) {
			result = calcSingle(result, numq.poll(), operq.poll());
		}
		answer = Math.max(answer, result);
	}
	
	static void makeCombination(int idx) {
		if(idx >= totalOper) {
			calcAll();
			return;
		}
		makeCombination(idx + 1);
		priority[idx] = true;
		makeCombination(idx + 2);
		priority[idx] = false;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		line = br.readLine();
		if(N == 1) {
			System.out.println(line);
			return;
		}
		totalOper = N / 2;
		opers = new char[totalOper];
		num = new int[totalOper+1];
		priority = new boolean[totalOper];
		for (int i = 0, idx = 0; i <= totalOper; i++, idx += 2) {
			num[i] = (int)(line.charAt(idx)-'0');
		}
		for (int i = 0, idx = 1; i < totalOper; i++, idx += 2) {
			opers[i] = line.charAt(idx);
		}
		makeCombination(0);
		System.out.println(answer);
		
	}
	
}
