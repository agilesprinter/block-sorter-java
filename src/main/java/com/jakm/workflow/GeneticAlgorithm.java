package com.jakm.workflow;

import com.jakm.entities.FitnessFunciton;
import com.jakm.entities.PlanManager;
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
    private FitnessFunciton fitnessFunciton;
    private PlanManager planManager;

    public GeneticAlgorithm(Stacks stacks, FitnessFunciton fitnessFunciton, PlanManager planManager) {
        this.stacks = stacks;
        this.fitnessFunciton = fitnessFunciton;
        this.planManager = planManager;
    }

    public void setUpProblem(List currentState, List target) {
        this.blocks = currentState;
        this.target = target;

        //now set the origin stack to be that of the currentState. The current state is the randomly ordered block set
        //that we want to be sorted with the GA

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
