import java.util.Scanner;

public class PrimMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int numVertices = scanner.nextInt();

        PrimMST graph = new PrimMST(numVertices);

        System.out.println("Enter the number of edges:");
        int numEdges = scanner.nextInt();

        System.out.println("Enter the edges (src dest weight):");
        for (int i = 0; i < numEdges; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(src, dest, weight);
        }

        graph.findMST();
    }
}

