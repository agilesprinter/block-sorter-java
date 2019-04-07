package com.jakm.config;

import com.jakm.entities.Stacks;
import com.jakm.workflow.GeneticAlgorithm;
import com.jakm.workflow.GeneticAlgorithmIF;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "geneticAlgorithm")
    public GeneticAlgorithmIF getGeneticAlgorithm() {

        return new GeneticAlgorithm(getStacks());
    }

    @Bean(name = "stacks")
    public Stacks getStacks() {
        return new Stacks();
    }

}
