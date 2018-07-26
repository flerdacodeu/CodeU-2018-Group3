import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Identifiers for parking spaces: A,B,C,...
 * Identifiers for cars: 1,2,3,...
 * <p>
 * State of the parking lot is represented by a map, which has the spaces as keys and the current car on the space as values
 * If there is no car the value of the space is 0
 */
public class RearrangingCars {

    /**
     * Gets to valid parking lot states and generates a sequence of moves from the start state to the end state
     * (Valid = same number of spaces/cars and just one free space in each state)
     *
     * @param startState start state of the parking lot
     * @param endState   end state of the parking lot
     * @return sequence of moves from the start state to the end state
     */
    public static List<Step> rearrangeCars(Map<Character, Integer> startState, Map<Character, Integer> endState) {
        List<Step> steps = new LinkedList<>();
        while (!startState.equals(endState)) {
            Step step = findNextStep(startState, endState);
            if (step != null) {
                steps.add(step);
                startState.put(step.from, 0);
                startState.put(step.to, step.car);
            }
        }
        return steps;
    }

    /**
     * Finds the best next step, so that one more position is correct
     *
     * @param start current state of the parking lot
     * @param end   end state of the parking lot
     * @return next step
     */
    private static Step findNextStep(Map<Character, Integer> start, Map<Character, Integer> end) {
        // find free space in start state
        char freeSpace = 'A';
        for (Character space : start.keySet()) {
            if (start.get(space) == 0) {
                freeSpace = space;
                break;
            }
        }
        // find car on freespace in end state
        int car = end.get(freeSpace);
        // search car in start state
        if (car != 0) {
            for (Character space : start.keySet()) {
                if (start.get(space) == car) {
                    return new Step(car, space, freeSpace);
                }
            }
        }
        for (Character space : start.keySet()) {
            if (space != freeSpace && !start.get(space).equals(end.get(space))) {
                return new Step(start.get(space), space, freeSpace);
            }
        }
        return null;
    }

    public static class Step {
        private int car;
        private char from;
        private char to;

        public Step(int car, char from, char to) {
            this.car = car;
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Step) {
                Step step = (Step) object;
                return this.car == step.car && this.from == step.from && this.to == step.to;
            }
            return false;
        }
    }
}
