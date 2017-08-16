package it.caldesi.geneticalgorithm.selection;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;
import it.caldesi.geneticalgorithm.population.Population;

public class TournamentSelection<T> extends Selection<T> {

	private int size;

	public TournamentSelection(int size) {
		this.size = size;
	}

	@Override
	public Population<T> selectPopulation(Population<T> population) {
		Population<T> tournmentPopulation = new Population<>(size);
		for (int i = 0; i < size; i++) {
			Chromosome<T>[] chromosomes = population.chromosomes;
			tournmentPopulation.chromosomes[i] = chromosomes[(int) Math.random() * chromosomes.length];
		}

		return tournmentPopulation;
	}

}
