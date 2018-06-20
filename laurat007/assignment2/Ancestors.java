/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_2;

import assignment_2.Ancestors.BinaryTreeNode;
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
            BinaryTreeNode newNode = new BinaryTreeNode(newValue);
            Queue<BinaryTreeNode> queue = new LinkedList();
            queue.add(this);
            
            while(!queue.isEmpty()) {
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
            Queue<BinaryTreeNode> q = new LinkedList();
            q.add(this); 
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
    
    public static void main(String[] args) {
    }
    
}
