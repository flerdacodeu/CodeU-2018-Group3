import java.util.Iterator;
import java.util.LinkedList;

public class DisjointSet {
    private LinkedList<LinkedList<Node>> sets;

    /*
        I didn't put a pointer to the next node in the Node class
        because I used an iterator to search through the lists,
        instead of using that pointer.
     */
    class Node {
        private int number;
        private Node representative;

        public Node(int number) {
            this.number = number;
            representative = null;
        }

        public void setRepresentative(Node repr) {
            representative = repr;
        }

        public int getNumber() {
            return number;
        }
    }

    public DisjointSet() {
        sets = new LinkedList<>();
    }

    public void makeSet(int value) {
        LinkedList<Node> list = new LinkedList<>();
        Node node = new Node(value);

        node.setRepresentative(node);
        list.add(node);
        sets.add(list);
    }

    public LinkedList<Node> find(int value) {
        Iterator<LinkedList<Node>> i = sets.iterator();

        while (i.hasNext()) {
            LinkedList<Node> list = i.next();
            Iterator<Node> j = list.iterator();

            while (j.hasNext()) {
                Node node = j.next();
                if (node.getNumber() == value) {
                    return list;
                }
            }
        }
        return null;
    }


    private void unionHelper(LinkedList<Node> list1, LinkedList<Node> list2) {
        Node repr = list2.getFirst();
        Iterator<Node> iterator = list1.iterator();

        while(iterator.hasNext()) {
            Node node = iterator.next();
            node.setRepresentative(repr);
            list2.add(node);
        }
    }

    public void union(int value1, int value2) {
        LinkedList<Node> list1 = find(value1);
        LinkedList<Node> list2 = find(value2);

        if (list1.size() <= list2.size()) {
            unionHelper(list1, list2);
            sets.remove(list1);
        } else {
            unionHelper(list2, list1);
            sets.remove(list2);
        }
    }

    public int getCountSets() {
        return sets.size();
    }
}
