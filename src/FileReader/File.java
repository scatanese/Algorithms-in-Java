/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReader;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author salvo
 */
public class File {
    
     /**
     * Read list from csv file and save each element in an array
     * 
     * @param array 
     */
    
    public static void readCSV(String[] array, String path){
       
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(path));
            for(int i = 0; i < array.length; i++){
                String str = reader.readLine();
                array[i] = str;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            } finally {
            try {
                if (reader != null)reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                }
            }
    }
    
    public static String[] readTXT(String path) {
        
        int words = 0;
        String regex = "[^a-zA-Z0-9\\-\']+";
       
        try {
        Scanner s1 = new Scanner(new java.io.File(path));
        s1.useDelimiter(regex);
        while (s1.hasNext()) {
            s1.next();
            words++;        
        }
        
        String[] wordsarray =  new String[words];
        Scanner s2 = new Scanner(new java.io.File(path));
        s2.useDelimiter(regex);
        
        for (int i=0; i<words; i++)
            wordsarray[i] = s2.next().toLowerCase();
        return wordsarray;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found!");
    }
        return null;
    }

}