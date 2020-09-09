package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author Junhyeong
 * 249ms
 */
public class SWEA_2117_Junhyeong {

	static int N, M;
	
	static char[][] map = new char[20][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		String line;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int count = 0;
			for (int i = 0; i < N; i++) {
				line = br.readLine();
				map[i] = line.replace(" ", "").toCharArray();
				for (int j = 0; j < N; j++) {
					if(map[i][j] == '1') {
						count++;
					}
				}
			}
			int size = 1;
			int answer = 0;
			int tmp = 0;
			int maxCost = count * M;
			while(true) {
				int cost = size * size + (size - 1) * (size - 1);
				if(cost > maxCost) {
					break;
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						tmp = check(i, j, size);
						if(tmp * M >= cost && answer < tmp) {
							answer = tmp;
						}
					}
				}
				size++;
			}
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb.toString());
		
	}
	
	static int check(int r, int c, int size) {
		int s = size - 1;
		int row, col, lim;
		int result = 0;
		for (int i = -s; i <= s; i++) {
			row = r + i;
			if(row < 0 || row >= N) continue;
			lim = s - Math.abs(i);
			for (int j = -lim; j <= lim; j++) {
				col = c + j;
				if(0 <= col && col < N && map[row][col] == '1') {
					result++;
				}
			}
		}
		return result;
	}
	
}
