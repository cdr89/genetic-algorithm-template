package it.caldesi.geneticalgorithm.output;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;
import it.caldesi.geneticalgorithm.fitness.Fitness;
import it.caldesi.geneticalgorithm.population.Population;

public abstract class OutputPrinter<T> {

	protected Fitness<T> fitness;
	protected long startTime;
	protected long executionTime = -1;

	public OutputPrinter(Fitness<T> fitness) {
		this.fitness = fitness;
	}

	public void onStart() {
		startTime = System.currentTimeMillis();
	}

	public void onFinish() {
		executionTime = System.currentTimeMillis() - startTime;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public abstract void onInitialization(Population<T> population, Chromosome<T> mostFitChromosome);

	public abstract void forEachGeneration(int generation, Population<T> population, Chromosome<T> mostFitChromosome);

	public abstract void printPopulation(Population<T> population);

}