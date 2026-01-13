package javaAnswer.linkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 13 ) 펠린드롬 연결 리스트
 */

public class PalindromeLinkedList {

	public boolean solution(ListNode node) {

		// node가 존재하지않거나 node.next가 존재하지않으면 펠린드롬이 아님.
		if (node == null && node.next == null) {
			return false;
		}

		List<Integer> str = new ArrayList<>();

		// 연결 리스트를 배열로 변경
		while (node != null) {
			str.add(node.val);
			node = node.next;
		}

		while ( !str.isEmpty() ) {
			// 배열 길이가 1이 될떄까지 밑의 if문에 걸리지않았다는 것은 펠린드롬이라는 말.
			// 현재 배열의 길이 1에 해당되는 요소는 펠린드롬의 중앙값인 것. ( 해당 문자열이 홀수인 경우)
			if (str.size() <= 1) {
				return true;
			}

			// 양쪽 끝이 동일한 문자열인지 비교
			// 파이썬의 str.pop() -> str.remove(str.size() - 1)
			// 파이썬의 str.pop(0) -> str.remove(0)
			if (!str.remove(str.size() - 1).equals(str.remove(0))) {
				return false;
			}
		}

		return true;
	}

	// 책의 풀이에서는 while len(str) > 1 : 으로 루프를 돌려서 if문을 두번 만들지않음.
	// if str.pop() != str.pop(0): 이 부분을 데크를 이용해 최적화 할 수 있다.
	// 동적 배열은 맨 앞 아이템을 가져오기에 적합한 자료형이 아니다. 시간복잡도 O(n)이 발생하기 때문.
	public ListNode stringToListNode(String inputString) {
		if (inputString == null || inputString.isEmpty()) {
			return null;
		}

		// '->'를 기준으로 문자열을 분리하여 값 목록을 생성
		// 예: "1->2->2->1" -> ['1', '2', '2', '1']
		String[] values = inputString.split("->");

		// 첫 번째 값을 사용하여 head 노드를 생성
		ListNode head = new ListNode(Integer.parseInt(values[0]));
		ListNode current = head;

		// 나머지 값들을 순회하며 노드를 만들고 연결
		for (int i = 1; i < values.length; i++) {
			// 새로운 노드를 만들고 이전 노드의 next에 연결
			current.next = new ListNode(Integer.parseInt(values[i]));
			current = current.next;
		}

		return head;
	}

	public static void main(String[] args) {
		PalindromeLinkedList app = new PalindromeLinkedList();

		// 팰린드롬 문자열 입력
		String inputStrPalindrome = "1->2->2->1";
		String inputStrNotPalindrome = "1->2";

		ListNode input1 = app.stringToListNode(inputStrPalindrome);
		ListNode input2 = app.stringToListNode(inputStrNotPalindrome);

		System.out.println(app.solution(input1)); // true
		System.out.println(app.solution(input2)); // false
	}
}