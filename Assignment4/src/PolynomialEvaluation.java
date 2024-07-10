import java.util.Arrays;

public class PolynomialEvaluation {
    public void runPolynomialEvaluation(String coefficientsInput, int x) {
        int[] coefficients = Arrays.stream(coefficientsInput.split(",")).mapToInt(Integer::parseInt).toArray();

        long startTime = System.currentTimeMillis();
        int result = polynomialEvaluation(coefficients, x);
        long endTime = System.currentTimeMillis();

        System.out.println("Polynomial result: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private int polynomialEvaluation(int[] coefficients, int x) {
        int result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }
}
