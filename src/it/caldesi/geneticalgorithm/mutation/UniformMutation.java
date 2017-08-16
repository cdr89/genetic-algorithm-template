package it.caldesi.geneticalgorithm.mutation;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;
import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;

public class UniformMutation<T> extends Mutation<T> {

	private RandomGene<T> randomGene;
	private double mutationRate;

	public UniformMutation(RandomGene<T> randomGene, double mutationRate) {
		this.randomGene = randomGene;
		this.mutationRate = mutationRate;
	}

	@Override
	public Chromosome<T> mutate(Chromosome<T> chromosome) {
		Chromosome<T> mutateChromosome = null;
		try {
			mutateChromosome = new Chromosome<>(chromosome.genes.size());
			for (int i = 0; i < chromosome.genes.size(); i++) {
				if (Math.random() < mutationRate) {
					mutateChromosome.genes.set(i, randomGene.getRandomGene());
				} else {
					mutateChromosome.genes.set(i, chromosome.genes.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mutateChromosome;
	}

}
