package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_5432_Junhyeong {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int answer, braceCount, len;
		String line;
		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			braceCount = 0;
			line = br.readLine().trim();
			len = line.length() - 1;
			for (int i = 0; i < len; i++) {
				if(line.charAt(i) == '(') {
					if(line.charAt(i+1) == ')') {
						answer += braceCount;
						i++;
					}
					else {
						braceCount ++;
						answer++;
					}
				}
				else {
					braceCount--;
				}
			}
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb.toString());
	}
	
}
