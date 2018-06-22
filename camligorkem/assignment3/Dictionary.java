// Trie Node class to create Dictionary based on prefixes
class TrieNode{
    TrieNode[] letters;
    boolean isWord;
    public TrieNode(){
        this.letters = new TrieNode[26];
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
            int ind = c -'a';
            if(pref.letters[ind] != null){
                pref = pref.letters[ind];
            }else{
                TrieNode temp = new TrieNode();
                pref.letters[ind]=temp;
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
        if(p != null) {
            if (p.isWord)
                return true;
        }
        return false;
    }

    /**
     * Checks whether a given string is a prefix in the dictionary.
     * @param prefix is the string that we want to know whether is a prefix or not
     */
    public boolean isPrefix(String prefix) {
        TrieNode p = searchNode(prefix);
        if(p == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Search dictionary to see whether a trienode exists for given string
     * (could be either word or prefix)
     * @param s is the string to be search in the tree to see if it exists
     */
    public TrieNode searchNode(String s){
        TrieNode r = root;
        for(int i=0; i <s.length(); i++){
            char c = s.charAt(i);
            int ind = c-'a';
            if(r.letters[ind]!= null){
                r = r.letters[ind];
            }else{
                return null;
            }
        }
        if(r == root)
            return null;
        return r;
    }
}
