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
     * Graph structure to present grid.
     */
    private List<List<Integer>> graph;
    /**
     * Flag array that use in dfs to check existing of current cell of grid at current string.
     */
    private boolean flag[];

    public WordSearch(int rows, int columns, char[][] grid, List<String> dict) {
        this.rows = rows;
        this.columns = columns;
        this.grid = grid.clone();
        this.dict = dict;
        graph = new ArrayList<>();
        for (int i = 0; i < rows*columns; i++){
            graph.add(new ArrayList<>());
        }
        this.gridToGraph();
        flag = new boolean[rows*columns];
    }

    /**
     * The method that represent the grid as a graph.
     */
    private void gridToGraph(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (i+1 < rows){
                    graph.get((i*columns) + j).add(((i+1)*columns) + j);
                    graph.get(((i+1)*columns) + j).add((i*columns) + j);
                }
                if (j+1 < columns){
                    graph.get((i*columns) + j).add((i*columns) + j+1);
                    graph.get((i*columns) + j+1).add((i*columns) + j);
                }
                if (i+1 < rows && j + 1 < columns){
                    graph.get((i*columns) + j).add(((i+1)*columns) + j + 1);
                    graph.get(((i+1)*columns) + j + 1).add((i*columns) + j);
                }
                if (i+1 < rows && j-1 >= 0){
                    graph.get((i*columns) + j).add(((i+1)*columns) + j - 1);
                    graph.get(((i+1)*columns) + j - 1).add((i*columns) + j);
                }
            }
        }
    }

    /**
     * The method to make traversal in graph starting at position v and current word curWord.
     * If we find a word we add it ti words set.
     * We don't go far in graph if current string are not prefix of any word in dictionary.
     * @param v is current vertex.
     * @param curWord is the current word that was formed before in traversal.
     * @param words is the set that contains all words that we found at the current moment.
     */
    private void dfs(int v, String curWord, Set<String> words){
        flag[v] = true;
        int curRow = v / columns;
        int curColumn = v - columns * curRow;
        char curChar = grid[curRow][curColumn];
        curWord += curChar;
        if (!isPrefix(curWord)){
            flag[v] = false;
            return;
        }
        if (isWord((curWord))){
            words.add(curWord);
        }
        for (int i = 0; i < graph.get(v).size(); i++){
            int to = graph.get(v).get(i);
            if (!flag[to]){
                dfs(to, curWord, words);
            }
        }
        flag[v] = false;
    }

    /**
     * The method to find all words in grid.
     * It calls dfs from each cells in the grid.
     * @return the set of all words that we have found.
     */
    public Set<String> findAllWords(){
        Set<String> words = new TreeSet<>();
        for (int i = 0; i < rows*columns; i++){
            dfs(i, "", words);
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
