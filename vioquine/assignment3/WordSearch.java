import java.util.HashSet;
import java.util.Optional;
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
        //up right
        if (row - 1 >= 0 && column + 1 < grid[row].length) {
            checkForPosition(dictionary, grid, row - 1, column + 1, prefix, visited).ifPresent(foundWords::addAll);
        }
        //right
        if (column + 1 < grid[row].length) {
            checkForPosition(dictionary, grid, row, column + 1, prefix, visited).ifPresent(foundWords::addAll);
        }
        //down right
        if (row + 1 < grid.length && column + 1 < grid[row].length) {
            checkForPosition(dictionary, grid, row + 1, column + 1, prefix, visited).ifPresent(foundWords::addAll);
        }
        // down
        if (row + 1 < grid.length) {
            checkForPosition(dictionary, grid, row + 1, column, prefix, visited).ifPresent(foundWords::addAll);
        }
        //up left
        if (row - 1 >= 0 && column - 1 >= 0) {
            checkForPosition(dictionary, grid, row - 1, column - 1, prefix, visited).ifPresent(foundWords::addAll);
        }
        //left
        if (column - 1 >= 0) {
            checkForPosition(dictionary, grid, row, column - 1, prefix, visited).ifPresent(foundWords::addAll);
        }
        //down left
        if (row + 1 < grid.length && column - 1 >= 0) {
            checkForPosition(dictionary, grid, row + 1, column - 1, prefix, visited).ifPresent(foundWords::addAll);
        }
        //up
        if (row - 1 >= 0) {
            checkForPosition(dictionary, grid, row - 1, column, prefix, visited).ifPresent(foundWords::addAll);
        }
        return foundWords;
    }

    /**
     * Checks, if the position can be used to form a word.
     * If it can be used, the search for the words that can be formed will be run
     * It it can't be used (because it was already visited or it is not part of an available prefix), an empty Optional will be returned
     *
     * @param dictionary dictionary of the words
     * @param grid       grid with characters in lower case
     * @param row        current row
     * @param column     current column
     * @param prefix     current formed prefix
     * @param visited    information about visited positions
     * @return if the given position wasn't visited and prefix with the value of the position is also a prefix, it returns the set of found words. Otherwise it return an empty optional
     */
    static Optional<Set<String>> checkForPosition(Dictionary dictionary, char[][] grid, int row, int column, String prefix, boolean[][] visited) {
        if (!visited[row][column] && dictionary.isPrefix(prefix + grid[row][column])) {
            visited[row][column] = true;
            Set<String> result = searchWordsFromPosition(dictionary, grid, row, column, prefix + grid[row][column], visited);
            visited[row][column] = false;
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
