class Solution {
public:
    int myAtoi(string s) {
        int i = 0, n = s.size();

        // Step 1: Skip leading spaces
        while (i < n && s[i] == ' ')
            i++;

        // Step 2: Check sign
        int sign = 1;
        if (i < n && (s[i] == '+' || s[i] == '-')) {
            if (s[i] == '-') sign = -1;
            i++;
        }

        long long num = 0;

        // Step 3: Skip leading zeros
        while (i < n && s[i] == '0')
            i++;

        // Step 3: Convert digits
        while (i < n && isdigit(s[i])) {
            num = num * 10 + (s[i] - '0');

            // Step 4: Clamp if overflow
            if (sign == 1 && num > INT_MAX) return INT_MAX;
            if (sign == -1 && -num < INT_MIN) return INT_MIN;

            i++;
        }

        return (int)(num * sign);
    }
};
