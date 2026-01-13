package javaAnswer.string;
import java.util.*;

/**
 *  01 ) 유효한 펠린드롬
 */

public class ValidPalindrome {

	public boolean solution(String s) {
		// 배열(List)로 만들기
		List<Character> strs = new ArrayList<>();

		// toCharArray() : 문자열(String)을 한 글자씩 쪼개서 문자 배열(char[])로 변환
		for (char c : s.toCharArray()) {
			// Character.isLetterOrDigit(c) : 문자(한글/영어) 혹은 숫자인지 확인
			if (Character.isLetterOrDigit(c)) {
				// 대소문자 구별 안 함으로 소문자로 통일
				strs.add(Character.toLowerCase(c));
			}
		}

		// 중앙값을 기준으로 양 끝을 비교하며 삭제
		// str에 값이 1개보다 많이 남을 때까지 반복
		while (strs.size() > 1) {
			// 자바 ArrayList의 remove(index)는 삭제된 요소를 반환함.
			char last = strs.remove(strs.size() - 1);
			char first = strs.remove(0);
			if (last != first) {
				return false;
			}
		}
		return true;
	}

	public boolean solution2(String s) {
		List<Character> strList = new ArrayList<>();

		for (char c : s.toCharArray()) {
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