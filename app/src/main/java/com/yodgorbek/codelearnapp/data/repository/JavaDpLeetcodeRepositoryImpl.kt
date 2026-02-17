package com.yodgorbek.codelearnapp.data.repository

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.model.LessonType

object JavaDpLeetcodeRepositoryImpl {
    val javaDpLessons = listOf(
        // DP Lesson 1: Fibonacci (Memo + Tabulation)
        Lesson(
            id = "dp-1",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Fibonacci Sequence - Memoization and Tabulation",
            content = """🟢 Easy

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Think of Fibonacci like family tree: each person has parents, and to know how many ancestors you have at each generation, you can either remember (memoize) previous calculations or build up from the bottom (tabulation).

When to Use This Pattern:
Use when you see recursion with overlapping subproblems. Look for problems where the same calculation happens multiple times.

Problem Statement:
Given n, calculate the nth Fibonacci number where F(0)=0, F(1)=1, and F(n)=F(n-1)+F(n-2).

Step-by-Step Approach:
1. Start with naive recursion
2. Optimize with memoization (top-down)
3. Further optimize with tabulation (bottom-up)
4. Track only necessary values to save space

Dry Run Example:
For n=5:
F(0)=0, F(1)=1
F(2)=F(1)+F(0)=1
F(3)=F(2)+F(1)=2
F(4)=F(3)+F(2)=3
F(5)=F(4)+F(3)=5

Java Runnable Code:
import java.util.*;

public class Solution {

    // Memoization approach
    public static int fibMemo(int n) {
        int[] memo = new int[n + 1];
        return fibHelper(n, memo);
    }
    
    private static int fibHelper(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];  // Already calculated
        memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
        return memo[n];
    }
    
    // Tabulation approach
    public static int fibTab(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // Space optimized approach
    public static int fibOptimized(int n) {
        if (n <= 1) return n;
        
        int prev2 = 0;  // F(i-2)
        int prev1 = 1;  // F(i-1)
        int curr = 0;   // F(i)
        
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        
        return curr;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Input: n = " + n);
        
        int result = fibOptimized(n);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Naive Recursion: O(2^n) time, O(n) space (recursion stack)
- Memoization: O(n) time, O(n) space
- Tabulation: O(n) time, O(n) space
- Space Optimized: O(n) time, O(1) space

Common Mistakes:
1. Forgetting base cases (n <= 1)
2. Not handling edge cases (n = 0)
3. Using recursion without memoization leading to exponential time

LeetCode Practice Problems:
- Climbing Stairs
- N-th Tribonacci Number
- Fibonacci Number

Mini Challenge:
Modify the fibonacci function to calculate the n-th tribonacci number where T(n) = T(n-1) + T(n-2) + T(n-3) with base cases T(0)=0, T(1)=1, T(2)=1.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
public class Solution {

    // Memoization approach
    public static int fibMemo(int n) {
        int[] memo = new int[n + 1];
        return fibHelper(n, memo);
    }
    
    private static int fibHelper(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];  // Already calculated
        memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
        return memo[n];
    }
    
    // Tabulation approach
    public static int fibTab(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // Space optimized approach
    public static int fibOptimized(int n) {
        if (n <= 1) return n;
        
        int prev2 = 0;  // F(i-2)
        int prev1 = 1;  // F(i-1)
        int curr = 0;   // F(i)
        
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        
        return curr;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Input: n = " + n);
        
        int result = fibOptimized(n);
        System.out.println("Output: " + result);
    }
}"""
        ),
        
        // DP Lesson 2: Climbing Stairs
        Lesson(
            id = "dp-2",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Climbing Stairs",
            content = """🟢 Easy

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Imagine climbing a staircase where you can take 1 or 2 steps at a time. To reach step n, you could have come from step n-1 (take 1 step) or step n-2 (take 2 steps). So the number of ways to reach n is the sum of ways to reach n-1 and n-2.

When to Use This Pattern:
When you have choices at each step and need to count possibilities, or when the problem follows the recurrence relation f(n) = f(n-1) + f(n-2).

Problem Statement:
You are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Step-by-Step Approach:
1. Recognize this is similar to Fibonacci: f(n) = f(n-1) + f(n-2)
2. Base cases: n=1 has 1 way, n=2 has 2 ways
3. Use DP to avoid recalculating subproblems
4. Optimize space by keeping only last two values

Dry Run Example:
n=4:
- f(1) = 1 way: [1]
- f(2) = 2 ways: [1,1] or [2]
- f(3) = f(2) + f(1) = 2 + 1 = 3 ways: [1,1,1], [1,2], [2,1]
- f(4) = f(3) + f(2) = 3 + 2 = 5 ways: [1,1,1,1], [1,1,2], [1,2,1], [2,1,1], [2,2]

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int climbStairs(int n) {
        if (n <= 2) return n;
        
        // Use variables to track only the last two values
        int prev2 = 1;  // ways to reach step 1
        int prev1 = 2;  // ways to reach step 2
        int curr = 0;   // ways to reach current step
        
        for (int i = 3; i <= n; i++) {
            curr = prev1 + prev2;  // f(i) = f(i-1) + f(i-2)
            prev2 = prev1;         // shift window
            prev1 = curr;
        }
        
        return curr;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println("Input: n = " + n);
        
        int result = climbStairs(n);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n) - single loop
- Space: O(1) - only using a few variables

Common Mistakes:
1. Not handling base cases (n=1, n=2)
2. Off-by-one errors in the loop
3. Thinking this is permutation instead of combination

LeetCode Practice Problems:
- Min Cost Climbing Stairs
- House Robber
- Unique Paths

Mini Challenge:
Modify the solution to allow taking 1, 2, or 3 steps at a time. What would be the recurrence relation?

🖥️ Expected Console Output:
Input: n = 4
Output: 5

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int climbStairs(int n) {
        if (n <= 2) return n;
        
        // Use variables to track only the last two values
        int prev2 = 1;  // ways to reach step 1
        int prev1 = 2;  // ways to reach step 2
        int curr = 0;   // ways to reach current step
        
        for (int i = 3; i <= n; i++) {
            curr = prev1 + prev2;  // f(i) = f(i-1) + f(i-2)
            prev2 = prev1;         // shift window
            prev1 = curr;
        }
        
        return curr;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println("Input: n = " + n);
        
        int result = climbStairs(n);
        System.out.println("Output: " + result);
    }
}"""
        ),
        
        // DP Lesson 3: Min Cost Climbing Stairs
        Lesson(
            id = "dp-3",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Min Cost Climbing Stairs",
            content = """🟢 Easy

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Think of it as a game where each step has a cost. You can start from step 0 or 1, and after paying the cost of a step, you can climb either 1 or 2 steps. The goal is to reach the top (beyond the last step) with minimum cost.

When to Use This Pattern:
When you need to minimize/maximize a value and have choices at each step with costs/benefits. Similar to climbing stairs but with costs attached to each position.

Problem Statement:
Given an integer array cost where cost[i] is the cost of the ith step. Once you pay the cost, you can climb one or two steps. You can start from step 0 or 1. Return the minimum cost to reach the top.

Step-by-Step Approach:
1. Define dp[i] = minimum cost to reach step i
2. Recurrence: dp[i] = cost[i] + min(dp[i-1], dp[i-2])
3. Base cases: dp[0] = cost[0], dp[1] = cost[1]
4. Result: min(dp[n-1], dp[n-2]) since you can reach the top from either last or second-last step

Dry Run Example:
cost = [10, 15, 20]
- dp[0] = 10 (pay to start at step 0)
- dp[1] = 15 (pay to start at step 1) 
- dp[2] = 20 + min(10, 15) = 30
- Result = min(30, 15) = 15 (start at step 1, then jump to top)

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n == 0) return 0;
        if (n == 1) return cost[0];
        
        // We only need the last two values
        int prev2 = cost[0];
        int prev1 = cost[1];
        
        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        
        // Can reach top from either last or second last step
        return Math.min(prev1, prev2);
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println("Input: " + Arrays.toString(cost));
        
        int result = minCostClimbingStairs(cost);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n) - single loop through the array
- Space: O(1) - only using a few variables

Common Mistakes:
1. Forgetting that you can start from index 0 or 1
2. Thinking you must land on the last element (you can skip it)
3. Not considering you can reach the top from n-1 or n-2 positions

LeetCode Practice Problems:
- Climbing Stairs
- House Robber
- Best Time to Buy and Sell Stock

Mini Challenge:
Modify the solution if you can now take 1, 2, or 3 steps at a time. What would be the recurrence relation?

🖥️ Expected Console Output:
Input: [10, 15, 20]
Output: 15

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 3,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n == 0) return 0;
        if (n == 1) return cost[0];
        
        // We only need the last two values
        int prev2 = cost[0];
        int prev1 = cost[1];
        
        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        
        // Can reach top from either last or second last step
        return Math.min(prev1, prev2);
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println("Input: " + Arrays.toString(cost));
        
        int result = minCostClimbingStairs(cost);
        System.out.println("Output: " + result);
    }
}"""
        ),
        
        // DP Lesson 4: House Robber
        Lesson(
            id = "dp-4",
            courseId = "java-dp-patterns",
            language = "java",
            title = "House Robber",
            content = """🟢 Easy

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
You're a robber planning to rob houses along a street. Each house has a certain amount of money. The constraint is that you cannot rob two adjacent houses (as it would trigger an alarm). The goal is to maximize the total money robbed.

When to Use This Pattern:
When you have to make a choice at each position (rob or not rob) and there's a constraint that prevents taking adjacent elements. Also applies to any scenario where you need to select non-adjacent elements to maximize/minimize a value.

Problem Statement:
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Step-by-Step Approach:
1. At each house, you have two choices: rob it or skip it
2. If you rob house i, you get nums[i] + max money from houses up to i-2
3. If you skip house i, you get max money from houses up to i-1
4. Take the maximum of these two choices
5. Recurrence: dp[i] = max(dp[i-1], nums[i] + dp[i-2])

Dry Run Example:
nums = [2, 7, 9, 3, 1]
- dp[0] = 2 (rob house 0)
- dp[1] = max(2, 7) = 7 (rob house 1, skip house 0)
- dp[2] = max(7, 9+2) = 11 (rob houses 0 and 2: 2+9=11)
- dp[3] = max(11, 3+7) = 11 (best is to skip house 3)
- dp[4] = max(11, 1+11) = 12 (rob house 4: 11+1=12)

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Only need the last two values
        int prev2 = nums[0];                    // max at i-2
        int prev1 = Math.max(nums[0], nums[1]); // max at i-1
        
        for (int i = 2; i < nums.length; i++) {
            int curr = Math.max(prev1, nums[i] + prev2); // max at i
            prev2 = prev1;  // shift window
            prev1 = curr;
        }
        
        return prev1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println("Input: " + Arrays.toString(nums));
        
        int result = rob(nums);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n) - single loop through the array
- Space: O(1) - only using a few variables

Common Mistakes:
1. Forgetting edge cases (empty array, single element)
2. Getting confused about the recurrence relation
3. Not realizing you don't always have to rob alternate houses

LeetCode Practice Problems:
- House Robber II (circular arrangement)
- Maximum Sum of Non-Adjacent Elements
- Delete and Earn

Mini Challenge:
Modify the solution if you need to rob at least 3 houses. How would you approach this?

🖥️ Expected Console Output:
Input: [2, 7, 9, 3, 1]
Output: 12

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Only need the last two values
        int prev2 = nums[0];                    // max at i-2
        int prev1 = Math.max(nums[0], nums[1]); // max at i-1
        
        for (int i = 2; i < nums.length; i++) {
            int curr = Math.max(prev1, nums[i] + prev2); // max at i
            prev2 = prev1;  // shift window
            prev1 = curr;
        }
        
        return prev1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println("Input: " + Arrays.toString(nums));
        
        int result = rob(nums);
        System.out.println("Output: " + result);
    }
}"""
        ),
        
        // DP Lesson 5: House Robber II
        Lesson(
            id = "dp-5",
            courseId = "java-dp-patterns",
            language = "java",
            title = "House Robber II",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Similar to House Robber I, but now the houses are arranged in a circle. This means the first and last houses are adjacent, so you cannot rob both. The key insight is that we can't rob house 0 and house n-1 together, so we solve two separate problems: one excluding the first house, and one excluding the last house.

When to Use This Pattern:
When you have a circular arrangement of elements and need to select non-adjacent elements. The general approach is to break the circle by considering two cases: one where the first element is included (last excluded) and one where the first is excluded (last possibly included).

Problem Statement:
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night. Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Step-by-Step Approach:
1. Since houses are in a circle, first and last are adjacent
2. Either rob the first house (then can't rob the last) or don't rob the first (can rob the last)
3. Solve two subproblems: max profit in [0, n-2] and [1, n-1]
4. Return the maximum of the two results

Dry Run Example:
nums = [2, 3, 2]
Case 1: Rob houses [0, 1] (exclude last) -> [2, 3] -> max is 3
Case 2: Rob houses [1, 2] (exclude first) -> [3, 2] -> max is 3
Result: max(3, 3) = 3

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Max profit if we exclude the last house
        int maxWithoutLast = robHelper(nums, 0, nums.length - 2);
        // Max profit if we exclude the first house  
        int maxWithoutFirst = robHelper(nums, 1, nums.length - 1);
        
        return Math.max(maxWithoutLast, maxWithoutFirst);
    }
    
    private static int robHelper(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        
        int prev2 = nums[start];
        int prev1 = Math.max(nums[start], nums[start + 1]);
        
        for (int i = start + 2; i <= end; i++) {
            int curr = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println("Input: " + Arrays.toString(nums));
        
        int result = rob(nums);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n) - two passes through the array
- Space: O(1) - only using a few variables

Common Mistakes:
1. Not considering the circular nature of the problem
2. Trying to solve it with a single DP approach without breaking the circle
3. Getting confused about the indices in the helper function

LeetCode Practice Problems:
- House Robber
- House Robber III (tree version)
- Cherry Pickup II

Mini Challenge:
Extend the solution to handle a linear chain of houses where you cannot rob 3 consecutive houses.

🖥️ Expected Console Output:
Input: [2, 3, 2]
Output: 3

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 5,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Max profit if we exclude the last house
        int maxWithoutLast = robHelper(nums, 0, nums.length - 2);
        // Max profit if we exclude the first house  
        int maxWithoutFirst = robHelper(nums, 1, nums.length - 1);
        
        return Math.max(maxWithoutLast, maxWithoutFirst);
    }
    
    private static int robHelper(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        
        int prev2 = nums[start];
        int prev1 = Math.max(nums[start], nums[start + 1]);
        
        for (int i = start + 2; i <= end; i++) {
            int curr = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println("Input: " + Arrays.toString(nums));
        
        int result = rob(nums);
        System.out.println("Output: " + result);
    }
}"""
        ),
        
        // DP Lesson 6: Maximum Subarray (Kadane)
        Lesson(
            id = "dp-6",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Maximum Subarray (Kadane's Algorithm)",
            content = """🟢 Easy

Pattern / Topic Name: Dynamic Programming / Greedy

Intuition (Simple Explanation):
Kadane's algorithm is based on the idea that at each position, we decide whether to extend the existing subarray or start a new one. If the sum of the current subarray becomes negative, it's better to start fresh from the next element since a negative sum would only decrease our future sums.

When to Use This Pattern:
When you need to find the contiguous subarray with maximum sum. This is a classic algorithm that appears in many variations like maximum product subarray, minimum subarray, etc.

Problem Statement:
Given an integer array nums, find the subarray with the largest sum, and return its sum.

Step-by-Step Approach:
1. At each position i, we keep track of the maximum sum ending at i
2. Either extend the previous subarray (prev_sum + nums[i]) or start new (nums[i])
3. Keep track of the overall maximum seen so far
4. Recurrence: dp[i] = max(nums[i], dp[i-1] + nums[i])

Dry Run Example:
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
- At i=0: maxEndingHere = -2, maxSoFar = -2
- At i=1: maxEndingHere = max(1, -2+1) = 1, maxSoFar = max(-2, 1) = 1
- At i=2: maxEndingHere = max(-3, 1-3) = -2, maxSoFar = max(1, -2) = 1
- At i=3: maxEndingHere = max(4, -2+4) = 4, maxSoFar = max(1, 4) = 4
- Continue until i=8: maxSoFar = 6 ([4, -1, 2, 1])

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int maxEndingHere = nums[0];  // Max sum ending at current position
        int maxSoFar = nums[0];       // Overall maximum sum
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend the existing subarray or start a new one
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            // Update the overall maximum
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Input: " + Arrays.toString(nums));
        
        int result = maxSubArray(nums);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the array
- Space: O(1) - only using a few variables

Common Mistakes:
1. Not handling the case where all numbers are negative
2. Forgetting to update maxSoFar in each iteration
3. Confusing with the longest increasing subsequence problem

LeetCode Practice Problems:
- Maximum Product Subarray
- Best Time to Buy and Sell Stock
- Maximum Sum Circular Subarray

Mini Challenge:
Modify the algorithm to return the actual subarray (not just the sum) that gives the maximum sum.

🖥️ Expected Console Output:
Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 6

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int maxEndingHere = nums[0];  // Max sum ending at current position
        int maxSoFar = nums[0];       // Overall maximum sum
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend the existing subarray or start a new one
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            // Update the overall maximum
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Input: " + Arrays.toString(nums));
        
        int result = maxSubArray(nums);
        System.out.println("Output: " + result);
    }
}"""
        ),
        
        // DP Lesson 7: Longest Increasing Subsequence (O(n²))
        Lesson(
            id = "dp-7",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Longest Increasing Subsequence (O(n²))",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
For each element, we calculate the length of the longest increasing subsequence ending at that element. This is done by checking all previous elements that are smaller than the current element and taking the maximum LIS length among them, then adding 1.

When to Use This Pattern:
When you need to find the longest subsequence (not necessarily contiguous) where elements are in strictly increasing order. This pattern is applicable to various optimization problems where you need to maintain a specific ordering.

Problem Statement:
Given an integer array nums, return the length of the longest strictly increasing subsequence.

Step-by-Step Approach:
1. Create a dp array where dp[i] represents LIS ending at index i
2. For each element at i, look at all elements j < i where nums[j] < nums[i]
3. Update dp[i] = max(dp[j]) + 1 for all valid j
4. The answer is the maximum value in the dp array

Dry Run Example:
nums = [10, 9, 2, 5, 3, 7, 101, 18]
- dp[0]=1: [10]
- dp[1]=1: [9] 
- dp[2]=1: [2]
- dp[3]=2: [2,5]
- dp[4]=2: [2,3]
- dp[5]=3: [2,3,7] or [2,5,7]
- dp[6]=4: [2,3,7,101] or [2,5,7,101]
- dp[7]=4: [2,3,7,18]
Result: max(dp) = 4

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        
        // Initialize each position with LIS length of 1 (the element itself)
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        int maxLength = 1;
        
        // For each position i
        for (int i = 1; i < n; i++) {
            // Check all previous positions j
            for (int j = 0; j < i; j++) {
                // If nums[j] < nums[i], we can extend the LIS ending at j
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update the overall maximum
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Input: " + Arrays.toString(nums));
        
        int result = lengthOfLIS(nums);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n²) - nested loops
- Space: O(n) - dp array

Common Mistakes:
1. Not checking if nums[j] < nums[i] before extending the sequence
2. Forgetting to initialize dp array with 1s
3. Confusing with longest contiguous increasing subsequence

LeetCode Practice Problems:
- Russian Doll Envelopes
- Maximum Length of Pair Chain
- Number of Longest Increasing Subsequence

Mini Challenge:
Can you modify the solution to return the actual LIS (the sequence itself, not just the length)?

🖥️ Expected Console Output:
Input: [10, 9, 2, 5, 3, 7, 101, 18]
Output: 4

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        
        // Initialize each position with LIS length of 1 (the element itself)
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        int maxLength = 1;
        
        // For each position i
        for (int i = 1; i < n; i++) {
            // Check all previous positions j
            for (int j = 0; j < i; j++) {
                // If nums[j] < nums[i], we can extend the LIS ending at j
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update the overall maximum
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("LIS length for [10,9,2,5,3,7,101,18]: " + lengthOfLIS(nums1));
        
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("LIS length for [0,1,0,3,2,3]: " + lengthOfLIS(nums2));
    }
}"""
        ),
        
        // DP Lesson 8: LIS (Binary Search Optimization)
        Lesson(
            id = "dp-8",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Longest Increasing Subsequence (Binary Search O(n log n))",
            content = """🔴 Hard

Pattern / Topic Name: Dynamic Programming + Binary Search

Intuition (Simple Explanation):
Instead of checking all previous elements for each position, we maintain an auxiliary array that stores the smallest tail element for each possible LIS length. Using binary search, we can efficiently find the correct position to update in this array.

When to Use This Pattern:
When you need O(n log n) solution for LIS. This pattern is useful when dealing with large inputs where O(n²) is too slow. The key insight is maintaining an array where tails[i] is the smallest tail element for all increasing subsequences of length i+1.

Problem Statement:
Given an integer array nums, return the length of the longest strictly increasing subsequence. Optimize to O(n log n) time complexity.

Step-by-Step Approach:
1. Maintain a tails array where tails[i] is smallest tail of LIS of length i+1
2. For each number, find its position in tails using binary search
3. If position equals tails size, append the number (extend LIS)
4. Otherwise, replace the element at found position
5. Length of tails array is the LIS length

Dry Run Example:
nums = [10, 9, 2, 5, 3, 7, 101, 18]
- num=10: tails=[10]
- num=9: tails=[9] (replace 10)
- num=2: tails=[2] (replace 9)
- num=5: tails=[2,5] (append)
- num=3: tails=[2,3] (replace 5)
- num=7: tails=[2,3,7] (append)
- num=101: tails=[2,3,7,101] (append)
- num=18: tails=[2,3,7,18] (replace 101)
Result: length = 4

Java Runnable Code:
import java.util.Arrays;

public class Solution {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] tails = new int[nums.length];
        int size = 0;
        
        for (int num : nums) {
            // Binary search to find the position to insert/replace
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            tails[left] = num;
            
            // If we're appending, increase the size
            if (left == size) {
                size++;
            }
        }
        
        return size;
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("LIS length for [10,9,2,5,3,7,101,18]: " + lengthOfLIS(nums1));
        
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("LIS length for [0,1,0,3,2,3]: " + lengthOfLIS(nums2));
    }
}

Time & Space Complexity:
- Time: O(n log n) - binary search for each element
- Space: O(n) - tails array

Common Mistakes:
1. Getting the binary search condition wrong
2. Forgetting to handle the case when we append to the array
3. Not understanding why this algorithm correctly finds LIS length

LeetCode Practice Problems:
- Russian Doll Envelopes
- Maximum Length of Pair Chain
- Number of Longest Increasing Subsequence

Mini Challenge:
Can you modify this solution to return the actual LIS sequence instead of just its length?

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Master""",
            type = LessonType.THEORY,
            order = 8,
            isCompleted = false,
            codeExample = """
import java.util.Arrays;

public class Solution {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] tails = new int[nums.length];
        int size = 0;
        
        for (int num : nums) {
            // Binary search to find the position to insert/replace
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            tails[left] = num;
            
            // If we're appending, increase the size
            if (left == size) {
                size++;
            }
        }
        
        return size;
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("LIS length for [10,9,2,5,3,7,101,18]: " + lengthOfLIS(nums1));
        
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("LIS length for [0,1,0,3,2,3]: " + lengthOfLIS(nums2));
    }
}"""
        ),
        
        // DP Lesson 9: Coin Change (Min Coins)
        Lesson(
            id = "dp-9",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Coin Change (Minimum Coins)",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
We want to make a certain amount using the minimum number of coins. At each amount value, we try each coin denomination and see if using that coin leads to a better (smaller) solution. This is essentially a "unbounded knapsack" problem where we want to minimize the count rather than maximize a value.

When to Use This Pattern:
When you need to make change for a target amount using given denominations, and want to minimize the number of coins. More generally, when you have unlimited quantities of different items and want to achieve a target value with minimum cost.

Problem Statement:
Given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money, return the fewest number of coins that you need to make up that amount. If that amount cannot be made up by any combination of the coins, return -1.

Step-by-Step Approach:
1. Create dp array where dp[i] = min coins needed for amount i
2. Initialize dp[0] = 0, rest as infinity (or amount+1)
3. For each amount from 1 to target:
   - For each coin, if coin <= current amount:
     - Update dp[i] = min(dp[i], dp[i - coin] + 1)
4. Return dp[amount] if reachable, else -1

Dry Run Example:
coins = [1, 3, 4], amount = 6
- dp[0]=0, dp[1..6]=∞ initially
- Amount 1: min(∞, dp[0]+1)=1 → dp[1]=1
- Amount 2: min(∞, dp[1]+1)=2 → dp[2]=2
- Amount 3: min(∞, dp[2]+1, dp[0]+1)=min(3,1)=1 → dp[3]=1
- Amount 4: min(∞, dp[3]+1, dp[1]+1, dp[0]+1)=min(2,2,1)=1 → dp[4]=1
- Amount 5: min(∞, dp[4]+1, dp[2]+1, dp[1]+1)=min(2,3,2)=2 → dp[5]=2
- Amount 6: min(∞, dp[5]+1, dp[3]+1, dp[2]+1)=min(3,2,3)=2 → dp[6]=2
Result: 2 coins (3+3)

Java Runnable Code:
import java.util.Arrays;

public class Solution {

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        int[] dp = new int[amount + 1];
        // Fill with amount+1 (larger than any possible answer)
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;  // 0 coins needed for amount 0
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins1 = {1, 3, 4};
        int amount1 = 6;
        System.out.println("Min coins for amount " + amount1 + " with " + Arrays.toString(coins1) + ": " + coinChange(coins1, amount1));
        
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Min coins for amount " + amount2 + " with " + Arrays.toString(coins2) + ": " + coinChange(coins2, amount2));
    }
}

