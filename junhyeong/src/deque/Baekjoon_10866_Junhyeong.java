package deque;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_10866_Junhyeong {

	static class Deque {
		final static int MAX_SIZE = 10000;
		int size;
		int start;
		int end;
		int[] arr;

		public Deque() {
			size = 0;
			start = 0;
			end = 0;
			arr = new int[MAX_SIZE];
		}

		public int getSize() {
			return size;
		}

		public int isEmpty() {
			return size == 0 ? 1 : 0;
		}

		public int front() {
			return size > 0 ? arr[start] : -1;
		}

		public int back() {
			return size > 0 ? arr[(end + MAX_SIZE - 1) % MAX_SIZE] : -1;
		}

		public void push_front(int n) {
			start = (start + MAX_SIZE - 1) % MAX_SIZE;
			size++;
			arr[start] = n;
		}

		public void push_back(int n) {
			arr[end] = n;
			size++;
			end = (end + 1) % MAX_SIZE;
		}

		public int pop_front() {
			if (size == 0) {
				return -1;
			}
			int n = arr[start];
			start = (start + 1) % MAX_SIZE;
			size--;
			return n;
		}

		public int pop_back() {
			if (size == 0) {
				return -1;
			}
			end = (end + MAX_SIZE - 1) % MAX_SIZE;
			size--;
			return arr[end];
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Deque dq = new Deque();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switch(st.nextToken()) {
			case "push_front":
				dq.push_front(Integer.parseInt(st.nextToken()));
				break;
			case "push_back":
				dq.push_back(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front":
				sb.append(dq.pop_front()).append('\n');
				break;
			case "pop_back":
				sb.append(dq.pop_back()).append('\n');
				break;
			case "size":
				sb.append(dq.getSize()).append('\n');
				break;
			case "empty":
				sb.append(dq.isEmpty()).append('\n');
				break;
			case "front":
				sb.append(dq.front()).append('\n');
				break;
			case "back":
				sb.append(dq.back()).append('\n');
				break;
			}
		}
		
		System.out.print(sb);
	}

}
