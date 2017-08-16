package it.caldesi.geneticalgorithm.mutation;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;

public abstract class Mutation<T> {

	public abstract Chromosome<T> mutate(Chromosome<T> chromosome);
}
