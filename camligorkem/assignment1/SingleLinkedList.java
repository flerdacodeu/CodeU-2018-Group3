import java.util.Scanner;

public class SingleLinkedList<T> {

    private LinkedListNode<T> head=null;

    public SingleLinkedList(LinkedListNode<T> head) {
        this.head = head;
    }

    public SingleLinkedList() {
    }

    /**
     * Adds new node to the beginning of the list
     * @param  value: Takes the value of a new node to be inserted
     */
    public void addFirst(T value){
        LinkedListNode<T> node= new LinkedListNode<T>(value,null);
        if(head==null)
            head=node;
        else{
            node.setNext(head);
            head=node;
        }
    }

    /**
     * Adds new node to the end of the list
     * @param  value: Takes the value of a new node to be inserted
     */
    public void addLast(T value){
        LinkedListNode<T> node= new LinkedListNode<T>(value,null);
        LinkedListNode<T> iter=head;
        if(head == null)
            head = node;
        else{
            while(iter.getNext()!=null)
                iter= iter.getNext();
            iter.setNext(node);
        }
    }

    /**
     * Removes from the beginning of the list
     * @return return the node deleted.
     */
    public LinkedListNode<T> removeFirst(){
        if(head == null)
            return null;
        LinkedListNode<T> nodeToDelete = head;
        head = head.getNext();
        return nodeToDelete;

    }

    /**
     * Removes from the end of the list
     * @return return the node deleted.
     */
    public LinkedListNode<T> removeLast(){
        if(head == null)  // 0 element
            return null;
        else if(head.getNext()==null){  //only 1 element
            head = null;
            return head;
        }else{   // at least 2 elements
            LinkedListNode<T> iter = head;
            LinkedListNode<T> nodeToDelete =null;
            while(iter.getNext().getNext()!=null){
                iter = iter.getNext();
            }
            nodeToDelete = iter.getNext();
            iter.setNext(null);
            return nodeToDelete;
        }
    }

    /**
     * Prints all the list elements
     */
    public void printAll(){
        LinkedListNode<T> iter =head;
        while(iter!=null){
            System.out.println(iter.getValue());
            iter= iter.getNext();
        }
    }

    /**
     * @return return the head value of the list.
     */
    public T peek() {
        return head.getValue();
    }

    /**
     * Finds and return the kthLastNode of the list
     * @param k : kth number of node from the end of the list to be return
     * @return node which is kth last in the list
     */
    public LinkedListNode<T> getKthLastNode (int k){
        if(k < 0)
            return null;
        if(head == null)
            return null;

        LinkedListNode<T> iter1 = head;
        LinkedListNode<T> iter2 = head;
        int i=0;
        while(iter1!=null && i < k){
            iter1= iter1.getNext();
            i++;
        }
        if(iter1 == null)
            return null;
        while(iter1.getNext() !=null){
            iter1= iter1.getNext();
            iter2 = iter2.getNext();
        }
        return iter2;
    }

    public static void main(String[] args) {
        String s1 = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Program finds the kth Last element in a single linked list First you need to create a linked list.");
        int option, k ;
        SingleLinkedList<String> singleList= new SingleLinkedList<String>();
        while(true){
            System.out.println("Enter a new element to your list (enter exit if you don't want to add any other element):");
            s1 = scan.nextLine();
            if((s1.toLowerCase()).equals("exit"))
                break;
            singleList.addLast(s1);
        }
        System.out.println("Your list is:");
        System.out.println("-------------");
        singleList.printAll();
        System.out.println("-------------");
        LinkedListNode<String> kthLastNode;
        do{
            System.out.println("Enter a k value to see kth last element of your list (k must be integer)");
            k = scan.nextInt();
            scan.nextLine();
            kthLastNode = singleList.getKthLastNode(k);
            if(kthLastNode== null)
                System.out.println("Invalid k node (either it has a negative value or exceed the size of your list )");
            else
                System.out.println("The kth last node value is: "+kthLastNode.getValue());
            System.out.println("Do you want to find another k? (enter 1 to find, or any other integer to exit)");
            option = scan.nextInt();
            scan.nextLine();
        }while(option==1);

    }
}
