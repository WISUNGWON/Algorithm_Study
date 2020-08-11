package algorithm_study_jiun;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baekjoon_1695_팰린드롬 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(); // (팰린드롬 고유숫자, 숫자의 갯수)
		int len = arr.length;
		int mid = n/2;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n; i++) {
			hash.put(arr[i], hash.getOrDefault(arr[i], 0) + 1);
		}
		int cnt = 0;
		if(len % 2 == 0) { //가운데 숫자 후보가 두 개.
			int a = hash.get(arr[mid]);
//			int b = hash.get(mid+1);
			if(a % 2 == 1) cnt--;
//			if(b % 2 == 0) 
			
		}
		
		for (int key : hash.keySet()) {
			int current = hash.get(key);
			while(current % 2 != 0) {
				cnt++;
				current++;
			}
		}
		System.out.println(len % 2 == 0? cnt : cnt-1);
	} 
}
