
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreq;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreq = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreq.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            String s = word.toLowerCase();
            if(! myWords.contains(s)){
                myWords.add(s);
                myFreq.add(1);
            }else{
                int index = myWords.indexOf(s);
                int value = myFreq.get(index);
                myFreq.set(index,value+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max = 0;
        for(int i = 0; i<myFreq.size(); ++i){
            if(myFreq.get(i) > myFreq.get(max)){
                max = i;
            }
        }
        return max;
    }
    
    public void tester(){
        findUnique();
        System.out.println("Number of Words : " + myFreq.size());
        System.out.println("Most Common Word " + myFreq.get(findIndexOfMax()) +" times " 
        + myWords.get(findIndexOfMax()));
        for(int i = 0; i<myWords.size(); ++i){
            System.out.println(myFreq.get(i) + "\t" + myWords.get(i));
        }
    }
}
