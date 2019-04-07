package com.jakm.entities;

import java.util.*;


public class Stacks implements StacksIF {

    private Map<String, List> stackStore;

    public Stacks(Set<String> stackNameList) {

        stackStore = new HashMap<>();

        for (String stack : stackNameList) {
            stackStore.put(stack, new ArrayList());
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

    /**
     * A simple utility method. Give me a list of strings, I'll make it into a list of Blocks
     *
     * @param input
     * @return
     */
    public List<Block> blockSetCreator(List<String> input) {

        if (input == null || input.size() == 0) return null;

        List<Block> blocks = new ArrayList<>();

        for (String anInput : input) {
            Block b = new Block();
            b.setName(anInput);
            blocks.add(b);
        }

        return blocks;

    }

    @Override
    public Map<String, List> getStackStore() {
        return stackStore;
    }

    @Override
    public void moveBlock(String fromStackCalled, String toStackCalled) {
        if (fromStackCalled == null) {
            throw new RuntimeException("The origin stack is empty, cannot move from this stack");
        }
        if (toStackCalled == null) {
            throw new RuntimeException("The target stack is null, cannot move to this stack");
        }
        if (!stackStore.keySet().contains(fromStackCalled)) {
            throw new RuntimeException("The origin stack does not exist, cannot move to this stack");
        }
        if (!stackStore.keySet().contains(toStackCalled)) {
            throw new RuntimeException("The target stack does not exist, cannot move to this stack");
        }
        if (stackStore.get(fromStackCalled) == null || stackStore.get(fromStackCalled).size() == 0) {
            throw new RuntimeException("The from stack is null or empty- we cannot move anything from it");
        }

        List<Block> fromStack = getStackStore().get(fromStackCalled);
        List<Block> toStack = getStackStore().get(toStackCalled);

        Block topOfStackBlock = fromStack.get(fromStack.size() - 1);
        toStack.add(topOfStackBlock);
        fromStack.remove(fromStack.size() - 1);
    }
}
