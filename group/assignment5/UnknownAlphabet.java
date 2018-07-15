import java.util.*;

class UnknownAlphabet{

    /**
     * Builds the alphabet for a given dictionary
     * Using a graph implemented as a map with nodes as keys and lists of successors as values
     * @param dictionary ordered list with all words in the dictionary
     * @return alphabet for the given dictionary
     */
    static List<Character> findAlphabet(List<String> dictionary){
        Map<Character, List<Character>> graph = new HashMap<>();
        insertWords(graph, dictionary);

        List<Character> alphabet = new ArrayList<>();
        Set<Character> nodesToVisit = getStartNodes(graph);
        while (!nodesToVisit.isEmpty()){
            Character node = nodesToVisit.iterator().next();
            alphabet.add(node);
            nodesToVisit.remove(node);
            graph.remove(node);
            nodesToVisit.addAll(getStartNodes(graph));
        }
        return alphabet;
    }

    /**
     * Creates a set of all nodes that have no predecessors for a given graph
     * @param graph map with nodes as keys and lists of successors as values
     * @return set of nodes which have no predecessors
     */
    private static Set<Character> getStartNodes(Map<Character, List<Character>> graph) {
        Set<Character> startNodes = new HashSet<>(graph.keySet());
        for(Character node:graph.keySet()){
            startNodes.removeAll(graph.get(node));
        }
        return startNodes;
    }

    /**
     * Inserts a given lists of words into a given graph
     * @param graph map with nodes as keys and lists of successors as values
     * @param words list of words to be inserted
     */
    private static  void insertWords(Map<Character, List<Character>> graph, List<String> words) {
        String lastword = "";
        for(String  word: words){
            boolean edgeAdded = false;
            for(int i=0; i<word.length(); i++){
                if(! graph.containsKey(word.charAt(i))){
                    graph.put(word.charAt(i), new ArrayList<>());
                }
                if(!edgeAdded && i < lastword.length() && lastword.charAt(i) != word.charAt(i)){
                    graph.get(lastword.charAt(i)).add(word.charAt(i));
                    edgeAdded=true;
                }
            }
            lastword = word;
        }
    }
}
