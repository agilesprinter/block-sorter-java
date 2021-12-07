package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

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
    List<String> initialState;
    List<String> targetState;
    Stacks myStacks;
    List<Step> steps = new ArrayList<Step>();
    private Random rand = new Random();
    private List<MutatorIF> mutators = new ArrayList();

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
        this.initialState = initialState;
        this.targetState = targetState;
        this.myStacks = new Stacks(initialState);

        this.mutators.add(new MutatorFlip());
    }

    /**
     * Move from the top of one stack to the top of another.
     * The returned plan should be a series of steps
     * A to C
     * or B to A etc
     *
     * @return
     */
    void generateAndRunNewPlan() {

        for (int i = 0; i < planSize; i++) {

            StackNames from = getNonEmptyFromStack();

            Step step = new Step(from, getAnyToStackOtherThan(from));

            //we must apply the step each time to ensure the next steps which are generated
            //will keep making sense.
            myStacks.applyStep(step);

            //keep a record of the steps that we are generating
            steps.add(step);

            //now make the change to our set of scratchPadStacks, so only legal
            // moves will be generated in each plan

            System.out.println(step + "\n");
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

    void combinePlans(Plan otherPlan) {

        int splitIndex = this.steps.size() / 2;

        //remove half of the steps in our plan starting from the end
        for (int i = this.steps.size() - 1; i >= splitIndex; i--) {
            this.steps.remove(i);
        }

        //replace those steps with half of those in the incoming plan
        for (int j = splitIndex; j < otherPlan.getSteps().size(); j++) {
            this.steps.add(otherPlan.getSteps().get(j));
        }

    }

    void mutate() {
        //I think here we should have a collection of mutation strategies
        //and this method (which should be on the plan class), should ask for mutations
        if (CollectionUtils.isEmpty(this.mutators))
            throw new UnsupportedOperationException("Every plan must have at least one Mutator");

        this.mutators.get(0).mutate(this.steps, 1);

    }

}
