package it.caldesi.geneticalgorithm.fitness;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;

public abstract class Fitness<T> {

	public abstract double calcFitness(Chromosome<T> chromosome);

	public abstract double getTargetFitness();

}
