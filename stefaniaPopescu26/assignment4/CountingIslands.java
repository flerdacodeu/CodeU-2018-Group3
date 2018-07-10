import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/*
    The idea of this algorithm is to consider all land tiles as independent
    islands and then try to connect as many of them as possible. This is
    similar to the minimum spanning tree algorithms.

    I use an ArrayList to store land tiles, then iterate through them
    and try to connect them with other land tiles to form a bigger island.

    When iterating, I try to connect the current tile with the one from
    above and from the left because it is enough to find all the connections
    and it is more efficient this way than checking every tile with all of
    its neighbours if they are already part of the same island or not.
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
        This method returns the total number of islands.
     */
    public int countIslands() {
        int count = 0;
        DisjointSet sets = new DisjointSet();
        ArrayList<Pair> landTiles = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (map[row][col]) {
                    sets.makeSet(count++);
                    landTiles.add(new Pair(row, col));
                }
            }
        }

        /*
            The landTiles ArrayList keeps all the land tiles as a pair of the row and
            the column where they can be found on the map, at the index that
            corresponds to the representative number that has been assigned to them.
         */

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 2; j++) {
                Pair pos = landTiles.get(i);
                int row = pos.getRow() + offset[j].getRow();
                int col = pos.getCol() + offset[j].getCol();

                if (row >= 0 && col >= 0 && row < rows && col < cols) {
                    if (map[row][col]) {
                        int value2 = landTiles.indexOf(new Pair(row, col));
                        LinkedList<DisjointSet.Node> list1 = sets.find(i);
                        LinkedList<DisjointSet.Node> list2 = sets.find(value2);

                        if (list1.getFirst() != list2.getFirst()) {
                            /*
                                The two land tiles are part of different islands, so they
                                need to be merged.
                             */
                            sets.union(i, value2);
                        }
                    }
                }
            }
        }

        return sets.getCountSets();
    }

    public static void main(String[] args) {
        CountingIslands islands = new CountingIslands("CountingIslands.txt");
        System.out.println(islands.countIslands());
    }
}
