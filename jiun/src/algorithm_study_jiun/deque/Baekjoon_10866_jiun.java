package algorithm_study_jiun.deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/10866
public class Baekjoon_10866_jiun {
	private static int[] deque;
	private static int front = -1;
	private static int rear = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		String cmd = "";
		int value = 0;
		deque = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			if (st.hasMoreTokens()) {
				value = Integer.parseInt(st.nextToken());
			}
			switch (cmd) {
			case "push_back":
				push_back(value);
				break;
			case "push_front":
				push_front(value);
				break;
			case "pop_front":
				sb.append(pop_front()).append("\n");
				break;
			case "pop_back":
				sb.append(pop_back()).append("\n");// 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				break;
			case "size":
				sb.append(size()).append("\n");
				break;
			case "empty":
				sb.append(empty()).append("\n");
				break;
			case "front":
				sb.append(front()).append("\n");
				break;
			case "back":
				sb.append(back()).append("\n");
				break;
			}
		}
		System.out.println(sb);
	}

	/** 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다. */
	private static int back() {
		return size() <= 0 ? -1 : deque[rear];
	}

	/** 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다. */
	private static int front() {
		return size() <= 0 ? -1 : deque[front + 1];
	}

	/** 덱이 비어있으면 1을, 아니면 0을 출력한다. */
	private static int empty() {
		return rear - front <= 0 ? 1 : 0;
	}

	private static int size() {
		return rear - front;
	}

	/** 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다. */
	private static int pop_back() {
		if (empty() == 1)
			return -1;
		else {
			int value = deque[rear];
			deque[rear--] = 0;
			return value;
		}
	}

	/** 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다. */
	private static int pop_front() {
		if (empty() == 1)
			return -1;
		else {
			int value = deque[0];
			for (int i = 1; i <= rear; i++) {
				deque[i - 1] = deque[i];
			}
			deque[rear] = 0;
			rear--;
			return value;
		}
	}

	/** 정수 X를 덱의 앞에 넣는다. */
	private static void push_front(int value) {
		// 현재 rear부터 한칸 씩 민다.
		for (int i = rear + 1; i > 0; i--) {
			deque[i] = deque[i - 1];
		}
		deque[0] = value; // 생성된 빈 자리에 값 넣기
		rear++;
	}

	/** 정수 X를 덱의 뒤에 넣는다. */
	private static void push_back(int value) {
		deque[++rear] = value;

	}
}
