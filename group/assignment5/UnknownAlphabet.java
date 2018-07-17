import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class UnknownAlphabet {
    private LinkedList<String> dictionary;

    private void readDictionary(String filename) {
        try {
            Scanner read = new Scanner(new File(filename), "Unicode");

            while (read.hasNext()) {
                dictionary.add(read.next());
            }

            read.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UnknownAlphabet(String filename) {
        dictionary = new LinkedList<>();
        readDictionary(filename);
    }

    /*
        Adds all the letters of a word to the graph, as isolated nodes.
     */
    private void addWord(Graph g, String word) {
        for (int i = 0; i < word.length(); i++) {
            g.addEdge(word.charAt(i), word.charAt(i));
        }
    }

    /*
        This method finds the first different letter between two words and adds
        an edge in the graph to suggest that the first different letter from
        word1 should be before the first different letter form word2 in the
        alphabet. Then, it adds the rest of the letters to the graph.
     */
    private void addWords(Graph g, String word1, String word2) {
        int i = 0;

        while (i < word1.length() && i < word2.length()) {
            if (word1.charAt(i) != word2.charAt(i))
                break;
            i++;
        }

        if (i < word1.length() && i < word2.length()) {
            g.addEdge(word1.charAt(i), word2.charAt(i));
        }

        addWord(g, word1);
        addWord(g, word2);
    }

    public LinkedList<Character> getAlphabet() {
        Iterator<String> iterator = dictionary.iterator();
        Graph g = new Graph();
        String word1;
        String word2;

        if (dictionary.size() == 0) {
            System.out.println("Empty dictionary!");
        } else if (dictionary.size() == 1) {
            addWord(g, dictionary.getFirst());
        } else {
            word1 = iterator.next();

            while (iterator.hasNext()) {
                word2 = iterator.next();
                addWords(g, word1, word2);
                word1 = word2;
            }
        }
        return g.topologicalSort();
    }

    public static void main(String[] args) {
        UnknownAlphabet alphabet = new UnknownAlphabet("moreWords.txt");
        System.out.println(alphabet.getAlphabet());
    }
}
