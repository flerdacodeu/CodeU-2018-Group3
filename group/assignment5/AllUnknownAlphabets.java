import java.util.LinkedList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RearrangingCarsTest {
    @Test
    public void testBasic() {
        // test case given in the question
        LinkedList<RearrangingCars.Car> cars = new LinkedList<>();
        cars.add(new RearrangingCars.Car(1,0,1));
        cars.add(new RearrangingCars.Car(2,1,2));
        cars.add(new RearrangingCars.Car(3,3,0));

        LinkedList<RearrangingCars.Move> moves = new LinkedList<>();
        moves.add(new RearrangingCars.Move(1,0,2));
        moves.add(new RearrangingCars.Move(2,1,0));
        moves.add(new RearrangingCars.Move(3,3,1));
        moves.add(new RearrangingCars.Move(1,2,3));
        moves.add(new RearrangingCars.Move(2,0,2));
        moves.add(new RearrangingCars.Move(3,1,0));
        moves.add(new RearrangingCars.Move(1,3,1));

        LinkedList<RearrangingCars.Move> results=RearrangingCars.reArrangeCars(cars);

        for(int i = 0; i < results.size(); i++){
            assertEquals(moves.get(i).carId, results.get(i).carId);
            assertEquals(moves.get(i).startState, results.get(i).startState);
            assertEquals(moves.get(i).endState, results.get(i).endState);
        }
    }

    @Test
    public void testEmpty() {
        // test case given in the question
        LinkedList<RearrangingCars.Car> cars = new LinkedList<>();
        LinkedList<RearrangingCars.Move> results=RearrangingCars.reArrangeCars(cars);
        assertEquals(null, RearrangingCars.reArrangeCars(cars));
    }

}
