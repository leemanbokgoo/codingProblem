package javaAnswer.string;
// 06 ) 가장 긴 펠림드롬 부분 문자열

// 내 풀이
// 힌트 : 처음 문자열부터 시작해서 윈도우를 확장해나가며 검사해야함.
//  책의 풀이와 비교했을때 비효율적이다. 중첩된 반복문과 문자열 슬라이싱 및 역순 비교 때문에 시간 복잡도가 O(N^3)임.
public class LongestPalindrome {

	// // 내 풀이 (브루트 포스 방식)
	// 시간 복잡도: O(N^3)
	public String solution(String s) {
		int length = s.length();
		String maxPalindrome = "";

		for (int i = 0; i < length; i++) {
			int left = i;
			int right = length - 1;

			while (left < right) {
				// window = s[ left : right + 1]
				String window = s.substring(left, right + 1);

				// window == window[::-1] 검사
				if (isPalindrome(window)) {
					if (window.length() > maxPalindrome.length()) {
						maxPalindrome = window;
					}
				}
				right--;
			}
		}
		// 문자열 전체가 한 글자일 경우 등을 고려하여 초기값이 비어있다면 첫 글자 반환
		return maxPalindrome.equals("") && length > 0 ? s.substring(0, 1) : maxPalindrome;
	}

	// 팰린드롬 여부 확인을 위한 보조 메서드 (파이썬의 [::-1] 역할)
	private boolean isPalindrome(String s) {
		int l = 0, r = s.length() - 1;
		while (l < r) {
			if (s.charAt(l++) != s.charAt(r--)) return false;
		}
		return true;
	}

	// // 책 풀이 (중앙 확장 방식)
	// 시간 복잡도: O(N^2)
	public String solution2(String s) {
		// 예외 처리: 길이가 2 미만이거나 전체가 팰린드롬인 경우
		if (s.length() < 2 || isPalindrome(s)) {
			return s;
		}

		String result = "";

		for (int i = 0; i < s.length() - 1; i++) {
			// expand(i, i + 1) -> 짝수 중심 확장 (예: "baab")
			// expand(i, i + 2) -> 홀수 중심 확장 (예: "aba")
			String even = expand(s, i, i + 1);
			String odd = expand(s, i, i + 2);

			// result = max(result, even, odd, key=len)
			if (even.length() > result.length()) result = even;
			if (odd.length() > result.length()) result = odd;
		}

		return result;
	}

	// 중앙을 중심으로 양옆으로 확장해나가는 메서드
	private String expand(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		// while문을 빠져나올 때는 조건이 깨진 상태이므로 left+1부터 right까지 슬라이싱
		return s.substring(left + 1, right);
	}

	public static void main(String[] args) {
		LongestPalindrome app = new LongestPalindrome();

		System.out.println("풀이 1 (babd): " + app.solution("babd")); // bab
		System.out.println("풀이 2 (cbbd): " + app.solution2("cbbd")); // bb
	}
}