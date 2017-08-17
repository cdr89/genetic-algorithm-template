package it.caldesi.geneticalgorithm.example;

import java.util.concurrent.ThreadLocalRandom;

import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;

public class MetaRandomGene extends RandomGene<Number> {

	// encoding genes as {
	// 		double mutationRate;
	// 		int populationSize;
	// 		int tournmentSize;
	// 		int eliteIndividuals;
	// }

	@Override
	public Number getRandomGene(int geneIndex) {
		switch (geneIndex) {
		case 0: // mutationRate
			// must be between 0 and 1, with mutation 0 we do not have any
			// evolution, so the algorithm could not stop
			// in this case we stay in the interval 0.50 - 0.60
			double mutationRate = ThreadLocalRandom.current().nextDouble(0.50d, 0.60d);
			return mutationRate;

		case 1: // populationSize
			// in this case we want to stay fixed to 10 for simplicity
			return 10;

		case 2: // tournmentSize
			// the tournment size must be less then or equals the
			// (entire population - elite individuals)
			// to be safe, we want to be in the interval [4 - 5]
			int tournmentSize = ThreadLocalRandom.current().nextInt(4, 5 + 1);
			return tournmentSize;

		case 3: // elite individuals
			// we want to be always 1
			int eliteIndividuals = 1;
			return eliteIndividuals;

		default:
			return null;
		}
	}

}
