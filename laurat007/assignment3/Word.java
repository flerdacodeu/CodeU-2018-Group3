
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
                visited[i][j] = false;
            }
        }
        return solution;
    }

    static private void backtrack(int startingCellX, int startingCellY, Grid G, 
        TreeSet<String> solution, String word, boolean[][] visited) {
        
        visited[startingCellX][startingCellY] = true;
        
        // stop condition
        if(! G.dictionary.isPrefix(word)) {
            return;
        }
        // if solution found, will be added
        if(G.dictionary.isWord(word))
            solution.add(word);
        
        // try moving
        // left up
        if(G.isValid(startingCellX - 1, startingCellY - 1, visited)) {
            backtrack(startingCellX - 1, startingCellY - 1, G, solution, 
                word + G.grid[startingCellX - 1][startingCellY - 1], visited);
            visited[startingCellX - 1][startingCellY - 1] = false;
        }
        
        // up
        if(G.isValid(startingCellX - 1, startingCellY, visited)) {
            backtrack(startingCellX - 1, startingCellY, G, solution, 
                word + G.grid[startingCellX - 1][startingCellY], visited);
            visited[startingCellX - 1][startingCellY] = false;
        }
        
        // right up
        if(G.isValid(startingCellX - 1, startingCellY + 1, visited)) {
            backtrack(startingCellX - 1, startingCellY + 1, G, solution, 
                word + G.grid[startingCellX - 1][startingCellY + 1], visited);
            visited[startingCellX - 1][startingCellY + 1] = false;
        }
        
        // left
        if(G.isValid(startingCellX, startingCellY - 1, visited)) {
            backtrack(startingCellX, startingCellY - 1, G, solution, 
                word + G.grid[startingCellX][startingCellY - 1], visited);
            visited[startingCellX][startingCellY - 1] = false;
        }
        
        // right
        if(G.isValid(startingCellX, startingCellY + 1, visited)) {
            backtrack(startingCellX, startingCellY + 1, G, solution, 
                word + G.grid[startingCellX][startingCellY + 1], visited);
            visited[startingCellX][startingCellY + 1] = false;
        }
        
        // left down
        if(G.isValid(startingCellX + 1, startingCellY - 1, visited)) {
            backtrack(startingCellX + 1, startingCellY - 1, G, solution, 
                word + G.grid[startingCellX + 1][startingCellY - 1], visited);
            visited[startingCellX + 1][startingCellY - 1] = false;
        }
        
        // down
        if(G.isValid(startingCellX + 1, startingCellY, visited)) {
            backtrack(startingCellX + 1, startingCellY, G, solution, 
                word + G.grid[startingCellX + 1][startingCellY], visited);
            visited[startingCellX + 1][startingCellY] = false;
        }
        
        // right down
        if(G.isValid(startingCellX + 1, startingCellY + 1, visited)) {
            backtrack(startingCellX + 1, startingCellY + 1, G, solution, 
                word + G.grid[startingCellX + 1][startingCellY + 1], visited);
            visited[startingCellX + 1][startingCellY + 1] = false;
        }
        
    }
    
    public static void main(String[] args) {
        
    }
}

