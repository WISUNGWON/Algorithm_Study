package algorithm_study_jiun.stack;

import java.util.Scanner;
import java.util.Stack;

//https://www.acmicpc.net/problem/1918
public class Baekjoon_1918_jiun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String expression = sc.next();
		Stack<Character> st = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			switch (ch) {
			case '(':
				st.push(ch);
				break;
			case ')':
				while (!st.isEmpty() && st.peek() != '(') {
					System.out.print(st.pop());
				}
				st.pop();
				break;
			case '+':
			case '-':
				while (!st.isEmpty() && st.peek() != '(') {
					System.out.print(st.pop());
				}
				st.add(ch);
				break;
			case '*':
			case '/':
				while (!st.isEmpty() && (st.peek() == '*' || st.peek() == '/')) {
					System.out.print(st.pop());
				}
					
				st.add(ch);
				break;
			default:
				System.out.print(ch);
			}
		}

		while (!st.isEmpty()) {
			System.out.print(st.pop());
		}
	}
}
