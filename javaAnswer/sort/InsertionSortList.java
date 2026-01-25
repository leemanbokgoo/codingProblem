package javaAnswer.sort;

/**
 *  60 ) 삽입 정렬 리스트
 */
public class InsertionSortList {

	public ListNode solution(ListNode head) {
		if (head == null) return null;

		ListNode dummy = new ListNode(0); // 정렬된 리스트의 가짜 머리
		ListNode curr = head;             // 정렬해야 할 현재 노드

		while (curr != null) {
			// 다음에 정렬할 노드를 미리 저장 (연결을 끊을 것이기 때문)
			ListNode nextNode = curr.next;

			// 삽입할 위치의 '직전' 노드를 찾기 위해 항상 dummy부터 시작
			// 즉, 매번 정렬된 연결리스트(dummy)의 가장 앞에서 부터 탐색해서 정렬하는 것이다.
			ListNode prev = dummy;

			// 정렬된 부분에서 curr.val이 들어갈 위치를 탐색
			// prev.next가 curr.val보다 작으면 계속 전진
			while (prev.next != null && prev.next.val < curr.val) {
				prev = prev.next;
			}

			// 노드 끼워 넣기 (정렬된 리스트의 prev와 prev.next 사이)
			//  4->6 사이에 5를 끼워서 4->5->6을 만드는 과정이다.
			curr.next = prev.next;
			prev.next = curr;

			// 다음 정렬할 노드로 이동
			curr = nextNode;
		}

		return dummy.next;
	}
}
