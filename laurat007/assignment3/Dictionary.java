
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura
 */
class Dictionary {
    Trie dictionary = new Trie();
    
    Dictionary(TreeSet<String> wordsSet) {
        Iterator it = wordsSet.iterator();
        while(it.hasNext()) {
            dictionary.insert(it.next().toString());

        }
    }
    
    boolean isPrefix(String prefix) {
        if(prefix == "")
            return false;
        if (dictionary.searchWord(prefix) != 0)
            return true;
        return false; 
    }
    
    boolean isWord(String word) {
        if(word == "")
            return false;
        if (dictionary.searchWord(word) == 2)
            return true;
        return false;
    }
}
