package it.caldesi.geneticalgorithm.example;

import it.caldesi.geneticalgorithm.chromosome.Chromosome;
import it.caldesi.geneticalgorithm.fitness.Fitness;

public class CharsToGivenStringFitness extends Fitness<Character> {

	private String target;

	public CharsToGivenStringFitness(String target) {
		this.target = target;
	}

	@Override
	public double calcFitness(Chromosome<Character> chromosome) {
		int fitnessCount = 0;
		for (int i = 0; i < target.length(); i++) {
			if (target.charAt(i) == chromosome.genes.get(i))
				fitnessCount++;
		}

		return fitnessCount;
	}

	@Override
	public double getTargetFitness() {
		return target.length();
	}

}
