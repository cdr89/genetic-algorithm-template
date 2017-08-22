# genetic-algorithm-template
A template for genetic algorithm

## Quick start
- Import the genetic-algorithm-template project dependency;
- Do a Maven "clean package install" to use the .jar dependency in your project or simply package and import as a jar;
Once installed in your local Maven repository, you can import the dependency:

```xml
<dependency>
	<groupId>it.caldesi</groupId>
	<artifactId>genetic-algorithm-template</artifactId>
	<version>RELEASE</version>
</dependency>
```
  
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
		// parameters
		double mutationRate = 0.5;
		int populationSize = 10;
		int tournmentSize = 4;
		int eliteIndividuals = 1;
		
		runAlgorithm(mutationRate, populationSize, tournmentSize, eliteIndividuals);
	}

	public static void runAlgorithm(double mutationRate, int populationSize, int tournmentSize, int eliteIndividuals) {
		String target = "Hello world";
		RandomGene<Character> randomGene = new CharRandomGene();
		CharsToGivenStringFitness fitness = new CharsToGivenStringFitness(target);

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
				.withOutputPrinter(new StandardOutputPrinter<>(fitness)) // Use a standar printer (optional)
				.build(); // Build the instance!

		geneticAlgorithm.run(); // Run the algorithm!
	}

}
```

For more information and to test the algorithm, see the [genetic-algorithm-string-sample](https://github.com/cdr89/genetic-algorithm-template/tree/master/samples/genetic-algorithm-string-sample)  

### Sample run output
```
Initialized first generation #0
Most fit chromosome: 0.0
Target fitness: 11.0
Chromosome #0: [F, Y, O, q, r, F, p, N, t, d, W] fitness: 0.0
Chromosome #1: [k, d, e, m, t, C, p, P, n, Y, l] fitness: 0.0
Chromosome #2: [e, f, F, J, h, n, e, B, Y, O, c] fitness: 0.0
Chromosome #3: [a, r, k, Y, w, e, M, W, W, L, P] fitness: 0.0
Chromosome #4: [b, X, D, R, P, c, A, p, k, A, C] fitness: 0.0
Chromosome #5: [c, I, x, k, h, m, V, Q, x, N, w] fitness: 0.0
Chromosome #6: [h, q, e, n, G, n, m, x, y, r, b] fitness: 0.0
Chromosome #7: [L, Y, e, X, m, T, p, B, P, a, b] fitness: 0.0
Chromosome #8: [g, i, K, t, W, v, q, i, C, J, n] fitness: 0.0
Chromosome #9: [G, F, P, J, f, w, S, N, h, U, q] fitness: 0.0
---------------------------------------------------------------

[...]

Generation #12505
Most fit chromosome: 11.0
Target fitness: 11.0
Chromosome #0: [H, e, l, l, o,  , w, o, r, l, d] fitness: 11.0
Chromosome #1: [H, n, l, l, o,  , w, o, r, l, d] fitness: 10.0
Chromosome #2: [H, M, l, l, o,  , w, l, r, v, d] fitness: 8.0
Chromosome #3: [H, n, l, l, o, L, w, p, I, l, d] fitness: 7.0
Chromosome #4: [v, n, l, D, F,  , w, o, r, l, d] fitness: 7.0
Chromosome #5: [H, n, N, R, o, m, w, o, r, m, d] fitness: 6.0
Chromosome #6: [h, v, o, l, o,  , w, v, r, l, C] fitness: 6.0
Chromosome #7: [H, E, l, l, I, u, w, o, g, f, d] fitness: 6.0
Chromosome #8: [V, n, R, l, G,  , b, r, r, l, q] fitness: 4.0
Chromosome #9: [j, A, l, E, o, Q, w, i, r, g, C] fitness: 4.0
---------------------------------------------------------------
Execution time: 00h 00min 04s 4523ms
```

### Meta genetic algorithm
If you want to see something of perverse with genetic algorithm, take a look to [meta-genetic-algorithm-string-sample](https://github.com/cdr89/genetic-algorithm-template/tree/master/samples/meta-genetic-algorithm-string-sample): an application of genetic algorithm to itself, in order to determine a solution that minimize the execution time.


### References
[Genetic algorithms](https://en.wikipedia.org/wiki/Genetic_algorithm)  
[Fitness function](https://en.wikipedia.org/wiki/Fitness_function)  
[Genetic operator](https://en.wikipedia.org/wiki/Genetic_operator)  
[Crossover](https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm))  
[Mutation](https://en.wikipedia.org/wiki/Mutation_(genetic_algorithm))  
[Selection](https://en.wikipedia.org/wiki/Selection_(genetic_algorithm))  
[Application of genetic algorithms](https://en.wikipedia.org/wiki/List_of_genetic_algorithm_applications)  
