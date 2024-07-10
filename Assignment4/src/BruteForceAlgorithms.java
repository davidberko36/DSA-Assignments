import java.util.Scanner;

public class BruteForceAlgorithms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an algorithm to run:");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Polynomial Evaluation");
            System.out.println("3. Brute Force String Matching");
            System.out.println("4. Subset Sum Problem");
            System.out.println("5. Traveling Salesman Problem");
            System.out.println("6. Knapsack Problem");
            System.out.println("7. Selection Sort");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter a comma-separated list of integers: ");
                    String bubbleSortInput = scanner.nextLine();
                    new BubbleSort().runBubbleSort(bubbleSortInput);
                    break;
                case 2:
                    System.out.print("Enter the coefficients of the polynomial (comma-separated): ");
                    String coefficientsInput = scanner.nextLine();
                    System.out.print("Enter the value of x: ");
                    int x = scanner.nextInt();
                    new PolynomialEvaluation().runPolynomialEvaluation(coefficientsInput, x);
                    break;
                case 3:
                    System.out.print("Enter the text: ");
                    String text = scanner.nextLine();
                    System.out.print("Enter the pattern to search for: ");
                    String pattern = scanner.nextLine();
                    new BruteForceStringMatching().runBruteForceStringMatching(text, pattern);
                    break;
                case 4:
                    System.out.print("Enter the set of integers (comma-separated): ");
                    String setInput = scanner.nextLine();
                    System.out.print("Enter the target sum: ");
                    int sum = scanner.nextInt();
                    new SubsetSumProblem().runSubsetSumProblem(setInput, sum);
                    break;
                case 5:
                    int[][] graph = {
                            {0, 10, 15, 20},
                            {10, 0, 35, 25},
                            {15, 35, 0, 30},
                            {20, 25, 30, 0}
                    };
                    new TravelingSalesman().runTravelingSalesman(graph);
                    break;
                case 6:
                    System.out.print("Enter the weights (comma-separated): ");
                    String weightsInput = scanner.nextLine();
                    System.out.print("Enter the values (comma-separated): ");
                    String valuesInput = scanner.nextLine();
                    System.out.print("Enter the capacity of the knapsack: ");
                    int capacity = scanner.nextInt();
                    new Knapsack().runKnapsack(weightsInput, valuesInput, capacity);
                    break;
                case 7:
                    System.out.print("Enter a comma-separated list of integers: ");
                    String selectionSortInput = scanner.nextLine();
                    new SelectionSort().runSelectionSort(selectionSortInput);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

            System.out.println();
        }

        scanner.close();
    }
}
