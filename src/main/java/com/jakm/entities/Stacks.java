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
    public void seedStack(String stackName, List<String> orderedBlockList) {
        if (stackName == null || stackStore == null) {
            throw new RuntimeException("The stack you're trying to initiaize does not exist");
        }

        if (!stackStore.containsKey(stackName)) {
            throw new RuntimeException("The stack you're trying to initiaize does not exist");
        }

        //looks safe to initialize with this list of blocks, so go ahead
        stackStore.get(stackName).add(orderedBlockList);
    }

}
