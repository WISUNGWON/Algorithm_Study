package algorithm_study_jiun.bfs;

import java.util.Arrays;

/* 서로소 집합 - 그룹을 나눌때 유용한 방법*/

public class DisjointSetTest {
	private static int n;
	private static int[] parents;

	public static void main(String[] args) {
		n = 5;// 정점갯수
		parents = new int[n];

		// makeSet
		for (int i = 0; i < n; i++) {
			makeSet(i);
		}
//		System.out.println(Arrays.toString(parents));
//		union(0,1);
//		System.out.println(Arrays.toString(parents));
//		union(3,4);
//		System.out.println(Arrays.toString(parents));
//		union(0,2);
//		System.out.println(Arrays.toString(parents));
//		union(0,4);
//		System.out.println(Arrays.toString(parents));
		System.out.println(Arrays.toString(parents));
		union(0,1);
		System.out.println(Arrays.toString(parents));
		union(2,1);
		System.out.println(Arrays.toString(parents));
		union(3,2);
		System.out.println(Arrays.toString(parents));
		union(4,3);
		System.out.println(Arrays.toString(parents));
		System.out.println(findSet(1));
	}// end of main

	/** 두 집합을 합치기 */
	public static boolean union(int a, int b) {
		System.out.println(a+","+b+" 합침");
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) {// 같은그룹이ㅣ면
			return false;
		}
		parents[bRoot] = aRoot;// 두 그룹을 합치기
		return true;
	}

	/** 대표자 찾기 */
	public static int findSet(int a) {
//		if (parents[a] == a) { // 내가 대표자라면
//			return a;
//		} else {
//			return findSet(parents[a]);
//		}
		if(parents[a] != a) {
			parents[a] = findSet(parents[a]);
		}
		return parents[a];
	}

	public static void makeSet(int a) {
		parents[a] = a;// 대표자를 표시

	}
}
