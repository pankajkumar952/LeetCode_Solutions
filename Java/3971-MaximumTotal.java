class Solution {

    long mod = 1_000_000_007 ;

    public int maxTotalValue(int[] value, int[] decay, int m) {

        int n = value.length ;

        long max = 0 ;
        for(int val : value) max = Math.max(max, val) ;

        if(findCount(value, decay, 1, n) < m) return (int)(findSum(value, decay, 1, n)) ;

        long low = 1 ;
        long high = max ;
        long count = 0 ;
        long G = 1 ;


        while(low <= high)
        {
            long mid = (high + low) / 2 ;

            long tempCount = findCount(value, decay, mid, n) ;

            if( tempCount >= m)
            {
                low = mid + 1 ;
                count = tempCount ;
                G = mid ;
            }
            else
            {
                high = mid - 1 ;
            } 
        }

        long sum = findSum(value, decay, G, n) ;

        long extra = count - m ;
        long res = (sum - (extra % mod) * (G % mod)) % mod ;
        if(res < 0) res += mod ;
        return (int) res ;
    }

    long findCount(int[] value, int[] decay, long G, int n)
    {
        long count = 0 ;

        for(int i = 0 ; i < n ; i ++)
        {
            int a = value[i] ;
            int d = decay[i] ;

            if(a < G) continue ;

            long k = (a - G) / d ;

            count += k + 1 ;

        }

        return count ;
    }

    long findSum(int[] value, int[] decay, long G, int n)
    {
        long sum = 0 ;

        for(int i = 0 ; i < n ; i ++)
        {
            int a = value[i] ;
            int d = decay[i] ;

            if(a < G) continue ;

            long k = (a - G) / d ;

            sum += (((k + 1) * (2 * a - d * k)) / 2) % mod ;
        }

        return sum % mod ;        
    }

}
