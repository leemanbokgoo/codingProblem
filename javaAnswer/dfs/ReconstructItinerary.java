package javaAnswer.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 38) 일정 재구성

// 만약 문제에 주어진 모든 항공권을 사용해야한다는 조건이 있으면 오일러 경로를 찾으라는 조건이다.
// 오일러 경로는 후위 순회로 구현가능. 현재 문제에서는 그런말이 없긴함.

public class ReconstructItinerary {

	public List<Object> solution(String[][] tickets) {

		List<Object> route = new ArrayList<>();
		Map<String, List<String>> graph = new HashMap<>();

		// 사전 순서대로 구성해야함으로 sorted를 이용해 정렬
		// 자바에서는 배열을 먼저 정렬한 뒤 그래프를 구성합니다.
		Arrays.sort(tickets, (a, b) -> {
			if (a[0].equals(b[0])) return a[1].compareTo(b[1]);
			return a[0].compareTo(b[0]);
		});

		for (String[] ticket : tickets) {
			graph.computeIfAbsent(ticket[0], k -> new ArrayList<>()).add(ticket[1]);
		}

		// 내부 dfs 호출 (자바 메서드로 구현)
		dfs1("JFK", graph, route);

		// return route[::-1] 구현
		Collections.reverse(route);
		return route;
	}

	private void dfs1(String a, Map<String, List<String>> graph, List<Object> route) {
		// 오일러 문제 임으로 모든 항공권을 사용해야한다. 그래서 while문을 통해 a에서 출발하는 남아있는 모든 티켓을 소진해야함.
		// JFK에서 시작했고, 만약 JFK : b가 여러개라면 첫번째 루프에서 b[0]의 간선을 모두 조사하고 다시 JFK로 돌아와서 b[1]의 경로를 찾을 것이다.
		while (graph.containsKey(a) && !graph.get(a).isEmpty()) {
			dfs1(graph.get(a).remove(0), graph, route);
		}
		// 원본 코드의 route.append(graph[a])를 그대로 따름
		// graph[a]가 없으면 빈 리스트를 추가하도록 처리
		route.add(graph.getOrDefault(a, new ArrayList<>()));
	}

	// 책 풀이
	public List<String> solution_book(String[][] tickets) {

		Map<String, List<String>> graph = new HashMap<>();

		// 정렬 후 그래프 구성
		Arrays.sort(tickets, (a, b) -> {
			if (a[0].equals(b[0])) return a[1].compareTo(b[1]);
			return a[0].compareTo(b[0]);
		});

		for (String[] ticket : tickets) {
			graph.computeIfAbsent(ticket[0], k -> new ArrayList<>()).add(ticket[1]);
		}

		List<String> route = new ArrayList<>();

		dfs_book("JFK", graph, route);

		Collections.reverse(route);
		return route;
	}

	private void dfs_book(String a, Map<String, List<String>> graph, List<String> route) {
		// 현재 노드 a에서 더이상 갈곳이 없을때까지 재귀 호출을 반복
		while (graph.containsKey(a) && !graph.get(a).isEmpty()) {
			dfs_book(graph.get(a).remove(0), graph, route);
		}
		route.add(a);
	}

	public static void main(String[] args) {
		ReconstructItinerary sol = new ReconstructItinerary();

		// 테스트 데이터: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
		String[][] tickets = {
			{"JFK", "MUC"},
			{"MUC", "LHR"},
			{"LHR", "SFO"},
			{"SFO", "SJC"}
		};

		System.out.println("solution 결과: " + sol.solution(tickets));
		System.out.println("solution_book 결과: " + sol.solution_book(tickets));
	}
}
