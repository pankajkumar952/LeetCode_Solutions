class Solution {
    public int minimumBuckets(String hamsters) {

        char[] arr = hamsters.toCharArray();
        int n = arr.length;
        int buckets = 0;

        for (int i = 0; i < n; i++) {

            if (arr[i] != 'H') {
                continue;
            }

            // Already fed by left bucket
            if (i > 0 && arr[i - 1] == 'B') {
                continue;
            }

            // Place bucket on right if possible
            if (i + 1 < n && arr[i + 1] == '.') {
                arr[i + 1] = 'B';
                buckets++;
            }

            // Otherwise place bucket on left
            else if (i > 0 && arr[i - 1] == '.') {
                arr[i - 1] = 'B';
                buckets++;
            }

            // No available position
            else {
                return -1;
            }
        }

        return buckets;
    }
}
