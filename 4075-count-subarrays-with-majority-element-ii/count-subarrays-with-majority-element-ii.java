import java.util.*;

class Solution {

    class BIT {
        int[] tree;

        BIT(int n) {
            tree = new int[n + 2];
        }

        void update(int idx) {
            while (idx < tree.length) {
                tree[idx]++;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        // Prefix sums after transforming:
        // target -> +1
        // others -> -1
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        // Coordinate Compression
        int[] sorted = prefix.clone();
        Arrays.sort(sorted);

        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int x : sorted) {
            if (!map.containsKey(x)) {
                map.put(x, rank++);
            }
        }

        BIT bit = new BIT(rank);

        long ans = 0;

        for (int x : prefix) {
            int idx = map.get(x);

            // Count previous prefix sums strictly smaller
            ans += bit.query(idx - 1);

            // Insert current prefix sum
            bit.update(idx);
        }

        return ans;
    }
}