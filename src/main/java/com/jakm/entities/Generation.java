package com.jakm.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class Generation {

    List<Plan> plans;
    List<String> targetState;
    List<String> initialState;
    int howManyPlans;
    int maxLengthOfEachPlan;

    public Generation(List<Plan> plans, List<String> targetState, List<String> initialState, int howManyPlans, int maxLengthOfEachPlan) {
        this.plans = plans;
        this.targetState = targetState;
        this.initialState = initialState;
        this.howManyPlans = howManyPlans;
        this.maxLengthOfEachPlan = maxLengthOfEachPlan;

        //this is the first generation, so we need to create an object for the Plans
        if (this.plans == null) this.plans = new ArrayList<Plan>();

        if (targetState == null || initialState == null || howManyPlans == 0 || maxLengthOfEachPlan == 0) {
            throw new RuntimeException("Your problem configuration is incorrect. Program cannot continue");
        }
    }

    public void createPlans() {

        //checking that the plans really are empty. If so, create them- this is the first generation
        if (plans.isEmpty()) {
            IntStream.range(0, howManyPlans).forEach(i -> plans.add(
                    new Plan(maxLengthOfEachPlan, initialState, targetState)));
        }
    }

    public void runGeneration() {

    }

}
