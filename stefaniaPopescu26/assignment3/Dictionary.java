import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/*
    For the Dictionary class I implemented the Trie data structure.
    Notes:
        n = number of words in the dictionary
        m = maximum length of a word
    Complexities:
        add - O(m)
        readData - O(n * m)
        isPrefix - O(m)
        isWord - O(m)
 */

public class Dictionary {

    class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    private TrieNode root;

    public Dictionary() {
        root = new TrieNode();
    }

    public Dictionary(String filename) {
        root = new TrieNode();
        readData(filename);
    }

    private void readData(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));

            int nrWords = sc.nextInt();
            if (nrWords != 0)
                sc.nextLine();

            for(int i = 0; i < nrWords; i++) {
                add(sc.next());
                if (i != nrWords - 1)
                    sc.nextLine();
            }

            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void add(String word) {
        if (word.isEmpty()) {
            return;
        }

        TrieNode node = root;

         do {
            char letter = word.charAt(0);

            if (!node.children.containsKey(letter)) {
                node.children.put(letter, new TrieNode());
            }

            node = node.children.get(letter);
            word = word.substring(1);
        } while (!word.isEmpty());

         node.isWord = true;
    }

    public boolean isPrefix(String prefix) {
        if (prefix.isEmpty()) {
            return false;
        }

        TrieNode node = root;

        do {
            char letter = prefix.charAt(0);

            if (!node.children.containsKey(letter)) {
                return false;
            }

            node = node.children.get(letter);
            prefix = prefix.substring(1);
        } while (!prefix.isEmpty());

        return true;
    }

    public boolean isWord(String word) {
        if (word.isEmpty()) {
            return false;
        }

        TrieNode node = root;

        do {
            char letter = word.charAt(0);

            if (!node.children.containsKey(letter)) {
                return false;
            }

            node = node.children.get(letter);
            word = word.substring(1);
        } while (!word.isEmpty());

        return node.isWord;
    }

    public boolean isEmpty() {
        return root.children.isEmpty();
    }
}
