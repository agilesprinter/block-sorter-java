package com.jakm.interfaces;

import com.jakm.entities.Block;
import com.jakm.workflow.GeneticAlgorithm;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class CommandLineStarter {


    public static void main(String args[]) {

        SpringApplication.run(CommandLineStarter.class, args);

        //this is where we'll start the project

        //new CommandLineStarter().begin(Arrays.asList(currentState), Arrays.asList(targetState));

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
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
