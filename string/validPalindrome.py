# 01 유효한 팰린드롬


# 풀이 ( 두번째 )
# 처음 풀이는 소문자로 변경,''없애기 등의 전처리 후 문자열을 뒤집었음. 그리고 나서 입력 문자열과 비슷한지 확인했으나
# 기호(:,)등으로 인해 제대로 동작하지않음. 이후로 힌트보고 다시 품.
# pop()와 같은 함수랑 char.isalnum() 등의 파이썬 기능들을 잘 몰라서 생기는 문제가 발생하고있음. 파이썬 함수에 대해 좀 더 알아갈 것
# 참고로 슬라이싱으로 풀수 있으나 이는 너무 간단한 방법이라..알고리즘이 아니라 사용하지않음. re.sub('[^a-Z0-9]', '' , s) 하고 [::-1]하면 된다.
# 정규식과 슬라이싱을 쓰는 방법에 대해서도 능숙해져야함.
def solution( s : str) -> bool:

    # 배열로 만들기
    strs = []

    for char in s:
        if char.isalnum(): # 문자와 숫자인 경우만 필터링
            # 대소문자 구별 안함으로 소문자로 통일
            strs.append(char.lower())

    # 중앙값을 기준으로 오른쪽과 왼쪽은 같음.
    # 그러니까 중앙값이 남을떄까지 오른쪽과 왼쪽을 없앤다.
    # str에 값이 있을때까지 pop
    while len(str) > 1:
        if strs[0] == strs[-1]:
            strs.pop()
            strs.pop(0)
        else :
            return False
    return True

# 책의 풀이
def answer( s : str) -> bool:
    strs = []
    for char in s:
        if char.isalnum():
            strs.append(char.lower())

    while len(str) > 1:
        if strs.pop() != strs.pop(0):
            return False
    return True

def solution( s : str) -> bool:

    # 배열로 만들기
    str = []

    for char in s:
        if char.isalnum(): # 문자와 숫자인 경우만 필터링
            # 대소문자 구별 안함으로 소문자로 통일
            str.append(char.lower())

    # 중앙값을 기준으로 오른쪽과 왼쪽은 같음.
    # 그러니까 중앙값이 남을떄까지 오른쪽과 왼쪽을 없앤다.
    # str에 값이 있을때까지 pop
    while len(str) > 1:
        if str[0] == str[-1]:
            str.pop()
            str.pop(0)
        else :
            return False

    return True

# 실행 테스트
if __name__ == "__main__":
    print(solution("A man, a plan, a canal: Panama"))  # True
    print(solution( "race a car"))




