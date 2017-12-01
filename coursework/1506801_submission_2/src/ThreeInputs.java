import org.apache.commons.math3.linear.RealMatrix;

public class ThreeInputs {

	//Question D
	/*
	 * Evaluates 3 inputs at the same time by collating them in to a single input, then passing
	 * them to the synaptic matrix along with the size of the output.
	 */
	public static void main(String[] args) {
		double[] input1 = {0,1,0,1};
		double[] input2 = {0,1,1,0};
		double[] input3 = {0,0,0,1};
		double[] output = {1,0,0,1};
		
		double[] collatedInput = collate(input1, input2, input3);
		RealMatrix synapticMatrix = NeuralNetwork.generateSynapticMatrix(collatedInput, output);
		double[] generatedOutput = NeuralNetwork.testSynapticMatrix(collatedInput, collatedInput, synapticMatrix, output.length);
		NeuralNetwork.printArray(generatedOutput);
		
	}

	//Collate 3 inputs in to a single array
	public static double[] collate(double[] input1, double[] input2, double[] input3) {
		double[] retval = new double[input1.length + input2.length + input3.length];
		int pos = 0;
		for (int i = 0; i < input1.length; i++) {
			retval[pos] = input1[i];
			pos++;
		}
		for (int i = 0; i < input2.length; i++) {
			retval[pos] = input2[i];
			pos++;
		}
		for (int i = 0; i < input3.length; i++) {
			retval[pos] = input3[i];
			pos++;
		}
		return retval;
	}
}
