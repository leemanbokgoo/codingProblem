package javaAnswer.string;

import java.util.*;
// 03 ) 로그 파일 재정렬

// 힌트 : 람다식을 사용해야함.
public class ReorderLogFiles {

	public List<String> solution(List<String> logFiles) {
		List<String> letter = new ArrayList<>();
		List<String> numbers = new ArrayList<>();

		for (String log : logFiles) {
			// split(" ", 2) : 첫 번째 공백에서만 딱 한 번 나누고, 그 뒤에 공백이 아무리 많아도 그냥 하나의 통으로 나눠서 [식별자, 나머지 내용] 배열을 만든다.
			String[] split = log.split(" ", 2);

			// .charAt(0) : 문자열(String)에서 내가 원하는 위치(인덱스)에 있는 문자 딱 하나를 'char' 타입으로 추출. 여기서는 0번째 인덱스의 문자를 추출
			// Character.isDigit() : 문자(char)이 숫자인지 판별하는 메소드, 문자열이 아님. 문자열 넣으면 에러 남.
			if (Character.isDigit(split[1].charAt(0))) {
				numbers.add(log);
			} else {
				letter.add(log);
			}
		}

		// 자바의 람다식을 이용한 다중 조건 정렬
		// (s1, s2) -> 비교할 두개의 데이터(문자열)
		// sort는 문자열 전체를 통쨰로 비교한다. 하지만 특정 부분은 우선순위로 두고 나머지는 보조적으로만 쓰고 싶을때 람다식 형태의 커스텀 정렬이 필요하다.
		// 해당 문제는 내용으로 먼저 정렬하고 내용이 같으면 식별자로 정렬해야하기때문에 람다식을 사용함.
		letter.sort((s1, s2) -> {
			String[] split1 = s1.split(" ", 2);
			String[] split2 = s2.split(" ", 2);

			// 1순위: 식별자를 제외한 나머지 내용(split[1]) 비교
			// compareTo : 문자열을 사전순으로 비교한다. s1이 작으면 음수 같으면 0 , s1이 크면 양수를 반환함. 두 객체의 순서를 비교할때 사용하는 중요한 메서드.
			int compare = split1[1].compareTo(split2[1]);

			// 2순위: 내용이 같다면 식별자(split[0]) 비교
			if (compare == 0) {
				return split1[0].compareTo(split2[0]); // 식별자로 사전순 정렬
			}
			return compare;
		});

		// letter + numbers (리스트 합치기)
		letter.addAll(numbers);
		return letter;
	}

	public static void main(String[] args) {
		ReorderLogFiles app = new ReorderLogFiles();

		List<String> logs = Arrays.asList(
			"dig1 8 1 5 1",
			"let1 art can",
			"dig2 3 6",
			"let2 own kit dig",
			"let3 art zero"
		);

		List<String> result = app.solution(logs);
		System.out.println(result);
		// 출력: [let1 art can, let3 art zero, let2 own kit dig, dig1 8 1 5 1, dig2 3 6]
	}
}