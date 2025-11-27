# 30 ) 중복 문자 없는 가장 긴 부분 문자열

def solution( s : str ) -> int :

    stack = {}
    start = max_length = 0

    for i, char in enumerate(s):
        if stack and start < stack[char] :
            start = i + 1

        else :
            max_length = max( max_length, i - start + 1 )

        stack[char] = i

    return max_length

# 책 풀이
def solution2( s : str ) -> int :
    used = {}
    max_length = start = 0

    for index, char in enumerate(s):

        if char in used and start <= used[char] :
            start = used[char] + 1

        else :
            max_length = max(max_length, index - start + 1)

        used[char] = index

    return max_length

if __name__ == "__main__":
    s1 = "abcabcbb"
    s2 = "bbbbb"
    s3 = "pwwkew"

    print(solution(s1))
    print(solution(s2))
    print(solution(s3))

