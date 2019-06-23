import java.util.ArrayList;
import java.util.List;

class CountingIslands {

    /**
     * Counts the islands in a given grid
     *
     * @param rows    number of rows
     * @param columns number of columns
     * @param tiles   grid with land(true) and sea(false)
     * @return number of islands in the grid
     */
    static int countIslands(boolean[][] tiles) {
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j]) {
                    tiles = markIsland(tiles, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Removes one island recursively
     *
     * @param tiles  grid with land and see
     * @param row row of the current point
     * @param column column of the current point
     * @return grid with land tiles of the island marked as false
     */
    private static boolean[][] markIsland(boolean[][] tiles, int row, int column) {
        tiles[row][column] = false;
        for (GridPos pos : getNeighbors(row, column, tiles.length, tiles[row].length)) {
            if (tiles[pos.row][pos.column]) {
                tiles = markIsland(tiles, pos.row, pos.column);
            }
        }
        return tiles;
    }

    /**
     * Creates a list of the direct neighbors of a point
     *
     * @param row     row of the point
     * @param column  column of the point
     * @param rows    number of rows in the grid
     * @param columns number of columns in the grid
     * @return list of grid positions with the neighbors
     */
    private static List<GridPos> getNeighbors(int row, int column, int rows, int columns) {
        List<GridPos> positions = new ArrayList<>();
        if (row + 1 < rows) {
            positions.add(new GridPos(row + 1, column));
        }
        if (column + 1 < columns) {
            positions.add(new GridPos(row, column + 1));
        }
        if (row - 1 >= 0) {
            positions.add(new GridPos(row - 1, column));
        }
        if (column - 1 >= 0) {
            positions.add(new GridPos(row, column - 1));
        }
        return positions;
    }

    /**
     * Represents a position in a grid
     */
    private static class GridPos {
        int row;
        int column;

        GridPos(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
