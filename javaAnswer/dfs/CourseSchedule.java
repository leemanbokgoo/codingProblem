package javaAnswer.dfs;
import java.util.*;

// # 39 코스 스케쥴
// 풀이
// 책 풀이 참고해서 해당 코스로 시작했을때 순환하는 부분이 존재하는지 확인하면 됨. 예를 들어 1->2->1 이런식으로 돌아온다면 이건 안됨.
// 주어지는 값을 전부 쓰지않고 풀이 가능한것도 생각해봐야할 부분인듯.
public class CourseSchedule {
	public boolean solution(int n, int[][] course) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int[] pair : course) {
			int x = pair[0];
			int y = pair[1];
			graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
		}

		// 순환하는 걸 확인하려면 현재 경로를 기록해야함.(똑같은 게 또 나오면 중단)
		Set<Integer> traced = new HashSet<>();
		// a -> d , d-> a로 가는 경로는 역순일뿐 같다. a->d가 순환이아니면 d->a도 역순아님.
		// 고로 가지치기 가능
		Set<Integer> visited = new HashSet<>();

		// 내부 dfs 로직 (재귀를 위해 별도 메서드로 분리)
		return solveDfs(graph, traced, visited, course);
	}

	private boolean dfs(int e, Map<Integer, List<Integer>> graph, Set<Integer> traced, Set<Integer> visited) {
		if (traced.contains(e)) {
			return false;
		}

		if (visited.contains(e)) {
			return false;
		}

		// 현재 경로 기록
		traced.add(e);

		// e가 의존하고 있는 코스들(y)을 꺼냄
		if (graph.containsKey(e)) {
			for (int y : graph.get(e)) {
				if (!dfs(y, graph, traced, visited)) {
					return false;
				}
			}
		}

		// 그리고 방문했다는 표시로 현재 코스 visited에 표기
		visited.add(e);
		// traced는 현재 경로를 추적하는 배열임으로 추적이 끝나면 삭제
		traced.remove(e);
		return true;
	}

	private boolean solveDfs(Map<Integer, List<Integer>> graph, Set<Integer> traced, Set<Integer> visited, int[][] course) {
		// course에 있는 요소들 다 list로 만들어줌.
		// 파이썬의 for x in list(course): 부분 재현
		for (int[] pair : course) {
			int x = pair[0];
			// 만약 x를 탐색했는데 false가 리턴된다면 순환 발생
			if (!dfs(x, graph, traced, visited)) {
				return false;
			}
		}
		return true;
	}


	public boolean solutionBook(int n, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();

		for (int[] pair : prerequisites) {
			int x = pair[0];
			int y = pair[1];
			graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
		}

		Set<Integer> traced = new HashSet<>();
		Set<Integer> visited = new HashSet<>();

		// 모든 키에 대해 dfs 수행
		for (int x : graph.keySet()) {
			if (!dfsBook(x, graph, traced, visited)) {
				return false;
			}
		}

		return true;
	}

	private boolean dfsBook(int i, Map<Integer, List<Integer>> graph, Set<Integer> traced, Set<Integer> visited) {
		if (traced.contains(i)) {
			return false;
		}

		if (visited.contains(i)) {
			return true;
		}

		traced.add(i);

		if (graph.containsKey(i)) {
			for (int y : graph.get(i)) {
				if (!dfsBook(y, graph, traced, visited)) {
					return false;
				}
			}
		}

		traced.remove(i);
		visited.add(i);

		return true;
	}

	public static void main(String[] args) {
		CourseSchedule sol = new CourseSchedule();
		int[][] course = {{1, 0}, {0, 1}};
		System.out.println("solution: " + sol.solution(2, course));
		System.out.println("solution_book: " + sol.solutionBook(2, course));
	}
}