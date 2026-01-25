package javaAnswer.sort;

import java.util.PriorityQueue;

/**
 *  64 ) 원점에 K 번째로 가까운 점
 *
 */
public class KClosestPointsToOrigin {

	public int[][] solution(int[][] points, int k) {

		// 1. 최대 힙(Max Heap) 생성: 거리가 먼 순서대로 정렬되도록 설정
		// (b[0]^2 + b[1]^2) - (a[0]^2 + a[1]^2) -> 내림차순
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
			// [b의 원점 거리] - [a의 원점 거리]
			// 유클리드 거리 공식에서 루트만 뺀 값
			// 두 점의 거리를 비교해서 누가 더 먼저인지 순서를 정한다. a와 b중 거리가 더 먼 쪽을 큐의 맨 위로 올린다.
			// 여기서 a도 b도 [x,y]이다. a는 x , b는 y가 아님.
			// 참고로 a - b를 비교하면 a가 더 클 경우 뒤로 보낸다. 즉, 작은게 앞으로 오는데 여기는 최대 힙임으로 큰 값이 앞에 와야하기때문에 기존의 공식을 뒤집어서 b - a를 사용함.
			(b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
		);

		for (int[] point : points) {
			pq.offer(point);
			// 2. 큐의 크기가 k를 넘어가면, 가장 거리가 먼 점(루트)을 제거
			// 게속 거리가 먼 점(루트)를 제거하다보면 마지막에 큐 안에 살아남은 k개는 결국 가장 가까운 점들이 된다.
			if (pq.size() > k) {
				pq.poll();
			}
		}

		// 3. 큐에 남은 k개의 점을 결과 배열로 변환
		// [k] : 몇개의 점(좌표)를 담을 것인지 (행의 개수)
		// [2] : 2차원 배열을 만드는 선언 , 하나의 좌표는 몇개의 숫자로 이루어져있는지 (열의 개수)
		int[][] result = new int[k][2];
		while (k > 0) {
			// --k : k - 1 하는 것으로 k가 2개라면 인덱스는 0,1 이어야하기때문.
			// 최대 힙을 사용했기때문에 poll() 하면 가장 먼 거리부터 나온다. 그래서 큰 값이 뒤로 가도록 뒤쪽부터 채우는 것.
			result[--k] = pq.poll();
		}

		return result;
	}
}
