package javaAnswer.linkedList;

public class OddEvenLinkedList {

	// # 도움없이 처음으로 혼자 풀이
	public ListNode solutionOrigin(ListNode node) {
		if (node == null || node.next == null) return node;

		ListNode oven = node; // 홀수
		ListNode odd = node.next; // 짝수
		ListNode odd_head = odd;

		// 파이썬: while node: 지만 자바에서는 실제 이동하는 포인터인 odd와 odd.next의 null 체크가 필요하다.
		while (odd != null && odd.next != null) {
			oven.next = oven.next.next;
			odd.next = odd.next.next;

			oven = oven.next;
			odd = odd.next;
		}

		oven.next = odd_head;
		return node; // 전체 리스트를 보려면 시작점인 node를 반환해야 한다.
	}

	// # 첫번째 풀이
	public ListNode solution(ListNode head) {
		if (head == null || head.next == null) {
			return head; // 파이썬의 return None 대신 head 반환이 안전함.
		}

		ListNode odd = head;
		ListNode even = head.next;
		ListNode even_head = head.next; // 현재 짝수 번째 가장 앞에 있는 노드 위치를 백업 해둠.

		while (even != null && even.next != null) {
			// 홀수 번째 애들끼리, 짝수번째 애들 끼리 연결
			odd.next = odd.next.next;
			even.next = even.next.next;

			// 칸 이동
			odd = odd.next;
			even = even.next;
		}

		odd.next = even_head;

		// 현재 odd 노드는 홀수번째 가장 마지막 노드를 가리키고 있음으로 head를 반환
		return head;
	}

	// # 책의 풀이
	public ListNode solution2(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode odd = head; // 홀수
		ListNode even = head.next; // 짝수
		ListNode even_head = head.next;

		while (even != null && even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;

			odd = odd.next;
			even = even.next;
		}

		odd.next = even_head;
		return head;
	}

	public static void main(String[] args) {
		OddEvenLinkedList app = new OddEvenLinkedList();

		// 1 -> 2 -> 3 -> 4 -> 5 세팅
		ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

		ListNode resultNode = app.solution(node);

		// 결과 출력 로직
		StringBuilder result = new StringBuilder();
		while (resultNode != null) {
			result.append(resultNode.val);
			resultNode = resultNode.next;
			if (resultNode != null) {
				result.append(" -> ");
			}
		}

		System.out.println(result.toString()); // 출력: 1 -> 3 -> 5 -> 2 -> 4
	}
}