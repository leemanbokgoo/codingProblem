# 34 ) 순열
from typing import List


def solution( numbers: List[int]) -> List[List[int]]:
    results = []
    prev_elements = []

    def dfs(elements):
        # 재귀함수로 계속 남은 요소를 넘겨줄 것임으로 종료조건은 더이상 남은 요소가 없을때
        if len(elements) == 0 :
            results.append(prev_elements)
            return

        # 지금 받아온 숫자들을 차례대로 출력
        for e in elements:
            # prev_elements는 답이 될 배열을 저장해두는 변수다.
            prev_elements.append(e)
            # 다음 재귀에 현재 요소를 제외한 수열 배열을 넘겨줘야함.
            next_elements = elements[:]
            next_elements.remove(e)
            # 재귀 함수 선언
            dfs(next_elements)
            # 방금 위에서 넣어준 현재 요소를 다시 삭제해줘야 다음 재귀에 사용가능.
            # 왜냐하면 [1]ㅇ ㅣ들어있는 상황에서 [1,2] , [1,3] 이런식으로 들어가야하니까 pop을 통해 2는 뺴고 for문에서 3을 넣어주는 것
            prev_elements.pop()

    dfs(numbers)
    return results

def solution( numbers : List[int] ) -> List[List[int]] :

    results = []
    prev_elements = []

    def dfs( elements ):

        if len(elements) == 0 :
            results.append(prev_elements[:])
            return

        for number in elements:
            next_element = elements[:]
            next_element.remove(number)

            prev_elements.append(number)
            dfs(next_element)
            prev_elements.pop()

    results = []
    dfs(numbers)

    return results






















def solution( numbers : List[int]) -> List[List[int]]:
    results = []
    prev_elements = []

    def dfs( elements ):
        if len(elements) == 0:
            results.append(prev_elements[:])

        for e in elements:
            next_elements = elements[:]
            next_elements.remove(e)

            prev_elements.append(e)
            dfs(next_elements)
            prev_elements.pop()

    dfs(numbers)
    return results
