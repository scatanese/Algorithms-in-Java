package sorts;

/**
 * {@code Insertion} sort class provides static methods for sorting an
 * array using insertion sort algorithm and Generic data type.
 * <p>
 * Average case = O(n^2)<br>
 * Worst case = O(n^2)<br>
 * Best case = O(n)<br>
 * <p>
 * @see <a href="https://...</a>
 * <br>
 * @author Salvo Catanese
 */


import java.util.Comparator;

import comparators.*;

public class InsertionSort {

	/**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */
	
	public static <T> void sort(T[] array, Comparator<? super T> comp) {
        
        for (int j = 1; j < array.length; j++) {
            T key = array[j];
            int i = j - 1;
            while ( (i >= 0) && (comp.compare(array[i],key)>0) ) {
            	array[i + 1] = array[i];
              i--;
            }
            array[i + 1] = key;
          } 
        
    }
	
	
	/**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
	
	public static <T extends Comparable<? super T>> void sort(T array[]) 
    { 
        int n = array.length; 
        for (int j = 1; j < n; j++) 
        { 
            T key = array[j]; 
            int i = j-1; 
            while ( (i >= 0) && ( array[i].compareTo(key) > 0 ) ) { 
                array [i+1] = array [i]; 
                i--; 
            } 
            array[i+1] = key; 
        }

    }

	// print array to standard output	
    public static <T> void printArray(T array[]) {
    	System.out.println("Ordered array");
    	for (T i : array) {
            System.out.println(i);
        }
    }
    
    public static void main(String[] args) {
		 
		Integer a[] = {4, 6, 3, 5, 2, 9, 11, 9};
		IntegerComparator intcomp = new IntegerComparator();
		InsertionSort.sort(a, intcomp);		
		
		InsertionSort.printArray(a);
		InsertionSort.sort(a);
		InsertionSort.printArray(a);
	}
	
}
