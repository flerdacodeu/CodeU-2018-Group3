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
import static org.junit.Assert.*;

/**
 *
 * @author Laura
 */
public class AncestorsTest extends TestCase{
    
    public AncestorsTest() {
    }
    
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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

    /**
     * Test of main method, of class Ancestors.
     */
    @Test
    public void Test1() {
        // build a binary tree
        Ancestors.BinaryTreeNode<Integer> tree = buildTree1();
        
        ArrayList<Integer> ancestors = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();

        // solution given by program for Q1
        tree.getAncestors(tree, ancestors, 6);

        // edit right solution 
        solution.add(2); solution.add(3); solution.add(7);
        
        assertEquals(solution, ancestors);
    }
    
}
