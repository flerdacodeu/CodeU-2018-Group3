import java.util.Optional;

public class Q2SingleLinkedList {
    public static void main(String... args) {
        int listLength = 10000;
        Node<Integer> list = createLinkedList(listLength);
        for (int k = -5; k < listLength + 5; k++) {
            int finalK = k;
            Optional<Node<Integer>> result = getKthLastElement(list, k);
            result.ifPresent(integerNode -> System.out.println("k=" + finalK + "\t -> " + integerNode.getContent()));
            if (!result.isPresent()) {
                System.out.println("k=" + finalK + "\t -> error");
            }
        }
    }

    /**
     * Creates a new SingleLinkedList with count elements
     *
     * @param count Number of elements in the new SingleLinkedList
     * @return new SingleLinkedList, if count > 0, else it will return null
     */
    private static Node<Integer> createLinkedList(int count) {
        if(count <= 0){
            return null;
        }
        Node<Integer> head = new Node<>(count-1, null);
        for(int i=count-1; i > 0; i--){
            head = new Node<>(i-1,head);
        }
        return head;
    }

    /**
     * Gets the kth to the last node of a given SingleLinkedList
     *
     * @param head First element in the SingleLinkedList
     * @param k
     * @param <T> Type of the content of the SingleLinkedList
     * @return Optional with Content of the kth to the last element or an empty Optional if k < 0 or k > length of the list
     */
    private static <T> Optional<Node<T>> getKthLastElement(Node<T> head, int k) {
        Node<T> last = head;
        if (k < 0) {
            return Optional.empty();
        }
        for (int i = 0; i < k; i++) {
            if (last.getNext() == null) {
                return Optional.empty();
            }
            last = last.getNext();
        }
        for (; last.getNext() != null; last = last.getNext()) {
            head = head.getNext();
        }
        return Optional.of(head);
    }
}
