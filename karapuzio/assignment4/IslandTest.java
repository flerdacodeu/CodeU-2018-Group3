package test.edu.codeU.assignment4;

import edu.codeU.assignment4.Island;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IslandTest {

    Island island;
    int rows;
    int columns;
    boolean[][] map;

    @Before
    public void createInput(){
        rows = 4;
        columns = 4;
        map = new boolean[][]{{false,true,false,true},
                {true, true, false, false},
                {false,false,true,false},
                {false,false, true,false}};
    }

    @Test
    public void checkTheCorrectNumberOfIsland(){
        island = new Island(rows, columns, map);
        int islands = island.countIslanads();
        int correctAnswer = 3;
        Assert.assertTrue(islands == correctAnswer);
    }

    @Test
    public void checkNotCorrectAnswer(){
        map[1][2] = true;
        island = new Island(rows, columns, map);
        int islands = island.countIslanads();
        int correctAnswer = 3;
        Assert.assertFalse(islands == correctAnswer);
    }
}
