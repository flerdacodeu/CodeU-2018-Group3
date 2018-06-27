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
        TrieNode[] children = new TrieNode[MAX_LETTERS];
      
        boolean isEndOfWord;
         
        TrieNode(){
            isEndOfWord = false;
            for (int i = 0; i < MAX_LETTERS; i++)
                children[i] = null;
        }
        
        void print() {
            for(int i = 0; i < children.length; i++) {
                if(children[i] != null) {
                    children[i].print();
                }
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
            if (aux.children[index] == null) {
                aux.children[index] = new TrieNode();
            }
            aux = aux.children[index];
        }
      
        // mark last node as leaf
        aux.isEndOfWord = true;
    }
      
    int searchWord(String key)
    {   
        TrieNode aux = root;
      
        for (int level = 0; level < key.length(); level++)
        {
            int index = key.charAt(level) - 'A';
            
            // the word given is longer than the 
            // word in dictionary that should match
            if (aux.children[index] == null)
                return 0;
      
            aux = aux.children[index];
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
