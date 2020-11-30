
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shifted1;
    private String shifted2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shifted1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shifted2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder output = new StringBuilder(input);
        
        for(int i = 0; i<output.length(); ++i){
            char ch = output.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(ch));
            if(idx != -1){
                if(i % 2 == 0){
                    if(ch == Character.toUpperCase(ch)){
                        output.setCharAt(i,shifted1.charAt(idx));
                    }else{
                        output.setCharAt(i,Character.toLowerCase(shifted1.charAt(idx)));
                    }
                }else{
                    if(ch == Character.toUpperCase(ch)){
                        output.setCharAt(i,shifted2.charAt(idx));
                    }else{
                        output.setCharAt(i,Character.toLowerCase(shifted2.charAt(idx)));
                    }
                }
            }
        }
        
        return output.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cct.encrypt(input);
    }
}
