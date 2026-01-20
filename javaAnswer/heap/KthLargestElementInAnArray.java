package javaAnswer.heap;

import java.util.PriorityQueue;

/**
 * 55 ) 배열의 K번째 큰 요소
 */
public class KthLargestElementInAnArray {

	public int solution( int[] arr, int k){

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < arr.length; i++) {
			if ( pq.size() >= k ){
				pq.poll();
			}
			pq.offer(arr[i]);
		}
		return pq.peek();
	}

	// 책 풀이
	public int solution_book(int[] nums, int k) {
		// 최소 힙 생성 (기본값)
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int num : nums) {
			pq.offer(num);

			// 힙 크기가 k를 초과하면 가장 작은 값(현재 힙의 최솟값)을 탈락시킴
			if (pq.size() > k) {
				pq.poll();
			}
		}

		// 마지막에 남은 k개 중 가장 작은 것이 전체에서 k번째로 큰 수
		return pq.peek();
	}
	public static void main(String[] args) {

		int[] arr = { 3,2,3,1,2,4,5,5,6};
		int k = 4;

		KthLargestElementInAnArray app = new KthLargestElementInAnArray();
		System.out.println(app.solution(arr, k));
	}
}
