class Solution {
    List<int[]>[] graph;
    boolean[] visited;
    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        visited = new boolean[n + 1];
        dfs(1);

        return ans;
    }

    private void dfs(int node) {
        visited[node] = true;

        for (int[] edge : graph[node]) {
            ans = Math.min(ans, edge[1]);

            if (!visited[edge[0]]) {
                dfs(edge[0]);
            }
        }
    }
}