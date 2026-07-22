class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        // ---- Run-length encode s ----
        List<Integer> startL = new ArrayList<>(), endL = new ArrayList<>();
        List<Character> typeL = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            startL.add(i); endL.add(j - 1); typeL.add(s.charAt(i));
            i = j;
        }
        int m = startL.size();
        int[] rs = new int[m], re = new int[m], rlen = new int[m];
        char[] rtype = new char[m];
        for (int k = 0; k < m; k++) {
            rs[k] = startL.get(k);
            re[k] = endL.get(k);
            rtype[k] = typeL.get(k);
            rlen[k] = re[k] - rs[k] + 1;
        }

        int totalOnes = 0;
        for (int k = 0; k < m; k++) if (rtype[k] == '1') totalOnes += rlen[k];

        final int INF = Integer.MAX_VALUE / 4;
        final int NEG = Integer.MIN_VALUE / 4;

        int[] onesLen = new int[m];
        int[] zerosLen = new int[m];
        int[] adjSum = new int[m];

        for (int k = 0; k < m; k++) {
            onesLen[k] = (rtype[k] == '1') ? rlen[k] : INF;
            zerosLen[k] = (rtype[k] == '0') ? rlen[k] : NEG;
            if (rtype[k] == '1' && k > 0 && k < m - 1) {
                adjSum[k] = rlen[k - 1] + rlen[k + 1];
            } else {
                adjSum[k] = NEG;
            }
        }

        SparseMin minOnes = new SparseMin(onesLen);
        SparseMax maxZeros = new SparseMax(zerosLen);
        SparseMax maxAdj = new SparseMax(adjSum);

        int qn = queries.length;
        List<Integer> ans = new ArrayList<>(qn);

        for (int q = 0; q < qn; q++) {
            int l = queries[q][0], r = queries[q][1];
            int idxL = findRun(rs, l, m);
            int idxR = findRun(rs, r, m);

            if (idxL == idxR) {
                ans.add(totalOnes);
                continue;
            }

            int tL = re[idxL] - l + 1;
            int tR = r - rs[idxR] + 1;

            int A = idxL + 1;
            int B = idxR - 1;

            if (A > B) {
                ans.add(totalOnes);
                continue;
            }

            long maxAdjZSum = Long.MIN_VALUE;

            if (A == B) {
                if (rtype[A] == '1') {
                    maxAdjZSum = (long) tL + tR;
                }
            } else {
                if (rtype[A] == '1') {
                    long cand = (long) tL + rlen[A + 1];
                    maxAdjZSum = Math.max(maxAdjZSum, cand);
                }
                if (rtype[B] == '1') {
                    long cand = (long) rlen[B - 1] + tR;
                    maxAdjZSum = Math.max(maxAdjZSum, cand);
                }
                if (A + 1 <= B - 1) {
                    int midMax = maxAdj.query(A + 1, B - 1);
                    if (midMax > NEG) {
                        maxAdjZSum = Math.max(maxAdjZSum, midMax);
                    }
                }
            }

            if (maxAdjZSum == Long.MIN_VALUE) {
                ans.add(totalOnes);
                continue;
            }

            int gmz = Integer.MIN_VALUE;
            if (rtype[idxL] == '0') gmz = Math.max(gmz, tL);
            if (rtype[idxR] == '0') gmz = Math.max(gmz, tR);
            if (idxL + 1 <= idxR - 1) {
                gmz = Math.max(gmz, maxZeros.query(idxL + 1, idxR - 1));
            }

            int minOne = minOnes.query(A, B);

            long maxGain = Math.max(maxAdjZSum, (long) gmz - minOne);
            ans.add((int) (totalOnes + maxGain));
        }

        return ans;
    }

    private int findRun(int[] rs, int p, int m) {
        int lo = 0, hi = m - 1, res = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (rs[mid] <= p) { res = mid; lo = mid + 1; }
            else hi = mid - 1;
        }
        return res;
    }

    static class SparseMin {
        int[][] table;
        int[] log;
        SparseMin(int[] arr) {
            int n = arr.length;
            log = new int[n + 1];
            for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;
            int K = log[n] + 1;
            table = new int[K][n];
            table[0] = arr.clone();
            for (int k = 1; k < K; k++) {
                for (int j = 0; j + (1 << k) <= n; j++) {
                    table[k][j] = Math.min(table[k - 1][j], table[k - 1][j + (1 << (k - 1))]);
                }
            }
        }
        int query(int l, int r) {
            int k = log[r - l + 1];
            return Math.min(table[k][l], table[k][r - (1 << k) + 1]);
        }
    }

    static class SparseMax {
        int[][] table;
        int[] log;
        SparseMax(int[] arr) {
            int n = arr.length;
            log = new int[n + 1];
            for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;
            int K = log[n] + 1;
            table = new int[K][n];
            table[0] = arr.clone();
            for (int k = 1; k < K; k++) {
                for (int j = 0; j + (1 << k) <= n; j++) {
                    table[k][j] = Math.max(table[k - 1][j], table[k - 1][j + (1 << (k - 1))]);
                }
            }
        }
        int query(int l, int r) {
            int k = log[r - l + 1];
            return Math.max(table[k][l], table[k][r - (1 << k) + 1]);
        }
    }
}