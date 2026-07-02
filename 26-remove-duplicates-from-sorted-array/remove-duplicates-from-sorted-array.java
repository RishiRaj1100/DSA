class Solution {
   public int removeDuplicates(int[] nums) {
    Set<Integer> set = new TreeSet<>();

    for (int arr : nums) {
        set.add(arr);
    }
     int i = 0;
    for (int num : set) {
        nums[i++] = num;
    }
    System.out.println(set);
    return set.size();
}
}