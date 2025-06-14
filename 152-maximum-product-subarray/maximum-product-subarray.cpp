class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int maxProd = nums[0], currMax = nums[0], currMin = nums[0];

    for (int i = 1; i < nums.size(); i++) {
        int temp = currMax;
        currMax = max({nums[i], currMax * nums[i], currMin * nums[i]});
        currMin = min({nums[i], temp * nums[i], currMin * nums[i]});
        maxProd = max(maxProd, currMax);
    }

    return maxProd;
    }
};