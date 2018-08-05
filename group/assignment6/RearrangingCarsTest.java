import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RearrangingCarsTest {

    @Test
    public void test_assignment_input() {
        Map<String, Integer> start = new HashMap<>();
        Map<String, Integer> end = new HashMap<>();

        start.put("A", 1);
        start.put("B", 2);
        start.put("C", 0);
        start.put("D", 3);

        end.put("A", 3);
        end.put("B", 1);
        end.put("C", 2);
        end.put("D", 0);

        RearrangingCars cars = new RearrangingCars(start, end);
        List<Move> moves = new LinkedList<>();

        moves.add(new Move(2, "B", "C"));
        moves.add(new Move(1, "A", "B"));
        moves.add(new Move(3, "D", "A"));

        assertEquals(moves, cars.rearrangeCars());
    }

    @Test
    public void test_empty_parking_space() {
        Map<String, Integer> empty_map = new HashMap<>();
        RearrangingCars cars = new RearrangingCars(empty_map, empty_map);
        List<Move> moves = new LinkedList<>();

        assertEquals(moves, cars.rearrangeCars());
    }

    @Test
    public void test_same_empty_space() {
        Map<String, Integer> start = new HashMap<>();
        Map<String, Integer> end = new HashMap<>();

        start.put("A", 1);
        start.put("B", 2);
        start.put("C", 0);
        start.put("D", 3);

        end.put("A", 3);
        end.put("B", 1);
        end.put("C", 0);
        end.put("D", 2);

        RearrangingCars cars = new RearrangingCars(start, end);
        List<Move> moves = new LinkedList<>();

        moves.add(new Move(1, "A", "C"));
        moves.add(new Move(3, "D", "A"));
        moves.add(new Move(2, "B", "D"));
        moves.add(new Move(1, "C", "B"));

        assertEquals(moves, cars.rearrangeCars());
    }

    @Test
    public void test_false_end_state() {
        Map<String, Integer> start = new HashMap<>();
        Map<String, Integer> end = new HashMap<>();

        start.put("A", 1);
        start.put("B", 2);
        start.put("C", 0);
        start.put("D", 3);

        end.put("A", 3);
        end.put("B", 0);
        end.put("C", 2);
        end.put("D", 1);

        RearrangingCars cars = new RearrangingCars(start, end);
        List<Move> moves = new LinkedList<>();

        moves.add(new Move(2, "B", "C"));
        moves.add(new Move(1, "A", "B"));
        moves.add(new Move(3, "D", "A"));
        moves.add(new Move(1, "B", "D"));

        assertEquals(moves, cars.rearrangeCars());
    }
}
