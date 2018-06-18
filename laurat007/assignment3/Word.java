
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura
 * 
 *  This program returns the words that can be formed using the letters in a 
 * given grid by moving left, right, up, down and on diagonals. 
 *  I used a dictionary class in order to define the correspondence between a 
 * particular word and its explanation ( + the 2 helping methods isPrefix and 
 * isWord, and a grid class in order to define a cell (a pair of indexes - which 
 * facilitates moving in the grid) and its value.
 *  The Cell subclass has methods implemented for every moving option.
 *  Moving in the grid is similar to a backtracking from each Cell which stops
 * if the current word formed is not a prefix, and if it finds a word it is added
 * to solution.
 * 
 */

class Dictionary {
    HashMap<String, String> dictionary;
    
    Dictionary(HashMap<String, String> wordsSet) {
        this.dictionary = new HashMap<>();
        dictionary.putAll(wordsSet);
    }
    
    boolean isPrefix(String prefix) {
        Set<String> keys = dictionary.keySet();
        Iterator it = keys.iterator();
        while(it.hasNext()) {
            String next = (String) it.next();
            if(next.startsWith(prefix))
                return true;
        }
        return false;
    }
    
    boolean isWord(String word) {
        Set<String> keys = dictionary.keySet();
        return keys.contains(word);
    }
}

class Grid {
    
    Dictionary dictionary;
    int rows, columns;
    char[][] grid;
    Cell[][] cells;
    
    Grid(int r, int c, char[][] letterMatrix, Dictionary dict) {
        dictionary = dict;
        rows = r;
        columns = c;
        grid = new char[rows][columns];
        cells = new Cell[rows][columns];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(i, j);
                grid[i][j] = letterMatrix[i][j];
            }
    }
   
    class Cell {
        int x, y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        boolean isValid() {
            if(x >= 0 && x < rows)
                if(y >= 0 && y < columns)
                    return true;
            return false;
        }
        
        // moving in the grid
        
        Cell leftUp() {
            return new Cell(x - 1, y - 1);
        }
        
        String leftUpValue() {
            return Character.toString(grid[this.leftUp().x][this.leftUp().y]);
        }
        
        Cell up() {
            return new Cell(x - 1, y);
        }
        
        String upValue() {
            return Character.toString(grid[this.up().x][this.up().y]);
        }
        
        Cell rightUp() {
            return new Cell(x - 1, y + 1);
        }
        
        String rightUpValue() {
            return Character.toString(grid[this.rightUp().x][this.rightUp().y]);
        }
        
        Cell left() {
            return new Cell(x, y - 1);
        }
        
        String leftValue() {
            return Character.toString(grid[this.left().x][this.left().y]);
        }
        
        Cell right() {
            return new Cell(x, y + 1);
        }
        
        String rightValue() {
            return Character.toString(grid[this.right().x][this.right().y]);
        }
        
        Cell leftDown() {
            return new Cell(x + 1, y - 1);
        }
        
        String leftDownValue() {
            return Character.toString(grid[this.leftDown().x][this.leftDown().y]);
        }
        
        Cell down() {
            return new Cell(x + 1, y);
        }
        
        String downValue() {
            return Character.toString(grid[this.down().x][this.down().y]);
        }
        
        Cell rightDown() {
            return new Cell(x + 1, y + 1);
        }
        
        String rightDownValue() {
            return Character.toString(grid[this.rightDown().x][this.rightDown().y]);
        }
    }   
}

public class Word {
    
    static TreeSet<String> words(Grid G) {
        TreeSet<String> solution = new TreeSet<>();
        boolean[][] visited = new boolean[G.rows][G.columns];
        
        // initialize visited
        for(int i = 0; i < G.rows; i++) {
            for(int j = 0; j < G.columns; j++)
                visited[i][j] = false;
        }
        
        // start search from each existing cell
        for(int i = 0; i < G.rows; i++) {
            for(int j = 0; j < G.columns; j++) {
                backtrack(G.cells[i][j], G, solution, 
                    Character.toString(G.grid[i][j]), visited);
            }
        }
        return solution;
    }

    static private void backtrack(Grid.Cell cell, Grid G, 
        TreeSet<String> solution, String word, boolean[][] visited) {
        
        visited[cell.x][cell.y] = true;
        
        // stop condition
        if(! G.dictionary.isPrefix(word))
            return;
        
        // if solution found, will be added
        if(G.dictionary.isWord(word))
            solution.add(word);
        
        // try moving
        if(cell.leftUp().isValid()) {
            backtrack(cell.leftUp(), G, solution, 
                word + cell.leftUpValue(), visited);
            visited[cell.leftUp().x][cell.leftUp().y] = false;
        }
        
        if(cell.up().isValid()) {
            backtrack(cell.up(), G, solution, word + cell.upValue(), visited);
            visited[cell.up().x][cell.up().y] = false;
        }
        
        if(cell.rightUp().isValid()) {
            backtrack(cell.rightUp(), G, solution, 
                word + cell.rightUpValue(), visited);
            visited[cell.rightUp().x][cell.rightUp().y] = false;
        }
        
        if(cell.left().isValid()) {
            backtrack(cell.left(), G, solution, word + cell.leftValue(), visited);
            visited[cell.left().x][cell.left().y] = false;
        }
        
        if(cell.right().isValid()) {
            backtrack(cell.right(), G, solution, word + cell.rightValue(), visited);
            visited[cell.right().x][cell.right().y] = false;
        }
        
        if(cell.leftDown().isValid()) {
            backtrack(cell.leftDown(), G, solution, 
                word + cell.leftDownValue(), visited);
            visited[cell.leftDown().x][cell.leftDown().y] = false;
        }
        
        if(cell.down().isValid()) {
            backtrack(cell.down(), G, solution, word + cell.downValue(), visited);
            visited[cell.down().x][cell.down().y] = false;
        }
        
        if(cell.rightDown().isValid()) {
            backtrack(cell.rightDown(), G, solution, 
                word + cell.rightDownValue(), visited);
            visited[cell.rightDown().x][cell.rightDown().y] = false;
        }
    }
    
    public static void main(String[] args) {
        
    }
}
