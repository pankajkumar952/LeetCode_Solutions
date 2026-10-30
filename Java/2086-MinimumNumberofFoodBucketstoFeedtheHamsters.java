class Solution {
    public int minimumBuckets(String hamsters) {

        char[] arr = hamsters.toCharArray();
        int n = arr.length;
        int buckets = 0;

        for (int i = 0; i < n; i++) {

            if (arr[i] != 'H') {
                continue;
            }
            if (i > 0 && arr[i - 1] == 'B') {
                continue;
            }

            if (i + 1 < n && arr[i + 1] == '.') {
                arr[i + 1] = 'B';
                buckets++;
            }

            else if (i > 0 && arr[i - 1] == '.') {
                arr[i - 1] = 'B';
                buckets++;
            }

            else {
                return -1;
            }
        }

        return buckets;
    }
}
