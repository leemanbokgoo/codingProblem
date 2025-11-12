# 02 ) 문자열 뒤집기
from typing import List

# 풀이
# 배열의 값을 바꿔치기 해서 바꾸려고 했음.
# 완번 백퍼센트 혼자 힘으로 풀지 못하고 어떻게 구현할지 검색이 필요했음.
# 아직 파이썬 문법에 익숙하지않아서 발생한 문제.
def solution(arr: List[str]) -> List[str]:
    for i in range(len(arr)//2):
        # “끝에서 첫 번째”를 -1로 맞추려면 -(i+1)을 해야함. i이 0일때 -0이 되면 작동 안함.
        arr[i], arr[-(i+1)] = arr[-(i+1)], arr[i]
    return arr

# 힌트를 보고 푼 풀이 : 슬라이딩 윈도우
# 풀이가 틀린 건 아니지만 for _ in range(half): -> while left < right 가 좀 더 정석적인 슬라이딩 윈도우 풀이 같음.
# 다음에는
def solution_sliding_window(arr: List[str]) -> List[str]:

    left = 0
    right = len(arr) - 1
    half = len(arr) // 2

    # 반만 돌아야함. 전체 돌면 다시 원상복귀됨.
    for _ in range(half):
        arr[left], arr[right] = arr[right] , arr[left]
        left += 1
        right -= 1

    return arr



# 실행 테스트
if __name__ == "__main__":
    print(solution(["h","e","l","l","0"]))
    print(solution_sliding_window(["h","e","l","l","0"]))
    print("===============================")
    print(solution(["H","a","n","n","a", "h"]))
    print(solution_sliding_window(["H","a","n","n","a", "h"]))

