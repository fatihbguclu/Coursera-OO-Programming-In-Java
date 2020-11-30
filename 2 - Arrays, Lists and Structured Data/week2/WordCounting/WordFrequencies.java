
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> words;
    private ArrayList<Integer> freqs;
    
    public WordFrequencies(){
        words = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            String s = word.toLowerCase();
            int idx = words.indexOf(s);
            if(idx == -1){
                words.add(s);
                freqs.add(1);
            }else{
                int value = freqs.get(idx);
                freqs.set(idx,value+1);
            }    
        }
    }
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: " + freqs.size());
        for(int i = 0; i<words.size(); ++i){
            System.out.println(freqs.get(i) + "\t" + words.get(i));
        }
    }
}
