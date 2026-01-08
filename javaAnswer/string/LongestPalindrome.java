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

	// 팰린드롬 여부 확인을 위한 보조 메서드
	private boolean isPalindrome(String s) {
		// 슬라이딩 윈도우, 투 포인터
		int l = 0, r = s.length() - 1;

		// 두 포인터가 만날때까지 반복
		while (l < r) {
			// 먼저 s.charAt(l)과 s.charAt(r)을 비교한다.
			// 다르면 즉시 false를 리턴하고 끝냄(팰린드롬이 아님)
			// 비교가 끝나면 후위 연산자(++, --) 덕분에 l은 증가하고 r은 감소하여 다음칸으로 이동한다.
			if (s.charAt(l++) != s.charAt(r--)) return false;
		}
		// 반복문을 끝까지 통과했다면 모든 대칭 글자가 일치했다는 뜻이므로 true를 반환.
		return true;
	}

	// 중앙을 중심으로 양옆으로 확장해나가는 메서드
	private String expand(String s, int left, int right) {

		// left와 right 포인터가 범위 밖으로 나가지않고 둘이 같다면, (= 아직까진 펠린드롬이라는 뜻)
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		// while문을 빠져나올 때는 조건이 깨진 상태이므로 left+1부터 right까지 슬라이싱
		// 루프가 끝난 시점의 left와 right는 팰린드롬의 일부가 아닌 한 발자국 더 나간 상태임으로 left + 1한다.
		// substring()할때 right는 +1 하지않는 이유는 start 인덱스는 포함하지만 end 인덱스는 포함하지않기때문이다.
		return s.substring(left + 1, right);
	}

	public static void main(String[] args) {
		LongestPalindrome app = new LongestPalindrome();

		System.out.println("풀이 1 (babd): " + app.solution("babd")); // bab
		System.out.println("풀이 2 (cbbd): " + app.solution2("cbbd")); // bb
	}
}