Time & Space Complexity:
- Time: O(amount × coins.length) - nested loops
- Space: O(amount) - dp array

Common Mistakes:
1. Not handling the impossible case (return -1)
2. Initializing with Integer.MAX_VALUE which can overflow
3. Forgetting to check if coin <= current amount

LeetCode Practice Problems:
- Coin Change II (Number of Ways)
- Perfect Squares
- Combination Sum

Mini Challenge:
Modify the solution to return the actual combination of coins used to make the target amount.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 9,
            isCompleted = false,
            codeExample = """
import java.util.Arrays;

public class Solution {

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        int[] dp = new int[amount + 1];
        // Fill with amount+1 (larger than any possible answer)
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;  // 0 coins needed for amount 0
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins1 = {1, 3, 4};
        int amount1 = 6;
        System.out.println("Min coins for amount " + amount1 + " with " + Arrays.toString(coins1) + ": " + coinChange(coins1, amount1));
        
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Min coins for amount " + amount2 + " with " + Arrays.toString(coins2) + ": " + coinChange(coins2, amount2));
    }
}"""
        ),
        
        // DP Lesson 10: Coin Change (Number of Ways)
        Lesson(
            id = "dp-10",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Coin Change II (Number of Ways)",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Instead of minimizing the number of coins, we count the number of ways to make change. For each amount, we add the number of ways we could make that amount using each coin. The key insight is that for each coin, we can add it to all the ways we made the remaining amount.

When to Use This Pattern:
When you need to count the number of ways to achieve a target using given options. This is different from optimization problems - here we want to enumerate possibilities. It's the "counting version" of the coin change problem.

Problem Statement:
Given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money, return the number of combinations that make up that amount. If that amount cannot be made up by any combination of the coins, return 0.

Step-by-Step Approach:
1. Create dp array where dp[i] = number of ways to make amount i
2. Initialize dp[0] = 1 (one way to make 0: use no coins)
3. For each coin in coins:
   - For each amount from coin to target:
     - Add dp[amount - coin] to dp[amount]
4. Return dp[target]

Dry Run Example:
coins = [1, 2, 5], amount = 5
- Initially: dp=[1,0,0,0,0,0]
- Process coin 1:
  - dp[1]+=dp[0]=1 → dp=[1,1,0,0,0,0]
  - dp[2]+=dp[1]=1 → dp=[1,1,1,0,0,0]
  - ...continuing → dp=[1,1,1,1,1,1]
- Process coin 2:
  - dp[2]+=dp[0]=1 → dp=[1,1,2,1,1,1]
  - dp[3]+=dp[1]=1 → dp=[1,1,2,2,1,1]
  - dp[4]+=dp[2]=2 → dp=[1,1,2,2,3,1]
  - dp[5]+=dp[3]=2 → dp=[1,1,2,2,3,3]
- Process coin 5:
  - dp[5]+=dp[0]=1 → dp=[1,1,2,2,3,4]
Result: 4 ways (5×1, 2+2+1, 2+1+1+1, 1+1+1+1+1)

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;  // One way to make 0: use no coins
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("Input: coins = " + Arrays.toString(coins) + ", amount = " + amount);
        
        int result = change(amount, coins);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(amount × coins.length) - nested loops
- Space: O(amount) - dp array

Common Mistakes:
1. Processing amounts in wrong order (inner loop) causing overcounting
2. Forgetting to initialize dp[0] = 1
3. Confusing with the minimum coins problem

LeetCode Practice Problems:
- Coin Change (Min Coins)
- Combination Sum IV
- Target Sum

Mini Challenge:
Modify the solution to return all possible combinations (not just the count).

🖥️ Expected Console Output:
Input: coins = [1, 2, 5], amount = 5
Output: 4

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 10,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;  // One way to make 0: use no coins
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int amount1 = 5;
        System.out.println("Number of ways for amount " + amount1 + " with " + java.util.Arrays.toString(coins1) + ": " + change(amount1, coins1));
        
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Number of ways for amount " + amount2 + " with " + java.util.Arrays.toString(coins2) + ": " + change(amount2, coins2));
    }
}"""
        ),
        
        // DP Lesson 11: Target Sum
        Lesson(
            id = "dp-11",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Target Sum",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming / Subset Sum

Intuition (Simple Explanation):
We need to assign '+' or '-' signs to elements to reach the target sum. This can be transformed into a subset sum problem: if we assign '+' to a subset S1 and '-' to the remaining S2, then Sum(S1) - Sum(S2) = target. Since Sum(S1) + Sum(S2) = total, we get Sum(S1) = (total + target) / 2.

When to Use This Pattern:
When you need to partition an array into subsets with a specific difference or when you need to assign positive/negative signs to elements to reach a target. This transforms into a subset sum counting problem.

Problem Statement:
You are given an integer array nums and an integer target. You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers. Return the number of different expressions that evaluate to target.

Step-by-Step Approach:
1. Transform: Let S be total sum, we want P - N = target where P+N=S
2. Solving: P = (S + target) / 2, so we need to count subsets with sum P
3. Check feasibility: (S + target) must be non-negative and even
4. Use subset sum counting DP: dp[i][j] = ways to get sum j using first i elements

Dry Run Example:
nums = [1,1,1,1,1], target = 3
- Total = 5, we need P=(5+3)/2=4, so subset with sum 4
- dp[0][0]=1, dp[0][1]=0, ..., dp[0][4]=0
- Process nums[0]=1: dp[1][0]=1, dp[1][1]=1
- Process nums[1]=1: dp[2][0]=1, dp[2][1]=2, dp[2][2]=1
- Continue... final dp[5][4] = 5
Result: 5 ways

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        // Check if target is achievable
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }
        
        int subsetSum = (sum + target) / 2;
        if (subsetSum < 0) return 0;
        
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;
        
        for (int num : nums) {
            // Traverse backwards to avoid using updated values
            for (int j = subsetSum; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        
        return dp[subsetSum];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
        
        int result = findTargetSumWays(nums, target);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n × subsetSum) where subsetSum = (total + target) / 2
- Space: O(subsetSum) - dp array

Common Mistakes:
1. Not checking if (sum + target) is even
2. Not handling negative subsetSum
3. Forgetting to traverse backwards in DP to avoid overcounting

LeetCode Practice Problems:
- Partition Equal Subset Sum
- Ones and Zeroes
- Last Stone Weight II

Mini Challenge:
Modify the solution to return all possible expressions that evaluate to the target.

🖥️ Expected Console Output:
Input: nums = [1, 1, 1, 1, 1], target = 3
Output: 5

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Master""",
            type = LessonType.THEORY,
            order = 11,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        // Check if target is achievable
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }
        
        int subsetSum = (sum + target) / 2;
        if (subsetSum < 0) return 0;
        
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;
        
        for (int num : nums) {
            // Traverse backwards to avoid using updated values
            for (int j = subsetSum; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        
        return dp[subsetSum];
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("Target sum ways for [1,1,1,1,1] target " + target1 + ": " + findTargetSumWays(nums1, target1));
        
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Target sum ways for [1] target " + target2 + ": " + findTargetSumWays(nums2, target2));
    }
}"""
        ),
        
        // DP Lesson 12: Partition Equal Subset Sum
        Lesson(
            id = "dp-12",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Partition Equal Subset Sum",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming / Subset Sum

Intuition (Simple Explanation):
To partition an array into two equal sum subsets, the total sum must be even. Then we need to find if there's a subset with sum equal to half the total. This is equivalent to the classic subset sum problem where we check if a target sum is achievable.

When to Use This Pattern:
When you need to divide an array into two parts with equal sums, or when you need to check if a subset with a specific sum exists. This is a variant of the knapsack problem where we care about existence rather than optimization.

Problem Statement:
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal, or false otherwise.

Step-by-Step Approach:
1. Calculate total sum, if odd return false
2. Target sum = total / 2
3. Use subset sum DP: dp[i] = true if sum i is achievable
4. For each number, update dp array backwards
5. Return dp[target]

Dry Run Example:
nums = [1, 5, 11, 5]
- Total = 22, target = 11
- Initially dp=[true,false,...,false]
- Process 1: dp[1]=true
- Process 5: dp[5]=true, dp[6]=true
- Process 11: dp[11]=true (through 0+11), dp[12]=true (through 1+11), etc.
- Process 5: dp[5]=true, dp[6]=true, dp[10]=true, dp[11]=true (through 6+5)
Result: true (subsets [1,5,5] and [11])

Java Runnable Code:
import java.util.*;

public class Solution {

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        // If sum is odd, we can't partition into equal subsets
        if (sum % 2 != 0) return false;
        
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;  // We can always make sum 0 (empty subset)
        
        for (int num : nums) {
            // Traverse backwards to avoid using the same number twice
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println("Input: " + Arrays.toString(nums));
        
        boolean result = canPartition(nums);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n × sum/2) - nested loops
- Space: O(sum/2) - dp array

Common Mistakes:
1. Not checking if total sum is odd first
2. Traversing forward in DP causing same element to be used multiple times
3. Forgetting to initialize dp[0] = true

LeetCode Practice Problems:
- Target Sum
- Last Stone Weight II
- Split Array Largest Sum

Mini Challenge:
Modify the solution to return the actual partition (the two subsets).

🖥️ Expected Console Output:
Input: [1, 5, 11, 5]
Output: true

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        // If sum is odd, we can't partition into equal subsets
        if (sum % 2 != 0) return false;
        
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;  // We can always make sum 0 (empty subset)
        
        for (int num : nums) {
            // Traverse backwards to avoid using the same number twice
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Can partition [1,5,11,5]: " + canPartition(nums1));
        
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Can partition [1,2,3,5]: " + canPartition(nums2));
    }
}"""
        ),
        
        // DP Lesson 13: 0/1 Knapsack
        Lesson(
            id = "dp-13",
            courseId = "java-dp-patterns",
            language = "java",
            title = "0/1 Knapsack Problem",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
You have a knapsack with a weight capacity and a set of items, each with a weight and value. You can take each item at most once (hence 0/1). The goal is to maximize the total value without exceeding the weight capacity. At each item, you decide: take it (if you have capacity) or skip it.

When to Use This Pattern:
When you have limited resources and need to select items optimally. This is the foundation for many DP problems. The key insight is that for each item and each possible capacity, you make a choice between taking or skipping the item.

Problem Statement:
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W.

Step-by-Step Approach:
1. Create 2D DP table: dp[i][w] = max value using first i items with capacity w
2. Base case: dp[0][w] = 0 for all w (no items = 0 value)
3. For each item i and each capacity w:
   - Skip item: dp[i][w] = dp[i-1][w]
   - Take item (if possible): dp[i][w] = dp[i-1][w-weight[i]] + value[i]
   - Choose max of both options

Dry Run Example:
weights = [1, 3, 4], values = [1, 4, 5], capacity = 7
- dp[0][*] = 0 (no items)
- Item 0 (w=1, v=1): dp[1][1]=1, dp[1][2..7]=1
- Item 1 (w=3, v=4): dp[2][3]=max(1, 0+4)=4, dp[2][4]=max(1, 1+4)=5, etc.
- Continue filling...
Result: dp[n][W] = maximum value

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int knapSack(int W, int[] wt, int[] val) {
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];
        
        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                // Don't take the current item
                dp[i][w] = dp[i - 1][w];
                
                // Take the current item if it fits
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], 
                                       dp[i - 1][w - wt[i - 1]] + val[i - 1]);
                }
            }
        }
        
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        
        System.out.println("Input: Values = " + Arrays.toString(values) + ", Weights = " + Arrays.toString(weights) + ", Capacity = " + capacity);
        
        int result = knapSack(capacity, weights, values);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n × W) - nested loops
