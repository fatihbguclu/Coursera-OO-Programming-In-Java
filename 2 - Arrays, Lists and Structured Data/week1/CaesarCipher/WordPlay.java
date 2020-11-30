
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        if((ch == 'A' || ch == 'a') || (ch == 'E' || ch == 'e')
        || (ch == 'I' || ch == 'i') || (ch == 'O' || ch == 'o')
        || (ch == 'U' || ch == 'u')){
            return true;
        }else{
            return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder newStr = new StringBuilder(phrase);
        
        for(int i = 0; i<phrase.length(); ++i){
            char c = newStr.charAt(i);
            if(isVowel(c)){
                newStr.setCharAt(i,ch);
            }
        }
        
        return newStr.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder newStr = new StringBuilder(phrase);
        
        for(int i = 1; i<=phrase.length(); ++i){
            char c = newStr.charAt(i-1);
            if(Character.toLowerCase(c) == ch || Character.toUpperCase(c) == ch){
                if(i%2 != 0){
                    newStr.setCharAt(i-1, '*');
                }else{
                    newStr.setCharAt(i-1, '+');
                }
            }
            
        }
        
        return newStr.toString();
    }
}
