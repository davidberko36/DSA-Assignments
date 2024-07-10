import java.util.Arrays;

public class SubsetSumProblem {
    public void runSubsetSumProblem(String setInput, int sum) {
        int[] set = Arrays.stream(setInput.split(",")).mapToInt(Integer::parseInt).toArray();

        long startTime = System.currentTimeMillis();
        boolean result = subsetSum(set, set.length, sum);
        long endTime = System.currentTimeMillis();

        System.out.println("Subset sum result: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private boolean subsetSum(int[] set, int n, int sum) {
        if (sum == 0) return true;
        if (n == 0 && sum != 0) return false;
        if (set[n - 1] > sum) return subsetSum(set, n - 1, sum);
        return subsetSum(set, n - 1, sum) || subsetSum(set, n - 1, sum - set[n - 1]);
    }
}

