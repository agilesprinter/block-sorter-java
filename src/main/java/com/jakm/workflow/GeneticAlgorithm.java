package com.jakm.workflow;

import com.jakm.entities.Stacks;
import com.jakm.entities.StacksIF;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeneticAlgorithm implements GeneticAlgorithmIF {

    private List blocks;
    private List target;
    private Stacks stacks;

    public GeneticAlgorithm(Stacks stacks) {
        this.stacks = stacks;
    }

    public void setUpProblem(List currentState, List target) {
        this.blocks = currentState;
        this.target = target;

        //now set the origin stack to be that of the currentState

        stacks.getStackStore().put(StacksIF.ORIGIN_STACK, currentState);

    }


    public List<String> runAlgorithm() {

        stacks.moveBlock("originStack", "firstStack");
        stacks.moveBlock("originStack", "firstStack");
        stacks.moveBlock("originStack", "secondStack");

        System.out.println("Origin Stack is length: " + stacks.getStackStore().get(StacksIF.ORIGIN_STACK).size());
        System.out.println("First Stack is length: " + stacks.getStackStore().get(StacksIF.FIRST_STACK).size());
        System.out.println("Second Stack is length: " + stacks.getStackStore().get(StacksIF.SECOND_STACK).size());

        return null;

    }


}
