import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

/*
    My code can handle simple anagrams, both case sensitive and
    insensitive, as well as anagrams of sentences.
    I chose to consider symbols as "words", for example:
    For the input:
        Hi, ar8e you ok1?
        k1O ,OuY aE8r? Ih
    My code will return true. So in the result sentence the symbols
    should also be somewhere, with the condition of respecting the
    length of the first sentence(no other spaces can be added).
*/

public class Anagrams {
    private static String INPUT_FILE;
    private String sentence1, sentence2;

    public Anagrams(String file) {
        INPUT_FILE = file;
    }

    private void readInput() {
        try {
            Scanner sc = new Scanner(new File(INPUT_FILE));

            sentence1 = sc.nextLine();
            sentence2 = sc.nextLine();

            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String transformString(String str) {
        /*
            Transforms a string to a key for the hashtable.
            It needs to be lower case and sorted.
         */
        str = str.toLowerCase();
        char[] c =  str.toCharArray();
        Arrays.sort(c);

        return String.copyValueOf(c);
    }

    public boolean solve() {
        int i;
        String key;
        readInput();

        /*
            If the two sentences have different sizes, they cannot be anagrams.
         */
        if (sentence1.length() != sentence2.length()) {
            return false;
        }

        /*
            If the order of the letters in the word(s) is the same, the sentences
            cannot be anagrams.
         */
        if (sentence1.toLowerCase().equals(sentence2.toLowerCase())) {
            return false;
        }

        Hashtable<String, Integer> hash = new Hashtable<>();
        /*
            The key is a word from the sentence, transformed with transformString method,
            or a string that contains one symbol from delim, apart from space.
            The value is the frequency of the key.
         */

        String aux = "";
        String delim = ",.!? ";

        /*
            I evaluate the first sentence and fill the hashtable.
            I consider the symbols as being words and also include them in the hashtable.
         */
        for (i = 0; i < sentence1.length(); i++) {
            Character ch = sentence1.charAt(i);
            if (delim.contains(ch.toString())) {
                if (ch != ' ') {
                    /* ch is a character from delim, but it's not space, so if the key
                        already exists, it increments the frequency number, else adds it
                        to the hashtable.

                     */

                    if (hash.containsKey(ch.toString())) {
                        hash.put(ch.toString(), hash.get(ch.toString()) + 1);
                    }
                    else {
                        hash.put(ch.toString(), 1);
                    }
                }
                else {
                    /*
                        ch is a space.
                        If the string is not empty, it transforms the string into key and
                        does the same procedure as above.
                     */
                    if (aux.length() != 0) {
                        key = transformString(aux);
                        if (hash.containsKey(key)) {
                            hash.put(key, hash.get(key) + 1);
                        }
                        else {
                            hash.put(key, 1);
                        }
                        aux = "";
                    }
                }
            }
            else {
                /*
                    ch is not a punctuation symbol, so it's added to the current word.
                 */
                aux = aux.concat(ch.toString());
            }
        }

        /*
            This part is for the last word of the first sentence because the for loop
            ends before adding it to the hashtable.
         */
        if (aux.length() != 0) {
            key = transformString(aux);
            if (hash.containsKey(key)) {
                hash.put(key, hash.get(key) + 1);
            }
            else {
                hash.put(key, 1);
            }
        }
        aux = "";

        /*
            Here, the process is similar to the one for the first sentence. The difference is
            that instead of incrementing the frequency, it is decremented. When a key has 0
            frequency, it is removed from the hashtable. If the second sentence contains a key
            that has not been included from the first sentence, it is clear that the sentences
            are not anagrams and it returns false instantly.
         */
        for (i = 0; i < sentence2.length(); i++) {
            Character ch = sentence2.charAt(i);
            if (delim.contains(ch.toString())) {
                if (ch != ' ') {
                    if (hash.containsKey(ch.toString())) {
                        hash.put(ch.toString(), hash.get(ch.toString()) - 1);
                        if (hash.get(ch.toString()) == 0) {
                            hash.remove(ch.toString());
                        }
                    } else {
                        return false;
                    }
                } else {
                    if (aux.length() != 0) {

                        if (sentence1.toLowerCase().contains(aux.toLowerCase())) {
                            return false;
                        }

                        key = transformString(aux);
                        if (hash.containsKey(key)) {
                            hash.put(key, hash.get(key) - 1);
                            if (hash.get(key) == 0) {
                                hash.remove(key);
                            }
                        } else {
                            return false;
                        }
                        aux = "";
                    }
                }
            }
            else {
                aux = aux.concat(ch.toString());
            }
        }
        /*
            This part is also for the last word from the second sentence.
         */
        if (sentence1.contains(aux)) {
            return false;
        }

        key = transformString(aux);
        if (hash.containsKey(key)) {
            hash.put(key, hash.get(key) - 1);
            if (hash.get(key) == 0) {
                hash.remove(key);
            }
        }
        else {
            return false;
        }

        /*
            If the hashtable is empty -> True,
            otherwise -> False (it means that something that has been added to the hashtable
            from the first sentence, has not been removed in the second sentence)
         */
        return (hash.isEmpty());
    }

    public static void main(String[] args) {
        System.out.println(new Anagrams("anagrams.in.txt").solve());
    }
}
