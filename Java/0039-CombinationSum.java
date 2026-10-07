import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();

        backtrack(candidates, target, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void backtrack(int[] candidates,
                            int target,
                            int index,
                            List<Integer> current,
                            List<List<Integer>> ans) {

        // Target reached
        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }

        // Try every candidate from current index
        for (int i = index; i < candidates.length; i++) {

            // Candidate is too large
            if (candidates[i] > target) {
                continue;
            }

            // Choose
            current.add(candidates[i]);

            // Same number can be used again
            backtrack(
                candidates,
                target - candidates[i],
                i,
                current,
                ans
            );

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}
