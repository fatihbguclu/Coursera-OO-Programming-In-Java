
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public String lastPart(String stringA, String stringB){
        int startIndex = stringB.indexOf(stringA);
        String lastPart;
        
        if(startIndex == -1){
            return stringB;
        }
        else{
            lastPart = stringB.substring( startIndex + stringA.length() );
            return lastPart;
        }
        
    }
    
    
    public boolean twoOccurrences(String stringA, String stringB){
        int occur = 0;
        int startIndex = 0;
        
        for(int x = 0;  x < stringB.length(); x++){
            startIndex = stringB.indexOf(stringA,startIndex);
            System.out.println(startIndex);
            if(startIndex == -1){break;}

            startIndex += stringA.length();
            occur++;
        }
        System.out.println("occurs " + occur + " times");
        if(occur >= 2){
            return true;
        }else{
            return false;
        }
        
        
    }
    
    public void testing(){
        String stringA = "by";
        String stringB = "A story by Abby Long";
        boolean isOccur = twoOccurrences(stringA, stringB);
        
        if(isOccur == true){
            System.out.println("'" + stringA + "'" + " appears in " 
                            + "'" + stringB + "'" + " at least 2 times");
        }else{
            System.out.println("'" + stringA + "'" + " does not appears in " 
                            + "'" + stringB + "'");
        }
        
        System.out.println("The part of the string after " 
                            + "'" + stringA + "'" + " in " 
                            + "'" + stringB + "'" +" is " 
                            + lastPart(stringA,stringB)
                            );
        
        
    }
}
