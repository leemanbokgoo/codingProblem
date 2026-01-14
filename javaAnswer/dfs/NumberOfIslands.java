package javaAnswer.dfs;

/**
 *  32 ) 섬의 개수
 *  현재 노드의 오른쪽, 아래 , 위 , 왼쪽이 전부 0이면 섬이 아님. 1이 하나라도 있으면 섬으로 체크.
 *  1을 처음 만났을때 연결된 1들을 돌면서 0으로 다 바꿔줘야 중복 카운팅이 안됨. 전부 0으로 바꿔서 방문 처리하고 섬 갯수를 카운팅하면 된다.
 *
 */

public class NumberOfIslands {

	public int solution(char[][] gird) {

		int count = 0; // 섬의 갯수 카운팅할 변수

		// 각 노드마다 호출해서 섬의 여부를 확인해야함.
		// 열과 행이 존재함으로 이중 for문 사용
		for (int i = 0; i < gird.length; i++) {
			for (int j = 0; j < gird[0].length; j++) {

				// 해당 노드가 1이면 섬이기때문에 재귀호출
				if (gird[i][j] == '1') {
					// 재귀 호출을 통해 해당 섬을 탐색하며 방문표기
					dfs1(i, j, gird);
					// 방문 표기가 완료되면 카운팅
					count += 1;
				}
			}
		}

		return count;
	}

	// 재귀 호출
	private void dfs1(int i, int j, char[][] gird) {

		// 종료 조건
		// i와 j가 범위를 벗어나지않고 해당 노드가 더이상 '1'이 아니면 종료
		if (i < 0 || i >= gird.length || j < 0 || j >= gird[0].length || gird[i][j] != '1') {
			return;
		}

		// 방문한 섬은 0 으로 표기하여 방문 처리
		gird[i][j] = '0';

		// 현재 노드의 위,아래,오른쪽,왼쪽 순회
		dfs1(i + 1, j, gird);
		dfs1(i - 1, j, gird);
		dfs1(i, j + 1, gird);
		dfs1(i, j - 1, gird);
	}

	// 책풀이
	public int solution2(char[][] grid) {

		int count = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					dfs2(i, j, grid);
					count += 1;
				}
			}
		}

		return count;
	}

	private void dfs2(int i, int j, char[][] grid) {

		// 종료 조건
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
			return;
		}

		grid[i][j] = '0';
		// 재귀 호출
		dfs2(i + 1, j, grid);      // 오른쪽
		dfs2(i - 1, j + 1, grid);  // 왼쪽 (원본 파이썬 코드의 특이한 좌표 이동 그대로 유지)
		dfs2(i, j + 1, grid);      // 위
		dfs2(i, j - 1, grid);      // 아래
	}

	public static void main(String[] args) {
		NumberOfIslands sol = new NumberOfIslands();

		// 테스트 데이터 1 (solution용)
		char[][] grid1 = {
			{'1', '1', '1', '1', '0'},
			{'1', '1', '0', '1', '0'},
			{'1', '1', '0', '0', '0'},
			{'0', '0', '0', '0', '0'}
		};

		// 테스트 데이터 2 (solution2용)
		char[][] grid2 = {
			{'1', '1', '0', '0', '0'},
			{'1', '1', '0', '0', '0'},
			{'0', '0', '1', '0', '0'},
			{'0', '0', '0', '1', '1'}
		};

		System.out.println("solution 결과: " + sol.solution(grid1));
		System.out.println("solution2 결과: " + sol.solution2(grid2));
	}
}