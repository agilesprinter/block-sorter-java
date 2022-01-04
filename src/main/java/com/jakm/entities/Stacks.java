package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Stacks implements StacksIF {

    private static final String EMPTY_BLOCK = "[_]";
    private final List<String> originStack;
    private final List<String> firstStack;
    private final List<String> secondStack;
    private final Map<StackNames, List<String>> stacks;
    private Random rand = new Random();


    private final List<String> initialState;

    public Stacks(List initialState) {

        this.initialState = initialState;

        if (initialState == null) {
            throw new RuntimeException("One of the stacks you're trying to initiaize does not exist");
        }

        originStack = new ArrayList<>();
        firstStack = new ArrayList<>();
        secondStack = new ArrayList<>();
        stacks = new HashMap<StackNames, List<String>>();

        stacks.put(StackNames.ORIGINSTACK, originStack);
        stacks.put(StackNames.FIRSTSTACK, firstStack);
        stacks.put(StackNames.SECONDSTACK, secondStack);

        //set up the origin stack so it looks like the problem statement
        originStack.addAll(initialState);
    }

    public void applyStep(Step step) {

        List<String> fromStack = stacks.get(step.from);
        List<String> toStack = stacks.get(step.to);

        //plans will have steps that don't make sense and are random
        //when we see one, just move past it
        if (CollectionUtils.isEmpty(fromStack)) return;

        toStack.add(fromStack.get(this.getTopBlockIndex(step.from)));

        fromStack.remove(this.getTopBlockIndex(step.from));

    }

    public int getTopBlockIndex(StackNames stackNames) {
        List<String> stack = stacks.get(stackNames);

        if (stack.isEmpty()) return -1;

        return stack.size() - 1;
    }

    /**
     * Get me any of the other stacks, but not from
     *
     * @return
     */
    StackNames getAnyStack() {

        return getRandomElementFrom(stacks.keySet().stream().collect(Collectors.toList()));
    }

    /**
     * Get me THE NAME OF any stack that I could take a block from
     *
     * @return
     */
    StackNames getNonEmptyFromStack() {

        List<StackNames> possibleStacks = new ArrayList<>();

        for (Map.Entry<StackNames, List<String>> entry : stacks.entrySet()) {

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
                stacks.keySet().
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

    void printStacks() {
        int maxLengthOfWingStacks = Math.max(firstStack.size(), secondStack.size());
        int maxLengthOfAllStacks = Math.max(maxLengthOfWingStacks, originStack.size());

        StringBuffer line;
        for (int i = maxLengthOfAllStacks; i > -1; i--) {
            line = new StringBuffer();
            line.append(this.printBlock(i, StackNames.FIRSTSTACK));
            line.append(this.printBlock(i, StackNames.ORIGINSTACK));
            line.append(this.printBlock(i, StackNames.SECONDSTACK));
            System.out.println(line);
        }
    }

    String printBlock(int index, StackNames stackName) {
        List<String> stack = this.getStacks().get(stackName);

        if (CollectionUtils.isEmpty(stack)) return EMPTY_BLOCK;

        if (stack.size() > index) {
            return "[" + stack.get(index) + "]";
        }

        return EMPTY_BLOCK;
    }

}
