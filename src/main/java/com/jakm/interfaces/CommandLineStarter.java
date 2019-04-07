package com.jakm.interfaces;

import com.jakm.config.AppConfig;
import com.jakm.util.Utils;
import com.jakm.workflow.GeneticAlgorithm;
import com.jakm.workflow.GeneticAlgorithmIF;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;


class CommandLineStarter {

    public static void main(String args[]) {

        //Check that the input is what we want and then get the input formatted
        new Utils().validateEntryArray(args);
        Map<String, List> formattedLists = new Utils().getListsFromArray(args);

        //Spring setup
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //Actually get the code running
        GeneticAlgorithmIF geneticAlgorithm = applicationContext.getBean("geneticAlgorithm", GeneticAlgorithm.class);
        geneticAlgorithm.setUpProblem(formattedLists.get("currentState"), formattedLists.get("targetState"));
        geneticAlgorithm.runAlgorithm();

    }

}
