
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    private String halfOfString(String input, int start){
        String output = "";
        for(int i = start; i<input.length(); i+=2){
            output += input.charAt(i); 
        }
        return output;
    }
    
    private int[] countLetters(String input){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[26];
        for(int i = 0; i<input.length(); ++i){
            char ch = input.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(ch));
            if(idx != -1){
                counts[idx] += 1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] arr){
        int maxIndex = 0;
        for(int i = 0; i<arr.length; ++i){
            if(arr[i] >= arr[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private int getKey(String input){
        int[] freq = countLetters(input);
        int maxIndex = maxIndex(freq);
        int Dkey = maxIndex -4;
        if(maxIndex < 4){
            Dkey = 26 - (4 - maxIndex);
        }
        return Dkey;
    }
    
    private String breakCaesarCipher(String input){
        String s1 = halfOfString(input,0);
        String s2 = halfOfString(input,1);
        int dkey1 = getKey(s1);
        int dkey2 = getKey(s2);
        System.out.println("key1 : " + dkey1 + " key2 : " + dkey2);
        CaesarCipherTwo cct = new CaesarCipherTwo(26-dkey1, 26-dkey2);
        return cct.encrypt(input);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        CaesarCipherTwo cct = new CaesarCipherTwo(14,24);
        //String encrypted = cct.encrypt(fr.asString());
//        String encrypted = cct.encrypt("");
//        String decrypted = cct.decrypt(fr.asString());
//        System.out.println("Encrypted Message : " + encrypted);
//        System.out.println("\nDecrypted Message : " + decrypted);
        //System.out.println("\nBroken Message : " + breakCaesarCipher(encrypted));
        System.out.println("\nBroken Message : " + breakCaesarCipher(fr.asString()));
    }
}












