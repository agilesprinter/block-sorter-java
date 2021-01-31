package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * A plan is just a series of POSSIBLE steps. Steps such as moving from an empty stack to a full one make no sense.
 * Create a plan with some stacks, then ask it to generate. A random plan of possible moves will be assembled.
 * <p>
 * Many plans are generated. Each has its own Stacks object. The Stacks object captures the current state,
 * the target state and the initial state
 */
@Setter
@Getter
public class Plan {

    int planSize;
    int planScore;
    Stacks myStacks;
    List<Step> steps = new ArrayList<Step>();
    private Random rand = new Random();

    public Plan(int planSize, List<String> initialState, List<String> targetState) {

        if (initialState == null || targetState == null)
            throw new RuntimeException("Plan cannot be created. Stacks object is null");

        if (initialState.size() != targetState.size()) {
            throw new RuntimeException("Initial state and target state must have the same number of blocks");
        }

        if (!initialState.containsAll(targetState)) {
            throw new RuntimeException("Initial state and target state must have the same block names");
        }

        this.planSize = planSize;
        this.myStacks = new Stacks(initialState);
    }

    /**
     * Move from the top of one stack to the top of another.
     * The returned plan should be a series of steps
     * A to C
     * or B to A etc
     *
     * @return
     */
    void generatePlan() {

        for (int i = 0; i < planSize; i++) {

            StackNames from = getNonEmptyFromStack();

            Step step = new Step(from, getAnyToStackOtherThan(from));

            steps.add(step);

            //now make the change to our set of scratchPadStacks, so only legal moves will be generated in each plan
        }

    }

    /**
     * Get me THE NAME OF any stack that I could take a block from
     *
     * @return
     */
    StackNames getNonEmptyFromStack() {

        List<StackNames> possibleStacks = new ArrayList<>();

        for (Map.Entry<StackNames, List<String>> entry : myStacks.getStacks().entrySet()) {

            StackNames stackName = entry.getKey();
            List<String> stack = entry.getValue();

            if (!stack.isEmpty()) possibleStacks.add(stackName);
        }

        return getRandomElementFrom(possibleStacks);
    }

    /**
     * Get me any of the other stacks, but not from
     *
     * @param from
     * @return
     */
    StackNames getAnyToStackOtherThan(StackNames from) {

        List<StackNames> possibleStacks =
                myStacks.getStacks().keySet().
                        stream().filter(element -> !element.equals(from))
                        .collect(Collectors.toList());

        return getRandomElementFrom(possibleStacks);
    }

    StackNames getRandomElementFrom(List<StackNames> selection) {
        if (selection == null || selection.size() == 0)
            throw new RuntimeException("selection is null, I can't chose a random one from a null list");

        StackNames stackName = selection.get(rand.nextInt(selection.size()));

        return stackName;
    }

}
