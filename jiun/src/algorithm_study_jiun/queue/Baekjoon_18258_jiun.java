package algorithm_study_jiun.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 972ms
//https://www.acmicpc.net/problem/18258
public class Baekjoon_18258_jiun {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		ArrayQueue q = new ArrayQueue(n);
		String cmd = "";
		int value = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			if (st.hasMoreTokens()) {
				value = Integer.parseInt(st.nextToken());
			}
			switch (cmd) {
			case "push":
				q.push(value);
				break;
			case "pop":
				sb.append(q.pop()).append("\n");
				break;
			case "size":
				sb.append(q.size()).append("\n");
				break;
			case "empty":
				sb.append(q.empty()).append("\n"); // 덱이 비어있으면 1을, 아니면 0을 출력한다.
				break;
			case "front":
				sb.append(q.front()).append("\n"); // 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				break;
			case "back":
				sb.append(q.back()).append("\n"); // 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				break;
			}
		}
		System.out.println(sb);
	}
}

class ArrayQueue implements Queue {
	int front;
	int rear;
	private int size;
	private int queue[];

	public ArrayQueue(int size) {
		front = -1;
		rear = -1;
		this.size = size;
		queue = new int[size];
	}

	@Override
	/** empty: 큐가 비어있으면 1, 아니면 0을 출력한다. */
	public int empty() {
		return size() <= 0 ? 1 : 0;
	}

	/** 정수 X를 큐에 넣는 연산이다. */
	@Override
	public void push(int x) {
		queue[++rear] = x;
	}

	@Override
	/** 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다. */
	public int pop() {
		return this.empty() == 1 ? -1 : queue[++front];
	}

	@Override
	/** front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다. */
	public int front() {
		return this.size() <= 0 ? -1 : queue[front + 1]; 
	}

	@Override
	/** back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다. */
	public int back() {
		return this.size() <= 0 ? -1 : queue[rear];
	}

	@Override
	/** size: 큐에 들어있는 정수의 개수를 출력한다. */
	public int size() {
		return rear - front;
	}

}

interface Queue {
	int empty();

	void push(int x);

	int pop();

	int front();

	int back();

	int size();
}
