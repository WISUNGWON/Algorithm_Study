package combination;

import java.util.ArrayList;

public class Custom_001_Minkyung {

	static int N;
	static int[] comb;
	
	public static void main(String[] args) {
		ArrayList<Integer> primes = new ArrayList<>();
		for (int i = 2; i < 1000; i++) {
			boolean isPrime = true;
			for (int j = 2; j < i; j++) {
				if(i%j==0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) primes.add(i);
		}
		comb = new int[primes.size()];
		N = comb.length;
		comb[N-1] = 1;
		comb[N-2] = 1;
		do {
			int a=0, b=0;
			boolean isSelected = false;
			for (int i = 0; i < N; i++) {
				if(comb[i] == 1) {
					if(!isSelected) {
						a = primes.get(i);
						isSelected = true;
					}
					else b = primes.get(i);
				}
			}
			if(primes.contains(a+b)) System.out.println(a+"+"+b+"="+(a+b));
		} while(np());
	}

	private static boolean np() {
		int i = N-1;
		while(i>0 && comb[i-1]>=comb[i]) i--;
		if(i==0) return false;
		
		int j = N-1;
		while(comb[i-1]>=comb[j]) j--;
		swap(i-1, j);
		
		int k = N-1;
		while(i<k) swap(i++, k--);
		return true;
	}

	private static void swap(int i, int j) {
		int temp = comb[i];
		comb[i] = comb[j];
		comb[j] = temp;
	}
}
