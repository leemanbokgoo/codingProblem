package javaAnswer.linkedList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbers {

	/**
	 * 방식 1: 내 풀이 (BigInteger 변환 방식)
	 * 자바의 long은 노드가 많아질 경우 숫자를 다 담지 못해 에러(Overflow)가 날 수 있기 때문에 bigInteger 사용.
	 */
	public ListNode solutionWithConversion(ListNode node1, ListNode node2) {
		String node1Str = "";
		String node2Str = ""; // 변수명을 node3Str에서 node2Str로 수정

		while (node1 != null) {
			node1Str += String.valueOf(node1.val);
			node1 = node1.next;
		}

		while (node2 != null) {
			node2Str += String.valueOf(node2.val);
			node2 = node2.next;
		}

		node1Str = new StringBuilder(node1Str).reverse().toString();
		node2Str = new StringBuilder(node2Str).reverse().toString();

		BigInteger sumResult = new BigInteger(node1Str).add(new BigInteger(node2Str));

		String sumStr = sumResult.toString();
		char[] sumResultList = sumStr.toCharArray();

		ListNode prev = null;
		ListNode node = null;

		// 문자열 배열을 뒤에서부터 순회하며 노드 연결
		for (int i = sumResultList.length - 1; i >= 0; i--) {
			int number = Character.getNumericValue(sumResultList[i]);
			node = new ListNode(number);
			node.next = prev;
			prev = node;
		}
		return node;
	}

	/**
	 * 방식 2: 전가산기 방식 (표준 정답)
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		int carry = 0;

		// 1. 일단 1의 자리부터 계산해서 7 -> 0 -> 8 을 만듭니다.
		while (l1 != null || l2 != null || carry != 0) {
			int sum = carry;
			if (l1 != null) { sum += l1.val; l1 = l1.next; }
			if (l2 != null) { sum += l2.val; l2 = l2.next; }

			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
		}

		// 2. 결과가 807이 되도록 전체 리스트를 뒤집어서 8 -> 0 -> 7 로 만듭니다.
		return reverse(dummy.next);
	}

	// 리스트를 뒤집는 유틸리티 함수
	public ListNode reverse(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	// 유틸리티 및 테스트 로직
	public void printNodes(String title, ListNode node) {
		System.out.print(title + ": ");
		List<String> vals = new ArrayList<>();
		while (node != null) {
			vals.add(String.valueOf(node.val));
			node = node.next;
		}
		System.out.println(String.join(" -> ", vals));
	}

	public static void main(String[] args) {
		AddTwoNumbers app = new AddTwoNumbers();

		// 데이터 세팅 (2 -> 4 -> 3) + (5 -> 6 -> 4)
		ListNode n1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		ListNode n2 = new ListNode(5, new ListNode(6, new ListNode(4)));

		// 결과 실행 및 출력
		ListNode res1 = app.solutionWithConversion(n1, n2);
		app.printNodes("방식 1 (변환)", res1);

		// 데이터 재세팅 (이전 연산에서 n1, n2의 참조가 끝까지 이동했으므로 새로 생성)
		n1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		n2 = new ListNode(5, new ListNode(6, new ListNode(4)));

		ListNode res2 = app.addTwoNumbers(n1, n2);
		app.printNodes("방식 2 (가산기)", res2);
	}
}