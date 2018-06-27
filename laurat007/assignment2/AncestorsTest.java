/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_2;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Laura
 */
public class AncestorsTest extends TestCase {

    Ancestors.BinaryTreeNode<Integer> buildTree1() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = new Ancestors.BinaryTreeNode<>(7);
        tree.addLeft(3);
        tree.addRight(4);
        tree.addLeft(2);
        tree.addBalanced(5, 0);
        tree.addBalanced(8, 1);
        tree.addBalanced(1, 1);
        tree.addBalanced(6, 1);
        return tree;
    }

    Ancestors.BinaryTreeNode<Integer> buildTree2() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = new Ancestors.BinaryTreeNode<>(7);
        return tree;
    }

    Ancestors.BinaryTreeNode<Integer> buildTree3() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = new Ancestors.BinaryTreeNode<>(7);
        tree.addLeft(3);
        tree.addLeft(8);
        tree.addLeft(10);
        tree.addLeft(5);
        tree.addLeft(4);
        tree.addLeft(1);
        return tree;
    }

    @Before
    public void setUp() {
        try {
            super.setUp();
        } catch (Exception ex) {
            Logger.getLogger(AncestorsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
        try {
            super.tearDown();
        } catch (Exception ex) {
            Logger.getLogger(AncestorsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // test getAncestors
    @Test
    public void Test_getAncestors_1() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = buildTree1();

        ArrayList<Integer> ancestors = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();

        // solution given by program for Q1
        tree.getAncestors(tree, ancestors, 6);

        // edit right solution 
        solution.add(2);
        solution.add(3);
        solution.add(7);

        assertEquals(solution, ancestors);
    }

    @Test
    public void Test_getAncestors_emptyTree() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = buildTree2();

        ArrayList<Integer> ancestors = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();

        // solution given by program for Q1
        tree.getAncestors(tree, ancestors, 6);

        assertEquals(solution, ancestors);
    }
    
    @Test
    public void Test_getAncestors_2() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = buildTree3();

        ArrayList<Integer> ancestors = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();

        // solution given by program for Q1
        tree.getAncestors(tree, ancestors, 10);

        // edit right solution 
        solution.add(8);
        solution.add(3);
        solution.add(7);

        assertEquals(solution, ancestors);
    }

    // test commonAncestor
    @Test
    public void Test_commonAncestor_emptyTree() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = buildTree2();

        // solution given by program for Q2
        Integer ancestor = tree.commonAncestor(2, 6);

        // edit right solution
        Integer solution = null;

        assertEquals(solution, ancestor);
    }

    @Test
    public void Test_commonAncestor_1() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = buildTree1();

        // solution given by program for Q2
        Integer ancestor = tree.commonAncestor(5, 6);

        // edit right solution
        Integer solution = 3;

        assertEquals(solution, ancestor);
    }
    
    @Test
    public void Test_commonAncestor_2() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = buildTree3();

        // solution given by program for Q2
        Integer ancestor = tree.commonAncestor(5, 1);

        // edit right solution
        Integer solution = 10;

        assertEquals(solution, ancestor);
    }

}
