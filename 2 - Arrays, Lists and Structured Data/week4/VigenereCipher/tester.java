
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class tester {
    
    public void tester(){
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource("data/oslusiadas.txt");
        String encrypted = cc.encrypt(fr.asString());
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        CaesarCracker ck = new CaesarCracker('a');
        System.out.println(ck.decrypt(encrypted));
    }
}
