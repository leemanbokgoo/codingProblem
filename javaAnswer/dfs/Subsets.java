package javaAnswer.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 37 ) 부분 집합
 */

public class Subsets {

	public List<List<Integer>> solution(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();

		// 내부 dfs 호출
		dfs(0, new ArrayList<>(), nums, results);

		return results;
	}

	private void dfs(int index, List<Integer> path, int[] nums, List<List<Integer>> results) {

		// 모든 경우를 저장해야함으로 배열이 비어있지만 않는 다면 전부 저장.
		// 부분 집합은 모든 탐색의 경로가 정답이 되기떄문에
		if (!path.isEmpty()) {
			results.add(new ArrayList<>(path));
		}

		// i +1 하는 이유는 index를 1씩 증가하는 형태로 깊이 탐색할 것이기때문.
		for (int i = index; i < nums.length; i++) {
			path.add(nums[i]);
			dfs(i + 1, path, nums, results);
			// 백트래킹
			path.remove(path.size() - 1);
		}
	}

	// 책풀이
	public List<List<Integer>> solution_book(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();

		dfs_book(0, new ArrayList<>(), nums, results);

		return results;
	}

	private void dfs_book(int index, List<Integer> path, int[] nums, List<List<Integer>> results) {

		if (!path.isEmpty()) {
			results.add(new ArrayList<>(path));
		}

		for (int i = index; i < nums.length; i++) {
			path.add(nums[i]);
			dfs_book(i + 1, path, nums, results);
			path.remove(path.size() - 1);
		}
	}

	public static void main(String[] args) {
		Subsets sol = new Subsets();

		// 테스트 데이터: [1, 2, 3]
		int[] nums = {1, 2, 3};

		System.out.println("solution 결과: " + sol.solution(nums));
		System.out.println("solution_book 결과: " + sol.solution_book(nums));
	}
}
