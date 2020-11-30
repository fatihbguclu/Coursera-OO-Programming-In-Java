
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
    
    public int howMany(String strA, String strB){
        
        int startIndex = 0;
        int foundIndex = strB.indexOf(strA, startIndex);
        int count = 0;
        
        if(foundIndex == -1){
            return count;
        }else{
            count += 1;
        }
        
        while(foundIndex != -1){
            startIndex = foundIndex + strA.length();
            foundIndex = strB.indexOf(strA, startIndex);
            //System.out.println(foundIndex);
            if(foundIndex != -1) {count += 1;}
        }
        
    
        return count;
    }
    
    public void testHowMany(){
        String strA = "GAA";
        String strB = "ATGAACGAATTGAATC";
        System.out.println(strA + " occurs in " + strB + " " + howMany(strA,strB) + " times");
        
        strA = "AA";
        strB = "ATAAAA";
        System.out.println(strA + " occurs in " + strB + " " + howMany(strA,strB) + " times");
        
        
    }
}














