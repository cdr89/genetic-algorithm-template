package it.caldesi.geneticalgorithm.chromosome;

import java.util.ArrayList;
import java.util.List;

public class Chromosome<T> {

	public List<T> genes;

	public Chromosome(int size) {
		genes = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			genes.add(null);
		}
		assert (genes.size() == size);
	}

	@Override
	public String toString() {
		return genes.toString();
	}

}
