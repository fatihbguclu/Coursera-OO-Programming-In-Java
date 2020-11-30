
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    
    public int countGenes(String dna){
        int startIndex = 0;
        int numberOfGenes = 0;
        while(true){
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
                break;
            }else{
                numberOfGenes += 1;
                startIndex = dna.indexOf(currentGene , startIndex) + currentGene.length();
            }
        }
        
        return numberOfGenes;
    }
    
    public void testCountGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println(countGenes(dna) + " gene found in " + dna);
    }
    
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1 || index >= input.length() - 3){
               break;
        }
        System.out.println("index = " + index);
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+3);
        System.out.println("index after updating " + index);
        
    }
}
   public void test() {
    findAbc("abcdabc");
}
}














