class Solution {
public:
    int maximumProduct(vector<int>& nums) {
       sort(nums.begin(), nums.end());
    int n = nums.size();
    int product1 = nums[n-1] * nums[n-2] * nums[n-3];
    int product2 = nums[0] * nums[1] * nums[n-1];
    int maxProduct = max(product1, product2);
    return maxProduct;
    }
};