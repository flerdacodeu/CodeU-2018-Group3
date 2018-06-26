import java.util.ArrayList;
import java.util.List;

/**
 * Dictionary implemented as a prefix tree
 */
class Dictionary {

    private PrefixTreeNode root;

    Dictionary(List<String> words) {
        root = new PrefixTreeNode("", false);
        insertWords(words);
    }

    /**
     * Inserts a list of words to the dictionary
     *
     * @param words list of words, which should be inserted
     */
    private void insertWords(List<String> words) {
        for (String word : words) {
            insertWord(word.toLowerCase());
        }
    }

    /**
     * Inserts a new word to the dictionary
     * @param word
     */
    private void insertWord(String word) {
        PrefixTreeNode currentNode = root;
        boolean nextNodeFound = true;
        while (nextNodeFound) {
            nextNodeFound = false;
            for (PrefixTreeNode node : currentNode.children) {
                if (word.substring(0, node.value.length()).equals(node.value)) {
                    currentNode = node;
                    nextNodeFound = true;
                    break;
                }
            }
        }
        for (int i = currentNode.value.length() + 1; i <= word.length(); i++) {
            PrefixTreeNode node = new PrefixTreeNode(word.substring(0, i), i == word.length());
            currentNode.addChild(node);
            currentNode = node;
        }
    }

    /**
     * Checks if a given word is a word in the dictionary
     *
     * @param word
     * @return true, if the word is a word in the dictionary, false otherwise
     */
    boolean isWord(String word) {
        return searchWord(root, word.toLowerCase(), false);
    }

    /**
     * Checks if a given word is a prefix
     *
     * @param word
     * @return true, if the word is a prefix, false otherwise
     */
    boolean isPrefix(String word) {
        return searchWord(root, word.toLowerCase(), true);
    }

    /**
     * Checks if a given word is a prefix/word in the dictionary
     *
     * @param root         root node of the dictionary
     * @param word         word, which should be found
     * @param searchPrefix true to check if word is a prefix, false to check if word is a word
     * @return true, if the word is a prefix/word in the dictionary
     */
    private boolean searchWord(PrefixTreeNode root, String word, boolean searchPrefix) {
        if (root.value.equals(word)) {
            return searchPrefix || root.isWord;
        }
        if (root.children.isEmpty()) {
            return false;
        }
        for (PrefixTreeNode node : root.children) {
            if (word.substring(0, node.value.length()).equals(node.value)) {
                return searchWord(node, word, searchPrefix);
            }
        }
        return false;
    }

    /**
     * Represents one node of a prefix tree
     */
    private class PrefixTreeNode {
        private String value;
        private boolean isWord;
        private List<PrefixTreeNode> children;

        PrefixTreeNode(String value, boolean isWord) {
            this.value = value;
            this.isWord = isWord;
            this.children = new ArrayList<>();
        }

        private void addChild(PrefixTreeNode node) {
            children.add(node);
        }
    }
}