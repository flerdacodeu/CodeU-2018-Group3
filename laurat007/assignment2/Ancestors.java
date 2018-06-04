package assignment_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author Laura
 *  This program builds a binary tree by using three methods:
 *      1. add node in the left extremity
 *      2. add node in the right extremity
 *      3. add node in order to balance the tree with the possibility to skip 
 *      insert locations
 * 
 *  The method solving the first task is a recursive one, similar to searching a
 * node in a tree: when the given value is found, the method will return true, 
 * but if the value was not found yet, we test if it was found in the current
 * subtree (in either left or right subtree of the current root) - if this is 
 * true we add the root to solution and return true, else return false.
 * 
 *  The method solving the second task uses the first method as follows:
 * there are two values given; for each one it finds its ancestors and it 
 * searches the first common value.
 * 
 */

public class Ancestors {
    static class BinaryTreeNode<T> {
        BinaryTreeNode left, right;
        T value;
        
        BinaryTreeNode(T value) {
            this.value  = value;
        }
        
        // add to left extremity
        void addLeft(T newValue) {
            BinaryTreeNode aux = this;
            while(aux.left != null) {
                aux = aux.left;
            }
            BinaryTreeNode newNode = new BinaryTreeNode(newValue);
            aux.left = newNode;
        }
        
        // add to right extremity
        void addRight(T newValue) {
            BinaryTreeNode aux = this;
            while(aux.right != null) {
                aux = aux.right;
            }
            BinaryTreeNode newNode = new BinaryTreeNode(newValue);
            aux.right = newNode;
        }
        
        // add to balance tree
        // similar to bfs traversal
        void addBalanced(T newValue, int skips) { 
            BinaryTreeNode aux = this;
            BinaryTreeNode newNode = new BinaryTreeNode(newValue);
            Queue<BinaryTreeNode> queue = new LinkedList();
            queue.add(aux);
            
            while(true) {
                BinaryTreeNode current = queue.poll();
                if(current.left != null)
                    queue.add(current.left);
                else {
                    if(skips != 0)
                        skips--;
                    else {
                        current.left = newNode;
                        break;
                    }
                }
                if(current.right != null)
                    queue.add(current.right);
                else {
                    if(skips != 0)
                        skips--;
                    else {
                        current.right = newNode;
                        break;
                    }
                }
            }
        }
        
        public String toString() {
            String s = "";
            BinaryTreeNode aux = this;
            Queue<BinaryTreeNode> q = new LinkedList();
            q.add(aux); 
            while(!q.isEmpty()) {
                BinaryTreeNode current = q.poll();
                if(current.left != null)
                    q.add(current.left);
                if(current.right != null)
                    q.add(current.right);
                s += current.value + " ";
            }
            
            return s;
        }
        
        Boolean getAncestors(BinaryTreeNode<T> root, ArrayList<T> ancestors, T givenKey) {
            if(root == null)
                return false;
            
            if(root.value.equals(givenKey)) {
                return true;
            }
            
            // if given value is found in this subtree we add the root to solution
            if(getAncestors(root.left, ancestors, givenKey) || getAncestors(root.right, ancestors, givenKey)) {
                ancestors.add(root.value);
                return true;
            }
            return false;
        }
        
        T commonAncestor(T node1, T node2) {
            ArrayList<T> fstNodeAncestors = new ArrayList<>();
            ArrayList<T> secNodeAncestors = new ArrayList<>();
            
            getAncestors(this, fstNodeAncestors, node1);
            getAncestors(this, secNodeAncestors, node2);
            
            // search the first ancestor common to first node ancestors and
            // to second node ancestors
            for(int ancestorIndex = 0; ancestorIndex < fstNodeAncestors.size(); ancestorIndex++) {
                for(int secAncestorIndex = 0; secAncestorIndex < secNodeAncestors.size(); secAncestorIndex++) {
                    if(fstNodeAncestors.get(ancestorIndex).equals(secNodeAncestors.get(secAncestorIndex)))
                        return fstNodeAncestors.get(ancestorIndex);
                }
            }
            
            return null;
        }
    }
    
    static class AncestorsTest {

        void Test1() {
            
            // build a binary tree
            BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(7);
            tree.addLeft(3);
            tree.addRight(4);
            tree.addLeft(2);
            tree.addBalanced(5, 0);
            tree.addBalanced(8, 1);
            tree.addBalanced(1, 1);
            tree.addBalanced(6, 1);
            
            // check if it was build correctly (bfs)
            System.out.println(tree);
            
            ArrayList<Integer> ancestors = new ArrayList<>();
            ArrayList<Integer> solution = new ArrayList<>();
            
            // solution given by program for Q1
            tree.getAncestors(tree, ancestors, 6);
            
            // edit right solution 
            solution.add(2); solution.add(3); solution.add(7);
            
            // check if solution given by program is the right one
            if(solution.equals(ancestors))
                System.out.println("getAncestors method works fine!");
            else {
                System.out.println("getAncestors method does not work!");
            }
            
            // result given by program for Q2
            Integer result = tree.commonAncestor(6, 5);
            
            // edit right solution
            Integer solutionQ2 = 3;
            
            // check if solution given by program is the right one
            if(result.equals(solutionQ2)) {
                System.out.println("commonAncestor method works fine!");
            }
            else {
                System.out.println("commonAncestor method does not work!");
            }
        }
    }
    
    public static void main(String[] args) {
        AncestorsTest test = new AncestorsTest();
        test.Test1();
    }
    
}
