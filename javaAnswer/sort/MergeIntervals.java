package javaAnswer.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 59 ) 구간 병합
 */
public class MergeIntervals {

	public int[][] solution(int[][] intervals) {
		// 1. 시작 시간을 기준으로 오름차순 정렬
		// (a, b) -> Integer.compare(a[0], b[0])는 a[0]과 b[0]을 비교함
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

		// 병합된 결과를 담을 리스트 (크기를 알 수 없으므로 우선 List 사용)
		List<int[]> merged = new ArrayList<>();

		for (int[] interval : intervals) {
			// 결과 리스트가 비어있거나, 현재 구간이 이전 구간과 겹치지 않는 경우
			// (이전 구간의 종료 시간 < 현재 구간의 시작 시간)
			// 			// 예외 상황(새 데이터 추가)"을 먼저 털어버려야, 나머지 메인 로직(병합)이 단순해지기때문에 먼저 if문으로 걸러내기
			if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
				merged.add(interval);
			}
			// 겹치는 경우
			else {
				// 이전 구간의 종료 시간을 더 큰 값으로 업데이트
				merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
			}
		}

		// List를 다시 2차원 배열로 변환하여 반환
		return merged.toArray(new int[merged.size()][]);
	}

	public static void main(String[] args) {

		MergeIntervals app = new MergeIntervals();

		int[][] times = new int[][]{ {1, 3}, {2, 6}, {8, 10}, {15, 18} };
		System.out.println(Arrays.deepToString(app.solution(times)));
	}
}