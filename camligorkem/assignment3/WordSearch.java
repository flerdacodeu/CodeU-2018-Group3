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
        Optional<HashSet<String>> foundWords = Optional.empty();
        Optional<HashSet<String>> res;
        for(int i=0; i < n1; i++){
            for(int j=0; j< n2; j++){
                used = new boolean[n1][n2];
                res = Optional.ofNullable(findWordsHelper(dictionary, grid, i, j, used, "").orElse(null));
               if(res !=null){
                   if(foundWords.isPresent()){
                       foundWords.get().addAll(res.get());
                   }else{
                       foundWords = Optional.of(res.get());
                   }
               }
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
    public static Optional<HashSet<String>> findWordsHelper(Dictionary dictionary, char[][] grid, int i, int j, boolean[][] used, String prefix){

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
            //System.out.println("added "+currWord);
        }

        if(dictionary.isPrefix(currWord)){

            // to make sure we don't exceed the upper or lower bound position of the grid and used board
            boolean colUpLimit = (j+1) < grid[0].length ? true:false;
            boolean rowUpLimit = (i+1) < grid.length ? true:false;
            boolean colDownLimit = (j-1) >= 0 ? true:false;
            boolean rowDownLimit = (i-1) >= 0 ? true:false;

            // --  VERTICAL ---
            if( colUpLimit ){ // go right
                if(!used[i][j+1])
                    findWordsHelper(dictionary, grid, i, j+1, used,  currWord).ifPresent(group.get()::addAll);
            }
            if(colDownLimit ){  // go left
                if(!used[i][j-1] )
                    findWordsHelper(dictionary, grid, i, j-1, used, currWord).ifPresent(group.get()::addAll);
            }
            // --  HORIZONTAL ---
            if(rowDownLimit){ // go up
                if(!used[i-1][j])
                    findWordsHelper(dictionary, grid, i-1, j, used, currWord).ifPresent(group.get()::addAll);
            }
            if(rowUpLimit){ // go down
                if(!used[i+1][j])
                    findWordsHelper(dictionary, grid, i+1, j, used, currWord).ifPresent(group.get()::addAll);
            }
            // --  DIAGONAL ---
            if(rowDownLimit && colUpLimit ){ // go up-right
                if(!used[i-1][j+1]  )
                    findWordsHelper(dictionary, grid, i-1, j+1, used, currWord).ifPresent(group.get()::addAll);
            }
            if(rowUpLimit && colUpLimit ){ // go down-right
                if(!used[i+1][j+1] )
                    findWordsHelper(dictionary, grid, i+1, j+1, used, currWord).ifPresent(group.get()::addAll);
            }
            if(rowDownLimit && colDownLimit ){ // go up-left
                if(!used[i-1][j-1])
                    findWordsHelper(dictionary, grid, i-1, j-1, used, currWord).ifPresent(group.get()::addAll);
            }
            if(rowUpLimit && colDownLimit ){ // go down-left
                if(!used[i+1][j-1]  )
                    findWordsHelper(dictionary, grid, i+1, j-1, used, currWord).ifPresent(group.get()::addAll);
            }
        }

        if(group.isPresent())
            return group;
        else
            return Optional.empty();
    }
}
