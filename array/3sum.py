from typing import List

# 09 ) 세수의 합

# 내 풀이. 투포인터로 3개의 요소를 어떻게 더하는지 모르겠어서 책에서 힌트 참고 함.
def solution( nums : List[int]) -> List[List[int]]:

    # 투포인터는 정렬이 반드시 필요.
    nums.sort()
    results = []

    for i in range(len(nums) - 2):

        if i > 0 and nums[i] == nums[i - 1]:
            continue

        left , right = i + 1 , len(nums) - 1
        while left < right :
            sum = nums[i] + nums[left] + nums[right]
            if sum < 0 :
                left += 1
            elif sum > 0 :
                right -= 1
            else:
                results.append([ nums[i], nums[left], nums[right] ])

                # 내 풀이에는 중복된 left/right 값을 건너뛰는 부분이 없었음.
                while left < right and nums[left] == nums[left + 1]:
                    left += 1
                while left < right and nums[right] == nums[right - 1]:
                    right -= 1
                ### 이 부분이 빠짐.

                left += 1
                right -= 1

    return results

# 실행 테스트
if __name__ == "__main__":

    nums = [ -1, 0 , 1, 2, -1, -4 ]

    print(solution(nums))