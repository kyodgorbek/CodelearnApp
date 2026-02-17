package com.yodgorbek.codelearnapp.data.repository

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.model.LessonType

object LeetcodePatternsRepositoryImpl {
    val leetcodePatternLessons = listOf(
        // Pattern Lesson 1: Two Pointers
        Lesson(
            id = "lp-1",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Two Pointers Technique",
            content = """🟢 Easy

Pattern / Topic Name: Two Pointers

Intuition (Simple Explanation):
Think of two people walking towards each other from opposite ends of a corridor. They can meet in the middle, or one can move faster than the other depending on the situation. The two pointers technique uses two indices to traverse an array or string efficiently.

When to Use This Pattern:
Use when you need to find pairs of elements that satisfy a condition, or when processing sorted arrays. Common scenarios include finding two numbers that sum to a target, removing duplicates, or reversing arrays.

Problem Statement:
Given a sorted array of integers and a target sum, find two numbers that add up to the target. Return their indices.

Step-by-Step Approach:
1. Initialize left pointer at start (0) and right pointer at end (n-1)
2. While pointers haven't crossed:
   - Calculate sum of elements at both pointers
   - If sum equals target: return indices
   - If sum < target: move left pointer right (increase sum)
   - If sum > target: move right pointer left (decrease sum)

Dry Run Example:
nums = [2, 7, 11, 15], target = 9
- l=0, r=3: sum=2+15=17 > 9 → r=2
- l=0, r=2: sum=2+11=13 > 9 → r=1
- l=0, r=1: sum=2+7=9 = 9 → return [0,1]

Java Runnable Code:
import java.util.*;

public class Solution {

    public static int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;  // Need larger sum
            } else {
                right--; // Need smaller sum
            }
        }

        return new int[]{-1, -1}; // Not found
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);

        int[] result = twoSum(nums, target);
        System.out.println("Output: " + Arrays.toString(result));
    }
}

Time & Space Complexity:
- Time: O(n) - at most one pass through the array
- Space: O(1) - only using two pointer variables

Common Mistakes:
1. Not checking if array is sorted before applying two pointers
2. Forgetting to handle the case when no solution exists
3. Off-by-one errors when moving pointers

LeetCode Practice Problems:
- Two Sum II (Input array is sorted)
- Container With Most Water
- 3Sum

Mini Challenge:
Modify the solution to find all pairs that sum to the target value.

🖥️ Expected Console Output:
Input: nums = [2, 7, 11, 15], target = 9
Output: [0, 1]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
public class Solution {

    public static int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;  // Need larger sum
            } else {
                right--; // Need smaller sum
            }
        }

        return new int[]{-1, -1}; // Not found
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);

        int[] result = twoSum(nums, target);
        System.out.println("Output: " + Arrays.toString(result));
    }
}"""
        ),

        // Pattern Lesson 2: Fast & Slow Pointers
        Lesson(
            id = "lp-2",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Fast & Slow Pointers (Floyd's Cycle Detection)",
            content = """🟢 Easy

Pattern / Topic Name: Fast & Slow Pointers

Intuition (Simple Explanation):
Imagine two runners on a circular track, where one runs twice as fast as the other. The fast runner will eventually lap the slow runner. This technique is perfect for detecting cycles in linked lists or arrays.

When to Use This Pattern:
Use when you need to detect cycles in a sequence, find the middle element, or when dealing with linked lists where you can't go backwards. It's particularly effective for cycle detection and finding duplicate numbers.

Problem Statement:
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

Step-by-Step Approach:
1. Use two pointers: slow moves 1 step, fast moves 2 steps
2. If there's a cycle, they will meet at some point inside the cycle
3. Reset one pointer to head, keep the other at meeting point
4. Move both pointers at same speed (1 step each) - they'll meet at cycle start

Dry Run Example:
List: 3 -> 2 -> 0 -> -4 -> (points back to 2)
- Phase 1: Fast and slow pointers move, they meet inside cycle
- Phase 2: Reset one to head, move both at same speed until they meet
- Meeting point is start of cycle

Java Runnable Code:
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break; // Cycle detected
            }
        }

        // If no cycle
        if (fast == null || fast.next == null) {
            return null;
        }

        // Phase 2: Find start of cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // Start of cycle
    }

    public static void main(String[] args) {
        // Create cycle: 3->2->0->-4->2 (cycle starts at index 1)
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // Creates cycle

        System.out.println("Input: Linked list with cycle at node 2");

        ListNode cycleStart = detectCycle(head);
        System.out.println("Output: " + (cycleStart != null ? cycleStart.val : "null"));
    }
}

Time & Space Complexity:
- Time: O(n) - both phases combined
- Space: O(1) - only using two pointers

Common Mistakes:
1. Not handling edge cases (empty list, single node)
2. Forgetting to check if fast.next is null before accessing fast.next.next
3. Not understanding why the second phase works

LeetCode Practice Problems:
- Linked List Cycle
- Happy Number
- Find the Duplicate Number

Mini Challenge:
Apply this technique to find the entrance to a cycle in an array where each element points to another index.

🖥️ Expected Console Output:
Input: Linked list with cycle at node 2
Output: 2

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break; // Cycle detected
            }
        }

        // If no cycle
        if (fast == null || fast.next == null) {
            return null;
        }

        // Phase 2: Find start of cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // Start of cycle
    }

    public static void main(String[] args) {
        // Create cycle: 3->2->0->-4->2 (cycle starts at index 1)
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // Creates cycle

        System.out.println("Input: Linked list with cycle at node 2");

        ListNode cycleStart = detectCycle(head);
        System.out.println("Output: " + (cycleStart != null ? cycleStart.val : "null"));
    }
}"""
        ),

        // Pattern Lesson 3: Sliding Window (Fixed)
        Lesson(
            id = "lp-3",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Sliding Window (Fixed Size)",
            content = """🟢 Easy

Pattern / Topic Name: Sliding Window

Intuition (Simple Explanation):
Think of a camera with a fixed frame moving across a landscape. As it moves, it captures a fixed-width view of the scene. Similarly, a fixed-size sliding window maintains a constant number of elements while moving through an array.

When to Use This Pattern:
Use when you need to analyze subarrays of fixed size k. Common applications include finding maximum/minimum in each subarray of size k, calculating averages, or finding subarrays with specific properties.

Problem Statement:
Given an array of integers and a number k, find the maximum value in each subarray of length k.

Step-by-Step Approach:
1. Initialize window from index 0 to k-1
2. Calculate initial result for this window
3. Slide window one position at a time:
   - Remove element going out of window (leftmost)
   - Add element coming into window (rightmost)
   - Update result for new window
4. Continue until window reaches end of array

Dry Run Example:
arr = [1, 2, 3, 4, 5, 6], k = 3
- Window [1,2,3]: max = 3
- Window [2,3,4]: max = 4
- Window [3,4,5]: max = 5
- Window [4,5,6]: max = 6
Result: [3, 4, 5, 6]

Java Runnable Code:
import java.util.*;

class Solution {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        if (n < k) {
            return new int[0];
        }

        int[] result = new int[n - k + 1];

        // Process each window
        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            // Find max in current window
            for (int j = i + 1; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", k = " + k);

        int[] result = maxSlidingWindow(nums, k);
        System.out.println("Output: " + Arrays.toString(result));
    }
}

Time & Space Complexity:
- Time: O(n*k) - for each of n-k+1 windows, we scan k elements
- Space: O(1) - excluding output array

Common Mistakes:
1. Not handling edge cases (k > array length)
2. Off-by-one errors in loop bounds
3. Forgetting to reset max for each window

LeetCode Practice Problems:
- Sliding Window Maximum
- Find All Anagrams in a String
- Permutation in String

Mini Challenge:
Optimize the solution to run in O(n) time using a deque.

🖥️ Expected Console Output:
Input: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
Output: [3, 3, 5, 5, 6, 7]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 3,
            isCompleted = false,
            codeExample = """
class Solution {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        if (n < k) {
            return new int[0];
        }

        int[] result = new int[n - k + 1];

        // Process each window
        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            // Find max in current window
            for (int j = i + 1; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", k = " + k);

        int[] result = maxSlidingWindow(nums, k);
        System.out.println("Output: " + Arrays.toString(result));
    }
}"""
        ),

        // Pattern Lesson 4: Sliding Window (Variable)
        Lesson(
            id = "lp-4",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Sliding Window (Variable Size)",
            content = """🟡 Medium

Pattern / Topic Name: Sliding Window

Intuition (Simple Explanation):
Like a stretchable window that expands and contracts based on certain conditions. Expand when condition is met, contract when it's violated. This is useful for finding minimum/maximum sized subarrays/substrigs satisfying certain criteria.

When to Use This Pattern:
Use when you need to find subarrays/substrigs that satisfy certain conditions, especially when the size isn't fixed. Common in problems asking for minimum/maximum length subarrays with specific properties.

Problem Statement:
Given an array of positive numbers and a positive number 'S', find the length of the smallest contiguous subarray whose sum is greater than or equal to 'S'. Return 0 if no such subarray exists.

Step-by-Step Approach:
1. Initialize two pointers: left and right at start
2. Expand window by moving right pointer and adding elements
3. Contract window by moving left pointer when sum >= S
4. Track minimum window size that satisfies condition
5. Continue until right pointer reaches end

Dry Run Example:
arr = [2, 1, 2, 4, 3, 5], S = 7
- [2] (sum=2) < 7, expand
- [2,1] (sum=3) < 7, expand
- [2,1,2] (sum=5) < 7, expand
- [2,1,2,4] (sum=9) >= 7, update min=4, contract
- [1,2,4] (sum=7) >= 7, update min=3, contract
- [2,4] (sum=6) < 7, expand
- [2,4,3] (sum=9) >= 7, update min=3, contract
- [4,3] (sum=7) >= 7, update min=2, contract
- [3] (sum=3) < 7, expand
- [3,5] (sum=8) >= 7, min still 2
Result: 2

Java Runnable Code:
import java.util.*;

class Solution {

    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int left = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            // Contract window while sum >= s
            while (sum >= s) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3, 5};
        int s = 7;
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", s = " + s);

        int result = minSubArrayLen(s, nums);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n) - each element is visited at most twice
- Space: O(1) - only using pointers and variables

Common Mistakes:
1. Not properly handling the contraction phase
2. Forgetting to update the minimum when condition is met
3. Not resetting sum properly during contraction

LeetCode Practice Problems:
- Longest Substring Without Repeating Characters
- Minimum Window Substring
- Fruits into Baskets

Mini Challenge:
Modify the solution to return the actual subarray that gives the minimum length.

🖥️ Expected Console Output:
Input: nums = [2, 1, 2, 4, 3, 5], s = 7
Output: 2

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = """
class Solution {

    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int left = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            // Contract window while sum >= s
            while (sum >= s) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3, 5};
        int s = 7;
        System.out.println("Minimum length subarray with sum >= " + s + ": " +
                          minSubArrayLen(s, nums));
    }
}"""
        ),

        // Pattern Lesson 5: Prefix Sum
        Lesson(
            id = "lp-5",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Prefix Sum Technique",
            content = """🟢 Easy

Pattern / Topic Name: Prefix Sum

Intuition (Simple Explanation):
Think of a running total that accumulates values as you go. If you know the total up to position i and up to position j, you can find the sum between i and j by subtracting the totals. This allows O(1) range sum queries after O(n) preprocessing.

When to Use This Pattern:
Use when you need to repeatedly calculate sums of subarrays. Also useful for finding subarrays with specific sums, differences, or other properties. Common in problems involving cumulative values.

Problem Statement:
Given an array of integers, handle multiple range sum queries efficiently. For each query [i, j], return the sum of elements from index i to j.

Step-by-Step Approach:
1. Precompute prefix sums: prefix[i] = sum of elements from 0 to i-1
2. For range query [i, j]: result = prefix[j+1] - prefix[i]
3. This works because prefix[j+1] contains sum(0,j) and prefix[i] contains sum(0,i-1)
4. Their difference gives sum(i,j)

Dry Run Example:
arr = [1, 3, 5, 7, 9]
prefix = [0, 1, 4, 9, 16, 25]
Query [1,3]: sum = prefix[4] - prefix[1] = 16 - 1 = 15
Verify: arr[1] + arr[2] + arr[3] = 3 + 5 + 7 = 15 ✓

Java Runnable Code:
import java.util.*;

class Solution {

    static class NumArray {
        private int[] prefix;

        public NumArray(int[] nums) {
            int n = nums.length;
            prefix = new int[n + 1];

            for (int i = 0; i < n; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return prefix[right + 1] - prefix[left];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        System.out.println("Input: nums = " + Arrays.toString(nums));

        NumArray numArray = new NumArray(nums);
        int result = numArray.sumRange(0, 2);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Preprocessing: O(n) - build prefix array
- Query: O(1) - constant time range sum
- Space: O(n) - for prefix array

Common Mistakes:
1. Off-by-one errors in prefix array indexing
2. Not accounting for empty ranges
3. Forgetting to handle edge cases

LeetCode Practice Problems:
- Range Sum Query 2D - Immutable
- Contiguous Array
- Subarray Sum Equals K

Mini Challenge:
Use prefix sums to find the number of subarrays with sum equal to a given target.

🖥️ Expected Console Output:
Input: nums = [1, 3, 5, 7, 9]
Output: 9

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 5,
            isCompleted = false,
            codeExample = """
class Solution {

    static class NumArray {
        private int[] prefix;

        public NumArray(int[] nums) {
            int n = nums.length;
            prefix = new int[n + 1];

            for (int i = 0; i < n; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return prefix[right + 1] - prefix[left];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        NumArray numArray = new NumArray(nums);

        System.out.println("Sum from index 0 to 2: " + numArray.sumRange(0, 2));
        System.out.println("Sum from index 1 to 4: " + numArray.sumRange(1, 4));
        System.out.println("Sum from index 2 to 3: " + numArray.sumRange(2, 3));
    }
}"""
        ),

        // Pattern Lesson 6: Difference Array
        Lesson(
            id = "lp-6",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Difference Array Technique",
            content = """🟡 Medium

Pattern / Topic Name: Difference Array

Intuition (Simple Explanation):
Instead of storing actual values, store the differences between consecutive elements. This allows efficient range updates. When you want to add a value to a range [i,j], you only need to update diff[i] and diff[j+1].

When to Use This Pattern:
Use when you need to perform multiple range updates efficiently. This is the inverse of prefix sum - instead of enabling fast range queries, it enables fast range updates. Common in problems with multiple range additions.

Problem Statement:
Given an array and multiple range update operations (add a value to a range), return the final array after all operations.

Step-by-Step Approach:
1. Create a difference array of size n+1 (extra space to handle boundary)
2. For each range update [start, end, value]:
   - Add value to diff[start]
   - Subtract value from diff[end+1]
3. Convert difference array back to result array using prefix sum

Dry Run Example:
Initial array: [0, 0, 0, 0, 0] (size 5)
Operations: [0,2,1], [1,3,2], [2,4,3]
- After [0,2,1]: diff=[1,0,0,-1,0,0], result=[1,1,1,0,0]
- After [1,3,2]: diff=[1,2,0,-1,-2,0], result=[1,3,3,2,0]
- After [2,4,3]: diff=[1,2,3,-1,-2,-3], result=[1,3,6,5,3]

Java Runnable Code:
import java.util.*;

class Solution {

    public static int[] getModifiedArray(int length, int[][] updates) {
        int[] diff = new int[length + 1];

        // Apply updates to difference array
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int inc = update[2];

            diff[start] += inc;
            if (end + 1 < length) {
                diff[end + 1] -= inc;
            }
        }

        // Convert difference array back to result array
        int[] result = new int[length];
        result[0] = diff[0];
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] + diff[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int length = 5;
        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        System.out.println("Input: length = " + length + ", updates = " + Arrays.deepToString(updates));

        int[] result = getModifiedArray(length, updates);
        System.out.println("Output: " + Arrays.toString(result));
    }
}

Time & Space Complexity:
- Time: O(n + k) where n is array length, k is number of updates
- Space: O(n) - for difference array

Common Mistakes:
1. Not handling the boundary condition (end+1 >= length)
2. Confusing the conversion from difference array to result
3. Off-by-one errors in indexing

LeetCode Practice Problems:
- Corporate Flight Bookings
- Range Addition II
- Car Pooling

Mini Challenge:
Extend the solution to handle 2D range updates efficiently.

🖥️ Expected Console Output:
Input: length = 5, updates = [[1, 3, 2], [2, 4, 3], [0, 2, -2]]
Output: [-2, 0, 3, 5, 3]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
class Solution {

    public static int[] getModifiedArray(int length, int[][] updates) {
        int[] diff = new int[length + 1];

        // Apply updates to difference array
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int inc = update[2];

            diff[start] += inc;
            if (end + 1 < length) {
                diff[end + 1] -= inc;
            }
        }

        // Convert difference array back to result array
        int[] result = new int[length];
        result[0] = diff[0];
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] + diff[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int length = 5;
        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        int[] result = getModifiedArray(length, updates);

        System.out.print("Final array: ");
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}"""
        ),

        // Pattern Lesson 7: Merge Intervals
        Lesson(
            id = "lp-7",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Merge Intervals",
            content = """🟡 Medium

Pattern / Topic Name: Interval Manipulation

Intuition (Simple Explanation):
Think of time slots that might overlap. If two events overlap in time, they can be merged into a single time slot. Sort intervals by start time, then merge overlapping ones by extending the end time to the maximum of both intervals.

When to Use This Pattern:
Use when you need to process, combine, or manipulate intervals that might overlap. Common in scheduling problems, calendar applications, or when merging ranges of values.

Problem Statement:
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals and return an array of the non-overlapping intervals that cover all the intervals in the input.

Step-by-Step Approach:
1. Sort intervals by start time
2. Initialize result list with first interval
3. For each subsequent interval:
   - If current interval overlaps with the last merged interval, merge them
   - Otherwise, add current interval as new interval
4. Two intervals [a,b] and [c,d] overlap if b >= c

Dry Run Example:
intervals = [[1,3],[2,6],[8,10],[15,18]]
- Sorted: [[1,3],[2,6],[8,10],[15,18]] (already sorted)
- Start with [1,3]
- [2,6] overlaps with [1,3] (3 >= 2), merge to [1,6]
- [8,10] doesn't overlap with [1,6] (6 < 8), add as new
- [15,18] doesn't overlap with [8,10] (10 < 15), add as new
Result: [[1,6],[8,10],[15,18]]

Java Runnable Code:
import java.util.*;

class Solution {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] lastMerged = result.get(result.size() - 1);

            // Check if intervals overlap
            if (current[0] <= lastMerged[1]) {
                // Merge by extending end time
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                // No overlap, add as new interval
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Input: intervals = " + Arrays.deepToString(intervals));

        int[][] merged = merge(intervals);
        System.out.println("Output: " + Arrays.deepToString(merged));
    }
}

Time & Space Complexity:
- Time: O(n log n) - dominated by sorting
- Space: O(n) - for result list

Common Mistakes:
1. Forgetting to sort intervals first
2. Incorrect overlap condition check
3. Not handling edge cases (empty input)

LeetCode Practice Problems:
- Insert Interval
- Meeting Rooms II
- Employee Free Time

Mini Challenge:
Modify the solution to find the intersection of intervals instead of merging.

🖥️ Expected Console Output:
Input: intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]
Output: [[1, 6], [8, 10], [15, 18]]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] lastMerged = result.get(result.size() - 1);

            // Check if intervals overlap
            if (current[0] <= lastMerged[1]) {
                // Merge by extending end time
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                // No overlap, add as new interval
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merged = merge(intervals);

        System.out.println("Merged intervals:");
        for (int[] interval : merged) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}"""
        ),

        // Pattern Lesson 8: Cyclic Sort
        Lesson(
            id = "lp-8",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Cyclic Sort",
            content = """🟢 Easy

