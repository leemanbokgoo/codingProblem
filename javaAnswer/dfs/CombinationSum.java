package javaAnswer.dfs;
import java.util.ArrayList;
import java.util.List;

/**
 *  36 ) 조합의 합
 *  target값이 되도록 더하는 것보다 target값에 빼는 방식을 고려해봐야함.
 */
public class CombinationSum {
	public List<List<Integer>> solution(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<>();
		dfs_original(target, 0, new ArrayList<>(), candidates, results);
		return results;
	}

	private void dfs_original(int csum, int index, List<Integer> path, int[] candidates, List<List<Integer>> results) {

		// 종료 조건
		// cusm에다가 계속 값을 빼고 있기때문에 cusm이 0이 된다면 목표 합계에 도달했다는 뜻임으로
		if (csum == 0) {
			results.add(new ArrayList<>(path));
			return;
		}

		// 가지치기
		// 이미 목표 금액을 초과했을 때 (음수가 됐을 때)
		if (csum < 0) {
			return;
		}

		// i = index : 이미 계산한 숫자는 처리 되지않도록 index부터 시작하는 것.
		for (int i = index; i < candidates.length; i++) {
			// 현재 i 를 추가
			path.add(candidates[i]);
			// i를 넘겨줌으로써 '중복 조합'(같은 숫자 여러 번 사용) 허용
			// csum - candidates[i] 함으로 csum에서 현재 값을 빼면서 최종 합계를 찾아는 것.
			dfs_original(csum - candidates[i], i, path, candidates, results);

			// 백트래킹
			path.remove(path.size() - 1); // 돌아와서 가장 마지막(방금 추가한) 요소 제거
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