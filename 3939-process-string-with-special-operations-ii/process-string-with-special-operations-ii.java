class Solution {
    public char processStr(String s, long k) {
        int n = s.length();

        long[] lenAfter = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            long len = lenAfter[i];

            if (ch >= 'a' && ch <= 'z') {
                len++;
            } else if (ch == '*') {
                if (len > 0) len--;
            } else if (ch == '#') {
                len = Math.min((long)1e18, len * 2);
            }

            lenAfter[i + 1] = len;
        }

        long finalLen = lenAfter[n];

        if (k < 0 || k >= finalLen) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            long curLen = lenAfter[i + 1];
            long prevLen = lenAfter[i];

            if (ch >= 'a' && ch <= 'z') {

                if (k == curLen - 1) {
                    return ch;
                }

            } else if (ch == '#') {

                if (prevLen > 0 && k >= prevLen) {
                    k -= prevLen;
                }

            } else if (ch == '%') {

                k = curLen - 1 - k;

            }
        }

        return '.';
    }
}