Pattern / Topic Name: Cyclic Sort

Intuition (Simple Explanation):
When elements are in a known range (typically 1 to n) and each number appears once, we can place each number directly at its correct index. If we encounter a number that's already at its correct position, we've found a duplicate.

When to Use This Pattern:
Use when dealing with arrays containing numbers in a specific range (usually 1 to n). This is especially useful for finding missing numbers, duplicates, or placing elements in their correct positions.

Problem Statement:
Given an array of n integers where each integer is in the range [1, n], find all numbers in the range [1, n] that do not appear in the array.

Step-by-Step Approach:
1. Place each number in its correct position: number x goes to index x-1
2. Iterate through the array, swapping elements to their correct positions
3. After sorting, any index i that doesn't contain i+1 is missing

Dry Run Example:
nums = [4, 3, 2, 7, 8, 2, 3, 1]
- Place 4 at index 3: [7, 3, 2, 4, 8, 2, 3, 1]
- Place 7 at index 6: [3, 3, 2, 4, 8, 2, 7, 1]
- Continue until array is cyclically sorted
- Check which indices don't have correct values

Java Runnable Code:
import java.util.*;

class Solution {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;

        // Cyclic sort: place each number at its correct index
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;

            // If number is not at its correct position, swap
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        // Find missing numbers
        List<Integer> result = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }

        return result;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Input: nums = " + Arrays.toString(nums));

        List<Integer> result = findDisappearedNumbers(nums);
        System.out.println("Output: " + result.toString());
    }
}

Time & Space Complexity:
- Time: O(n) - although there's a nested loop, each element is moved at most once
- Space: O(1) - only using constant extra space

Common Mistakes:
1. Not checking if the swapped element is in correct position before moving forward
2. Forgetting to handle duplicate values properly
3. Index off-by-one errors

LeetCode Practice Problems:
- Find the Duplicate Number
- First Missing Positive
- Set Mismatch

Mini Challenge:
Modify the solution to find all duplicate numbers in the array.

🖥️ Expected Console Output:
Input: nums = [4, 3, 2, 7, 8, 2, 3, 1]
Output: [5, 6]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 8,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;

        // Cyclic sort: place each number at its correct index
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;

            // If number is not at its correct position, swap
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        // Find missing numbers
        List<Integer> result = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }

        return result;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> missing = findDisappearedNumbers(nums);
        System.out.println("Missing numbers: " + missing);
    }
}"""
        ),

        // Pattern Lesson 9: Top K Elements (Heap)
        Lesson(
            id = "lp-9",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Top K Elements (Using Heap)",
            content = """🟡 Medium

