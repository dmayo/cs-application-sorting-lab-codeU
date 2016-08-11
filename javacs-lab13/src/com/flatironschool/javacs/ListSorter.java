/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}


	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
		int length = list.size();
		int mid=length/2;
		if(length<=1){
			return list;
		}
		List<T> left = new LinkedList<T>(list.subList(0, mid));
    	List<T> right = new LinkedList<T>(list.subList(mid, length));
    	List<T> leftMerge=mergeSort(left,comparator);
    	List<T> rightMerge=mergeSort(right,comparator);

    	return merge(leftMerge, rightMerge, comparator);
	}

	private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
		List<T> result = new LinkedList<T>();
		int total = left.size() + right.size();
		for (int i=0; i<total; i++) {
			result.add(compareList(left, right, comparator).remove(0));
		}
		return result;
	}

	private List<T> compareList(List<T> left, List<T> right, Comparator<T> comparator) {
		if (left.size()==0) {
			return right;
		}
		if (right.size()==0) {
			return left;
		}
		int cmp = comparator.compare(left.get(0), right.get(0));
		if (cmp > 0) {
			return right;
		}
		if (cmp < 0) {
			return left;
		}
		return left;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> q = new PriorityQueue<T>(list.size(),comparator);
        q.addAll(list);
        //extract root of heap in a loop
        list.clear();
        while(!q.isEmpty()){
        	list.add(q.poll());
        }
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        heapSort(list,comparator);
        List<T> result = new LinkedList<T>();
        for(int i=k;i>0;i--){
        	result.add(list.get(list.size()-i));
        }
        return result;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
