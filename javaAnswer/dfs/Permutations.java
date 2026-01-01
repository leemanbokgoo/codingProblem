package javaAnswer.dfs;
import java.util.ArrayList;
import java.util.List;

// 34 ) 순열

public class Permutations {

	// 첫 번째 풀이
	public List<List<Integer>> solution(int[] numbers) {
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> prev_elements = new ArrayList<>();

		// 초기 elements 리스트 생성
		List<Integer> elements = new ArrayList<>();
		for (int num : numbers) {
			elements.add(num);
		}

		dfs1(elements, prev_elements, results);
		return results;
	}

	private void dfs1(List<Integer> elements, List<Integer> prev_elements, List<List<Integer>> results) {
		// 재귀함수로 계속 남은 요소를 넘겨줄 것임으로 종료조건은 더이상 남은 요소가 없을때
		if (elements.size() == 0) {
			// 파이썬의 results.append(prev_elements)는 참조를 저장하므로
			// 자바에서는 새로운 리스트로 복사해서 넣어야 의도한 결과가 나옵니다.
			results.add(new ArrayList<>(prev_elements));
			return;
		}

		// 지금 받아온 숫자들을 차례대로 출력
		for (Integer e : elements) {
			// prev_elements는 답이 될 배열을 저장해두는 변수다.
			prev_elements.add(e);

			// 다음 재귀에 현재 요소를 제외한 수열 배열을 넘겨줘야함.
			List<Integer> next_elements = new ArrayList<>(elements);
			next_elements.remove(e);

			// 재귀 함수 선언
			dfs1(next_elements, prev_elements, results);

			// 방금 위에서 넣어준 현재 요소를 다시 삭제해줘야 다음 재귀에 사용가능.
			// 왜냐하면 [1]이 들어있는 상황에서 [1,2] , [1,3] 이런식으로 들어가야하니까 pop을 통해 2는 뺴고 for문에서 3을 넣어주는 것
			prev_elements.remove(prev_elements.size() - 1);
		}
	}

	// 두 번째 풀이
	public List<List<Integer>> solution2(int[] numbers) {
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> prev_elements = new ArrayList<>();

		List<Integer> elements = new ArrayList<>();
		for (int num : numbers) {
			elements.add(num);
		}

		results = new ArrayList<>(); // 리스트 초기화
		dfs2(elements, prev_elements, results);

		return results;
	}

	private void dfs2(List<Integer> elements, List<Integer> prev_elements, List<List<Integer>> results) {

		if (elements.size() == 0) {
			// results.append(prev_elements[:])와 동일하게 복사본 추가
			results.add(new ArrayList<>(prev_elements));
			return;
		}

		for (Integer number : elements) {
			List<Integer> next_element = new ArrayList<>(elements);
			next_element.remove(number);

			prev_elements.add(number);
			dfs2(next_element, prev_elements, results);
			prev_elements.remove(prev_elements.size() - 1);
		}
	}

	public static void main(String[] args) {
		Permutations sol = new Permutations();

		// 테스트 데이터: [1, 2, 3]
		int[] numbers = {1, 2, 3};

		System.out.println("첫 번째 solution 결과:");
		List<List<Integer>> result1 = sol.solution(numbers);
		System.out.println(result1);

		System.out.println("\n두 번째 solution 결과:");
		List<List<Integer>> result2 = sol.solution2(numbers);
		System.out.println(result2);
	}
}
