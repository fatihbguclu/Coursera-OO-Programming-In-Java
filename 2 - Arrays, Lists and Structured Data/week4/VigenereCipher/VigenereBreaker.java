import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String slice = "";
        for(int i = whichSlice; i<message.length(); i+=totalSlices){
            if(message.charAt(i) != -1){    
                slice += message.charAt(i);
            }
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i<klength; ++i){
            String slice = sliceString(encrypted,i,klength);
            int dkey = cc.getKey(slice);
            key[i] = dkey;
            //System.out.println(key[i]);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted,4,'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
        
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<>();
        for(String line : fr.words()){
            String words = line.toLowerCase();
            if(!dict.contains(words)){
                dict.add(words);
            }
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> map){
        int count = 0;
        String[] wordList = message.split("\\W");
        for(String word : wordList){
            String temp = word.toLowerCase();
            if(map.contains(temp)){
                count+=1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> map){
        int max = 0;
        String d = "";
        int keyLong = 0;
        for(int i = 57; i<58; ++i){
            int[] key = tryKeyLength(encrypted,i,'e');
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted,map);
            if(count > max){
                max = count;
                d = decrypted;
                keyLong = i;
            }   
        }
        System.out.println("key length used to encrypt is " + keyLong);
        return d;
    }
    
    public void breakVigenere2(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        FileResource frDic = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(frDic);
        
        String decrypt = breakForLanguage(message, dictionary);
        System.out.println("words in decrypted message is " + countWords(decrypt, dictionary));
        System.out.println(decrypt);
        
    }
}



