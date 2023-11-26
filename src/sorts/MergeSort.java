package sorts;

import java.util.Comparator;
import java.util.Arrays;

import comparators.*;


public class MergeSort{

/**
 * Merge-sort contents of array unordered.
 * 
 * @param <T>
 * @param unordered
 * @param comp 
 */
	
public static <T> void mergeSort(T[] unordered, Comparator<T> comp) {
    
    int n = unordered.length;
    
    if (n < 2) {
        return;
    }
    
    // divide
    int mid = n/2;
    T[] L = Arrays.copyOfRange(unordered, 0, mid);         
    T[] R = Arrays.copyOfRange(unordered, mid, n);         
    
    // conquer
    mergeSort(L, comp);
    mergeSort(R, comp);
    
    // merge results
    merge(L, R, unordered, comp);
}

 /**
  * Merge contents of arrays L and R into properly sized array unorderedArray.
  * 
  * @param <T>
  * @param L
  * @param R
  * @param unordered
  * @param comp 
  */
public static <T> void merge(T[] L, T[] R, T[] unordered, Comparator<T> comp) {
    int i = 0, j = 0;
    
    while (i + j < unordered.length) {
        if (j == R.length || (i < L.length && comp.compare(L[i], R[j]) < 0)) {
        	unordered[i+j] = L[i++];
        } else {
        	unordered[i+j] = R[j++];
        }
    }
}

//print array to standard output	
public static <T> void printArray(T array[]) {
	System.out.println("Ordered array");
	for (T i : array) {
        System.out.println(i);
    }
    System.out.println();
}


public static void main(String args[])
{
	
    Integer testArray[] = {1,3,5,3,10,7,8,9,1};
    IntegerComparator intcomp = new IntegerComparator();
    mergeSort(testArray, intcomp);
    System.out.println(Arrays.toString(testArray));
    
    
}


}