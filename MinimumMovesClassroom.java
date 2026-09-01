import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0, litterCount = 0;

        int[][] id = new int[m][n];
        for (int[] row : id) Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (classroom[i].charAt(j) == 'S') {
                    sr = i;
                    sc = j;
                }

                if (classroom[i].charAt(j) == 'L') {
                    id[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) return 0;

        int fullMask = (1 << litterCount) - 1;

        // best[pos][mask] = maximum energy reached at this state
        int[][] best = new int[m * n][1 << litterCount];

        for (int[] row : best) {
            Arrays.fill(row, -1);
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();

        int startPos = sr * n + sc;
        best[startPos][0] = energy;

        q.offer(new int[]{sr, sc, 0, energy, 0});

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int en = cur[3];
            int moves = cur[4];

            if (mask == fullMask) {
                return moves;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                char cell = classroom[nr].charAt(nc);

                if (cell == 'X') {
                    continue;
                }

                if (en == 0) {
                    continue;
                }

                int newEnergy = en - 1;

                // Reset energy on R
                if (cell == 'R') {
                    newEnergy = energy;
                }

                int newMask = mask;

                // Collect litter
                if (cell == 'L') {
                    int idx = id[nr][nc];
                    newMask |= (1 << idx);
                }

                int pos = nr * n + nc;

                // If we've already reached this state with
                // equal or greater energy, this path is useless.
                if (best[pos][newMask] >= newEnergy) {
                    continue;
                }

                best[pos][newMask] = newEnergy;

                q.offer(new int[]{
                    nr, nc, newMask, newEnergy, moves + 1
                });
            }
        }

        return -1;
    }
}
