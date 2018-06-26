import java.util.ArrayList;
import java.util.List;

class CountingIslands {
    static int countIslands(int rows, int columns, boolean[][] tiles) {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (tiles[i][j]) {
                    tiles = markIsland(tiles, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean[][] markIsland(boolean[][] tiles, int row, int column) {
        tiles[row][column] = false;
        for (GridPos pos : getNeighbors(row, column, tiles.length, tiles[row].length)) {
            if (tiles[pos.row][pos.column]) {
                tiles = markIsland(tiles, pos.row, pos.column);
            }
        }
        return tiles;
    }

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

    private static class GridPos {
        int row;
        int column;

        GridPos(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
