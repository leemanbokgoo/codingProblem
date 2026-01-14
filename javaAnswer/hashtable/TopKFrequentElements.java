package javaAnswer.hashtable;
import java.util.*;

/**
 *  31 ) 상위 K 빈도 요소
 */
public class TopKFrequentElements {

	public List<Integer> solution(int[] nums, int k) {
		// 1. 빈도수 계산하기 위한 Map( python 의 Counter 역활)
		Map<Integer, Integer> counter = new HashMap<>();

		// num의 빈도수 계산
		for (int num : nums) {
			counter.put(num, counter.getOrDefault(num, 0) + 1);
		}

		// 2. 우선순위 큐(Heap) 선언
		// (빈도수, 숫자)를 저장하고 빈도수가 높은 순서대로 정렬 (Max-Heap)
		// PriorityQueue 은 기본 MinHeap 이기때문에 최대힙으로 정렬하기위해 람다식으로 정렬 기준 삽입.
		// 밑에처럼 Comparator 를 사용해도 됨. 이 방법이 오버 플로우/언더플로우 버그에서 안정적이고 가독성이 좋다.
		// PriorityQueue<int[]> heap = new PriorityQueue<>(
		// 	Comparator.comparingInt((int[] a) -> a[0]).reversed()
		// );
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

		// 3. 힙에 데이터 삽입
		for (Integer num : counter.keySet()) {
			// int[] 배열 형태로 {빈도수, 숫자} 저장. 배열이 0번 인덱스에는 count(카운트), 1번 인덱스에는 num(실제숫자)가 들어감
			heap.add(new int[]{counter.get(num), num});
		}

		// 4. 상위 k개 추출
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			if (!heap.isEmpty()) {
				result.add(heap.poll()[1]); // item[1]이 실제 숫자임으로 item[1]을 추출
			}
		}

		return result;
	}

	public static void main(String[] args) {
		TopKFrequentElements app = new TopKFrequentElements();
		int[] nums = {1, 1, 1, 2, 2, 3};
		int k = 2;

		System.out.println(app.solution(nums, k)); // [1, 2]
	}
}