import edu.duke.*;
import java.util.*;

public class GladLib {
    private HashMap<String,ArrayList<String>> map;
    private ArrayList<String> seenWords;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        map = new HashMap<String,ArrayList<String>>();
        seenWords = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        map = new HashMap<String,ArrayList<String>>();
        seenWords = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        map.clear();
        String[] sourceArr = {"adjective","noun","color","country","name","animal"
                                ,"timeframe","verb","fruit"};
        for(String label : sourceArr){
            ArrayList<String> list = readIt(source + "/" + label + ".txt");
            map.put(label,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        for(String key : map.keySet()){
            if(key.equals(label)){
                return randomFrom(map.get(key));
            }
        } 
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        //System.out.println(seenWords.contains(sub));
        if(!seenWords.contains(sub)){
            seenWords.add(sub);
        }else{
            while(seenWords.contains(sub)){
                sub = getSubstitute(w.substring(first+1,last));
                if(! seenWords.contains(sub)){
                    seenWords.add(sub);
                    break;
                }
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int totalWords = 0;
        for(String key : map.keySet()){
            totalWords += map.get(key).size();
        }
        return totalWords;
    }
    
    /*private int totalWordsConsidered(){
    
    }*/
    
    public void makeStory(){
        //System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\n" + seenWords.size() + " number of words were replaced\n");
        for(int i = 0; i<seenWords.size(); ++i){
            System.out.println(seenWords.get(i) + " ");
        }
        System.out.println("\nNumber Of Words in All ArrayLists in the HashMap : " + totalWordsInMap());
    }
    


}
