package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Beakjoon_1918_Minkyung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		Stack<Character> stack = new Stack<>();
		char c;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if('A'<=c && 'Z'>=c) {
				sb.append(c);
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				while(!stack.isEmpty() && priority(stack.peek()) != 3) {
					sb.append(stack.pop());
				}
				stack.pop();
			} else {
				if(stack.isEmpty()) {
					stack.push(c);
				} else {
					while(!stack.isEmpty() && priority(stack.peek()) <= priority(c)) {
						sb.append(stack.pop());
					}
					stack.push(c);
				}
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb.toString());
		
	}

	static int priority(char x) {
		switch(x) {
		case '(':
			return 3;
		case '+':
		case '-':
			return 2;
		default:
			return 1;
		}
	}
	
}