Pattern / Topic Name: Heap / Priority Queue

Intuition (Simple Explanation):
Use a heap data structure to efficiently maintain the top K elements. For the largest K elements, use a min-heap of size K. For the smallest K elements, use a max-heap of size K.

When to Use This Pattern:
Use when you need to find the top K elements, bottom K elements, or Kth largest/smallest element. This is efficient when K is much smaller than the total number of elements.

Problem Statement:
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Step-by-Step Approach:
1. Count frequency of each element using a HashMap
2. Use a min-heap to keep the top k frequent elements
3. For each unique element:
   - Add to heap
   - If heap size > k, remove the smallest frequency element
4. Extract elements from heap

Dry Run Example:
nums = [1,1,1,2,2,3], k = 2
- Frequency map: {1:3, 2:2, 3:1}
- Add to min-heap: [1], [1,2], [1,2,3]
- Remove smallest when size > k: [2,1]
Result: [1,2] (most frequent)

Java Runnable Code:
import java.util.*;

class Solution {

    public static int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Use min-heap to keep top k elements
        PriorityQueue<Integer> minHeap =
            new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

        for (int num : freqMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the least frequent
            }
        }

        // Extract results
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", k = " + k);

        int[] result = topKFrequent(nums, k);
        System.out.println("Output: " + Arrays.toString(result));
    }
}

Time & Space Complexity:
- Time: O(n log k) - n insertions into heap of max size k
- Space: O(n + k) - for frequency map and heap

Common Mistakes:
1. Using max-heap when min-heap is needed (or vice versa)
2. Not controlling heap size to k
3. Incorrect comparator implementation

LeetCode Practice Problems:
- Kth Largest Element in an Array
- Sort Characters By Frequency
- K Closest Points to Origin

Mini Challenge:
Modify the solution to return the k most frequent elements in descending order of frequency.

🖥️ Expected Console Output:
Input: nums = [1, 1, 1, 2, 2, 3], k = 2
Output: [1, 2]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 9,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Use min-heap to keep top k elements
        PriorityQueue<Integer> minHeap =
            new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

        for (int num : freqMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the least frequent
            }
        }

        // Extract results
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] topK = topKFrequent(nums, k);
        System.out.print("Top " + k + " frequent elements: ");
        for (int num : topK) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}"""
        ),

        // Pattern Lesson 10: K-Way Merge
        Lesson(
            id = "lp-10",
            courseId = "leetcode-patterns",
            language = "java",
            title = "K-Way Merge",
            content = """🟡 Medium

Pattern / Topic Name: K-Way Merge

Intuition (Simple Explanation):
Like merging two sorted arrays, but now we have k sorted arrays. Use a min-heap to keep track of the smallest elements from each array. Repeatedly extract the minimum and add the next element from the same array.

When to Use This Pattern:
Use when you need to merge k sorted arrays or find the smallest elements across multiple sorted sequences. This is also useful for finding the Kth smallest element among k sorted arrays.

Problem Statement:
Given k sorted linked lists, merge all of them into one sorted linked list.

Step-by-Step Approach:
1. Create a min-heap containing the first element of each list
2. While heap is not empty:
   - Extract minimum element
   - Add it to the result list
   - Add the next element from the same list to the heap
3. Return the merged list

Dry Run Example:
lists = [[1,4,5],[1,3,4],[2,6]]
- Heap starts with [1,1,2]
- Extract 1, add next from first list: [1,4]
- Extract 1, add next from second list: [4,2,3]
- Continue until all elements processed
Result: [1,1,2,3,4,4,5,6]

Java Runnable Code:
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap to keep track of smallest elements from each list
        PriorityQueue<ListNode> minHeap =
            new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add first node of each list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Process nodes in heap
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            // Add next node from the same list
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Create sample lists: [1,4,5], [1,3,4], [2,6]
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        System.out.println("Input: lists = [[1,4,5], [1,3,4], [2,6]]");

        ListNode merged = mergeKLists(lists);
        System.out.println("Output: " + getLinkedListValues(merged));
    }

    private static String getLinkedListValues(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

Time & Space Complexity:
- Time: O(N log k) where N is total number of nodes and k is number of lists
- Space: O(k) for the heap

Common Mistakes:
1. Not handling null lists properly
2. Forgetting to add next element to heap after extraction
3. Incorrect comparator implementation

LeetCode Practice Problems:
- Merge Two Sorted Lists
- Ugly Number II
- Find K Pairs with Smallest Sums

Mini Challenge:
Modify the solution to merge k sorted arrays instead of linked lists.

🖥️ Expected Console Output:
Input: lists = [[1,4,5], [1,3,4], [2,6]]
Output: [1, 1, 2, 3, 4, 4, 5, 6]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 10,
            isCompleted = false,
            codeExample = """
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap to keep track of smallest elements from each list
        PriorityQueue<ListNode> minHeap =
            new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add first node of each list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Process nodes in heap
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            // Add next node from the same list
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Create sample lists: [1,4,5], [1,3,4], [2,6]
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        ListNode merged = mergeKLists(lists);

        System.out.print("Merged list: ");
        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }
        System.out.println();
    }
}"""
        ),

        // Pattern Lesson 11: Linked List Reversal
        Lesson(
            id = "lp-11",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Linked List Reversal",
            content = """🟢 Easy

Pattern / Topic Name: Linked List Manipulation

Intuition (Simple Explanation):
Reversing a linked list is like changing the direction of arrows. Each node originally points to the next node, but after reversal, each node points to the previous node. We need to keep track of the previous node as we traverse.

When to Use This Pattern:
Use when you need to change the order of elements in a linked list, or when solving problems that require processing nodes in reverse order. This is fundamental for many linked list problems.

Problem Statement:
Given the head of a singly linked list, reverse the list and return the reversed list.

Step-by-Step Approach:
1. Initialize three pointers: prev (null), current (head), next (null)
2. Iterate through the list:
   - Store the next node before changing the pointer
   - Reverse the current node's pointer to point to prev
   - Move prev and current one step forward
3. Return prev (which becomes the new head)

Dry Run Example:
Original: 1 -> 2 -> 3 -> null
Step 1: prev=null, cur=1, next=2 | 1->null, prev=1, cur=2
Step 2: prev=1, cur=2, next=3   | 2->1->null, prev=2, cur=3
Step 3: prev=2, cur=3, next=null| 3->2->1->null, prev=3, cur=null
Result: 3 -> 2 -> 1 -> null

Java Runnable Code:
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next; // Store next node
            current.next = prev;          // Reverse the pointer
            prev = current;               // Move prev forward
            current = next;               // Move current forward
        }

        return prev; // prev is now the new head
    }

    public static void main(String[] args) {
        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Input: Linked list [1, 2, 3, 4, 5]");

        ListNode reversed = reverseList(head);

        System.out.println("Output: " + getListValues(reversed));
    }

    public static String getListValues(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

Time & Space Complexity:
- Time: O(n) - visit each node once
- Space: O(1) - only using constant extra space

Common Mistakes:
1. Forgetting to store the next node before changing the pointer
2. Incorrectly setting up the initial pointers
3. Not handling edge cases (empty list, single node)

LeetCode Practice Problems:
- Reverse Linked List II
- Palindrome Linked List
- Rotate List

Mini Challenge:
Reverse the linked list in groups of k nodes.

🖥️ Expected Console Output:
Input: Linked list [1, 2, 3, 4, 5]
Output: [5, 4, 3, 2, 1]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 11,
            isCompleted = false,
            codeExample = """
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next; // Store next node
            current.next = prev;          // Reverse the pointer
            prev = current;               // Move prev forward
            current = next;               // Move current forward
        }

        return prev; // prev is now the new head
    }

    public static void main(String[] args) {
        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.print("Original: ");
        printList(head);

        ListNode reversed = reverseList(head);

        System.out.print("Reversed: ");
        printList(reversed);
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}"""
        ),

        // Pattern Lesson 12: Linked List Cycle Start
        Lesson(
            id = "lp-12",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Linked List Cycle Start",
            content = """🟡 Medium

Pattern / Topic Name: Fast & Slow Pointers

Intuition (Simple Explanation):
Uses the Floyd's Cycle Detection algorithm (also known as the tortoise and hare algorithm). First, detect if there's a cycle using fast and slow pointers. Then, to find where the cycle begins, reset one pointer to the head and move both at the same pace.

When to Use This Pattern:
Use when you need to detect cycles in linked lists or find the start of a cycle. This is also applicable to problems involving repeated elements in arrays or finding duplicates.

Problem Statement:
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

Step-by-Step Approach:
1. Use fast and slow pointers to detect if a cycle exists
2. If a cycle exists, find where it begins:
   - Reset one pointer to the head
   - Move both pointers at the same speed
   - Where they meet is the start of the cycle

Dry Run Example:
List: 3 -> 2 -> 0 -> -4 -> 2 (cycle from -4 back to 2)
Phase 1: Fast and slow pointers meet inside the cycle
Phase 2: Reset one pointer to head, move both at same speed
They meet at node with value 2 (start of cycle)

Java Runnable Code:
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if there is a cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break; // Cycle detected
            }
        }

        // If no cycle exists
        if (fast == null || fast.next == null) {
            return null;
        }

        // Phase 2: Find the start of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // Start of the cycle
    }

    public static void main(String[] args) {
        // Create a linked list with a cycle
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        head.next = node2;
        head.next.next = node3;
        head.next.next.next = node4;
        head.next.next.next.next = node2; // Cycle: -4 -> 2

        System.out.println("Input: Linked list with cycle at node 2");

        ListNode cycleStart = detectCycle(head);
        System.out.println("Output: " + (cycleStart != null ? cycleStart.val : "null"));
    }
}

Time & Space Complexity:
- Time: O(n) - both phases combined
- Space: O(1) - only using two pointers

Common Mistakes:
1. Not properly handling the case where there's no cycle
2. Forgetting to check if fast.next is null before accessing fast.next.next
3. Not understanding why the second phase works mathematically

LeetCode Practice Problems:
- Linked List Cycle
- Find the Duplicate Number
- Happy Number

Mini Challenge:
Detect if a cycle exists and return its length.

