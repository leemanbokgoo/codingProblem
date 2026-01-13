package javaAnswer.linkedList;

/**
 *  19 ) 역순 연결 리스트 2
 */

public class ReverseLinkedList2 {

	// 이 풀이는 다른 데서 쓰일 것 같아서 외워두는 게 좋을듯
	public ListNode solution(ListNode head, int m, int n) {
		// if not head and m == n :
		if (head == null || m == n) {
			return head;
		}

		// 값을 셋팅
		ListNode root = new ListNode(0);
		root.next = head;
		ListNode start = root;

		// start 를 역순으로 뒤집어야하는 노드의 바로 이전 노드까지 이동.
		// for _ in range( m - 1 ):
		for (int i = 0; i < m - 1; i++) {
			start = start.next;
		}

		// end는 start 다음 노드로 셋팅.
		// start.next가 end 노드가 되는 이유는 역순으로 뒤집고 나면 해당 노드가 가장 뒤에 있어야하기때문에 end 노드다.
		ListNode end = start.next;

		for (int i = 0; i < n - m; i++) {
			// 현재 m번째 노드를 임시로 저장.
			ListNode tmp = start.next;

			// m 자리에 end.next 노드를 넣어준다. (end 뒤의 노드를 뽑아서 start 바로 뒤로 이동)
			start.next = end.next;

			// end 뒤에 있는 노드를 뽑았으니, end와 그 다음 노드(end.next.next)를 연결
			end.next = end.next.next;

			// 새로 앞으로 온 노드(start.next) 뒤에 기존의 m번째 노드(tmp)를 연결
			start.next.next = tmp;
		}

		return root.next;
	}

	// 책풀이
	public ListNode solution2(ListNode head, int m, int n) {
		if (head == null || m == n) {
			return head;
		}

		ListNode root = new ListNode(0);
		root.next = head;
		ListNode start = root;

		for (int i = 0; i < m - 1; i++) {
			start = start.next;
		}

		ListNode end = start.next;

		for (int i = 0; i < n - m; i++) {
			ListNode tmp = start.next;
			start.next = end.next;
			end.next = end.next.next;
			start.next.next = tmp;
		}

		return root.next;
	}

	public static void main(String[] args) {
		ReverseLinkedList2 app = new ReverseLinkedList2();

		// 1 -> 2 -> 3 -> 4 -> 5 세팅
		ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		int m = 2, n = 4;

		ListNode resultNode = app.solution(node, m, n);

		// 결과 출력
		StringBuilder result = new StringBuilder();
		while (resultNode != null) {
			result.append(resultNode.val);
			resultNode = resultNode.next;
			if (resultNode != null) result.append(" -> ");
		}

		System.out.println(result.toString()); // 출력: 1 -> 4 -> 3 -> 2 -> 5
	}
}