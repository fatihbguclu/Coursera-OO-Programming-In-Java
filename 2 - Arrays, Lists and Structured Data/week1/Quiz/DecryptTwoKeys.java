
/**
 * Write a description of DecryptTwoKeys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class DecryptTwoKeys {
    public int[] countLetters(String message){
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] count = new int[26];
        for(int i = 0; i<message.length(); ++i){
            char ch = Character.toUpperCase(message.charAt(i));
            int idx = alph.indexOf(ch);
            if(idx != -1){
                count[idx] += 1;
            }
        }
        return count;
    }
    
    public int maxIndex(int[] arr){
        int maxIdx = 0;
        for(int i = 0; i<arr.length; ++i){
            if(arr[i] >= arr[maxIdx]){
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public String decrypt(String message){
        CaesarCipher cc = new CaesarCipher();
        int dkey = getDkey(message);
        
        System.out.println("Key is " + dkey);
        return cc.encrypt(message, 26-dkey);
    }
    
    public int getDkey(String message){
        int[] freq = countLetters(message);
        int maxIdx = maxIndex(freq);
        int dkey = maxIdx - 4;
        if(maxIdx < 4){
            dkey = 26 - (4 - maxIdx);
        }
        return dkey;
    }
    
    public String halfOfString(String message, int start){
        String result = "";
        for(int i = start; i<message.length(); i+=2){
            result += message.charAt(i);
        }
        return result;
    }
    
    public void decryptTwoKeys(String message){
        CaesarCipher cc = new CaesarCipher();
        String s1 = halfOfString(message,0);
        String s2 = halfOfString(message,1);
        int key1 = getDkey(s1);
        int key2 = getDkey(s2);
        System.out.println("Key 1: " + key1 + " Key 2: " + key2);
        System.out.println(cc.encryptTwoKeys(message, 26-key1, 26-key2));
    }
    
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource("data/mysteryTwoKeysPractice.txt");
        System.out.println("Encrypted Message : \n" + fr.asString());
        System.out.println("Decrpted Message : \n");
        decryptTwoKeys(fr.asString());
        /*CaesarCipher cc = new CaesarCipher();
        FileResource fr = new FileResource("data/smallHamlet.txt");
        
        String encrypted = cc.encryptTwoKeys(fr.asString(),10,13);
        System.out.println("Original Message : \n" + fr.asString());
        System.out.println("\n\nEncrypted Message : \n" + encrypted);
        System.out.println("\n\nDecrypted Message : \n");
        decryptTwoKeys(encrypted);*/
    }
}








