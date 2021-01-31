package com.jakm.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PlanManager {

    List<Plan> plans = new ArrayList<Plan>();

    public void createPlans(int howManyPlans, int lengthOfEachPlan, Stacks stacks) {

        //IntStream.range(0, howManyPlans).forEach(i -> plans.add(new Plan(lengthOfEachPlan, stacks)));

    }

}
