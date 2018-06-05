package edu.codeU.assignment2.CommonAncestor;

import edu.codeU.assignment2.BinarySearchElementExeption;
import edu.codeU.assignment2.BinaryTreeInitializationExceprion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTree<T> {
    /**
     * The head is the first node in tree.
     */
    private Node head;
    /**
     * Traversal of binaty tree.
     */
    private List<Integer> traversal;
    /**
     * Constant of upper bound for values in tree.
     */
    private final static int UPPER_BOUND_VOF_VALUES = 1000;

    private class Node{
        Node left;
        Node right;
        int value;
    }

    public BinaryTree() {
        this.head = null;
        this.traversal = new ArrayList<>(Collections.nCopies(UPPER_BOUND_VOF_VALUES, 0));
    }

    /**
     * Add element method by sequence of command.
     * @param direction is the string with the command how to get to concrete position.
     * @param value is the added value
     * @throws BinaryTreeInitializationExceprion
     */
    public void addElement(String direction, int value) throws BinaryTreeInitializationExceprion{
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
            throw new BinaryTreeInitializationExceprion("Chaeck the inpur paramenters.");
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
     * Deep-first searching for save the traversal of tree.
     * @param temp is current Node.
     * @param traversal is support traversal.
     * @param pos is the index in out tree.
     */
    private void dfs(Node temp, List<Integer> traversal, int pos){
        if (temp == null){
            return;
        }
        traversal.set(temp.value, pos);
        dfs(temp.left, traversal, pos*2);
        dfs(temp.right, traversal, pos*2 + 1);
    }

    /**
     * Method for call dfs with proper parameters.
     */
    public void treeTraversal(){
        int position = 1;
        Node temp = head;
        dfs(temp, traversal, position);
    }

    /**
     * Method to get common ancestors.
     * @param value1 is the first key.
     * @param value2 is the second key.
     * @return the least common ancestor.
     * @throws BinarySearchElementExeption
     */
    public int getCommonAncestors(int value1, int value2) throws  BinarySearchElementExeption{
        int pos1 = traversal.get(value1);
        int pos2 = traversal.get(value2);
        if (pos1 == 0 || pos2 == 0){
            throw new BinarySearchElementExeption("The element is not in the BT.");
        }
        StringBuilder route = new StringBuilder();
        while (pos1 != pos2) {
            if (pos1 > pos2) {
                pos1 /= 2;
            } else {
                pos2 /= 2;
            }
        }
        while (pos1 != 1) {
            if (pos1 % 2 == 0) {
                route.append("L");
            } else {
                route.append("R");
            }
            pos1 /= 2;
        }
        route = route.reverse();
        int positionAtRoute = 0;
        Node temp = head;
        Node lca = findNode(temp, String.valueOf(route), positionAtRoute);
        return lca.value;
    }

    /**
     * Find node by string with route.
     * @param temp is current considerable Node.
     * @param route is the string with path.
     * @param pos is the position in the path.
     * @return the concrete Node.
     */
    private Node findNode(Node temp, String route, int pos) {
        if (pos == route.length()) {
            return temp;
        }
        if (route.charAt(pos) == 'L') {
            return findNode(temp.left, route, pos + 1);
        } else {
            return findNode(temp.right, route, pos + 1);
        }
    }
}
