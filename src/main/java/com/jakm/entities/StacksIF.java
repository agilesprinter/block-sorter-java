package com.jakm.entities;

import java.util.List;
import java.util.Map;

public interface StacksIF {

    String ORIGIN_STACK = "originStack";
    String FIRST_STACK = "firstStack";
    String SECOND_STACK = "secondStack";

    String[] defaultStackNameList = new String[]{ORIGIN_STACK, FIRST_STACK, SECOND_STACK};

    static void moveBlock(Step step, Map<String, List> stacks) {
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

    Map<String, List> getStackStore();

    void seedStack(String stackName, List<String> orderedBlockList);

}
