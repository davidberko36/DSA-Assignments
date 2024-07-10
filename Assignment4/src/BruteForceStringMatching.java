public class BruteForceStringMatching {
    public void runBruteForceStringMatching(String text, String pattern) {
        long startTime = System.currentTimeMillis();
        int index = bruteForceStringMatching(text, pattern);
        long endTime = System.currentTimeMillis();

        System.out.println("Pattern found at index: " + index);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private int bruteForceStringMatching(String text, String pattern) {
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j = 0;
            while (j < pattern.length() && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i;
            }
        }
        return -1;
    }
}
