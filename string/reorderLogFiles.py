from typing import List
# 03 ) 로그 파일 재정렬

# 힌트 : 람다식을 사용해야함.
def solution( logFiles : List[str] ) -> List[str]:

    letter = []
    numbers = []
    for log in logFiles:
        if log.split()[1].isdigit():
            numbers.append(log)
        else :
            letter.append(log)

    letter.sort( key = lambda x : (x.split()[1:], x.split()[0]))

    return letter + numbers

# 실행 테스트
if __name__ == "__main__":
    logs = [
        "dig1 8 1 5 1",
        "let1 art can",
        "dig2 3 6",
        "let2 own kit dig",
        "let3 art zero"
    ]
    print(solution(logs))