- Space: O(n × W) - DP table

Common Mistakes:
1. Index confusion (0-indexed vs 1-indexed in DP table)
2. Forgetting to check if item fits before taking it
3. Not initializing base cases properly

LeetCode Practice Problems:
- Target Sum
- Ones and Zeroes
- Last Stone Weight II

Mini Challenge:
Modify the solution to return the actual items selected in the optimal solution.

🖥️ Expected Console Output:
Input: Values = [60, 100, 120], Weights = [10, 20, 30], Capacity = 50
Output: 220

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 13,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int knapSack(int W, int[] wt, int[] val) {
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];
        
        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                // Don't take the current item
                dp[i][w] = dp[i - 1][w];
                
                // Take the current item if it fits
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], 
                                       dp[i - 1][w - wt[i - 1]] + val[i - 1]);
                }
            }
        }
        
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        
        System.out.println("Max value in knapsack: " + knapSack(capacity, weights, values));
    }
}"""
        ),
        
        // DP Lesson 14: Unbounded Knapsack
        Lesson(
            id = "dp-14",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Unbounded Knapsack",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Similar to 0/1 knapsack, but now you can use each item unlimited times. This changes the recurrence relation: when you take an item, you don't move to the next item, you can consider taking the same item again. The decision at each step is: use this item (and stay at this item) or move to the next item.

When to Use This Pattern:
When you have unlimited supply of each item type. This appears in problems like coin change, rod cutting, and other problems where you can reuse elements. The key difference from 0/1 knapsack is that after taking an item, you can take it again.

Problem Statement:
Given a knapsack weight W and a set of items with weights and values, find the maximum value that can be obtained by selecting items from the set, allowing unlimited use of each item type.

Step-by-Step Approach:
1. Create 1D DP array: dp[w] = max value for capacity w
2. For each capacity from 1 to W:
   - For each item that fits in current capacity:
     - Update dp[w] = max(dp[w], dp[w - weight[i]] + value[i])
3. Return dp[W]

Dry Run Example:
weights = [5, 10, 15], values = [10, 30, 20], capacity = 100
- dp[0] = 0
- For w=5: dp[5] = max(0, dp[0]+10) = 10
- For w=10: dp[10] = max(0, dp[5]+10, dp[0]+30) = 30
- Continue filling...
Result: dp[100]

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int unboundedKnapsack(int W, int[] wt, int[] val) {
        int n = wt.length;
        int[] dp = new int[W + 1];
        
        for (int w = 1; w <= W; w++) {
            for (int i = 0; i < n; i++) {
                if (wt[i] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
                }
            }
        }
        
        return dp[W];
    }

    public static void main(String[] args) {
        int[] values = {10, 30, 20};
        int[] weights = {5, 10, 15};
        int capacity = 100;
        
        System.out.println("Input: Values = " + Arrays.toString(values) + ", Weights = " + Arrays.toString(weights) + ", Capacity = " + capacity);
        
        int result = unboundedKnapsack(capacity, weights, values);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(W × n) - nested loops
- Space: O(W) - 1D DP array

Common Mistakes:
1. Confusing with 0/1 knapsack (using 2D DP unnecessarily)
2. Processing items in outer loop instead of capacities
3. Not handling the unlimited use property correctly

LeetCode Practice Problems:
- Coin Change
- Rod Cutting
- Maximum Product Cutting

Mini Challenge:
Modify the solution to return the count of each item used in the optimal solution.

🖥️ Expected Console Output:
Input: Values = [10, 30, 20], Weights = [5, 10, 15], Capacity = 100
Output: 300

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int unboundedKnapsack(int W, int[] wt, int[] val) {
        int n = wt.length;
        int[] dp = new int[W + 1];
        
        for (int w = 1; w <= W; w++) {
            for (int i = 0; i < n; i++) {
                if (wt[i] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
                }
            }
        }
        
        return dp[W];
    }

    public static void main(String[] args) {
        int[] values = {10, 30, 20};
        int[] weights = {5, 10, 15};
        int capacity = 100;
        
        System.out.println("Max value in unbounded knapsack: " + unboundedKnapsack(capacity, weights, values));
    }
}"""
        ),
        
        // DP Lesson 15: Longest Common Subsequence
        Lesson(
            id = "dp-15",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Longest Common Subsequence (LCS)",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Find the longest sequence that appears in both strings in the same relative order but not necessarily contiguous. At each character position in both strings, we have two choices: if characters match, include it in LCS; if not, take the best from excluding either character.

When to Use This Pattern:
When comparing two sequences to find common patterns. This is fundamental for diff algorithms, DNA sequence analysis, and text similarity. The approach is to build the solution incrementally considering prefixes of both strings.

Problem Statement:
Given two strings text1 and text2, return the length of their longest common subsequence. A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

Step-by-Step Approach:
1. Create 2D DP table: dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1]
2. Base case: dp[0][j] = dp[i][0] = 0 (empty string has LCS 0)
3. For each cell (i,j):
   - If text1[i-1] == text2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
   - Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])

