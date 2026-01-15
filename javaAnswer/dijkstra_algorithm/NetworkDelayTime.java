package javaAnswer.dijkstra_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 40 ) 네트워크 딜레이 타임
 * 이 문제는 1. 모든 노드가 신호를 받는 데 걸리는 시간 2. 모든 노드에 도달 할 수 있는 지 여부.를 판별해야한다.
 * 첫번쨰, 모든 노드가 신호를 받는 데 걸리는 시간이란 가장 멀리 있는 노드까지 가는 데 걸리는 시간이라고 할 수 있다. 즉, 가장 멀리 있는 노드까지 가는데 걸리는 최단 시간을 말한다.
 * 		각 노드까지 신호가 도착하는 최소 시간들을 모두 구한 후 , 그중에서 가장 큰 값을 찾는다.
 * 두번째, 모든 노드에 도달할 수 있는 지 여부는 모든 노드의 다익스트라 알고리즘 계산값이 존재하는 유무로 판별할 수 있다.
 * 		만약 노드가 8개인데 다익스트라 알고리즘 계산은 7개밖에 할 수 없다면 나머지 한 노드는 도달할 수 없다는 의미다. 이 경우 -1을 리턴한다.
 *
 */

public class NetworkDelayTime {


	public int solution( int[][] times , int n , int k ){

		// 배열
		Map<Integer, List<int[]>> graph = new HashMap<>();
		for( int[] time : times){
			graph.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
		}

		// dist 가중치 기록 배열
		int[] dist = new int[ n + 1 ]; // 1번으로 시작하니까 + 1
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[k] = 0; // 출발지 미리 셋팅

		// 우선 순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[]{k , 0}); // 출발지 셋팅


		// 우선순위 큐에 값이 존재하는 동안
		while( !pq.isEmpty()){
			// 현재 우선순위가 가장 높은 노드 꺼냄
			int[] currentNode = pq.poll();
			int node = currentNode[0];
			int weight = currentNode[1];

			// 가지치기
			// 현재 가중치가 dist 에 기록된 가중치보다 크다면 최단 경로가 아니기때문에 탐색할 필요가 없음.
			if( weight > dist[node] ) continue;

			// 이웃 노드가 있다면 탐색
			if (graph.containsKey(node)){
				for ( int[] edges : graph.get(node) ){
					int nextNode = edges[0];
					int nextWeight = weight + edges[1];

					// 현재 가중치가 dist에 기록된 가중치보다 작다면 현재 경로를 새로운 최단 거리 경로로 갱신
					if ( nextWeight < dist[nextNode] ){
						dist[nextNode] = nextWeight;
						pq.offer(new int[]{nextNode, nextWeight});
					}
				}
			}
		}

		int maxDelay = 0;
		for( int i = 1 ; i < n; i++){
			// dist에 Integer.MAX_VALUE값이 존재한다면 모든 노드의 경로를 탐색하지않았다는 뜻.
			if ( dist[i] == Integer.MAX_VALUE ) return -1;
			maxDelay = Math.max(maxDelay, dist[i]);
		}

		return maxDelay;
	}




