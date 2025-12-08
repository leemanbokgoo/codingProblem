from typing import List
# 35 ) 조합

# n 전체수 k 개의 조합
def solution( n : int , k : int ):

    results = []

    def dfs( elements , start , k) :

        if k == 0 :
            results.append(elements[:])
            return

        for i in range(start, n + 1):
            elements.append(i)
            # i + 1 해야하는 이유는 다음 depth로 넘어가기 위함.
            # k -1 : 는 k 만큼의 배열을 만들어야하는데 현재 i를 더해서 자리 하나가 찼으니까 -1
            # elements -> 현재까지 들어온 깊이만큼의 요소를 더한 배열
            dfs( elements , i + 1 , k - 1)
            # 재귀함수가 끝났으면 pop해서 요소를 지움 즉,다시 되돌아오는 것
            elements.pop()

    dfs([], 1 , k)
    return results


def solution2( n : int, k : int ) -> List[int]:

    results = []

    def dfs( elements, start : int, k : int ):
        # 종료 조건
        if k == 0 :
            results.append(elements[:])
            return

        for i in range(start, n + 1):
            elements.append(i)
            dfs(elements, i + 1, k - 1)
            elements.pop()

    dfs([], 1 , k )
    return results