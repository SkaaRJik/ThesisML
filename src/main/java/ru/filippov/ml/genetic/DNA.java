package ru.filippov.ml.genetic;

import ru.filippov.ml.genetic.fitness.FitnessCalculator;
import ru.filippov.ml.genetic.geneGenerator.GeneGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DNA<T> {
    List<T> genes;
    float fitness;
    Random random;
    GeneGenerator geneGenerator;
    FitnessCalculator fitnessCalculator;

    public DNA(int dnaSize, Random random, GeneGenerator geneGenerator, FitnessCalculator fitnessCalculator, boolean shouldInitGenes) {
        this.genes = new ArrayList<T>(dnaSize);
        this.random = random;
        this.geneGenerator = geneGenerator;
        this.fitnessCalculator = fitnessCalculator;
        if (shouldInitGenes) {
            for (int i = 0; i < dnaSize; i++) {
                this.genes.add((T) this.geneGenerator.getRandomGene());
            }
        }

    }


    public float calucalteFitness(int index) {
        this.fitness = fitnessCalculator.calculate(index);
        return this.fitness;
    }

    public DNA crossover(DNA otherParent){
        DNA child = new DNA(this.genes.size(), this.random, this.geneGenerator, this.fitnessCalculator, false);

        for (int i = 0; i < this.genes.size(); i++) {
            T gene = random.nextFloat() < 0.5 ?  this.genes.get(i) : (T) otherParent.genes.get(i);
            child.genes.add(gene);
        }

        return child;
    }

    public void mutate(float mutationRate){
        for (int i = 0; i < this.genes.size(); i++) {
            try {
                if(this.random.nextFloat() < mutationRate)
                    this.genes.set(i, (T) this.geneGenerator.getRandomGene());
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public float getFitness() {
        return fitness;
    }

    public List<T> getGenes() {
        return genes;
    }
}
