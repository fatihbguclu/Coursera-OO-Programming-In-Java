
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    private int[] getCountLetters(String message){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
    
    public String breakingCaesarCipher(String input){
        int[] freq = getCountLetters(input);
        int maxIndex = maxIndex(freq);
        int dkey = getKey(maxIndex);
        System.out.println("Key is " + dkey);
        CaesarCipher cc = new CaesarCipher(26 - dkey);
        return cc.encrypt(input);
    }
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        CaesarCipher cc  = new CaesarCipher(15);
        //String encrypted = cc.encrypt(fr.asString());
        String encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println("Encrypted Message : " + encrypted);
        System.out.println("Decrypted Message : " + cc.decrypt(encrypted));
        System.out.println("Break CaesarCipher : " + breakingCaesarCipher(encrypted));
    }
}
