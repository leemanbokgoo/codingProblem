package javaAnswer.sort;

/**
 * 58) 리스트 정렬
 */
public class SortList {

	// 병합 정렬
	public ListNode mergeSort(ListNode head) {
		// 전처리
		if (head == null || head.next == null) return head;

		// 1. 분할 (Divide): 리스트를 반으로 나눔
		// 연결 리스트는 인덱스가 없어서 중간을 찾으려면 slow & fast pointer 전략을 사용함.
		ListNode prev = null; // 리스트를 두 조각으로 자르기 위해 slow 의 바로 직전 노드를 추적
		ListNode slow = head; // 한칸씩 이동
		ListNode fast = head; // 두칸씩 이동

		// fast 가 끝에 도달하면 slow 는 정확히 중간에 가있게 됨.
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		// prev.next 를 null 로 만들어 앞부분(head)와 뒷부분(slow)의 연결 고리를 완전히 끊는다. -> 두개의 독립된 리스트가 됨.
		prev.next = null;

		// 2. 재귀적으로 정렬
		// 리스트가 노드 한 개 단위가 될 때까지 계속해서 반으로 쪼개고 정렬을 시도한다.
		ListNode l1 = mergeSort(head); // 왼쪽 절반 정렬
		ListNode l2 = mergeSort(slow); // 오른쪽 절반 정렬

		// 3. 병합 (Merge)
		// 위에서 쪼갠 노드들을 다시 합치면서 크기 순서대로 줄을 세운다.
		return merge(l1, l2);
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0); // 가짜 노드 생성
		ListNode curr = dummy; // 결과 리스트를 만들어 나갈 현재 포인터

		while (l1 != null && l2 != null) { // 두 리스트 모두 데이터가 있을 때까지 반복

			// 더 작은 쪽의 값을 curr 에 이어붙이고 작은 값의 리스트를 한칸 전진.
			if (l1.val < l2.val) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}

			// 현재 리스트도 한칸 전진.
			curr = curr.next;
		}
		/// 한쪽 리스트가 먼저 끝났을때 남은 리스트를 통째로 뒤에 붙임.
		if (l1 != null) curr.next = l1;
		if (l2 != null) curr.next = l2;

		return dummy.next;
	}

	// 퀵 정렬
	public ListNode sortList(ListNode head) {
		// 예외 처리
		if (head == null || head.next == null) return head;

		// 3개의 리스트 (left, mid, right)를 만들기 위한 가짜 노드들
		ListNode leftDummy = new ListNode(0);
		ListNode left = leftDummy; // 피벗보다 작은 노드
		ListNode midDummy = new ListNode(0), mid = midDummy; // 피벗과 같은 노드
		ListNode rightDummy = new ListNode(0), right = rightDummy; // 피벗보다 큰 노드

		int pivot = head.val; // 첫번째 노드의 값을 기준점(피벗)으로 잡음.
		ListNode curr = head; // 리스트를 순회할 현재 포인터

		// 1. 피벗 기준으로 3개 리스트로 분할
		while (curr != null) {
			if (curr.val < pivot) { // 피벗보다 작으면
 				left.next = curr; // 포인터(curr)를 left 뒤에 붙임.
				left = left.next; // left 포인터 전진.

			} else if (curr.val == pivot) { // 피벗과 같으면
				mid.next = curr; // 포인터(curr)를 mid 뒤에 붙임
				mid = mid.next; // mid 포인터 전진

			} else { // 피벗보다 크면
				right.next = curr; // 포인터(curr)를 right 뒤에 붙임.
 				right = right.next; // right 포인터 전진.
			}

			curr = curr.next; // 원본 리스트(curr) 의 다음 노드로 이동
		}

		// 끝단 처리
		// 각 바구니에 들어간 노드들은 여전히 원본 리스트의 뒤쪽 노드들과 연결되어있을 수 있음으로 연결을 끊어주지않으면 무한 루프에 빠져 리스트가 꼬이게 됨.
		left.next = null;
		mid.next = null;
		right.next = null;

		// 2. 재귀 호출 및 리스트 이어붙이기
		leftDummy.next = sortList(leftDummy.next); // 작은 그룹을 다시 정렬
		rightDummy.next = sortList(rightDummy.next); // 큰 그룹을 다시 정렬

		// 정렬된 리스트 합치기: Left -> Mid -> Right
		return concat(leftDummy.next, midDummy.next, rightDummy.next);
	}

	private ListNode concat(ListNode l1, ListNode l2, ListNode l3) {
		// dummy : 결과 리스트의 head 를 잡기 위한 가짜 노드
		// curr : 기차 칸을 하나씩 이어붙이며 전진하는 작업용 포인터
		ListNode dummy = new ListNode(0), curr = dummy;

		ListNode[] lists = {l1, l2, l3};  // l1(작은 것들), l2(같은 것들), l3(큰 것들)

		// 그냥 l1의 끝과 l2의 시작을 연결 하는 식으로 구현하면 작은 값이 하나도 없어서 l1이 null 이어도 코드가 터지지않고 l2로 넘어간다.
		// 그래서 while 문을 통해 일일히 연결해준다.
		for (ListNode l : lists) {

			// 현재 리스트(l) 빌 때까지 반복
			while (l != null) {
				curr.next = l; // 현재 리스트(l)의 노드를 curr 에 연결
				curr = curr.next; // curr 한칸 전진.
				l = l.next; // 현재 리스트(l)의 다음 노드로 이동
			}
		}
		return dummy.next;
	}

	public static void main(String[] args) {

	}
}