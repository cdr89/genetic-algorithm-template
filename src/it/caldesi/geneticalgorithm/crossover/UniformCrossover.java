package it.caldesi.geneticalgorithm.crossover;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;

public class UniformCrossover<T> extends Crossover<T> {

	private float ratio;

	public UniformCrossover() {
		this(0.5f);
	}

	public UniformCrossover(float ratio) {
		if (ratio < 0 || ratio > 1)
			throw new IllegalArgumentException();
		this.ratio = ratio;
	}

	@Override
	public Chromosome<T> crossover(Chromosome<T> c1, Chromosome<T> c2) {
		Chromosome<T> crossoverChromosome = null;
		try {
			crossoverChromosome = new Chromosome<>(c1.genes.size());
			for (int i = 0; i < c1.genes.size(); i++) {
				if (Math.random() < ratio) {
					crossoverChromosome.genes.set(i, c1.genes.get(i));
				} else {
					crossoverChromosome.genes.set(i, c2.genes.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return crossoverChromosome;
	}

}
