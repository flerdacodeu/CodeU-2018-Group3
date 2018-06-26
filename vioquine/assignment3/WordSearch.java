import java.util.HashSet;
import java.util.Set;

class WordSearch {

    /**
     * Searches for all words from a given dictionary that can be formed in a given grid
     *
     * @param dictionary dictionary of the words
     * @param grid       grid with characters in lower case
     * @return set of the found words
     */
    static Set<String> searchWords(Dictionary dictionary, char[][] grid) {
        Set<String> foundWords = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (dictionary.isPrefix(String.valueOf(grid[i][j]))) {
                    boolean[][] visited = new boolean[grid.length][grid[0].length];
                    visited[i][j] = true;
                    //Search word from here
                    foundWords.addAll(searchWordsFromPosition(dictionary, grid, i, j, String.valueOf(grid[i][j]), visited));
                }
            }
        }
        return foundWords;
    }

    /**
     * Searches for all words from a given dictionary that can be formed in a given grid starting
     * Recursive function, which gets the current position and an array with information about visited positions
     * Additionally it gets the current prefix
     *
     * @param dictionary dictionary of the words
     * @param grid       grid with characters in lower case
     * @param row        current row
     * @param column     current column
     * @param prefix     current formed prefix
     * @param visited    information about visited positions
     * @return set of words that can be formed
     */
    private static Set<String> searchWordsFromPosition(Dictionary dictionary, char[][] grid, int row, int column, String prefix, boolean[][] visited) {
        Set<String> foundWords = new HashSet<>();
        if (dictionary.isWord(prefix)) {
            foundWords.add(prefix);
        }
        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, grid.length - 1); i++) {
            for (int j = Math.max(0, column - 1); j <= Math.min(column + 1, grid[row].length - 1); j++) {
                if (!visited[i][j] && dictionary.isPrefix(prefix + grid[i][j])) {
                    visited[i][j] = true;
                    foundWords.addAll(searchWordsFromPosition(dictionary, grid, i, j, prefix + grid[i][j], visited));
                    visited[i][j] = false;
                }
            }
        }
        return foundWords;
    }
}
