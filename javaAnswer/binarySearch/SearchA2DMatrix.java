package javaAnswer.binarySearch;

/**
 *  69 ) 2D 행렬 검색 2
 */
public class SearchA2DMatrix {

	public boolean solution(int[][] matrix, int target){
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}

		// 1. 오른쪽 위 끝에서 시작 (첫 행, 마지막 열)
		// 각 행은 왼쪽에서 오른쪽으로 오름차순 정렬, 각 열은 위에서 아래로 오름 차순 정렬.
		// 오른쪽 위에서 시작한다. right-top(첫번째 행의 마지막 열)에서 시작하면 이 숫자보다 작은 값은 무조건 왼쪽, 큰 값은 무조껀 아래쪽에 존재한다.
		// 그렇기때문에 이 지점을 mid 로 보고 범위를 좁혀간다.
		int row = 0;
		int col = matrix[0].length - 1;

		// 2. 행렬의 범위를 벗어나지 않는 동안 반복
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] == target) {
				return true;

			} else if (matrix[row][col] > target) {
				// 현재 값이 타겟보다 크면 이 열(column) 아래쪽은 다 더 큼.
				// 따라서 왼쪽 열로 이동
				col--;
			} else {
				// 현재 값이 타겟보다 작으면 이 행(row) 왼쪽은 다 더 작다.
				// 따라서 아래쪽 행으로 이동
				row++;
			}
		}

		return false; // 범위를 벗어날 때까지 못 찾으면 없음
	}

	public static void main(String[] args) {

		SearchA2DMatrix app = new SearchA2DMatrix();
		int[][] matrix = {
			{1, 4, 7, 11, 15},
			{2, 5, 8, 12, 19},
			{3, 6, 9, 16, 22},
			{10, 13, 14, 17, 24},
			{18, 21, 23, 26, 30}
		};
		int target = 5;

		System.out.println(app.solution(matrix,target ));
	}
}
