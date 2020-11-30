
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key ) + alphabet.substring(0, key );
        
        for(int i = 0; i<encrypted.length(); ++i){
            char c = encrypted.charAt(i);
            
            int idx = alphabet.indexOf(Character.toUpperCase(c));
            if(idx != -1){
                if(c == Character.toUpperCase(c)){
                    encrypted.setCharAt(i, shifted.charAt(idx));
                }else{
                    encrypted.setCharAt(i, Character.toLowerCase(shifted.charAt(idx)));
                }
            }
        }
        
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shifted2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        
        for(int i = 0; i<input.length(); i++){
            if(i%2 == 0){
                char c = encrypted.charAt(i);
                int idx = alphabet.indexOf(Character.toUpperCase(c));
                if(idx != -1){
                    if(c == Character.toUpperCase(c)){
                        encrypted.setCharAt(i, shifted1.charAt(idx));
                    }else{
                        encrypted.setCharAt(i, Character.toLowerCase(shifted1.charAt(idx)));
                    }
                }
            }else{
                char c = encrypted.charAt(i);
                int idx = alphabet.indexOf(Character.toUpperCase(c));
                if(idx != -1){
                    if(c == Character.toUpperCase(c)){
                        encrypted.setCharAt(i, shifted2.charAt(idx));
                    }else{
                        encrypted.setCharAt(i, Character.toLowerCase(shifted2.charAt(idx)));
                    }
                }
            }
        }
        
        return encrypted.toString();
    }
    
    public void testCeaser(){
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        //System.out.println(encrypt(message,15));
        System.out.println(encryptTwoKeys(message,8,21));
    }
    
    public int[] countLetters(String message){
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] freq = new int[26];
        for(int i = 0; i<message.length(); ++i){
            char ch = Character.toUpperCase(message.charAt(i));
            int idx = alph.indexOf(ch);
            if(idx != -1){
                freq[idx] += 1;
            }
        }
        return freq;
    }
    
    public int maxDex(int[] arr){
        int max = 0;
        for(int i = 0; i<arr.length; ++i){
            if(arr[i] > max){
                max = i;
            }
        }
        return max;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freq = countLetters(encrypted);
        int maxDex = maxDex(freq);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
    }
}
