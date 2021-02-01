package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Stacks implements StacksIF {

    private final List<String> originStack;
    private final List<String> firstStack;
    private final List<String> secondStack;
    private final Map<StackNames, List<String>> stacks;

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

}
