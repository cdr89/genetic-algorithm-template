# genetic-algorithm-template
A template for genetic algorithm

## Quick start
- Import the genetic-algorithm-template project dependency;
- Do a Maven "clean package install" to use the .jar dependency in your project or simply package and import as a jar;

### Implement a Fitness
Implement your fitness function extending the class it.caldesi.geneticalgorithm.fitness.Fitness

```java
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
```

### Implement the RandomGene
The RandomGene is used to generate a random gene for a Chromosome

```java
package it.caldesi.geneticalgorithm.example;

import it.caldesi.geneticalgorithm.chromosome.gene.RandomGene;

public class CharRandomGene extends RandomGene<Character> {

	private final static char[] ALPHABET = { ' ', // space
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', // lowercase
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };// uppercase

	@Override
	public Character getRandomGene() {
		return ALPHABET[(int) Math.round((ALPHABET.length - 1) * Math.random())];
	}

}
```

### Parametrize and run the genetic algorithm!
Now is time to choose all the parameter of the algorithm and just run it.  
To do this, create a class with a main method and instantiate the GeneticAlgorithm through the Builder.

```java
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
				.build(); // Build the instance!

		geneticAlgorithm.run(); // Run the algorithm!
	}

}
```

For more information and to test the algorithm, see the [genetic-algorithm-string-sample](https://github.com/cdr89/genetic-algorithm-template/tree/master/samples/genetic-algorithm-string-sample)  


### References
[Genetic algorithms](https://en.wikipedia.org/wiki/Genetic_algorithm)  
[Fitness function](https://en.wikipedia.org/wiki/Fitness_function)  
[Genetic operator](https://en.wikipedia.org/wiki/Genetic_operator)  
[Crossover](https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm))  
[Mutation](https://en.wikipedia.org/wiki/Mutation_(genetic_algorithm))  
[Selection](https://en.wikipedia.org/wiki/Selection_(genetic_algorithm))  
[Application of genetic algorithms](https://en.wikipedia.org/wiki/List_of_genetic_algorithm_applications)  
