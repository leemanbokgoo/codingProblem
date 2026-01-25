package javaAnswer.dijkstra_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 41 ) K 경유지 내 가장 저렴한 항공권
 *
 * 가격을 시간으로 가정한다면 최단 시간을 계산하는 경로는 앞서 다익스트라 알고리즘으로 동일하게 구현할 수 있다.
 * 다만 여기에는 한가지 제약 사항이 추가되었는데 K개의 경유지 이내에 도착해야한다는 점이다.
 * 따라서 다익스트라 알고리즘 구현을 위해 우선순위 큐에 추가할때 K이내 일때만 경로를 추가하여 K를 넘어서는 경로는 더이상 탐색되지않게 한다.
 */

public class CheapestFlightsWithinKStops {

	// 거리를 배열로 저장했지만 횟수를 저장해야함. dist 가 이나리 count
	public int solution(int n, int src, int dst, int k, int[][] edges) {

		// 그래프
		Map<Integer, List<int[]>> graph = new HashMap<>();
		for (int[] node : edges){
			graph.computeIfAbsent(node[0], x -> new ArrayList<>()).add(new int[]{node[1], node[2]});
		}
		// count 배열
		int[] count = new int[n];
		Arrays.fill(count, Integer.MAX_VALUE);
		count[src] = 0;

		// 우선순위
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[]{src, 0 , k + 1 }); // 우선 순위에 노드, 가격, 이동 횟수를 넣어야함. 이동횟수 + 1 해야 k 경유지 이내에 움직일 수 있음.

		while ( !pq.isEmpty() ){

			int[] currentNode = pq.poll();
			int node = currentNode[0]; // 현재 노드
			int price = currentNode[1]; // 현재 노드의 가격
			int restCount = currentNode[2]; // 현재 남은 경우 횟수

			// 종료 조건?
			// 현재 노드가 도착지와 같다면 도착지에 도착했다는 뜻임으로 경로를 탐색하며 최종적으로 더해온 price 반환.
			// 우선순위 큐임으로 k번째 이내에 가장 먼저 도착한 경로가 가장 적은 가격으로 오는 경로다.
			if ( node == dst ) return price;

			// 아직 남은 경우 횟수가 있다면 이웃 노드 꺼내서 탐색 시작
			if (restCount > 0 && graph.containsKey(node)){
				for ( int[] neighborNode : graph.get(node)){
					int nextNode = neighborNode[0];
					int nextPrice = price + neighborNode[1];

					// nextNode까지 온 현재 경로의 경유 횟수가 count에 기록되어있는 경유 횟수보다 작아야함.
					// 여기서 count에 저장되어있는 경우 횟수는 nextNode 까지 도착한 다른 경로의 경유 횟수다.(혹은 초기화값)
					if( restCount - 1 < count[nextNode] ){
						count[nextNode] = restCount - 1; // 경유 했으니까 -1 해서 경유 횟수 차감.
						pq.offer(new int[]{nextNode, nextPrice, restCount -1 });
					}
				}
			}

		}

		return -1;
	}


	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		// 1. 그래프 구성 (출발 노드 -> {도착 노드, 비용})
		Map<Integer, List<int[]>> adj = new HashMap<>();
		for (int[] f : flights) {
			adj.computeIfAbsent(f[0], x -> new ArrayList<>()).add(new int[]{f[1], f[2]});
		}

		// 2. 방문 배열: stops[i]는 i번 노드에 도달했을 때의 '최소 경유 횟수'를 저장
		// 단순히 비용만 체크하면 안 되고, 더 적은 경유지로 도착하는 경우를 허용해야 함
		int[] stops = new int[n];
		Arrays.fill(stops, Integer.MAX_VALUE);

		// 3. 우선순위 큐: {비용, 현재 노드, 남은 경유 가능 횟수}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		pq.offer(new int[]{src, 0, k + 1});

		// 우선 순위 큐임으로 가장 싼 경로부터 탐색을 시작함.
		while (!pq.isEmpty()) {
			int[] temp = pq.poll();
			int price = temp[0];
			int curr = temp[1];
			int remainStops = temp[2];

			// 목적지 도착 시 최저가 반환 ( 우선순위 큐이므로 처음 도착한 경로가 최저가임을 보장함)
			if (curr == dst) return price;

			// 남은 이동 횟수가 있다면 다음 탐색
			if (remainStops > 0) {
				if (adj.containsKey(curr)) {
					for (int[] next : adj.get(curr)) {
						int nextNode = next[0];
						int nextPrice = price + next[1];

						// 최적화: 이전에 이 노드에 왔을 때보다 경유 횟수가 더 적다면 탐색 진행
						if (remainStops - 1 < stops[nextNode]) {
							stops[nextNode] = remainStops - 1;
							pq.offer(new int[]{nextPrice, nextNode, remainStops - 1});
						}
					}
				}
			}
		}

		return -1;
	}


	public static void main(String[] args){

		int n = 3; // 노드 갯수
		int src = 0; // 시작 노드
		int dst = 2; // 도착 노드
		int k = 0; // 허용 경유지 갯수
		int[][] edges = {
			{0, 1, 100},
			{1, 2, 100},
			{0, 2, 500}
		};

		CheapestFlightsWithinKStops app = new CheapestFlightsWithinKStops();
		System.out.println("나의 풀이 " + app.solution(n , src, dst, k , edges) );

	}
}
