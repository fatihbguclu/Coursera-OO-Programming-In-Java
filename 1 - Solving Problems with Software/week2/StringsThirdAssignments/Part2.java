
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part2 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
                
            }
        }
        
        return -1;
    }
    
    public void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        
        int dex = findStopCodon(dna, 0,"TAA");
        System.out.println(dex);
        
        dex = findStopCodon(dna, 9,"TAA");
        System.out.println(dex);
        
        dex = findStopCodon(dna, 1,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 0,"TAG");
        System.out.println(dex);
    }
    
    public String findGene(String dna, int strIndex){
        
        int startIndex = dna.indexOf("ATG", strIndex);
        if(startIndex == -1){
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");        
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //System.out.println(taaIndex);
        //System.out.println(tagIndex);
        //System.out.println(tgaIndex);
        int minIndex = 0;
        
        if(taaIndex == -1 || (tagIndex != -1 && tagIndex<taaIndex)){
            minIndex = tagIndex;
        }else{
            minIndex = taaIndex;
        }
        
        if(minIndex == -1 || (tgaIndex != -1 && tgaIndex<minIndex)){
            minIndex = tgaIndex;
        }
        
        if(minIndex == -1){
            return "";
        }
        //System.out.println(minIndex);
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindGene(){
        String dna1 = "ATFxxxyyyzzzTAAxxxTAGxxx";
        String dna2 = "xxxATGxxxyyyxxTAGxTAAxxx";
        String dna3 = "xyyATGxxxyyyuuuTGAxxxTAGxxx";
        String dna4 = "xyyATGxxxyyxxxyuuuTGAxxxTAGxxx";
        
        System.out.println("Dna is: " + dna1);
        System.out.println("Gene is: " + findGene(dna1,0));
        
        System.out.println("\nDna is: " + dna2);
        System.out.println("Gene is: " + findGene(dna2,0));
        
        System.out.println("\nDna is: " + dna3);
        System.out.println("Gene is: " + findGene(dna3,0));

        System.out.println("\nDna is: " + dna4);
        System.out.println("Gene is: " + findGene(dna4,0));
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
    
        while(true){
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
                break;
            }else{
                System.out.println(currentGene);
                startIndex = dna.indexOf(currentGene , startIndex) + currentGene.length();
            }
        }
    }
    
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes("\n" + dna);
    }
    
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        
        while(true){
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
                break;
            }else{
                sr.add(currentGene);
                startIndex = dna.indexOf(currentGene , startIndex) + currentGene.length();
            }
        }
        return sr;
    }
    
    public void testGetAllGenes(){
        String dna = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        StorageResource sr = getAllGenes(dna);
        for(String gene : sr.data()){
            System.out.println(gene);
        }
    }
    
    public double cgRatio(String dna){
        double countcg = 0.0;
        
        for(int i = 0; i<dna.length(); i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                countcg++;
            }
        }
        //double ratio = charRatio / dna.length();
        return (double) (countcg / dna.length());
    }
   
    public int countCTG(String dna){
        
        int startIndex = 0;
        int count = 0;
        int index = dna.indexOf("CTG", startIndex);
        
        while(index != -1){
            count++;
            index = dna.indexOf("CTG",index+3);
        }

        return count;
    }
    
    public void processGenes(StorageResource sr){
        int geneLengthCount = 0;
        int cgRatioCount = 0;
        String longestGene = "";
        int max = 0;
        
        for(String gene : sr.data()){
            if(gene.length() > 60){
                System.out.println(gene);
                geneLengthCount += 1;
                
                if(max < gene.length()){
                    max = gene.length();
                    longestGene = gene;
                }
            }
        }
        
        System.out.println("C-G ratio higher than 0.35 Genes : ");
        
        for(String gene : sr.data()){             
            if(cgRatio(gene) > 0.35){
                cgRatioCount += 1;
                System.out.println(gene);
            }
        }
        
        System.out.println("Number of Strings in sr that are longer than 60 char : " 
        + geneLengthCount);
        System.out.println("Number of Strings in sr whose C-G-ratio is higher than 0.35 : " 
        + cgRatioCount);
        System.out.println("The Length of the longest gene in sr : " + longestGene.length() 
        + " " + countCTG(longestGene));
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("dna/Axl2p.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        
        System.out.println("\n\n----------------------------------------\n\n");
        fr = new FileResource("dna/brca1.fa");
        dna = fr.asString().toUpperCase();
        sr = getAllGenes(dna);
        processGenes(sr);
        
        System.out.println("\n\n----------------------------------------\n\n");
        fr = new FileResource("dna/brca1line.fa");
        dna = fr.asString().toUpperCase();
        sr = getAllGenes(dna);
        System.out.println(sr.size());
        processGenes(sr);
        
        System.out.println("\n\n----------------------------------------\n\n");
        fr = new FileResource("dna/GRch38dnapart.fa");
        dna = fr.asString().toUpperCase();
        sr = getAllGenes(dna);
        System.out.println(sr.size());
        System.out.println(" " + countCTG(dna));
        processGenes(sr);
        
        System.out.println("\n\n----------------------------------------\n\n");
        fr = new FileResource("dna/mansmall.fa");
        dna = fr.asString().toUpperCase();
        sr = getAllGenes(dna);
        processGenes(sr);
        
    }
}















