# 39 코스 스케쥴
import collections
from typing import List

# 풀이
# 책 풀이 참고해서 해당 코스로 시작했을때 순환하는 부분이 존재하는지 확인하면 됨. 예를 들어 1->2->1 이런식으로 돌아온다면 이건 안됨.
# 주어지는 값을 전부 쓰지않고 풀이 가능한것도 생각해봐야할 부분인듯.
def solution( n : int, course : List[int]) -> bool:
    graph = collections.defaultdict(list)
    for x, y in course:
        graph[x].append(y)

    # 순환하는 걸 확인하려면 현재 경로를 기록해야함.(똑같은 게 또 나오면 중단)
    traced = set()
    # a -> d , d-> a로 가는 경로는 역순일뿐 같다. a->d가 순환이아니면 d->a도 역순아님.
    # 고로 가지치기 가능
    visited = set()

    def dfs( e ):

        if e in traced:
            return False

        if e in visited:
            return False

        # 현재 경로 기록
        traced.add(e)

        # e가 의존하고 있는 코스들(y)을 꺼냄
        for y in graph[e]:
            if not dfs(y):
                return False

        # 그리고 방문했다는 표시로 현재 코스 visited에 표기
        visited.add(e)
        # traced는 현재 경로를 추적하는 배열임으로 추적이 끝나면 삭제
        traced.remove(e)
        return True


    # course에 있는 요소들 다 list로 만들어줌.
    for x in list(course):
        # 만약 x를 탐색했는데 false가 리턴된다면 순환 발생
        if not dfs(x):
            return False

    return True


def solution_book( n : int, prerequisites : List[int]) -> bool:
    graph = collections.defaultdict(list)

    for x, y in prerequisites:
        graph[x].append(y)

    traced = set()
    visited = set()

    def dfs(i):

        if i in traced:
            return False

        if i in visited:
            return True

        traced.add(i)

        for y in graph[i]:
            if not dfs(y):
                return False

        traced.remove(i)
        visited.add(i)

        return True

    for x in list(graph):
        if not dfs(x):
            return False

    return True
