import java.util.*;

public class KruskalMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph(6);
        
        // Add edges (source, destination, weight)
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 0, 4);
        graph.addEdge(2, 0, 4);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 5, 2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 2, 3);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 2, 4);
        graph.addEdge(4, 3, 3);
        graph.addEdge(5, 2, 2);
        graph.addEdge(5, 4, 3);
        
        KruskalMST kruskal = new KruskalMST();
        List<Edge> mst = kruskal.getMinimumSpanningTree(graph);
        
        System.out.println("Minimum Spanning Tree using Kruskal's Algorithm:");
        for (Edge edge : mst) {
            System.out.println("Edge: (" + edge.source + ", " + edge.destination + ") - Weight: " + edge.weight);
        }
    }
}