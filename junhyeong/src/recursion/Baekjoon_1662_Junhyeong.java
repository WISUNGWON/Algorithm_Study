package recursion;

import java.util.Scanner;

public class Baekjoon_1662_Junhyeong {
	
	static String line;
	
	static int getLength(int start, int end) {
		if(start == end) {
			return 0;
		}
		int length = 0;
		for (int i = start; i < end - 1; i++) {
			if(Character.isDigit(line.charAt(i)) && line.charAt(i+1) == '(') { // 압축된 부분
				int multiplier = line.charAt(i) - '0';
				int nextStart = i + 2;
				int count = 1;
				for (int j = i + 2; j < end; j++) {
					if(line.charAt(j) == '(') {
						count++;
					}
					else if(line.charAt(j) == ')') {
						if(--count == 0) {
							i = j;
							break;
						}
					}
				}
				length += multiplier * getLength(nextStart, i);
			}
			else {
				length ++;
			}
		}
		if(line.charAt(end - 1) != ')') {
			length ++;
		}
		return length;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		line = sc.nextLine();
		sc.close();
		System.out.println(getLength(0, line.length()));
	}
	
}
