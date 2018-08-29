import java.util.HashSet;
import java.util.Stack;
import static java.lang.Math.abs;

// This class contains 2 solution to the problem the first one is chosen and reasons are stated below
// but both solutions are kept in the file
public class CountIslands {
    /**
     * General Note:
     * 1. In both solutions I used visited matrix since I didn't want
     * to manipulate the data in the original tiles grid. Even though the problem could be solved with only one matrix
     * I don't think that manipulating the original data is a good approach.
     *
     * 2. I implemented two approaches recursive and iterative however, I would have chosen to use
     *  the first one for several reasons:
     *  - code is more compact, clear and easy to read
     *  - in the second solution we use an additional data structure stack that adds
     *  space complexity whereas first solution doesn't
     *  - both solutions have a time complexity O(rows*cols)
     */


    /**
     * Given a 2d map of boolean tiles (false = water, true= island), number of rows and number of cols
     * returns the count of islands
     * @param tilesMap 2d map of boolean tiles
     * @param rows number of rows
     * @param cols number of columns
     * @return returns the number of islands
     * Note: assumes non-empty tiles map
     */
    public static int countIslands(boolean[][] tilesMap, int rows, int cols){
        boolean[][] visited = new boolean[rows][cols];
        int numIslands = 0;
        for(int i =0; i < rows; i++){
            for (int j=0; j <cols; j++){
                if(tilesMap[i][j] && !visited[i][j]){
                    visited[i][j]= true;
                    countIslandsHelper(tilesMap, visited, i, j);
                    numIslands++;
                }
            }
        }
        return numIslands;
    }

    /**
     * Recursive helper function for countIslands
     * and iterate through the elements of the similar islands
     * @param tiles 2d map of boolean tiles
     * @param visited 2d map to see if a tile is already visited (boolean)
     * @param i rows ith position in the tiles map to be checked
     * @param j columns jth position in the tiles map to be checked
     */
    private static void countIslandsHelper(boolean[][] tiles, boolean [][]visited, int i, int j){
        //check for its neighbors
        visited[i][j]=true;
        if(tiles[i][j]){
            if(j-1 >= 0 && !visited[i][j-1])//left
                countIslandsHelper(tiles, visited,i, j-1);
            if(j+1 < tiles[0].length && !visited[i][j+1])//right
                countIslandsHelper(tiles, visited,i, j+1);
            if(i+1 < tiles.length && !visited[i+1][j])//down
                countIslandsHelper(tiles, visited,i+1, j);
            if(i-1 >= 0 && !visited[i-1][j])//up
                countIslandsHelper(tiles, visited,i-1, j);
        }
    }

    /**
     * Returns the horizontal and vertical neighbours of a selected tile
     * @param row row index of the selected tile
     * @param col column index of the selected tile
     * @param rowUpperLimit number of rows in the tiles map
     * @param colUpperLimit number of columns in the tiles map
     *  only used for iterative version
     */
    private static HashSet<Neighbour> getNeighbours(int row, int col, int rowUpperLimit, int colUpperLimit){
        //only horizontal and vertical
        boolean colLimit;
        boolean rowLimit ;
        boolean sameElement ;  // it shouldn't be itself
        boolean diagonal;    // it shouldn't return diagonal neighbours

        HashSet<Neighbour> neighbours = new HashSet<>(8);
        for(int i=-1; i <=1; i++){
            for(int j=-1; j <=1; j++){
                colLimit = (j+col) >= colUpperLimit ||  (j+col) < 0;
                rowLimit = (i+row) >= rowUpperLimit || (i+row) < 0 ;
                sameElement = (i==0) && (j==0);
                diagonal = (i == j) || (abs(i-j)==2);
                if(!colLimit && !rowLimit && !sameElement && !diagonal)
                    neighbours.add(new Neighbour(row+i,col+j));
            }
        }
        return neighbours;
    }

    /**
     * Iterative Solution: Given a 2d map of boolean tiles (false = water, true= island),
     * number of rows and number of cols returns the count of islands
     * @param tilesMap 2d map of boolean tiles
     * @param rows number of rows
     * @param cols number of columns
     * @return returns the number of islands
     * Note: assumes non-empty tiles map
     */
    public static int countIslandsIterative(boolean[][] tilesMap, int rows, int cols){
        // false= water, true =land
        Neighbour p;
        boolean[][] visited = new boolean[rows][cols];
        int numIslands = 0;
        Stack<Neighbour> stack = new Stack<>();
        for(int i =0; i < rows; i++){
            for (int j=0; j <cols; j++){
                if(tilesMap[i][j] && !visited[i][j]) {
                    stack.push(new Neighbour(i, j));
                    numIslands++;
                }
                while(!stack.empty()){
                    p = stack.pop();
                    visited[p.row][p.col] = true;
                    // get neighbours
                    for (Neighbour n : getNeighbours(p.row, p.col, tilesMap.length, tilesMap[0].length)) {
                        if (tilesMap[n.row][n.col] && !visited[n.row][n.col]) {
                            stack.push(new Neighbour(n.row, n.col));
                            visited[n.row][n.col] = true;
                        }
                    }
                }
            }
        }
        return numIslands;
    }

    static class Neighbour{
        int row;
        int col;
        private  Neighbour(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}
