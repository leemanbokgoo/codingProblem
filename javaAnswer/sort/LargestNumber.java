package javaAnswer.sort;

import java.util.Arrays;

/**
 *  61 ) 가장 큰 수
 */
public class LargestNumber {

	public String solution(int[] nums) {
		// 1. 숫자를 문자열 배열로 변환
		String[] asStrs = new String[nums.length];

		for (int i = 0; i < nums.length; i++) {
			asStrs[i] = String.valueOf(nums[i]);
		}

		// 2. 커스텀 정렬 규칙 적용
		// (b + a).compareTo(a + b) -> 내림차순 정렬
		// b + a가 더 크다면 양수를 반환하여 b를 a보다 앞에 오게 한다.
		Arrays.sort(asStrs, (a, b) -> (b + a).compareTo(a + b));

		// 3. 정렬 후 첫 번째 원소가 "0"이면 전체가 0임 (예: [0, 0])
		if (asStrs[0].equals("0")) {
			return "0";
		}

		// 4. 문자열 합치기
		StringBuilder sb = new StringBuilder();
		for (String s : asStrs) {
			sb.append(s);
		}

		return sb.toString();
	}

	// 삽입 정렬로 구현 shift 방식
	public String largestNumber(int[] nums) {
		// 1. 숫자를 문자열 배열로 변환 (비교를 편하게 하기 위함)
		String[] asStrs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			asStrs[i] = String.valueOf(nums[i]);
		}

		// 2. 삽입 정렬 시작
		for (int i = 1; i < asStrs.length; i++) {
			String curr = asStrs[i]; // 정렬할 값
			int j = i - 1; // 정렬할 값은 위에서 따로 변수로 뺏음으로 현재 값(curr)의 왼쪽인 i-1 부터 시작하면됨.

			// 핵심 비교 로직: (curr + j번째)와 (j번째 + curr)를 비교
			// j가 앞에 오는 경우가 더 작다면, j를 뒤로 이동시킨다.
			while (j >= 0 && isBetter(curr, asStrs[j])) {
				asStrs[j + 1] = asStrs[j]; // j + 1에 j를 넣음으로 j를 오른쪽으로 한칸씩 이동시킴.
				j--; // 탐색 위치를 왼쪽으로 이동
			}
			// while 문에서 j--했기때문에 while 문이 끝났다는 말은 j 값보다 작은 값의 자리까지 왔다는 뜻이다.
			// 고로 현재 값이 들어갈 자리는 j 바로 다음칸이다.
			asStrs[j + 1] = curr;
		}

		// 3. 예외 처리: [0, 0] 같은 경우 "0" 반환
		if (asStrs[0].equals("0")) return "0";

		// 4. 결과 합치기
		StringBuilder sb = new StringBuilder();
		for (String s : asStrs) sb.append(s);
		return sb.toString();
	}

	// 두 문자열을 합쳐서 어떤 순서가 더 큰지 판단하는 헬퍼 함수
	private boolean isBetter(String curr, String prev) {
		// (curr + prev) 가 (prev + curr) 보다 크면 curr가 앞으로 가야 함
		// > 0 은 A가 B보다 클 때를 의미함. 즉, curr + prev 가 prev + curr 보다 크다면 이라는 뜻.
		return (curr + prev).compareTo(prev + curr) > 0;
	}

	// 책풀이 swap 방식
	public String largestNumber2(int[] nums) {

		// 1. 삽입 정렬 (Insertion Sort) 시작
		// 삽입 정렬은 내 왼쪽 줄과 비교해서 들어갈 자리를 찾는 알고리즘 임으로 0번 칸은 이미 정렬되어 있다고 치고 시작한다. 고로 1부터 시작
		int i = 1;

		while (i < nums.length) {
			int j = i; // swap 방식임으로 현재 위치와 현재 위치의 왼쪽 위치를 비교해서 교환해야하기때문에 i부터 시작.
			// j > 0 이고 앞의 숫자와 비교했을 때 바꿔야 한다면 반복
			while (j > 0 && toSwap(nums[j - 1], nums[j])) {
				// 서로의 위치를 맞바꿈 (Swap)
				int temp = nums[j];
				nums[j] = nums[j - 1];
				nums[j - 1] = temp;
				j--;
			}
			i++;
		}

		// 2. 결과 처리
		// 정렬 후 가장 큰 수가 0이면 결과는 무조건 "0"
		if (nums[0] == 0) {
			return "0";
		}

		// 3. StringBuilder를 사용해 문자열 합치기
		StringBuilder sb = new StringBuilder();
		for (int num : nums) {
			sb.append(num);
		}

		return sb.toString();
	}

	private boolean toSwap(int n1, int n2) {
		String s1 = String.valueOf(n1);
		String s2 = String.valueOf(n2);
		// "n1+n2" 가 "n2+n1" 보다 작으면 true를 반환 (자리를 바꿔야 함)
		// compareTo 결과가 0보다 작으면 s1s2 < s2s1 이라는 뜻
		return (s1 + s2).compareTo(s2 + s1) < 0;
	}
}
