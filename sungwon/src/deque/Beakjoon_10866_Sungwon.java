package deque;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Beakjoon_10866_Sungwon {
	
	static Deque<Integer> deque = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String order;
		int num;
		
		for (int i = 1; i <= N; i++) {
			order = sc.next();
			if (order.equals("push_back")) {
				num = sc.nextInt();
				deque.addLast(num);;
			} else if (order.equals("push_front")) {
				num = sc.nextInt();
				deque.addFirst(num);
			} else if (order.equals("pop_front")) {
				if (deque.size() < 1) {
					System.out.println(-1);
				} else {
					System.out.println(deque.pollFirst());
				}
			} else if (order.equals("pop_back")) {
				if (deque.size() < 1) {
					System.out.println(-1);
				} else {
					System.out.println(deque.pollLast());
				}
			} else if (order.equals("size")) {
				System.out.println(deque.size());
			} else if (order.equals("empty")) {
				if (deque.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			} else if (order.equals("front")) {
				if (deque.size() < 1) {
					System.out.println(-1);
				} else {
					System.out.println(deque.peekFirst());
				}
			} else if (order.equals("back")) {
				if (deque.size() < 1) {
					System.out.println(-1);
				} else {
					System.out.println(deque.peekLast());
				}
			}
		}
		
		sc.close();
	}

}