Dry Run Example:
text1 = "ABCDGH", text2 = "AEDFHR"
- dp[0][*] = dp[*][0] = 0
- Compare A-A: match, dp[1][1] = dp[0][0] + 1 = 1
- Compare A-E: no match, dp[1][2] = max(dp[0][2], dp[1][1]) = max(0,1) = 1
- Continue filling...
Result: dp[m][n] = LCS length

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "ABCDGH";
        String text2 = "AEDFHR";
        System.out.println("Input: text1 = \"" + text1 + "\", text2 = \"" + text2 + "\"");
        
        int result = longestCommonSubsequence(text1, text2);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(m × n) - nested loops
- Space: O(m × n) - DP table

Common Mistakes:
1. Off-by-one errors in character access
2. Not handling empty string cases
3. Confusing with longest common substring (contiguous)

LeetCode Practice Problems:
- Edit Distance
- Longest Palindromic Subsequence
- Shortest Common Supersequence

Mini Challenge:
Modify the solution to return the actual LCS string, not just its length.

🖥️ Expected Console Output:
Input: text1 = "ABCDGH", text2 = "AEDFHR"
Output: 3

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 15,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "ABCDGH";
        String text2 = "AEDFHR";
        System.out.println("LCS length: " + longestCommonSubsequence(text1, text2));
        
        String text3 = "programming";
        String text4 = "grading";
        System.out.println("LCS length: " + longestCommonSubsequence(text3, text4));
    }
}"""
        ),
        
        // DP Lesson 16: Longest Palindromic Subsequence
        Lesson(
            id = "dp-16",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Longest Palindromic Subsequence",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Find the longest subsequence that reads the same forwards and backwards. The key insight is that for a substring s[i...j], if s[i] == s[j], then the palindromic length increases by 2 plus the result for s[i+1...j-1]. If they don't match, take the maximum of excluding either character.

When to Use This Pattern:
When you need to find palindromic structures in strings. This is related to LCS - the LPS of a string equals the LCS of the string and its reverse. This pattern appears in various palindrome-related problems.

Problem Statement:
Given a string s, find the longest palindromic subsequence's length in s. A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

Step-by-Step Approach:
1. Create 2D DP table: dp[i][j] = LPS length in s[i...j]
2. Base case: dp[i][i] = 1 (single character is palindrome of length 1)
3. For each length from 2 to n:
   - For each starting position i:
     - Ending position j = i + length - 1
     - If s[i] == s[j]: dp[i][j] = dp[i+1][j-1] + 2
     - Else: dp[i][j] = max(dp[i+1][j], dp[i][j-1])

Dry Run Example:
s = "bbbab"
- dp[i][i] = 1 for all i
- Length 2: "bb" → dp[0][1] = 2, "ba" → dp[1][2] = 1, etc.
- Length 3: "bbb" → dp[0][2] = dp[1][1] + 2 = 3, "bba" → dp[1][3] = max(1,1) = 1
- Continue filling...
Result: dp[0][n-1] = 4 ("bbbb")

Java Runnable Code:
public class Solution {

    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        // Every single character is a palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // Fill the dp table for substrings of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s1 = "bbbab";
        System.out.println("Longest palindromic subsequence length: " + longestPalindromeSubseq(s1));
        
        String s2 = "cbbd";
        System.out.println("Longest palindromic subsequence length: " + longestPalindromeSubseq(s2));
    }
}

Time & Space Complexity:
- Time: O(n²) - nested loops
- Space: O(n²) - DP table

Common Mistakes:
1. Not iterating in the correct order (length increasing)
2. Off-by-one errors in index calculation
3. Confusing with longest palindromic substring

LeetCode Practice Problems:
- Longest Common Subsequence
- Palindromic Substrings
- Valid Palindrome III

Mini Challenge:
Modify the solution to return the actual palindromic subsequence string.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        // Every single character is a palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // Fill the dp table for substrings of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s1 = "bbbab";
        System.out.println("Longest palindromic subsequence length: " + longestPalindromeSubseq(s1));
        
        String s2 = "cbbd";
        System.out.println("Longest palindromic subsequence length: " + longestPalindromeSubseq(s2));
    }
}"""
        ),
        
        // DP Lesson 17: Edit Distance
        Lesson(
            id = "dp-17",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Edit Distance (Levenshtein Distance)",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Find the minimum number of operations (insert, delete, replace) to convert one string to another. At each position in both strings, if characters match, no operation is needed. If they don't match, consider the cost of insert, delete, or replace operations.

When to Use This Pattern:
When comparing strings for similarity or finding the minimum operations to transform one into another. This is widely used in spell checkers, DNA analysis, and version control systems. The three operations map to different scenarios in string transformation.

Problem Statement:
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2. You have the following three operations permitted on a word: Insert a character, Delete a character, Replace a character.

Step-by-Step Approach:
1. Create 2D DP table: dp[i][j] = min edits to convert word1[0..i-1] to word2[0..j-1]
2. Base cases: dp[0][j] = j (insert j chars), dp[i][0] = i (delete i chars)
3. For each cell (i,j):
   - If word1[i-1] == word2[j-1]: dp[i][j] = dp[i-1][j-1] (no op needed)
   - Else: dp[i][j] = 1 + min(replace, delete, insert)

Dry Run Example:
word1 = "horse", word2 = "ros"
- dp[0][j] = j, dp[i][0] = i
- Compare h-r: different → dp[1][1] = 1 + min(dp[0][0], dp[1][0], dp[0][1]) = 1
- Continue filling...
Result: dp[m][n]

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;  // Need i deletions to get empty string
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;  // Need j insertions to get word2[0..j-1]
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];  // No operation needed
                } else {
                    // Take minimum of replace, delete, insert
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j - 1],  // replace
                                                   dp[i - 1][j]),     // delete
                                                   dp[i][j - 1]);    // insert
                }
            }
        }
        
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Input: word1 = \"" + word1 + "\", word2 = \"" + word2 + "\"");
        
        int result = minDistance(word1, word2);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(m × n) - nested loops
