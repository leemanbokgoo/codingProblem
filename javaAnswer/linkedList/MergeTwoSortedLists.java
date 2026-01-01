package javaAnswer.linkedList;

public class MergeTwoSortedLists {

	// # 14 ) 두 정렬 리스트의 병합
	// 힌트 받아서 푼 풀이
	// 외우는 것이 좋을 듯
	public ListNode solution(ListNode node, ListNode node2) {

		// 파이썬: if (not node) or ( node2 and node.val > node2.val )
		if (node == null || (node2 != null && node.val > node2.val)) {
			// 파이썬의 다중 할당 (node, node2 = node2, node) 재현
			ListNode temp = node;
			node = node2;
			node2 = temp;
		}

		if (node != null) {
			// node.next = solution(node.next , node2)
			node.next = solution(node.next, node2);
		}

		return node;
	}

	public static void main(String[] args) {
		MergeTwoSortedLists app = new MergeTwoSortedLists();

		// node 세팅 (1 -> 2 -> 4)
		ListNode node = new ListNode(1, new ListNode(2, new ListNode(4)));

		// node2 세팅 (1 -> 3 -> 4)
		ListNode node2 = new ListNode(1, new ListNode(3, new ListNode(4)));

		// 병합 실행
		ListNode resultList = app.solution(node, node2);

		// 결과 출력 로직
		StringBuilder result = new StringBuilder();
		while (resultList != null) {
			result.append(resultList.val);
			resultList = resultList.next;
			if (resultList != null) {
				result.append(" -> ");
			}
		}

		System.out.println(result.toString());
	}
}