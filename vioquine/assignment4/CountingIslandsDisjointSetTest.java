import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountingIslandsDisjointSetTest {

    @Test
    public void testEmptyMap() {
        assertEquals(0, CountingIslandsDisjointSet.countIslands(new boolean[0][0]));
    }

    @Test
    public void testNoIslands() {
        boolean[][] tiles = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}};
        assertEquals(0, CountingIslandsDisjointSet.countIslands(tiles));
    }

    @Test
    public void testJustIsland() {
        boolean[][] tiles = {
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true}};
        assertEquals(1, CountingIslandsDisjointSet.countIslands(tiles));
    }

    @Test
    public void testFromAssignment() {
        boolean[][] tiles = {
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {false, false, true, false}};
        assertEquals(3, CountingIslandsDisjointSet.countIslands(tiles));
    }
}
