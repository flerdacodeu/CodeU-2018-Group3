import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RearrangingCarsTest {

    @Test
    public void testEmptyParkingLot() {
        Map<Character, Integer> state = new HashMap<>();
        assertEquals(new LinkedList<>(), RearrangingCars.rearrangeCars(state, state));
    }

    @Test
    public void testFromAssignment() {
        Map<Character, Integer> start = new HashMap<>();
        start.put('A', 1);
        start.put('B', 2);
        start.put('C', 0);
        start.put('D', 3);
        Map<Character, Integer> end = new HashMap<>();
        end.put('A', 3);
        end.put('B', 1);
        end.put('C', 2);
        end.put('D', 0);
        List<RearrangingCars.Step> expectedSteps = new LinkedList<>();
        expectedSteps.add(new RearrangingCars.Step(2,'B','C'));
        expectedSteps.add(new RearrangingCars.Step(1,'A','B'));
        expectedSteps.add(new RearrangingCars.Step(3,'D','A'));
        assertEquals(expectedSteps, RearrangingCars.rearrangeCars(start, end));
    }


    @Test
    public void testComplex() {
        Map<Character, Integer> start = new HashMap<>();
        start.put('A', 1);
        start.put('B', 3);
        start.put('C', 0);
        start.put('D', 4);
        start.put('E', 2);
        start.put('F', 5);
        Map<Character, Integer> end = new HashMap<>();
        end.put('A', 5);
        end.put('B', 4);
        end.put('C', 3);
        end.put('D', 2);
        end.put('E', 1);
        end.put('F', 0);
        List<RearrangingCars.Step> expectedSteps = new LinkedList<>();
        expectedSteps.add(new RearrangingCars.Step(3,'B','C'));
        expectedSteps.add(new RearrangingCars.Step(4,'D','B'));
        expectedSteps.add(new RearrangingCars.Step(2,'E','D'));
        expectedSteps.add(new RearrangingCars.Step(1,'A','E'));
        expectedSteps.add(new RearrangingCars.Step(5,'F','A'));
        assertEquals(expectedSteps, RearrangingCars.rearrangeCars(start, end));
    }
}
