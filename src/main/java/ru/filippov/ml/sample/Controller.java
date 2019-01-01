package ru.filippov.ml.sample;

import ru.filippov.ml.genetic.DNA;
import ru.filippov.ml.genetic.GeneticAlgorithm;
import ru.filippov.ml.genetic.fitness.FitnessCalculator;
import ru.filippov.ml.genetic.geneGenerator.CharGeneGenerator;
import ru.filippov.ml.utils.RandomGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Controller {
    private int populationSize = 200;
    private float mutationRate = 0.02f;
    private final int magicNumber = 5;
    private int elitism = 5;
    String targetString = "To be or not to be, that is a question!";
    String allowedSymbols = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,./:{}?;'[]! ";
    Random random;
    int averageGeneration;
    int tries = 30;
    private GeneticAlgorithm<Character> ga;
    private boolean stopFlag = false;

    public void start() {


        this.random = new RandomGenerator();
        long rgenseed = System.currentTimeMillis();
        this.averageGeneration = 0;


        //The best seeds
        //1546144780541  -  9046
        //1546149716657  -  9662
        //1546151112325  -  9150
        //1546171876651
        //1546165138635
        //1546175631409  -  296
        rgenseed = System.currentTimeMillis();
        random.setSeed(rgenseed);

        ga = new GeneticAlgorithm<>(populationSize, this.elitism, targetString.length(), this.random, new CharGeneGenerator(targetString, random) {
            //Realisation of GeneGenerator
            @Override
            public Character getRandomGene() {
                return allowedSymbols.charAt(random.nextInt(allowedSymbols.length()));
            }
        }, index -> { //Realisation of fitness function
            float score = 0.0f;
            DNA<Character> dna = ga.getPopulation().get(index);
            for (int i = 0; i < dna.getGenes().size(); i++) {
                if (dna.getGenes().get(i) == targetString.charAt(i)) {
                    score++;
                }
            }
            score /= targetString.length();
            score = (float) ((Math.pow(this.magicNumber, score) - 1) / (this.magicNumber - 1)); // magic function
            return score;

        }, mutationRate);

        ga.createNewGeneration();
        while (ga.getBestIndividual().getFitness() <= 0.99 || stopFlag) {
            ga.createNewGeneration();
            System.out.print(ga.getCurentGeneration() + "   BestFitness = " + ga.getBestIndividual().getFitness() + " Best string:  ");
            for (char ch : ga.getBestIndividual().getGenes()) {
                System.out.print(ch);
            }
            System.out.println();
            //System.out.println("Geneartion:  " + ga.getCurentGeneration() + "  Best fitness  " + ga.getBestIndividual().getFitness() + "  String:  " + ga.getBestIndividual().getGenes());
            /* if(ga.getCurentGeneration() >= 300) {j--; break;} // Break too many generations
             *//*System.out.print(ga.getCurentGeneration() + "   BestFitness = " + ga.getBestIndividual().getFitness() + " Best string:  ");
                for (char ch : ga.getBestIndividual().getGenes()) {
                    System.out.print(ch);
                }
                System.out.println();*//*
             *//*
                for (DNA<Character> dna : ga.getPopulation()) {
                    for (char ch : dna.getGenes()){
                        System.out.print(ch);
                    }
                    System.out.print("  - " + dna.getFitness());
                    System.out.println();
                }*//*
            }
            if(ga.getCurentGeneration() != 300) {
                System.out.println("Seed of RandomGenerator = " + rgenseed + "  Generations -  "+ga.getCurentGeneration());
                this.averageGeneration+=ga.getCurentGeneration();
            }*/
        }
    }



}
