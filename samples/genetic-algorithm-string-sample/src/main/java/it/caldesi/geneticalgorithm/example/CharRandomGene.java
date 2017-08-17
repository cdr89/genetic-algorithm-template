package it.caldesi.geneticalgorithm.example;

import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;

public class CharRandomGene extends RandomGene<Character> {

	private final static char[] ALPHABET = { ' ', // space
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', // lowercase
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };// uppercase

	@Override
	public Character getRandomGene(int geneIndex) {
		return ALPHABET[(int) Math.round((ALPHABET.length - 1) * Math.random())];
	}

}
