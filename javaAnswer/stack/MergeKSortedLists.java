package javaAnswer.stack;

import java.util.PriorityQueue;

/**
 * 27 ) k개 정렬 리스트 병합
 */
public class MergeKSortedLists {

	/**
	 * k개 정렬 리스트 병합
	 * 우선순위 큐(PriorityQueue)를 사용하여 구현
	 */
	public ListNode solution(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;

		ListNode root = new ListNode(0);
		ListNode result = root;

		// 파이썬에서는 (val, index, ListNode) 이렇게 튜플을 넣으면 알아서 첫번쨰 요소부터 순서대로 자동으로 비교하지만 자바는 명시하도록 되어있다.
		// 자바의 우선순위 큐는 구조적으로 하나의 데이터(Type)만 넣을 수 있어 현재 풀이처럼 여러개를 넣고싶으면 객체로 넣어서 보내줘야한다. 클래스를 생성하거나 간단한 경우 배열을 활용한다.
		// 값(val)이 같을 경우 리스트의 인덱스(index)로 비교하도록 설정
		// 여기서 a, b는 NodeElement 객체들을 비교하는 것으로 val끼리 비교하고 val이 같다면 index를 비교해서 정렬하도록 선언.
		PriorityQueue<NodeElement> heap = new PriorityQueue<>((a, b) -> {
			if (a.val == b.val) return Integer.compare(a.index, b.index);
			// return a.val - b.val;이라고 써도 동작은 하지만, Integer.compare를 쓰는 것이 훨씬 안전함.
			// 1. 만약 값이 엄청나게 작거나 큰 경우, 뺄셈을 하다가 숫자가 뒤집히는(Overflow) 현상이 생길 수 있는데 compare 함수는 이를 방지해줌.
			// 2. 가독성
			return Integer.compare(a.val, b.val);
		});

		// 1. 각 연결 리스트의 첫 번째 노드를 힙에 추가
		// 자바의 힙은 따로 설정 안하면 최소 힙으로 동작함. 참고로 우선순위 큐는 내부적으로 모든 데이터를 선서대로 줄세워놓지않는다.
		// poll()을 호출하는 순간에만 가장 작은 값을 맨 위로 올리는 Heap알고리즘을 사용한다.
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				heap.add(new NodeElement(lists[i].val, i, lists[i]));
			}
		}

		// k개의 연결 리스트들에서 노드를 가져와 heap에 노드를 넣어준다.
		// 그렇게 노드를 넣은 heap에서 가장 작은 노드(최소힙)을 꺼내고 result 에 연결한다.
		// 그 다음 해당 연결 리스트의 next값을 heap에 넣으면서 루프를 돌리고 모든 연결 리스트가 null이 될때까지(=heap이 빌때까지) 반복함.
		while (!heap.isEmpty()) {
			// 가장 작은 값을 가진 노드 정보 추출 (heappop)
			//  heap.poll() : 가장 우선순위가 높은 데이터를 꺼내면서 삭제. 그 값을 반환
			NodeElement element = heap.poll();
			int index = element.index;
			ListNode node = element.node;

			// 결과 리스트의 다음 노드로 연결
			result.next = node;
			// result를 한 칸 전진
			result = result.next;

			// 꺼낸 노드의 다음 노드가 존재하면 다시 힙에 추가 (heappush)
			if (node.next != null) {
				heap.add(new NodeElement(result.next.val, index, result.next));
			}
		}

		return root.next;
	}

	// 파이썬의 (val, index, ListNode) 튜플을 대체하는 자바 클래스
	private static class NodeElement {
		int val;
		int index;
		ListNode node;

		NodeElement(int val, int index, ListNode node) {
			this.val = val;
			this.index = index;
			this.node = node;
		}
	}

	public static void main(String[] args) {
		MergeKSortedLists app = new MergeKSortedLists();

		// 테스트 데이터 세팅
		ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
		ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
		ListNode l3 = new ListNode(2, new ListNode(6));

		ListNode[] lists = {l1, l2, l3};
		ListNode res = app.solution(lists);

		// 출력 확인
		while (res != null) {
			System.out.print(res.val + (res.next != null ? " -> " : ""));
			res = res.next;
		}
	}
}