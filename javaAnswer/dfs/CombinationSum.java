package javaAnswer.dfs;
import java.util.ArrayList;
import java.util.List;

// 36 ) 조합의 합
// target값이 되도록 더하는 것보다 target값에 빼는 방식을 고려해봐야함.
public class CombinationSum {
	public List<List<Integer>> solution(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<>();

		// 내부 dfs 함수 호출
		dfs_original(target, 0, new ArrayList<>(), candidates, results, target);

		return results;
	}

	private void dfs_original(int csum, int index, List<Integer> path, int[] candidates, List<List<Integer>> results, int target) {
		// 종료 조건
		if (csum == 0) {
			results.add(path);
			return;
		}
		// cusm은 target보다 클수가 없음. 왜냐하면 밑에서 csum - candidates[i]해서 계속 값을 빼고있기때문에 csum이 target보다 크다면 바로 종료 필요
		if (csum > target) {
			return;
		}
		// 자바에서 무한 루프(StackOverflow)를 방지하기 위해 음수 체크만 추가.
		if (csum < 0) {
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			// path + [candidates[i]]를 그대로 재현하기 위해 매번 새 리스트 생성
			List<Integer> nextPath = new ArrayList<>(path);
			nextPath.add(candidates[i]);
			dfs_original(csum - candidates[i], i, nextPath, candidates, results, target);
		}
	}

	// 책풀이
	public List<List<Integer>> solution_book(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<>();

		dfs_book(target, 0, new ArrayList<>(), candidates, results, target);

		return results;
	}

	private void dfs_book(int csum, int index, List<Integer> path, int[] candidates, List<List<Integer>> results, int target) {
		if (csum == 0) {
			results.add(path);
			return;
		}

		if (csum > target) {
			return;
		}

		if (csum < 0) {
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			List<Integer> nextPath = new ArrayList<>(path);
			nextPath.add(candidates[i]);
			dfs_book(csum - candidates[i], i, nextPath, candidates, results, target);
		}
	}

	public static void main(String[] args) {
		CombinationSum sol = new CombinationSum();
		int[] candidates = {2, 3, 6, 7};
		int target = 7;

		System.out.println("solution 결과: " + sol.solution(candidates, target));
		System.out.println("solution_book 결과: " + sol.solution_book(candidates, target));
	}
}