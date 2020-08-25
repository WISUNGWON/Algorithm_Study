import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * nextPermutation으로 조합을 구현하여 1000 이하의 소수들 중 두 개를 골라 합하면 다른 소수가 될 경우를 출력
 */
public class _0825_prime_nextPermutation_jiun {
	static int n = 0, r = 2;
	private static boolean[] prime;

	public static void main(String[] args) {
		prime = new boolean[1001];
		ArrayList<Integer> primelist = new ArrayList<>();
		 
		// 소수 구하기: n의 최대약수는 n의 제곱근이다. 떄문에 루트n이하의 정수로 나누어지는 확인하면 됨.
		for (int i = 2; i <= 1000; i++) {
			if(isPrime(i)) primelist.add(i);//
		}
		for (int i = 0; i < prime.length; i++) {
			if (prime[i]) {
				primelist.add(i); /// 소수 추가
				n++;// 소수 갯수 세어주기
			}
		}
		int[] p = new int[n]; // 소수 조합선택을 위한 배열. 소수 갯수만큼 크기 지정.
		
		int cnt = 0;
		while (++cnt <= r) {// 0. 뒤에서부터 r개 만큼 1을 채운다.
			p[n - cnt] = 1;
		}

		// 1. list 정렬
		Arrays.sort(p);
		int len = 0; 
		do {// prime으로 nextPermutation진행 -> nC2가 완성되었을 때 두 숫자의 합이 두 숫자와 다르면 출력
			int sum = 0, index = 0;
			int[] num = new int[2]; 
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if(p[i] == 1) { // 조합완성
					sum += primelist.get(i);
					num[index++]= primelist.get(i);
				}
			}
			if(isPrime(sum)) {
				System.out.println(num[0] +"+"+num[1]+"="+sum);
				len++;
			}
		} while (nextPermutation(p));
		System.out.println("출력 갯수(합 또한 소수인 결과): "+len+"개");
	}

	private static boolean isPrime(int n) {
		for (int j = 2;j * j <= n; j++) {
			if (n % j == 0) // 제곱근 이하의 숫자로 나누어 떨어지면 소수가 아니다.
				return false;			
		}// end of for
		
		if(n <= 1000) prime[n] = true; // 소수만 true로 변경
		return true;
	}

	/** nextPermutation을 사용해 조합 구하기 */
	private static boolean nextPermutation(int[] arr) {

		// 1. 꼭대기 찾기 (뒤에서 부터 비교하며 작아지는 지점을 찾는다.)
		int i = n - 1;
		while (i > 0 && arr[i] <= arr[i - 1])
			--i; // 부등호 빼보기
		if (i == 0)
			return false;

		// 2. i-1위치와 교환할 다음단계 큰 수 뒤쪽에서 부터 찾기
		int j = n - 1;
		while (arr[j] <= arr[i - 1])
			--j;

		// 3. i-1위치 값과 j 위치값 교환
		int tmp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = tmp;

		// 4. i부터 맨 뒤까지 오름차순 정렬( => 다음 순열)
		int k = n - 1;
		while (k > i) {
			tmp = arr[k];
			arr[k--]=arr[i];
			arr[i++] = tmp;
		}

		return true;
	}
}
