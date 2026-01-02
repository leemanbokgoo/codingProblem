package javaAnswer.string;
import java.util.*;
// 01 ) 유효한 펠린드롬

public class ValidPalindrome {

	public boolean solution(String s) {
		// 배열(List)로 만들기
		List<Character> strs = new ArrayList<>();

		for (char c : s.toCharArray()) {
			// char.isalnum(): 문자와 숫자인 경우만 필터링
			if (Character.isLetterOrDigit(c)) {
				// 대소문자 구별 안 함으로 소문자로 통일
				strs.add(Character.toLowerCase(c));
			}
		}

		// 중앙값을 기준으로 양 끝을 비교하며 삭제
		// str에 값이 1개보다 많이 남을 때까지 반복
		while (strs.size() > 1) {
			// strs.pop() != strs.pop(0)
			// 자바 ArrayList의 remove(index)는 삭제된 요소를 반환한다.
			char last = strs.remove(strs.size() - 1);
			char first = strs.remove(0);

			if (last != first) {
				return false;
			}
		}
		return true;
	}

	// # 책의 풀이 방식 변환
	public boolean answer(String s) {
		List<Character> strs = new ArrayList<>();

		// for char in s:
		for (char c : s.toCharArray()) {
			// if char.isalnum():
			if (Character.isLetterOrDigit(c)) {
				// strs.append(char.lower())
				strs.add(Character.toLowerCase(c));
			}
		}

		// while len(strs) > 1: (파이썬 코드의 오타 str을 strs로 교정)
		while (strs.size() > 1) {
			// if strs.pop() != strs.pop(0):
			// 자바 remove()는 삭제된 요소를 반환하므로 pop과 동일하게 작동합니다.
			char last = strs.remove(strs.size() - 1); // pop()
			char first = strs.remove(0);              // pop(0)

			if (last != first) {
				return false;
			}
		}
		return true;
	}

	public boolean solution2(String s) {
		// 배열(리스트)로 만들기
		List<Character> strList = new ArrayList<>();

		for (char c : s.toCharArray()) {
			// 문자와 숫자인 경우만 필터링
			if (Character.isLetterOrDigit(c)) {
				// 대소문자 구별 안 하므로 소문자로 통일
				strList.add(Character.toLowerCase(c));
			}
		}

		// 중앙값을 기준으로 오른쪽과 왼쪽은 같음.
		// 그러므로 중앙값이 남을때까지 오른쪽과 왼쪽을 없앤다.
		// strList에 값이 남을 때까지 반복
		while (strList.size() > 1) {
			// if str[0] == str[-1]:
			if (strList.get(0).equals(strList.get(strList.size() - 1))) {
				// str.pop()
				strList.remove(strList.size() - 1);
				// str.pop(0)
				strList.remove(0);
			} else {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		ValidPalindrome app = new ValidPalindrome();

		System.out.println(app.solution("A man, a plan, a canal: Panama")); // true
		System.out.println(app.solution("race a car"));                   // false
	}
}