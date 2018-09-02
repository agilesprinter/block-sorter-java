package com.jakm.workflow;

import com.jakm.entities.Block;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {

    private final List<Block> blocks;
    private final List<Block> target;

    public GeneticAlgorithm(List<Block> blocks, List<Block> target) {

        this.blocks = blocks;
        this.target = target;
    }

    public List<String> runAlgorithm() {

        return new ArrayList<String>();

    }
}
