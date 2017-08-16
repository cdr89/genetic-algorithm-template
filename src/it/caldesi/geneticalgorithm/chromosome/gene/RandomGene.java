package it.caldesi.geneticalgorithm.chromosome.gene;

import java.util.List;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;

public abstract class RandomGene<T> {

	public void initRandomly(Chromosome<T> chromosome) {
		List<T> genes = chromosome.genes;
		for (int i = 0; i < genes.size(); i++) {
			genes.set(i, getRandomGene());
		}
	}

	public abstract T getRandomGene();

}
