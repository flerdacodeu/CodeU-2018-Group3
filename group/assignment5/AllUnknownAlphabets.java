import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class AllUnknownAlphabets {

    /**
     * Takes a dictionary that contains ordered words and returns all the alphabets in a List of Character Lists
     * @param dictionary list of string that language's words.
     */
    public static List<List<Character>> findAllUnknownAlphabets (List<String> dictionary) {
        Map<Character, Set<Character>> dictionaryGraph = new HashMap<>();
        Map<Character, Integer> ingoingEdgeMap = new HashMap<>();
        Map<Character, Boolean> visited = new HashMap<>();

        // Creating the char graph for the dictionary
        for (int i = 0; i < dictionary.size(); i++) {
            for (int j = 0; j < dictionary.get(i).length(); j++) {
                if (!dictionaryGraph.containsKey(dictionary.get(i).charAt(j))) {
                    char letter = dictionary.get(i).charAt(j);
                    // adds all the chars in the dictionary into the maps
                    dictionaryGraph.put(letter, new HashSet<>());
                    // initially ingoingEdges to the chars are 0
                    ingoingEdgeMap.put(letter, 0);
                    // initially none of the nodes is visited
                    visited.put(letter, false);
                }
            }
        }

        return findAllTopSorts(dictionary, ingoingEdgeMap, dictionaryGraph, visited);
    }

    /**
     * Compares each pair of neighboring words to find the incoming edges to a character
     * This is important for topological sorting and understanding the precedence of the characters
     * @param dictionary list of string that language's words.
     * @param ingoingEdgeMap takes the initialized 0 incoming edge maps for chars and after every comparison if
     *        we find a precedence between letters increments the corresponding letters incoming edge
     * @param dictionaryGraph takes the dictionary graph created to, it adds the chars that
     *         have incoming edges to certain chars
     * @param visited represents the evidence of the visited nodes and it is sent as parameter because of the time
     *         complexity, to avoid going through all the chars again
     * Returns all the alphabets to its caller function
     */
    private static List<List<Character>> findAllTopSorts(List<String> dictionary,
                                                         Map<Character, Integer> ingoingEdgeMap,
                                                         Map<Character, Set<Character>> dictionaryGraph,
                                                         Map<Character, Boolean> visited) {
        List<List<Character>> result = new LinkedList<>();

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

        allTopSorts(ingoingEdgeMap,dictionaryGraph, result, visited, new LinkedList<>());
        return result;
    }

    /**
     * Does topological sorting and backtracking, in order to obtain all the alphabets.
     * @param ingoingEdgeMap use it in order to find the number of incoming edges and order between the chars
     * @param dictionaryGraph takes the dictionary graph in order to iterate through the chars that
     *        has incoming edges between each other
     * @param result used to store the resulting alphabets
     * @param visited used to keep evidence of the visited nodes
     * @param list used to form all the alphabets using backtracking
     * Adds all the alphabets to result list
     */
    private static void allTopSorts(Map<Character, Integer> ingoingEdgeMap, Map<Character, Set<Character>> dictionaryGraph,
                             List<List<Character>> result, Map<Character, Boolean> visited, LinkedList<Character> list){

        boolean done = false;

        for (char c : dictionaryGraph.keySet()) {
            if (ingoingEdgeMap.get(c) == 0 && !visited.get(c)) {
                for (char incomingEdge : dictionaryGraph.get(c)) {
                    ingoingEdgeMap.put(incomingEdge, ingoingEdgeMap.get(incomingEdge) - 1);
                }

                list.add(c);
                visited.put(c, true);
                allTopSorts(ingoingEdgeMap, dictionaryGraph, result, visited, list);

                visited.put(c, false);
                list.removeLast();
                for (char incomingEdge : dictionaryGraph.get(c)) {
                    ingoingEdgeMap.put(incomingEdge, ingoingEdgeMap.get(incomingEdge) + 1);
                }

                done = true;
            }
        }

        if (!done) {
            result.add((LinkedList)list.clone());
        }
    }
}
