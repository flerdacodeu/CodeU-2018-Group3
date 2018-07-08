
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Laura
 * 
 *  This program returns the words that can be formed using the letters in a 
 * given grid by moving left, right, up, down and on diagonals.
 *  Moving in the grid is similar to a backtracking from each Cell which stops
 * if the current word formed is not a prefix, and if it finds a word it is added
 * to solution.
 * 
 */

public class Word {
    
    static TreeSet<String> words(Grid G) {
        TreeSet<String> solution = new TreeSet<>();
        boolean[][] visited = new boolean[G.rows][G.columns];
        
        // start search from each existing cell
        for(int i = 0; i < G.rows; i++) {
            for(int j = 0; j < G.columns; j++) {
                backtrack(i, j, G, solution, 
                    Character.toString(G.grid[i][j]), visited);
            }
        }
        return solution;
    }

    static private void backtrack(int startingCellX, int startingCellY, Grid G, 
        TreeSet<String> solution, String word, boolean[][] visited) {
        
        visited[startingCellX][startingCellY] = true;
        
        // stop condition
        if(! G.dictionary.isPrefix(word)) {
            visited[startingCellX][startingCellY] = false;
            return;
        }
        // if solution found, will be added
        if(G.dictionary.isWord(word))
            solution.add(word);
        
        // try moving
        int[] deltaX = {startingCellX + 1, startingCellX, startingCellX - 1};
        int[] deltaY = {startingCellY + 1, startingCellY, startingCellY - 1};
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                
                // initial position
                if(i == 1 && j == 1) 
                    continue;
                
                else {
                    if(G.isValid(deltaX[i], deltaY[j], visited)) {
                        backtrack(deltaX[i], deltaY[j], G, solution, 
                            word + G.grid[deltaX[i]][deltaY[j]], visited);
                        visited[deltaX[i]][deltaY[j]] = false;
                    }
                }
            }
       
        visited[startingCellX][startingCellY] = false;
        
    }
    
    public static void main(String[] args) {
        
    }
}

