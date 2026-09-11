import java.util.HashMap;
import java.util.Map;

class Solution {
    public int totalNumbers(int[] digits) {
        // Step 1: Count frequency of each available digit
        int[] count = new int[10];
        for (int digit : digits) {
            count[digit]++;
        }

        int totalCount = 0;

        // Step 2: Iterate through all valid 3-digit even numbers (100 to 998)
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;       // Hundreds digit
            int d2 = (num / 10) % 10; // Tens digit
            int d3 = num % 10;        // Units digit

            // Count frequency required for the current number
            int[] currentCount = new int[10];
            currentCount[d1]++;
            currentCount[d2]++;
            currentCount[d3]++;

            // Step 3: Check if available digits satisfy the required count
            if (currentCount[d1] <= count[d1] && 
                currentCount[d2] <= count[d2] && 
                currentCount[d3] <= count[d3]) {
                totalCount++;
            }
        }

        return totalCount;
    }
}
