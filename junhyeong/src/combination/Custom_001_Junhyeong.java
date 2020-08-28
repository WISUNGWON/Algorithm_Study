package combination;

import java.util.ArrayList;

public class Custom_001_Junhyeong {

	static final int MAX_NUM = 1000;
	
	static int limit = MAX_NUM << 1;
	
	static boolean[] isPrime = new boolean[limit];
	
	static int N;
	static ArrayList<Integer> primeList = new ArrayList<>();
	
	static void makePrimeList() {
		int i, j;
		for (i = 2; i < limit; i++) {
			isPrime[i] = true;
		}
		
		for (i = 2; i <= MAX_NUM; i++) {
			if(isPrime[i]) {
				primeList.add(i);
				j = i << 1;
				while(j < limit) {
					isPrime[j] = false;
					j += i;
				}
			}
		}
		N = primeList.size();
		for (i = MAX_NUM + 1; i < limit; i++) {
			if(isPrime[i]) {
				j = i << 1;
				while(j < limit) {
					isPrime[j] = false;
					j += i;
				}
			}
		}
	}
	
	static boolean nextPermutation(int[] number) {
		int i = N - 1;
		while(i > 0 && number[i-1] >= number[i]) {
			i --;
		}
		if(i == 0) return false;
		int j = N - 1;
		while(number[i-1] >= number[j]) {
			j --;
		}
		
		int tmp;
		tmp = number[i-1];
		number[i-1] = number[j];
		number[j] = tmp;
		
		j = N - 1;
		while(i < j) {
			tmp = number[i];
			number[i] = number[j];
			number[j] = tmp;
			i++;
			j--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		makePrimeList();
		
		int[] combNumber = new int[N];
		combNumber[N-1] = 1;
		combNumber[N-2] = 1;
		int sum, idx;
		StringBuilder sb = new StringBuilder();
		int[] nums = new int[2];
		do {
			sum = 0;
			idx = 0;
			for (int i = 0; i < combNumber.length; i++) {
				if(combNumber[i] > 0) {
					nums[idx] = primeList.get(i);
					sum += nums[idx++];
				}
			}
			if(isPrime[sum]) {
				sb.append(nums[0]).append('+').append(nums[1]).append('=').append(sum).append('\n');
			}
		} while(nextPermutation(combNumber));
		
		System.out.print(sb.toString());
		
	}
	
}
