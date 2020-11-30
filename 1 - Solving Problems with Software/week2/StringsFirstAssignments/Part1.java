

import edu.duke.*;
import java.io.*;

public class Part1 {
    public String findSimpleGene(String s){
        String gene = "";
        
        int startIndex = s.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        
        int stopIndex = s.indexOf("TAA",startIndex+3);
        if(((stopIndex - startIndex) % 3) == 0  && stopIndex != -1){
            gene = s.substring(startIndex, stopIndex+3);
        }else{
            return "";
        }
        
        
        return gene;
    }
    
    public void testSimpleGene(){
        String dna = "AATGCTACGGCATAA"; // no ATG
        String simpleGene = findSimpleGene(dna);
        System.out.println("DNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
        dna = "ATGGCTACGGCA"; // no TAA
        simpleGene = findSimpleGene(dna);
        System.out.println("DNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
        dna = "GCAGCTACGGCA"; // no TAA or ATG
        simpleGene = findSimpleGene(dna);
        System.out.println("DNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
        dna = "GCAATGGCTACGGCATGATAACGAAGC"; // TAA an ATG with between multiple of 3
        simpleGene = findSimpleGene(dna);
        System.out.println("DNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
        dna = "GCAATGTACGGCATGATAACGAAGC"; // TAA an ATG no between multiple of 3
        simpleGene = findSimpleGene(dna);
        System.out.println("DNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
    }
}
