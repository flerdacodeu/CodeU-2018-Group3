import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CountIslandsTest {

    /**
     * In each test case first call test the recursive solution and second call tests the iterative solution
     *  Recursive solution calls made to CountIslands.countIslands() function
     *  Iterative solution calls made to CountIslands.countIslandsIterative function
     */
    @Test
    public void testBasic() {
        // test case given in the question
        boolean[][] tilesMap = {{false, true, false, true},
                                {true, true, false, false},
                                {false, false, true, false},
                                {false, false, true, false}};
        assertEquals(3, CountIslands.countIslands(tilesMap, tilesMap.length, tilesMap[0].length));
        assertEquals(3, CountIslands.countIslandsIterative(tilesMap, tilesMap.length, tilesMap[0].length));
    }

    @Test
    public void testBasic2() {
        // test case given in the question
        boolean[][] tilesMap = {{false, false, false, true},
                                {false, false, false, false},
                                {false, false, false, false}};
        assertEquals(1, CountIslands.countIslands(tilesMap, tilesMap.length, tilesMap[0].length));
        assertEquals(1, CountIslands.countIslandsIterative(tilesMap, tilesMap.length, tilesMap[0].length));
    }


    @Test
    public void testEdgeCases() {
        //all false - 0 island
        boolean[][] tilesMapFalse = {{false, false, false, false},
                                    {false, false, false, false},
                                    {false, false, false, false}};
        assertEquals(0, CountIslands.countIslands(tilesMapFalse, tilesMapFalse.length, tilesMapFalse[0].length));
        assertEquals(0, CountIslands.countIslandsIterative(tilesMapFalse, tilesMapFalse.length, tilesMapFalse[0].length));

        //all true - 1 island
        boolean[][] tilesMapTrue = {{true, true, true, true},
                                    {true, true, true, true}};
        assertEquals(1, CountIslands.countIslands(tilesMapTrue, tilesMapTrue.length, tilesMapTrue[0].length));
        assertEquals(1, CountIslands.countIslandsIterative(tilesMapTrue, tilesMapTrue.length, tilesMapTrue[0].length));
    }

    @Test
    public void testAdvanced() {
        //horizontal islands
        boolean[][] tilesMap = {{false, true, false, true},
                                {false, true, false, true},
                                {false, true, false, true},
                                {false, true, false, true}};
        assertEquals(2, CountIslands.countIslands(tilesMap, tilesMap.length, tilesMap[0].length));
        assertEquals(2, CountIslands.countIslandsIterative(tilesMap, tilesMap.length, tilesMap[0].length));

        //vertical islands
        boolean[][] tilesMapV = {{false, false, false, false},
                                {true, true, true, true},
                                {false, false, false, false},
                                {true, true, true, true}};
        assertEquals(2, CountIslands.countIslands(tilesMapV, tilesMapV.length, tilesMapV[0].length));
        assertEquals(2, CountIslands.countIslandsIterative(tilesMapV, tilesMapV.length, tilesMapV[0].length));

        // trues replaced horizontally - each true is an island itself since down, up, right ,left of them is false
        boolean[][] tilesMap2 = {{false, true, false, true},
                                {true, false, true, false},
                                {false, true, false, true},
                                {true, false, false, false}};
        assertEquals(7, CountIslands.countIslands(tilesMap2, tilesMap2.length, tilesMap2[0].length));
        assertEquals(7, CountIslands.countIslandsIterative(tilesMap2, tilesMap2.length, tilesMap2[0].length));
    }
}
