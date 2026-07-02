class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int startHealth = health - grid.get(0).get(0);

        if (startHealth <= 0) return false;

        int[][] best = new int[m][n];
        for (int[] row : best) {
            Arrays.fill(row, -1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]
        );

        pq.offer(new int[]{startHealth, 0, 0});
        best[0][0] = startHealth;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int curHealth = cur[0];
            int r = cur[1];
            int c = cur[2];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            // Ignore outdated state
            if (curHealth < best[r][c]) continue;

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                int newHealth = curHealth - grid.get(nr).get(nc);

                if (newHealth > 0 && newHealth > best[nr][nc]) {
                    best[nr][nc] = newHealth;
                    pq.offer(new int[]{newHealth, nr, nc});
                }
            }
        }

        return false;
    }
}