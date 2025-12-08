from typing import List
# 33 ) 전화번호 문자 조합

def solution( numbers : str) -> List[str]:

    results = []

    def dfs( index, path):

        # 해당 종료 조건 때문에 for i in range( index, len(numbers)):에서 첫번째 for문 외에 나머지 for의 결과 값은 results안에 append안됨.
        # 왜냐면 첫번째 for문만 numbers 길이만큼 path를 만들어낼 수 있기때문임.
        # 그래서 for문이 계속 돌아도 if문 조건때문에 결과값에는 반영 안되며 아래 for문의 range( index, len(numbers)) 조건으로 인해 마지막 단계에서 for문이 못돌아가고 종료됨.
        if len(path) == len(numbers):
            results.append(path)
            return

        # index를 통해 depth를 더 들어가야한다. 그렇기때문에 numbers의 길이 만큼 만복되어야함.
        # 즉, "다음 선택을 어디서부터(해당 문제에선 index) 시작할지" 결정하여 중복되지 않은 경로로 탐색을 확장하는 부분.
        # for i in range( index, len(numbers)은 일반적인 백트래킹/ DFS 함수의 기본 구조로 핵심 템플릿 부분.
        for i in range( index, len(numbers)):
            # 해당 인덱스에 numbers[i]의 값에 해당되는 phone문자열을 하나씩 반복문을 돌려서 다음 재귀를 호출해 dfs로 다음 depth로 간다.
            for j in phone[numbers[i]]:
                dfs( i + 1 , path + j )

    phone = {
        "2" : ["a", "b", "c"],
        "3": ["d", "e", "f"],
        "4": ["g", "h", "i"],
        "5": ["j", "k", "l"],
        "6": ["m", "n", "o"],
        "7": ["p", "q", "r", "s"],
        "8": ["t", "u", "v"],
        "9": ["w", "x", "y", "z"],
        "0": ["+"]
    }

    dfs(0,"")

    return results

# 책 풀이
def solution2( numbers : str ) -> List[str]:
    def dfs(index, path):
        # 종료 조건
        if len(path) == len(numbers):
            result.append(path)
            return

        for i in range(index, len(numbers)):
            for j in phone[numbers[i]]:
                dfs(i + 1, path + j )

    if not numbers:
        return []

    phone = {
        "2" : ["a", "b", "c"],
        "3": ["d", "e", "f"],
        "4": ["g", "h", "i"],
        "5": ["j", "k", "l"],
        "6": ["m", "n", "o"],
        "7": ["p", "q", "r", "s"],
        "8": ["t", "u", "v"],
        "9": ["w", "x", "y", "z"],
        "0": ["+"]
    }
    result = []
    dfs(0 , "")

    return result

