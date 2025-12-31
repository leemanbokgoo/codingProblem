package javaAnswer.dfs;

import java.util.ArrayList;
import java.util.List;

public class Combination {

	// 35 ) 조합
	// n 전체수 k 개의 조합
	public List<List<Integer>> solution(int n, int k) {

		List<List<Integer>> results = new ArrayList<>();

		// 파이썬의 dfs([], 1, k) 호출을 그대로 구현
		dfs(new ArrayList<>(), 1, k, n, results);
		return results;
	}

	private void dfs(List<Integer> elements, int start, int k, int n, List<List<Integer>> results) {

		if (k == 0) {
			// results.append(elements[:])와 동일하게 리스트 복사하여 추가
			results.add(new ArrayList<>(elements));
			return;
		}

		for (int i = start; i <= n; i++) {
			elements.add(i);
			// i + 1 해야하는 이유는 중복을 허용하지않음으로 다음 index의 요소를 선택해야하기때문.
			// k -1 : 는 k 만큼의 배열을 만들어야하는데 현재 i를 더해서 자리 하나가 찼으니까 -1
			// elements -> 현재까지 들어온 깊이만큼의 요소를 더한 배열
			dfs(elements, i + 1, k - 1, n, results);
			// 재귀함수가 끝났으면 pop해서 요소를 지움 즉,다시 되돌아오는 것
			elements.remove(elements.size() - 1);
		}
	}

	public List<List<Integer>> solution2(int n, int k) {

		List<List<Integer>> results = new ArrayList<>();

		// 파이썬의 dfs([], 1, k) 호출을 그대로 구현
		dfs2(new ArrayList<>(), 1, k, n, results);
		return results;
	}

	private void dfs2(List<Integer> elements, int start, int k, int n, List<List<Integer>> results) {
		// 종료 조건
		if (k == 0) {
			// results.append(elements[:])와 동일하게 리스트 복사하여 추가
			results.add(new ArrayList<>(elements));
			return;
		}

		for (int i = start; i <= n; i++) {
			elements.add(i);
			dfs2(elements, i + 1, k - 1, n, results);
			elements.remove(elements.size() - 1);
		}
	}

	public static void main(String[] args) {
		Combination sol = new Combination();
		System.out.println("solution: " + sol.solution(4, 2));
		System.out.println("solution2: " + sol.solution2(4, 2));
	}
}