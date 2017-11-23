
public class NeuralNetwork {
	public static final int[] DEFAULT_INPUT = {0,1,0,1};
	public static final int[] DEFAULT_OUTPUT = {1,0,1,0};
	
	public static void main(String[] args) {
		int[] input = DEFAULT_INPUT;
		int[][] synapticMatrix = new int[input.length][input.length];
		for (int i = 0; i < input.length; i++) {
			for(int j = 0; j < input.length; j++) {
				if (input[i] == 1 && DEFAULT_OUTPUT[j] == 1) {
					synapticMatrix[i][j] = 1;
				} else {
					synapticMatrix[i][j] = 0;
				}
			}
		}
	}
	
	public static int[] compare(int[] input) {
		return new int[0];
	}
}