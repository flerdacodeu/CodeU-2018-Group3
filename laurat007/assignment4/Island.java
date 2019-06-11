
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
 *  - when a true cell already mapped is found, I find the minimum area of 
 * its neighbors and, if it is lower than the current area, I put the minimum
 * value found to all neighbors, and to current cell.
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
        else {
            unifyNeighbors(mapIslands, i, j, pairMap);
            return;
        }

        // check neighbours
        int[] deltaX = {i - 1, i, i, i + 1};
        int[] deltaY = {j, j - 1, j + 1, j};
        
        for(int k = 0; k < 4; k++) {
            if(isLand(deltaX[k], deltaY[k], mapIslands, rows, columns, map, countIslands, pairMap)) {
               
                // already mapped neighbor
                if(mapIslands.containsKey(pairMap[deltaX[k]][deltaY[k]])) {
                    // the original cell gets mapped to neighbor's area
                    // extra area is deleted
                    mapIslands.put(pairMap[i][j], mapIslands.get(pairMap[deltaX[k]][deltaY[k]]));
                    countIslands--;
                }
                else
                    mapIslands.put(pairMap[deltaX[k]][deltaY[k]], mapIslands.get(pairMap[i][j]));
            }      
        }
    }

    
    Island(int r, int c, boolean[][] map) {
        rows = r;
        columns = c;
        this.map = map;
    }
    
    // create a grid of pairs which builds only one instance for
    // each cell - used for tracking more easily the neighbors 
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

    private void unifyNeighbors(HashMap<Pair, Integer> mapIslands, int i, int j, Pair[][] pairMap) {
        int min = 1000;
        // check neighbours
        int[] deltaX = {i - 1, i, i, i + 1};
        int[] deltaY = {j, j - 1, j + 1, j};
        
        for(int k = 0; k < 4; k++) {
            if(isLand(deltaX[k], deltaY[k], mapIslands, rows, columns, map, countIslands, pairMap)){
                // already mapped neighbor
                if(mapIslands.containsKey(pairMap[deltaX[k]][deltaY[k]])) {
                    int neighbor = mapIslands.get(pairMap[deltaX[k]][deltaY[k]]);
                    if(neighbor < min)
                        min = neighbor;
                }
            }
        }
        
        // unify 2 areas
        if(min < mapIslands.get(pairMap[i][j])) {
            countIslands--;
            mapIslands.put(pairMap[i][j], min);
            for(int k = 0; k < 4; k++) {
                if(isLand(deltaX[k], deltaY[k], mapIslands, rows, columns, map, countIslands, pairMap)) {
                    mapIslands.put(pairMap[deltaX[k]][deltaY[k]], min);
                }   
            }
        }
    }
}
