package ru.filippov.ml.genetic.geneGenerator;

import java.util.Random;

public abstract class CharGeneGenerator implements GeneGenerator<Character> {
    private final Random random;
    final private String data;

    public CharGeneGenerator(String data, Random random){
        this.data = data;
        this.random = random;
    }

    public Character getRandomGene(){
        return data.charAt(random.nextInt(this.data.length()));
    }
}
