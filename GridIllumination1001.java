import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Set<Long> activeLamps = new HashSet<>();
        Map<Long, Integer> rows = new HashMap<>();
        Map<Long, Integer> cols = new HashMap<>();
        Map<Long, Integer> diagonals = new HashMap<>();
        Map<Long, Integer> antiDiagonals = new HashMap<>();

        for (int[] lamp : lamps) {
            int row = lamp[0];
            int col = lamp[1];
            long key = getKey(row, col);

            if (activeLamps.add(key)) {
                rows.put((long) row, rows.getOrDefault((long) row, 0) + 1);
                cols.put((long) col, cols.getOrDefault((long) col, 0) + 1);
                diagonals.put((long) row - col,
                        diagonals.getOrDefault((long) row - col, 0) + 1);
                antiDiagonals.put((long) row + col,
                        antiDiagonals.getOrDefault((long) row + col, 0) + 1);
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int row = queries[i][0];
            int col = queries[i][1];

            if (rows.getOrDefault((long) row, 0) > 0
                    || cols.getOrDefault((long) col, 0) > 0
                    || diagonals.getOrDefault((long) row - col, 0) > 0
                    || antiDiagonals.getOrDefault((long) row + col, 0) > 0) {
                answer[i] = 1;
            }

            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int nr = row + dr;
                    int nc = col + dc;

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                        continue;
                    }

                    long key = getKey(nr, nc);

                    if (activeLamps.remove(key)) {
                        decrement(rows, nr);
                        decrement(cols, nc);
                        decrement(diagonals, (long) nr - nc);
                        decrement(antiDiagonals, (long) nr + nc);
                    }
                }
            }
        }

        return answer;
    }

    private long getKey(int row, int col) {
        return ((long) row << 32) | (col & 0xffffffffL);
    }

    private void decrement(Map<Long, Integer> counts, long key) {
        int count = counts.get(key);
        if (count == 1) {
            counts.remove(key);
        } else {
            counts.put(key, count - 1);
        }
    }
}
