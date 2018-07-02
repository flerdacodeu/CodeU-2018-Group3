import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

/*
    The idea of this algorithm is to consider all land tiles as independent
    islands and then try to connect as many of them as possible. This is
    similar to the minimum spanning tree algorithms.

    I use an ArrayList to store land tiles, then iterate through them
    and try to connect them wih other land tiles to form a bigger island.

    When iterating, I try to connect the current tile with the one from
    above and from the left because it is enough to find all the connections
    and it is more efficient this way than checking every tile with all of
    its neighbours if they are already part of the same island or not.

    The final number of islands is calculated as the total number of
    land tiles minus the number of merged ones.
 */

public class CountingIslands {
    private int rows;
    private int cols;
    private boolean[][] map;
    private final Pair[] offset;

    class Pair {
        private final int row;
        private final int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public String toString() {
            return "" + row + " " + col + " ";
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair = (Pair) obj;
            return (row == pair.getRow() && col == pair.getCol());
        }
    }

    private void readData(String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            rows = read.nextInt();
            cols = read.nextInt();
            map = new boolean[rows][cols];

            read.nextLine();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    map[row][col] = read.nextBoolean();
                }
                if (row != rows - 1)
                    read.nextLine();
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CountingIslands(String filename) {
        readData(filename);
        offset = new Pair[2];
        // up
        offset[0] = new Pair(-1, 0);
        // left
        offset[1] = new Pair(0, -1);
    }

    /*
        This method returns the number of islands that can be merged.
     */
    private int mergedIslands(ArrayList<Pair> landTiles) {
        Iterator<Pair> iterator = landTiles.iterator();
        int merged = 0;

        while (iterator.hasNext()) {
            Pair current = iterator.next();

            for (int i = 0; i < 2; i++) {
                int row = current.getRow() + offset[i].getCol();
                int col = current.getRow() + offset[i].getCol();

                if (landTiles.contains(new Pair(row, col))) {
                    merged++;
                }
            }
        }

        return merged;
    }

    /*
        This method returns the total number of islands.
     */
    public int countIslands() {
        int islands = 0;
        ArrayList<Pair> landTiles = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (map[row][col]) {
                    landTiles.add(new Pair(row, col));
                    islands++;
                }
            }
        }

        islands -= mergedIslands(landTiles);
        return islands;
    }

    public static void main(String[] args) {
        CountingIslands islands = new CountingIslands("CountingIslands.txt");
        System.out.println(islands.countIslands());
    }
}
