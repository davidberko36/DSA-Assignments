import java.util.*;

public class TravelingSalesman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of cities
        System.out.print("Enter the number of cities: ");
        int numCities = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Read the distance matrix
        System.out.print("Enter the " + numCities + "x" + numCities + " distance matrix (columns separated by commas and rows by semicolons): ");
        String matrixInput = scanner.nextLine();
        int[][] distanceMatrix = parseMatrix(matrixInput, numCities);

        // Find the shortest path using the Nearest Neighbor algorithm
        int[] path = nearestNeighborTSP(distanceMatrix);

        // Calculate the total distance of the path
        int totalDistance = calculateTotalDistance(path, distanceMatrix);

        // Print the path and total distance
        System.out.print("The path is: ");
        for (int city : path) {
            System.out.print(city + " -> ");
        }
        System.out.println(path[0]); // To complete the cycle
        System.out.println("The total distance is: " + totalDistance);
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

    private static int[] nearestNeighborTSP(int[][] distanceMatrix) {
        int numCities = distanceMatrix.length;
        boolean[] visited = new boolean[numCities];
        int[] path = new int[numCities];
        int currentCity = 0;

        for (int i = 0; i < numCities; i++) {
            visited[currentCity] = true;
            path[i] = currentCity;

            int nextCity = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int j = 0; j < numCities; j++) {
                if (!visited[j] && distanceMatrix[currentCity][j] < minDistance) {
                    nextCity = j;
                    minDistance = distanceMatrix[currentCity][j];
                }
            }

            currentCity = nextCity;
        }

        return path;
    }

    private static int calculateTotalDistance(int[] path, int[][] distanceMatrix) {
        int totalDistance = 0;
        for (int i = 0; i < path.length - 1; i++) {
            totalDistance += distanceMatrix[path[i]][path[i + 1]];
        }
        totalDistance += distanceMatrix[path[path.length - 1]][path[0]]; // Return to the starting city
        return totalDistance;
    }
}
