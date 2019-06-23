import java.util.ArrayList;
import java.util.List;

class CountingIslandsDisjointSet {
    static int countIslands(boolean[][] tiles) {
        if(tiles.length == 0){
            return 0;
        }
        int noLand = 1;
        DisjointSet<Integer> landSet = new DisjointSet<>();
        int[][] lands = new int[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j]) {
                    lands[i][j] = noLand;
                    landSet.makeSet(noLand++);
                }
            }
        }
        for (int i = 0; i < lands.length; i++) {
            for (int j = 0; j < lands[i].length; j++) {
                if (lands[i][j] != 0) {
                    for (Integer neighbor : getNeighbors(lands, i, j)) {
                        landSet.union(lands[i][j], neighbor);
                    }
                }
            }
        }
        return landSet.getSize();
    }

    private static List<Integer> getNeighbors(int[][] field, int row, int column) {
        List<Integer> neighbors = new ArrayList<>();
        if (row + 1 < field.length && field[row + 1][column] != 0) {
            neighbors.add(field[row + 1][column]);
        }
        if (column + 1 < field[row].length && field[row ][column+1] != 0) {
            neighbors.add(field[row][column + 1]);
        }
        if (row - 1 >= 0 && field[row - 1][column] != 0) {
            neighbors.add(field[row - 1][column]);
        }
        if (column - 1 >= 0 && field[row][column - 1] != 0) {
            neighbors.add(field[row][column - 1]);
        }
        return neighbors;
    }


}
