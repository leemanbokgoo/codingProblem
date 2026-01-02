package javaAnswer.string;

import java.util.*;

// 03 ) 로그 파일 재정렬

// 힌트 : 람다식을 사용해야함.
public class ReorderLogFiles {

	public List<String> solution(List<String> logFiles) {
		List<String> letter = new ArrayList<>();
		List<String> numbers = new ArrayList<>();

		for (String log : logFiles) {
			// log.split()[1] 에 해당하는 부분 확인
			// split(" ", 2)는 첫 번째 공백에서만 쪼개서 [식별자, 나머지 내용] 배열을 만든다.
			String[] split = log.split(" ", 2);

			// log.split()[1].isdigit() 체크
			if (Character.isDigit(split[1].charAt(0))) {
				numbers.add(log);
			} else {
				letter.add(log);
			}
		}

		// letter.sort( key = lambda x : (x.split()[1:], x.split()[0]))
		// 자바의 람다식을 이용한 다중 조건 정렬
		letter.sort((s1, s2) -> {
			String[] split1 = s1.split(" ", 2);
			String[] split2 = s2.split(" ", 2);

			// 1순위: 식별자를 제외한 나머지 내용(split[1]) 비교
			int compare = split1[1].compareTo(split2[1]);

			// 2순위: 내용이 같다면 식별자(split[0]) 비교
			if (compare == 0) {
				return split1[0].compareTo(split2[0]);
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