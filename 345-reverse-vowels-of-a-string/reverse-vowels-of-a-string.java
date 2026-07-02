class Solution {
    public String reverseVowels(String s) {
        char arr[] = s.toCharArray();
        int i=0, j= arr.length-1;

        while(i<j){
            if(i<j && !"aeiouAEIOU".contains(arr[i] + "")) i++;
            else if(i<j && !"aeiouAEIOU".contains(arr[j] + "")) j--;
            else{
                char temp = arr[i];
                arr[i]=arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        String result = new String(arr);
        return result;
    }
}