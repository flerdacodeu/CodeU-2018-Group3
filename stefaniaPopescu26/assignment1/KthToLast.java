/*
    In case of k being greater than the size of
    the list, my code will return null.
 */

public class KthToLast {
    class Node<T>{
        /*
            This is the structure that is stored in the list.
         */
        private T data;
        private Node<T> next;

        public Node() {
            this(null);
        }

        public Node(T data) {
            this.data = data;
            next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    class MyLinkedList<T> {
        /*
            This is the list structure.
         */
        private Node<T> head;

        public MyLinkedList() {
            head = new Node();
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void add(T data) {

            if (isEmpty()){
                head.setData(data);
                return;
            }

            Node<T> current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(new Node(data));
        }

        public void remove(T data){

            Node<T> current = head.getNext();
            Node<T> prev = head;

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

        public Node<T> getHead(){
            return head;
        }

        public String toString() {

            String listString = "";
            Node<T> current = head;

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

        Node<Object> left = List.getHead();
        Node<Object> right = List.getHead();
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

        System.out.println(instance.getKthToLast(List, 0));
    }
}
