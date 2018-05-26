import java.util.AbstractMap;
import java.util.Map;

public class Q2SingleLinkedList {
    public static void main(String... args) {
        int listLength = 10;
        Node<Integer> list = createLinkedList(0,listLength);
        for(int k=0; k < listLength; k++){
            System.out.println("k="+k+"\t -> "+getKthLastElement(list,k).toString());
        }
    }

    /**
     * Creates a new SingleLinkedList with count elements using recursion
     * @param value content of the first node
     * @param count Number of elements in the new SingleLinkedList
     * @return new SingleLinkedList, if count > 0, else it will return null
     */
    private static Node<Integer> createLinkedList(int value, int count) {
        if(count <= 0){
            return null;
        }
        System.out.println("Value: "+value +"\tPosition from last: "+count);
        if(count == 1){
            return  new Node<>(value,null);
        }
        return new Node<>(value, createLinkedList(++value,--count));
    }

    /**
     * Gets the kth to the last element of a given SingleLinkedList
     * @param head First element in the SingleLinkedList
     * @param k
     * @param <T> Type of the content of the SingleLinkedList
     * @return Content of the kth to the last element
     */
    private static <T extends Object> T getKthLastElement(Node<T> head, int k) {
        return getKthLastElementRecursive(head, k).getKey();
    }

    /**
     * Gets the kth to the last element of a given SingleLinkedList using recursion
     * @param head First element in the SingleLinkedList
     * @param k
     * @param <T> Type of the content of the SingleLinkedList
     * @return Content of the kth to the last element and the level of the "recursive rise"
     */
    private static <T extends Object> Map.Entry<T, Integer> getKthLastElementRecursive(Node<T> head, int k) {
        if (head.getNext() == null) {
            return new AbstractMap.SimpleEntry<>(head.getContent(), 0);
        }
        Map.Entry<T, Integer> result = getKthLastElementRecursive(head.getNext(), k);
        if (result.getValue() == k) {
            return result;
        }
        return new AbstractMap.SimpleEntry<>(head.getContent(), result.getValue() + 1);
    }
}
