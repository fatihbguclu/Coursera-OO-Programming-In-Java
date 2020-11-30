
/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import edu.duke.*;
public class WordsInFile {
    private HashMap<String,ArrayList<String>> wordsMap;
    
    public WordsInFile(){
        wordsMap = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String w : fr.words()){
            if(!wordsMap.containsKey(w)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(f.getName());
                wordsMap.put(w,list);
            }else{
                ArrayList<String> list = wordsMap.get(w);
                list.add(f.getName());
                wordsMap.put(w,list);
            }
        }
    }
    
    private void buildWordFileMap(){
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int max = 0;
        for(ArrayList<String> list : wordsMap.values()){
            if(list.size() >= max){
                max = list.size();
            }
        }
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> list = new ArrayList<String>();
        String fileName = "brief" + number + ".txt";
        int count = 0;
        for(String word : wordsMap.keySet()){
            if(wordsMap.get(word).size() == number){
                list.add(word);
                count += 1;
            }
        }
        System.out.println("           " + count + "           ");
        return list;
    }
    
    private void printFilesIn(String word){
        ArrayList<String> list = wordsMap.get(word);
        for(int i = 0; i<list.size(); ++i){
            System.out.println(list.get(i));
        }
    }
    
    private void printWordsMap(){
        for(String word : wordsMap.keySet()){
            ArrayList<String> list = wordsMap.get(word);
            System.out.println("\nKey Word is: " + word );
            System.out.println("File name: ");
            for(int i = 0; i<list.size(); ++i){
                System.out.println(list.get(i));
            }
        }
    }
    
    public void tester(){
        buildWordFileMap();
        System.out.println(maxNumber() + "\n");
        ArrayList<String> list = wordsInNumFiles(4);
        /*for(int i = 0; i<list.size(); ++i){
            System.out.println(list.get(i));
        }*/
        System.out.println("\n");
        printFilesIn("tree");
        System.out.println("\n");
        //printWordsMap();
    }
}










