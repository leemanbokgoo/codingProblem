package javaAnswer.linkedList;
// 15 ) 역순 연결 리스트
public class ReverseLinkedList {

	// # 나 혼자 풀이 : 반복 구조로 풀이
	public ListNode solutionMyself(ListNode node) {
		// list = ListNode(None) -> 자바에서는 null을 가진 노드 혹은 null 자체로 시작
		ListNode list = null;

		while (node != null) {
			// tmp = node.next
			ListNode tmp = node.next;
			// node.next = list
			node.next = list;
			// list = node
			list = node;
			// node = tmp
			node = tmp;
		}

		return list;
	}

	// # 두 번째 풀이 (재귀)
	public ListNode solution(ListNode head) {
		// 내부 함수 reverse를 자바 메서드로 구현
		return reverse(head, null);
	}

	private ListNode reverse(ListNode node, ListNode prev) {
		// if not node : return prev
		if (node == null) {
			return prev;
		}

		// next_node, node.next = node.next, prev
		// 자바에서는 다중 할당이 안 되므로 순서대로 처리
		ListNode nextNode = node.next;
		node.next = prev;

		// return reverse(next_node, node)
		return reverse(nextNode, node);
	}

	public static void main(String[] args) {
		ReverseLinkedList app = new ReverseLinkedList();

		// 데이터 세팅: 1 -> 2 -> 3 -> 4 -> 5
		ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

		// solution 실행 (재귀 방식)
		ListNode resultList = app.solution(node);

		// 결과 출력
		StringBuilder result = new StringBuilder();
		while (resultList != null) {
			result.append(resultList.val);
			resultList = resultList.next;
			if (resultList != null) {
				result.append(" -> ");
			}
		}

		System.out.println(result.toString()); // 출력: 5 -> 4 -> 3 -> 2 -> 1
	}
}