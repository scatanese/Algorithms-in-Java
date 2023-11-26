import FileReader.File;
import sorts.*;
import java.util.Scanner;

import comparators.*;


public class SortAlgorithms {

    static String PATH = "data/integers.csv";
    final static int ARRAY_SIZE = 5000000;
    
    static long startTime;
    static long elapsedTime;
  
    public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    System.out.print("Enter array data type (int, long, float, string): ");
    String type = in.nextLine(); 
    
    System.out.print("Choose a sort algorithm (1=insertion sort, 2=merge sort): ");
    int sortalgorithm = in.nextInt(); 
    
    in.close();
    
    String array[] = new String[ARRAY_SIZE];
    File.readCSV(array, PATH);
                
    switch (type) {
            case "long":
                LongComparator longcomp = new LongComparator();
                Long[] ArrayLong = new Long[array.length];
                for (int i = 0; i<array.length; i++) 
                    ArrayLong[i] = Long.parseLong(array[i]);
                if (sortalgorithm==1) {
                    InsertionSort.sort(ArrayLong, longcomp);
                    InsertionSort.printArray(ArrayLong);
                } else {
                MergeSort.mergeSort(ArrayLong, longcomp);
                MergeSort.printArray(ArrayLong);
                }
                
                break;
            case "int":
                IntegerComparator intcomp = new IntegerComparator();
                Integer[] ArrayInt = new Integer[array.length];
                for (int i = 0; i<array.length; i++) 
                    ArrayInt[i] = Integer.parseInt(array[i]);
                if (sortalgorithm==1) {
                    InsertionSort.sort(ArrayInt, intcomp);
                    InsertionSort.printArray(ArrayInt);
                } else {
                MergeSort.mergeSort(ArrayInt, intcomp);
                MergeSort.printArray(ArrayInt);
                }
                break;
            case "string":
                StringComparator strcomp = new StringComparator();
               
                if (sortalgorithm==1) {
                    InsertionSort.sort(array, strcomp);
                    InsertionSort.printArray(array);
                } else {
                MergeSort.mergeSort(array, strcomp);
                MergeSort.printArray(array);
                }
                break;
            case "float":
                FloatComparator floatcomp = new FloatComparator();
                Float[] ArrayFloat = new Float[array.length];
                for (int i = 0; i<array.length; i++) 
                    ArrayFloat[i] = Float.parseFloat(array[i]);
                if (sortalgorithm==1) {
                    InsertionSort.sort(ArrayFloat, floatcomp);
                    InsertionSort.printArray(ArrayFloat);
                } else {
                MergeSort.mergeSort(ArrayFloat, floatcomp);
                MergeSort.printArray(ArrayFloat);
                }
                break;
            default:
               
        }
    
     
    }
    
    
    
   
}
       
