package javaAnswer.linkedList;
// 17 ) 페어의 노드 스왑

public class SwapNodesInPairs {

	// 내 풀이: 값만 교환
	public ListNode solution(ListNode node) {
		ListNode head = node;

		// while node and node.next :
		while (node != null && node.next != null) {
			// node.val, node.next.val = node.next.val , node.val
			int temp = node.val;
			node.val = node.next.val;
			node.next.val = temp;

			// node = node.next.next
			node = node.next.next;
		}

		return head;
	}

	// 책의 풀이 1 : 재귀 구조
	public ListNode solution2(ListNode node) {
		// if node and node.next :
		if (node != null && node.next != null) {
			ListNode p = node.next;
			// node.next = solution2(p.next)
			node.next = solution2(p.next);
			// p.next = node
			p.next = node;
			return p;
		}

		return node;
	}

	// 책의 풀이 2 : 반복 구조로 스왑
	public ListNode solution3(ListNode node) {
		// root, prev = ListNode(None)
		ListNode root = new ListNode(0);
		ListNode prev = root;
		prev.next = node;

		while (node != null && node.next != null) {
			// b = node.next
			ListNode b = node.next;

			// node.next = b.next
			node.next = b.next;
			// b.next = node
			b.next = node;

			// prev.next = b
			prev.next = b;

			// node = node.next
			node = node.next;
			// prev = prev.next.next
			prev = prev.next.next;
		}

		return root.next;
	}

	public static void main(String[] args) {
		SwapNodesInPairs app = new SwapNodesInPairs();

		// 1 -> 2 -> 3 -> 4 데이터 세팅
		ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));

		// solution 실행 (원하는 풀이로 변경 가능)
		ListNode resultNode = app.solution(node);

		// 결과 출력
		StringBuilder result = new StringBuilder();
		while (resultNode != null) {
			result.append(resultNode.val);
			if (resultNode.next != null) {
				result.append("->");
			}
			resultNode = resultNode.next;
		}

		System.out.println(result.toString()); // 출력: 2->1->4->3
	}
}