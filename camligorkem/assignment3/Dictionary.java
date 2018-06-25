import java.util.HashMap;
import java.util.Map;

// Trie Node class to create Dictionary based on prefixes
class TrieNode{
    Map<Character, TrieNode> letters;
    boolean isWord;
    public TrieNode(){
        this.letters = new HashMap<>();
    }
}

public class Dictionary {
    private TrieNode root;
     public Dictionary(){
         root = new TrieNode();
     }

    /**
     * Inserts the given word into dictonary along with its prefixes:
     * CAR inserts C, CA, CAR. If doesn't allow duplicates, if the element
     * already exists it skips adding a new node for that one.
     * @param word the word to be inserted.
     */
    public void insert(String word) {
        word = word.toLowerCase();
        TrieNode pref = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(pref.letters.get(c) != null){
                pref = pref.letters.get(c);
            }else{
                TrieNode temp = new TrieNode();
                pref.letters.put(c,temp);
                pref = temp;
            }
        }
        pref.isWord=true;
    }

    /**
     * Checks whether a given string is a word in the dictionary.
     * @param str is the string that we want to know whether a word or not
     */
    public boolean isWord(String str) {
        TrieNode p = searchNode(str);
        return(p != null && p.isWord);
    }

    /**
     * Checks whether a given string is a prefix in the dictionary.
     * @param prefix is the string that we want to know whether is a prefix or not
     */
    public boolean isPrefix(String prefix) {
        return (searchNode(prefix)!=null);
    }

    /**
     * Search dictionary to see whether a trienode exists for given string
     * (could be either word or prefix)
     * @param s is the string to be search in the tree to see if it exists
     */
    private TrieNode searchNode(String s){
        TrieNode r = root;
        for(int i=0; i <s.length(); i++){
            char c = s.charAt(i);
            if(r.letters.get(c)!= null){
                r = r.letters.get(c);
            }else{
                return null;
            }
        }
        return r == root ? null : r;
    }
}
