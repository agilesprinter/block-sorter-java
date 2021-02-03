package com.jakm.workflow;

import com.jakm.entities.FitnessFunciton;
import com.jakm.entities.Generation;
import com.jakm.entities.Stacks;
import com.jakm.interfaces.StackNames;
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
    private Generation generation;

    public GeneticAlgorithm(Stacks stacks, FitnessFunciton fitnessFunciton, Generation generation) {
        this.stacks = stacks;
        this.fitnessFunciton = fitnessFunciton;
        this.generation = generation;
    }

    public void setUpProblem(List currentState, List target) {
        this.blocks = currentState;
        this.target = target;

        //now set the origin stack to be that of the currentState. The current state is the randomly ordered block set
        //that we want to be sorted with the GA

        stacks.getStacks().put(StackNames.ORIGINSTACK, currentState);

    }


    public List<String> runAlgorithm() {

        return null;

    }


}
