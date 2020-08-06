//package algorithm_study_jiun.dfs;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
////https://www.acmicpc.net/problem/1167
//public class Main_memorysolution {
//	private static List<Node>[] map;
//	private static boolean[][] visit;
//	private static int max = Integer.MIN_VALUE;
//	private static int v;
//	static Node maxNode;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		// --input start--
//		v = sc.nextInt();
//		map = new ArrayList[v + 1];
//		visit = new boolean[v + 1][v + 1];
//		int start, end, val;
//		for (int i = 1; i <= v; i++) { // 간선 정보 입력
//			start = sc.nextInt();
//			map[start] = new ArrayList<>();
//			while (true) {
//				end = sc.nextInt();
//				if (end == -1)
//					break;
//				val = sc.nextInt();
////				System.out.println(start+" "+end+" "+val);
//				map[start].add(new Node(end, val));// 두 정점사이의 거리 입력
//			}
//		} // --input end--
//
//		// 각 정점을 출발점으로 dfs시작
//		for (int i = 1; i < v + 1; i++) {
//			visit = new boolean[v + 1][v + 1];
////			System.out.println(i);
//			dfs(i, 1, 0);
//		}
//
//		System.out.println(max);
//	}
//
//	private static void dfs(Node v, int dist) {
//		visit[v.end] = true;
//
//		for (Node tmp : map[v.end]) {
//			if (!visit[tmp.end])
//				dfs(tmp, dist + tmp.dist);
//		}
//
//		if (max < dist) {
//			maxNode = v;
//			max = dist;
//		}
//		return maxNode;
//	}
//
//	static class Node {
//		int end, val;
//
//		public Node(int end, int val) {
//			this.end = end;
//			this.val = val;
//		}
//
//		@Override
//		public String toString() {
//			return "Node [end=" + end + ", val=" + val + "]";
//		}
//
//	}
//}
