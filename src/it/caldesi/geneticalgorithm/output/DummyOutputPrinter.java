package it.caldesi.geneticalgorithm.output;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;
import it.caldesi.geneticalgorithm.fitness.Fitness;
import it.caldesi.geneticalgorithm.population.Population;

public class DummyOutputPrinter<T> extends OutputPrinter<T> {

	public DummyOutputPrinter(Fitness<T> fitness) {
		super(fitness);
	}

	@Override
	public void onInitialization(Population<T> population, Chromosome<T> mostFitChromosome) {
	}

	@Override
	public void forEachGeneration(int generation, Population<T> population, Chromosome<T> mostFitChromosome) {
	}

	@Override
	public void printPopulation(Population<T> population) {
	}

}
