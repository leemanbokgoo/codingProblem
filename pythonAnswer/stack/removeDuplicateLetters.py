import collections
# 21 ) 중복 문자 제거

def solution( s : str ) -> str :

    count, stack, seen = collections.Counter(s), [], set()

    for char in s:
        count[s] -= 1

        if char in seen:
            continue

        if stack and char < stack[-1] and count[stack[-1]] > 0 :
            seen.remove(stack.pop())

        stack.append(char)
        seen.add(char)

    return ''.join(stack)


# 책 풀이
def solution( s : str ) -> str :

    counter, stack , seen = collections.Counter(s), [] , set()
    for char in s:
        counter[char] -= 1

        if char in seen:
            continue

        while stack and char < stack[-1] and counter[stack[-1]] > 0 :
            seen.remove(stack.pop())
        stack.append(char)
        seen.add(char)

    return ''.join(stack)



if __name__ == "__main__":
    s = "bcabc"
    print(solution(s))