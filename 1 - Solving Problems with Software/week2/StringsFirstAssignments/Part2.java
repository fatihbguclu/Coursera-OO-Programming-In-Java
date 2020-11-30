

import edu.duke.*;
import java.io.*;

public class Part2 {
    public String findSimpleGene(String s, String startCodon, String stopCodon){
        String gene = "";
        String dna = s;
        String startC = startCodon;
        String stopC = stopCodon;
        if(s.equals(s.toLowerCase())){
            dna = s.toLowerCase();
            startC = startCodon.toLowerCase();
            stopC = stopCodon.toLowerCase();
        }else if(s.equals(s.toUpperCase())){
            dna = s.toUpperCase();
            startC = startCodon.toUpperCase();
            stopC = stopCodon.toUpperCase();
        }
        
        int startIndex = dna.indexOf(startC);
        if(startIndex == -1){
            return "";
        }
        
        int stopIndex = dna.indexOf(stopC,startIndex+3);
        if(((stopIndex - startIndex) % 3) == 0  && stopIndex != -1){
            gene = dna.substring(startIndex, stopIndex+3);
        }else{
            return "";
        }
        
        
        return gene;
    }
    
    public void testSimpleGene(){
        String dna = "AATGCTACGGCATAA"; // no ATG
        String simpleGene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("DNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
        dna = "ATGGCTACGGCA"; // no TAA
        simpleGene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("\nDNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
        dna = "GCAGCTACGGCA"; // no TAA or ATG
        simpleGene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("\nDNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
        dna = "GCAATGGCTACGGCATGATAACGAAGC"; // TAA an ATG with between multiple of 3
        simpleGene = findSimpleGene(dna.toLowerCase(),"ATG","TAA");
        System.out.println("\nDNA = " + dna.toLowerCase() + "\n" + "SimpleGene = " + simpleGene);
        
        dna = "GCAATGTACGGCATGATAACGAAGC"; // TAA an ATG no between multiple of 3
        simpleGene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("\n DNA = " + dna + "\n" + "SimpleGene = " + simpleGene);
        
    }
}
