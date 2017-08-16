package it.caldesi.geneticalgorithm.selection;

import it.caldesi.geneticalgorithm.population.Population;

public abstract class Selection<T> {

	public abstract Population<T> selectPopulation(Population<T> population);

}
