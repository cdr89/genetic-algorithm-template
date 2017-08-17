package it.caldesi.geneticalgorithm.example;

import it.caldesi.geneticalgorithm.GeneticAlgorithm;
import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;
import it.caldesi.geneticalgorithm.crossover.UniformCrossover;
import it.caldesi.geneticalgorithm.mutation.UniformMutation;
import it.caldesi.geneticalgorithm.output.StandardOutputPrinter;
import it.caldesi.geneticalgorithm.selection.TournamentSelection;

public class Main {

	public static void main(String[] args) {
		// parameters
		double mutationRate = 0.5;
		int populationSize = 10;
		int tournmentSize = 4;
		int eliteIndividuals = 1;

		runAlgorithm(mutationRate, populationSize, tournmentSize, eliteIndividuals);
	}

	public static void runAlgorithm(double mutationRate, int populationSize, int tournmentSize, int eliteIndividuals) {
		System.out.println("Running...");
		RandomGene<Number> randomGene = new MetaRandomGene();
		MetaFitness fitness = new MetaFitness();

		GeneticAlgorithm.Builder<Number> builder = new GeneticAlgorithm.Builder<Number>();
		GeneticAlgorithm<Number> geneticAlgorithm = builder //
				.withChromosomeSize(4) // 4 are the parameters that represent the gene
				.withCrossover(new UniformCrossover<>()) // The type of crossover
				.withEliteIndividuals(eliteIndividuals) // Elite individuals to "save" for each generation
				.withMutation(new UniformMutation<>(randomGene, mutationRate)) // Mutation algorithm
				.withPopulationSize(populationSize) // Size of the population for each generation
				.withRandomGene(randomGene) // The RandomGene implementation
				.withSelection(new TournamentSelection<>(tournmentSize)) // Selection algorithm
				.withFitness(fitness) // Fitness function implementation
				.withOutputPrinter(new StandardOutputPrinter<>(fitness)) // Use a standard printer (optional)
				.build(); // Build the instance!

		geneticAlgorithm.run(); // Run the algorithm!

	}

}
