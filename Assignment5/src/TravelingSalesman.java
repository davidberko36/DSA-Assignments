import java.util.Scanner;

public class TravelingSalesman {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        int numCities = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter the " + numCities + "x" + numCities + " distance matrix (columns separated by commas and rows by semicolons): ");
        String matrixInput = scanner.nextLine();

        int[][] matrix = parseMatrix(matrixInput, numCities);

        int[] shortestPath = new int[numCities];
        for (int i = 0; i < numCities; i++) {
            shortestPath[i] = i;
        }

        int[] bestPath = new int[numCities];
        System.arraycopy(shortestPath, 0, bestPath, 0, numCities);
        int minDistance = Integer.MAX_VALUE;

        do {
            int currentDistance = calculateTotalDistance(shortestPath, matrix);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                System.arraycopy(shortestPath, 0, bestPath, 0, numCities);
            }
        } while (nextPermutation(shortestPath));

        System.out.print("The shortest path is: ");
        for (int city : bestPath) {
            System.out.print(city + " -> ");
        }
        System.out.println(bestPath[0]); // To complete the cycle
        System.out.println("The minimum distance is: " + minDistance);
    }

    private static int[][] parseMatrix(String matrixInput, int numCities) {
        int[][] matrix = new int[numCities][numCities];
        String[] rows = matrixInput.split(";");
        for (int i = 0; i < rows.length; i++) {
            String[] columns = rows[i].split(",");
            for (int j = 0; j < columns.length; j++) {
                matrix[i][j] = Integer.parseInt(columns[j]);
            }
        }
        return matrix;
    }

    private static int calculateTotalDistance(int[] path, int[][] matrix) {
        int totalDistance = 0;
        for (int i = 0; i < path.length - 1; i++) {
            totalDistance += matrix[path[i]][path[i + 1]];
        }
        totalDistance += matrix[path[path.length - 1]][path[0]]; // Return to the starting city
        return totalDistance;
    }

    private static boolean nextPermutation(int[] array) {
        // Find the largest index k such that array[k] < array[k + 1]
        int k = array.length - 2;
        while (k >= 0 && array[k] >= array[k + 1]) {
            k--;
        }
        if (k < 0) {
            return false;
        }

        // Find the largest index l greater than k such that array[k] < array[l]
        int l = array.length - 1;
        while (array[k] >= array[l]) {
            l--;
        }

        // Swap the value of array[k] with that of array[l]
        int temp = array[k];
        array[k] = array[l];
        array[l] = temp;

        // Reverse the sequence from array[k + 1] up to and including the final element array[array.length - 1]
        int start = k + 1;
        int end = array.length - 1;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }

        return true;
    }
}
