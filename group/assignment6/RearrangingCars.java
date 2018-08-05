import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
    This class is like a node for the final list, which will store all the moves.
 */
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

    /*
        @param state: start_state or end_state
        @return the first empty space in the required state
     */
    private String getEmptySpace(Map<String, Integer> state) {
        for (String space : state.keySet()) {
            if (state.get(space) == 0)
                return space;
        }
        return null;
    }

    /*
        @return the first different space between start and end state or null if they are the same
     */
    private String getNextSpace() {
        for (String space : end_state.keySet()) {
            if (!end_state.get(space).equals(start_state.get(space))) {
                return space;
            }
        }
        return null;
    }

    /*
        @param car: the number of the car
        @return the space in the start_state where that car is placed
     */
    private String spaceOf(int car) {
        for (String space : start_state.keySet()) {
            if (start_state.get(space) == car) {
                return space;
            }
        }
        return null;
    }

    /*
        @param new_space: the space where the car will be moved
        @param old-space: the space where the car is now
        @param car: number of the car
        @param moves: the list where this move is added

        @return the old space of the car, which after the move will be empty

        It continuously compares the empty space from the start state with the one from the
        end state. If these spaces are the same, there is a possibility that the end state is
        reached. At this time, it compares all the spaces between start and end state with the
        getNextSpace method. If it returns null, the end state has been reached. Otherwise,
        an additional move needs to be done to continue, meaning moving the first car that stays
        in a wrong place and move it in the empty space. Than, the loop starts again.

        Here is an example of a case where an additional move is required:
        Current state:
        A-1 B-2 C-3 D-0
        End stateL
        A-2 B-1 C-3 D-0
     */
    private String makeMove(String new_space,
                            String old_space,
                            int car,
                            List<Move> moves) {
        start_state.put(new_space, car);
        start_state.put(old_space, 0);
        moves.add(new Move(car, old_space, new_space));

        return old_space;
    }

    /*
        @return moves: the list of the moves that have to be done in order to reach end_state with
            as few moves as possible
     */
    public List<Move> rearrangeCars() {
        List<Move> moves = new LinkedList<>();
        String empty_space_start = getEmptySpace(start_state);
        String empty_space_end = getEmptySpace(end_state);
        String car_space, next_space;

        if (empty_space_start == null || empty_space_end == null) {
            return moves;
        }

        while (true) {
            while (!empty_space_start.equals(empty_space_end)) {
                int car = end_state.get(empty_space_start);
                car_space = spaceOf(car);

                empty_space_start = makeMove(empty_space_start, car_space, car, moves);
            }

            next_space = getNextSpace();

            if (next_space != null) {
                empty_space_start = makeMove(empty_space_start, next_space,
                        start_state.get(next_space), moves);
            } else {
                break;
            }
        }
        return moves;
    }
}
