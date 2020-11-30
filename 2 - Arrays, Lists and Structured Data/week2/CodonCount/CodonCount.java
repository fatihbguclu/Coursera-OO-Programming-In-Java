
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CodonCount {
    private HashMap<String,Integer> codonMap;
    
    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        codonMap.clear();
        String codon = "";
        for(int i = start; i<dna.length(); ++i){
            char ch = dna.charAt(i);
            codon += ch;
            if(codon.length() == 3){
                if(!codonMap.containsKey(codon)){
                    codonMap.put(codon,1);
                }else{
                    codonMap.put(codon, codonMap.get(codon) + 1);
                }
                codon = "";
            }
        }
    }
    
    public void testBuild(){
        buildCodonMap(1,"CGTTCAAGTTCAA");
        for(String s : codonMap.keySet()){
            System.out.println(s + " " + codonMap.get(s));
        }
        System.out.println("\n\n" + getMostCommonCodon());
    }
    
    public String getMostCommonCodon(){
        String mostCommonCodon = "";
        int mostCommon = 0;
        for(String s : codonMap.keySet()){
            if(codonMap.get(s) > mostCommon){
                mostCommonCodon = s;
                mostCommon = codonMap.get(s);
            }
        }
        return mostCommonCodon;
    }
    
    public void printCodonCounts(int start, int end){
        for(String s : codonMap.keySet()){
            if(codonMap.get(s) >= start && codonMap.get(s) <= end){
                System.out.println("CODON : " + s + "  TIMES : " + codonMap.get(s));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        buildCodonMap(0,fr.asString());
        for(String s : codonMap.keySet()){
            System.out.println(s + " " + codonMap.get(s));
        }
        System.out.println("\n\n" + getMostCommonCodon() 
                            + " " + codonMap.get(getMostCommonCodon()) + "\n\n");
        printCodonCounts(1,5);
    }
}
















