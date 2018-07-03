/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laura
 */
public class IslandTest {
    
    @Test
    public void testIslandEmptyGrid() {
        
        boolean[][] map = new boolean[0][0];
        
        Island island = new Island(0, 0, map);
        assertEquals(island.countIslands(), 0);
    }

    @Test
    public void testIslandGridWithValues() {
        
        boolean[][] map = new boolean[4][4];
        map[0][0] = false;
        map[0][1] = true;        
        map[0][2] = false;
        map[0][3] = true;
        
        map[1][0] = true;
        map[1][1] = true;        
        map[1][2] = false;
        map[1][3] = false;
        
        map[2][0] = false;
        map[2][1] = false;        
        map[2][2] = true;
        map[2][3] = false;

        map[3][0] = false;
        map[3][1] = false;        
        map[3][2] = true;
        map[3][3] = false;
        
        // F T F T
        // T T F F
        // F F T F
        // F F T F
        
        Island island = new Island(4, 4, map);
        assertEquals(island.countIslands(), 3);
    }
    
    
    @Test
    public void testIslandGridWithMoreValues() {
        
        boolean[][] map = new boolean[5][7];
        map[0][0] = false;
        map[0][1] = true;        
        map[0][2] = false;
        map[0][3] = true;
        map[0][4] = true;
        map[0][5] = false;
        map[0][6] = true;
        
        map[1][0] = true;
        map[1][1] = true;        
        map[1][2] = false;
        map[1][3] = true;
        map[1][4] = false;        
        map[1][5] = true;
        map[1][6] = false;
        
        map[2][0] = false;
        map[2][1] = false;        
        map[2][2] = false;
        map[2][3] = false;
        map[2][4] = false;
        map[2][5] = false;
        map[2][6] = true;

        map[3][0] = false;
        map[3][1] = false;        
        map[3][2] = true;
        map[3][3] = false;       
        map[3][4] = true;       
        map[3][5] = true;       
        map[3][6] = true;
        
        map[4][0] = true;
        map[4][1] = true;        
        map[4][2] = false;
        map[4][3] = false;
        map[4][4] = false;        
        map[4][5] = false;
        map[4][6] = true;
        
        // F T F T T F T
        // T T F T F T F
        // F F F F F F T
        // F F T F T T T
        // T T F F F F T
        
        Island island = new Island(5, 7, map);
        assertEquals(island.countIslands(), 7);
    }
}
