import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();

        // Create adjacency list
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] count = new int[2];
                // count[0] = number of vertices
                // count[1] = sum of degrees

                dfs(i, adj, visited, count);

                int vertices = count[0];
                int degreeSum = count[1];

                // Complete component condition
                if (degreeSum == vertices * (vertices - 1)) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private void dfs(int node,
                     List<List<Integer>> adj,
                     boolean[] visited,
                     int[] count) {

        visited[node] = true;

        // Count vertex
        count[0]++;

        // Add degree of current vertex
        count[1] += adj.get(node).size();

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, count);
            }
        }
    }
}