import java.util.*;

public class PrimMST {
    private int numVertices;
    private List<Edge>[] adjacencyList;

    public PrimMST(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new List[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjacencyList[src].add(new Edge(dest, weight));
        adjacencyList[dest].add(new Edge(src, weight));
    }

    public void findMST() {
        boolean[] visited = new boolean[numVertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int node = edge.dest;

            if (visited[node]) continue;
            visited[node] = true;
            totalWeight += edge.weight;
            mst.add(edge);

            for (Edge adjEdge : adjacencyList[node]) {
                if (!visited[adjEdge.dest]) {
                    pq.add(adjEdge);
                }
            }
        }

        System.out.println("Edges in the MST:");
        for (Edge e : mst) {
            System.out.println("Node: " + e.dest + " Weight: " + e.weight);
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    static class Edge {
        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}
