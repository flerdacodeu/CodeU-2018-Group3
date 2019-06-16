
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura
 *  Implemented a Trie structure to build a dictionary.
 *  The Trie works like this: when a word is added, the trie instantiates 
 * its nth child (n = first letter of the word - 'A' in ASCII codes), so it 
 * goes increasing the height of the trie, simultaneously with the length 
 * of the word. If the nth child is already instantiated, the algorithm goes
 * to the next step.
 *  
 */
 
public class Trie {
     
    static final int MAX_LETTERS = 26;
    
    class TrieNode
    {
        HashMap<Integer,TrieNode> children = new HashMap<>();
      
        boolean isEndOfWord;
        
        void print() {
            Iterator it = children.keySet().iterator();
            while(it.hasNext()) {
                TrieNode current = (TrieNode) it.next();
                System.out.println(children.get(current));
                current.print();
            }
        }
    };
      
    TrieNode root; 
    
    Trie() {
        root = new TrieNode();
    }
    
    void insert(String key)
    {
        TrieNode aux = root;
      
        for (int level = 0; level < key.length(); level++)
        {
            int index = key.charAt(level) - 'A';
            
            // need new child instance
            if (!aux.children.containsKey(index)) {
                aux.children.put(index, new TrieNode());
            }
            aux = aux.children.get(index);
        }
      
        // mark last node as leaf
        aux.isEndOfWord = true;
    }
    
    /*  this method returns:
        2 if the key is a word (it is contained
          in the trie and its last letter is marked 
          as end of the word
        1 if the key is a prefix (it is contained
          in the trie but its last letter is not marked 
          as end of the word)
        0 if the key is neither word or prefix
    */
    int searchWord(String key)
    {   
        TrieNode aux = root;
      
        for (int level = 0; level < key.length(); level++)
        {
            int index = key.charAt(level) - 'A';
            
            // the word given is longer than the 
            // word in dictionary that should match
            if (aux.children.get(index) == null)
                return 0;
      
            aux = aux.children.get(index);
        }
        
        // word
        if (aux != null && aux.isEndOfWord)
           return 2;
        
        // prefix
        if (aux != null && !aux.isEndOfWord)
          return 1;
        
        // neither word or prefix
        return 0;
    }
    
    void print() {
        root.print();
    }
    
}
