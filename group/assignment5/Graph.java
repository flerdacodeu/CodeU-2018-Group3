import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    private LinkedList<LinkedList<Character>> adj;

    public Graph()
    {
        adj = new LinkedList<>();
    }

    public void addEdge(char v,char w) {
        Iterator<LinkedList<Character>> iterator = adj.iterator();

        while (iterator.hasNext()) {
            LinkedList<Character> list = iterator.next();
            if (list.getFirst() == v) {
                if (!list.contains(w)) {
                    list.add(w);
                }
                return;
            }
        }

        LinkedList<Character> list = new LinkedList<>();
        list.add(v);
        if (v != w) {
            list.add(w);
        }
        adj.add(list);
    }

    private void topologicalSortHelper(char v, LinkedList<Character> visited, Stack stack)
    {
        char i;
        LinkedList<Character> list;
        Iterator<LinkedList<Character>> iterator1 = adj.iterator();

        visited.add(v);

        while (iterator1.hasNext())
        {
            list = iterator1.next();
            if (list.getFirst() == v) {
                Iterator<Character> iterator2 = list.iterator();
                iterator2.next();

                while (iterator2.hasNext()) {
                    i = iterator2.next();
                    if (!visited.contains(i)) {
                        topologicalSortHelper(i, visited, stack);
                    }
                }
            }
        }
        stack.push(v);
    }

    public LinkedList<Character> topologicalSort()
    {
        Iterator<LinkedList<Character>> iterator = adj.iterator();
        LinkedList<Character> result = new LinkedList<>();
        LinkedList<Character> visited = new LinkedList<>();
        Stack<Character> stack = new Stack();
        LinkedList<Character> list;

        while (iterator.hasNext()) {
            list = iterator.next();
            if (!visited.contains(list.getFirst())) {
                topologicalSortHelper(list.getFirst(), visited, stack);
            }
        }

        while (!stack.empty()) {
            result.add(stack.pop());
        }

        return result;
    }
}
