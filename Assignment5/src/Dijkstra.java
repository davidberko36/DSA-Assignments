import java.util.*;

public class Dijkstra {
    static class Node {
        private String name;
        private List<Node> shortestPath = new LinkedList<>();
        private Integer distance = Integer.MAX_VALUE;
        private Map<Node, Integer> adjacentNodes = new HashMap<>();

        public Node(String name) {
            this.name = name;
        }

        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public List<Node> getShortestPath() {
            return shortestPath;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        public void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }
    }

    static class Graph {
        private Set<Node> nodes = new HashSet<>();

        public void addNode(Node nodeA) {
            nodes.add(nodeA);
        }

        public Set<Node> getNodes() {
            return nodes;
        }
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create graph
        Graph graph = new Graph();

        System.out.println("Enter number of nodes:");
        int nodeCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Map<String, Node> nodes = new HashMap<>();

        // Create nodes
        for (int i = 0; i < nodeCount; i++) {
            System.out.println("Enter node name:");
            String nodeName = scanner.nextLine();
            Node node = new Node(nodeName);
            nodes.put(nodeName, node);
            graph.addNode(node);
        }

        System.out.println("Enter number of edges:");
        int edgeCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Create edges
        for (int i = 0; i < edgeCount; i++) {
            System.out.println("Enter edge (format: source destination distance):");
            String edge = scanner.nextLine();
            String[] edgeData = edge.split(" ");
            String sourceName = edgeData[0];
            String destinationName = edgeData[1];
            int distance = Integer.parseInt(edgeData[2]);

            Node sourceNode = nodes.get(sourceName);
            Node destinationNode = nodes.get(destinationName);
            sourceNode.addDestination(destinationNode, distance);
        }

        System.out.println("Enter source node:");
        String sourceName = scanner.nextLine();
        Node sourceNode = nodes.get(sourceName);

        // Calculate shortest paths from source
        graph = calculateShortestPathFromSource(graph, sourceNode);

        // Print shortest paths
        System.out.println("Shortest paths from node " + sourceName + ":");
        for (Node node : graph.getNodes()) {
            System.out.print("To node " + node.getName() + ": ");
            for (Node pathNode : node.getShortestPath()) {
                System.out.print(pathNode.getName() + " -> ");
            }
            System.out.println(node.getName() + " (Distance: " + node.getDistance() + ")");
        }

        scanner.close();
    }
}