- Space: O(m × n) - DP table

Common Mistakes:
1. Not handling base cases properly
2. Confusing the operations (insert vs delete directions)
3. Off-by-one errors in character comparison

LeetCode Practice Problems:
- One Edit Distance
- Delete Operation for Two Strings
- Minimum ASCII Delete Sum

Mini Challenge:
Modify the solution to return the actual sequence of operations needed to transform word1 to word2.

🖥️ Expected Console Output:
Input: word1 = "horse", word2 = "ros"
Output: 3

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;  // Need i deletions to get empty string
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;  // Need j insertions to get word2[0..j-1]
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];  // No operation needed
                } else {
                    // Take minimum of replace, delete, insert
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j - 1],  // replace
                                                   dp[i - 1][j]),     // delete
                                                   dp[i][j - 1]);    // insert
                }
            }
        }
        
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Edit distance: " + minDistance(word1, word2));
        
        String word3 = "intention";
        String word4 = "execution";
        System.out.println("Edit distance: " + minDistance(word3, word4));
    }
}"""
        ),
        
        // DP Lesson 18: Unique Paths
        Lesson(
            id = "dp-18",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Unique Paths",
            content = """🟢 Easy

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
A robot is at the top-left corner of an m×n grid and wants to reach the bottom-right corner. It can only move right or down. The number of ways to reach any cell is the sum of ways to reach the cell above and the cell to the left.

When to Use This Pattern:
When counting paths in a grid with movement constraints. This is foundational for many grid DP problems. The key insight is that to reach any cell, you must come from either the cell above or the cell to the left.

Problem Statement:
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time. Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

Step-by-Step Approach:
1. Create 2D DP table: dp[i][j] = number of paths to reach (i,j)
2. Base cases: dp[0][j] = 1 (only one way: all right moves), dp[i][0] = 1 (only one way: all down moves)
3. For each cell (i,j): dp[i][j] = dp[i-1][j] + dp[i][j-1]
4. Return dp[m-1][n-1]

Dry Run Example:
m=3, n=7
- dp[0][*] = 1, dp[*][0] = 1
- dp[1][1] = dp[0][1] + dp[1][0] = 1 + 1 = 2
- dp[1][2] = dp[0][2] + dp[1][1] = 1 + 2 = 3
- Continue filling...
Result: dp[2][6]

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        // Initialize first row and column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        // Fill the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println("Input: m = " + m + ", n = " + n);
        
        int result = uniquePaths(m, n);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(m × n) - nested loops
- Space: O(m × n) - DP table

Common Mistakes:
1. Not initializing the first row and column properly
2. Confusing movement directions
3. Off-by-one errors in indexing

LeetCode Practice Problems:
- Unique Paths II (with obstacles)
- Minimum Path Sum
- Dungeon Game

Mini Challenge:
Can you optimize the space complexity to O(min(m,n))?

🖥️ Expected Console Output:
Input: m = 3, n = 7
Output: 28

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 18,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        // Initialize first row and column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        // Fill the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println("Unique paths for " + m + "x" + n + ": " + uniquePaths(m, n));
        
        int m2 = 3, n2 = 2;
        System.out.println("Unique paths for " + m2 + "x" + n2 + ": " + uniquePaths(m2, n2));
    }
}"""
        ),
        
        // DP Lesson 19: Unique Paths with Obstacles
        Lesson(
            id = "dp-19",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Unique Paths with Obstacles",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Similar to unique paths, but now some cells are blocked (obstacles). We can't pass through these cells. The approach is similar, but we need to check if a cell is blocked before calculating paths to it.

When to Use This Pattern:
When you have constraints or obstacles in pathfinding problems. This builds on the basic unique paths problem but adds the complexity of blocked cells. It's commonly used in robotics path planning and game development.

Problem Statement:
You are given an m x n integer matrix grid where 0 represents an empty cell and 1 represents an obstacle. Return the number of possible unique paths from the top-left corner to the bottom-right corner. The robot can only move right or down and cannot pass through obstacles.

Step-by-Step Approach:
1. Create 2D DP table: dp[i][j] = number of paths to reach (i,j)
2. If cell (i,j) is obstacle (grid[i][j] == 1), set dp[i][j] = 0
3. Base cases: dp[0][0] = 1 if not obstacle, initialize first row/column considering obstacles
4. For each cell (i,j): if not obstacle, dp[i][j] = dp[i-1][j] + dp[i][j-1]

Dry Run Example:
grid = [[0,0,0],[0,1,0],[0,0,0]] (1 is obstacle)
- dp[0][0] = 1
- dp[0][1] = 1, dp[0][2] = 1
- dp[1][0] = 1, dp[1][1] = 0 (obstacle), dp[1][2] = 1
- dp[2][0] = 1, dp[2][1] = 1, dp[2][2] = 2
Result: 2 paths

Java Runnable Code:
public class Solution {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // If start or end is blocked, no path exists
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        
        int[][] dp = new int[m][n];
        
        // Initialize starting point
        dp[0][0] = 1;
        
        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = (obstacleGrid[0][j] == 0) ? dp[0][j-1] : 0;
        }
        
        // Initialize first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = (obstacleGrid[i][0] == 0) ? dp[i-1][0] : 0;
        }
        
        // Fill the rest of the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                } else {
                    dp[i][j] = 0; // Obstacle
                }
            }
        }
        
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("Unique paths with obstacles: " + uniquePathsWithObstacles(grid1));
        
        int[][] grid2 = {{0,1},{0,0}};
        System.out.println("Unique paths with obstacles: " + uniquePathsWithObstacles(grid2));
    }
}

Time & Space Complexity:
- Time: O(m × n) - nested loops
- Space: O(m × n) - DP table

Common Mistakes:
1. Not checking if start or end is blocked
2. Forgetting to handle obstacles properly in initialization
3. Not setting dp[i][j] = 0 when cell is obstacle

LeetCode Practice Problems:
- Unique Paths
- Minimum Path Sum
- Dungeon Game

Mini Challenge:
Modify the solution to return one of the actual valid paths instead of just the count.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // If start or end is blocked, no path exists
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        
        int[][] dp = new int[m][n];
        
        // Initialize starting point
        dp[0][0] = 1;
        
        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = (obstacleGrid[0][j] == 0) ? dp[0][j-1] : 0;
        }
        
        // Initialize first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = (obstacleGrid[i][0] == 0) ? dp[i-1][0] : 0;
        }
        
        // Fill the rest of the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                } else {
                    dp[i][j] = 0; // Obstacle
                }
            }
        }
        
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("Unique paths with obstacles: " + uniquePathsWithObstacles(grid1));
        
        int[][] grid2 = {{0,1},{0,0}};
        System.out.println("Unique paths with obstacles: " + uniquePathsWithObstacles(grid2));
    }
}"""
        ),
        
        // DP Lesson 20: Minimum Path Sum (Grid)
        Lesson(
            id = "dp-20",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Minimum Path Sum in Grid",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Find the path from top-left to bottom-right with minimum sum of values. At each cell, you decide whether to come from the top or left based on which gives a smaller cumulative sum. This is similar to unique paths but optimizing for sum instead of counting paths.

When to Use This Pattern:
When you need to find the optimal path in a grid with costs/weights associated with each cell. Common in transportation, logistics, and optimization problems. The key insight is that to reach any cell, you must come from either the cell above or the cell to the left.

Problem Statement:
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path. You can only move either down or right at any point in time.

Step-by-Step Approach:
1. Create 2D DP table: dp[i][j] = minimum sum to reach (i,j)
2. Base case: dp[0][0] = grid[0][0]
3. Initialize first row: dp[0][j] = dp[0][j-1] + grid[0][j]
4. Initialize first column: dp[i][0] = dp[i-1][0] + grid[i][0]
5. For each cell (i,j): dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])

Dry Run Example:
grid = [[1,3,1],[1,5,1],[4,2,1]]
- dp[0][0] = 1
- dp[0][1] = 4, dp[0][2] = 5
- dp[1][0] = 2, dp[1][1] = 7(min(4+5,2+3)), dp[1][2] = 6
- dp[2][0] = 6, dp[2][1] = 8, dp[2][2] = 7
Result: 7 (path: 1→3→1→1→1)

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        
        // Initialize starting point
        dp[0][0] = grid[0][0];
        
        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        
        // Initialize first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        
        // Fill the rest of the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("Input grid:");
        for (int[] row : grid) {
            System.out.println(java.util.Arrays.toString(row));
        }
        
        int result = minPathSum(grid);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(m × n) - nested loops
- Space: O(m × n) - DP table

Common Mistakes:
1. Not initializing the first row and column separately
2. Using max instead of min in the recurrence
3. Forgetting to add the current cell's value to the sum

LeetCode Practice Problems:
- Unique Paths
- Triangle Minimum Path
- Dungeon Game

Mini Challenge:
Modify the solution to return the actual path that gives the minimum sum.

