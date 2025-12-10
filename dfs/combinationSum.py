from typing import List

from sqlalchemy.sql.base import elements


# 36 ) 조합의 합
# target값이 되도록 더하는 것보다 target값에 빼는 방식을 고려해봐야함.
def solution( candidates : List[int] , target : int) -> List[List[int]]:

    results = []

    def dfs( csum, index, path):
        # 종료 조건
        if csum == 0 :
            results.append(path)
            return
        # cusm은 target보다 클수가 없음. 왜냐하면 밑에서 csum - candidates[i]해서 계속 값을 빼고있기때문에 csum이 target보다 크다면 바로 종료 필요
        if csum > target :
            return

        for i in range(index, len(candidates)):
            dfs( csum - candidates[i],  i , path + [candidates[i]] )

    dfs(target, 0, [])

    return results

# 책풀이
def solution_book( candidates : List[int], target : int) -> List[List[int]] :

    results = []

    def dfs( csum, index, path ):

        if csum == 0:
            results.append(path)
            return

        if csum > target:
            return

        for i in range(index, len(candidates)):
            dfs(csum - candidates[i], i, path + [candidates[i]] )

    dfs(target, 0 , [])

    return results