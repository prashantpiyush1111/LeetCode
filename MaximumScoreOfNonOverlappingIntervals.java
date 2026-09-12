class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;

        Arrays.sort(order, (a, b) -> {
            int c = Integer.compare(intervals.get(a).get(0), intervals.get(b).get(0));
            if (c != 0) return c;
            return Integer.compare(intervals.get(a).get(1), intervals.get(b).get(1));
        });

        int[] l = new int[n], r = new int[n], w = new int[n], id = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(order[i]);
            l[i] = x.get(0);
            r[i] = x.get(1);
            w[i] = x.get(2);
            id[i] = order[i];
        }

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int lo = i + 1, hi = n;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (l[mid] > r[i]) hi = mid;
                else lo = mid + 1;
            }
            next[i] = lo;
        }

        long[][] dp = new long[n + 1][5];
        int[][][] best = new int[n + 1][5][];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                long skipScore = dp[i + 1][k];
                long takeScore = w[i] + dp[next[i]][k - 1];

                if (takeScore > skipScore) {
                    dp[i][k] = takeScore;
                    best[i][k] = add(best[next[i]][k - 1], id[i]);
                } else if (takeScore < skipScore) {
                    dp[i][k] = skipScore;
                    best[i][k] = best[i + 1][k];
                } else {
                    dp[i][k] = takeScore;
                    int[] take = add(best[next[i]][k - 1], id[i]);
                    int[] skip = best[i + 1][k];
                    best[i][k] = smaller(take, skip);
                }
            }
        }

        return best[0][4] == null ? new int[0] : best[0][4];
    }

    private int[] add(int[] a, int x) {
        int n = a == null ? 0 : a.length;
        int[] res = new int[Math.min(4, n + 1)];
        int i = 0, j = 0;
        while (i < n && j < res.length) {
            if (a[i] < x) res[j++] = a[i++];
            else break;
        }
        if (j < res.length) res[j++] = x;
        while (i < n && j < res.length) res[j++] = a[i++];
        return res;
    }

    private int[] smaller(int[] a, int[] b) {
        if (a == null) return b;
        if (b == null) return a;
        int n = Math.min(a.length, b.length);
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) return a[i] < b[i] ? a : b;
        }
        return a.length <= b.length ? a : b;
    }
}