🖥️ Expected Console Output:
Input: Linked list with cycle at node 2
Output: 2

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = """
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if there is a cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break; // Cycle detected
            }
        }

        // If no cycle exists
        if (fast == null || fast.next == null) {
            return null;
        }

        // Phase 2: Find the start of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // Start of the cycle
    }

    public static void main(String[] args) {
        // Create a linked list with a cycle
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        head.next = node2;
        head.next.next = node3;
        head.next.next.next = node4;
        head.next.next.next.next = node2; // Cycle: -4 -> 2

        ListNode cycleStart = detectCycle(head);
        if (cycleStart != null) {
            System.out.println("Cycle starts at node with value: " + cycleStart.val);
        } else {
            System.out.println("No cycle found");
        }
    }
}"""
        ),

        // Pattern Lesson 13: Tree BFS
        Lesson(
            id = "lp-13",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Tree BFS (Level Order Traversal)",
            content = """🟢 Easy

Pattern / Topic Name: Breadth-First Search on Trees

Intuition (Simple Explanation):
Like ripples spreading from a stone thrown in water, BFS explores the tree level by level. We start from the root, visit all nodes at depth 1, then all nodes at level 2, and so on.

When to Use This Pattern:
Use when you need to process nodes level by level, find the shortest path in unweighted trees, or when you need to process nodes at the same depth together. This is ideal for level-order traversal and tree visualization.

Problem Statement:
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Step-by-Step Approach:
1. Initialize a queue with the root node
2. While queue is not empty:
   - Get the current level size
   - Process all nodes at the current level
   - Add their children to the queue for the next level
3. Store each level's values separately

Dry Run Example:
Tree:     3
        / \
       9   20
          /  \
         15   7
Level 0: [3] -> queue: [3]
Process level 0: add 3 to level list, add children 9,20 to queue
Level 1: [9,20] -> queue: [15,7]
Process level 1: add 9,20 to level list, add children to queue
Level 2: [15,7] -> queue: []
Result: [[3],[9,20],[15,7]]

Java Runnable Code:
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // Add children to queue for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        // Create tree: [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Input: Tree with root = 3, left = 9, right = 20, right.left = 15, right.right = 7");

        List<List<Integer>> levels = levelOrder(root);
        System.out.println("Output: " + levels);
    }
}

Time & Space Complexity:
- Time: O(n) - visit each node once
- Space: O(w) where w is the maximum width of the tree

Common Mistakes:
1. Not capturing the level size before processing nodes
2. Forgetting to add children to the queue
3. Not handling null nodes properly

LeetCode Practice Problems:
- Binary Tree Right Side View
- Average of Levels in Binary Tree
- Binary Tree Zigzag Level Order Traversal

Mini Challenge:
Modify the solution to return the tree in zigzag order (left to right, then right to left, alternating).

🖥️ Expected Console Output:
Input: Tree with root = 3, left = 9, right = 20, right.left = 15, right.right = 7
Output: [[3], [9, 20], [15, 7]]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 13,
            isCompleted = false,
            codeExample = """
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // Add children to queue for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        // Create tree: [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> levels = levelOrder(root);
        System.out.println("Level order traversal: " + levels);
    }
}"""
        ),

        // Pattern Lesson 14: Tree DFS
        Lesson(
            id = "lp-14",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Tree DFS (Depth-First Search)",
            content = """🟢 Easy

Pattern / Topic Name: Depth-First Search on Trees

Intuition (Simple Explanation):
Like exploring a maze, DFS goes as deep as possible down one path before backtracking. It explores one branch completely before moving to the next. This can be done in three orders: pre-order (root-left-right), in-order (left-root-right), or post-order (left-right-root).

When to Use This Pattern:
Use when you need to explore paths deeply, when you need to process a node before or after its children, or when memory usage is a concern (DFS typically uses less memory than BFS). This is ideal for path-related problems and tree validation.

Problem Statement:
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Step-by-Step Approach:
1. Recursively traverse left subtree
2. Process current node
3. Recursively traverse right subtree
4. For iterative approach, use a stack to simulate recursion

Dry Run Example:
Tree:   1
         \
          2
         /
        3
Inorder: Left, Root, Right
- Go to leftmost (1, no left child)
- Process 1, go to right (null)
- Backtrack to 1, go to right subtree (2)
- In 2, go to leftmost (3)
- Process 3, no right child
- Back to 2, no right child
Result: [1, 3, 2]

Java Runnable Code:
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    // Recursive approach
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderHelper(node.left, result);   // Visit left subtree
            result.add(node.val);               // Process current node
            inorderHelper(node.right, result);  // Visit right subtree
        }
    }

    // Iterative approach
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process current node
            current = stack.pop();
            result.add(current.val);

            // Visit right subtree
            current = current.right;
        }

        return result;
    }

    public static void main(String[] args) {
        // Create tree: [1,null,2,3]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println("Input: Tree with root = 1, right = 2, right.left = 3");

        List<Integer> recursive = inorderTraversal(root);
        System.out.println("Output: " + recursive);
    }
}

Time & Space Complexity:
- Time: O(n) - visit each node once
- Space: O(h) where h is the height of the tree (recursion stack)

Common Mistakes:
1. Forgetting to check for null nodes
2. Getting the order wrong (pre-order vs in-order vs post-order)
3. In iterative version, forgetting to move to right subtree

LeetCode Practice Problems:
- Binary Tree Preorder Traversal
- Binary Tree Postorder Traversal
- Maximum Depth of Binary Tree

Mini Challenge:
Implement all three traversals (pre-order, in-order, post-order) iteratively using a stack.

🖥️ Expected Console Output:
Input: Tree with root = 1, right = 2, right.left = 3
Output: [1, 3, 2]

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    // Recursive approach
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderHelper(node.left, result);   // Visit left subtree
            result.add(node.val);               // Process current node
            inorderHelper(node.right, result);  // Visit right subtree
        }
    }

    // Iterative approach
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process current node
            current = stack.pop();
            result.add(current.val);

            // Visit right subtree
            current = current.right;
        }

        return result;
    }

    public static void main(String[] args) {
        // Create tree: [1,null,2,3]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> recursive = inorderTraversal(root);
        System.out.println("Recursive inorder: " + recursive);

        List<Integer> iterative = inorderTraversalIterative(root);
        System.out.println("Iterative inorder: " + iterative);
    }
}"""
        ),

        // Pattern Lesson 15: Binary Search Tree Patterns
        Lesson(
            id = "lp-15",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Binary Search Tree Patterns",
            content = """🟡 Medium

Pattern / Topic Name: Binary Search Tree Properties

Intuition (Simple Explanation):
BSTs maintain the property that all nodes in the left subtree are smaller than the root, and all nodes in the right subtree are larger. This allows efficient searching, insertion, and deletion operations. In-order traversal of a BST gives a sorted sequence.

When to Use This Pattern:
Use when you need to maintain sorted data with efficient insertion, deletion, and search operations. BSTs are ideal for problems requiring range queries, successor/predecessor finding, or maintaining order statistics.

Problem Statement:
Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k.

Step-by-Step Approach:
1. Perform in-order traversal to get sorted array of values
2. Use two-pointer technique on the sorted array
3. Alternatively, use a HashSet to store visited values and check complement

Dry Run Example:
BST:     5
       /   \
      3     6
     / \     \
    2   4     7
k = 9
- Inorder: [2, 3, 4, 5, 6, 7]
- Two pointers: left=0, right=5, sum=2+7=9=k ✓
Result: true

Java Runnable Code:
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public static boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    private static boolean dfs(TreeNode node, Set<Integer> set, int k) {
        if (node == null) {
            return false;
        }

        // Check if complement exists
        if (set.contains(k - node.val)) {
            return true;
        }

        // Add current value to set
        set.add(node.val);

        // Recursively check left and right subtrees
        return dfs(node.left, set, k) || dfs(node.right, set, k);
    }

    // Alternative approach: get sorted list and use two pointers
    public static boolean findTargetAlternative(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        inorderTraversal(root, values);

        int left = 0, right = values.size() - 1;

        while (left < right) {
            int sum = values.get(left) + values.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    private static void inorderTraversal(TreeNode node, List<Integer> values) {
        if (node != null) {
            inorderTraversal(node.left, values);
            values.add(node.val);
            inorderTraversal(node.right, values);
        }
    }

    public static void main(String[] args) {
        // Create BST: [5,3,6,2,4,null,7]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("Input: BST with root = 5, left = 3, right = 6, left.left = 2, left.right = 4, right.right = 7");

        int k = 9;
        boolean result = findTarget(root, k);
        System.out.println("Output: " + result);
    }
}

Time & Space Complexity:
- Time: O(n) - visit each node once
- Space: O(n) - hashset or recursion stack

Common Mistakes:
1. Not recognizing that it's a BST problem and using inefficient approach
2. Forgetting to leverage the sorted property of BST
3. Incorrectly implementing BST validation

LeetCode Practice Problems:
- Validate Binary Search Tree
- Lowest Common Ancestor of a BST
- Insert into a Binary Search Tree

Mini Challenge:
Modify the solution to return all pairs of nodes that sum to k.

🖥️ Expected Console Output:
Input: BST with root = 5, left = 3, right = 6, left.left = 2, left.right = 4, right.right = 7
Output: true

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 15,
            isCompleted = false,
            codeExample = """
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public static boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    private static boolean dfs(TreeNode node, Set<Integer> set, int k) {
        if (node == null) {
            return false;
        }

        // Check if complement exists
        if (set.contains(k - node.val)) {
            return true;
        }

        // Add current value to set
        set.add(node.val);

        // Recursively check left and right subtrees
        return dfs(node.left, set, k) || dfs(node.right, set, k);
    }

    // Alternative approach: get sorted list and use two pointers
    public static boolean findTargetAlternative(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        inorderTraversal(root, values);

        int left = 0, right = values.size() - 1;

        while (left < right) {
            int sum = values.get(left) + values.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    private static void inorderTraversal(TreeNode node, List<Integer> values) {
        if (node != null) {
            inorderTraversal(node.left, values);
            values.add(node.val);
            inorderTraversal(node.right, values);
        }
    }

    public static void main(String[] args) {
        // Create BST: [5,3,6,2,4,null,7]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        int k = 9;
        System.out.println("Target sum " + k + " exists: " + findTarget(root, k));

        k = 28;
        System.out.println("Target sum " + k + " exists: " + findTarget(root, k));
    }
}"""
        ),

        // Pattern Lesson 16: Binary Search Classic
        Lesson(
            id = "lp-16",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Binary Search (Classic)",
            content = """🟢 Easy

Pattern / Topic Name: Binary Search

Intuition (Simple Explanation):
Like searching for a word in a dictionary, binary search eliminates half of the remaining possibilities at each step. It works on sorted collections by comparing the target with the middle element and deciding which half to continue searching in.

When to Use This Pattern:
Use when you need to find an element in a sorted array or when the problem has monotonic properties (if condition holds for x, it holds for all values greater than x). This is highly efficient with O(log n) time complexity.

Problem Statement:
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

Step-by-Step Approach:
1. Initialize left and right pointers
2. While left <= right:
   - Calculate mid index
   - If nums[mid] == target: return mid
   - If nums[mid] < target: search right half
   - If nums[mid] > target: search left half
3. Return -1 if not found

Dry Run Example:
nums = [-1, 0, 3, 5, 9, 12], target = 9
- left=0, right=5, mid=2, nums[2]=3 < 9 → search right
- left=3, right=5, mid=4, nums[4]=9 == 9 → return 4

Java Runnable Code:
class Solution {

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevent overflow

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        int target2 = 2;

        System.out.println("Index of " + target1 + ": " + search(nums, target1));
        System.out.println("Index of " + target2 + ": " + search(nums, target2));
    }
}

Time & Space Complexity:
- Time: O(log n) - eliminate half at each step
- Space: O(1) - only using pointers

Common Mistakes:
1. Infinite loops due to improper pointer updates
2. Integer overflow with (left + right) / 2
3. Incorrect boundary conditions

LeetCode Practice Problems:
- First Bad Version
- Search Insert Position
- Peak Index in a Mountain Array

Mini Challenge:
Modify the solution to find the first occurrence of the target if there are duplicates.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = """
class Solution {

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevent overflow

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        int target2 = 2;

        System.out.println("Index of " + target1 + ": " + search(nums, target1));
        System.out.println("Index of " + target2 + ": " + search(nums, target2));
    }
}"""
        ),

        // Pattern Lesson 17: Modified Binary Search
        Lesson(
            id = "lp-17",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Modified Binary Search",
            content = """🟡 Medium

