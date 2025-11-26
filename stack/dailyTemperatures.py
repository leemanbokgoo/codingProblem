from typing import List
# 22 ) 일일 온도

def solution( T : List[int]) -> List[int]:

    stack = []
    results = [0] * len(T)

    for i, cur in enumerate(T):
        # 반복문을 돌면서 인덱스(i)를 넣어주다가 현재 온도가 T[stack[-1]]보다 크다면, while문 루프 발동
        while stack and cur > T[stack[-1]]:
            # 해당 날짜의 인덱스를 꺼내서 현재 날짜의 인덱스를 빼면 last가 몇일을 기다려야 더 높은 온도가 되는지 알수 있다.
            # while stack 조건을 통해 stack에 여태까지 쌓인 날짜가 cur > T[stack[-1]] 조건을 만족한다면 다른 날짜 인덱스 들도 꺼내서 계산한다.
            last = stack.pop()
            results[last] = i - last
        stack.append(i)

    return results

# 책풀이
def solution( T : List[int]) -> List[int]:

    results = [0] * len(T)
    stack = []

    # 원리는 stack에 인덱스를 저장해나가면서 가장 최근에 저장된 stack의 값이 value보다 작아지면
    # 그때부터 while문을 통해 stack에 있는 인덱스(순서)들을 꺼내 값을 계산해나가면서 results에 값을 저장한다.
    # pop을 통해 가장 최근의 날짜부터 계산해나가며 그렇게 계산이 끝나면 자연스럽게 stack이 비워진다.
    #
    for i , value in enumerate(T):

        # 현재 온도가 stack에 저장한 온도보다 높아지는 순간을 찾는다.
        # while stack이라는 조건이 존재함으로 이 while문이 돌아갈때 하나의 날짜 값만 계산하는게 아니라 stack에 있는 모든 날짜 값을 계산한다.
        while stack and value > T[stack[-1]]:
            last = stack.pop() # 최상단 값을 들고오며 stack에서 해당 값을 제외( 해당값을 계산할 예정임으로 stack에서 제외해줌)
            results[last] = i - last
        # stack에 인딕스를 저장
        stack.append(i)

    return results


if __name__ == "__main__":
    T = [73, 74, 75, 71, 69, 72, 76, 73]
    print(solution(T))