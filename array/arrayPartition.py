from typing import List

# 00 ) 배열 파티션 1

def solution( nums : List[int]) -> List[List[int]]:

    nums.sort()
    array = []
    sum = 0

    for num in nums:
        array.append(num)
        if len(array) == 2:
            sum += min(array[0], array[1]) # min(array)
            array.clear() # array = []
    return sum

# 책에서 힌트 보고 푼 풀이
def solution2( nums : List[int]) -> List[List[int]]:

    nums.sort()
    sum =0

    for i, value in enumerate(nums):

        if i % 2 == 0 :
            sum += value

    return sum


# 실행 테스트
if __name__ == "__main__":
    nums = [1,4,3,2]
    print(solution(nums))
    print(solution2(nums))
