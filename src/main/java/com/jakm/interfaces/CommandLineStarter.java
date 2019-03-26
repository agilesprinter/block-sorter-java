package com.jakm.interfaces;

import com.jakm.entities.Block;
import com.jakm.workflow.GeneticAlgorithm;
import com.jakm.workflow.GeneticAlgorithmIF;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;


public class CommandLineStarter {



    public static void main(String args[]) {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //this is where we'll start the project

        if (args == null || args.length == 0) {
            throw new RuntimeException("Please pass a single argument showing initial and target state");
        }

        if (args.length % 2 != 0) {
            throw new RuntimeException("Please pass a single argument showing initial and target state");
        }
        ;

        Map<String, List> formattedLists = getListsFromArray(args);


        GeneticAlgorithmIF geneticAlgorithm = applicationContext.getBean("geneticAlgorithm", GeneticAlgorithm.class);

        geneticAlgorithm.setUpProblem(formattedLists.get("currentState"), formattedLists.get("targetState"));

        geneticAlgorithm.runAlgorithm();


    }


    static Map getListsFromArray(String[] args) {

        List<String> currentState = new ArrayList<String>();
        List<String> targetState = new ArrayList<String>();

        int counter = 0;

        for (String block : args) {
            counter++;
            if (counter <= block.length() / 2) {
                currentState.add(block);
            }
            targetState.add(block);
        }

        Map<String, List> lists = new HashMap<>();

        lists.put("currentState", currentState);
        lists.put("targetState", targetState);

        return lists;


    }



    List<Block> blockSetCreator(List<String> input) {

        if (input == null || input.size() == 0) return null;

        List<Block> blocks = new ArrayList<>();

        for (Iterator<String> it = input.iterator(); it.hasNext(); ) {
            Block b = new Block();
            b.setName(it.next());
            blocks.add(b);
        }

        return blocks;

    }

}
