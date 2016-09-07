/*
Name: Jin Seol Kim (김진설)
Student ID: 2011133024
*/

import java.util.Arrays;

public class HW0
{
    private static int outcur = 0;
    private static String[] allPermutations;

    // helper functions
    /* calculate n! */
    private static int factorial(int n)
    {
        if (n == 0)
            return 1;
        int result = 1;
        for (int i = 1; i <= n; i++)
        {
            result *= i;
        }
        return result;
    }

    /* swap elements in character array */
    private static void swap(char[] carr, int a, int b)
    {
        char temp = carr[a];
        carr[a] = carr[b];
        carr[b] = temp;
    }

    /* calculate the number of cases */
    private static int numOfPermutations(String s)
    {
        int repeat = 1;
        int[] temp = new int[26];
        for (int i = 0; i < s.length(); i++)
        {
            temp[(int) s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++)
        {
            repeat *= factorial(temp[i]);
        }
        return factorial(s.length()) / repeat;
    }

    /* generate string permutations */
    private static void permutations(char[] carr, int cur, int length)
    {

        if (cur == length)
        {
            StringBuilder sb = new StringBuilder();
            allPermutations[outcur] = sb.append(carr).toString();
            outcur++;
        }
        for (int i = cur; i < length; i++)
        {
            swap(carr, i, cur);
            permutations(carr, cur + 1, length);
            swap(carr, i, cur);
        }
    }

    /* remove a repeating strings (for sorted) */
    private static String[] removeRepeat(String[] arr)
    {
        String[] output = new String[numOfPermutations(arr[0])];
        output[0] = arr[0];
        String test = arr[0];
        int outputcur = 1;
        for (int i = 1; i < arr.length; i++)
        {
            if (!arr[i].equals(test))
            {
                test = arr[i];
                output[outputcur++] = arr[i];
            }
        }
        return output;
    }
    // end of helper functions

    public static String[] printPermutations(String s)
    {
        int length = s.length();
        allPermutations = new String[factorial(length)];
        permutations(s.toCharArray(), 0, length);
        Arrays.sort(allPermutations);
        return removeRepeat(allPermutations);
		/*
        You should return a list of strings populated by the unique permutations
        of the input, s, in alphabetical order.
        */
    }

    /*
    Do not edit anything below this comment.
    */

    public static void main(String[] args)
    {
        String[] permutations = printPermutations(args[0]);
        for (String p : permutations)
        {
            System.out.println(p);
        }
    }
}
