package javaAnswer.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *  80 ) 테스크 스케쥴러
 *  리트코드 621
 */
public class TaskScheduler {
	public int solution( char[] tasks, int n ) {
		Map<Character, Integer> counts = new HashMap<>();
		for (char t : tasks) {
			counts.put(t, counts.getOrDefault(t, 0) + 1);
		}

		// 2. 빈도수를 내림차순으로 관리할 우선순위 큐
		// Collections.reverseOrder() : 설정하면 최대 힙이 됨.
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		// 위에서 만든 counts 의 value 값만 우선 순위 큐에 넣는다.
		pq.addAll(counts.values());

		int totalTime = 0;

		// 3. 큐가 빌 때까지 반복
		while (!pq.isEmpty()) {
			// 한번의 작업 사이클 동안 수행된 작업들을 저장할 배열
			List<Integer> temp = new ArrayList<>();
			int cycle = n + 1; // 한 사이클 (작업 + 쿨타임)

			// 한 사이클 동안 최대한 많은 작업을 수행
			for (int i = 0; i < cycle; i++) {
				if (!pq.isEmpty()) {
					// temp에 들어간다는 건 작업이 하나 수행되었다는 뜻임으로 꺼낸 횟수(pq.poll() - 1)에 -1한다.
					temp.add(pq.poll() - 1);
				}
			}

			// 수행 후 남은 작업들을 다시 큐에 삽입
			for (int remaining : temp) {
				if (remaining > 0) pq.add(remaining);
			}

			// 큐가 비었다면 마지막 작업들이므로 실제 수행한 작업 수만큼만 더함
			// 큐에 작업이 남았다면 쿨타임을 포함한 한 사이클 전체를 더함
			totalTime += pq.isEmpty() ? temp.size() : cycle;
		}

		return totalTime;
	}
}
