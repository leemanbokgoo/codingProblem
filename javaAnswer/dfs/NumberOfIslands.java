package javaAnswer.dfs;

// 32 ) 섬의 개수

// 현재 노드의 오른쪽, 아래 , 위 , 왼쪽이 전부 0이면 섬이 아님. 1이 하나라도 있으면 섬으로 체크..
// 다만..어떻게...하나의 섬이 끝난 걸 알지..??
// 일단 1을 만나면 그때부터 주변 탐색 계속 재귀로 조회하다가 0을 만나면 종료...??
// 아하 전체 섬을 방문 처리 해서 0으로 바꾸면 된다.
public class NumberOfIslands {

	public int solution(char[][] gird) {

		// 내부 dfs를 위한 별도 호출 (자바는 메서드 안에 메서드를 정의할 수 없으므로)
		int count = 0;

		for (int i = 0; i < gird.length; i++) {
			for (int j = 0; j < gird[0].length; j++) {
				if (gird[i][j] == '1') {
					dfs1(i, j, gird);
					count += 1;
				}
			}
		}

		return count;
	}

	private void dfs1(int i, int j, char[][] gird) {
		if (i < 0 || i >= gird.length || j < 0 || j >= gird[0].length || gird[i][j] != '1') {
			return;
		}

		// 방문한 섬은 0 으로 표기하여 방문 처리
		// 원본 파이썬 코드에서는 gird[i][j] = 0 이었으나, char 배열이므로 '0'으로 처리합니다.
		gird[i][j] = '0';

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
		// 파이썬 원본의 grid[i][j] != 1 조건을 자바의 char 환경에 맞춰 '1'이 아닐 때로 처리
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