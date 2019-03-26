package com.jakm.workflow;

import java.util.List;

public interface GeneticAlgorithmIF {

    public void setUpProblem(List currentState, List target);

    public List<String> runAlgorithm();
}
