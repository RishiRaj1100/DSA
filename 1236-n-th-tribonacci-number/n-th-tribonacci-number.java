class Solution {

    public int trib(int n, int[] dp){
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if(dp[n]!=0) return dp[n];
        int ans = trib(n-1,dp) + trib(n-2,dp) + trib(n-3,dp);
        dp[n] = ans;
        return ans;
    }
    public int tribonacci(int n) {
        int dp[] = new int[n+1];
        return trib(n,dp);
        
    }
}