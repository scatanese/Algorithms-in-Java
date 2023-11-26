/**
 * 
 * @author salvo
 */
package search;



public class Search {
    
    /** 
     * Apply the brute-force Sequential Search algorithm to search the 
     * index of the specified target key in an ordered array (of type T).
     * 
     * Worst-case performance      O(n)<br>
     * Best-case performance       O(1)<br>
     * Average performance         O(n)<br>
     */
    
    public static <T extends Comparable<T>> int linearSearch (T[] sortedArray, T key){
        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i].compareTo(key) == 0)
                return i;
        }
        return -1;
    }
    
    /**
     * 
     * Returns the index of the specified key in the specified array.
     * 
     * @param sortedArray the array of T elements sorted in ascending order
     * @param key the search key
     * @return index of key in array if present
     * 
     */
   
    public static <T extends Comparable<T>> int binarySearch(T[] sortedArray, T key) {
        
        int left = 0;
        int right = sortedArray.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left )/2;
            
            if (key.compareTo(sortedArray[mid]) < 0)
                right = mid - 1;
                
            else if (key.compareTo(sortedArray[mid]) > 0)
                left = mid + 1;
            else
                return mid;

        }
        return -1;
    }

    /**
     * 
     * Returns the index of the specified key in the specified array.
     * 
     * @param sortedArray the array of Long sorted in ascending order
     * @param key the search key
     * @return index of key in array if present
     * 
     */
   
    public static int binarySearchLong(Long[] sortedArray, Long key) {
        
        int left = 0;
        int right = sortedArray.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left )/2;
            
            if (key<sortedArray[mid])
                right = mid - 1;
                
            else if (key>sortedArray[mid])
                left = mid + 1;
            else
                return mid;

        }
        return -1;
    }
    
    /**
     * Brute force method Find Sum
     * 
     * Given an integer array, a number N and its index (in the array)
     * find a pair of numbers whose sum is equal to N
     * 
     * Time complexity O(n^2)
     * Space complexity O(n)
     * 
     * @param sortedArray the array of Integer sorted in ascending order
     * @param N the search key
     * 
     */
   
    public static boolean bruteFindSum(Long [] sortedArray, Long N) {
        int right = sortedArray.length - 1;
        while (sortedArray[right]>N && right>0) {
            right--;
        }

        for (int i=0; i<sortedArray.length-1; i++) {
            for (int j=i+1; j<right; j++) {
                if(sortedArray[i]+sortedArray[j]==N){
                    System.out.println("Found numbers:");
                    System.out.println("A["+i+"] = "+sortedArray[i]);
                    System.out.println("A["+j+"] = "+sortedArray[j]);
                    System.out.println("whose sum is  = "+ N);
                    return true;
                } 
            } 
        }
        System.out.println("Numbers Not Found!");
        return false;
}
    
    
    /**
     * Find Sum using binary search algorithm
     * 
     * Given an integer array, a number N and its index (in the array)
     * find a pair of numbers whose sum is equal to N 
     * 
     * Time complexity O(n log n)
     * 
     * @param sortedArray the array of Integer sorted in ascending order
     * @param N the search key
     * 
     */
   
    public static boolean binaryFindSum(Long[] sortedArray, Long N) {
                
        int right = sortedArray.length - 1;
        while (sortedArray[right]>N && right>0) {
            right--;
        }
        
        for (int i=0; i<right; i++){
            Long a = sortedArray[i];
            int b = Search.binarySearchLong(sortedArray, (N-a));
            if (b>=0){
                System.out.println("Found numbers:");
                System.out.println("A["+i+"] = "+sortedArray[i]);
                System.out.println("A["+b+"] = "+sortedArray[b]);
                System.out.println("whose sum is  = "+ N);
                return true;
            }
        }
        System.out.println("Numbers Not Found!");
        return false;
}

    /**
     * Find Sum using binary search algorithm
     * 
     * Given an integer array, a number N and its index (in the array)
     * find a pair of numbers whose sum is equal to N 
     * 
     * Time complexity O(n log n)
     * 
     * @param sortedArray the array of Integer sorted in ascending order
     * @param N the search key
     * @return true if it found the two numbers
     * 
     */
   
    public static boolean binaryFindSumTwo(Long[] sortedArray, Long N) {
        
        
        int right = sortedArray.length - 1;

        while (sortedArray[right]>N && right>0) {
            right--;
        }
        
        for (int i=0; i<right; i++){
            int l=0;
            int r=right;
            while (l <= r) {
            int mid = l + (r - l)/2;
            
            if ((sortedArray[i]+sortedArray[mid]) == N){
                System.out.println("Found numbers:");
                System.out.println("A["+i+"] = "+sortedArray[i]);
                System.out.println("A["+mid+"] = "+sortedArray[mid]);
                System.out.println("whose sum is  = "+ N);
                return true;
            }
            else if ((sortedArray[i]+sortedArray[mid]) >= N) {
                r = mid - 1;
            }
            else
                l = mid + 1;   
            }         
        }
        System.out.println("Numbers Not Found!");
        return false;
}
      
    /**
     * Find Sum using alternative search algorithm
     * 
     * Given an integer array, a number N and its index (in the array)
     * find a pair of numbers whose sum is equal to N 
     * 
     * Time complexity O(n log n)
     * 
     * @param sortedArray the array of Integer sorted in ascending order
     * @param N the search key
     * 
     */
   
    public static boolean otherFindSum(Long[] sortedArray, Long N) {
        int i = 0;
        int j = sortedArray.length-1;
        
        while (sortedArray[j]>N && j>0) j--;
        //System.out.println("sortedArray["+j+"]: "+sortedArray[j]);
        while (i<j){
            Long a = sortedArray[i];
            Long b = sortedArray[j];
           // System.out.println("a: "+a+" b: "+b);
            Long sum = a+b;
            
            if (sum > N) {
                j--;
            } else if (sum < N) {
                i++;
            } else if (sum.equals(N)){
                System.out.println("Found numbers:");
                System.out.println("A["+i+"] = "+sortedArray[i]);
                System.out.println("A["+j+"] = "+sortedArray[j]);
                System.out.println("whose sum is  = "+ N);
                return true;
            } 
        }
        return false;
    }
}
