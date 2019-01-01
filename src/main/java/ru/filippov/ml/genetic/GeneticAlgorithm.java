package ru.filippov.ml.genetic;

import ru.filippov.ml.genetic.fitness.FitnessCalculator;
import ru.filippov.ml.genetic.geneGenerator.GeneGenerator;
import ru.filippov.ml.utils.ListsJoiner;

import java.util.*;

public class GeneticAlgorithm<T> {
    private List<DNA<T>> population;
    private int curentGeneration;
    private float mutationRate;
    private Random random;
    private float fitness;
    private DNA<T> bestIndividual;
    private int elitism;


    public GeneticAlgorithm(int populationSize, int elitism, int dnaSize, Random random, GeneGenerator geneGenerator, FitnessCalculator fitnessCalculator, float mutationRate) {
        this.population = new ArrayList<DNA<T>>(populationSize);
        this.curentGeneration = 1;
        this.mutationRate = mutationRate;
        this.random = random;
        this.elitism = elitism;
        for (int i = 0; i < populationSize; i++) {
            population.add(new DNA<T>(dnaSize, random, geneGenerator, fitnessCalculator, true));

        }
    }

    public void createNewGeneration(){
        if(this.population.size() <= 0) return;
        this.calculateFitness();
        Collections.sort(this.population, (o1, o2) -> -Float.compare(o1.fitness, o2.fitness));
        List<DNA<T>>  newPopulation = new ArrayList<>(this.population.size());

        for (int i = 0; i < this.population.size(); i++) {
            if(i < this.elitism){
                newPopulation.add(this.population.get(i));
            }
            else {

                DNA<T> parent1 = chooseParent();
                DNA<T> parent2 = chooseParent();

                DNA<T> child = parent1.crossover(parent2);
                child.mutate(this.mutationRate);
                newPopulation.add(child);
            }

        }

        this.population = newPopulation;
        this.curentGeneration++;
    }

    private void calculateFitness() {
        this.fitness = 0f;
        DNA<T>  best = this.population.get(0);

        for (int i = 0; i < this.population.size(); i++) {
            this.fitness += this.population.get(i).calucalteFitness(i);
        }
        if(this.population.get(0).getFitness() > best.getFitness()) best = this.population.get(0); //TRY THIS!
        this.bestIndividual = best;
    }

    private DNA<T> chooseParent(){
        double randomNumber = random.nextFloat() * this.fitness;
        for (int i = 0; i < this.population.size(); i++) {
            if(randomNumber < this.population.get(i).getFitness()) return this.population.get(i);
            randomNumber -= this.population.get(i).getFitness();
        }
        return this.population.get(this.random.nextInt(this.population.size()));
    }

    public List<DNA<T>> getPopulation() {
        return population;
    }

    public DNA<T> getBestIndividual() {
        return bestIndividual;
    }

    public int getCurentGeneration() {
        return curentGeneration;
    }
}
