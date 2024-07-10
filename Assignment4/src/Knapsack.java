import java.util.Arrays;

public class Knapsack {
    public void runKnapsack(String weightsInput, String valuesInput, int capacity) {
        int[] weights = Arrays.stream(weightsInput.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] values = Arrays.stream(valuesInput.split(",")).mapToInt(Integer::parseInt).toArray();

        long startTime = System.currentTimeMillis();
        int result = knapsack(weights, values, capacity);
        long endTime = System.currentTimeMillis();

        System.out.println("Maximum value: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }
}
