package javaAnswer.stack;

import java.util.PriorityQueue;

//  27 ) k개 정렬 리스트 병합
public class MergeKSortedLists {

	/**
	 * k개 정렬 리스트 병합
	 * 우선순위 큐(PriorityQueue)를 사용하여 구현
	 */
	public ListNode solution(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;

		// root = result = ListNode(None)
		ListNode root = new ListNode(0);
		ListNode result = root;

		// 파이썬의 heapq 역할을 하는 우선순위 큐
		// 값(val)이 같을 경우 리스트의 인덱스(index)로 비교하도록 설정
		PriorityQueue<NodeElement> heap = new PriorityQueue<>((a, b) -> {
			if (a.val == b.val) return Integer.compare(a.index, b.index);
			return Integer.compare(a.val, b.val);
		});

		// 1. 각 연결 리스트의 첫 번째 노드를 힙에 추가
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				heap.add(new NodeElement(lists[i].val, i, lists[i]));
			}
		}

		// 2. 힙이 빌 때까지 반복
		while (!heap.isEmpty()) {
			// 가장 작은 값을 가진 노드 정보 추출 (heappop)
			NodeElement element = heap.poll();
			int index = element.index;
			ListNode node = element.node;

			// 결과 리스트의 다음 노드로 연결
			result.next = node;
			// result를 한 칸 전진
			result = result.next;

			// 꺼낸 노드의 다음 노드가 존재하면 다시 힙에 추가 (heappush)
			if (result.next != null) {
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