	// 자바 다익스트라(배열 방식) 코드
	// 보편적으로 책의 Map 방식은 내부적으로 해시 연산을 거쳐야 하지만, dist[v]는 메모리 주소로 바로 접근함으로 더 빠르다.
	// 다만 노드 번호가 1, 10억, 20억 이런 식으로 띄엄띄엄 있으면 배열 크기를 너무 크게 잡아야 해서 메모리 낭비가 심해진다.
	public int solution_answer(int[][] times, int n, int k) {

		// 1. 인접 리스트 구성 (그래프 생성)
		Map<Integer, List<int[]>> graph = new HashMap<>();

		for (int[] time : times) {
			graph.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
		}

		// 2. 거리 배열 초기화 (무한대로 설정)
		// dist : index가 노드 번호가 되고 value값이 해당 노드까지 가는 최단 거리를 뜻한다. 시작노드 k에서 해당 index(노드)까지 걸리는 최단 거리를 기록한다.
		int[] dist = new int[n + 1]; // 노드 번호가 0이 아니라 1부터 시작하기때문에.
		Arrays.fill(dist, Integer.MAX_VALUE); // 무한대로 배열을 채워서 초기화.
		dist[k] = 0; // 시작 노드 설정

		// 3. 우선순위 큐 설정 (소요 시간이 짧은 순서대로 정렬)
		// int[] 구성: {현재 노드, 소요 시간}
		// PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[]{k, 0}); // 시작 노드와 가중치를 큐에 삽입

		// 큐가 빌때까지 돌아가야함. 큐가 있다는 건 아직 갈 수 있는 노드가 남아있다는 뜻임으로
		while (!pq.isEmpty()) {
			int[] current = pq.poll(); // 우선 순위가 가장 높은 노드를 꺼냄(참고로 최소 힙)
			int currNode = current[0]; // 노드
			int currWeight = current[1]; // 가중치

			// currWeight(현재 최단거리)가 이미 처리된 경로dist[currNode] 보다 더 길다면 무시
			if (currWeight > dist[currNode]) continue;

			// 이웃노드가 존재한다면 주변 노드 탐색
			if (graph.containsKey(currNode)) {

				// 현재 노드의 이웃 노드의 간선들을 꺼내 봄.(이웃노드를 찾는 다는 말임)
				for (int[] edge : graph.get(currNode)) {
					int nextNode = edge[0]; // 노드 번호
					int nextWeight = currWeight + edge[1]; // 현재 노드의 가중치 + 이웃 노드의 가중치

					// 더 짧은 경로를 발견하면 업데이트 후 큐에 삽입
					// dist[nextNode] : 시작 노드(k)에서 nextNode까지 가는 데 발견된 현재까지의 최단 거리
					// 현재 노드에서 nextNode로 가는 가중치가 최단 거리(가중치)인 dist[nextNode]보다 작다면 새로운 최단 경로가 생긴 것.
					if (nextWeight < dist[nextNode]) {
						dist[nextNode] = nextWeight;
						pq.offer(new int[]{nextNode, nextWeight});
					}
				}
			}
		}

		// 4. 결과 도출
		int maxDelay = 0;
		for (int i = 1; i <= n; i++) {
			if (dist[i] == Integer.MAX_VALUE) return -1; // 도달 불가능한 노드가 있음
			maxDelay = Math.max(maxDelay, dist[i]);
		}

		return maxDelay;
	}


	// 책 풀이
	// 파이썬을 변환한 풀이라 HashMap 방식을 사용. 노드 번호가 엄청나게 크거나 불규칙할 때 혹은 노드가 숫자가 아니라 문자열일때.
	// 배열보다 연산 속도가 느린 편
	public int networkDelayTime(int[][] times, int n, int k) {
		// 1. 그래프 구성 (Python의 defaultdict(list) 역할)
		Map<Integer, List<int[]>> graph = new HashMap<>();
		for (int[] edge : times) {
			// edge[0]: 출발, edge[1]: 도착, edge[2]: 가중치
			graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
		}

		// 2. 우선순위 큐 설정 (Python의 Q 역할)
		// [소요시간, 노드번호] 저장, 시간을 기준으로 오름차순 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
		pq.offer(new int[]{0, k});

		// 3. 최단 거리 테이블 (Python의 dist = defaultdict(int) 역할)
		// 자바에서는 Map을 써서 방문 여부와 최단 시간을 동시에 관리합니다.
		Map<Integer, Integer> dist = new HashMap<>();

		// 4. 다익스트라 탐색 시작
		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int time = current[0];
			int node = current[1];

			// 파이썬의 "if node not in dist:" 부분
			// 이미 dist에 들어있다면, 지금 꺼낸 것은 더 긴 경로로 온 것이므로 무시합니다.
			if (!dist.containsKey(node)) {
				dist.put(node, time); // 최단 시간 확정 기록

				// 이웃 노드 탐색
				if (graph.containsKey(node)) {
					for (int[] neighbor : graph.get(node)) {
						int v = neighbor[0];
						int w = neighbor[1];

						// alt = 현재까지 시간 + 이웃까지 가는 시간
						int alt = time + w;
						pq.offer(new int[]{alt, v});
					}
				}
			}
		}

		// 5. 결과 반환 (모든 노드 방문 여부 확인)
		if (dist.size() == n) {
			// 모든 노드 중 가장 오래 걸린 시간 반환
			return Collections.max(dist.values());
		}

		return -1; // 도달하지 못한 노드가 있는 경우
	}

	public static void main(String[] args){

		int[][] times = {
			{2, 1, 1},
			{2, 3, 1},
			{3, 4, 1}
		};
		int n = 4;
		int k = 2;

		NetworkDelayTime networkDelayTime = new NetworkDelayTime();
		System.out.println("NetworkDelayTime solution " + networkDelayTime.solution( times, n , k));
		System.out.println("NetworkDelayTime solution_book " + networkDelayTime.solution( times, n , k));
	}

}
