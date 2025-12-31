# 31 ) 상위 K 빈도 요소
import collections
import heapq
from typing import List

def solution( nums : List[int], k : int ) -> List[int]:

    counter = collections.Counter(nums)
    result = []
    heap = []

    # key 추출
    for num in counter:
        heapq.heappush(heap, ( -counter[num] , num))

    for _ in range(len(k)):
        item = heapq.heappop(heap)
        result.append(item[1])

    return result

# 책 풀이
def solution( nums : List[int], k : int ) -> List[int]:

    counter = collections.Counter(nums)
    result = []
    heap = []

    for num in counter:
        heapq.heappush(heap, ( -counter[num] , num))

    for _ in range(k):
        result.append(heapq.heappop(heap)[1])
    return result


if __name__ == "__main__":
    nums = [1,1,1,2,2,3]
    k = 2

    print(solution(nums, k ))