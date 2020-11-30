
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> charCount;

    public CharactersInPlay(){
        characters = new ArrayList<String>();
        charCount = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = characters.indexOf(person);
        if(index == -1){
            characters.add(person);
            charCount.add(1);
        }else{
            int value = charCount.get(index);
            charCount.set(index, value + 1);
        }
    }
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int index = line.indexOf('.');
            
            if(index != -1 && Character.isUpperCase(line.charAt(index-1)) 
            && Character.isLetter(line.charAt(index-1))){
                update(line.substring(0,index));
                //System.out.println(line.substring(0,index));
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i<charCount.size(); ++i){
            int countValue = charCount.get(i);
            if(num1 <= num2){
                if(countValue >= num1 && countValue <= num2){
                    System.out.println(characters.get(i) + "\t" + charCount.get(i));
                }
            }else{
                if(countValue >= num2 && countValue <= num1){
                    System.out.println(characters.get(i) + "\t" + charCount.get(i));
                }
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        for(int i = 0; i<characters.size(); ++i){
            System.out.println(characters.get(i) + "\t" + charCount.get(i));
        }
        System.out.println("\n\n\n ------------------------------------");
        charactersWithNumParts(10,15);
    }
}
