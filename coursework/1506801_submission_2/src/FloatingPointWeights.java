import org.apache.commons.math3.linear.RealMatrix;

public class FloatingPointWeights {
	public static void main(String[] args) {
		double[] input = NeuralNetwork.DEFAULT_INPUT;
		double[] output = NeuralNetwork.DEFAULT_OUTPUT;
		RealMatrix synapticMatrix = NeuralNetwork.generateSynapticMatrix(input, output);

		
	}
	
	//TODO: Implement weights and test
	public static double[] testSynapticMatrix(double[] input, double[] originalInput, RealMatrix synapticMatrix) {
		double threshold;
		double[] weights = new double[synapticMatrix.getColumn(0).length];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 1;
		}
		double inputSum = NeuralNetwork.integrate(weights, input);
		double originalInputSum = NeuralNetwork.integrate(weights, originalInput);
		
		if (inputSum < originalInputSum) {
			threshold = inputSum;
		} else {
			threshold = originalInputSum;
		}
		
		double[] output = new double[input.length];
		for (int i = 0; i < synapticMatrix.getColumn(0).length; i++) {
			output[i] = NeuralNetwork.compare(NeuralNetwork.integrate(weights, synapticMatrix.getColumn(i)), threshold);
		}
		return output;
	}

}
