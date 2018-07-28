import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Move{
    private int car;
    private String old_space;
    private String new_space;

    public Move(int car, String old_space, String new_space) {
        this.car = car;
        this.old_space = old_space;
        this.new_space = new_space;
    }

    @Override
    public boolean equals(Object obj) {
        Move move = (Move) obj;
        return (move.getCar() == car && move.getOld_space() == old_space && move.getNew_space() == new_space);
    }

    public int getCar() {
        return car;
    }

    public String getOld_space() {
        return old_space;
    }

    public String getNew_space() {
        return new_space;
    }
}

public class RearrangingCars {

    private Map<String, Integer> start_state;
    private Map<String, Integer> end_state;

    public RearrangingCars(Map<String, Integer> start_state, Map<String, Integer> end_state) {
        this.start_state = start_state;
        this.end_state = end_state;
    }

    private String getEmptySpace(Map<String, Integer> state) {
        for (String space : state.keySet()) {
            if (state.get(space) == 0)
                return space;
        }
        return null;
    }

    private String isFinished() {
        for (String space : end_state.keySet()) {
            if (!end_state.get(space).equals(start_state.get(space))) {
                return space;
            }
        }
        return null;
    }

    private String spaceOf(int car) {
        for (String space : start_state.keySet()) {
            if (start_state.get(space) == car) {
                return space;
            }
        }
        return null;
    }

    public List<Move> rearrangeCars() {
        List<Move> moves = new LinkedList<>();
        String empty_space_start = getEmptySpace(start_state);
        String empty_space_end = getEmptySpace(end_state);
        String next_space = isFinished();
        boolean stop = false;

        while (next_space != null) {
            if (stop) {
                start_state.put(empty_space_start, start_state.get(next_space));
                start_state.put(next_space, 0);
                moves.add(new Move(start_state.get(empty_space_start), next_space, empty_space_start));
                empty_space_start = next_space;
            }

            while (!empty_space_start.equals(empty_space_end)) {
                int car = end_state.get(empty_space_start);
                String space = spaceOf(car);

                start_state.put(empty_space_start, car);
                start_state.put(space, 0);
                moves.add(new Move(car, space, empty_space_start));
                empty_space_start = space;
            }

            stop = true;
            next_space = isFinished();
        }
        return moves;
    }
}
