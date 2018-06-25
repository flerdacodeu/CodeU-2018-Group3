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
    private static final int row_offset[] =
            new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int col_offset[] =
            new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    private Dictionary dictionary;
    private String[][] grid;
    private int rows;
    private int cols;

    class Pair {
        private int row;
        private int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public void setRow(int row) {
            this.row = row;
        }
    }

    public WordSearch(String word_filename, String dictionary_filename) {
        readGrid(word_filename);
        dictionary = new Dictionary(dictionary_filename);
    }

    public void setGrid(int rows, int cols, String[][] grid) {
        this.rows = rows;
        this.cols = cols;
        this.grid = grid;
    }

    public void getEmptyDictionary() {
        dictionary = new Dictionary();
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
    private Vector<Pair> getNeighbours(Pair cell) {
        Vector<Pair> neighbours = new Vector<>();

        for (int i = 0; i < 8; i++) {
            int cellRow = (int) cell.getRow() + row_offset[i];
            int cellCol = (int) cell.getCol() + col_offset[i];
            if (cellRow >= 0 && cellRow < rows)
                if (cellCol >= 0 && cellCol < cols)
                    neighbours.add(new Pair(cellRow, cellCol));
        }

        return neighbours;
    }

    /*
        This method is similar to the exploration method from DFS.
            cell -> current cell to continue exploration
            words -> list of all words formed from the grid
            word -> word that is formed so far
            visited -> keeps track of the cells already visited

        To continue exploration to a cell, it can't be already visited while forming the same word and
        it needs to form a prefix when its value is concatenated to the word formed so far.
     */
    private void explore(Pair cell, List<String> words, String word, Vector<Pair> visited) {
        visited.add(cell);
        Vector<Pair> neighbours = getNeighbours(cell);

        if (dictionary.isWord(word) && !words.contains(word)) {
            words.add(word);
        }

        for (int i = 0; i < neighbours.size(); i++) {
            int nextRow = (int) neighbours.get(i).getRow();
            int nextCol = (int) neighbours.get(i).getCol();
            if (!visited.contains(neighbours.get(i)) && dictionary.isPrefix(word + grid[nextRow][nextCol])) {
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
