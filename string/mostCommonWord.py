import collections
import re
from typing import List
# 04 가장흔한 단어

# 풀이
# Counter()를 쓰면 되는 문제이지만 쓰지않고 해결해본다.
def solution( paragraph : str, banned : List[str]) -> str:

    paragraphArr = paragraph.split()
    wordCountList = collections.defaultdict(int)

    for word in paragraphArr:
        word = word.lower()
        # [^\w] 단어가 아닌 모든 문자
        word = re.sub(r'[^\w]', '', word)

        if word and word not in banned :
            wordCountList[word] += 1

    maxWord = max( wordCountList, key= wordCountList.get)
    return maxWord

# counter, 리스트 컴프리 헨션을 사용한 책의 풀이
def solution_counter( paragraph : str, banned : List[str]) -> str:
    words = [word for word in re.sub(r'[^\w]', ' ', paragraph).lower().split() if word not in banned]

    # 리스트 컴프리 헨션을 풀어 쓰면 이렇다
    # words = []
    # for word in re.sub(r'[^\w]', ' ', paragraph).lower().split():
    #     if word not in banned:
    #         words.append(word)

    counts = collections.Counter(words)
    return counts.most_common(1)[0][0]


# 실행 테스트
if __name__ == "__main__":
    banned = ["hit"]
    print(solution("Bob hit a ball, the hit BALL flew far after is was hit",  banned)) # ball
    print(solution_counter("Bob hit a ball, the hit BALL flew far after is was hit",  banned)) # ball








