import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1;
        int startC = -1;

        ArrayList<int[]> litter = new ArrayList<>();

        // Find S and L
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();

        // No litter
        if (k == 0) {
            return 0;
        }

        int fullMask = (1 << k) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << k];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(
            startR,
            startC,
            energy,
            0
        ));

        visited[startR][startC][energy][0] = true;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                State curr = queue.poll();

                int r = curr.r;
                int c = curr.c;
                int e = curr.energy;
                int mask = curr.mask;

                // All litter cleaned
                if (mask == fullMask) {
                    return moves;
                }

                // No energy
                if (e == 0) {
                    continue;
                }

                // Four directions
                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Out of bounds
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    // Wall
                    if (cell == 'X') {
                        continue;
                    }

                    int newEnergy = e - 1;

                    // Recharge
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    int newMask = mask;

                    // Check litter
                    for (int i = 0; i < k; i++) {

                        int[] pos = litter.get(i);

                        if (pos[0] == nr && pos[1] == nc) {

                            newMask |= (1 << i);
                            break;
                        }
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.offer(new State(
                            nr,
                            nc,
                            newEnergy,
                            newMask
                        ));
                    }
                }
            }

            moves++;
        }

        return -1;
    }


    static class State {

        int r;
        int c;
        int energy;
        int mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }
}