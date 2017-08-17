package it.caldesi.geneticalgorithm.example;

import it.caldesi.geneticalgorithm.GeneticAlgorithm;
import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;
import it.caldesi.geneticalgorithm.crossover.UniformCrossover;
import it.caldesi.geneticalgorithm.mutation.UniformMutation;
import it.caldesi.geneticalgorithm.output.StandardOutputPrinter;
import it.caldesi.geneticalgorithm.selection.TournamentSelection;

public class Main {

	public static void main(String[] args) {
		String target = "Hello world";
		RandomGene<Character> randomGene = new CharRandomGene();
		CharsToGivenStringFitness fitness = new CharsToGivenStringFitness(target);

		GeneticAlgorithm.Builder<Character> builder = new GeneticAlgorithm.Builder<Character>();
		GeneticAlgorithm<Character> geneticAlgorithm = builder //
				.withChromosomeSize(target.length()) // How many genes must have each chromosome
				.withCrossover(new UniformCrossover<>()) // The type of crossover
				.withEliteIndividuals(1) // Elite individuals to "save" for each generation
				.withMutation(new UniformMutation<>(randomGene, 0.5)) // Mutation algorithm
				.withPopulationSize(10) // Size of the population for each generation
				.withRandomGene(randomGene) // The RandomGene implementation
				.withSelection(new TournamentSelection<>(4)) // Selection algorithm
				.withFitness(fitness) // Fitness function implementation
				.withOutputPrinter(new StandardOutputPrinter<>(fitness)) // Use a standar printer (optional)
				.build(); // build the instance!

		geneticAlgorithm.run();
	}

}
