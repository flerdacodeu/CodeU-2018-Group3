import javafx.util.Pair;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/*
    The code reads the input from two files:

    wordSearch.txt :
        row_number col_number
        // the grid

    dictionary.txt :
        number_of_words
        word_1
        .....
        word_n
*/

public class WordSearch {
    private Dictionary dictionary;
    private String[][] grid;
    private int rows;
    private int cols;

    public WordSearch(String word_filename, String dictionary_filename) {
        readGrid(word_filename);
        dictionary = new Dictionary(dictionary_filename);
    }

    public void setGrid(int rows, int cols, String[][] grid) {
        this.rows = rows;
        this.cols = cols;
        this.grid = grid;
    }

    public void delDictionary() {
        dictionary.clear();
    }

    private void readGrid(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));

            this.rows = sc.nextInt();
            this.cols = sc.nextInt();
            grid = new String[rows][cols];

            if (rows != 0 && cols != 0)
                sc.nextLine();

            for(int row_index = 0; row_index < rows; row_index++) {
                for (int col_index = 0; col_index < cols; col_index++) {
                        grid[row_index][col_index] = sc.next();
                }
                if (row_index != rows - 1)
                    sc.nextLine();
            }

            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        This method returns the vector of all valid neighbours for a cell using
        two arrays of offsets, for rows and for cols.
     */
    private Vector<Pair> getSuccessors(Pair cell) {
        Vector<Pair> succ = new Vector<>();
        int row_offset[] = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int col_offset[] = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int cellK = (int) cell.getKey() + row_offset[i];
            int cellV = (int) cell.getValue() + col_offset[i];
            if (cellK >= 0 && cellK < rows)
                if (cellV >= 0 && cellV < cols)
                    succ.add(new Pair(cellK, cellV));
        }

        return succ;
    }

    /*
        This method is similar to the exploration method from DFS.
            cell -> current cell to continue exploration
            words -> list of all words formed from the grid
            word -> word that is formed so far
            visited -> keeps track of the cells already visited

        To continue exploration to a cell, it can't be already visited while forming the same word and
        it needs to form a prefix when it's concatenated to the word formed so far.
     */
    private void explore(Pair cell, List<String> words, String word, Vector<Pair> visited) {
        visited.add(cell);
        Vector<Pair> succ = getSuccessors(cell);

        if (dictionary.isWord(word) && !words.contains(word)) {
            words.add(word);
        }

        for (int i = 0; i < succ.size(); i++) {
            int nextRow = (int) succ.get(i).getKey();
            int nextCol = (int) succ.get(i).getValue();
            if (!visited.contains(succ.get(i)) && dictionary.isPrefix(word + grid[nextRow][nextCol])) {
                Vector<Pair> visited_copy = (Vector<Pair>) visited.clone();
                explore(new Pair(nextRow, nextCol), words, word + grid[nextRow][nextCol], visited_copy);
            }
        }
    }

    /*
        This method tries to form words from every cell of the grid.
        Returns null for an empty grid and an empty list for an
        empty dictionary.
     */
    public List<String> getWords() {
        List<String> words = new ArrayList<String>();

        if (rows == 0 && cols == 0){
            System.out.println("Grid is empty");
            return null;
        }

        if (dictionary.isEmpty()) {
            System.out.println("Dictionary is empty");
        }

        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Vector<Pair> visited = new Vector<>();
                explore(new Pair(row, col), words, grid[row][col], visited);
            }
        }

        return words;
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch("wordSearch.txt", "dictionary.txt");
        System.out.println(ws.getWords());
    }
}
