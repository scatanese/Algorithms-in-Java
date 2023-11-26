/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import StringSimilarity.EditDistance;
import FileReader.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author salvo
 */
public class CorrectText {
    
    static String TXT_PATH = "data/correctme.txt";
    static String DICTIONARY_FILEPATH = "data/dictionary.txt";
    static String OUTPUT_DATA = "data/CorrectMeTest.txt";
    final static int DICTIONARY_SIZE = 661562;
    
    static long startTime;
    static long elapsedTime;
    
    public static void main(String[] args) throws FileNotFoundException {
       
        Scanner in = new Scanner(System.in);
        System.out.println("1. Console output");
        System.out.println("2. Redirect output to a file");
        int type = in.nextInt(); 
        
        // Store console print stream.
        PrintStream ps_console = System.out;
        
        if (type==2){
        System.out.println("### Print results to file ["+OUTPUT_DATA+"] ...");
        java.io.File file = new java.io.File(OUTPUT_DATA);
        FileOutputStream fos = new FileOutputStream(file);

        // Create new print stream for file.
        PrintStream ps = new PrintStream(fos);

        // Set file print stream.
        System.setOut(ps);
        
        }

        String[] dictionary = new String[DICTIONARY_SIZE];

        // Load text file
        System.out.println("Load ["+ TXT_PATH + "]");
        startTime = System.currentTimeMillis();
        String[] wordsToCorrect = File.readTXT(TXT_PATH);
        System.out.println("### " + wordsToCorrect.length + " key loaded in: " + (System.currentTimeMillis()-startTime) + " millisec " );
        System.out.println();

        // Load dictionary
        System.out.println("Load ["+ DICTIONARY_FILEPATH + "]");
        startTime = System.currentTimeMillis();
        File.readCSV(dictionary, DICTIONARY_FILEPATH);
        System.out.println("### " + dictionary.length + " key loaded in: " + (System.currentTimeMillis()-startTime) + " millisec " );
        System.out.println();

        // Find minimum edit distance words
        System.out.println("### START Edit Distance algorithm:");
        startTime = System.currentTimeMillis();
        EditDistance.getMinumumEditDistanceList(wordsToCorrect, dictionary);
        System.out.println("### END in: " + (System.currentTimeMillis()-startTime) + " millisec " );
        System.out.println();

        if (type==2){
        // Set console print stream.
        System.setOut(ps_console);
        System.out.println("### Saved ... !!");
        }
    }
    
}
