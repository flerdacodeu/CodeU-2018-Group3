package edu.codeU.assignment4;

import java.util.ArrayDeque;

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

    public Island(int rows, int columns, boolean[][] map) {
        this.rows = rows;
        this.columns = columns;
        this.map = map;
    }

    /**
     * Method to add element in current traversal to queue.
     * @param queX stores the x-coordinates in queue for all appropriate neighbor.
     * @param queY stores the y-coordinates in queue for all appropriate neighbor.
     * @param x the x-coordinates of new element.
     * @param y the y-coordinates of new element.
     */
    private void addElementToQue(ArrayDeque<Integer> queX, ArrayDeque<Integer> queY, int x, int y){
         queX.addLast(x);
         queY.addLast(y);
         map[x][y] = false;
    }


    /**
     * Method to traverse the map to find all cells for current island,
     * that we didn't visit before.
     * @param i the first x-coordinate in new traversal.
     * @param j the first y-coordinate in new traversal.
     */
    private void traversal(int i, int j){
        ArrayDeque<Integer> queX = new ArrayDeque<>();
        ArrayDeque<Integer> queY = new ArrayDeque<>();
        queX.addFirst(i);
        queY.addFirst(j);
        map[i][j] = false;
        while (!queX.isEmpty() && !queY.isEmpty()){
            int x = queX.poll();
            int y = queY.poll();
            if (x - 1 >= 0 && map[x - 1][y]){
                addElementToQue(queX, queY,x - 1, y);
            }
            if (x + 1 < rows && map[x + 1][y]){
                addElementToQue(queX, queY,x + 1, y);
            }
            if (y - 1 >= 0 && map[x][y - 1]){
                addElementToQue(queX, queY, x, y - 1);
            }
            if (y + 1 < columns && map[x][y + 1]){
                addElementToQue(queX, queY, x, y + 1);
            }
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
                if (map[i][j]){
                     numberOfIsland++;
                     traversal(i, j);
                }
            }
        }
        return numberOfIsland;
    }
}
