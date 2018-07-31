import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RearrangingCars {

    public static ArrayList<Car> reArrangeCars(ArrayList<Car> cars){
        if(cars.size() == 0)
            return null;

        boolean[] N = markInitialPlaces(cars);
        Queue<Car> carQueue = new LinkedList<>();

        for(int i=0; i<cars.size(); i++){
            if(!checkCarsInState(cars.get(i))) {
                // if initial state not equals the end state then we at least need
                // to do one swap do we add the car to the stack
                carQueue.add(cars.get(i));
            }
            else
                System.out.println("Car "+cars.get(i).id+" is placed on "+cars.get(i).currentState);
        }

        int moves =0;
        while(!carQueue.isEmpty()){
            Car c = carQueue.remove();
            //change place
            int tempSpace = c.currentState;
            c.currentState = getEmptyPlace(N);
            N[c.currentState] = true;
            N[tempSpace] = false;
            moves ++;
            System.out.print("\nCar "+c.id+" is moved to place "+tempSpace+" to "+c.currentState);
            if(!checkCarsInState(c))
                carQueue.add(c);
            else
                System.out.print(" and Car "+c.id+" is placed on "+c.currentState);
        }
        System.out.println("\nProcess done in "+moves+" steps");
        return cars;
    }

    private static boolean[] markInitialPlaces(ArrayList<Car> cars){
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
