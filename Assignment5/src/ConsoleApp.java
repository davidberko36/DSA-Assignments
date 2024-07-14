import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Quick Sort");
            System.out.println("2. Traveling Salesman Problem");
            System.out.println("3. Huffman Coding");
            System.out.println("4. Kruskal's Algorithm");
            System.out.println("5. Dijkstra's Algorithm");
            System.out.println("6. Quick Hull");
            System.out.println("7. Strassen Algorithm");
            System.out.println("8. Prim's Algorithm");
            System.out.println("9. Merge Sort");
            System.out.println("10. Closest Pair");
            System.out.println("0. Exit");

            int choice = getValidIntInput(scanner, "Enter your choice: ", 0, 10);

            switch (choice) {
                case 1:
                    handleQuickSort(scanner);
                    break;
                case 2:
                    TravelingSalesman.main(new String[0]);
                    break;
                case 3:
                    HuffmanCoding.main(new String[0]);
                    break;
                case 4:
                    KruskalMain.main(new String[0]);
                    break;
                case 5:
                    Dijkstra.main(new String[0]);
                    break;
                case 6:
                    QuickHull.main(new String[0]);
                    break;
                case 7:
                    Strassen.main(new String[0]);
                    break;
                case 8:
                    PrimMain.main(new String[0]);
                    break;
                case 9:
                    MergeSort.main(new String[0]);
                    break;
                case 10:
                    ClosestPair.main(new String[0]);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Application exited.");
    }

    private static void handleQuickSort(Scanner scanner) {
        System.out.print("Enter the number of elements in the array: ");
        int n = getValidIntInput(scanner, 1, Integer.MAX_VALUE);

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = getValidIntInput(scanner, "Enter element " + (i + 1) + ": ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        QuickSort.sort(array, 0, array.length - 1);

        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static int getValidIntInput(Scanner scanner, String prompt, int min, int max) {
        int input = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character
        return input;
    }

    private static int getValidIntInput(Scanner scanner, int min, int max) {
        int input = -1;
        boolean valid = false;
        while (!valid) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character
        return input;
    }
}