🖥️ Expected Console Output:
Input grid:
[1, 3, 1]
[1, 5, 1]
[4, 2, 1]
Output: 7

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        
        // Initialize starting point
        dp[0][0] = grid[0][0];
        
        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        
        // Initialize first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        
        // Fill the rest of the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("Minimum path sum: " + minPathSum(grid1));
        
        int[][] grid2 = {{1,2,3},{4,5,6}};
        System.out.println("Minimum path sum: " + minPathSum(grid2));
    }
}"""
        ),
        
        // DP Lesson 21: Triangle Minimum Path
        Lesson(
            id = "dp-21",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Triangle Minimum Path",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Given a triangle of numbers, find the minimum sum path from top to bottom. At each position, you can move to adjacent numbers on the next row. This can be solved from top-down or bottom-up. Bottom-up is often cleaner as it avoids boundary checks.

When to Use This Pattern:
When you have a hierarchical structure with limited movement options between levels. This is a classic DP problem that appears in various forms. The triangle structure limits possible moves to adjacent positions.

Problem Statement:
Given a triangle array, return the minimum path sum from top to bottom. For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

Step-by-Step Approach:
1. Create DP table: dp[i][j] = minimum sum to reach position (i,j)
2. Base case: dp[0][0] = triangle[0][0]
3. For each row i, for each position j:
   - If j=0: only way is from above (i-1,0)
   - If j=i: only way is from diagonal left (i-1,j-1)
   - Else: min(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]

Dry Run Example:
triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
- dp[0][0] = 2
- dp[1][0] = 5, dp[1][1] = 6
- dp[2][0] = 11, dp[2][1] = 7, dp[2][2] = 13
- dp[3][0] = 15, dp[3][1] = 8, dp[3][2] = 15, dp[3][3] = 16
Result: min of last row = 8

Java Runnable Code:
import java.util.List;
import java.util.Arrays;

public class Solution {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        
        // Initialize first row
        dp[0][0] = triangle.get(0).get(0);
        
        // Fill the dp table
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // Only one way: from top
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    // Only one way: from diagonal left
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                } else {
                    // Take minimum of two possible paths
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + 
                               triangle.get(i).get(j);
                }
            }
        }
        
        // Find minimum in the last row
        int minPath = dp[n-1][0];
        for (int j = 1; j < n; j++) {
            minPath = Math.min(minPath, dp[n-1][j]);
        }
        
        return minPath;
    }

    public static void main(String[] args) {
        // Create triangle: [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> triangle = Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3)
        );
        
        System.out.println("Input triangle:");
        for (List<Integer> row : triangle) {
            System.out.println(row);
        }
        
        int result = minimumTotal(triangle);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n²) - nested loops
- Space: O(n²) - DP table

Common Mistakes:
1. Not handling boundary conditions (j=0 and j=i)
2. Index out of bounds errors
3. Forgetting to find minimum in the last row

LeetCode Practice Problems:
- Minimum Path Sum
- Cherry Pickup
- Bomb Enemy

Mini Challenge:
Can you optimize the space complexity to O(n) using only one row?

🖥️ Expected Console Output:
Input triangle:
[2]
[3, 4]
[6, 5, 7]
[4, 1, 8, 3]
Output: 11

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 21,
            isCompleted = false,
            codeExample = """
import java.util.List;
import java.util.Arrays;

public class Solution {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        
        // Initialize first row
        dp[0][0] = triangle.get(0).get(0);
        
        // Fill the dp table
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // Only one way: from top
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    // Only one way: from diagonal left
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                } else {
                    // Take minimum of two possible paths
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + 
                               triangle.get(i).get(j);
                }
            }
        }
        
        // Find minimum in the last row
        int minPath = dp[n-1][0];
        for (int j = 1; j < n; j++) {
            minPath = Math.min(minPath, dp[n-1][j]);
        }
        
        return minPath;
    }

    public static void main(String[] args) {
        // Create triangle: [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> triangle = Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3)
        );
        
        System.out.println("Minimum path sum: " + minimumTotal(triangle));
    }
}"""
        ),
        
        // DP Lesson 22: DP Rolling Array Optimization
        Lesson(
            id = "dp-22",
            courseId = "java-dp-patterns",
            language = "java",
            title = "DP Rolling Array Optimization",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming Space Optimization

Intuition (Simple Explanation):
Many DP problems only depend on the previous row or previous state. Instead of storing the entire DP table, we can optimize space by using just the current and previous rows. This reduces space complexity from O(n²) to O(n) or O(1).

When to Use This Pattern:
When the recurrence relation only depends on the previous row/state. This is common in problems like unique paths, minimum path sum, and many others. It's important to update the array in the correct order to avoid overwriting needed values.

Problem Statement:
Optimize the space complexity of the Unique Paths problem from O(m×n) to O(min(m,n)).

Step-by-Step Approach:
1. Identify the recurrence relation: dp[i][j] = dp[i-1][j] + dp[i][j-1]
2. Notice that we only need the previous row to compute the current row
3. Use a 1D array to represent the current row
4. Update the array from left to right to maintain the dependency

Dry Run Example:
For m=3, n=7:
- Initialize dp[0..6] = [1,1,1,1,1,1,1]
- Row 1: dp[j] = dp[j] + dp[j-1]
- Row 2: dp[j] = dp[j] + dp[j-1]
Result: dp[n-1]

Java Runnable Code:
public class Solution {

    public static int uniquePaths(int m, int n) {
        // Ensure we use the smaller dimension for space optimization
        if (m < n) {
            return uniquePaths(n, m);
        }
        
        // Use a 1D array to represent the current row
        int[] dp = new int[n];
        
        // Initialize the first row
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        
        // Process each row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] represents the cell above (previous value)
                // dp[j-1] represents the cell to the left (updated value)
                dp[j] = dp[j] + dp[j-1];
            }
        }
        
        return dp[n-1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println("Unique paths (optimized): " + uniquePaths(m, n));
        
        int m2 = 3, n2 = 2;
        System.out.println("Unique paths (optimized): " + uniquePaths(m2, n2));
    }
}

Time & Space Complexity:
- Time: O(m × n) - still need to process all cells
- Space: O(min(m, n)) - only one row stored

Common Mistakes:
1. Updating the array in wrong direction (right to left when should be left to right)
2. Forgetting to handle the first column separately
3. Not considering which dimension to optimize

LeetCode Practice Problems:
- Minimum Path Sum
- Unique Paths II
- Triangle

Mini Challenge:
Apply rolling array optimization to the minimum path sum problem.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int uniquePaths(int m, int n) {
        // Ensure we use the smaller dimension for space optimization
        if (m < n) {
            return uniquePaths(n, m);
        }
        
        // Use a 1D array to represent the current row
        int[] dp = new int[n];
        
        // Initialize the first row
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        
        // Process each row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] represents the cell above (previous value)
                // dp[j-1] represents the cell to the left (updated value)
                dp[j] = dp[j] + dp[j-1];
            }
        }
        
        return dp[n-1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println("Unique paths (optimized): " + uniquePaths(m, n));
        
        int m2 = 3, n2 = 2;
        System.out.println("Unique paths (optimized): " + uniquePaths(m2, n2));
    }
}"""
        ),
        
        // DP Lesson 23: Best Time to Buy & Sell Stock I
        Lesson(
            id = "dp-23",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Best Time to Buy & Sell Stock I",
            content = """🟢 Easy

Pattern / Topic Name: Dynamic Programming / Kadane's Algorithm

Intuition (Simple Explanation):
Track the minimum price seen so far and calculate the maximum profit possible at each day. This is similar to Kadane's algorithm where we keep track of the best solution ending at each position.

When to Use This Pattern:
When you need to make decisions based on historical data to optimize future outcomes. This pattern is applicable to various buy/sell problems and other optimization problems where you track running minimums or maximums.

Problem Statement:
You are given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Step-by-Step Approach:
1. Keep track of the minimum price seen so far
2. For each day, calculate profit if sold today (price - minPrice)
3. Update maximum profit if current profit is greater
4. Update minimum price if current price is lower

Dry Run Example:
prices = [7, 1, 5, 3, 6, 4]
- Day 0: minPrice=7, maxProfit=0
- Day 1: minPrice=1, profit=0, maxProfit=0
- Day 2: minPrice=1, profit=4, maxProfit=4
- Day 3: minPrice=1, profit=2, maxProfit=4
- Day 4: minPrice=1, profit=5, maxProfit=5
- Day 5: minPrice=1, profit=3, maxProfit=5
Result: 5

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // Calculate profit if we sell today
            int profit = prices[i] - minPrice;
            
            // Update maximum profit
            maxProfit = Math.max(maxProfit, profit);
            
            // Update minimum price
            minPrice = Math.min(minPrice, prices[i]);
        }
        
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Input: " + Arrays.toString(prices));
        
        int result = maxProfit(prices);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the array
- Space: O(1) - only using two variables

Common Mistakes:
1. Not initializing minPrice to the first element
2. Forgetting to handle edge cases (empty or single element)
3. Confusing with multiple transactions allowed

LeetCode Practice Problems:
- Best Time to Buy and Sell Stock II
- Best Time to Buy and Sell Stock III
- Maximum Subarray

Mini Challenge:
Modify the solution to return the days on which to buy and sell for maximum profit.

🖥️ Expected Console Output:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // Calculate profit if we sell today
            int profit = prices[i] - minPrice;
            
            // Update maximum profit
            maxProfit = Math.max(maxProfit, profit);
            
            // Update minimum price
            minPrice = Math.min(minPrice, prices[i]);
        }
        
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Max profit: " + maxProfit(prices1));
        
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Max profit: " + maxProfit(prices2));
    }
}"""
        ),
        
        // DP Lesson 24: Best Time to Buy & Sell Stock II
        Lesson(
            id = "dp-24",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Best Time to Buy & Sell Stock II",
            content = """🟢 Easy

Pattern / Topic Name: Dynamic Programming / Greedy

Intuition (Simple Explanation):
Now you can make multiple transactions (buy and sell as many times as you want). The key insight is to capture every profitable opportunity. If tomorrow's price is higher than today's, buy today and sell tomorrow. This greedy approach works because we can break any multi-day transaction into day-by-day transactions.

When to Use This Pattern:
When you're allowed to perform multiple transactions to maximize profit. The greedy approach works here because we can capture every upward price movement. This is different from the single transaction problem where we needed to track the best single buy-sell pair.

Problem Statement:
You are given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing to buy and sell multiple times. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Step-by-Step Approach:
1. Iterate through the prices array
2. If tomorrow's price is higher than today's, add the profit (tomorrow - today)
3. Accumulate all such profits

Dry Run Example:
prices = [7, 1, 5, 3, 6, 4]
- Day 0→1: 7→1, no profit (decreasing)
- Day 1→2: 1→5, profit = 4
- Day 2→3: 5→3, no profit (decreasing)
- Day 3→4: 3→6, profit = 3
- Day 4→5: 6→4, no profit (decreasing)
Total profit = 4 + 3 = 7

Java Runnable Code:
public class Solution {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int totalProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // If tomorrow's price is higher, buy today and sell tomorrow
            if (prices[i] > prices[i-1]) {
                totalProfit += prices[i] - prices[i-1];
            }
        }
        
        return totalProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Max profit (multiple transactions): " + maxProfit(prices1));
        
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Max profit (increasing): " + maxProfit(prices2));
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the array
- Space: O(1) - only using one variable

Common Mistakes:
1. Trying to use DP when greedy approach suffices
2. Overthinking the problem by considering complex state machines
3. Forgetting to handle edge cases

LeetCode Practice Problems:
- Best Time to Buy and Sell Stock I
- Best Time to Buy and Sell Stock III
- Best Time to Buy and Sell Stock with Cooldown

Mini Challenge:
Implement a version that charges a transaction fee for each buy-sell pair.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Beginner""",
            type = LessonType.THEORY,
            order = 24,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int totalProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // If tomorrow's price is higher, buy today and sell tomorrow
            if (prices[i] > prices[i-1]) {
                totalProfit += prices[i] - prices[i-1];
            }
        }
        
        return totalProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Max profit (multiple transactions): " + maxProfit(prices1));
        
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Max profit (increasing): " + maxProfit(prices2));
    }
}"""
        ),
        
        // DP Lesson 25: Best Time to Buy & Sell Stock with Cooldown
        Lesson(
            id = "dp-25",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Best Time to Buy & Sell Stock with Cooldown",
            content = """🔴 Hard

