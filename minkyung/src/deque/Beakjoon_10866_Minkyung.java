package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beakjoon_10866_Minkyung {
	
	static int front = -1;
	static int rear = -1;
	static int[] deque;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		deque = new int[N];
		String order;
		StringTokenizer st;
		int X;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			order = br.readLine();
			switch(order) {
			case "front":
				sb.append(front()).append("\n");
				break;
			case "back":
				sb.append(back()).append("\n");
				break;
			case "size":
				sb.append(size()).append("\n");
				break;
			case "empty":
				sb.append(isEmpty()).append("\n");
				break;
			case "pop_front":
				sb.append(popFront()).append("\n");
				break;
			case "pop_back":
				sb.append(popBack()).append("\n");
				break;
			default:
				st = new StringTokenizer(order, " ");
				switch(st.nextToken()) {
				case "push_front":
					X = Integer.parseInt(st.nextToken());
					pushFront(X);
					break;
				case "push_back":
					X = Integer.parseInt(st.nextToken());
					pushBack(X);
					break;
				}
			}
		}
		
		System.out.print(sb.toString());
	}
	
	public static void pushFront(int X) {
		if(rear == -1) {
			deque[++rear] = X;
		} else if(front == -1) {
				System.arraycopy(deque, 0, deque, 1, ++rear);
				deque[0] = X;
		} else {
			deque[front--] = X;
		}
	}

	public static void pushBack(int X) {
		deque[++rear] = X;
	}
	
	public static int isEmpty() {
		if(rear == front) {
			return 1;
		} else {			
			return 0;
		}
	}
	
	public static int front() {
		if(front == rear) {
			return -1;
		} else {
			return(deque[front+1]);
		}
	}
	
	public static int back() {
		if(front == rear) {
			return -1;
		} else {
			return(deque[rear]);
		}
	}
	
	public static int size() {
		return(rear-front);
	}
	
	public static int popFront() {
		if(front == rear) {
			return(-1);
		} else {
			return(deque[++front]);
		}
	}
	
	public static int popBack() {
		if(front == rear) {
			return(-1);
		} else {
			return(deque[rear--]);
		}
	}
}
