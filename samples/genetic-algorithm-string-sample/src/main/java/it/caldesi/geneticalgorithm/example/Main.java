package it.caldesi.geneticalgorithm.example;

import it.caldesi.geneticalgorithm.GeneticAlgorithm;
import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;
import it.caldesi.geneticalgorithm.crossover.UniformCrossover;
import it.caldesi.geneticalgorithm.mutation.UniformMutation;
import it.caldesi.geneticalgorithm.selection.TournamentSelection;

public class Main {

	public static void main(String[] args) {
		String target = "Hello world";
		RandomGene<Character> randomGene = new CharRandomGene();

		GeneticAlgorithm.Builder<Character> builder = new GeneticAlgorithm.Builder<Character>();
		GeneticAlgorithm<Character> geneticAlgorithm = builder //
				.withChromosomeSize(target.length()) //
				.withCrossover(new UniformCrossover<>()) //
				.withEliteIndividuals(1) //
				.withMutation(new UniformMutation<>(randomGene, 0.5)) //
				.withPopulationSize(10) //
				.withRandomGene(randomGene) //
				.withSelection(new TournamentSelection<>(4)) //
				.withFitness(new CharsToGivenStringFitness(target)) //
				.build();

		geneticAlgorithm.run();
	}

}
