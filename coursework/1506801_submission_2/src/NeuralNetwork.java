import org.apache.commons.math3.linear.*;
public class NeuralNetwork {
	public static final double[] DEFAULT_INPUT = {0,1,0,1};
	public static final double[] DEFAULT_OUTPUT = {1,0,1,0};
	public static final double[] INCOMPLETE_INPUT = {0,0,0,1};
	public static final double[] NOISY_INPUT = {1,1,0,1};
	
	public static void main(String[] args) {
		//Question B.1.
		System.out.println("B.1.");
		double[] input = DEFAULT_INPUT;
		double[] output = DEFAULT_OUTPUT;
		RealMatrix synapticMatrix = generateSynapticMatrix(input, output);
		
		double[] generatedOutput = testSynapticMatrix(input, DEFAULT_INPUT, synapticMatrix, output.length);
		System.out.println(synapticMatrix);
		printArray(DEFAULT_OUTPUT);
		printArray(generatedOutput);
		System.out.println();
		
		
		//Question B.2.
		System.out.println("B.2.");
		double[] incompleteInput = {0,0,0,1};
		generatedOutput = testSynapticMatrix(incompleteInput, DEFAULT_INPUT, synapticMatrix, output.length);
		printArray(DEFAULT_OUTPUT);
		printArray(generatedOutput);
		System.out.println();
		
		//Question B.3.
		System.out.println("B.3.");
		double[] noisyInput = {1,1,0,1};
		generatedOutput = testSynapticMatrix(noisyInput, DEFAULT_INPUT, synapticMatrix, output.length);
		printArray(DEFAULT_OUTPUT);
		printArray(generatedOutput);
		System.out.println();
		
		//Question B.4.
		System.out.println("B.4");
		double[] largeInput = {0,1,0,0,0,1,0,0};
		double[] largeOutput = {1,0,0,0,1,0,0,0};
		RealMatrix largeSynapticMatrix = generateSynapticMatrix(largeInput, largeOutput);
		
		generatedOutput = testSynapticMatrix(largeInput, largeInput, largeSynapticMatrix, largeOutput.length);
		System.out.println(largeSynapticMatrix);
		printArray(largeOutput);
		printArray(generatedOutput);
		System.out.println();
		
		//Question B.5.
		System.out.println("B.5.");
		double[] distortedInput = {1,1,0,0,0,0,0,0};
		generatedOutput = testSynapticMatrix(distortedInput, largeInput, largeSynapticMatrix, largeOutput.length);
		
		printArray(largeOutput);
		printArray(generatedOutput);
		System.out.println();
		
		//Question B.6.
		/*
		 * Code below will make the network mistake the first input (largeInput) with the second input.
		 * Neurons strengthened = 1/8
		 * Load parameter = (patterns stored / input layer size) = 2/8 = 1/4
		 */
		System.out.println("B.6.");
		double[] secondInput = {0,1,0,0,0,1,0,1};
		double[] secondOutput = {1,1,0,0,1,0,0,0};
		
		trainSynapticMatrix(secondInput, secondOutput, largeSynapticMatrix);
		generatedOutput = testSynapticMatrix(largeInput, largeInput, largeSynapticMatrix, largeOutput.length);
		printArray(generatedOutput);
		generatedOutput = testSynapticMatrix(secondInput, secondInput, largeSynapticMatrix, largeOutput.length);
		printArray(generatedOutput);
		
		//Question B.7. is answered in the report.
		
	}
	
	//Generate a synaptic matrix given input and output
	public static RealMatrix generateSynapticMatrix(double[] input, double[] output) {
		double[][] matrix = new double[input.length][output.length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < output.length; j++) {
				if (input[i] == 1 && output[j] == 1) {
					matrix[i][j] = 1;
				} else {
					matrix[i][j] = 0;
				}
			}
		}
		
		return new Array2DRowRealMatrix(matrix);
	}
	
	//Train a given synaptic matrix on the given input and output
	public static void trainSynapticMatrix(double[] input, double[] output, RealMatrix synapticMatrix) {
		for (int i = 0; i < synapticMatrix.getRowDimension(); i++) {
			for (int j = 0; j < synapticMatrix.getColumnDimension(); j++) {
				if (input[i] == 1 && output[j] == 1) {
					 synapticMatrix.setEntry(i, j, 1.0);
				}
			}
		}
	}
	
	//Test a given synaptic matrix' recall given input and returns recalled output
	public static double[] testSynapticMatrix(double[] input, double[] originalInput, RealMatrix synapticMatrix, int outputLength) {
		double threshold;
		double[] weights = new double[synapticMatrix.getColumn(0).length];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 1;
		}
		double inputSum = integrate(weights, input);
		double originalInputSum = integrate(weights, originalInput);
		
		if (inputSum < originalInputSum) {
			threshold = inputSum;
		} else {
			threshold = originalInputSum;
		}
		
		double[] output = new double[outputLength];
		for (int i = 0; i < outputLength; i++) {
			output[i] = compare(integrate(weights, synapticMatrix.getColumn(i)), threshold);
		}
		return output;
	}
	
	
	public static int integrate(double[] weights, double[] firing) {
		int total = 0;
		for (int i = 0; i < firing.length; i++) {
			total += (firing[i] * weights[i]);
		}
		return total;
	}
	
	public static int compare(double sum, double threshold) {
		if (sum >= threshold) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static void printArray(double[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}	
}
