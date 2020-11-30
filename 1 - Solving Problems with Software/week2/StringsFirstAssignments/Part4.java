
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    public static void main(){
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        URLResource ur = new URLResource(url);
        String result = "";
        
        for(String line : ur.lines()){
            
            int index = line.indexOf("youtube.com");
            
            if(index != -1){
                System.out.println(line);
                
                int firstIndexOfQuotes = line.indexOf("=\"");
                int secondIndexOfQuotes = line.indexOf("\">", firstIndexOfQuotes + 1);
                
                result = line.substring(firstIndexOfQuotes + 2 , secondIndexOfQuotes);
                
                //System.out.println(firstIndexOfQuotes + "  " + secondIndexOfQuotes);
                System.out.println(result + "\n");
            }
        }
    }
}
