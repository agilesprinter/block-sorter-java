package com.jakm.workflow;

import com.jakm.entities.Block;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm implements GeneticAlgorithmIF {

    private List<Block> blocks;
    private List<Block> target;


    public List<Block> getTarget() {
        return target;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setUpProblem(List currentState, List target) {
        this.blocks = currentState;
        this.target = target;
    }


    public List<String> runAlgorithm() {

        List<String> stub = new ArrayList<String>();
        stub.add("I AM JUST A STUB");

        System.out.println(stub);

        return stub;

    }

}
