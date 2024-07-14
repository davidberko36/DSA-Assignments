import java.util.*;

public class KruskalMST {
    public List<Edge> getMinimumSpanningTree(Graph graph) {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(graph.getEdges());
        UnionFind unionFind = new UnionFind(graph.vertices);

        for (Edge edge : graph.getEdges()) {
            int root1 = unionFind.find(edge.source);
            int root2 = unionFind.find(edge.destination);
            if (root1 != root2) {
                mst.add(edge);
                unionFind.union(root1, root2);
            }
        }
        return mst;
    }
}