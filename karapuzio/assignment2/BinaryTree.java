package edu.codeU.assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;


public class BinaryTree<T>{
    /**
     * The head is the first node in tree.
     */
    private Node head;
    /**
     * Traversal of binaty tree.
     */
    private TreeMap<T, Integer> traversal;
    /**
     * Constant of upper bound for values in tree.
     */
    private final static int UPPER_BOUND_VOF_VALUES = 1000;

    private class Node{
        Node left;
        Node right;
        T value;
    }

    public BinaryTree() {
        this.traversal = new TreeMap<>();
    }

    /**
     * Add element method by sequence of command.
     * @param direction is the string with the command how to get to concrete position.
     * @param value is the added value
     * @throws BinaryTreeInitializationException
     */
    public void addElement(String direction, T value) throws BinaryTreeInitializationException {
        Node temp = head;
        int i = 0;
        while (i < direction.length()-1 && temp != null){
            temp = (direction.charAt(i) == 'L') ? temp.left : temp.right;
            i++;
        }
        if (temp == null && direction.length() == 0) {
            head = new Node();
            head.value = value;
            return;
        }
        if (temp == null) {
            throw new BinaryTreeInitializationException("Check the parameters");
        }
        Node newChild = new Node();
        newChild.value = value;
        if (direction.charAt(direction.length()-1) == 'L'){
            temp.left = newChild;
        }
        else{
            temp.right = newChild;
        }
    }

    /**
     * The method for save the position for each key in the tree.
     * The numeration of nodes looks like:
     *       1
     *    2       3
     * 4   5    6   7
     * @param temp is current Node.
     * @param traversal is support traversal.
     * @param pos is the index in out tree.
     */
    private void dfs(Node temp, Map<T, Integer>traversal, int pos){
        if (temp == null){
            return;
        }
        traversal.put(temp.value, pos);
        dfs(temp.left, traversal, pos*2);
        dfs(temp.right, traversal, pos*2 + 1);
    }

    /**
     * Method for call dfs with proper parameters for mapping keys with position in the tree.
     */
    public void treeTraversal(){
        int position = 1;
        Node temp = head;
        dfs(temp, traversal, position);
    }

    /**
     * The method to get all ancestors for the concrete Node from bottom to top.
     * @param value is the key.
     * @throws BinarySearchElementException
     * return the list of all ancestors
     */
    public List<T> getAllAncestors(T value) throws BinarySearchElementException {
        if (!traversal.containsKey(value)){
            throw new BinarySearchElementException("There is sno this element in BT");
        }
        int pos = traversal.get(value);
        ArrayList<T> path = new ArrayList<>();
        Node cur = findRoute(pos, path);
        path.remove(path.size()-1);
        System.out.println(path);
        return path;
    }

    /**
     * Find route by recursion steps.
     * @param pos the current position of node in tree.
     * @param route the route that we are searching.
     * @return the node in the position pos.
     */
    private Node findRoute(int pos, List<T> route){
        if (pos == 1){
            route.add(head.value);
            System.out.println(head.value);
            return head;
        }
        if (pos % 2 == 0) {
            Node temp = findRoute(pos/2, route).left;
            route.add(temp.value);
            System.out.println(temp.value);
            return temp;
        }
        else{
            Node temp = findRoute(pos/2, route).left;
            route.add(temp.value);
            System.out.println(temp.value);
            return temp;
        }
    }

    /**
     * Method to get common ancestors.
     * @param value1 is the first key.
     * @param value2 is the second key.
     * @return the least common ancestor.
     * @throws BinarySearchElementException
     */
    public T getCommonAncestors(int value1, int value2) throws BinarySearchElementException {
        if (!traversal.containsKey(value1) || !traversal.containsKey(value2) ){
            throw new BinarySearchElementException("The element is not in the BT.");
        }
        int pos1 = traversal.get(value1);
        int pos2 = traversal.get(value2);
        while (pos1 != pos2) {
            if (pos1 > pos2) {
                pos1 /= 2;
            } else {
                pos2 /= 2;
            }
        }
        Node lca = findNodeLCA(pos1);
        return lca.value;
    }

    /**
     * Find node by position in tree
     * @param pos is the current position in the tree.
     * @return the concrete Node.
     */
    private Node findNodeLCA(int pos){
        if (pos == 1){
            return head;
        }
        if (pos % 2 == 0) {
            return findNodeLCA(pos/2).left;
        } else {
            return findNodeLCA(pos/2).right;
        }
    }
}
