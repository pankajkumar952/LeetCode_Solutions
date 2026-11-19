class Solution {
    long dp[][];
    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        dp = new long[n][2];
        for(int i=0;i<n;i++)
            {
                Arrays.fill(dp[i],Long.MIN_VALUE);
            }
        return nums[0] + recur(nums,n,x,1,nums[0]%2);
    }
    public long recur(int nums[],int n,int x,int idx,int parity)
    {
        if(idx == n)return 0;

        if(dp[idx][parity]!=Long.MIN_VALUE)return dp[idx][parity];
        
        long nopick = recur(nums,n,x,idx+1,parity);
        long pick;
        if(nums[idx]%2==parity)
        {
            pick = nums[idx]+recur(nums,n,x,idx+1,parity);
        }
        else
        {
            pick = nums[idx] - x + recur(nums,n,x,idx+1,nums[idx]%2);
        }

        return dp[idx][parity]=(long)Math.max(pick,nopick);
    }
}
