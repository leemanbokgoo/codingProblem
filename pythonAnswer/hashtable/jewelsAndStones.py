# 29 ) 보석과 돌
import collections

# 내 풀이
def solution( J : str, S : str ) -> int:
    table = collections.defaultdict(int)
    count = 0

    for char in J :
        table[char] += 1

    for char in S:
        if table[char] > 0 :
            count += 1
    return count

# 책 풀이
def solution2(  J : str, S : str ) -> int:
    freqs = collections.defaultdict(int)
    count = 0

    for char in J:
        freqs[char] += 1

    for char in S:
        count += freqs[char]
    return count

if __name__ == "__main__":
    S = "aAAbbbb"
    J = "aA"
    print(solution(J, S))