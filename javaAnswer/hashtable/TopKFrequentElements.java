package javaAnswer.hashtable;
import java.util.*;
//  31 ) 상위 K 빈도 요소
public class TopKFrequentElements {

	public List<Integer> solution(int[] nums, int k) {
		// 1. 빈도수 계산 (Python의 Counter 역할)
		Map<Integer, Integer> counter = new HashMap<>();
		for (int num : nums) {
			counter.put(num, counter.getOrDefault(num, 0) + 1);
		}

		// 2. 우선순위 큐(Heap) 선언
		// (빈도수, 숫자)를 저장하고 빈도수가 높은 순서대로 정렬 (Max-Heap)
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

		// 3. 힙에 데이터 삽입
		for (Integer num : counter.keySet()) {
			// int[] 배열 형태로 {빈도수, 숫자} 저장
			heap.add(new int[]{counter.get(num), num});
		}

		// 4. 상위 k개 추출
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			if (!heap.isEmpty()) {
				result.add(heap.poll()[1]); // item[1]에 해당하는 숫자 추출
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