/*
 * Name: Kim, Jin Seol
 * Student ID: 2011133024
 * Don't forget to remove the package line (if it exists)
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * Do not import anything else
 */

public class HuffmanEncoder
{
    char[] alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,!?'-\" \n)(".toCharArray();
    /*
     * alphabet holds all of the characters that may appear in our tests
     * You should not edit alphabet in any way
     *
     * Note that some of the characters (quotes, newline) are represented as two
     * characters in text. These are still read as one character when
     * scanned or written so be sure to account for these special characters.
     *
     * You may add any other instance variable that you wish
     *
     */

    public HuffmanEncoder(HashMap<Character, Integer> frequencyTable)
    {
        /*
         * Constructor for our Huffman Encoder if we are given a predefined
         * frequency table from which to construct our encoding tree
         *
         * frequencyTable - HashMap<Character, Integer> - A hashmap containing
         *     each character and its frequency. If a character does not appear
         *     in the table, you can assume it will not appear in any test
         */
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for(int i=0;i<alphabet.length;i++)
        {
            if(frequencyTable.get(alphabet[i])==null)
                continue;
            pq.add(new HuffmanNode(frequencyTable.get(alphabet[i]),alphabet[i]));
        }
        while(pq.size()>1)
        {
            HuffmanNode temp1 = pq.remove();
            HuffmanNode temp2 = pq.remove();
            HuffmanNode newNode = new HuffmanNode(temp1.getFrequency() + temp2.getFrequency());
            newNode.setLeft(temp1);
            newNode.setRight(temp2);
            pq.add(newNode);
        }
        codeTreeRoot = pq.remove();
    }