Pattern / Topic Name: Dynamic Programming State Machine

Intuition (Simple Explanation):
You have three states: holding stock (hold), selling (sold), and cooldown (rest). After selling, you must cooldown for one day before buying again. This creates state transitions that need to be tracked carefully.

When to Use This Pattern:
When there are constraints between states or actions that require a waiting period. This is a state machine DP problem where you track different states and their transitions. Common in problems with restrictions like cooldown periods.

Problem Statement:
You are given an array prices where prices[i] is the price of a given stock on the ith day. Find the maximum profit you can achieve. You may complete as many transactions as you like but after you sell your stock, you cannot buy stock on the next day (cooldown one day).

Step-by-Step Approach:
1. Define three states: hold (have stock), sold (just sold), rest (cooldown or free to buy)
2. State transitions: 
   - hold[i] = max(hold[i-1], rest[i-1] - prices[i])
   - sold[i] = hold[i-1] + prices[i]
   - rest[i] = max(rest[i-1], sold[i-1])
3. Initialize: hold[0] = -prices[0], sold[0] = 0, rest[0] = 0

Dry Run Example:
prices = [1, 2, 3, 0, 2]
- Day 0: hold=-1, sold=0, rest=0
- Day 1: hold=max(-1, -2)=-1, sold=-1+2=1, rest=max(0,0)=0
- Day 2: hold=max(-1, -3)=-1, sold=-1+3=2, rest=max(0,1)=1
- Day 3: hold=max(-1, 1-0)=1, sold=2+0=2, rest=max(1,2)=2
- Day 4: hold=max(1, 2-2)=1, sold=1+2=3, rest=max(2,2)=2
Result: max(1,2,3) = 3

Java Runnable Code:
public class Solution {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int hold = -prices[0]; // Have stock
        int sold = 0;           // Just sold (in cooldown)
        int rest = 0;           // Free to buy
        
        for (int i = 1; i < prices.length; i++) {
            int prevHold = hold;
            int prevSold = sold;
            int prevRest = rest;
            
            // Either keep holding or buy today
            hold = Math.max(prevHold, prevRest - prices[i]);
            
            // Sell the stock we were holding
            sold = prevHold + prices[i];
            
            // Either keep resting or come out of cooldown
            rest = Math.max(prevRest, prevSold);
        }
        
        // Max profit is either having just sold or being in rest state
        return Math.max(sold, rest);
    }

    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3, 0, 2};
        System.out.println("Max profit with cooldown: " + maxProfit(prices1));
        
        int[] prices2 = {1};
        System.out.println("Max profit with cooldown: " + maxProfit(prices2));
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the array
- Space: O(1) - only using three variables

Common Mistakes:
1. Not properly modeling the state transitions
2. Forgetting that after selling you need to cooldown
3. Confusing the state definitions

LeetCode Practice Problems:
- Best Time to Buy and Sell Stock II
- Best Time to Buy and Sell Stock III
- Best Time to Buy and Sell Stock with Transaction Fee

Mini Challenge:
Modify the solution to have a cooldown period of k days instead of 1.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Master""",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int hold = -prices[0]; // Have stock
        int sold = 0;           // Just sold (in cooldown)
        int rest = 0;           // Free to buy
        
        for (int i = 1; i < prices.length; i++) {
            int prevHold = hold;
            int prevSold = sold;
            int prevRest = rest;
            
            // Either keep holding or buy today
            hold = Math.max(prevHold, prevRest - prices[i]);
            
            // Sell the stock we were holding
            sold = prevHold + prices[i];
            
            // Either keep resting or come out of cooldown
            rest = Math.max(prevRest, prevSold);
        }
        
        // Max profit is either having just sold or being in rest state
        return Math.max(sold, rest);
    }

    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3, 0, 2};
        System.out.println("Max profit with cooldown: " + maxProfit(prices1));
        
        int[] prices2 = {1};
        System.out.println("Max profit with cooldown: " + maxProfit(prices2));
    }
}"""
        ),
        
        // DP Lesson 26: Decode Ways
        Lesson(
            id = "dp-26",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Decode Ways",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Think of this as a path-counting problem where each digit or pair of digits can represent a letter. We need to count the number of ways to decode a string of digits. At each position, we can either take one digit (if not zero) or two digits (if they form a valid letter code 10-26).

When to Use This Pattern:
When you need to count the number of ways to parse or group elements with constraints. This is common in problems involving encoding/decoding, parsing strings with validation rules, or counting valid segmentations.

Problem Statement:
A message containing letters from A-Z can be encoded into numbers using the mapping: 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26". Given a string s containing only digits, return the number of ways to decode it.

Step-by-Step Approach:
1. Create DP array: dp[i] = number of ways to decode s[0...i-1]
2. Base case: dp[0] = 1 (empty string has one way)
3. For each position i:
   - If current digit is not '0', add dp[i-1] ways
   - If previous and current form valid 2-digit number (10-26), add dp[i-2] ways
4. Return dp[n]

Dry Run Example:
s = "12"
- dp[0] = 1 (empty)
- dp[1] = 1 (decode as 'A')
- dp[2] = 2 (decode as 'AB' or 'L')  
Result: 2 ways

Java Runnable Code:
public class Solution {

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // Empty string has 1 way
        dp[1] = 1; // First char is not '0', so 1 way
        
        for (int i = 2; i <= n; i++) {
            // Single digit decoding (if not '0')
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            
            // Two digit decoding (10-26)
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[n];
    }

    public static void main(String[] args) {
        String s1 = "12";
        System.out.println("Decode ways for \"" + s1 + "\": " + numDecodings(s1));
        
        String s2 = "226";
        System.out.println("Decode ways for \"" + s2 + "\": " + numDecodings(s2));
        
        String s3 = "06";
        System.out.println("Decode ways for \"" + s3 + "\": " + numDecodings(s3));
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the string
- Space: O(n) - DP array

Common Mistakes:
1. Not handling leading zeros properly
2. Forgetting to check if two-digit number is in range [10,26]
3. Off-by-one errors in substring extraction

LeetCode Practice Problems:
- Decode Ways II (with wildcards)
- Interleaving String
- Regular Expression Matching

Mini Challenge:
Modify the solution to return all possible decodings instead of just the count.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Master""",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // Empty string has 1 way
        dp[1] = 1; // First char is not '0', so 1 way
        
        for (int i = 2; i <= n; i++) {
            // Single digit decoding (if not '0')
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            
            // Two digit decoding (10-26)
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[n];
    }

    public static void main(String[] args) {
        String s1 = "12";
        System.out.println("Decode ways for \"" + s1 + "\": " + numDecodings(s1));
        
        String s2 = "226";
        System.out.println("Decode ways for \"" + s2 + "\": " + numDecodings(s2));
        
        String s3 = "06";
        System.out.println("Decode ways for \"" + s3 + "\": " + numDecodings(s3));
    }
}"""
        ),
        
        // DP Lesson 27: Word Break
        Lesson(
            id = "dp-27",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Word Break",
            content = """🟡 Medium

Pattern / Topic Name: Dynamic Programming

Intuition (Simple Explanation):
Think of this as a string segmentation problem where we need to check if we can break a string into words from a dictionary. At each position in the string, we check if any word from the dictionary ends at this position and if the preceding part can also be segmented.

When to Use This Pattern:
When you need to validate or segment a string against a dictionary of valid substrings. Common in text processing, syntax validation, and parsing problems.

Problem Statement:
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words. The same word in the dictionary may be reused multiple times in the segmentation.

Step-by-Step Approach:
1. Create DP array: dp[i] = true if s[0...i-1] can be segmented
2. Base case: dp[0] = true (empty string can always be segmented)
3. For each position i from 1 to n:
   - For each word in dictionary:
     - If s[i-len...i] matches the word and dp[i-len] is true
     - Set dp[i] = true and break
4. Return dp[n]

Dry Run Example:
s = "leetcode", wordDict = ["leet", "code"]
- dp[0] = true
- dp[4] = true (matched "leet" and dp[0] is true)
- dp[8] = true (matched "code" and dp[4] is true)
Result: true

Java Runnable Code:
import java.util.*;

public class Solution {

    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Empty string can always be segmented
        
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && dp[i - len] && 
                    s.substring(i - len, i).equals(word)) {
                    dp[i] = true;
                    break; // Found a valid segmentation
                }
            }
        }
        
        return dp[n];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> dict = Arrays.asList("leet", "code");
        System.out.println("Input: s = \"" + s + "\", wordDict = " + dict);
        
        boolean result = wordBreak(s, dict);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n × m × k) where n is string length, m is dict size, k is avg word length
- Space: O(n) - DP array

Common Mistakes:
1. Not checking if i >= len before accessing dp[i-len]
2. Forgetting to use substring equality check
3. Not breaking early when a valid word is found

LeetCode Practice Problems:
- Word Break II (return all possible sentences)
- Concatenated Words
- Basic Calculator IV

Mini Challenge:
Modify the solution to return all possible segmentations of the string.

🖥️ Expected Console Output:
Input: s = "leetcode", wordDict = [leet, code]
Output: true

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Master""",
            type = LessonType.THEORY,
            order = 27,
            isCompleted = false,
            codeExample = """
import java.util.*;

public class Solution {

    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Empty string can always be segmented
        
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && dp[i - len] && 
                    s.substring(i - len, i).equals(word)) {
                    dp[i] = true;
                    break; // Found a valid segmentation
                }
            }
        }
        
        return dp[n];
    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> dict1 = Arrays.asList("leet", "code");
        System.out.println("Can break \"" + s1 + "\": " + wordBreak(s1, dict1));
        
        String s2 = "applepenapple";
        List<String> dict2 = Arrays.asList("apple", "pen");
        System.out.println("Can break \"" + s2 + "\": " + wordBreak(s2, dict2));
    }
}"""
        ),
        
        // DP Lesson 28: DP on Intervals (Matrix Chain concept)
        Lesson(
            id = "dp-28",
            courseId = "java-dp-patterns",
            language = "java",
            title = "DP on Intervals (Matrix Chain Multiplication Concept)",
            content = """🔴 Hard

Pattern / Topic Name: Dynamic Programming on Intervals

Intuition (Simple Explanation):
In interval DP, we solve subproblems for intervals [i, j] by dividing them into smaller intervals. For matrix chain multiplication, we try all possible split points k and calculate the minimum cost of multiplying matrices from i to k and k+1 to j, plus the cost of multiplying the results.

When to Use This Pattern:
When you need to optimize operations on continuous sequences or intervals. Common in problems involving matrix multiplication, polygon triangulation, or games with optimal strategies.

Problem Statement:
Given a sequence of matrices, find the most efficient way to multiply these matrices. The problem is not to perform the multiplications, but merely to decide the order of the matrix multiplications.

Step-by-Step Approach:
1. Create 2D DP table: dp[i][j] = min cost to multiply matrices from i to j
2. Base case: dp[i][i] = 0 (single matrix has 0 multiplication cost)
3. For each length from 2 to n:
   - For each start position i:
     - End position j = i + length - 1
     - Try all split points k from i to j-1
     - dp[i][j] = min over k of (dp[i][k] + dp[k+1][j] + cost of combining)
4. Return dp[0][n-1]

Dry Run Example:
matrices with dimensions [1, 2, 3, 4] (so 3 matrices: 1x2, 2x3, 3x4)
- dp[i][i] = 0 for all i
- Length 2: dp[0][1] = 1*2*3 = 6, dp[1][2] = 2*3*4 = 24
- Length 3: dp[0][2] = min(6+0+1*3*4, 0+24+1*2*4) = min(18, 32) = 18
Result: 18 operations

Java Runnable Code:
public class Solution {

    public static int matrixChainMultiplication(int[] dims) {
        int n = dims.length - 1; // Number of matrices
        int[][] dp = new int[n][n];
        
        // Fill the dp table for all possible chain lengths
        for (int len = 2; len <= n; len++) { // Length of chain
            for (int i = 0; i <= n - len; i++) { // Starting position
                int j = i + len - 1; // Ending position
                dp[i][j] = Integer.MAX_VALUE;
                
                // Try all possible split points
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + 
                              dims[i] * dims[k + 1] * dims[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] dims = {1, 2, 3, 4}; // 3 matrices: 1x2, 2x3, 3x4
        System.out.println("Min operations: " + matrixChainMultiplication(dims));
        
        int[] dims2 = {40, 20, 30, 10, 30}; // 4 matrices
        System.out.println("Min operations: " + matrixChainMultiplication(dims2));
    }
}

Time & Space Complexity:
- Time: O(n³) - triple nested loops
- Space: O(n²) - DP table

Common Mistakes:
1. Getting the dimensions formula wrong
2. Incorrectly setting the bounds for the loops
3. Not understanding the order of matrix dimensions

LeetCode Practice Problems:
- Burst Balloons
- Minimum Cost Tree From Leaf Values
- Strange Printer

Mini Challenge:
Adapt this solution for the palindrome partitioning problem using interval DP.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Master""",
            type = LessonType.THEORY,
            order = 28,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int matrixChainMultiplication(int[] dims) {
        int n = dims.length - 1; // Number of matrices
        int[][] dp = new int[n][n];
        
        // Fill the dp table for all possible chain lengths
        for (int len = 2; len <= n; len++) { // Length of chain
            for (int i = 0; i <= n - len; i++) { // Starting position
                int j = i + len - 1; // Ending position
                dp[i][j] = Integer.MAX_VALUE;
                
                // Try all possible split points
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + 
                              dims[i] * dims[k + 1] * dims[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] dims = {1, 2, 3, 4}; // 3 matrices: 1x2, 2x3, 3x4
        System.out.println("Min operations: " + matrixChainMultiplication(dims));
        
        int[] dims2 = {40, 20, 30, 10, 30}; // 4 matrices
        System.out.println("Min operations: " + matrixChainMultiplication(dims2));
    }
}"""
        ),
        
        // DP Lesson 29: Burst Balloons (interval DP concept-level simplified)
        Lesson(
            id = "dp-29",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Burst Balloons (Interval DP Simplified)",
            content = """🔴 Hard

Pattern / Topic Name: Dynamic Programming on Intervals

Intuition (Simple Explanation):
When bursting balloons from i to j, we consider which balloon to burst last. If balloon k is burst last, then balloons from i to k-1 and k+1 to j must be burst first. The coins earned from bursting k last is nums[i-1] * nums[k] * nums[j+1] (since i-1 and j+1 are the adjacent balloons at that moment).

When to Use This Pattern:
When you need to make choices in a sequence where the effect of a choice depends on remaining elements. This is common in problems involving removal of elements with costs depending on neighbors.

Problem Statement:
You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons. If you burst the ith balloon, you'll get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, treat it as if there is a balloon with a 1 painted on it. Return the maximum coins you can collect by bursting the balloons wisely.

Step-by-Step Approach:
1. Add boundary balloons with value 1 at both ends
2. Create DP table: dp[i][j] = max coins for bursting balloons from i to j
3. Base case: dp[i][i] = 0 (single balloon, but need neighbors)
4. For each length from 2 to n:
   - For each start position i:
     - End position j = i + length - 1
     - Try all possible last burst balloons k in [i, j]
     - dp[i][j] = max over k of (dp[i][k-1] + dp[k+1][j] + coins from bursting k last)
5. Return dp[1][n]

Dry Run Example:
nums = [3, 1, 5, 8]
After adding boundaries: [1, 3, 1, 5, 8, 1]
- dp[i][i] = 0 for all i
- For small intervals, try each balloon as last to burst
- Combine results to get max coins
Result: 167 coins

Java Runnable Code:
public class Solution {

    public static int maxCoins(int[] nums) {
        int n = nums.length;
        // Add boundary balloons with value 1
        int[] newNums = new int[n + 2];
        newNums[0] = newNums[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i];
        }
        
        // dp[i][j] = max coins for bursting balloons from i to j (exclusive)
        int[][] dp = new int[n + 2][n + 2];
        
        // Fill for all possible lengths
        for (int len = 2; len <= n + 2; len++) {
            for (int i = 0; i <= n + 2 - len; i++) {
                int j = i + len - 1;
                
                // Try each balloon k as the last to burst in range [i+1, j-1]
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], 
                        dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j]);
                }
            }
        }
        
        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 1, 5, 8};
        System.out.println("Max coins: " + maxCoins(nums1));
        
        int[] nums2 = {1, 5};
        System.out.println("Max coins: " + maxCoins(nums2));
    }
}

