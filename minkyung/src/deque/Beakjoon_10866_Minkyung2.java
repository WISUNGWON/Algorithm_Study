package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beakjoon_10866_Minkyung2 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque deque = Deque.getDeque();
		String order;
		StringTokenizer st;
		int X;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			order = br.readLine();
			switch(order) {
			case "front":
				sb.append(deque.front()).append("\n");
				break;
			case "back":
				sb.append(deque.back()).append("\n");
				break;
			case "size":
				sb.append(deque.size()).append("\n");
				break;
			case "empty":
				sb.append(deque.isEmpty()).append("\n");
				break;
			case "pop_front":
				sb.append(deque.popFront()).append("\n");
				break;
			case "pop_back":
				sb.append(deque.popBack()).append("\n");
				break;
			default:
				st = new StringTokenizer(order, " ");
				switch(st.nextToken()) {
				case "push_front":
					X = Integer.parseInt(st.nextToken());
					deque.pushFront(X);
					break;
				case "push_back":
					X = Integer.parseInt(st.nextToken());
					deque.pushBack(X);
					break;
				}
			}
		}
		
		System.out.print(sb.toString());
	}

}

class Deque {
	private int front = -1;
	private int rear = -1;
	private Deque() {
	}
	private static Deque Deque = new Deque();
	private static int[] deque = new int[10000];
	static Deque getDeque () {
		return Deque;
	}
	
	public void pushFront(int X) {
		if(rear == -1) {
			deque[++rear] = X;
		} else if(front == -1) {
				System.arraycopy(deque, 0, deque, 1, ++rear);
				deque[0] = X;
		} else {
			deque[front--] = X;
		}
	}
	
	public void pushBack(int X) {
		deque[++rear] = X;
	}
	
	public int isEmpty() {
		if(rear == front) {
			return 1;
		} else {			
			return 0;
		}
	}
	
	public int front() {
		if(front == rear) {
			return -1;
		} else {
			return(deque[front+1]);
		}
	}
	
	public int back() {
		if(front == rear) {
			return -1;
		} else {
			return(deque[rear]);
		}
	}
	
	public int size() {
		return(rear-front);
	}
	
	public int popFront() {
		if(front == rear) {
			return(-1);
		} else {
			return(deque[++front]);
		}
	}
	
	public int popBack() {
		if(front == rear) {
			return(-1);
		} else {
			return(deque[rear--]);
		}
	}
}

