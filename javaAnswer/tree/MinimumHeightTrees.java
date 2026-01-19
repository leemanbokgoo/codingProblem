package javaAnswer.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  49 ) 최소 높이 트리
 *
 */

public class MinimumHeightTrees {

	public List<Integer> solution(int n , List<int[]> edges ) {

		// 예외 처리: 노드가 1개인 경우 바로 반환
		if (n == 1) return Collections.singletonList(0);

		// 1. 그래프(인접 리스트) 및 차수(Degree) 초기화
		// 노드 : { 연결 노드 } 이런식으로 저장. set으로 중복 노드 제거
		List<Set<Integer>> adj = new ArrayList<>();
		// 위에 만든 adj에 노드 값부터 셋팅.
		for (int i = 0; i < n; i++) adj.add(new HashSet<>());

		// 해당 노드에 연결되어있는 노드들의 갯수 (즉, 간선의 갯수)
		int[] degree = new int[n];

		// 노드 값을 셋팅한 adj에 연결 노드 입력
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			// 방향 없는 그래프이기때문에 양쪽으로 연결.
			adj.get(u).add(v);
			adj.get(v).add(u);
			degree[u]++;
			degree[v]++;
		}

		// 2. 첫 번째 리프 노드(차수가 1인 노드)들을 큐에 삽입
		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (degree[i] == 1) leaves.add(i);
		}

		// 3. 노드가 1개 또는 2개 남을 때까지 껍질 벗기기 반복
		int remainingNodes = n;

		// 트리의 정중앙(루트 후보)은 무조건 1개 아니면 2개뿐이기떄문에 > 2
		// 그래프 외곽(리프 노드)부터 안쪽으로 좁혀들어가는 알고리즘의 핵심.
		while (remainingNodes > 2) {

			// 노드 갯수 - 리프 노드의 갯수
			// 이번 단계에서 삭제할 리프 노드들의 개수만큼 전체 카운트에서 뺀다.
			remainingNodes -= leaves.size();

			// 다음 층의 리프 노드들을 저장할 배열
			List<Integer> newLeaves = new ArrayList<>();

			// 현재 리프 노드들을 하나씩 꺼낸다.
			// 해당 리프 노드를 위에서 삭제 했음으로 연결된 노드들에서 해당 리프노드를 지워주는 중.
			for (int leaf : leaves) {
				// 리프 노드와 연결된 이웃 노드(parent)를 찾음
				// 리프 노드가 되는 조건은 차수가 1임으로 이 방복문은 보통 1번만 돈다.
				for (int neighbor : adj.get(leaf)) {
					// 이웃 노드의 차수를 1 줄임
					degree[neighbor]--;

					// 차수가 감소한 결과, 이웃 노드도 간선이 1개만 남았다면
					// 이 노드도 이제 외곽(리프)이 된 것이므로 다음 단계 제거 명단에 추가
					if (degree[neighbor] == 1) {
						newLeaves.add(neighbor);
					}
				}
			}
			// 새로운 리프노드 값을 넣어주며 다음 단계로 이동
			leaves = newLeaves;
		}

		// 위의 알고리즘은 소거법을 써서 정답을 추출하기때문에 남은 노드가 1개 또는 2개가 된다.
		// 그리고 남은 노드가 중심노드들인것이다. 왜냐면 밑에서부터 리프 노드를 한층씩 제거하다보면 마지막엔 루트 노드만 남으니까.
		return leaves;
	}


	public static void main(String[] args) {
		int n = 4;
		List<int[]> edges = new ArrayList<>();
		edges.add(new int[]{1, 0});
		edges.add(new int[]{1, 2});
		edges.add(new int[]{1, 3});

		MinimumHeightTrees app = new MinimumHeightTrees();
		System.out.println(app.solution(n, edges));

	}
}
