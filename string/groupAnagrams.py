import collections
from typing import List
# 05 ) 그룹 에너 그럼

# 힌트 : 애너그럼은 정렬하면 똑같은 형태가 된다.
def solution( strs : List[str] ) -> List[List[str]] :

    anagrams = collections.defaultdict(list)

    for word in strs:
        sortedWord = ''.join(sorted(word))
        anagrams[sortedWord].append(word)

    return list(anagrams.values())

# 실행 테스트
if __name__ == "__main__":
    print(solution(["eat", "tea", "tan", "ate", "nat", "bat"]))




