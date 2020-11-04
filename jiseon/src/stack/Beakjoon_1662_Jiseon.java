package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Beakjoon_1662_Jiseon{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] c = br.readLine().toCharArray();
		
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < c.length; i++) {
			if(c[i] == '(') {
				st.push(-1);
			} else if(c[i] == ')') {
				int len = 0;
				while(true) {
					int temp = st.pop();
					if(temp == -1) break;
					
					if(temp >= 100) len += temp - 100;
					else len++;
				}
				st.push((st.pop() * len) + 100);
			} else {
				st.push(c[i] - '0');
			}
		}
		int ans = 0;
		while(st.size() > 0) {
			int cur = st.pop();
			if(cur >= 100) ans += cur - 100;
			else ans++;
		}
		System.out.println(ans);
	}
}
