import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class SynapticMatrix {
	public static final double[] DEFAULT_INPUT = {0,1,0,1};
	public static final double[] DEFAULT_OUTPUT = {1,0,1,0};
	public static final double[] INCOMPLETE_INPUT = {0,0,0,1};
	public static final double[] NOISY_INPUT = {1,1,0,1};
	
	private RealMatrix synapticMatrix;
	
	public SynapticMatrix(double[] input, double[] output) {
		generateSynapticMatrix(input, output);
	}
	
	//Returns the recalled output given an input vector
	public double[] test(double[] input, double[] originalInput, int outputLength) {
		double threshold;
		double[] weights = new double[synapticMatrix.getColumn(0).length];
		double[] output = new double[outputLength];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 1;
		}
		double inputSum = integrate(weights, input);

		if (inputSum < integrate(weights, originalInput)) {
			threshold = inputSum;
		} else {
			threshold = integrate(weights, originalInput);
		}
		for (int i = 0; i < outputLength; i++) {
			output[i] = compare(integrate(weights, synapticMatrix.getColumn(i)), threshold);
		}
		return output;
	}
	
	//Returns the recalled output with a simoid function given an input vector
	public double[] testSigmoid(double[] input, double[] originalInput, int outputLength) {
		double[] weights = new double[synapticMatrix.getColumn(0).length];
		double[] output = new double[outputLength];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 1;
		}
		for (int i = 0; i < outputLength; i++) {
			output[i] = compareSigmoid(integrate(weights, synapticMatrix.getColumn(i)));
		}
		return output;
	}
	
	//Train an association between an input and an output
	public void train(double[] input, double[] output) {
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < output.length; j++) {
				if (input[i] == 1 && output[j] == 1) {
					synapticMatrix.setEntry(i, j, 1);
				}
			}
		}
	}
	
	//Instantiate the synaptic matrix on the first set of input
	public void generateSynapticMatrix(double[] input, double[] output) {
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
		this.synapticMatrix = new Array2DRowRealMatrix(matrix);
	}
	
	//Applies weights to the input vector
	private double integrate(double[] weights, double[] firing) {
		int total = 0;
		for (int i = 0; i < firing.length; i++) {
			total += (firing[i] * weights[i]);
		}
		return total;
	}
	
	//Compares the sum of a column to a threshold value
	private double compare(double sum, double threshold) {
		if (sum >= threshold) {
			return 1;
		} else {
			return 0;
		}
	}
	
	//Applies the sigmoid function to the sum of a column
	private double compareSigmoid(double sum) {
		return 1 / (1 + Math.pow(Math.E, -sum));
	}
	
	
	@Override
	public String toString() {
		return this.synapticMatrix.toString();
	}
}