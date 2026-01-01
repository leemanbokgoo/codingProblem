package javaAnswer.dfs;
import java.util.*;
// 33 ) 전화번호 문자 조합
public class LetterCombinationsOfAPhoneNumber {

		public List<String> solution(String numbers) {
			List<String> results = new ArrayList<>();

			// 파이썬과 동일한 phone 맵 구성
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

		private void dfs(int index, String path, String numbers, List<String> results, Map<String, String[]> phone) {
			// 해당 종료 조건 때문에 for i in range( index, len(numbers)):에서 첫번째 for문 외에 나머지 for의 결과 값은 results안에 append안됨.
			// 왜냐면 첫번째 for문만 numbers 길이만큼 path를 만들어낼 수 있기때문임.
			// 그래서 for문이 계속 돌아도 if문 조건때문에 결과값에는 반영 안되며 아래 for문의 range( index, len(numbers)) 조건으로 인해 마지막 단계에서 for문이 못돌아가고 종료됨.
			if (path.length() == numbers.length()) {
				results.add(path);
				return;
			}

			// index를 통해 depth를 더 들어가야한다. 그렇기때문에 numbers의 길이 만큼 만복되어야함.
			// 즉, "다음 선택을 어디서부터(해당 문제에선 index) 시작할지" 결정하여 중복되지 않은 경로로 탐색을 확장하는 부분.
			// for i in range( index, len(numbers)은 일반적인 백트래킹/ DFS 함수의 기본 구조로 핵심 템플릿 부분.
			for (int i = index; i < numbers.length(); i++) {
				// 해당 인덱스에 numbers[i]의 값에 해당되는 phone문자열을 하나씩 반복문을 돌려서 다음 재귀를 호출해 dfs로 다음 depth로 간다.
				String digit = String.valueOf(numbers.charAt(i));
				for (String j : phone.get(digit)) {
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