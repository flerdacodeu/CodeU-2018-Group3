import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RearrangingCars {

    public static LinkedList<Move> reArrangeCars(LinkedList<Car> cars){
        if(cars.size() == 0)
            return null;

        boolean[] spaces = markInitialPlaces(cars);
        Queue<Car> carQueue = new LinkedList<>();
        LinkedList<Move> movements = new LinkedList<>();

        for(int i=0; i<cars.size(); i++){
            if(!checkCarsInState(cars.get(i))) {
                // if initial state not equals the end state then we at least need
                // to do one swap do we add the car to the stack
                carQueue.add(cars.get(i));
            }
        }

        int tempSpace;
        Car c;
        while(!carQueue.isEmpty()){
            c = carQueue.remove();
            //change place
            tempSpace= c.currentState;
            c.currentState = getEmptyPlace(spaces);
            spaces[c.currentState] = true;
            spaces[tempSpace] = false;
           // System.out.print("\nCar "+c.id+" is moved to place "+tempSpace+" to "+c.currentState);
            movements.add(new Move(c.id, tempSpace,c.currentState));
            if(!checkCarsInState(c))
                carQueue.add(c);
        }

        return movements;
    }

    private static boolean[] markInitialPlaces(LinkedList<Car> cars){
        boolean[] numSpace = new boolean[cars.size()+1];
        for(Car c:cars){
            numSpace[c.startState]=true;
        }
        return numSpace;
    }

    private static boolean checkCarsInState(Car c){
        return c.currentState == c.endState;
    }

    private static int getEmptyPlace(boolean[] N){
        for ( int i=0; i <N.length; i++){
            if(N[i]==false)
                return i;
        }
        return -1;
    }


    public static class Move {
        int startState;
        int endState;
        int carId;
        public Move(int carId, int startState, int endState) {
            this.carId=carId;
            this.startState = startState;
            this.endState = endState;
        }
    }

    public static class Car {
        int id;
        int startState;
        int endState;
        int currentState;
        public Car(int id, int startState, int endState) {
            this.id = id;
            this.startState = startState;
            this.currentState = startState;
            this.endState = endState;
        }
    }
}
