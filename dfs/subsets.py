from typing import List
# 37 ) 부분 집합

def solution( nums : List[int]) -> List[List[int]]:

    results = []

    def dfs( index, path):
        # 모든 경우를 저장해야함으로 종료 조건 없이 값을 전부 저장.
        # 부분 집합은 모든 탐색의 경로가 정답이 되기떄문에
        results.append(path)

        # i +1 하는 이유는 index를 1씩 증가하느 ㄴ형태로 깊이 탐색할 것이기때문.
        for i in range(index, len(nums)):
            dfs( i + 1 , path + [ nums[i] ] )
    dfs(0, [])
    return results


def solution_book(nums : List[int]) -> List[List[int]]:
    results = []

    def dfs( index, path):
        results.append(path)
        for i in range(index, len(nums)):
            dfs( i + 1, path + [nums[i]])

    dfs(0,[])
    return results