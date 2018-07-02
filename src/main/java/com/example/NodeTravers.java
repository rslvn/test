/**
 * 
 */
package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author resulav
 *
 */
public class NodeTravers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 })));
		System.out.println(Arrays.toString(solution(new int[] { 0, 0, 1, 2, 3, 4, 5, 6, 7, 8 })));
	}

	public static int[] solution(int[] A) {
		if (A == null || A.length < 2) {
			return new int[] {};
		}

		int[] road = new int[A.length - 1];

		// prepare road relations
		int capital = 0;
		Map<Integer, Set<Integer>> listOfDirectConnection = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			if (i == A[i]) {
				capital = i;
			}
			Set<Integer> set = listOfDirectConnection.getOrDefault(i, new HashSet<>());
			set.add(A[i]);
			listOfDirectConnection.put(i, set);

			set = listOfDirectConnection.getOrDefault(A[i], new HashSet<>());
			set.add(i);
			listOfDirectConnection.put(A[i], set);
		}

		int directConnection;
		Set<Integer> oldCities = new HashSet<>();
		oldCities.add(capital);

		// direct connections to old cities
		Set<Integer> directRoadToOldCities = listOfDirectConnection.get(capital);
		directRoadToOldCities.removeAll(oldCities);

		for (int i = 0; i < road.length; i++) {
			directConnection = directRoadToOldCities.size();
			if (directConnection == 0) {
				break;
			}
			road[i] = directConnection;
			Set<Integer> tmp = new HashSet<>(directRoadToOldCities);
			directRoadToOldCities = getDirects(listOfDirectConnection, directRoadToOldCities, oldCities);
			oldCities = tmp;
		}

		return road;
	}

	/**
	 * get next direct connected cities
	 * 
	 * @param listOfDirectConnection
	 * @param directs
	 * @param olds
	 * @return
	 */
	private static Set<Integer> getDirects(Map<Integer, Set<Integer>> listOfDirectConnection, Set<Integer> directs,
			Set<Integer> olds) {
		Set<Integer> nextDirects = new HashSet<>();
		for (Integer integer : directs) {
			Set<Integer> tmp = listOfDirectConnection.get(integer);
			tmp.removeAll(olds);
			nextDirects.addAll(tmp);
		}
		return nextDirects;
	}

}
