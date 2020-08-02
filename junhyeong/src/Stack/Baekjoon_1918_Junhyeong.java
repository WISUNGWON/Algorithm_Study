package Stack;

import java.util.Scanner;

public class Baekjoon_1918_Junhyeong {

	final static int UNDEFINED = -1;	// Not Defined
	final static int ALPHA = 0;	// Alphabet
	final static int OP_PM = 1;	// Operator - Plus or Minus
	final static int OP_MD = 2;	// Operator - Multiply or Divide
	final static int BR_OPEN = 3;	// Bracket Open
	final static int BR_CLOSE = 4;	// Bracket Close

	static String line;
	static char[] opStack = new char[100];
	static int[] brCount = new int[100];
	static int idx = 0;
	static int cbr;

	static int identify(char ch) {
		if(ch >= 'A' && ch <= 'Z') {
			return ALPHA;
		}
		else if(ch == '+' || ch == '-') {
			return OP_PM;
		}
		else if(ch == '*' || ch == '/') {
			return OP_MD;
		}
		else if(ch == '(') {
			return BR_OPEN;
		}
		else if(ch == ')') {
			return BR_CLOSE;
		}
		return UNDEFINED;
	}

	static String solve() {
		StringBuilder sb = new StringBuilder();
		char ch;
		for (int i = 0; i < line.length(); i++) {
			ch = line.charAt(i);
			switch(identify(ch)) {
			case ALPHA:
				sb.append(ch);
				break;
			case OP_PM:
				while(idx > 0 && brCount[idx-1] == cbr) {
					sb.append(opStack[--idx]);
				}
				brCount[idx] = cbr;
				opStack[idx++] = line.charAt(i);
				break;
			case OP_MD:
				while(idx > 0 && brCount[idx-1] == cbr && identify(opStack[idx-1]) == OP_MD) {
					sb.append(opStack[--idx]);
				}
				brCount[idx] = cbr;
				opStack[idx++] = line.charAt(i);
				break;
			case BR_OPEN:
				cbr ++;
				break;
			case BR_CLOSE:
				while(idx > 0 && brCount[idx-1] == cbr) {
					sb.append(opStack[--idx]);
				}
				cbr --;
			}
		}
		while(idx > 0) {
			sb.append(opStack[--idx]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		line = sc.nextLine();
		sc.close();
		System.out.println(solve());
	}

}
