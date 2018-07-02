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
    public void countIslands_NormalInput() {
        CountingIslands islands = new CountingIslands("CountingIslands.txt");
        assertEquals(3, islands.countIslands());
    }
}
