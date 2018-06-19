package edu.codeU.assignment1.list;

/**
 * Single linked list class.
 * @param <T> is the type of values inside list.
 */
public class LinkedList<T> {
    /**
     * The size contains the number of elements in list.
     */
    private int size;
    /**
     * The head is the first node in list.
     */
    private Node head;

    private class Node{
        Node next;
        T value;
    }

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    /**
     * Check if list is empty.
     * @return the true/false according the size of list.
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * @return the size list.
     */
    public int size(){
        return size;
    }

    /**
     * Add new node in the head of the list.
     * @param value is the value in new node.
     */
    public void push(T value){
        Node prev_head = head;
        head = new Node();
        head.value = value;
        head.next = prev_head;
        size++;
    }

    /**
     * Find tke kth last value.
     * @param k: the number of the kth last element.
     * @return value at the kth last position.
     * @throws FindListElemException
     */
    public T findTheKthLastElement(int k) throws FindListElemException{
        if (this.isEmpty() || k < 0 || k > this.size - 1){
            throw new FindListElemException("The list is empty or the k is not correct parameter.");
        }
        int step = this.size - k - 1;
        Node temp = head;
        while (step != 0){
            temp = temp.next;
            step--;
        }
        return temp.value;
    }
}