Pattern / Topic Name: Binary Search Variants

Intuition (Simple Explanation):
Sometimes the array is rotated, contains duplicates, or has other modifications. The key is to identify which half of the array is properly sorted and whether the target lies in that half. The binary search logic is adapted based on the specific modification.

When to Use This Pattern:
Use when the sorted array has been modified (rotated, shifted, etc.) but still maintains some order that can be leveraged for binary search. This requires adapting the standard binary search logic to handle the specific modification.

Problem Statement:
There is an integer array nums sorted in ascending order (with distinct values). Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]. Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

Step-by-Step Approach:
1. At each step, one half of the array is always sorted
2. Check if the sorted half contains the target
3. If yes, search in the sorted half; otherwise, search in the other half

Dry Run Example:
nums = [4,5,6,7,0,1,2], target = 0
- left=0, right=6, mid=3, nums[3]=7
- Left half [4,5,6] is sorted, but 0 is not in [4,7], search right
- left=4, right=6, mid=5, nums[5]=1
- Left half [0,1] is sorted, 0 is in [0,1], search left
- left=4, right=5, mid=4, nums[4]=0 == target → return 4

Java Runnable Code:
class Solution {

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    // Target is in the sorted left half
                    right = mid - 1;
                } else {
                    // Target is not in the left half
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    // Target is in the sorted right half
                    left = mid + 1;
                } else {
                    // Target is not in the right half
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Index of " + target1 + ": " + search(nums1, target1));

        int target2 = 3;
        System.out.println("Index of " + target2 + ": " + search(nums1, target2));

        int[] nums2 = {1};
        int target3 = 0;
        System.out.println("Index of " + target3 + ": " + search(nums2, target3));
    }
}

Time & Space Complexity:
- Time: O(log n) - still eliminate half at each step
- Space: O(1) - only using pointers

Common Mistakes:
1. Not properly identifying which half is sorted
2. Incorrect boundary comparisons
3. Forgetting to handle edge cases

LeetCode Practice Problems:
- Search in Rotated Sorted Array II (with duplicates)
- Find Minimum in Rotated Sorted Array
- Find Peak Element

Mini Challenge:
Modify the solution to handle arrays with duplicate values.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = """
class Solution {

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    // Target is in the sorted left half
                    right = mid - 1;
                } else {
                    // Target is not in the left half
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    // Target is in the sorted right half
                    left = mid + 1;
                } else {
                    // Target is not in the right half
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Index of " + target1 + ": " + search(nums1, target1));

        int target2 = 3;
        System.out.println("Index of " + target2 + ": " + search(nums1, target2));

        int[] nums2 = {1};
        int target3 = 0;
        System.out.println("Index of " + target3 + ": " + search(nums2, target3));
    }
}"""
        ),

        // Pattern Lesson 18: Binary Search on Answer
        Lesson(
            id = "lp-18",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Binary Search on Answer",
            content = """🔴 Hard

Pattern / Topic Name: Binary Search on Answer

Intuition (Simple Explanation):
Instead of searching in the input array, we search in the answer space. If we can verify whether a particular answer is valid in O(f(n)) time, we can use binary search to find the optimal answer. This works when the answer space has monotonic properties.

When to Use This Pattern:
Use when the answer lies in a specific range and we can efficiently check if a candidate answer is valid. Common in optimization problems asking for minimum/maximum values where brute force would be too slow.

Problem Statement:
Given an array of n positive integers and a positive integer target, find the minimal length of a contiguous subarray whose sum is greater than or equal to target.

Step-by-Step Approach:
1. Identify the search space: [1, n] (subarray length)
2. For each candidate length, check if it's possible to achieve the target sum
3. If possible, search for smaller lengths; otherwise, search for larger lengths
4. Use a helper function to validate if a length is sufficient

Dry Run Example:
nums = [2,1,2,4,3], target = 7
Search space: [1, 5]
- mid=3: can we get sum>=7 with length 3? Yes [2,4,3]=9
- Search left: mid=2: can we get sum>=7 with length 2? Yes [4,3]=7
- Search left: mid=1: can we get sum>=7 with length 1? No
Result: 2

Java Runnable Code:
class Solution {

    public static int minSubArrayLen(int target, int[] nums) {
        int left = 1, right = nums.length;
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(mid, target, nums)) {
                result = mid;
                right = mid - 1; // Try to find smaller length
            } else {
                left = mid + 1; // Need larger length
            }
        }

        return result;
    }

    // Check if there exists a subarray of length 'len' with sum >= target
    private static boolean isValid(int len, int target, int[] nums) {
        int sum = 0;

        // Calculate sum of first window
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }

        if (sum >= target) return true;

        // Slide the window
        for (int i = len; i < nums.length; i++) {
            sum = sum - nums[i - len] + nums[i];
            if (sum >= target) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        int target = 7;
        System.out.println("Minimum subarray length with sum >= " + target + ": " +
                          minSubArrayLen(target, nums));

        int target2 = 15;
        System.out.println("Minimum subarray length with sum >= " + target2 + ": " +
                          minSubArrayLen(target2, nums));
    }
}

Time & Space Complexity:
- Time: O(n log n) - binary search (log n) * validation (n)
- Space: O(1) - only using constant extra space

Common Mistakes:
1. Not verifying if binary search on answer is applicable
2. Incorrectly defining the search space
3. Failing to implement the validation function correctly

LeetCode Practice Problems:
- Capacity To Ship Packages Within D Days
- Split Array Largest Sum
- Koko Eating Bananas

Mini Challenge:
Apply binary search on answer to find the kth smallest element in a multiplication table.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 18,
            isCompleted = false,
            codeExample = """
class Solution {

    public static int minSubArrayLen(int target, int[] nums) {
        int left = 1, right = nums.length;
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(mid, target, nums)) {
                result = mid;
                right = mid - 1; // Try to find smaller length
            } else {
                left = mid + 1; // Need larger length
            }
        }

        return result;
    }

    // Check if there exists a subarray of length 'len' with sum >= target
    private static boolean isValid(int len, int target, int[] nums) {
        int sum = 0;

        // Calculate sum of first window
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }

        if (sum >= target) return true;

        // Slide the window
        for (int i = len; i < nums.length; i++) {
            sum = sum - nums[i - len] + nums[i];
            if (sum >= target) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        int target = 7;
        System.out.println("Minimum subarray length with sum >= " + target + ": " +
                          minSubArrayLen(target, nums));

        int target2 = 15;
        System.out.println("Minimum subarray length with sum >= " + target2 + ": " +
                          minSubArrayLen(target2, nums));
    }
}"""
        ),

        // Pattern Lesson 19: Subsets (BFS)
        Lesson(
            id = "lp-19",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Subsets Generation (BFS Approach)",
            content = """🟡 Medium

Pattern / Topic Name: Subsets Generation

Intuition (Simple Explanation):
Build subsets incrementally. Start with the empty subset, then for each element in the input, add it to all existing subsets to create new ones. This is like expanding a tree where each level adds one more element.

When to Use This Pattern:
Use when you need to generate all possible subsets of a set. This is useful for problems involving combinatorial search, where you need to consider all possible combinations of elements.

Problem Statement:
Given an integer array nums of unique elements, return all possible subsets (the power set). The solution set must not contain duplicate subsets.

Step-by-Step Approach:
1. Start with an empty subset in the result list
2. For each number in the input array:
   - Take all existing subsets and add the current number to each
   - Add these new subsets to the result
3. Return all subsets

Dry Run Example:
nums = [1, 2, 3]
- Start: [[]]
- Add 1: [], [1] → [[], [1]]
- Add 2: [], [1], [2], [1,2] → [[], [1], [2], [1,2]]
- Add 3: [], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]
Result: [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]

Java Runnable Code:
import java.util.*;

class Solution {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with empty subset

        for (int num : nums) {
            int size = result.size();
            // Add current number to all existing subsets
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = subsets(nums);

        System.out.println("All subsets of [" + Arrays.toString(nums) + "]: ");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}

Time & Space Complexity:
- Time: O(2^n * n) - 2^n subsets, each takes O(n) to construct in worst case
- Space: O(2^n * n) - to store all subsets

Common Mistakes:
1. Modifying existing subsets instead of creating new ones
2. Not understanding the growth pattern of subsets
3. Incorrectly managing the iteration during expansion

LeetCode Practice Problems:
- Subsets II (with duplicates)
- Combination Sum
- Letter Case Permutation

Mini Challenge:
Modify the solution to generate subsets of a specific size k.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with empty subset

        for (int num : nums) {
            int size = result.size();
            // Add current number to all existing subsets
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = subsets(nums);

        System.out.println("All subsets of [" + Arrays.toString(nums) + "]: ");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}"""
        ),

        // Pattern Lesson 20: Subsets (DFS)
        Lesson(
            id = "lp-20",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Subsets Generation (DFS Approach)",
            content = """🟡 Medium

Pattern / Topic Name: Subsets Generation (Recursive)

Intuition (Simple Explanation):
Use recursion to make a choice for each element: either include it in the current subset or exclude it. This creates a binary decision tree where each path from root to leaf represents a unique subset.

When to Use This Pattern:
Use when you need to generate all possible subsets recursively. This approach is intuitive and maps naturally to the decision-making process for each element.

Problem Statement:
Given an integer array nums of unique elements, return all possible subsets (the power set). The solution set must not contain duplicate subsets.

Step-by-Step Approach:
1. Use recursion with a current subset and an index
2. At each step, make two recursive calls:
   - Exclude current element: proceed to next element
   - Include current element: add to subset, then proceed to next element
3. When index reaches end of array, add current subset to result

Dry Run Example:
nums = [1, 2]
- Start with [] at index 0
- At index 0: exclude 1 → dfs([ ], 1), include 1 → dfs([1], 1)
- At index 1: exclude 2 → dfs([ ], 2), include 2 → dfs([1], 2)
- At index 2: add [] and [1] to result
- Backtrack and add [2] and [1,2] to result
Result: [[], [2], [1], [1,2]]

Java Runnable Code:
import java.util.*;

class Solution {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Add current subset to result (base case reached)
        result.add(new ArrayList<>(current));

        // Explore further elements
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);              // Choose
            backtrack(nums, i + 1, current, result); // Explore
            current.remove(current.size() - 1);      // Unchoose (backtrack)
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = subsets(nums);

        System.out.println("All subsets of [" + Arrays.toString(nums) + "]: ");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}

Time & Space Complexity:
- Time: O(2^n * n) - 2^n subsets, each takes O(n) to construct
- Space: O(2^n * n) - to store all subsets

Common Mistakes:
1. Not properly backtracking (removing added elements)
2. Starting the loop from 0 instead of current index
3. Forgetting to create a copy of the current subset

LeetCode Practice Problems:
- Subsets II (with duplicates)
- Combination Sum
- Letter Case Permutation

Mini Challenge:
Modify the solution to generate subsets in lexicographic order.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Add current subset to result (base case reached)
        result.add(new ArrayList<>(current));

        // Explore further elements
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);              // Choose
            backtrack(nums, i + 1, current, result); // Explore
            current.remove(current.size() - 1);      // Unchoose (backtrack)
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = subsets(nums);

        System.out.println("All subsets of [" + Arrays.toString(nums) + "]: ");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}"""
        ),

        // Pattern Lesson 21: Permutations
        Lesson(
            id = "lp-21",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Permutations Generation",
            content = """🟡 Medium

