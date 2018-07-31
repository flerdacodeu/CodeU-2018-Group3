import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RearrangingCarsTest {
    @Test
    public void testBasic() {
        // test case given in the question
        ArrayList<RearrangingCars.Car> cars = new ArrayList<>();
        cars.add(new RearrangingCars.Car(1,0,1));
        cars.add(new RearrangingCars.Car(2,1,2));
        cars.add(new RearrangingCars.Car(3,3,0));

        ArrayList<RearrangingCars.Car> results=RearrangingCars.reArrangeCars(cars);

        assertEquals(cars.get(0).endState, results.get(0).currentState);
        assertEquals(cars.get(1).endState, results.get(1).currentState);
        assertEquals(cars.get(2).endState, results.get(2).currentState);
    }

    @Test
    public void testEmpty() {
        // test case given in the question
        ArrayList<RearrangingCars.Car> cars = new ArrayList<>();
        ArrayList<RearrangingCars.Car> results=RearrangingCars.reArrangeCars(cars);
        assertEquals(null, results);
    }

}
