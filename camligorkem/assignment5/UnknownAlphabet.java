import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class UnknownAlphabet {

    /**
     * Takes a dictionary that contains ordered words and return the alphabet order in a Character List
     * @param dictionary list of string that language's words.
     */
    public static List<Character> findUnkownAlphabet (List<String> dictionary) {
        Map<Character, Set<Character>> dictionaryGraph = new HashMap<>();
        Map<Character, Integer> ingoingEdgeMap = new HashMap<>();

        // Creating the char graph for the dictionary
        for (int i = 0; i < dictionary.size(); i++) {
             for (int j = 0; j < dictionary.get(i).length(); j++) {
                if (!dictionaryGraph.containsKey(dictionary.get(i).charAt(j))) {
                    // adds all the chars in the dictionary into the maps
                    dictionaryGraph.put(dictionary.get(i).charAt(j), new HashSet<>());
                    // initially ingoingEdges to the chars are 0
                    ingoingEdgeMap.put(dictionary.get(i).charAt(j), 0);
                }
            }
        }
        List<Character> alphabet = findIncomingEdges(dictionary, ingoingEdgeMap, dictionaryGraph);
        return alphabet;
    }

    /**
     * Compares each pair of neighboring words to find the incoming edges to a character
     * This is important for topological sorting and understanding the precendence of the characters
     * @param dictionary list of string that language's words.
     * @param ingoingEdgeMap takes the initialized 0 incoming edge maps for chars and after every comparison if
     *        we find a precendence between letters increments the corresponding letters incoming edge
     * @param dictionaryGraph takes the dictionary graph created to, it adds the chars that
     *         have incoming edges to certain chars
     * Returns the alphabet order to its caller function
     */
    private static List<Character> findIncomingEdges(List<String> dictionary, Map<Character, Integer> ingoingEdgeMap, Map<Character, Set<Character>> dictionaryGraph  ){
        for (int i = 0; i < dictionary.size()-1; i++) {
            String w1 = dictionary.get(i);
            String w2 = dictionary.get(i+1);

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++){
                if(w1.charAt(j) != w2.charAt(j)){
                    if (!dictionaryGraph.get(w1.charAt(j)).contains(w2.charAt(j))) {
                        dictionaryGraph.get(w1.charAt(j)).add(w2.charAt(j));
                        ingoingEdgeMap.put(w2.charAt(j), ingoingEdgeMap.get(w2.charAt(j)) + 1);
                    }
                    break;
                }
            }
        }
        List<Character> alp = topologicalSort(ingoingEdgeMap,dictionaryGraph );
        return alp;
    }

    /**
     * Does topological sorting with a use of queue and by doing a bfs.
     * @param ingoingEdgeMap use it in order to find the number of incoming edges and order between the chars
     * @param dictionaryGraph takes the dictionary graph in order to iterate through the chars that
     *        has incoming edges between each other
     * Returns the alphabet order to its caller function
     */
    private static List<Character> topologicalSort(Map<Character, Integer> ingoingEdgeMap, Map<Character, Set<Character>> dictionaryGraph){
        // topological sort!!!!!
        List<Character> alphabet = new LinkedList<>();
        Queue<Character> q = new LinkedList<>();
        for (char c : ingoingEdgeMap.keySet()) {
            if (ingoingEdgeMap.get(c) == 0)   //has no incoming edge can be added to the alphabet
                q.add(c);
        }

        while (!q.isEmpty()) {
            char c = q.remove();
            alphabet.add(c);
            for (char incomingEdge : dictionaryGraph.get(c)) {
                ingoingEdgeMap.put(incomingEdge, ingoingEdgeMap.get(incomingEdge) - 1);
                if (ingoingEdgeMap.get(incomingEdge) == 0) {
                    q.add(incomingEdge);
                }
            }
        }
        return alphabet;
    }
}
