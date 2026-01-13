package javaAnswer.linkedList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *  16 두수의 덧셈
 */
public class AddTwoNumbers {

	/**
	 * 방식 1: 내 풀이 (BigInteger 변환 방식)
	 * 자바의 long은 노드가 많아질 경우 숫자를 다 담지 못해 에러(Overflow)가 날 수 있기 때문에 bigInteger 사용.
	 */
	public ListNode solutionWithConversion(ListNode node1, ListNode node2) {
		String node1Str = "";
		String node2Str = "";

		// 연결 리스트들을 문자열로 변환
		// 1-> 2-> 3 => 123
		while (node1 != null) {
			node1Str += String.valueOf(node1.val);
			node1 = node1.next;
		}

		while (node2 != null) {
			node2Str += String.valueOf(node2.val);
			node2 = node2.next;
		}

		// string은 불변 객체임으로 reverse()를 한다면, 내부적으로 글자를 하나하나 다 뜯어서 새로운 메모리 공간에 새로운 문자열을 매번 만들어야하기때문에
		// 메모리 낭비가 심하고 성능이 떨어져서 string은 reverser()가 없음.
		// StringBuilder는 Mutable(가변)임으로 StringBuilder 객체를 생성하여 문자열을 reverse 함
		node1Str = new StringBuilder(node1Str).reverse().toString();
		node2Str = new StringBuilder(node2Str).reverse().toString();

		// BigInteger은 데이터 타입이 아닌 객체임으로 + 기호 대신 add() 함수를 사용함.
		BigInteger sumResult = new BigInteger(node1Str).add(new BigInteger(node2Str));

		// 더한 BigInteger을 string 으로 변환
		String sumStr = sumResult.toString();

		// string을 연결 리시트로 변환
		char[] sumResultList = sumStr.toCharArray();

		ListNode prev = null;
		ListNode node = null;

		// 문자열 배열을 뒤에서부터 순회하며 노드 연결
		for (int i = sumResultList.length - 1; i >= 0; i--) {
			int number = Character.getNumericValue(sumResultList[i]);
			node = new ListNode(number);

			// 기존 리스트를 현재 리스트의 다음으로 붙인다.
			node.next = prev;
			// 현재 노드가 새로운 이전 노드가 됨.
			prev = node;
		}
		return node;
	}

	/**
	 * 방식 2: 전가산기 방식 (표준 정답)
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// curr값의 첫번째 head를 저장하기위한 변수 할당
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		int carry = 0;

		// 1. 일단 1의 자리부터 계산해서 7 -> 0 -> 8 을 만듭니다.
		// 연결 리스트들의 값이 존재하거나 carry가 0이 아니라면
		while (l1 != null || l2 != null || carry != 0) {
			// 숫자 자리숫마다 더한 결과 값을 저장할 변수.
			// sum에 carry값도 더해야하기때문에 애초에 걍 할당해줌.
			int sum = carry;

			// 각 연결 리스트들의 현재 val 을 sum에 더해주고 li을 한칸 이동
			if (l1 != null) { sum += l1.val; l1 = l1.next; }
			if (l2 != null) { sum += l2.val; l2 = l2.next; }

			// 더한 값 sum에 10나눠서 다음 자리로 값을 올림해줌. 이때 carry의 값은 1을 넘지 못함.
			carry = sum / 10;

			// 나머지가 현재 숫자 자리의 값임으로 curr.next에 삽입
			// curr가 아니라 curr.next로 넣는 이유는 curr에다가 계속 넣으면 연결이 끊어지기때문임.
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
		}

		// 결과 값도 역순으로 반환해야함으로 reverse 를 통해 역순 반환.
		return reverse(dummy.next);
	}

	// 리스트를 뒤집는 유틸리티 함수
	// 이 코드는 외울 것
	public ListNode reverse(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;

		while (curr != null) {

			// curr.next 을 변경해야함으로 현재 curr.next 을 변수로 따로 뺴둠
			ListNode next = curr.next;
			// 연결을 재정의하는 부분
			curr.next = prev;

			// 다음 루프를 위해 새로운 prev, curr값을 할당
			// 1- -> nul | 2 -> 1 -> null 이런식으로 붙는 것
			prev = curr;
			curr = next;
		}
		return prev;
	}

	// 유틸리티 및 테스트 로직
	public void printNodes(String title, ListNode node) {
		System.out.print(title + ": ");

		// 연결 리스트를 배열로 변환
		List<String> vals = new ArrayList<>();

		while (node != null) {
			vals.add(String.valueOf(node.val));
			// 다음 노드로 이동
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