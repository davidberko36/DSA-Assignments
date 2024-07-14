import java.util.*;

public class Graph {
    int vertices;
    List<Edge> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }
}