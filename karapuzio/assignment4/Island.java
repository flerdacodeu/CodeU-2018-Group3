package edu.codeU.assignment4;

public class Island {

    /**
     * Number of rows in map.
     */
    private int rows;
    /**
     * Number of columns in map.
     */
    private int columns;
    /**
     * The map where we look for islands.
     */
    private boolean[][] map;
    /**
     * Flag array that is used in traversal map using queue structure.
     */
    private boolean[][] flag;

    public Island(int rows, int columns, boolean[][] map) {
        this.rows = rows;
        this.columns = columns;
        this.map = map;
        flag = new boolean[rows][columns];
    }

    /**
     * Method to add element in current traversal to queue.
     * @param queX stores the x-coordinates in queue for all appropriate neighbor.
     * @param queY stores the y-coordinates in queue for all appropriate neighbor.
     * @param queEnd is the position for adding new element.
     * @param x the x-coordinates of new element.
     * @param y the y-coordinates of new element.
     */
    private void addElementToQue(int[] queX, int[] queY, int queEnd, int x, int y){
         queX[queEnd] = x;
         queY[queEnd] = y;
         flag[x][y] = true;
    }

    /**
     * Method to traverse the map to find all cells for current island,
     * that we didn't visit before.
     * @param i the first x-coordinate in new traversal.
     * @param j the first y-coordinate in new traversal.
     */
    private void traversal(int i, int j){
        int[] queX = new int[rows*columns];
        int[] queY = new int[rows*columns];
        int queBegin = 0;
        int queEnd = 1;
        queX[0] = i;
        queY[0] = j;
        flag[i][j] = true;
        while (queBegin < queEnd){
            int x = queX[queBegin];
            int y = queY[queBegin];
            if (x - 1 >= 0 && map[x - 1][y] && !flag[x - 1][y]){
                addElementToQue(queX, queY, queEnd, x - 1, y);
                queEnd++;
            }
            if (x + 1 < rows && map[x + 1][y] && !flag[x + 1][y]){
                addElementToQue(queX, queY, queEnd, x + 1, y);
                queEnd++;
            }
            if (y - 1 >= 0 && map[x][y - 1] && !flag[x][y - 1]){
                addElementToQue(queX, queY, queEnd, x, y - 1);
                queEnd++;
            }
            if (y + 1 < columns && map[x][y + 1] && !flag[x][y + 1]){
                addElementToQue(queX, queY, queEnd, x, y + 1);
                queEnd++;
            }
            queBegin++;
        }
    }

    /**
     * Method that go through all map to check if we visit these cells before
     * and if these cells are land.
     * @return the number of all island.
     */
    public int countIslanads(){
        int numberOfIsland = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (!flag[i][j] && map[i][j]){
                     numberOfIsland++;
                     traversal(i, j);
                }
            }
        }
        return numberOfIsland;
    }
}
