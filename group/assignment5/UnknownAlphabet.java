import java.util.*;

public class UnknownAlphabet {

    /**
     * Takes a dictionary that contains ordered words and return the alphabet order in a Character List
     *
     * @param dictionary list of string that language's words.
     */
    public static List<Character> findUnkownAlphabet(List<String> dictionary) {
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
     *
     * @param dictionary      list of string that language's words.
     * @param ingoingEdgeMap  takes the initialized 0 incoming edge maps for chars and after every comparison if
     *                        we find a precendence between letters increments the corresponding letters incoming edge
     * @param dictionaryGraph takes the dictionary graph created to, it adds the chars that
     *                        have incoming edges to certain chars
     *                        Returns the alphabet order to its caller function
     */
    private static List<Character> findIncomingEdges(List<String> dictionary, Map<Character, Integer> ingoingEdgeMap, Map<Character, Set<Character>> dictionaryGraph) {
        for (int i = 0; i < dictionary.size() - 1; i++) {
            String w1 = dictionary.get(i);
            String w2 = dictionary.get(i + 1);

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    if (!dictionaryGraph.get(w1.charAt(j)).contains(w2.charAt(j))) {
                        dictionaryGraph.get(w1.charAt(j)).add(w2.charAt(j));
                        ingoingEdgeMap.put(w2.charAt(j), ingoingEdgeMap.get(w2.charAt(j)) + 1);
                    }
                    break;
                }
            }
        }
        List<Character> alp = topologicalSort(ingoingEdgeMap, dictionaryGraph);
        return alp;
    }

    /**
     * Does topological sorting with a use of queue and by doing a bfs.
     *
     * @param ingoingEdgeMap  use it in order to find the number of incoming edges and order between the chars
     * @param dictionaryGraph takes the dictionary graph in order to iterate through the chars that
     *                        has incoming edges between each other
     *                        Returns the alphabet order to its caller function
     */
    private static List<Character> topologicalSort(Map<Character, Integer> ingoingEdgeMap, Map<Character, Set<Character>> dictionaryGraph) {
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

    // ****** Challenge 2 **********
    /**
     * Checks, if a given dictionary is inconsistent
     * @param dictionary list of strings
     * @return minimal set of constraints that cannot be satisfied or empty Optional
     */
    public static Optional<Set<List<Character>>> checkInconsistency(List<String> dictionary) {
        Map<Character, Set<Character>> constraints = new HashMap<>();
        for (int i = 0; i < dictionary.size() - 1; i++) {
            String w1 = dictionary.get(i);
            String w2 = dictionary.get(i + 1);

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    if (!constraints.containsKey(dictionary.get(i).charAt(j))) {
                        // adds all the chars in the dictionary into the maps
                        constraints.put(dictionary.get(i).charAt(j), new HashSet<>());
                    }
                    constraints.get(w1.charAt(j)).add(w2.charAt(j));
                    break;
                }
            }
        }
        return checkForCircles(constraints);
    }

    /**
     * Checks for any circles in the constraints
     * @param constraints map ot the constraints
     * @return minimal set of constraints that cannot be satisfied or empty Optional
     */
    private static Optional<Set<List<Character>>> checkForCircles(Map<Character, Set<Character>> constraints) {
        for (Character before : constraints.keySet()) {
            for (Character after : constraints.get(before)) {
                Optional<List<Character>> path = findPath(constraints, after, before, new ArrayList<>(Arrays.asList(after)));
                if (path.isPresent()) {
                    Set<List<Character>> inconsistent = new HashSet<>();
                    inconsistent.add(new ArrayList<>(Arrays.asList(before, after)));
                    inconsistent.add(path.get());
                    return Optional.of(inconsistent);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Searches for a path in the constraints between from and to
     * @param constraints map of the constraints
     * @param from start node
     * @param to end node
     * @param path list, which represents the current path
     * @return the complete path, if it was found; Optional.empty otherwise
     */
    private static Optional<List<Character>> findPath(Map<Character, Set<Character>> constraints, Character from, Character to, List<Character> path) {
        if (from == to) {
            return Optional.of(path);
        }
        if (constraints.containsKey(from)) {
            for (Character after : constraints.get(from)) {
                path.add(after);
                Optional<List<Character>> foundPath = findPath(constraints, after, to, path);
                if (foundPath.isPresent()) {
                    return foundPath;
                }
                path.remove(after);
            }
        }
        return Optional.empty();
    }

    // ****** Challenge 3 **********
    /**
     * Takes a dictionary if it is consistent returns itself,
     * if not  returns a maximal (with as many words as possible) subset of
     * the dictionary that is consistent
     *
     * @param dictionary given dictionary
     * @return consistent dictionary
     */
    public static List<String> makeConsistent(List<String> dictionary) {
        //constraints minimal set of constraints on the alphabet that cannot be satisfied
        Optional<Set<List<Character>>> constraints = checkInconsistency(dictionary);
        if (!constraints.isPresent())  //consistent
            return dictionary;

        // Dictionary is inconsistent
        // returns a maximal (with as many words as possible) subset of
        // the dictionary that is consistent.
        List<String> subsetDictionary = dictionary;

        Iterator<List<Character>> iterator = constraints.get().iterator();
        while (iterator.hasNext()) {
            List<Character> befConstraint = iterator.next();
            if (iterator.hasNext()) {
                List<Character> aftConstraint = iterator.next();
                StringBuilder sb = new StringBuilder();

                for (int i = 0, j = aftConstraint.size() - 1; i < befConstraint.size() && j >= 0; i++, j--) {
                    if (befConstraint.get(i) == aftConstraint.get(j))
                        sb.append(befConstraint.get(i));
                }

                List<String> curDic = subsetDictionary;
                for (String s : subsetDictionary) {
                    if (s.startsWith(sb.toString())) {
                        curDic.remove(s);
                        Optional<Set<List<Character>>> res = checkInconsistency(curDic);
                        if (!res.isPresent())
                            return curDic;
                    }
                }
                subsetDictionary = curDic;
            }
        }
        return subsetDictionary;
    }
}
