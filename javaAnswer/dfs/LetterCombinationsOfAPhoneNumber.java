package javaAnswer.dfs;
import java.util.*;

/**
 * 33 ) 전화번호 문자 조합
 */

public class LetterCombinationsOfAPhoneNumber {

		public List<String> solution(String numbers) {
			List<String> results = new ArrayList<>();

			// phone 번호를 Map 자료구조로 구현
			Map<String, String[]> phone = new HashMap<>();
			phone.put("2", new String[]{"a", "b", "c"});
			phone.put("3", new String[]{"d", "e", "f"});
			phone.put("4", new String[]{"g", "h", "i"});
			phone.put("5", new String[]{"j", "k", "l"});
			phone.put("6", new String[]{"m", "n", "o"});
			phone.put("7", new String[]{"p", "q", "r", "s"});
			phone.put("8", new String[]{"t", "u", "v"});
			phone.put("9", new String[]{"w", "x", "y", "z"});
			phone.put("0", new String[]{"+"});

			// 내부 dfs 함수 호출
			dfs(0, "", numbers, results, phone);

			return results;
		}

		// 참고 : 여기서는 정답을 만들어 가는 변수 path를 String으로 저장함. String은 불변 객체임으로 매번 새로 문자열을 생성하기때문에 백트래킹 과정 없어도
		// 재귀가 끝나고 현재 단계로 돌아왔을때 원래의 상태를 그대로 유지하고있음. 고로 StringBuilder나 배열을 사용했을때와 달리 (얘네들은 값을 참조함으로) 백트래킹 과정이 필요 없음.
		private void dfs(int index, String path, String numbers, List<String> results, Map<String, String[]> phone) {

			// 종료 조건
			// 현재까지 만든 문자열 path의 길이가 입력받은 숫자 numbers의 길이와 같다면 원하는 길이의 조합이 완성된 것이므로 결과 리스트에 추가하고 재귀를 종료한다.
			if (path.length() == numbers.length()) {
				results.add(path);
				return;
			}

			// i가 index부터 시작하는 이유는 이미 처리한 숫자는 건너뛰고 다음 숫자로 넘어가기 위함임. 순서를 고정함.
			// 만약 23이 주어졌다면 2에서 문자를 고르고 3에서 문자를 골라야하기때문에 순서를 지켜야한다. 그렇기때문에 i가 index부터 시작해야함.
			for (int i = index; i < numbers.length(); i++) {
				// 현재 순서에 해당하는 숫자를 가져온다.
				String digit = String.valueOf(numbers.charAt(i));

				// 숫자에 할당된 문자 ( ex "a", "b", "c")을 하나씩 확인한다.
				for (String j : phone.get(digit)) {
					// 현재 문자 j를 기존 경로 path에 추가하고 다음 숫자(i+1)를 처리하기위해 깊이 우선 탐색 DFS를 이어감.
					dfs(i + 1, path + j, numbers, results, phone);
				}
			}
		}

	// 책 풀이
	public List<String> solution2(String numbers) {
		if (numbers == null || numbers.length() == 0) {
			return new ArrayList<>();
		}

		Map<String, String[]> phone = new HashMap<>();
		phone.put("2", new String[]{"a", "b", "c"});
		phone.put("3", new String[]{"d", "e", "f"});
		phone.put("4", new String[]{"g", "h", "i"});
		phone.put("5", new String[]{"j", "k", "l"});
		phone.put("6", new String[]{"m", "n", "o"});
		phone.put("7", new String[]{"p", "q", "r", "s"});
		phone.put("8", new String[]{"t", "u", "v"});
		phone.put("9", new String[]{"w", "x", "y", "z"});
		phone.put("0", new String[]{"+"});

		List<String> result = new ArrayList<>();

		// 내부 dfs 호출
		dfs2(0, "", numbers, result, phone);

		return result;
	}

	private void dfs2(int index, String path, String numbers, List<String> result, Map<String, String[]> phone) {
		// 종료 조건
		if (path.length() == numbers.length()) {
			result.add(path);
			return;
		}

		for (int i = index; i < numbers.length(); i++) {
			String digit = String.valueOf(numbers.charAt(i));
			for (String j : phone.get(digit)) {
				dfs2(i + 1, path + j, numbers, result, phone);
			}
		}
	}

	public static void main(String[] args) {
		LetterCombinationsOfAPhoneNumber sol = new LetterCombinationsOfAPhoneNumber();
		System.out.println("solution: " + sol.solution("23"));
		System.out.println("solution2: " + sol.solution2("23"));
	}
}