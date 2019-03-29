package com.jakm.workflow;

import java.util.List;

public interface GeneticAlgorithmIF {

    void setUpProblem(List currentState, List target);

    List<String> runAlgorithm();

}
