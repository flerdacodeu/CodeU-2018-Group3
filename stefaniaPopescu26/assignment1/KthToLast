/*
    In case of k being greater than the size of
    the list, my code will return null.
 */

public class KthToLast {
    class Node{
        /*
            This is the structure that is stored in the list.
         */
        private Object data;
        private Node next;

        public Node() {
            this(0);
        }

        public Node(Object data) {
            this.data = data;
            next = null;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    class MyLinkedList<T> {
        /*
            This is the list structure.
         */
        private Node head;

        public MyLinkedList() {
            head = new Node();
        }

        public boolean isEmpty() {
            return head.getNext() == null;
        }

        public void add(T data) {

            Node current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(new Node(data));
        }

        public void remove(T data){

            Node current = head.getNext();
            Node prev = head;

            if (!isEmpty()){

                while (current != null){
                    if (current.getData().equals(data)){
                        prev.setNext(current.getNext());
                        current = prev.getNext();
                        continue;
                    }

                    current = current.getNext();
                    prev = prev.getNext();
                }
            }
        }

        public Node getHead(){

            if (isEmpty())
                return null;

            return head;
        }

        public String toString() {

            String listString = "";
            Node current = head;

            while (current.getNext() != null) {
                current = current.getNext();
                listString += current.getData() + " ";
            }

            return listString;
        }
    }

    public Object getKthToLast(MyLinkedList<Object> List, int k) {
        /*
            This method uses two references, for two nodes : one to the left and
            one to the right. The "distance" between left and right needs to be k
            and after that, they are moved in parallel. When right reaches the
            end of the list, the left one reached the Kth to last element in the list.
         */

        Node left = List.getHead();
        Node right = List.getHead();
        int distance = 0;

        while(distance < k && right.getNext() != null) {
            right = right.getNext();
            distance++;
        }

        /*
            If k is greater than the size of the list, it returns null.
         */
        if (distance != k) {
            return null;
        }

        while(right.getNext() != null) {
            right = right.getNext();
            left = left.getNext();
        }

        return left.getData();
    }

    public static void main(String[] args) {
        KthToLast instance = new KthToLast();
        MyLinkedList<Object> List = instance.new MyLinkedList<>();

        List.add('a');
        List.add('b');
        List.add('c');
        List.add('d');
        List.add(1);
        List.add(2);

        List.remove('c');

        System.out.println(instance.getKthToLast(List, 2));
    }
}
