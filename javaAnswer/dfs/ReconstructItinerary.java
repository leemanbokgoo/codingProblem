package javaAnswer.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 38) 일정 재구성
 * 만약 문제에 주어진 모든 항공권을 사용해야한다는 조건이 있으면 오일러 경로를 찾으라는 조건이다.
 * 오일러 경로는 후위 순회로 구현가능. 현재 문제에서는 그런말이 없긴함.
 *
 */

public class ReconstructItinerary {

	public List<Object> solution(String[][] tickets) {

		List<Object> route = new ArrayList<>();
		Map<String, List<String>> graph = new HashMap<>();

		// 중복이 있는 경우 사전 순서대로 방문해야함으로 sorted 를 이용해 정렬


		Arrays.sort(tickets, (a, b) -> {
			// 0번째 인덱스는 출발지, 1번째 인덱스는 도착지
			// 만약 출발지[0]가 같다면 도착지를 사전순으로 비교 [1]
			if (a[0].equals(b[0])) return a[1].compareTo(b[1]);

			// 출발지를 사전순으로 비교
			return a[0].compareTo(b[0]);
		});

		// 이렇게도 사용가능 더 현재적인 방법
		// Arrays.sort(tickets, Comparator.comparing((String[] a) -> a[0]) // 1순위: 출발지
		//                                .thenComparing(a -> a[1]));

		// 정렬한 값들을 이제 Map의 형태로 변환함.
		// JFK 로 가는 도착지가 두개라면 { "JFK" : ["SFO","ATL"]} 이렇게 만들기 위함이다.
		for (String[] ticket : tickets) {
			// computeIfAbsent : 키가 없으면 해당 키와 value 생성하여 반환 키가 있다면 그냥 데이터 반환.
			// ( ticket[0] : 해당 키 값값이 없으면 뒤에 있는 람다식 실행.
			// k -> new ArrayList<>(): 만약 Map에 이 출발지가 아직 등록되지 않았다면, 새로운 ArrayList를 만들어서 넣어준다. (여기서 k는 ticket[0] 값을 의미하지만 보통 쓰이지 않아 _나 k로 둔다.)
			// .add(ticket[1]): computeIfAbsent는 해당 키에 연결된 리스트(Value)를 반환한다. 새로 만들었든 이미 있었든 그 리스트를 돌려주기 때문에, 바로 뒤에 .add()를 붙여서 도착지를 추가할 수 있다.
			graph.computeIfAbsent(ticket[0], k -> new ArrayList<>()).add(ticket[1]);
		}

		dfs1("JFK", graph, route);

		Collections.reverse(route);
		return route;
	}

	private void dfs1(String a, Map<String, List<String>> graph, List<Object> route) {

		// 오일러 문제 임으로 모든 항공권을 사용해야한다. 그래서 while문을 통해 a에서 출발하는 남아있는 모든 티켓을 소진해야함.
		// JFK에서 시작했고, 만약 JFK : b가 여러개라면 첫번째 루프에서 b[0]의 간선을 모두 조사하고 다시 JFK로 돌아와서 b[1]의 경로를 찾을 것이다.
		while (graph.containsKey(a) && !graph.get(a).isEmpty()) {
			// remove : 배열의 0번째 인덱스(가장 앞에 있는 데이터)를 삭제 하면서 그 값을 가져오는 return 역할을 한다.
			//          0번째를 삭제 하는 이유는 알파벳 순으로 방문하기 위함이며 이미 방문한 간선을 다시 방문할 필요가 없기때문에 remove를 통해 방문처리 하는 것.
			//          고로 graph.get(a).remove(0)하면 도착지 중 가장 앞에 있는 데이터가 삭제되면서 동시에 반환된다.
			dfs1(graph.get(a).remove(0), graph, route);
		}

		// graph[a]가 없으면 빈 리스트를 추가하도록 처리
		route.add(a);
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
