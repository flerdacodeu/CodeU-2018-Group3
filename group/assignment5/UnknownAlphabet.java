import java.util.*;

class UnknownAlphabet{

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

    private static Set<Character> getStartNodes(Map<Character, List<Character>> graph) {
        Set<Character> startNodes = new HashSet<>(graph.keySet());
        for(Character node:graph.keySet()){
            startNodes.removeAll(graph.get(node));
        }
        return startNodes;
    }

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
