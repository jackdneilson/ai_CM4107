import org.apache.commons.math3.linear.*;
public class NeuralNetwork {
	public static final double[] DEFAULT_INPUT = {0,1,0,1};
	public static final double[] DEFAULT_OUTPUT = {1,0,1,0};
	public static final double[] INCOMPLETE_INPUT = {0,0,0,1};
	public static final double[] NOISY_INPUT = {1,1,0,1};
	public static final double[] LARGE_INPUT_1 = {0,1,0,0,0,1};
	public static final double[] LARGE_INPUT_2 = {0,0,0,1,0,0};
	public static final double[] LARGE_OUTPUT_1 = {1,0,0,0,1,0};
	public static final double[] LARGE_OUTPUT_2 = {1,0,0,0,1,1};
	
	public static void main(String[] args) {
		//Question B.1.
		//Create a new synaptic matrix, then test with input and output of size 4
		System.out.println("B.1.");
		SynapticMatrix synapticMatrix = new SynapticMatrix(DEFAULT_INPUT, DEFAULT_OUTPUT);
		
		double[] generatedOutput = synapticMatrix.test(DEFAULT_INPUT, DEFAULT_INPUT, DEFAULT_OUTPUT.length);
		System.out.println(synapticMatrix);
		System.out.println("Correct output: " + printArray(DEFAULT_OUTPUT));
		System.out.println("Generated output: " + printArray(generatedOutput));
		System.out.println();
		

		//Question B.2.
		//Test the generated synaptic matrix with incomplete input
		System.out.println("B.2.");
		generatedOutput = synapticMatrix.test(INCOMPLETE_INPUT, DEFAULT_INPUT, DEFAULT_OUTPUT.length);
		System.out.println("Correct output: " + printArray(DEFAULT_OUTPUT));
		System.out.println("Generated output: " + printArray(generatedOutput));
		System.out.println();
		
		
		//Question B.3.
		//Test the generated synaptic matrix with noisy input
		System.out.println("B.3.");
		generatedOutput = synapticMatrix.test(NOISY_INPUT, DEFAULT_INPUT, DEFAULT_OUTPUT.length);
		System.out.println("Correct output: " + printArray(DEFAULT_OUTPUT));
		System.out.println("Generated output: " + printArray(generatedOutput));
		System.out.println();
		

		//Question B.4.
		//Generate and test a new synaptic matrix with 2 inputs and outputs of size 6
		System.out.println("B.4");
		SynapticMatrix largeSynapticMatrix = new SynapticMatrix(LARGE_INPUT_1, LARGE_OUTPUT_1);
		largeSynapticMatrix.train(LARGE_INPUT_2, LARGE_OUTPUT_2);
		
		generatedOutput = largeSynapticMatrix.test(LARGE_INPUT_1, LARGE_INPUT_1, LARGE_OUTPUT_1.length);
		System.out.println(largeSynapticMatrix);
		System.out.println("Correct output: " + printArray(LARGE_OUTPUT_1));
		System.out.println("Generated output: " + printArray(generatedOutput));
		System.out.println();
		
		
		//Question B.5.
		//Test the new synaptic matrix with distorted input
		System.out.println("B.5.");
		double[] distortedInput = {1,1,0,0,0,0};
		generatedOutput = largeSynapticMatrix.test(LARGE_INPUT_1, LARGE_INPUT_1, LARGE_OUTPUT_1.length);
		
		System.out.println("Correct output: " + printArray(LARGE_OUTPUT_1));
		System.out.println("Generated output: " + printArray(generatedOutput));
		System.out.println();
		
		
		//Question B.6.
		/*
		 * Code below will make the network mistake the first input (largeInput) with the second input, recalling the wrong output pattern.
		 * Neurons strengthened = 15/36
		 * Load parameter = (patterns stored / input layer size) = 3/6 = 1/2
		 */
		System.out.println("B.6.");
		double[] errorInput = {0,1,0,0,1,1};
		double[] errorOutput = {0,0,0,0,0,1};
		
		largeSynapticMatrix.train(errorInput, errorOutput);
		generatedOutput = largeSynapticMatrix.test(errorInput, errorInput, LARGE_OUTPUT_1.length);
		System.out.println("Correct output: " + printArray(errorOutput));
		System.out.println("Generated Output: " + printArray(generatedOutput));
		
		System.out.println();
		
		
		//Question C
		//Tests the synaptic matrix using floating point weights as opposed to binary.
		System.out.println("C.");
		System.out.println(printArray(largeSynapticMatrix.testSigmoid(LARGE_INPUT_1, LARGE_INPUT_1, LARGE_INPUT_1.length)));
		System.out.println();
		
		
		//Question D
		//Tests a synaptic matrix with 3 simultaneus inputs
		System.out.println("D.");
		ThreeInputs.main(null);
		System.out.println();
		
		
		//Question E
		//Takes the output of the first layer as the input of the second layer, acting as a hidden layer.
		System.out.println("E.");
		double[] inputLayer = {0,1,0,1,0,0};
		double[] intermediateLayer = {1,0,0,0,1,0};
		double[] outputLayer = {1,0,0,0,0,0};
		
		SynapticMatrix hiddenLayer = new SynapticMatrix(inputLayer, intermediateLayer);
		SynapticMatrix output = new SynapticMatrix(intermediateLayer, outputLayer);
		
		generatedOutput = output.test(hiddenLayer.test(inputLayer, inputLayer, outputLayer.length), hiddenLayer.test(inputLayer, inputLayer, outputLayer.length), outputLayer.length);
		System.out.println("Correct output: " + printArray(outputLayer));
		System.out.println("Generated Output: " + printArray(generatedOutput));
	}
	
	//Helper function to print array to StDOUT
	public static String printArray(double[] array) {
		String ret = "";
		for (int i = 0; i < array.length; i++) {
			ret += array[i] + " ";
		}
		return ret;
	}	
}
