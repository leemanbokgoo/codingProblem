package javaAnswer.linkedList;
// 15 ) 역순 연결 리스트
public class ReverseLinkedList {

	public ListNode solutionMyself(ListNode node) {
		ListNode list = null;

		// 노드가 존재하는 동안 무한 루프 돌림
		while (node != null) {
			// node.next값을 임시변수에 저장함.
			ListNode tmp = node.next;
			// node.next 값으로 list를 넣어줌
			node.next = list;
			// list에 node를 넣어줌.
			list = node;
			// node에는 임시 변수로 넣어주었던 node.next값을 넣어줌.
			node = tmp;
		}

		return list;
	}

	// # 두 번째 풀이 (재귀)
	public ListNode solution(ListNode head) {
		return reverse(head, null);
	}

	private ListNode reverse(ListNode node, ListNode prev) {

		// 재귀 종료 조건
		if (node == null) {
			return prev;
		}

		ListNode nextNode = node.next;
		// node.next에 prev를 할당함.
		node.next = prev;

		// 현재 node를 prev 파라미터로 넘기고 node.next값을 node 파라미터로 넘김.
		// 1 null 2 1 , 3 2, 4 3, null 5 (재귀 반환 시작 )
		// return을 통해 재귀를 호출함으로 재귀호출이 최종적으로 도달하는 마지막 노드인 5를 반환하게 되어있다.
		// 5을 반환하는 동안 이미 node.next = prev를 할당해왔기때문에 역순 정렬은 끝났고 재귀 호출의 종료 조건인 5(head가 된)를 반환하는 것
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