package it.caldesi.geneticalgorithm.population;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;

public class Population<T> {

	public Chromosome<T>[] chromosomes;

	@SuppressWarnings("unchecked")
	public Population(int size) {
		chromosomes = new Chromosome[size];
	}

}
