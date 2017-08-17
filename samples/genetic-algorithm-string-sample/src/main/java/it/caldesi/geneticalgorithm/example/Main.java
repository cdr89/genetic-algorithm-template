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
		String target = "Hello world";
		RandomGene<Character> randomGene = new CharRandomGene();
		CharsToGivenStringFitness fitness = new CharsToGivenStringFitness(target);

		GeneticAlgorithm.Builder<Character> builder = new GeneticAlgorithm.Builder<Character>();
		GeneticAlgorithm<Character> geneticAlgorithm = builder //
				.withChromosomeSize(target.length()) // How many genes must have each chromosome
				.withCrossover(new UniformCrossover<>()) // The type of crossover
				.withEliteIndividuals(eliteIndividuals) // Elite individuals to "save" for each generation
				.withMutation(new UniformMutation<>(randomGene, mutationRate)) // Mutation algorithm
				.withPopulationSize(populationSize) // Size of the population for each generation
				.withRandomGene(randomGene) // The RandomGene implementation
				.withSelection(new TournamentSelection<>(tournmentSize)) // Selection algorithm
				.withFitness(fitness) // Fitness function implementation
				.withOutputPrinter(new StandardOutputPrinter<>(fitness)) // Use a standar printer (optional)
				.build(); // Build the instance!

		geneticAlgorithm.run(); // Run the algorithm!
	}

}
