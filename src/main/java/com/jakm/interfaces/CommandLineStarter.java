package com.jakm.interfaces;

import com.jakm.entities.Block;
import com.jakm.util.Utils;
import com.jakm.workflow.GeneticAlgorithm;
import com.jakm.workflow.GeneticAlgorithmIF;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CommandLineStarter {



    public static void main(String args[]) {

        //Check that the input is what we want and then get the input formatted
        new Utils().validateEntryArray(args);
        Map<String, List> formattedLists = new Utils().getListsFromArray(args);

        //Spring setup
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //Actually get the code running
        GeneticAlgorithmIF geneticAlgorithm = applicationContext.getBean("geneticAlgorithm", GeneticAlgorithm.class);
        geneticAlgorithm.setUpProblem(formattedLists.get("currentState"), formattedLists.get("targetState"));
        geneticAlgorithm.runAlgorithm();


    }




    List<Block> blockSetCreator(List<String> input) {

        if (input == null || input.size() == 0) return null;

        List<Block> blocks = new ArrayList<>();

        for (String anInput : input) {
            Block b = new Block();
            b.setName(anInput);
            blocks.add(b);
        }

        return blocks;

    }

}
