import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. QuickSort");
            System.out.println("2. Traveling Salesman Problem");
            System.out.println("3. Huffman Coding");
            System.out.println("0. Exit");

            int choice = getValidIntInput(scanner, "Enter your choice: ", 0, 3);

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
