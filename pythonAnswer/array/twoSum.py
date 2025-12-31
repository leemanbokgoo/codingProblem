from typing import List
# 07 ) 두수의 합

# 내 풀이
# 이 문제는 투 포인터로 해결 하면 된다고 생각했음.
def solution( nums : List[int], target : int) -> List[int]:

        left, right = 0 , len(nums) - 1
        results = []

        while left < right:

            if nums[left] + nums[right] < target:
                left += 1

            elif nums[left] + nums[right] > target:
                right -= 1

            else :
                results.append(left)
                results.append(right)
                break

        return results


# 책의 풀이
def solution2(nums: List[int], target: int) -> List[int]:
    left, right = 0, len(nums) - 1

    while not left == right :

        if nums[left] + nums[right] < target:
            left += 1
        elif nums[left] + nums[right] > target:
            right -= 1

        else:
            return [left, right]

# 실행 테스트
if __name__ == "__main__":
    nums = [ 2, 7, 11, 15]
    target = 9
    print(solution(nums, target))


