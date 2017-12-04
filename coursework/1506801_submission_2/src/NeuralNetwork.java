import org.apache.commons.math3.linear.*;
public class NeuralNetwork {
	public static final double[] DEFAULT_INPUT = {0,1,0,1};
	public static final double[] DEFAULT_OUTPUT = {1,0,1,0};
	public static final double[] INCOMPLETE_INPUT = {0,0,0,1};
	public static final double[] NOISY_INPUT = {1,1,0,1};
	
	public static void main(String[] args) {
		//Question B.1.
		System.out.println("B.1.");
		SynapticMatrix synapticMatrix = new SynapticMatrix(DEFAULT_INPUT, DEFAULT_OUTPUT);
		
		double[] generatedOutput = synapticMatrix.test(DEFAULT_INPUT, DEFAULT_OUTPUT.length);
		System.out.println(synapticMatrix);
		printArray(DEFAULT_OUTPUT);
		printArray(generatedOutput);
		System.out.println();

		//Question B.2.
		System.out.println("B.2.");
		double[] incompleteInput = {0,0,0,1};
		generatedOutput = synapticMatrix.test(incompleteInput, DEFAULT_OUTPUT.length);
		printArray(DEFAULT_OUTPUT);
		printArray(generatedOutput);
		System.out.println();
		
		//Question B.3.
		System.out.println("B.3.");
		double[] noisyInput = {1,1,0,1};
		generatedOutput = synapticMatrix.test(noisyInput, DEFAULT_OUTPUT.length);
		printArray(DEFAULT_OUTPUT);
		printArray(generatedOutput);
		System.out.println();

		//Question B.4.
		System.out.println("B.4");
		double[] largeInput = {0,1,0,0,0,1};
		double[] largeOutput = {1,0,0,0,1,0};
		SynapticMatrix largeSynapticMatrix = new SynapticMatrix(largeInput, largeOutput);
		
		generatedOutput = largeSynapticMatrix.test(largeInput, largeOutput.length);
		System.out.println(largeSynapticMatrix);
		printArray(largeOutput);
		printArray(generatedOutput);
		System.out.println();
		
		//Question B.5.
		System.out.println("B.5.");
		double[] distortedInput = {1,1,0,0,0,0};
		generatedOutput = largeSynapticMatrix.test(largeInput, largeOutput.length);
		
		printArray(largeOutput);
		printArray(generatedOutput);
		System.out.println();
		
		
		//Question B.6.
		/*
		 * Code below will make the network mistake the first input (largeInput) with the second input, recalling the wrong output pattern.
		 * Neurons strengthened = 1/8
		 * Load parameter = (patterns stored / input layer size) = 2/8 = 1/4
		 */
		System.out.println("B.6.");
		double[] secondInput = {1,1,0,0,0,1};
		double[] secondOutput = {1,1,0,0,1,0};
		
		largeSynapticMatrix.train(secondInput, secondOutput);
		generatedOutput = largeSynapticMatrix.test(largeInput, largeOutput.length);
		printArray(generatedOutput);
		generatedOutput = largeSynapticMatrix.test(secondInput, largeOutput.length);
		printArray(generatedOutput);
		
		//Question B.7. is answered in the report.
		
	}
	
	public static void printArray(double[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}	
}
