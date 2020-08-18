import org.example.Word;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
public class Testes {

    @Test
    public void TestSecret(){
        System.out.println("-------Test Secret");
        Word w = new Word();
        w.setSecret("SecretMessage.txt");
        String secret=w.getSecret();
        System.out.println(secret);
    }

    @Test
    public void TestAssociated(){
        System.out.println("-------Test Associated");
        Word w = new Word();
        w.setAssociated();
        Character[] assocciateds=w.getAssociated();
        int p=0;
        for (char associated:assocciateds) {
            System.out.print("p " + p + " ");
            p++;
            System.out.println(associated);
        }
    }

    @Test
    public void TestMakeKey(){
        System.out.println("-------Test makeKey");
        Word w = new Word();
        w.setAssociated();
        String m = w.makeKey(0,"VICCENZO");
        System.out.println(m);
        //WJDDFO.P
        assertEquals(m, "VICCENZO");
    }



    @Test
    public void finalTest(){
        System.out.println("-------Test");
        Word w = new Word();
        w.setWords(".//pt//pt.txt");
        w.setAssociated();
        w.setSecret("SecretMessage.txt");
        w.makekey0to5();

    }
}
