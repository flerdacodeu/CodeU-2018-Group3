
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *  @author Laura
 *  This code is supposed to count the number of true values neighbors in a 
 * grid. I mapped each true cell to the area from which it belongs like this:
 *  - when a true cell not mapped yet is found, it is mapped to a new area
 *  - each neighbor is then tested: if it is not mapped yet, it will be mapped
 * in the same area/ if it is mapped the original cell will be mapped in
 * neighbor's area
 */


class Pair {
    int x, y;;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return x + " " + y;
    }
}

public class Island {
    
    int rows, columns;
    boolean[][] map;
    int countIslands;

    void checkLand(HashMap<Pair, Integer> mapIslands, Pair[][] pairMap, int i, int j) {
        
        // if we find a piece of land not processed yet we map it to next
        // available area
        if(!mapIslands.containsKey(pairMap[i][j])){
            mapIslands.put(pairMap[i][j], countIslands);
            countIslands++;
        }
        
        // the land was already processed
        else return;

        // check neighbours
        // up
        if(isLand(i - 1, j, mapIslands, rows, columns, map, countIslands, pairMap)){
            // already mapped neighbor
            if(mapIslands.containsKey(pairMap[i - 1][j])) {
                
                // the original cell gets mapped to neighbor's area
                // extra area is deleted
                mapIslands.put(pairMap[i][j], mapIslands.get(pairMap[i - 1][j]));
                countIslands--;
            }
            else
                mapIslands.put(pairMap[i - 1][j], mapIslands.get(pairMap[i][j]));
        }

        // left
        if(isLand(i, j - 1, mapIslands, rows, columns, map, countIslands, pairMap)){
            if(mapIslands.containsKey(pairMap[i][j - 1])) {
                mapIslands.put(pairMap[i][j], mapIslands.get(pairMap[i][j - 1]));
                countIslands--;
            }
            else
                mapIslands.put(pairMap[i][j - 1], mapIslands.get(pairMap[i][j]));
        }

        // right
        if(isLand(i, j + 1, mapIslands, rows, columns, map, countIslands, pairMap)){
            if(mapIslands.containsKey(pairMap[i][j + 1])) {
                mapIslands.put(pairMap[i][j], mapIslands.get(pairMap[i][j + 1]));
                countIslands--;
            }
            else
                mapIslands.put(pairMap[i][j + 1], mapIslands.get(pairMap[i][j]));
        }

        // down
        if(isLand(i + 1, j, mapIslands, rows, columns, map, countIslands, pairMap)){
            if(mapIslands.containsKey(pairMap[i + 1][j])) {
                mapIslands.put(pairMap[i][j], mapIslands.get(pairMap[i + 1][j]));
                countIslands--;
            }
            else
                mapIslands.put(pairMap[i + 1][j], mapIslands.get(pairMap[i][j]));
        }
    }

    
    Island(int r, int c, boolean[][] map) {
        rows = r;
        columns = c;
        this.map = map;
    }
    
    Pair[][] initializePair() {
        Pair[][] pairMap = new Pair[rows][columns];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                pairMap[i][j] = new Pair(i, j);
        return pairMap;
    }
    
    int countIslands() {
        Pair[][] pairMap = initializePair();
        
        HashMap<Pair, Integer> mapIslands = new HashMap<>();
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++){
                if(map[i][j]) {
                    checkLand(mapIslands, pairMap, i, j);
                }
            }
        }
        return countIslands;
    }
    
    public static void main(String[] args) {
    }

  boolean isLand(int i, int j, HashMap<Pair, Integer> mapIslands, 
       int rows, int columns, boolean[][] map, int countIslands, 
       Pair[][] pairMap) {
      
        if(i >= rows || i < 0)
            return false;
        if(j >= columns || j < 0)
            return false;
        if(!map[i][j])
            return false;
        return true;
    }
}
