package javaAnswer.greedy;

import java.util.Arrays;

/**
 *  82 ) 쿠키 부여
 *  리트코드 455
 *
 */
public class AssignCookies {
	public int solution(int[] cookies, int[] children) {

		// 1. 아이들의 욕심(children)과 쿠키 크기(s)를 오름차순으로 정렬
		Arrays.sort(children);
		Arrays.sort(cookies);

		int i = 0; // 아이들을 가리키는 포인터 (children_i)
		int j = 0; // 쿠키를 가리키는 포인터 (s_j)

		// 2. 아이도 남고 쿠키도 남았을 때까지만 반복
		while (i < children.length && j < cookies.length) {

			// 3. 현재 쿠키(s[j])가 현재 아이(children[i])의 요구치를 만족하는지 확인
			if ( cookies[j] >= children[i]) {
				i++; // 만족했으니 다음 아이로 넘어감
			}

			// 4. 만족했든 안 했든, 현재 쿠키는 검사가 끝났으므로 다음 쿠키로 넘어감
			// 검사가 끝났다는 말은 이미 아이들이 오름차순 순으로 서있기때문에 3 크기의 쿠키를 원하는 아이들에게 거부당한 쿠키가 4,5,6을 원하는 아이들을 만족할 수 없기때문이다.
			j++;
		}

		// 5. 만족한 아이의 총 인덱스(수)를 반환
		return i;
	}

}
