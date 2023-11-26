/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringSimilarity;

import ADT.LinkedStack;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author salvo
 */

public class EditDistance {
 
    static int[][] EDmatrix;
    static String OUTPUT_DATA = "data/EditDistanceTest.txt";
        
    static int recursive(String s1 , String s2)
    {
    int d_no_op;
    int d_canc;
    int d_ins;
        
    if (s1.length() == 0) 
        return s2.length();
    if (s2.length() == 0) 
        return s1.length();
      

    if (s1.toLowerCase().charAt(0) == s2.toLowerCase().charAt(0))
        d_no_op = recursive(rest(s1), rest(s2)); // s1[0]=s2[0]
    else 
        d_no_op = Integer.MAX_VALUE;
    
    d_canc = 1 + recursive(s1,  rest(s2));   // Remove 
    d_ins = 1 + recursive(rest(s1), s2);  // Insert
    return min (d_no_op, d_canc, d_ins);
    
    }
 
   
    static int dynamic2(String s1, String s2){
        
        int m = s1.length() + 1;
        int n = s2.length() + 1;
        
        EDmatrix = new int[m][n];
        int d_canc, d_ins;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (i == 0) {
                    EDmatrix[i][j] = j;
                    
                } else if (j == 0) {
                    EDmatrix[i][j] = i;
                
                } else if (s1.charAt(i-1) == s2.charAt(j - 1)){
                    EDmatrix[i][j] = EDmatrix[i-1][j-1]; 

                } else {
                    d_canc = EDmatrix[i - 1][j] + 1;
                    d_ins = EDmatrix[i][j - 1] + 1;
                    
                    EDmatrix[i][j] = Math.min(d_canc, d_ins);
                 
                }
                    
            }
        }

    
    return EDmatrix[m-1][n-1];
    
    }
       
        static int dynamic(String s1, String s2){

        int m = s1.length() + 1;
        int n = s2.length() + 1;
        EDmatrix = new int[m][n];
        
        int d_canc, d_ins, match, mismatch=Integer.MAX_VALUE;
        
        for (int i=0; i<m; i++){
            EDmatrix[i][0]=i;
        }
        for (int j=0; j<n; j++){
            EDmatrix[0][j]=j;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                
                d_ins = EDmatrix[i][j-1] + 1;
                d_canc = EDmatrix[i-1][j] + 1;
                match = EDmatrix[i-1][j-1];
                
                if (s1.charAt(i-1) == s2.charAt(j - 1)) {
                    EDmatrix[i][j] = min(d_ins,d_canc,match);
                }
                else 
                    EDmatrix[i][j] = min(d_ins,d_canc,mismatch);
                }
                    
            }
        
        return EDmatrix[m-1][n-1];
    
    }
    
        
    private static String rest(String s){
        return s.substring(1, s.length());
    }
    
    public static int min( int x, int y, int z ) {
        if ( x < y )
            return x < z ? x : z;
        else
            return y < z ? y : z;
    }
        
  
     
    public static void printDistanceMatrix(int[][] d, String x, String y) {
        StringBuilder builder = new StringBuilder();
        builder.append("\t\t");
        for (int i=0; i < y.length(); i++) {
            builder.append(y.charAt(i)).append('\t');
        }
        builder.append('\n');
        for (int i=0; i < d.length; i++) {
            if (i == 0) builder.append('\t');
            else builder.append(x.charAt(i-1)).append('\t');
            for (int j=0; j < d[i].length; j++) {
                builder.append(d[i][j]).append('\t');
            }
            builder.append('\n');
        }
        System.out.println("Distance Matrix: ");
        System.out.println(builder);
    }
    
    public static void alignStrings(int[][] dist, String Q, String R){
        int i = Q.length();
        int j = R.length();
                
        LinkedStack<Character> sQ = new LinkedStack<>();
        LinkedStack<Character> sR = new LinkedStack<>();
        LinkedStack<Character> editSequence = new LinkedStack<>();
        
        StringBuilder bldQ = new StringBuilder();
        StringBuilder bldR = new StringBuilder();
        StringBuilder bldEdSequence = new StringBuilder();

                
        StringBuilder builderPath = new StringBuilder();
        //StringBuilder builderSequence = new StringBuilder();
        StringBuilder builderEquals = new StringBuilder();
        //StringBuilder builderSource = new StringBuilder();
        //StringBuilder builderTarget = new StringBuilder();
        StringBuilder finalResults = new StringBuilder();


        
        while (i!=0 || j!=0){
            builderPath.append("("+i+", "+j+")");
                    
            if (i>0 && dist[i-1][j]<dist[i][j]){ // up
                sQ.push(Q.charAt(i-1));
                sR.push('-');
                
                editSequence.push('D');
                //builderSequence.append("D");
                //builderSource.append(Q.charAt(i-1));
                //builderTarget.append('-');
                i--;
            }
            else if(j>0 && dist[i][j-1]<dist[i][j]){ // left
                sQ.push('-');
                sR.push(R.charAt(j-1));
                
                editSequence.push('I');
                
                //builderSequence.append("I");
                //builderSource.append('-');
                //builderTarget.append(R.charAt(j-1));
                j--;
            }
            else  { // diagonally
                sQ.push(Q.charAt(i-1));
                sR.push(R.charAt(j-1));
                
                editSequence.push('N');
                //builderSequence.append("N");
                //builderSource.append(Q.charAt(i-1));
                //builderTarget.append(R.charAt(j-1));
                i--;
                j--;
            }

            
        }
       
        while(!sQ.isEmpty()){
            bldQ.append(sQ.pop());
        }
        
       
        while(!sR.isEmpty()){
            bldR.append(sR.pop());
        }
       
              
        while(!editSequence.isEmpty()){
            bldEdSequence.append(editSequence.pop());
        }
       
        String path = builderPath.toString();
        //String sequence = builderSequence.reverse().toString();
        //String source = builderSource.reverse().toString();
        //String target = builderTarget.reverse().toString();

       
        for (int k=0;k<bldEdSequence.length();k++){
           if (bldEdSequence.charAt(k) == 'N')
               builderEquals.append('|');
            else builderEquals.append(' ');
        }
       
       
        finalResults.append("\nBackward path:\t").append(path).append('\n');
        finalResults.append("Edit Sequence:\t").append(bldEdSequence).append('\n');
        //finalResults.append("Alignment: "+'\n');
        finalResults.append("Source:\t\t").append(bldQ).append('\n');
        finalResults.append("Equals:\t\t").append(builderEquals).append('\n');
        finalResults.append("Target:\t\t").append(bldR).append('\n');

        System.out.println(finalResults);

       
        System.out.println();
        
    }
    
    private static class WordItem {
        private String word;
        private int editdistance;
        
        public WordItem(String w, int ed){
            this.word = w;
            this.editdistance = ed;
        }
        
        public String getWord(){
            return this.word;
        }
    } 
    
    public static void getMinumumEditDistanceList (String[] wordsToCorrects, String[] dictionary){
        
        ArrayList <WordItem> matches = new ArrayList <> ();
        ArrayList <WordItem> compared = new ArrayList <> ();
        
        int distmin=0;
        int edit_distance_dyn=0;
        for (String wordsToCorrect : wordsToCorrects) {
            matches.clear();
            compared.clear();
            for (int i = 0; i<dictionary.length; i++) {
                edit_distance_dyn = dynamic(wordsToCorrect, dictionary[i]);
                compared.add(new WordItem(dictionary[i],edit_distance_dyn));
                if (i==0) 
                    distmin = edit_distance_dyn;
                else if (edit_distance_dyn<distmin) 
                    distmin = edit_distance_dyn;
            }
            
            for (WordItem x : compared){
                if (x.editdistance == distmin)
                    matches.add(x);
            }
            
            System.out.println("Input: " + wordsToCorrect);
            System.out.print("Matches: ");
            for (WordItem m : matches)
                System.out.print(m.word + "(" + m.editdistance + ") ");
            System.out.println();
        }
        

        
        
    }
    
    public static void main(String args[]) throws FileNotFoundException
    {
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
        
        String str1 = "casa";
        String str2 = "cassa";
        
        System.out.println("***");
        System.out.println("Source " + str1 + " to Target " + str2);
        System.out.println();
        System.out.println("Recursive\n ### Edit Distance: "+recursive( str1 , str2 ));
        System.out.println();
        System.out.println("Dynamic programming\n ### Edit Distance: "+dynamic(str1 , str2));
        System.out.println();        
        printDistanceMatrix(EDmatrix, str1, str2);
        alignStrings(EDmatrix, str1, str2);
        

        String str3 = "casa";
        String str4 = "cara";
        System.out.println("***");
        System.out.println("Source " + str3 + " to Target " + str4);
        System.out.println();
        System.out.println("Recursive\n ### Edit Distance: "+recursive( str3 , str4 ));
        System.out.println();
        System.out.println("Dynamic programming\n ### Edit Distance: "+dynamic(str3 , str4));
        System.out.println();
        printDistanceMatrix(EDmatrix, str3, str4);
        alignStrings(EDmatrix, str3, str4);
        
        String str5 = "tassa";
        String str6 = "passato";
        System.out.println("***");
        System.out.println("Source " + str5 + " to Target " + str6);
        System.out.println();
        System.out.println("Recursive\n ### Edit Distance: "+recursive( str5 , str6 ));
        System.out.println();
        System.out.println("Dynamic programming\n ### Edit Distance: "+dynamic(str5 , str6));
        System.out.println();
        printDistanceMatrix(EDmatrix, str5, str6);
        alignStrings(EDmatrix, str5, str6);

        String str7 = "pioppo";
        String str8 = "pioppo";
        System.out.println("***");
        System.out.println("Source " + str7 + " to Target " + str8);
        System.out.println();
        System.out.println("Recursive\n ### Edit Distance: "+recursive( str7 , str8 ));
        System.out.println();
        System.out.println("Dynamic programming\n ### Edit Distance: "+dynamic(str7 , str8));
        System.out.println();
        printDistanceMatrix(EDmatrix, str7, str8);
        alignStrings(EDmatrix, str7, str8);
        
        if (type==2){
        // Set console print stream.
        System.setOut(ps_console);
        System.out.println("### Saved ... !!");
        }
    }


}
