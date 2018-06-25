import java.util.HashSet;
import java.util.Optional;

public class WordSearch {
    /**
     * Given a grid and a dictionary it finds the words (isWord=true) that exists in the dictionary
     * and returns hashset of strings that are words
     * @param dictionary is made up a list of entries which are either words or prefixes
     * @param grid the board with the allowed letters
     * @return return a hashset of strings that are found to be words (isWord = true)
      */
    public static Optional<HashSet<String>> findWords(Dictionary dictionary, char[][] grid ){

        int n1 = grid.length;
        int n2 = grid[0].length;
        boolean[][] used;

        // HashSet doesn't allow duplicate words, it is a good option to keep the found word
        Optional<HashSet<String>> foundWords = Optional.of(new HashSet<>());;
        for(int i=0; i < n1; i++){
            for(int j=0; j< n2; j++){
                used = new boolean[n1][n2];
                findWordsHelper(dictionary, grid, i, j, used, "").ifPresent(foundWords.get()::addAll);
            }
        }
        return foundWords;
    }

    /**
     * Recursive function
     * and returns hashset of strings that are words
     * @param dictionary is made up a list of entries which are either words or prefixes
     * @param grid the board with the allowed letters
     * @param i rows ith position in the grid to be checked
     * @param j columns jth position in the grid to be checked
     * @param used to see if the given letter in the position is already used (to handle the 3rd condition)
     * @param prefix the currentPrefix we have to check for the current call of the function.
     *     Conditions to form a word:
     *     1. You can start at any position.
     *     2. You can move to one of the adjacent cells (horizontally/vertically/diagonally).
     *     3. You can't visit the same cell twice in the same word
     * @return return a hashset of strings that are found to be words (isWord = true) starting from a certain position
     */
    private static Optional<HashSet<String>> findWordsHelper(Dictionary dictionary, char[][] grid, int i, int j, boolean[][] used, String prefix){

        Optional<HashSet<String>> group = Optional.of(new HashSet<>());
        if(i > grid.length || j > grid[0].length) return group;
        if( i < 0 || j < 0) return group;

        char c = grid[i][j];
        String currWord = prefix.concat(Character.toString(c));

        if(dictionary.isWord(currWord)){
            if(group.isPresent())
                group.get().add(currWord);
            else{
                HashSet<String> set = new HashSet<>();
                set.add(currWord);
                group = Optional.of(set);
            }
        }
        if(dictionary.isPrefix(currWord)){
            for(Element elementNeighbours: getNeighbours(i, j, grid.length , grid[0].length)){
                if(!used[elementNeighbours.row][elementNeighbours.col])
                    findWordsHelper(dictionary, grid, elementNeighbours.row, elementNeighbours.col, used,  currWord).ifPresent(group.get()::addAll);
            }
        }
        if(group.isPresent())
            return group;
        else
            return Optional.empty();
    }

    static HashSet<Element> getNeighbours(int row, int col, int rowUpperBound, int colUpperBound ){
        // to make sure we don't exceed the upper or lower bound position of the grid and used board
        boolean colLimit;
        boolean rowLimit ;
        boolean sameElement ;
        HashSet<Element> neighbours = new HashSet<>(8);
        for(int i=-1; i <=1; i++){
            for(int j=-1; j <=1; j++){
                colLimit = (j+col) >= colUpperBound ||  (j+col) < 0;
                rowLimit = (i+row) >= rowUpperBound || (i+row) < 0 ;
                sameElement = (i==0) && (j==0);
                if(!colLimit && !rowLimit && !sameElement)
                    neighbours.add(new Element(row+i,col+j));
            }
        }
        return neighbours;
    }

    static class Element {
        int row;
        int col;
        private Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
