package com.jakm.workflow;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GeneticAlgorithm implements GeneticAlgorithmIF {

    private List blocks;
    private List target;


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
