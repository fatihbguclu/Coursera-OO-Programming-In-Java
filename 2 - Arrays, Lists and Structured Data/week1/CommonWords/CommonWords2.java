
/**
 * Write a description of CommonWords2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CommonWords2 {
    
    public String[] getCommon(){
        FileResource fr = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;

        for(String word : fr.words()){
            common[index] = word;
            index += 1;
        }
        
        return common;
    }
    
    public int indexOf(String[] list, String word){
        
        for(int i = 0; i<list.length; ++i){
            if(list[i].equals(word)){
                return i;
            }
        }
        return -1;
    }
    
    public void countWords(FileResource fr, String[] commonWords, int[] counts){
        for(String word : fr.words()){
            word = word.toLowerCase();
            int index = indexOf(commonWords,word);
            if(index != -1){
                counts[index] += 1;
            }
        }
    }
    
    void CountShakespeare(){
        //String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt","likeit.txt", 
        //                "macbeth.txt", "romeo.txt"};
        String[] plays = {"errors.txt"};
        String[] commonWords = getCommon();
        int[] counts = new int[commonWords.length];
        
        for(int i = 0; i<plays.length; ++i){
            FileResource fr = new FileResource("data/" + plays[i]);
            countWords(fr,commonWords,counts);
            System.out.println("done with " + plays[i]);
        }
        
        for(int k=0; k < commonWords.length; k++){
            System.out.println(commonWords[k] + "\t" + counts[k]);
        }
    }
}
