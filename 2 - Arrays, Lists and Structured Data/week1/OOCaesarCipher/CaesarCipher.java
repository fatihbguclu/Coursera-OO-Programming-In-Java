
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shifted;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shifted = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder output = new StringBuilder(input);
        for(int i = 0; i<output.length(); ++i){
            char ch = output.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(ch));
            if(idx != -1){
                if(ch == Character.toUpperCase(ch)){
                    output.setCharAt(i, shifted.charAt(idx));
                }else{
                    output.setCharAt(i, Character.toLowerCase(shifted.charAt(idx)));
                }
            }
        }
        return output.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
    private int[] getCountLetters(String message){
        int[] counts = new int[26];
        for(int i = 0; i<message.length(); ++i){
            char ch = Character.toUpperCase(message.charAt(i));
            int idx = alphabet.indexOf(ch);
            if(idx != -1){
                counts[idx] += 1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] arr){
        int maxIndex = 0;
        for(int i = 0; i<arr.length; ++i){
            if(arr[i] > arr[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private int getKey(int maxIndex){
        int dkey = maxIndex - 4;
        if(maxIndex < 4){
            dkey = 26 - (4 - maxIndex);
        }
        return dkey;
    }
}





