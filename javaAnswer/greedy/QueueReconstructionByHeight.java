package javaAnswer.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 *  79 ) 키에 따른 대기열 재구성
 */
public class QueueReconstructionByHeight {

	// 정렬을 통한 풀이
	public int[][] solution(int[][] people) {

		// 키가 큰 사람이 앞에 오도록 정렬. 키가 같으면 k값을 기준으로 오름 차순
		Arrays.sort(people, (a, b) -> {
			if (a[0] == b[0]) {
				// 키가 같으면 k값 오름차순
				return a[1] - b[1];
			}
			// 키가 큰 순서대로 내림차순
			return b[0] - a[0];
		});

		// 리스트에 k 인덱스 위치로 삽입
		// 키가 큰 사람부터 삽입해야 나중에 키작은 사람이 똑같은 위치에 들어와도 기존 사람들의 k값에 영향을 주지않는다.
		List<int[]> result = new ArrayList<>();
		for (int[] p : people) {
			// p[1]이 곧 k 인덱스값이므로, 해당 위치에 삽입하면
			// 리스트가 알아서 뒤로 밀어주며 자리를 잡는다.
			result.add(p[1], p);
		}

		// 3. 리스트를 다시 배열로 변환하여 반환
		return result.toArray(new int[people.length][2]);
	}

	// 우선순위 큐를 사용한 풀이
	public int[][] reconstructQueue(int[][] people) {
		// 1. 우선순위 큐 설정 (비교 규칙은 이전과 동일)
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1]; // 키 같으면 k 오름차순
			}
			return b[0] - a[0];     // 키 내림차순
		});

		// 2. 모든 데이터를 큐에 삽입 (삽입 시마다 내부 정렬 발생)
		for (int[] p : people) {
			pq.add(p);
		}

		// 3. 큐에서 하나씩 꺼내며 리스트의 k번 인덱스에 삽입
		List<int[]> result = new ArrayList<>();
		while (!pq.isEmpty()) {
			int[] p = pq.poll();
			result.add(p[1], p);
		}

		// 4. 결과 반환
		return result.toArray(new int[people.length][2]);
	}

	public static void main(String[] args) {
		int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
	}
}