Pattern / Topic Name: Permutations Generation

Intuition (Simple Explanation):
Unlike subsets where we choose to include/exclude elements, in permutations we arrange all elements in different orders. Think of it as arranging people in a line where each person can occupy any position.

When to Use This Pattern:
Use when you need to generate all possible arrangements of elements. This is common in problems where order matters, such as scheduling, sequencing, or trying all possible orderings of operations.

Problem Statement:
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Step-by-Step Approach:
1. Use backtracking with a current permutation and a set of used elements
2. At each step, try adding each unused element to the current permutation
3. Recursively continue until permutation is complete
4. Backtrack by removing the element and trying the next one

Dry Run Example:
nums = [1, 2, 3]
- Start with empty permutation []
- Try 1: [1] → [1,2] → [1,2,3] (add to result)
- Backtrack: [1,2] → [1] → [1,3] → [1,3,2] (add to result)
- Continue for permutations starting with 2 and 3
Result: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

Java Runnable Code:
import java.util.*;

class Solution {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        // Base case: permutation is complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try each unused number
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                current.add(nums[i]);  // Choose
                used[i] = true;        // Mark as used
                backtrack(nums, current, used, result); // Explore
                current.remove(current.size() - 1); // Unchoose
                used[i] = false;       // Mark as unused
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);

        System.out.println("All permutations of [" + Arrays.toString(nums) + "]: ");
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }
}

Time & Space Complexity:
- Time: O(n! * n) - n! permutations, each takes O(n) to construct
- Space: O(n! * n) - to store all permutations

Common Mistakes:
1. Not properly tracking used elements
2. Forgetting to unmark elements as unused during backtracking
3. Incorrect base case condition

LeetCode Practice Problems:
- Permutations II (with duplicates)
- Next Permutation
- Permutation Sequence

Mini Challenge:
Generate permutations in lexicographic order without using extra space for tracking used elements.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 21,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        // Base case: permutation is complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try each unused number
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                current.add(nums[i]);  // Choose
                used[i] = true;        // Mark as used
                backtrack(nums, current, used, result); // Explore
                current.remove(current.size() - 1); // Unchoose
                used[i] = false;       // Mark as unused
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);

        System.out.println("All permutations of [" + Arrays.toString(nums) + "]: ");
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }
}"""
        ),

        // Pattern Lesson 22: Combination Sum
        Lesson(
            id = "lp-22",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Combination Sum",
            content = """🟡 Medium

Pattern / Topic Name: Combination Sum

Intuition (Simple Explanation):
Find all combinations of numbers that sum to a target. Unlike permutations, order doesn't matter. We can reuse the same number multiple times. Use backtracking to explore all possibilities.

When to Use This Pattern:
Use when you need to find all combinations of elements that sum to a target value. This is common in problems involving making change, selecting items with constraints, or partitioning numbers.

Problem Statement:
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. The same number may be chosen from candidates an unlimited number of times.

Step-by-Step Approach:
1. Use backtracking with current combination and remaining target
2. At each step, try adding each candidate ≥ last added element (to avoid duplicates)
3. Continue recursively with reduced target
4. When target becomes 0, add current combination to result

Dry Run Example:
candidates = [2,3,5], target = 8
- Start with [] and target 8
- Add 2: [2] with target 6 → add 2: [2,2] with target 4 → ... → [2,2,2,2] (target 0) ✓
- Backtrack and try other combinations
Result: [[2,2,2,2], [2,3,3], [3,5]]

Java Runnable Code:
import java.util.*;

class Solution {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int remaining, int start,
                                  List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            // Found a valid combination
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= remaining) {
                current.add(candidates[i]);  // Choose
                // Recurse with same start index to allow reusing same element
                backtrack(candidates, remaining - candidates[i], i, current, result);
                current.remove(current.size() - 1); // Unchoose
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> result = combinationSum(candidates, target);

        System.out.println("Combinations that sum to " + target + ": ");
        for (List<Integer> combo : result) {
            System.out.println(combo);
        }
    }
}

Time & Space Complexity:
- Time: O(n^(t/m)) where n is number of candidates, t is target, m is minimal candidate value
- Space: O(t/m) - maximum recursion depth

Common Mistakes:
1. Not allowing reuse of the same element (passing i+1 instead of i)
2. Not starting from the correct index (causing duplicate combinations)
3. Forgetting to handle the base case properly

LeetCode Practice Problems:
- Combination Sum II (each element used once)
- Combination Sum III
- Factor Combinations

Mini Challenge:
Modify the solution to find combinations with exactly k elements that sum to target.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int remaining, int start,
                                  List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            // Found a valid combination
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= remaining) {
                current.add(candidates[i]);  // Choose
                // Recurse with same start index to allow reusing same element
                backtrack(candidates, remaining - candidates[i], i, current, result);
                current.remove(current.size() - 1); // Unchoose
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> result = combinationSum(candidates, target);

        System.out.println("Combinations that sum to " + target + ": ");
        for (List<Integer> combo : result) {
            System.out.println(combo);
        }
    }
}"""
        ),

        // Pattern Lesson 23: Backtracking Grid (Word Search)
        Lesson(
            id = "lp-23",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Backtracking on Grid (Word Search)",
            content = """🟡 Medium

Pattern / Topic Name: Backtracking on 2D Grid

Intuition (Simple Explanation):
Explore all possible paths on a 2D grid, marking visited cells to avoid cycles. When a path doesn't lead to a solution, backtrack by unmarking the cell and trying other directions. Like navigating a maze with the ability to undo steps.

When to Use This Pattern:
Use when you need to search for patterns, paths, or sequences on a 2D grid. This is common in word search problems, pathfinding, or exploring all possible configurations on a grid.

Problem Statement:
Given an m x n board of characters and a string word, return true if word exists in the grid. The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Step-by-Step Approach:
1. For each cell on the board, try to start the word search
2. Use DFS to explore all 4 directions (up, down, left, right)
3. Mark visited cells to avoid reuse in current path
4. Backtrack by unmarking cells when returning from recursion

Dry Run Example:
board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
], word = "ABCCED"
- Start at A(0,0), move to B(0,1), C(0,2), C(1,2), E(1,3), D(2,3) ✓
Result: true

Java Runnable Code:
class Solution {

    public static boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;

        // Try starting from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, String word, int index, int row, int col) {
        // Base case: found the complete word
        if (index == word.length()) {
            return true;
        }

        // Check bounds and if cell is already visited or doesn't match
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
            board[row][col] == '#' || board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark current cell as visited by temporarily changing its value
        char temp = board[row][col];
        board[row][col] = '#';

        // Explore all 4 directions
        boolean found = dfs(board, word, index + 1, row + 1, col) ||  // Down
                      dfs(board, word, index + 1, row - 1, col) ||  // Up
                      dfs(board, word, index + 1, row, col + 1) ||  // Right
                      dfs(board, word, index + 1, row, col - 1);   // Left

        // Restore the cell value (backtrack)
        board[row][col] = temp;

        return found;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };

        String word1 = "ABCCED";
        System.out.println("Word \"" + word1 + "\" exists: " + exist(board, word1));

        String word2 = "SEE";
        System.out.println("Word \"" + word2 + "\" exists: " + exist(board, word2));

        String word3 = "ABCB";
        System.out.println("Word \"" + word3 + "\" exists: " + exist(board, word3));
    }
}

Time & Space Complexity:
- Time: O(m*n*4^L) where L is the length of the word
- Space: O(L) - recursion stack depth

Common Mistakes:
1. Not properly marking/unmarking visited cells
2. Not checking all boundary conditions
3. Forgetting to restore the original value during backtracking

LeetCode Practice Problems:
- Word Search II (multiple words)
- Number of Islands
- Sudoku Solver

