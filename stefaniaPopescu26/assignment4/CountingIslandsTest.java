import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class CountingIslandsTest {

    @Test
    public void countIslands_NoLand() {
        CountingIslands islands = new CountingIslands("NoLand.txt");
        assertEquals(0, islands.countIslands());
    }

    @Test
    public void countIslnds_OneBigIsland() {
        CountingIslands islands = new CountingIslands("OneBigIsland.txt");
        assertEquals(1, islands.countIslands());
    }

    @Test
    public void countIslands_AssignmentInput() {
        CountingIslands islands = new CountingIslands("CountingIslands.txt");
        assertEquals(3, islands.countIslands());
    }

    @Test
    public void countIslands_NoMerging() {
        CountingIslands islands = new CountingIslands("NoMerging.txt");
        assertEquals(13, islands.countIslands());
    }

    @Test
    public void countIslands_MergeAll() {
        CountingIslands islands = new CountingIslands("MergeAll.txt");
        assertEquals(1, islands.countIslands());
    }
}