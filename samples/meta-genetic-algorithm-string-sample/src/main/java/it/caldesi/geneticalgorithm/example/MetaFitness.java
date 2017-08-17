package it.caldesi.geneticalgorithm.example;

import java.util.HashMap;
import java.util.Map;

import it.caldesi.geneticalgorithm.GeneticAlgorithm;
import it.caldesi.geneticalgorithm.chromosome.Chromosome;
import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;
import it.caldesi.geneticalgorithm.crossover.UniformCrossover;
import it.caldesi.geneticalgorithm.fitness.Fitness;
import it.caldesi.geneticalgorithm.mutation.UniformMutation;
import it.caldesi.geneticalgorithm.output.DummyOutputPrinter;
import it.caldesi.geneticalgorithm.output.OutputPrinter;
import it.caldesi.geneticalgorithm.selection.TournamentSelection;

public class MetaFitness extends Fitness<Number> {
	
	// encoding genes as {
	// 		double mutationRate;
	// 		int populationSize;
	// 		int tournmentSize;
	// 		int eliteIndividuals;
	// }

	private Map<Chromosome<Number>, Double> fitnessCache = new HashMap<>();

	@Override
	public double calcFitness(Chromosome<Number> chromosome) {
		// check if the fitness was already computed
		if (fitnessCache.containsKey(chromosome))
			return fitnessCache.get(chromosome);

		double mutationRate = (double) chromosome.genes.get(0);
		int populationSize = (int) chromosome.genes.get(1);
		int tournmentSize = (int) chromosome.genes.get(2);
		int eliteIndividuals = (int) chromosome.genes.get(3);
		
		String target = "Hello world";
		RandomGene<Character> randomGene = new CharRandomGene();
		CharsToGivenStringFitness fitness = new CharsToGivenStringFitness(target);
		OutputPrinter<Character> dummyOutputPrinter = new DummyOutputPrinter<>(fitness);

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
				.withOutputPrinter(dummyOutputPrinter) // Use a dummy printer to take trace of execution times
				.build(); // Build the instance!

		geneticAlgorithm.run(); // Run the algorithm!

		double fitnessValue = -dummyOutputPrinter.getExecutionTime();
		fitnessCache.put(chromosome, fitnessValue);

		return fitnessValue;
	}
	
	
	private Double targetFitness = null;

	@Override
	public double getTargetFitness() {
		if (targetFitness != null)
			return targetFitness;

		double targetFitness;
		// if has not enough samples return MAX_VALUE as target
		if (!hasEnoughSamples())
			targetFitness = Double.MAX_VALUE;
		else { // otherwise returns the minimum (maximum of the negative) of all
				// the execution times
			targetFitness = fitnessCache.values().stream().max(Double::compare).get();
			if (this.targetFitness == null) {
				// we want something best at least of 1ms of the first execution
				this.targetFitness = ++targetFitness;
			}
		}

		return targetFitness;
	}

	private boolean hasEnoughSamples() {
		return fitnessCache.keySet().size() >= 10; // it is ok after just the first generation
	}

}
