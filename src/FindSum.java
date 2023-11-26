
import FileReader.File;
import comparators.LongComparator;
import search.Search;
import sorts.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salvo
 */

public class FindSum {
    
    static String CVS_PATH = "data/integers.csv";
    static String INTEGERS_FILEPATH = "data/sums.txt";
    
    final static int CVS_ARRAY_SIZE = 3000000;
    final static int INTEGERS_ARRAY_SIZE = 11;
    
    final static Long NUMBER_TO_FIND = 16505359832L;
    
    static long startTime;
    static long elapsedTime;
  
    public static void main(String[] args) {
        
        String[] arrayString = new String[CVS_ARRAY_SIZE];
        String[] longString = new String[INTEGERS_ARRAY_SIZE];

        // Load Integer.csv
        System.out.println("Load ["+ CVS_PATH + "]");
        startTime = System.currentTimeMillis();
        File.readCSV(arrayString, CVS_PATH);
        System.out.println("### " + CVS_ARRAY_SIZE + " key loaded in: " + (System.currentTimeMillis()-startTime) + " millisec " );
        System.out.println();

        Long[] ArrayL = new Long[arrayString.length];
        for (int i = 0; i<arrayString.length; i++) 
            ArrayL[i] = Long.parseLong(arrayString[i]);
        
        LongComparator longcomp = new LongComparator();
        
        // Sort Integer array
        System.out.println("START Merge sort algorithm:");
        startTime = System.currentTimeMillis();
        MergeSort.mergeSort(ArrayL, longcomp);        
        System.out.println("### END in: " + (System.currentTimeMillis()-startTime) + " millisec " );
        System.out.println();
        
        // Find sum of a number
        System.out.println("Find two elements in the array whose sum is: " + NUMBER_TO_FIND);
        startTime = System.currentTimeMillis();
        Search.binaryFindSumTwo(ArrayL, NUMBER_TO_FIND);
        System.out.println("### in: " + (System.currentTimeMillis()-startTime) + " millisec " );
        System.out.println();
        
        // Find sum of 100 integer in a file
        // Load Integer.csv
        System.out.println("Load ["+ INTEGERS_FILEPATH + "]");
        startTime = System.currentTimeMillis();
        File.readCSV(longString, INTEGERS_FILEPATH);
        System.out.println("### " + INTEGERS_ARRAY_SIZE + " key loaded in: " + (System.currentTimeMillis()-startTime) + " millisec " );
        System.out.println();
        
        Long[] ArraySum = new Long[longString.length];
        for (int i = 0; i<longString.length; i++) 
            ArraySum[i] = Long.parseLong(longString[i]);
        
        for (int i = 0; i<longString.length; i++) {
            // Find sum of a number
            System.out.println("Find two elements in the array whose sum is: " + ArraySum[i]);
            startTime = System.currentTimeMillis();
            Search.binaryFindSumTwo(ArrayL, ArraySum[i]);
            System.out.println("### in: " + (System.currentTimeMillis()-startTime) + " millisec " );
            System.out.println();
        }
    }
}

