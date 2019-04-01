package com.jakm.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Stacks implements StacksIF {

    private Map<String, List> stackStore;

    public Stacks(List<String> stackNameList) {

        stackStore = new HashMap<>();

        for (String stack : stackNameList) {
            stackStore.put(stack, new ArrayList());
        }
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

        List<Block> fromStack = getStackStore().get(fromStackCalled);
        List<Block> toStack = getStackStore().get(toStackCalled);

        Block topOfStackBlock = fromStack.get(fromStack.size() - 1);
        toStack.add(topOfStackBlock);
        fromStack.remove(fromStack.size() - 1);
    }
}
