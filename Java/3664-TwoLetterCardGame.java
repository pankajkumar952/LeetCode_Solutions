class Solution {

    private int maxPairs(int[] freq, int remove) {
        int total = 0;
        int max = 0;

        for (int count : freq) {
            total += count;
            max = Math.max(max, count);
        }

        int remaining = total - remove;

        if (remaining <= 0) {
            return 0;
        }

        int low = 0, high = max;

        while (low < high) {
            int mid = low + (high - low) / 2;
            int capacity = 0;

            for (int count : freq) {
                capacity += Math.min(count, mid);
            }

            if (capacity >= remaining) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return Math.min(remaining / 2, remaining - low);
    }

    public int score(String[] cards, char x) {
        int[] left = new int[10];
        int[] right = new int[10];

        int special = 0;
        int totalLeft = 0;
        int totalRight = 0;

        for (String card : cards) {
            char a = card.charAt(0);
            char b = card.charAt(1);

            if (a == x && b == x) {
                special++;
            } else if (a == x) {
                left[b - 'a']++;
                totalLeft++;
            } else if (b == x) {
                right[a - 'a']++;
                totalRight++;
            }
        }

        int use = Math.min(special, totalLeft + totalRight);
        int ans = 0;

        for (int takeLeft = 0; takeLeft <= use; takeLeft++) {
            int takeRight = use - takeLeft;

            if (takeLeft > totalLeft || takeRight > totalRight) {
                continue;
            }

            int pairsLeft = maxPairs(left, takeLeft);
            int pairsRight = maxPairs(right, takeRight);

            ans = Math.max(ans,
                    use + pairsLeft + pairsRight);
        }

        return ans;
    }
}
