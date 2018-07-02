/**
 * 
 */
package com.example;

import java.util.HashSet;
import java.util.Set;

/**
 * @author resulav
 *
 */
public class MaxBiValuedSlice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 5, 4, 4, 5, 0, 12 }));

	}

	public static int solution(int[] A) {
		if (A == null || A.length < 2) {
			return 0;
		}

		Set<Integer> set = new HashSet<>();
		int maxLength = 0;
		int tempLenght = 0;
		int last = A[1];

		for (int i = 0; i < A.length; i++) {
			if (set.contains(A[i])) {
				tempLenght++;
			} else if (set.size() < 2) {
				tempLenght++;
				set.add(A[i]);
			} else {
				maxLength = Math.max(maxLength, tempLenght);
				tempLenght = 2;
				set.clear();
				set.add(last);
				set.add(A[i]);
			}
			last = A[i];
		}

		return maxLength;
	}
}
