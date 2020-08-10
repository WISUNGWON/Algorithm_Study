package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1695_Minkyung2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = N-1;
		
		for (int pivot = 1; pivot < N; pivot++) {
			// 뒤에서부터 탐색하는 숫자가 앞에서부터 탐색해야 하는 위치
			int index = 0;
			int cnt = 0;
			// 대칭의 기준이 숫자 2개 사이
			for (int end = N-1; end >= pivot; end--) {
				// 뒤에서부터 탐색하는 숫자가 앞에서부터 탐색하는 숫자와 같은 것을 찾았을 때 표시
				boolean flag = false;
				for (int i = index; i < pivot; i++) {
					if(arr[i] == arr[end]) {
						flag = true;
						cnt += i-index;
						index = i+1;
						break;
					}
				}
				if(!flag) cnt++;
			}
			cnt += pivot - index;
			if(min>cnt) min = cnt;
			
			// 대칭의 기준이 숫자
			index = 0;
			cnt = 0;
			for (int end = N-1; end > pivot; end--) {
				// 뒤에서부터 탐색하는 숫자가 앞에서부터 탐색하는 숫자와 같은 것을 찾았을 때 표시
				boolean flag = false;
				for (int i = index; i < pivot; i++) {
					if(arr[i] == arr[end]) {
						flag = true;
						cnt += i-index;
						index = i+1;
						break;
					}
				}
				if(!flag) cnt++;
			}
			cnt += pivot - index;
			if(min>cnt) min = cnt;
		}
		
		System.out.println(min);
	}

}
