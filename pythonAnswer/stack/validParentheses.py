# 20 ) 유효한 괄호
def solution( s : str ) -> bool:
    stack = []
    table = { "(": ")", "{" : "}", "[" : "]" }

    for item in s :
        # key가 들어오면 stack에 넣어줌
        if item in table.keys():
            stack.append(item)
            continue

        # value가 존재하는데 만약 stack에 값이 없다면 key값이 없다는 것임으로 value만 존재함으로 자동 False
        # stack에 값이 있는 상황에서 value가 들어오면 stack에 넣은 key와 한쌍인지 확인해줌
        if not stack or table[stack.pop()] != item:
            return False

    # 저렇게 모든 문자열을 한번씩 체크 했음에도 stack이 아직 남아있다면 짝이 맞지않는 것임으로
    if stack :
        return False
    else :
        return True

# 책 풀이
def solution2( s : str ) -> bool:
    stack = []
    table = {"(": ")", "{": "}", "[": "]"}

    for char in s :
        if char not in table:
            stack.append(char)

        elif not stack or table[char] != stack.pop():
            return False

    return len(stack) == 0 

if __name__ == "__main__":
    s = "()[]{}"

    print(solution(s))