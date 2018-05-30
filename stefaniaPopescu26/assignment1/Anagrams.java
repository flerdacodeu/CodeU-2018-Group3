import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
    My code can handle simple anagrams, both case sensitive and
    insensitive, as well as anagrams of sentences.

    For the input:
        false
        Hi, ar8e you ok1?
        k1O ,OuY aE8r? Ih
    My code will return true.

    For the input:
        true
        Hi, ar8e you ok1?
        k1O ,OuY aE8r? Ih
    My code will return false.

    In the result sentence the symbols should also be somewhere,
    with the condition of respecting the length of the first
    sentence(no other spaces can be added).
*/

public class Anagrams {
    private static String INPUT_FILE;
    private String sentence1, sentence2;
    private boolean caseSensitiveON;

    public Anagrams(String file) {
        INPUT_FILE = file;
    }

    private void readInput() {
        try {
            Scanner sc = new Scanner(new File(INPUT_FILE));

            caseSensitiveON = sc.nextBoolean();
            sc.nextLine();
            sentence1 = sc.nextLine();
            sentence2 = sc.nextLine();

            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getWords(String str) {
        /*
            The returned array contains sorted words.
         */
        int i = 0;

        StringTokenizer st = new StringTokenizer(str, ",.!? ");
        String words[] = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            words[i++] = (String) st.nextElement();
        }

        return words;
    }

    private boolean areAnagrams(String s1, String s2) {
        /*
            If the two strings have different sizes, they cannot be anagrams.
         */
        if (s1.length() != s2.length())
            return false;

        /*
            If the order of the letters in the strings is the same, they
            cannot be anagrams.
         */
        if (s1.equals(s2))
            return false;

        /*
            The next lines check if the strings contain the same letters.
         */
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        s1 = new String(c1);
        s2 = new String(c2);

        return (s1.equals(s2));
    }

    private String[] transformArrays(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (!caseSensitiveON)
                words[i] = words[i].toLowerCase();

            char[] c = words[i].toCharArray();
            Arrays.sort(c);
            words[i] = String.copyValueOf(c);
        }

        return words;
    }

    public boolean solve() {
        readInput();

        if (!caseSensitiveON) {
            sentence1 = sentence1.toLowerCase();
            sentence2 = sentence2.toLowerCase();
        }

        /*
            If the sentences are not anagrams, there's no point to continue
            checking the words.
         */
        if (!areAnagrams(sentence1, sentence2))
            return false;

        /*
            The next lines check if the words are anagrams. The symbols have
            been checked above.
         */
        String[] words1 = getWords(sentence1);
        String[] words2 = getWords(sentence2);

        if (words1.length != words2.length)
            return false;

        /*
            This loop checks if the second sentence contains a word that
            has the letters in the same order as they are in the first sentence.
         */
        for (int i = 0; i < words2.length; i++) {
            if (sentence1.contains(words2[i]))
                return false;
        }

        words1 = transformArrays(words1);
        words2 = transformArrays(words2);

        Arrays.sort(words1);
        Arrays.sort(words2);

        return Arrays.equals(words1, words2);
    }

    public static void main(String[] args) {
        System.out.println(new Anagrams("anagrams.in.txt").solve());
    }
}