Time & Space Complexity:
- Time: O(n³) - triple nested loops
- Space: O(n²) - DP table

Common Mistakes:
1. Not understanding why we consider the last balloon to burst
2. Getting the formula for coins calculation wrong
3. Incorrectly setting the bounds for k in the inner loop

LeetCode Practice Problems:
- Matrix Chain Multiplication
- Minimum Cost to Merge Stones
- Predict the Winner

Mini Challenge:
Modify the solution to return the order in which balloons should be burst to achieve maximum coins.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Master""",
            type = LessonType.THEORY,
            order = 29,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int maxCoins(int[] nums) {
        int n = nums.length;
        // Add boundary balloons with value 1
        int[] newNums = new int[n + 2];
        newNums[0] = newNums[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i];
        }
        
        // dp[i][j] = max coins for bursting balloons from i to j (exclusive)
        int[][] dp = new int[n + 2][n + 2];
        
        // Fill for all possible lengths
        for (int len = 2; len <= n + 2; len++) {
            for (int i = 0; i <= n + 2 - len; i++) {
                int j = i + len - 1;
                
                // Try each balloon k as the last to burst in range [i+1, j-1]
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], 
                        dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j]);
                }
            }
        }
        
        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 1, 5, 8};
        System.out.println("Max coins: " + maxCoins(nums1));
        
        int[] nums2 = {1, 5};
        System.out.println("Max coins: " + maxCoins(nums2));
    }
}"""
        ),
        
        // DP Lesson 30: DP Pattern Recognition Lesson (meta lesson)
        Lesson(
            id = "dp-30",
            courseId = "java-dp-patterns",
            language = "java",
            title = "Dynamic Programming Pattern Recognition",
            content = """🟡 Medium

Pattern / Topic Name: DP Meta-Analysis

Intuition (Simple Explanation):
DP problems typically involve overlapping subproblems and optimal substructure. The key is recognizing that a problem can be broken down into smaller, similar subproblems whose solutions can be cached and reused.

When to Use This Pattern:
Use DP when:
1. The problem asks for COUNTING (how many ways)
2. The problem asks for OPTIMIZATION (minimum/maximum)
3. The problem asks for YES/NO decisions
4. Solutions to subproblems can be combined to solve the main problem
5. The same subproblem is computed multiple times

Problem Statement:
Given a problem description, identify if it's suitable for dynamic programming and determine the approach.

Step-by-Step Approach:
1. Identify the main objective (count, optimize, decide)
2. Look for overlapping subproblems
3. Check for optimal substructure property
4. Define the state representation
5. Formulate the recurrence relation
6. Determine base cases
7. Decide on implementation (top-down vs bottom-up)

Common DP Patterns:
- Linear DP: f(i) depends on f(i-1), f(i-2), etc.
- Range DP: f(i,j) depends on subranges of [i,j]
- Tree DP: DP on tree structures
- Bitmask DP: When states can be represented as bitmasks
- Digit DP: For problems involving digits of numbers

Dry Run Example:
Fibonacci:
- Objective: Calculate nth Fibonacci number
- Overlapping: fib(n) = fib(n-1) + fib(n-2), fib(n-1) is computed multiple times
- Optimal substructure: Solution built from solutions to smaller problems
- State: n
- Recurrence: f(n) = f(n-1) + f(n-2)
- Base cases: f(0) = 0, f(1) = 1

Java Runnable Code:
public class Solution {

    // Demonstrates different DP approaches for Fibonacci
    
    // Memoization (top-down)
    public static int fibMemo(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        return memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    }
    
    // Tabulation (bottom-up)
    public static int fibTab(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    
    // Space optimized
    public static int fibOptimized(int n) {
        if (n <= 1) return n;
        int prev2 = 0, prev1 = 1, curr = 0;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci(" + n + ") - Memo: " + fibMemo(n, new int[n + 1]));
        System.out.println("Fibonacci(" + n + ") - Tab: " + fibTab(n));
        System.out.println("Fibonacci(" + n + ") - Opt: " + fibOptimized(n));
        
        System.out.println("
DP Pattern Recognition:");
        System.out.println("1. Counting problems: Use DP when counting arrangements, paths, etc.");
        System.out.println("2. Optimization: Use DP when minimizing/maximizing a value");
        System.out.println("3. Decision: Use DP when determining possibility/validity");
        System.out.println("4. Look for overlapping subproblems and optimal substructure");
    }
}

Time & Space Complexity:
- Depends on the specific DP problem
- Generally: Time O(states × transitions), Space O(states)

Common Mistakes:
1. Not identifying when DP is applicable
2. Incorrect state definition
3. Wrong recurrence relation
4. Forgetting base cases
5. Using DP when simpler approach exists

LeetCode Practice Problems:
- Climbing Stairs
- House Robber
- Longest Increasing Subsequence
- Edit Distance

Mini Challenge:
Identify which DP pattern applies to the following: Coin Change, Longest Common Subsequence, Best Time to Buy and Sell Stock, Unique Paths.

Pattern XP: +10
Streak Bonus: Eligible
Badge: DP Master""",
            type = LessonType.THEORY,
            order = 30,
            isCompleted = false,
            codeExample = """
public class Solution {

    // Demonstrates different DP approaches for Fibonacci
    
    // Memoization (top-down)
    public static int fibMemo(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        return memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    }
    
    // Tabulation (bottom-up)
    public static int fibTab(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    
    // Space optimized
    public static int fibOptimized(int n) {
        if (n <= 1) return n;
        int prev2 = 0, prev1 = 1, curr = 0;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci(" + n + ") - Memo: " + fibMemo(n, new int[n + 1]));
        System.out.println("Fibonacci(" + n + ") - Tab: " + fibTab(n));
        System.out.println("Fibonacci(" + n + ") - Opt: " + fibOptimized(n));
        
        System.out.println("
DP Pattern Recognition:");
        System.out.println("1. Counting problems: Use DP when counting arrangements, paths, etc.");
        System.out.println("2. Optimization: Use DP when minimizing/maximizing a value");
        System.out.println("3. Decision: Use DP when determining possibility/validity");
        System.out.println("4. Look for overlapping subproblems and optimal substructure");
    }
}"""
        )
    )
}