Mini Challenge:
Modify the solution to return all possible paths that form the word.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = """
class Solution {

    public static boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;

        // Try starting from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, String word, int index, int row, int col) {
        // Base case: found the complete word
        if (index == word.length()) {
            return true;
        }

        // Check bounds and if cell is already visited or doesn't match
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
            board[row][col] == '#' || board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark current cell as visited by temporarily changing its value
        char temp = board[row][col];
        board[row][col] = '#';

        // Explore all 4 directions
        boolean found = dfs(board, word, index + 1, row + 1, col) ||  // Down
                      dfs(board, word, index + 1, row - 1, col) ||  // Up
                      dfs(board, word, index + 1, row, col + 1) ||  // Right
                      dfs(board, word, index + 1, row, col - 1);   // Left

        // Restore the cell value (backtrack)
        board[row][col] = temp;

        return found;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };

        String word1 = "ABCCED";
        System.out.println("Word \"" + word1 + "\" exists: " + exist(board, word1));

        String word2 = "SEE";
        System.out.println("Word \"" + word2 + "\" exists: " + exist(board, word2));

        String word3 = "ABCB";
        System.out.println("Word \"" + word3 + "\" exists: " + exist(board, word3));
    }
}"""
        ),

        // Pattern Lesson 24: Greedy Jump Game
        Lesson(
            id = "lp-24",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Greedy Jump Game",
            content = """🟡 Medium

Pattern / Topic Name: Greedy Algorithms

Intuition (Simple Explanation):
At each position, make the locally optimal choice (jump as far as possible) hoping it leads to a globally optimal solution. Keep track of the furthest position reachable and check if we can reach the end.

When to Use This Pattern:
Use when you can make locally optimal choices that lead to a global optimum. Common in problems involving reaching a destination, maximizing coverage, or optimizing resource allocation.

Problem Statement:
Given an integer array nums, you are initially positioned at the first index of the array. Each element in the array represents your maximum jump length at that position. Return true if you can reach the last index, or false otherwise.

Step-by-Step Approach:
1. Keep track of the furthest index we can reach
2. Iterate through the array:
   - If current index is beyond furthest reachable, return false
   - Update furthest reachable = max(furthest, i + nums[i])
3. If we finish the loop, we can reach the end

Dry Run Example:
nums = [2, 3, 1, 1, 4]
- i=0: furthest=0, can reach (0<=0) ✓, furthest=max(0, 0+2)=2
- i=1: furthest=2, can reach (1<=2) ✓, furthest=max(2, 1+3)=4
- i=2: furthest=4, can reach (2<=4) ✓, furthest=max(4, 2+1)=4
- i=3: furthest=4, can reach (3<=4) ✓, furthest=max(4, 3+1)=4
- i=4: furthest=4, can reach (4<=4) ✓, reached end ✓
Result: true

Java Runnable Code:
class Solution {

    public static boolean canJump(int[] nums) {
        int furthest = 0;

        for (int i = 0; i < nums.length; i++) {
            // If current index is beyond furthest we can reach
            if (i > furthest) {
                return false;
            }

            // Update furthest reachable index
            furthest = Math.max(furthest, i + nums[i]);

            // Early termination if we can reach the end
            if (furthest >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump with [" + java.util.Arrays.toString(nums1) + "]: " + canJump(nums1));

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can jump with [" + java.util.Arrays.toString(nums2) + "]: " + canJump(nums2));
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the array
- Space: O(1) - only using one variable

Common Mistakes:
1. Not checking if current index is reachable before updating furthest
2. Forgetting to handle the edge case of single element
3. Incorrectly setting the termination condition

LeetCode Practice Problems:
- Jump Game II
- Frog Jump
- Video Stitching

Mini Challenge:
Modify the solution to return the minimum number of jumps needed to reach the end.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 24,
            isCompleted = false,
            codeExample = """
class Solution {

    public static boolean canJump(int[] nums) {
        int furthest = 0;

        for (int i = 0; i < nums.length; i++) {
            // If current index is beyond furthest we can reach
            if (i > furthest) {
                return false;
            }

            // Update furthest reachable index
            furthest = Math.max(furthest, i + nums[i]);

            // Early termination if we can reach the end
            if (furthest >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump with [" + java.util.Arrays.toString(nums1) + "]: " + canJump(nums1));

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can jump with [" + java.util.Arrays.toString(nums2) + "]: " + canJump(nums2));
    }
}"""
        ),

        // Pattern Lesson 25: Greedy Gas Station
        Lesson(
            id = "lp-25",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Greedy Gas Station",
            content = """🟡 Medium

Pattern / Topic Name: Greedy Algorithms

Intuition (Simple Explanation):
If the total gas available is greater than or equal to the total cost, there must be a solution. Use a greedy approach to find the starting station: if at any point the tank becomes negative, reset the starting point to the next station.

When to Use This Pattern:
Use when you need to find a starting point that allows completing a circular journey. This is common in problems involving circular routes with resource constraints.

Problem Statement:
There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i]. You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations. Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Step-by-Step Approach:
1. If total gas < total cost, return -1 (impossible)
2. Use a greedy approach: keep track of current tank balance
3. If tank becomes negative, update starting position to next station
4. Return the starting position

Dry Run Example:
gas = [1,2,3,4,5], cost = [3,4,5,1,2]
- Total gas = 15, Total cost = 15 → possible
- Start at 0: tank=1-3=-2 → start from 1, tank=0
- At 1: tank=0+2-4=-2 → start from 2, tank=0
- At 2: tank=0+3-5=-2 → start from 3, tank=0
- At 3: tank=0+4-1=3
- At 4: tank=3+5-2=6 → finish → return 3

Java Runnable Code:
class Solution {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
        int currentTank = 0, startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentTank += gas[i] - cost[i];

            // If current tank is negative, we can't reach the next station
            // So start from the next station
            if (currentTank < 0) {
                startIndex = i + 1;  // Start from next station
                currentTank = 0;     // Reset the tank
            }
        }

        // If total gas is less than total cost, impossible to complete circuit
        return totalGas >= totalCost ? startIndex : -1;
    }

    public static void main(String[] args) {
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Starting station: " + canCompleteCircuit(gas1, cost1));

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Starting station: " + canCompleteCircuit(gas2, cost2));
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the array
- Space: O(1) - only using constant extra space

Common Mistakes:
1. Not checking if total gas >= total cost first
2. Incorrectly resetting the starting index
3. Not handling the circular nature of the problem

LeetCode Practice Problems:
- Gas Station II
- Lemonade Change
- Score After Flipping Matrix

Mini Challenge:
Modify the solution to find all possible starting stations.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
class Solution {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
        int currentTank = 0, startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentTank += gas[i] - cost[i];

            // If current tank is negative, we can't reach the next station
            // So start from the next station
            if (currentTank < 0) {
                startIndex = i + 1;  // Start from next station
                currentTank = 0;     // Reset the tank
            }
        }

        // If total gas is less than total cost, impossible to complete circuit
        return totalGas >= totalCost ? startIndex : -1;
    }

    public static void main(String[] args) {
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Starting station: " + canCompleteCircuit(gas1, cost1));

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Starting station: " + canCompleteCircuit(gas2, cost2));
    }
}"""
        ),

        // Pattern Lesson 26: Greedy Interval Scheduling
        Lesson(
            id = "lp-26",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Greedy Interval Scheduling",
            content = """🟡 Medium

Pattern / Topic Name: Greedy Algorithms

Intuition (Simple Explanation):
To maximize the number of activities, always pick the one that finishes earliest. This leaves the most room for future activities. This greedy choice leads to an optimal solution because selecting an activity that finishes later would never give us more options.

When to Use This Pattern:
Use when you need to select a maximum number of non-overlapping intervals. Common in scheduling problems, resource allocation, or any problem where you need to maximize the number of selected items with time constraints.

Problem Statement:
Given an array of intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Step-by-Step Approach:
1. Sort intervals by end time (greedy choice)
2. Keep track of the end time of the last selected interval
3. For each interval, if it doesn't overlap with the last selected one, select it
4. The number of removed intervals = total - selected

Dry Run Example:
intervals = [[1,2],[2,3],[3,4],[1,3]]
- Sort by end time: [[1,2],[2,3],[1,3],[3,4]]
- Select [1,2], end=2
- [2,3] doesn't overlap (2 >= 2 is false, 2 > 2 is false, so 2 >= 2 means they touch, which is OK for non-overlapping)
- Actually [2,3] starts at 2, ends at 3, last ended at 2, so 2 >= 2 means overlap, skip
- [1,3] starts at 1, overlaps with [1,2], skip
- [3,4] starts at 3, last ended at 2, 3 > 2, no overlap, select
Result: selected 2 intervals, removed 2 intervals

Java Runnable Code:
import java.util.*;

class Solution {

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 1; // Count of non-overlapping intervals
        int end = intervals[0][1]; // End time of last selected interval

        for (int i = 1; i < intervals.length; i++) {
            // If current interval's start time is >= last selected end time,
            // then there's no overlap
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
            // If there's overlap, we skip this interval (effectively removing it)
        }

        return intervals.length - count; // Number of intervals to remove
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("Intervals to remove: " + eraseOverlapIntervals(intervals1));

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println("Intervals to remove: " + eraseOverlapIntervals(intervals2));
    }
}

Time & Space Complexity:
- Time: O(n log n) - dominated by sorting
- Space: O(1) - only using constant extra space

Common Mistakes:
1. Sorting by start time instead of end time
2. Incorrectly identifying overlap condition
3. Not handling edge cases properly

LeetCode Practice Problems:
- Meeting Rooms II
- Non-overlapping Intervals
- Minimum Number of Arrows to Burst Balloons

Mini Challenge:
Modify the solution to return the maximum number of intervals that can be selected if each selected interval must be separated by at least k units of time.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 1; // Count of non-overlapping intervals
        int end = intervals[0][1]; // End time of last selected interval

        for (int i = 1; i < intervals.length; i++) {
            // If current interval's start time is >= last selected end time,
            // then there's no overlap
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
            // If there's overlap, we skip this interval (effectively removing it)
        }

        return intervals.length - count; // Number of intervals to remove
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("Intervals to remove: " + eraseOverlapIntervals(intervals1));

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println("Intervals to remove: " + eraseOverlapIntervals(intervals2));
    }
}"""
        ),

        // Pattern Lesson 27: Monotonic Stack
        Lesson(
            id = "lp-27",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Monotonic Stack",
            content = """🟡 Medium

Pattern / Topic Name: Monotonic Stack

Intuition (Simple Explanation):
A stack that maintains elements in a monotonic order (either increasing or decreasing). This is useful for finding the next greater or smaller element efficiently. Elements in the stack are in a specific order, allowing us to solve problems in linear time.

When to Use This Pattern:
Use when you need to find the next/previous greater or smaller element for each element in an array. Common in problems involving nearest elements, temperature problems, or bracket matching.

Problem Statement:
Given an array of integers nums, return the length of the longest turbulent subarray. A subarray is turbulent if the comparison sign flips between each adjacent pair of elements.

Step-by-Step Approach:
1. A turbulent subarray alternates between increasing and decreasing
2. Use a sliding window approach to track the current turbulent length
3. Reset the window when the turbulent pattern breaks

Dry Run Example:
nums = [9,4,2,10,7,8,8,1,9]
- [9,4] decreasing, [4,2] decreasing → not turbulent
- [2,10] increasing, [10,7] decreasing → turbulent [2,10,7]
- [7,8] increasing, [8,8] equal → turbulent [7,8] but [8,8] breaks it
- [8,1] decreasing, [1,9] increasing → turbulent [8,1,9]
Result: max length is 5 ([2,10,7,8] or [4,2,10,7,8] corrected)
Actually: [4,2,10,7,8] is turbulent (4>2<10>7<8)

Java Runnable Code:
import java.util.*;

class Solution {

    public static int maxTurbulenceSize(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        int maxSize = 1;
        int currentSize = 1;

        for (int i = 1; i < n; i++) {
            if (i >= 2 && ((nums[i-2] > nums[i-1] && nums[i-1] < nums[i]) ||
                           (nums[i-2] < nums[i-1] && nums[i-1] > nums[i]))) {
                // Turbulent pattern continues
                currentSize++;
            } else if (nums[i-1] != nums[i]) {
                // Different but not continuing turbulence
                currentSize = 2;
            } else {
                // Equal elements, reset
                currentSize = 1;
            }

            maxSize = Math.max(maxSize, currentSize);
        }

        return maxSize;
    }

    public static void main(String[] args) {
        int[] nums1 = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        System.out.println("Max turbulent size: " + maxTurbulenceSize(nums1));

        int[] nums2 = {4, 8, 12, 16};
        System.out.println("Max turbulent size: " + maxTurbulenceSize(nums2));
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the array
- Space: O(1) - only using constant extra space

Common Mistakes:
1. Not properly identifying the turbulent condition
2. Incorrectly handling equal elements
3. Not tracking both increasing and decreasing comparisons

LeetCode Practice Problems:
- Next Greater Element I
- Daily Temperatures
- Online Stock Span

Mini Challenge:
Use monotonic stack to solve the original problem: find the next greater element for each element.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 27,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static int maxTurbulenceSize(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        int maxSize = 1;
        int currentSize = 1;

        for (int i = 1; i < n; i++) {
            if (i >= 2 && ((nums[i-2] > nums[i-1] && nums[i-1] < nums[i]) ||
                           (nums[i-2] < nums[i-1] && nums[i-1] > nums[i]))) {
                // Turbulent pattern continues
                currentSize++;
            } else if (nums[i-1] != nums[i]) {
                // Different but not continuing turbulence
                currentSize = 2;
            } else {
                // Equal elements, reset
                currentSize = 1;
            }

            maxSize = Math.max(maxSize, currentSize);
        }

        return maxSize;
    }

    public static void main(String[] args) {
        int[] nums1 = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        System.out.println("Max turbulent size: " + maxTurbulenceSize(nums1));

        int[] nums2 = {4, 8, 12, 16};
        System.out.println("Max turbulent size: " + maxTurbulenceSize(nums2));
    }
}"""
        ),

        // Pattern Lesson 28: Topological Sort
        Lesson(
            id = "lp-28",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Topological Sort",
            content = """🟡 Medium

Pattern / Topic Name: Graph Theory / Topological Sort

Intuition (Simple Explanation):
Like scheduling tasks with dependencies, we need to order elements such that all dependencies come before the dependent elements. Kahn's algorithm removes nodes with no incoming edges first, gradually reducing the graph.

When to Use This Pattern:
Use when you have a directed graph and need to order vertices such that for every directed edge uv, vertex u comes before v in the ordering. Common in prerequisite problems, course scheduling, or dependency resolution.

Problem Statement:
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai. Return true if you can finish all courses. Otherwise, return false.

Step-by-Step Approach:
1. Build adjacency list and calculate in-degrees for each node
2. Add all nodes with in-degree 0 to queue
3. While queue is not empty:
   - Remove node from queue
   - Reduce in-degree of its neighbors
   - Add neighbors with in-degree 0 to queue
4. If all nodes were processed, there's no cycle

Dry Run Example:
numCourses = 2, prerequisites = [[1,0]]
- Graph: 0 → 1
- In-degrees: [0,1]
- Node 0 has in-degree 0, add to queue
- Process 0: reduce in-degree of 1 → [0,0]
- Add 1 to queue, process 1
- All nodes processed → true

Java Runnable Code:
import java.util.*;

class Solution {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list and in-degree array
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            adj.get(prereq[1]).add(prereq[0]); // bi -> ai
            inDegree[prereq[0]]++;
        }

