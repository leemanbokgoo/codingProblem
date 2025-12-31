from typing import List
# 11 ) 자신을 제외한 배열의 곱

# 책의 풀이 힌트를 많이 참고 함.
def solution( nums : List[int]) -> List[int]:

    output = []
    sum = 1

    for i in range(len(nums)):
        output.append(sum)
        sum *=  nums[i]

    sum = 1
    for i in range(len(nums)- 1, 0 - 1, -1 ):
        output[i] = output[i] * sum
        sum *= nums[i]

    return output

# 실행 테스트
if __name__ == "__main__":
    nums = [1,2,3,4]

    print(solution(nums))