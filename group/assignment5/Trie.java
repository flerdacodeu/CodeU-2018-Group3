
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
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
 *  In order to find an alphabet from a given dictionary I thought of using
 * the same Trie structure as in assignment 3 and make a BFS. Keeping letters
 * in a visited array helps me not have duplicates, and the order is correct as 
 * it respects the order of words in dictionary.
 *  
 */
 
public class Trie {
    
    class TrieNode
    {
        HashMap<Integer,TrieNode> children = new HashMap<>();
      
        boolean isEndOfWord;
        
        ArrayList<Character> buildAlphabet() {
            Queue<TrieNode> queue = new LinkedList();
            ArrayList<Character> visited = new ArrayList();
            queue.add(this);
            bfs(queue, visited);
            return visited;
        }

        private void bfs(Queue<TrieNode> queue, ArrayList<Character> visited) {
            while(!queue.isEmpty()) {
                TrieNode current = queue.poll();
                
                // get children of current node
                Iterator it = current.children.keySet().iterator();
                while(it.hasNext()) {
                    Integer key = (int)it.next();
                    if(!visited.contains((char)(key + 65))) {
                        visited.add((char)(key + 65));
                    }
                    
                    // get children of each children 
                    // the only way to get the letter in a node
                    // is to discover children from parent
                    Iterator childrenIt = current.children.get(key).children.keySet().iterator();
                    while(childrenIt.hasNext()) {
                        Integer currentChild = (int)childrenIt.next();
                        
                        if(!visited.contains((char)(currentChild + 65))) {
                            visited.add((char)(currentChild + 65));
                        }
                        queue.add(current.children.get(key).children.get(currentChild));
                    } 
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
    
    void buildAlphabet() {
        root.buildAlphabet();
    }
    
}
