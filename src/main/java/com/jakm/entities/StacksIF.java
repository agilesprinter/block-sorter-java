package com.jakm.entities;

import com.jakm.interfaces.StackNames;

import java.util.List;
import java.util.Map;

public interface StacksIF {

    static void moveBlock(Step step, Map<StackNames, List<String>> stacks) {
        if (step.getFrom() == null) {
            throw new RuntimeException("The origin stack is empty, cannot move from this stack");
        }
        if (step.getTo() == null) {
            throw new RuntimeException("The target stack is null, cannot move to this stack");
        }
        if (!stacks.containsKey(step.getFrom())) {
            throw new RuntimeException("The origin stack does not exist, cannot move to this stack");
        }
        if (!stacks.containsKey(step.getTo())) {
            throw new RuntimeException("The target stack does not exist, cannot move to this stack");
        }
        if (stacks.get(step.getFrom()) == null || stacks.get(step.getFrom()).size() == 0) {
            throw new RuntimeException("The from stack is null or empty- we cannot move anything from it");
        }

        List<String> fromStack = stacks.get(step.getFrom());
        List<String> toStack = stacks.get(step.getTo());

        String topOfStackBlock = fromStack.get(fromStack.size() - 1);
        toStack.add(topOfStackBlock);
        fromStack.remove(fromStack.size() - 1);
    }

    Map<StackNames, List<String>> getStacks();

}
