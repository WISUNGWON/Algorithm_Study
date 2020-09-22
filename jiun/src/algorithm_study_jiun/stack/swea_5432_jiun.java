import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class swea_5432_jiun {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			String input = br.readLine();
			Stack<Character> st = new Stack<>();
			int cnt = 0, ans = 0; // cnt: 레이저를 쐈을 때 잘릴 막대기의 수
			for (int i = 0; i < input.length();i++) {
				char ch = input.charAt(i);
				switch (ch) {
				case '(':
						st.push('(');
						++cnt;
					break;

				case ')':
					if (input.charAt(i-1) == '(') { // 레이저
						st.pop();
						--cnt; // 레이저까지 세어준 것 빼주기.
						ans += cnt;
					}
					else if (input.charAt(i-1) == ')') {// 막대의 끝
						st.pop();
						--cnt; // 레이저까지 세어준 것 빼주기.
						ans+=1;
					}
					break;
				default:
					break;
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}// end of main
}// end of class
