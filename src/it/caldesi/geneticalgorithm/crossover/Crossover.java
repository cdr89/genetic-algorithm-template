package it.caldesi.geneticalgorithm.crossover;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;

public abstract class Crossover<T> {

	public abstract Chromosome<T> crossover(Chromosome<T> c1, Chromosome<T> c2);

}
