package edu.codeU.assignment6;

import java.util.ArrayList;
import java.util.List;

public class RearrangingCars {

    /**
     * N - number of cars. N + 1 - number of parking places.
     */
    private int n;
    /**
     * Start configuration: at i_th position in configuration store i_th car parking position.
     */
    private int[] startCarsPosition;
    /**
     * Final configuration: at i_th position in configuration store i_th car parking position.
     */
    private int[] finalCarsPosition;
    /**
     * Array to store for each place car in start configuration.
     */
    private int[] startPlacePosition;
    /**
     * Array to store for each place car in final configuration.
     */
    private int[] finalPlacePosition;

    public RearrangingCars(int n, int[] startPosition, int[] finalPosition) {
        this.n = n;
        this.startCarsPosition = startPosition;
        this.finalCarsPosition = finalPosition;
        this.startPlacePosition = new int[n+2];
        this.finalPlacePosition = new int[n+2];
        this.findForPlaceCar();
    }

    /**
     * Method to find to each parking place the car.
     */
    private void findForPlaceCar() {
        for (int i = 0; i < n; i++){
            startPlacePosition[startCarsPosition[i]] = i + 1;
            finalPlacePosition[finalCarsPosition[i]] = i + 1;
        }
    }

    /**
     * Find min moves to rearrange cars by finding the free place at each moment.
     * @return the string list of moves with patern as "Car X from Y to Z"
     */
    public List<String> rearrangeCars(){
        List<String> moves = new ArrayList<>();
        int startCar = -1;
        for (int i = 1; i < n+2; i++){
            if (startPlacePosition[i] == 0){
                startCar = i;
            }
        }
        while (finalPlacePosition[startCar] != 0){
            String move = "Car " + finalPlacePosition[startCar] + " from " + startCarsPosition[finalPlacePosition[startCar]-1]
                    + " to " + startCar;
            moves.add(move);
            startCar = startCarsPosition[finalPlacePosition[startCar]-1];
        }
        return moves;
    }
}
