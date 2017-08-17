package it.caldesi.geneticalgorithm.output;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;
import it.caldesi.geneticalgorithm.fitness.Fitness;
import it.caldesi.geneticalgorithm.population.Population;

public class StandardOutputPrinter<T> extends OutputPrinter<T> {

	public StandardOutputPrinter(Fitness<T> fitness) {
		super(fitness);
		if (fitness == null)
			throw new IllegalArgumentException("Fitness must not be null!");
	}

	@Override
	public void onInitialization(Population<T> population, Chromosome<T> mostFitChromosome) {
		System.out.println("Initialized first generation #0");
		System.out.println("Most fit chromosome: " + fitness.calcFitness(mostFitChromosome));
		System.out.println("Target fitness: " + fitness.getTargetFitness());
		printPopulation(population);
		System.out.println("---------------------------------------------------------------");
	}

	@Override
	public void forEachGeneration(int generation, Population<T> population, Chromosome<T> mostFitChromosome) {
		System.out.println("Generation #" + generation);
		System.out.println("Most fit chromosome: " + fitness.calcFitness(mostFitChromosome));
		System.out.println("Target fitness: " + fitness.getTargetFitness());
		printPopulation(population);
		System.out.println("---------------------------------------------------------------");

	}

	@Override
	public void printPopulation(Population<T> population) {
		for (int i = 0; i < population.chromosomes.length; i++) {
			Chromosome<T> chromosome = population.chromosomes[i];
			System.out.println("Chromosome #" + i + ": " + chromosome + " fitness: " + fitness.calcFitness(chromosome));
		}
	}

}