    public HuffmanEncoder(String filePath)
    {

        /*
         * Constructor for our Huffman Encoder if we are given a file path from
         * which to infer a frequency table
         *
         * We have provided some skeleton code that you can use (or not) in
         * order to scan the file.
         *
         * You can assume that the file will never have a trailing newline
         *     meaning the very last character will not be a newline character
         *
         * filePath - String - the path to a text file that should be scanned
         *     and used to construct our encoding tree. If a character does not
         *     appear in the file, you can assume it will not appear in any test
         */

        HashMap<Character, Integer> frequencyTable = new HashMap<>();
        HashMap<Character, Integer> alphabetCode = new HashMap<>();
        int index=0;
        for(char c : alphabet)
        {
            alphabetCode.put(c, index++);
        }
        int[] freq = new int[alphabet.length];

        Scanner scan = null;

        try
        {
            scan = new Scanner(new BufferedReader(new FileReader(filePath)));
            char[] characters;
            while (scan.hasNextLine())
            {
                String s = scan.nextLine();
                characters = s.toCharArray();
                for(char c : characters)
                {
                    /*
                     *
                     * Write your code here for parsing each of the characters
                     * from the input file
                     *
                     * The char[] array, characters, holds all of the characters
                     * in the current line of the file
                     *
                     * You are free to delete all of this and implement it in
                     * whatever way you want, but we don't recommend it.
                     *
                     */
                    freq[alphabetCode.get(c)]++;

                }
                freq[alphabetCode.get('\n')]++;
            }
            freq[alphabetCode.get('\n')]--;
        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        finally
        {
            if (scan != null)
            {
                scan.close();
            }
        }

        for(char c : alphabet)
        {
            frequencyTable.put(c,freq[alphabetCode.get(c)]);
        }
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for(int i=0;i<alphabet.length;i++)
        {
            if(frequencyTable.get(alphabet[i])==null)
                continue;
            if(frequencyTable.get(alphabet[i])==0)
                continue;
            pq.add(new HuffmanNode(frequencyTable.get(alphabet[i]),alphabet[i]));
        }
        while(pq.size()>1)
        {
            HuffmanNode temp1 = pq.remove();
            HuffmanNode temp2 = pq.remove();
            HuffmanNode newNode = new HuffmanNode(temp1.getFrequency() + temp2.getFrequency());
            newNode.setLeft(temp1);
            newNode.setRight(temp2);
            pq.add(newNode);
        }
        codeTreeRoot = pq.remove();
    }

    public double compressionRatio(String s)
    {
        /*
         * s will always be the original, decoded string
         *
         * Return the fraction of the length of the encoded string compared to
         * the (length of s)*16 (since we care about the number of bits)
         */
        return encodeString(s).length()/(s.length()*16);
    }

    public String encodeString(String s)
    {
        /*
         * Return the string containing the encoding of the provided string
         */
        getMapping();
        char[] input = s.toCharArray();
        String output = "";
        for(char c : input)
        {
            output = output.concat(this.codeMap.get(c));
        }
        return output;
    }

    public String decodeString(String s)
    {
        /*
         * Given an encoded string, return the original, decoded string
         */
        char[] input = s.toCharArray();
        String output = "";
        HuffmanNode curNode = codeTreeRoot;
        for(char c : input)
        {
            if(c=='0')
            {
                curNode = curNode.getLeft();
            }
            else if(c=='1')
            {
                curNode = curNode.getRight();
            }
            if(curNode.getLeft()==null&&curNode.getRight()==null)
            {
                output = output.concat(curNode.getCharacter().toString());
                curNode = codeTreeRoot;
            }

        }
        return output;
    }

    public void encodeFile(String inputFilePath, String outputFilePath)
    {
        /*
         * Read the file at inputFilePath and write the encoding of text to a
         * file stored at outputFilePath 
         */
        Scanner scan =null;
        BufferedWriter bw = null;
        String input = "";
        try
        {
            scan = new Scanner(new BufferedReader(new FileReader(inputFilePath)));
            bw = new BufferedWriter(new FileWriter(outputFilePath));
            while(scan.hasNext())
            {
                input = input.concat(scan.nextLine());
                input = input.concat("\n");
            }
            input = input.substring(0,input.length()-1);
            bw.write(encodeString(input));
            bw.close();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        finally
        {
            if (scan != null)
            {
                scan.close();
            }
        }

    }

    public void decodeFile(String inputFilePath, String outputFilePath)
    {
        /*
         * Read the file at inputFilePath (containing encoded text) and write
         * the decoded text to a file at outputFilePath
         */
        Scanner scan =null;
        BufferedWriter bw = null;
        String input = "";
        try
        {
            scan = new Scanner(new BufferedReader(new FileReader(inputFilePath)));
            bw = new BufferedWriter(new FileWriter(outputFilePath));
            while(scan.hasNext())
            {
                input = input.concat(scan.nextLine());
                input = input.concat("\n");
            }
            input = input.substring(0,input.length()-1);
            bw.write(decodeString(input));
            bw.close();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        finally
        {
            if (scan != null)
            {
                scan.close();
            }
        }
    }

    public HashMap<Character, String> getMapping()
    {
        /*
         * Return a hashmap containing each character in the encoder and its
         * encoding string
         */
        this.codeMap = new HashMap<>();
        mappingHelper(codeTreeRoot, "");
        return this.codeMap;
    }

    //////////////////////////////
    // Helper variable, methods //
    //////////////////////////////
    private HuffmanNode codeTreeRoot;
    private HashMap<Character, String> codeMap;

    private HuffmanNode mappingHelper(HuffmanNode hn, String s)
    {
        HuffmanNode tempNode = hn;
        String tempString = s;
        if(hn.getLeft()==null&&hn.getRight()==null)
            codeMap.put(tempNode.getCharacter(),tempString);
        if(hn.getLeft()!=null)
            mappingHelper(hn.getLeft(), tempString.concat("0"));
        if(hn.getRight()!=null)
            mappingHelper(hn.getRight(), tempString.concat("1"));
        return tempNode;
    }

}

/*
 *  ================================
 *  Do not modify below this comment
 *  ================================
 */

class HuffmanNode implements Comparable<HuffmanNode>
{
    private int frequency;
    private Character ch;
    private HuffmanNode left, right;

    public HuffmanNode(int frequency)
    {
        this.frequency = frequency;
    }

    public HuffmanNode(int frequency, Character ch)
    {
        this.frequency = frequency;
        this.ch = ch;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public Character getCharacter()
    {
        return ch;
    }

    public HuffmanNode getLeft()
    {
        return left;
    }

    public HuffmanNode getRight()
    {
        return right;
    }

    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }

    public void setCharacter(Character ch)
    {
        this.ch = ch;
    }

    public void setLeft(HuffmanNode n)
    {
        this.left = n;
    }

    public void setRight(HuffmanNode n)
    {
        this.right = n;
    }

    public String toString()
    {
        if(this.ch != null)
            return Character.toString(this.ch) + String.format(":%d",frequency);
        else
            return String.format("null:%d", frequency);
    }

    public int compareTo(HuffmanNode n)
    {
        if(this.getFrequency() == n.getFrequency())
        {
            if(this.getCharacter() != null && n.getCharacter() != null)
            {
                return this.getCharacter().compareTo(n.getCharacter());
            }

            else if(this.getCharacter() == null && n.getCharacter() != null)
            {
                return 1;
            }

            else if(this.getCharacter() != null && n.getCharacter() == null)
            {
                return -1;
            }

            else
            {
                return 0;
            }
        }
        return this.getFrequency()-n.getFrequency();
    }
}
