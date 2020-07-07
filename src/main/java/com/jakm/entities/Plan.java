package com.jakm.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;


/**
 * A plan is just a series of POSSIBLE steps. Steps such as moving from an empty stack to a full one make no sense.
 * Create a plan with some stacks, then ask it to generate. A random plan of possible moves will be assembled.
 */
@Setter
@Getter
public class Plan {

    int planSize;
    int planScore;
    Map<String, List> realStacks;
    Map<String, List> assessmentStacks = new HashMap<String, List>();
    List<String> stackNames;
    List<Step> steps = new ArrayList<Step>();
    private Random rand = new Random();

    public Plan(int planSize, Map<String, List> stacks) {
        this.planSize = planSize;
        this.realStacks = stacks;

        //initialize this plan's play area
        this.assessmentStacks.putAll(stacks);

        //set up a handy list for knowing the stack names
        this.stackNames = new ArrayList(this.assessmentStacks.keySet());
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

            String from = getNonEmptyFromStack();

            steps.add(i, new Step(from, getAnyToBlockOtherThan(from)));
        }

    }

    /**
     * Get me any stack that I could take a block from
     *
     * @return
     */
    String getNonEmptyFromStack() {

        List<String> possibleStacks = new ArrayList<>();

        for (String stackName : stackNames) {
            if (assessmentStacks.get(stackName).size() > 0)
                possibleStacks.add(stackName);
        }

        return getRandomElementFrom(possibleStacks);
    }

    /**
     * Get me any of the other stacks, but not from
     *
     * @param from
     * @return
     */
    String getAnyToBlockOtherThan(String from) {

        List<String> possibleStacks =
                stackNames.stream().filter(element -> !element.equals(from))
                        .collect(Collectors.toList());

        return getRandomElementFrom(possibleStacks);
    }

    public String getRandomElementFrom(List<String> selection) {
        if (selection == null || selection.size() == 0)
            throw new RuntimeException("selection is null, I can't chose a random one from a null list");

        return selection.get(rand.nextInt(selection.size()));
    }

}
