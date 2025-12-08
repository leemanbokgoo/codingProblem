from typing import List
# 32 ) 섬의 개수

# 현재 노드의 오른쪽, 아래 , 위 , 왼쪽이 전부 0이면 섬이 아님. 1이 하나라도 있으면 섬으로 체크..
# 다만..어떻게...하나의 섬이 끝난 걸 알지..??
# 일단 1을 만나면 그때부터 주변 탐색 계속 재귀로 조회하다가 0을 만나면 종료...??
# 아하 전체 섬을 방문 처리 해서 0으로 바꾸면 된다.
def solution( gird : List[List[int]]) -> int:

    def dfs( i , j ):
        if i < 0 or i >= len(gird) or j < 0 or j >= len(gird[0]) or gird[i][j] == '1':
            return

        # 방문한 섬은 0 으로 표기하여 방문 처리
        gird[i][j] = 0

        dfs( i + 1 , j)
        dfs( i - 1 , j)
        dfs( i , j + 1)
        dfs( i , j - 1)

    count = 0

    for i in range(len(gird)):
        for j in range(len(gird[0])):
            if gird[i][j] == '1':
                dfs( i, j )
                count += 1

    return count

# 책풀이
def solution2( grid : List[List[int]]) -> int:

    def dfs(i , j ) :
        # 종료 조건
        if i < 0 or i >= len(grid) or j < 0 or j >= len(grid[0]) or grid[i][j] != 1 :
            return

        grid[i][j] = 0
        # 재귀 호출
        dfs(i + 1 , j) # 오른쪽
        dfs(i - 1 , j + 1) # 왼쪽
        dfs(i, j + 1) # 위
        dfs(i, j - 1) # 아래

    count = 0

    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] == '1':
                dfs(i, j)
                count += 1

    return count