package it.caldesi.geneticalgorithm;

import java.util.Arrays;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;
import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;
import it.caldesi.geneticalgorithm.crossover.Crossover;
import it.caldesi.geneticalgorithm.fitness.Fitness;
import it.caldesi.geneticalgorithm.mutation.Mutation;
import it.caldesi.geneticalgorithm.population.Population;
import it.caldesi.geneticalgorithm.selection.Selection;

public class GeneticAlgorithm<T> {

	private Crossover<T> crossover;
	private Mutation<T> mutation;
	private Selection<T> selection;
	private RandomGene<T> randomGene;

	private int chromosomeSize;
	private int populationSize;
	private int eliteIndividuals;
	private Fitness<T> fitness;

	private GeneticAlgorithm() {
	}

	public void run() {
		int generation = 0;

		Population<T> population = new Population<T>(populationSize);
		initializePopulation(population, randomGene);
		sortChromosomesByFitness(population);

		Chromosome<T> mostFitChromosome = population.chromosomes[0];

		System.out.println(
				"Generation #" + generation + " most fit chromosome: " + fitness.calcFitness(mostFitChromosome));
		printPopulation(population);
		System.out.println("-------------------------------------------------------");

		while (fitness.calcFitness(mostFitChromosome) < fitness.getTargetFitness()) {
			generation++;
			// evolution of the population
			population = evolve(population);
			sortChromosomesByFitness(population);
			// take the most fit individual
			mostFitChromosome = population.chromosomes[0];
			System.out.println(
					"Generation #" + generation + " most fit chromosome: " + fitness.calcFitness(mostFitChromosome));
			printPopulation(population);
			System.out.println("-------------------------------------------------------");
		}
	}

	private void initializePopulation(Population<T> population, RandomGene<T> randomGene2) {
		for (int i = 0; i < population.chromosomes.length; i++) {
			population.chromosomes[i] = new Chromosome<T>(chromosomeSize);
			randomGene.initRandomly(population.chromosomes[i]);
		}

	}

	public Population<T> sortChromosomesByFitness(Population<T> population) {
		Arrays.sort(population.chromosomes, (c1, c2) -> {
			if (fitness.calcFitness(c1) > fitness.calcFitness(c2))
				return -1;
			else if (fitness.calcFitness(c1) < fitness.calcFitness(c2))
				return 1;
			return 0;
		});

		return population;
	}

	public Population<T> evolve(Population<T> originalPopulation) {
		return mutate(crossover(originalPopulation));
	}

	private Population<T> crossover(Population<T> population) {
		Population<T> crossoverPopulation = new Population<>(population.chromosomes.length);
		for (int i = 0; i < eliteIndividuals; i++) {
			crossoverPopulation.chromosomes[i] = population.chromosomes[i];
		}
		for (int i = eliteIndividuals; i < population.chromosomes.length; i++) {
			Chromosome<T> c1 = sortChromosomesByFitness(selection.selectPopulation(population)).chromosomes[0];
			Chromosome<T> c2 = sortChromosomesByFitness(selection.selectPopulation(population)).chromosomes[0];
			crossoverPopulation.chromosomes[i] = crossover.crossover(c1, c2);
		}

		return crossoverPopulation;
	}

	private Population<T> mutate(Population<T> population) {
		Population<T> mutatePopulation = new Population<>(population.chromosomes.length);
		for (int i = 0; i < eliteIndividuals; i++) {
			mutatePopulation.chromosomes[i] = population.chromosomes[i];
		}
		for (int i = eliteIndividuals; i < population.chromosomes.length; i++) {
			mutatePopulation.chromosomes[i] = mutation.mutate(population.chromosomes[i]);
		}

		return mutatePopulation;
	}

	public void printPopulation(Population<T> population) {
		for (int i = 0; i < population.chromosomes.length; i++) {
			Chromosome<T> chromosome = population.chromosomes[i];
			System.out.println("Chromosome #" + i + ": " + chromosome + " fitness: " + fitness.calcFitness(chromosome));
		}
	}

	public static class Builder<T> {

		private GeneticAlgorithm<T> instance;

		public Builder() {
			instance = new GeneticAlgorithm<>();
		}

		public GeneticAlgorithm<T> build() {
			if (instance.crossover == null)
				throw new IllegalStateException("Please specify a crossover");
			if (instance.mutation == null)
				throw new IllegalStateException("Please specify a mutation");
			if (instance.selection == null)
				throw new IllegalStateException("Please specify a selection");
			if (instance.randomGene == null)
				throw new IllegalStateException("Please specify a randomGene");
			if (instance.populationSize == 0)
				throw new IllegalStateException("Please specify a populationSize");
			if (instance.eliteIndividuals == 0)
				throw new IllegalStateException("Please specify a eliteIndividuals");
			if (instance.chromosomeSize == 0)
				throw new IllegalStateException("Please specify a chromosomeSize");
			if (instance.fitness == null)
				throw new IllegalStateException("Please specify a fitness");

			return instance;
		}

		public Builder<T> withCrossover(Crossover<T> crossover) {
			instance.crossover = crossover;
			return this;
		}

		public Builder<T> withMutation(Mutation<T> mutation) {
			instance.mutation = mutation;
			return this;
		}

		public Builder<T> withSelection(Selection<T> selection) {
			instance.selection = selection;
			return this;
		}

		public Builder<T> withRandomGene(RandomGene<T> randomGene) {
			instance.randomGene = randomGene;
			return this;
		}

		public Builder<T> withPopulationSize(int populationSize) {
			instance.populationSize = populationSize;
			return this;
		}

		public Builder<T> withEliteIndividuals(int eliteIndividuals) {
			instance.eliteIndividuals = eliteIndividuals;
			return this;
		}

		public Builder<T> withChromosomeSize(int chromosomeSize) {
			instance.chromosomeSize = chromosomeSize;
			return this;
		}

		public Builder<T> withFitness(Fitness<T> fitness) {
			instance.fitness = fitness;
			return this;
		}

	}

}
