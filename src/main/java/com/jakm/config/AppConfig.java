package com.jakm.config;

import com.jakm.entities.FitnessFunciton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*@Bean(name = "geneticAlgorithm")
    public GeneticAlgorithmIF getGeneticAlgorithm() {

        return new GeneticAlgorithm(getStacks(), getFitnessFunction(), getPlanManager());
    }*/

   /* @Bean(name = "stacks")
    public Stacks getStacks() {
        return new Stacks();
    }*/

    @Bean(name = "fitnessFunction")
    public FitnessFunciton getFitnessFunction() {
        return new FitnessFunciton();
    }

    /*@Bean(name = "generation")
    public Generation getPlanManager() {
        return new Generation();
    }*/


}
