package com.jakm.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Stacks implements StacksIF {

    Map<String, List> stackStore;

    public void setUpStacks(List<String> stackList) {

        stackStore = new HashMap<>();

        for (String stack : stackList) {
            stackStore.put(stack, new ArrayList());
        }

    }

    @Override
    public Map<String, List> getStackStore() {
        return stackStore;
    }

    public void moveBlock(List<Block> fromStack, List<Block> toStack) {
        if (fromStack == null) {
            throw new RuntimeException("The origin stack is empty, cannot move from this stack");
        }
        if (fromStack.isEmpty()) {
            throw new RuntimeException("The origin stack is empty, cannot move from this stack");
        }
        if (toStack == null) {
            throw new RuntimeException("The target stack is null, cannot move to this stack");
        }
        if (!stackStore.keySet().contains(fromStack)) {
            throw new RuntimeException("The origin stack does not exist, cannot move to this stack");
        }
        if (!stackStore.keySet().contains(toStack)) {
            throw new RuntimeException("The target stack does not exist, cannot move to this stack");
        }

        Block topOfStackBlock = fromStack.get(fromStack.size() - 1);
        toStack.add(topOfStackBlock);
        fromStack.remove(fromStack.size() - 1);
    }
}
