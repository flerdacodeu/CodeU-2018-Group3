package edu.codeU.assignment3;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The class to find all words in the grid.
 */
public class WordSearch {
    /**
     * The number of rows in grid.
     */
    private int rows;
    /**
     * The number of columns in grid;
     */
    private int columns;
    /**
     * The grid in which we look words.
     */
    private char[][] grid;
    /**
     * The List of Words in dictionary.
     */
    private List<String> dict;
    /**
     * Flag array that use in dfs to check existing of current cell of grid at current string.
     */
    private boolean flag[][];

    public WordSearch(int rows, int columns, char[][] grid, List<String> dict) {
        this.rows = rows;
        this.columns = columns;
        this.grid = grid.clone();
        this.dict = dict;
        flag = new boolean[rows][columns];
    }

    /**
     * We find x, y - coordinates in grid of all neighbors.
     * @param x is the current row.
     * @param y is the current column.
     * @param neighborX the x - coordinates of all neighbors.
     * @param neighborY the y - coordinates of all neighbors.
     */
    private void getAllNeighbors(int x, int y, List<Integer> neighborX, List<Integer> neighborY){
        int SIZE_DELTAS = 3;
        int[] deltaX = {-1, 0, 1};
        int[] deltaY = {-1, 0, 1};
        for (int i = 0; i < SIZE_DELTAS; i++){
            for (int j = 0; j < SIZE_DELTAS; j++){
                if ( (deltaX[i] != 0  || deltaY[j] != 0) && x+deltaX[i] >= 0 && x+deltaX[i] < rows && y+deltaY[j] >= 0 && y+deltaY[j] < columns){
                    neighborX.add(x+deltaX[i]);
                    neighborY.add(y+deltaY[j]);
                }
            }
        }
    }

     /**
     * The method to make traversal in the grid starting at position (x,y) and current word curWord.
     * If we find a word we add it ti words set.
     * We don't go far in graph if current string are not prefix of any word in dictionary.
     * @param x is current rows.
     * @paran y pd current column.
     * @param curWord is the current word that was formed before in traversal.
     * @param words is the set that contains all words that we found at the current moment.
     */
    private void dfs(int x, int y, String curWord, Set<String> words){
        flag[x][y] = true;
        char curChar = grid[x][y];
        curWord += curChar;
        if (!isPrefix(curWord)){
            flag[x][y] = false;
            return;
        }
        if (isWord((curWord))){
            words.add(curWord);
        }
        List<Integer> neighborX = new ArrayList<>();
        List<Integer> neighborY = new ArrayList<>();
        getAllNeighbors(x,y, neighborX, neighborY);
        for (int i = 0; i < neighborX.size(); i++){
            int newX = neighborX.get(i);
            int newY = neighborY.get(i);
            if (!flag[newX][newY]){
                dfs(newX, newY, curWord, words);
            }
        }
        flag[x][y] = false;
    }

    /**
     * The method to find all words in grid.
     * It calls dfs from each cells in the grid.
     * @return the set of all words that we have found.
     */
    public Set<String> findAllWords(){
        Set<String> words = new TreeSet<>();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                dfs(i, j, "", words);
            }
        }
        return words;
    }

    /**
     * The method to check if the sting presents as word in dictionary.
     * I use brut force to do it, although I know that we can implement it base on tree.
     * @param str is the current string.
     * @return True/False
     */
    private boolean isWord(String str){
        for (int i = 0; i < dict.size(); i++){
            if (dict.get(i).equals(str)){
                return true;
            }
        }
        return false;
    }

    /**
     * The method to check if the sting is the prefix of any word.
     * I use brut force to do it, although I know that we can implement it base on tree.
     * @param str is the current string.
     * @return True/False
     */
    private boolean isPrefix(String str){
        int len = str.length();
        for (int i = 0; i < dict.size(); i++){
            if (dict.get(i).length() >= len && dict.get(i).substring(0, len).equals(str)){
                return true;
            }
        }
        return false;
    }
}
