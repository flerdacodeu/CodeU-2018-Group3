package test.edu.codeU.assignment1.list;

import edu.codeU.assignment1.list.FindListElemException;
import edu.codeU.assignment1.list.LinkedList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for LinkedList class implementation.
 */
public class ListTest {

    @Test
    public void checkIsEmpty(){
        LinkedList<String> linkedList = new LinkedList<>();
        Assert.assertTrue(linkedList.isEmpty());
    }

    @Test
    public void checkTheSize(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.push(3);
        linkedList.push(9);
        linkedList.push(369);
        Assert.assertTrue(linkedList.size() == 3);
    }

    @Test(expected = FindListElemException.class)
    public void checkNegativeParameters() throws FindListElemException{
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.push("Lol");
        linkedList.push("Kek");
        linkedList.push("Chebureck");
        String value = linkedList.findTheKthLastElement(-1);
        Assert.fail("Test doesn't work correct.");
    }

    @Test(expected = FindListElemException.class)
    public void checkBiggerParameters() throws FindListElemException{
        LinkedList<Double> linkedList = new LinkedList<>();
        linkedList.push(0.001);
        linkedList.push(0.003);
        linkedList.push(0.009);
        Double value = linkedList.findTheKthLastElement(6);
        Assert.fail("Test doesn't work correct.");
    }
}
