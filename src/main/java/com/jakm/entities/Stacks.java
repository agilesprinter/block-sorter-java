package com.jakm.entities;

import java.util.*;


public class Stacks implements StacksIF {

    private final Map<String, List> stackStore;

    public Stacks(Set<String> stackNameList) {

        stackStore = new HashMap<>();

        for (String stack : stackNameList) {
            //Each stack is just an ordered list of Steps
            stackStore.put(stack, new <Step>ArrayList());
        }
    }

    /*
    If you do not provide stack names, you will get 3
     */
    public Stacks() {

        stackStore = new HashMap<>();

        for (String stack : defaultStackNameList) {
            stackStore.put(stack, new ArrayList());
        }
    }

    @Override
    public Map<String, List> getStackStore() {
        return stackStore;
    }

    @Override
    public void moveBlock(Step step) {
        if (step.getFrom() == null) {
            throw new RuntimeException("The origin stack is empty, cannot move from this stack");
        }
        if (step.getTo() == null) {
            throw new RuntimeException("The target stack is null, cannot move to this stack");
        }
        if (!stackStore.containsKey(step.getFrom())) {
            throw new RuntimeException("The origin stack does not exist, cannot move to this stack");
        }
        if (!stackStore.containsKey(step.getTo())) {
            throw new RuntimeException("The target stack does not exist, cannot move to this stack");
        }
        if (stackStore.get(step.getFrom()) == null || stackStore.get(step.getFrom()).size() == 0) {
            throw new RuntimeException("The from stack is null or empty- we cannot move anything from it");
        }

        List<Block> fromStack = getStackStore().get(step.getFrom());
        List<Block> toStack = getStackStore().get(step.getTo());

        Block topOfStackBlock = fromStack.get(fromStack.size() - 1);
        toStack.add(topOfStackBlock);
        fromStack.remove(fromStack.size() - 1);
    }
}
