import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.AfterClass;
import java.util.HashMap;

public class HuffmanEncoderTest
{
    @Test(timeout=1000)
    public void testBasic()
    {
        HashMap<Character, Integer> m = new HashMap<Character,Integer>();
        m.put('E',75);
        m.put('e',73);
        m.put('T',72);
        m.put('t',71);
        m.put('A',70);
        m.put('a',69);
        m.put('O',68);
        m.put('o',67);
        m.put('I',66);
        m.put('i',65);
        m.put('N',64);
        m.put('n',63);
        m.put('S',62);
        m.put('s',61);
        m.put('R',60);
        m.put('r',59);
        m.put('H',58);
        m.put('h',57);
        m.put(' ',56);
        m.put('.',55);
        m.put(',',54);
        m.put('D',53);
        m.put('d',52);
        m.put('L',51);
        m.put('l',50);
        m.put('U',49);
        m.put('u',48);
        m.put('C',47);
        m.put('c',46);
        m.put('M',45);
        m.put('m',44);
        m.put('F',43);
        m.put('f',42);
        m.put('Y',41);
        m.put('y',40);
        m.put('W',39);
        m.put('w',38);
        m.put('G',37);
        m.put('g',36);
        m.put('P',35);
        m.put('p',34);
        m.put('B',33);
        m.put('b',32);
        m.put('V',31);
        m.put('v',30);
        m.put('K',29);
        m.put('k',28);
        m.put('X',27);
        m.put('x',26);
        m.put('Q',25);
        m.put('q',24);
        m.put('J',23);
        m.put('j',22);
        m.put('Z',21);
        m.put('z',20);
        m.put('0',19);
        m.put('1',18);
        m.put('2',17);
        m.put('3',16);
        m.put('4',15);
        m.put('5',14);
        m.put('6',13);
        m.put('7',12);
        m.put('8',11);
        m.put('9',10);
        m.put('!',9);
        m.put('?',8);
        m.put('\'',7);
        m.put('-',6);
        m.put('\"',4);
        m.put('\n',3);
        m.put('(',2);
        m.put(')',1);
        HuffmanEncoder h = new HuffmanEncoder(m);
        String decodedString = h.decodeString("110001001100100100110110101111101010011110011010111010000100110101111100101100111100110101100001001001111101111000110000001110101101100111110000011110101011001110011111000000101001100000")
        assertEquals("Data Structures Yonsei University", decodedString);

        h.decodeFile("test_input.txt", "test_output.txt");
    }
}