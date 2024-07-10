public class TravelingSalesman {
    private int shortestPath = Integer.MAX_VALUE;

    public void runTravelingSalesman(int[][] graph) {
        int n = graph.length;

        long startTime = System.currentTimeMillis();
        int result = findShortestPath(graph, n);
        long endTime = System.currentTimeMillis();

        System.out.println("Shortest path length: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    public int findShortestPath(int[][] graph, int n) {
        boolean[] visited = new boolean[n];
        visited[0] = true;
        findPath(graph, visited, 0, n, 1, 0);
        return shortestPath;
    }

    private void findPath(int[][] graph, boolean[] visited, int currPos, int n, int count, int cost) {
        if (count == n && graph[currPos][0] > 0) {
            shortestPath = Math.min(shortestPath, cost + graph[currPos][0]);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[currPos][i] > 0) {
                visited[i] = true;
                findPath(graph, visited, i, n, count + 1, cost + graph[currPos][i]);
                visited[i] = false;
            }
        }
    }
}
