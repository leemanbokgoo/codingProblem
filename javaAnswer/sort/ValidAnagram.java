package javaAnswer.sort;

import java.util.Arrays;

/**
 *  62 ) 유효한 애너그램
 */
public class ValidAnagram {

	// 정렬 이용
	public boolean solution( String s , String t ){

		if ( s.length() != t.length()) return false;

		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();

		Arrays.sort(sChars);
		Arrays.sort(tChars);

		return Arrays.equals(sChars, tChars);
	}
}
