import java.util.*;


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
    private Map<String, Integer> constraints;
    private boolean constraintOn;


    public RearrangingCars(Map<String, Integer> start_state, Map<String, Integer> end_state) {
        this.start_state = start_state;
        this.end_state = end_state;
        this.constraintOn = false;
    }

    public RearrangingCars(Map<String, Integer> start_state, Map<String, Integer> end_state, Map<String, Integer> constraints) {
        this.start_state = start_state;
        this.end_state = end_state;
        this.constraintOn = true;
        this.constraints = constraints;
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

    /**
     * Optional challenge #3
     * # changes made in the rearrangeCars method by introducing conditions for the constraints
     * @return list with all possible sequences of moves
     */
    public List<Move> rearrangeCarsConstraint() {

        List<Move> moves = new LinkedList<>();
        List <Integer> cars = new LinkedList<>();
        for (String space : start_state.keySet()) {
            cars.add(start_state.get(space));
        }
       // boolean[] spaces = markInitialPlaces(cars);
        Queue<Integer> carQueue = new LinkedList<>();

        for(int i=0; i<cars.size(); i++){
            if(!checkCarsInState(cars.get(i))) {
                // if initial state not equals the end state then we at least need
                // to do one swap do we add the car to the stack
                carQueue.add(cars.get(i));
            }
        }

        String car_space;
        int car;
        while(!carQueue.isEmpty()){
            car = carQueue.remove();
            //change place
            car_space= spaceOf(car);
            String empty_space_start = getEmptySpace(start_state);
            if(constraintOn && hasConstraint(car,empty_space_start)){
                carQueue.add(car);
                continue;
            }
            makeMove(empty_space_start, car_space, car, moves);
            if(!checkCarsInState(car))
                carQueue.add(car);
        }

        return moves;
    }

    private boolean checkCarsInState(int car){
        for (String space : end_state.keySet()) {
            if (end_state.get(space) == car) {
                return spaceOf(car).equals(space);
            }
        }
        return false;
    }

    private boolean hasConstraint(int car, String constrainedSpace){
        for(String s:constraints.keySet()){
            if(s.equals(constrainedSpace))
                return constraints.get(constrainedSpace)==(car);
        }
        return false;

    }




    /**
     * Optional challenge #4
     *
     * @return list with all possible sequences of moves
     */
    public List<List<Move>> rearrangeCarsAllPossibilities() {
        List<List<Move>> sequences = new LinkedList<>();
        rearrangeCarsAllPossibilitiesRecursive(start_state, sequences, new LinkedList<>());
        return sequences;
    }

    private void rearrangeCarsAllPossibilitiesRecursive(Map<String, Integer> state, List<List<Move>> possibilities, List<Map<String, Integer>> oldStates) {
        if (!end_state.equals(state)) {
            List<Move> moves = possibilities.isEmpty() ? new LinkedList<>() : new LinkedList<>(possibilities.remove(possibilities.size() - 1));
            oldStates.add(state);
            Map<String, Integer> current_state = new HashMap<>(state);
            List<Move> possibleMoves = getAllPossibleMoves(state, oldStates);
            for (Move move : possibleMoves) {
                moves.add(move);
                possibilities.add(new LinkedList<>(moves));
                current_state.put(move.getNew_space(), move.getCar());
                current_state.put(move.getOld_space(), 0);
                rearrangeCarsAllPossibilitiesRecursive(current_state, possibilities, oldStates);
                current_state.put(move.getOld_space(), move.getCar());
                current_state.put(move.getNew_space(), 0);
                moves.remove(move);
            }
            oldStates.remove(state);
        }
    }

    /**
     * Calculates all possible moves from the current state
     *
     * @param state     current parking lot state
     * @param oldStates old states that shouldn't been repeated
     * @return list of possible moves
     */
    private List<Move> getAllPossibleMoves(Map<String, Integer> state, List<Map<String, Integer>> oldStates) {
        List<Move> moves = new LinkedList<>();
        String empty_space = getEmptySpace(state);
        for (String space : state.keySet()) {
            if (!space.equals(empty_space)) {
                boolean valid = true;
                for (Map<String, Integer> old : oldStates) {
                    if (old.get(empty_space) == state.get(space) && old.get(space) == state.get(empty_space)) {
                        valid = false;
                    }
                }
                if (valid)
                    moves.add(new Move(state.get(space), space, empty_space));
            }
        }
        return moves;
    }
}
