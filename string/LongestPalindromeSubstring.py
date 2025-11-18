# 06 ) 가장 긴 펠림드롬 부분 문자열

# 내 풀이
# 힌트 : 처음 문자열부터 시작해서 윈도우를 확장해나가며 검사해야함.
#  책의 풀이와 비교했을때 비효율적이다. 중첩된 반복문과 문자열 슬라이싱 및 역순 비교 때문에 시간 복잡도가 O(N^3)임.
def solution( s : str ) -> str:

    lenght = len(s)
    maxPalindrome = ""

    for i in range(len(s)):
        left = i
        right = lenght - 1

        while left < right :
            window = s[ left : right + 1]
            if window == window[::-1] :
                maxPalindrome = max(maxPalindrome, window, key=len)
            right -= 1

    return maxPalindrome

# 책 풀이 시간복잡도 O(N^2)
def solution2(s : str ) -> str:

    def expand(left : int, right : int) -> str:
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1

        return s[left + 1 : right]

    if len(s) < 2 or s == s[::-1]:
        return s

    reuslt = ""

    for i in range(len(s) - 1):
        result = max(result,
                     expand(i , i + 1), # 홀수
                     expand(i , i + 2), # 짝수
                     key= len )

    return result


# 실행 테스트
if __name__ == "__main__":
    print(solution("babd"))
    print(solution("cbbd"))





