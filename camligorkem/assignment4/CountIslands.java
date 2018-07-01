import java.awt.Point;
import java.util.HashSet;
import java.util.Stack;
import static java.lang.Math.abs;

public class CountIslands {
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
            for (int j=0; j <rows; j++){
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
        for(Neighbour n: getNeighbours(i,j, tiles.length, tiles[0].length)){
            if(tiles[n.row][n.col]  && !visited[n.row][n.col]){
                visited[n.row][n.col]=true;
                countIslandsHelper(tiles, visited,n.row, n.col);
            }
        }
    }

    /**
     * Returns the horizontal and vertical neighbours of a selected tile
     * @param row row index of the selected tile
     * @param col column index of the selected tile
     * @param rowUpperLimit number of rows in the tiles map
     * @param colUpperLimit number of columns in the tiles map
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
        Point p;
        boolean[][] visited = new boolean[rows][cols];
        int numIslands = 0;
        Stack<Point> stack = new Stack<>();
        for(int i =0; i < rows; i++){
            for (int j=0; j <rows; j++){
                stack.push(new Point(i,j));
                while(!stack.empty()){
                    p = stack.pop();
                    if(tilesMap[p.x][p.y] && !visited[p.x][p.y]) {
                        visited[p.x][p.y] = true;
                        numIslands++;
                    }
                    // get neighbours
                    if(tilesMap[p.x][p.y]) {
                        for (Neighbour n : getNeighbours(p.x, p.y, tilesMap.length, tilesMap[0].length)) {
                            if (tilesMap[n.row][n.col] && !visited[n.row][n.col]) {
                                stack.push(new Point(n.row, n.col));
                                visited[n.row][n.col] = true;
                            }
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

