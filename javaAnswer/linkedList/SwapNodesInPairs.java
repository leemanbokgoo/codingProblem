package javaAnswer.linkedList;

/**
 * 17 ) 페어의 노드 스왑
 */

public class SwapNodesInPairs {

	// 내 풀이: 값만 교환
	public ListNode solution(ListNode node) {
		ListNode head = node;

		while (node != null && node.next != null) {
			// 페어의 값 교환
			int temp = node.val;
			node.val = node.next.val;
			node.next.val = temp;

			// 페어 건너 뛰기
			node = node.next.next;
		}

		return head;
	}

	// 책의 풀이 1 : 재귀 구조
	public ListNode solution2(ListNode node) {

		if (node != null && node.next != null) {
			// 현재 노드의 다음 노드를 변수로 저장.
			ListNode p = node.next;

			// p.next는 다음 쌍을 의미한다. 예를 들어 3,4번에 해당하고 return값은 4번 노드가 된다.
			// node.next에 다음 페어를 연결하는 것.
			node.next = solution2(p.next);
			// p는 node.next다. p.next가 node를 가리킴으로써 순서가 swap된다.
			p.next = node;

			// p는 스왑하기전의 페어 중 오른쪽 값이다.
			// 해당 값을 반환해서 이전 페어와 연결한다.
			return p;
		}

		return node;
	}

	// 책의 풀이 2 : 반복 구조로 스왑
	public ListNode solution3(ListNode node) {

		ListNode root = new ListNode(0);
		ListNode prev = root;
		prev.next = node;

		while (node != null && node.next != null) {

			// 페어 중 오른쪽
			ListNode b = node.next;
			// 현제 페어의 왼쪽에 다음 페어를 연결
			node.next = b.next;
			// 페어 swap
			b.next = node;

			// 뒤집기가 끝난 앞쪽 페어와 현재 뒤집은 페어의 오른쪽을 연결해준다.
			prev.next = b;

			// 다음 페어를 향해 이동
			node = node.next; // 페어의 왼쪽임으로 한칸
			prev = prev.next.next; // 현재 페어를 가리켜야함으로 페어(2칸)을 건너 뛴다.
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