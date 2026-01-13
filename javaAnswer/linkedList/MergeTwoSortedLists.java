package javaAnswer.linkedList;

/**
 * 14 ) 두 정렬 리스트의 병합
 */

public class MergeTwoSortedLists {

	// 힌트 받아서 푼 풀이
	// 외우는 것이 좋을 듯
	public ListNode solution(ListNode node, ListNode node2) {

		// node가 null이고 node2의 값이 있을때 node의 값이 node2보다 크다면
		// node와 node2의 값을 교환한다. 그러면 더 작은 값이 앞으로 가게 된다.
		if (node == null || (node2 != null && node.val > node2.val)) {
			ListNode temp = node;
			node = node2;
			node2 = temp;
		}

		if (node != null) {
			// 재귀 호출로 node의 다음 값을 정한다.
			// node.next값을 넘겨주는 이유는 이미 node의 값은 위에서 더 작은 값으로 정렬해서 정했기 때문임.
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