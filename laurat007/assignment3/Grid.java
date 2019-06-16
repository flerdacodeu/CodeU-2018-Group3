
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura
 *  Defining a grid with fixed number of columns and rows.
 */
class Grid {
    
    Dictionary dictionary;
    int rows, columns;
    char[][] grid;
    
    Grid(int r, int c, char[][] letterMatrix, Dictionary dict) {
        dictionary = dict;
        rows = r;
        columns = c;
        grid = letterMatrix;
    }
    
    // check if the cell exists in grid and is not visited yet
    boolean isValid(int x, int y, boolean[][] visited) {
        if(x >= 0 && x < rows)
            if(y >= 0 && y < columns)
                if(visited[x][y] == false)
                    return true;
        return false;
    }  
}

