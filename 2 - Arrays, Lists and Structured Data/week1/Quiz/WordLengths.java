
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordLengths {
    private static HashMap map = new HashMap();
    
    public static void countWordLengths(FileResource fr, int[] counts){
        
        for(String word : fr.words()){
            StringBuilder sb = new StringBuilder(word);
            int wordLength = 0;
            for(int i = 0; i<sb.length(); ++i){
                
                if(i == 0 && !Character.isLetter(sb.charAt(i))){
                    sb.deleteCharAt(i);
                }
                else if(i == sb.length() - 1 && !Character.isLetter(sb.charAt(i))){
                    sb.deleteCharAt(i);
                }
                else{
                    wordLength++;
                }
            }
            String result = sb.toString();
            map.put(result, wordLength);
            counts[wordLength]++;
        }
        
        for (int i = 0; i<counts.length; ++i){
            if(counts[i] != 0){
                System.out.print(counts[i] + " word of length " + i + ": ");
                Set set = map.entrySet();
                Iterator iter = set.iterator();
                while(iter.hasNext()){
                    Map.Entry me = (Map.Entry) iter.next();
                    if(me.getValue().equals(i)){
                        System.out.print(me.getKey() + " ");
                    }
                }
                System.out.println();
            }
        }
    }
    public static int indexOfMax(int[] values){
        int max = 0;
        for(int i = 0; i<values.length; ++i){
            if(values[i] >= values[max]){
                max = i;
            }
        }
        return max;
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
        System.out.println(indexOfMax(counts));
    }
}