        // Add all nodes with in-degree 0 to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processedCount = 0;

        // Process nodes in topological order
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processedCount++;

            // Reduce in-degree of neighbors
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If all courses were processed, there's no cycle
        return processedCount == numCourses;
    }

    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println("Can finish courses: " + canFinish(numCourses1, prerequisites1));

        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Can finish courses: " + canFinish(numCourses2, prerequisites2));
    }
}

Time & Space Complexity:
- Time: O(V + E) where V is number of courses and E is number of prerequisites
- Space: O(V + E) for adjacency list and other data structures

Common Mistakes:
1. Not properly building the graph (wrong direction)
2. Forgetting to update in-degrees correctly
3. Not handling disconnected components

LeetCode Practice Problems:
- Course Schedule II
- Alien Dictionary
- Minimum Height Trees

Mini Challenge:
Modify the solution to return the actual order in which courses can be taken.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 28,
            isCompleted = false,
            codeExample = """
import java.util.*;

class Solution {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list and in-degree array
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            adj.get(prereq[1]).add(prereq[0]); // bi -> ai
            inDegree[prereq[0]]++;
        }

        // Add all nodes with in-degree 0 to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processedCount = 0;

        // Process nodes in topological order
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processedCount++;

            // Reduce in-degree of neighbors
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If all courses were processed, there's no cycle
        return processedCount == numCourses;
    }

    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println("Can finish courses: " + canFinish(numCourses1, prerequisites1));

        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Can finish courses: " + canFinish(numCourses2, prerequisites2));
    }
}"""
        ),

        // Pattern Lesson 29: Bitwise XOR Pattern
        Lesson(
            id = "lp-29",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Bitwise XOR Pattern",
            content = """🟡 Medium

Pattern / Topic Name: Bit Manipulation / XOR Properties

Intuition (Simple Explanation):
XOR has special properties: x ^ x = 0, x ^ 0 = x, and it's commutative and associative. This makes it useful for finding unique elements in arrays where others appear in pairs, swapping variables without temp, or solving problems involving toggling states.

When to Use This Pattern:
Use when you need to find unique elements in an array where all others appear in pairs, when you need to toggle states efficiently, or when you're dealing with binary representations and need to identify differences.

Problem Statement:
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

Step-by-Step Approach:
1. XOR all numbers together
2. Due to XOR properties, paired numbers cancel out (x ^ x = 0)
3. The single number remains (x ^ 0 = x)

Dry Run Example:
nums = [4, 1, 2, 1, 2]
- 4 ^ 1 = 5
- 5 ^ 2 = 7
- 7 ^ 1 = 6
- 6 ^ 2 = 4
Result: 4

Java Runnable Code:
class Solution {

    public static int singleNumber(int[] nums) {
        int result = 0;

        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 1};
        System.out.println("Single number: " + singleNumber(nums1));

        int[] nums2 = {4, 1, 2, 1, 2};
        System.out.println("Single number: " + singleNumber(nums2));
    }
}

Time & Space Complexity:
- Time: O(n) - single pass through the array
- Space: O(1) - only using one variable

Common Mistakes:
1. Not understanding XOR properties thoroughly
2. Applying XOR when it's not appropriate
3. Forgetting to initialize result to 0

LeetCode Practice Problems:
- Single Number II
- Single Number III
- Missing Number

Mini Challenge:
Find two unique numbers in an array where all others appear exactly twice.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 29,
            isCompleted = false,
            codeExample = """
class Solution {

    public static int singleNumber(int[] nums) {
        int result = 0;

        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 1};
        System.out.println("Single number: " + singleNumber(nums1));

        int[] nums2 = {4, 1, 2, 1, 2};
        System.out.println("Single number: " + singleNumber(nums2));
    }
}"""
        ),

        // Pattern Lesson 30: Pattern Recognition Decision Tree Lesson
        Lesson(
            id = "lp-30",
            courseId = "leetcode-patterns",
            language = "java",
            title = "Algorithm Pattern Recognition Decision Tree",
            content = """🟡 Medium

Pattern / Topic Name: Algorithm Pattern Classification

Intuition (Simple Explanation):
Different algorithmic problems require different approaches. By recognizing the characteristics of a problem, you can decide which pattern to apply. This lesson helps you develop a systematic approach to classify problems and choose appropriate algorithms.

When to Use This Pattern:
Use this decision-making framework when approaching new problems. This helps in quickly identifying the most suitable algorithmic pattern based on problem characteristics.

Problem Statement:
Given a problem description, identify which algorithmic pattern is most appropriate.

Step-by-Step Approach:
1. Analyze the problem requirements
2. Identify key characteristics (optimization, counting, searching, etc.)
3. Match characteristics to known patterns
4. Consider constraints and requirements
5. Select the most appropriate pattern

Pattern Decision Tree:
- Does the problem ask to find optimal value? → DP, Greedy, or Binary Search on Answer
- Does the problem involve sequences with constraints? → Sliding Window
- Does the problem require finding elements in sorted array? → Binary Search
- Does the problem involve trees? → Tree BFS/DFS
- Does the problem involve graphs? → BFS/DFS/Topological Sort
- Does the problem involve pairs/elements that sum to target? → Two Pointers
- Does the problem involve cycles in linked list/array? → Fast & Slow Pointers
- Does the problem involve subsets/permutations? → Backtracking
- Does the problem involve finding unique elements? → XOR
- Does the problem involve intervals? → Merge Intervals

Dry Run Example:
Problem: Find two numbers in sorted array that sum to target
- Characteristic: Finding pairs that sum to target in sorted array
- Pattern: Two Pointers
- Approach: Left at start, right at end, adjust based on sum

Problem: Find maximum sum of subarray of size k
- Characteristic: Fixed-size subarray optimization
- Pattern: Sliding Window
- Approach: Maintain window of size k, slide and update

Java Runnable Code:
class PatternRecognitionGuide {

    public static String identifyPattern(String problemDescription) {
        String desc = problemDescription.toLowerCase();

        if (desc.contains("sort") && desc.contains("cycle")) {
            return "Fast & Slow Pointers";
        } else if (desc.contains("sum") && desc.contains("sorted") && desc.contains("target")) {
            return "Two Pointers";
        } else if (desc.contains("subarray") && desc.contains("size") && desc.contains("maximum")) {
            return "Sliding Window";
        } else if (desc.contains("binary") && desc.contains("search")) {
            return "Binary Search";
        } else if (desc.contains("tree") && desc.contains("level")) {
            return "Tree BFS";
        } else if (desc.contains("optimize") && desc.contains("subproblem")) {
            return "Dynamic Programming";
        } else if (desc.contains("schedule") && desc.contains("interval")) {
            return "Greedy (Interval Scheduling)";
        } else if (desc.contains("unique") && desc.contains("pair")) {
            return "Bitwise XOR";
        } else {
            return "General approach needed - analyze specifics";
        }
    }

    public static void main(String[] args) {
        String[] problems = {
            "Find two numbers in sorted array that sum to target",
            "Find maximum sum of subarray of size k",
            "Detect cycle in linked list",
            "Find maximum subarray sum"
        };

        for (String problem : problems) {
            System.out.println("Problem: " + problem);
            System.out.println("Pattern: " + identifyPattern(problem));
            System.out.println();
        }

        System.out.println("Pattern Recognition Tips:");
        System.out.println("1. Counting problems: Often use DP or Combinatorics");
        System.out.println("2. Optimization problems: Consider DP, Greedy, or Binary Search");
        System.out.println("3. Graph problems: Think BFS, DFS, or Topological Sort");
        System.out.println("4. Array problems: Consider Two Pointers, Sliding Window, or Binary Search");
        System.out.println("5. Tree problems: Think BFS, DFS, or specific tree algorithms");
    }
}

Time & Space Complexity:
- Varies by specific pattern applied
- Pattern recognition itself is O(1) after analysis

Common Mistakes:
1. Not spending enough time analyzing the problem
2. Forcing a pattern that doesn't fit
3. Overcomplicating simple problems

LeetCode Practice Problems:
- Apply this decision tree to classify problems before solving
- Review solutions to understand why specific patterns were chosen

Mini Challenge:
Classify the following problems: Jump Game, Valid Parentheses, Merge Intervals, Kth Largest Element.

Pattern XP: +10
Streak Bonus: Eligible
Badge: Pattern Ninja""",
            type = LessonType.THEORY,
            order = 30,
            isCompleted = false,
            codeExample = """
class PatternRecognitionGuide {

    public static String identifyPattern(String problemDescription) {
        String desc = problemDescription.toLowerCase();

        if (desc.contains("sort") && desc.contains("cycle")) {
            return "Fast & Slow Pointers";
        } else if (desc.contains("sum") && desc.contains("sorted") && desc.contains("target")) {
            return "Two Pointers";
        } else if (desc.contains("subarray") && desc.contains("size") && desc.contains("maximum")) {
            return "Sliding Window";
        } else if (desc.contains("binary") && desc.contains("search")) {
            return "Binary Search";
        } else if (desc.contains("tree") && desc.contains("level")) {
            return "Tree BFS";
        } else if (desc.contains("optimize") && desc.contains("subproblem")) {
            return "Dynamic Programming";
        } else if (desc.contains("schedule") && desc.contains("interval")) {
            return "Greedy (Interval Scheduling)";
        } else if (desc.contains("unique") && desc.contains("pair")) {
            return "Bitwise XOR";
        } else {
            return "General approach needed - analyze specifics";
        }
    }

    public static void main(String[] args) {
        String[] problems = {
            "Find two numbers in sorted array that sum to target",
            "Find maximum sum of subarray of size k",
            "Detect cycle in linked list",
            "Find maximum subarray sum"
        };

        for (String problem : problems) {
            System.out.println("Problem: " + problem);
            System.out.println("Pattern: " + identifyPattern(problem));
            System.out.println();
        }

        System.out.println("Pattern Recognition Tips:");
        System.out.println("1. Counting problems: Often use DP or Combinatorics");
        System.out.println("2. Optimization problems: Consider DP, Greedy, or Binary Search");
        System.out.println("3. Graph problems: Think BFS, DFS, or Topological Sort");
        System.out.println("4. Array problems: Consider Two Pointers, Sliding Window, or Binary Search");
        System.out.println("5. Tree problems: Think BFS, DFS, or specific tree algorithms");
    }
}"""
        )
    )
}
