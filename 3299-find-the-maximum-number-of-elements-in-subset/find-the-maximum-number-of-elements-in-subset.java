class Solution {
    public int maximumLength(int[] nums) {
           HashMap<Long, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put((long) num, map.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        // Handle 1 separately
        if (map.containsKey(1L)) {
            int cnt = map.get(1L);
            ans = Math.max(ans, (cnt % 2 == 1) ? cnt : cnt - 1);
        }

        for (long start : map.keySet()) {

            if (start == 1)
                continue;

            long cur = start;
            int len = 1;

            while (true) {

                if (map.getOrDefault(cur, 0) < 2)
                    break;

                if (cur > 1000000000L)
                    break;

                long next = cur * cur;

                if (!map.containsKey(next))
                    break;

                len += 2;
                cur = next;
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}