
class Solution {
    public long shadowPairs(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        int m = 0;
        for (int x : sorted) {
            if (m == 0 || sorted[m - 1] != x) {
                sorted[m++] = x;
            }
        }

        int[] bit = new int[m + 1];
        int[] stack = new int[n];
        int top = 0;
        long ans = 0;

        for (int x : nums) {
            int rank = lowerBound(sorted, m, x);

            int count = 0;
            for (int p = rank; p > 0; p -= p & -p) {
                count += bit[p];
            }

            ans += count;

            while (top > 0 && stack[top - 1] > x) {
                int r = lowerBound(sorted, m, stack[--top]) + 1;

                for (int p = r; p <= m; p += p & -p) {
                    bit[p]--;
                }
            }

            stack[top++] = x;

            for (int p = rank + 1; p <= m; p += p & -p) {
                bit[p]++;
            }
        }

        return ans;
    }

    private int lowerBound(int[] arr, int n, int target) {
        int l = 0, r = n;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
