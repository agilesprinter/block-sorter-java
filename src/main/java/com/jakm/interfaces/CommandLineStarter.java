package com.jakm.interfaces;

import com.jakm.entities.Block;
import com.jakm.workflow.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineStarter {


    public static void main(String currentState[], String targetState[]) {

        //this is where we'll start the project

        new CommandLineStarter().begin(Arrays.asList(currentState), Arrays.asList(targetState));

    }

    void begin(List<String> current, List<String> target) {

        if (current == null || current.size() == 0)
            throw new RuntimeException("This program must be run with two arrays");

        if (target == null || target.size() == 0)
            throw new RuntimeException("This program must be run with two arrays");

        if (target.size() != current.size())
            throw new RuntimeException("This program must be run with equal sized lists");

        GeneticAlgorithm algorithm = new GeneticAlgorithm(blockSetCreator(current), blockSetCreator(target));

    }

    List<Block> blockSetCreator(List<String> input) {


        return new ArrayList<Block>();

    }

}
