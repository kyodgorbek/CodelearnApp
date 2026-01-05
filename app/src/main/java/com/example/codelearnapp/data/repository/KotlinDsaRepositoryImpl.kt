package com.example.codelearnapp.data.repository

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.model.LessonType
import com.example.codelearnapp.domain.model.Quiz

object KotlinDsaRepositoryImpl {
    val kotlinDsaLessons = listOf(
        // SECTION 1: INTRODUCTION & COMPLEXITY (Lessons 1-5)
        Lesson(
            id = "kt-dsa-1",
            courseId = "kotlin-dsa",
            title = "Introduction to DSA in Kotlin",
            content = "Data Structures and Algorithms are the foundation of efficient programming. Kotlin provides powerful collection frameworks to implement them.",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
                fun main() {
                    // Kotlin's standard library is rich with DS tools
                    val list = listOf(1, 2, 3)
                    val map = mapOf("key" to "value")
                    val set = setOf(1, 2, 3)
                    
                    println("Ready to master DSA with Kotlin!")
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-2",
            courseId = "kotlin-dsa",
            title = "Big O Notation",
            content = "Big O notation describes the performance or complexity of an algorithm. common orders: O(1), O(n), O(log n), O(n^2).",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
                // O(1) - Constant Time
                fun getFirst(list: List<Int>): Int = list[0]
                
                // O(n) - Linear Time
                fun findMax(list: List<Int>): Int {
                    var max = Int.MIN_VALUE
                    for (num in list) {
                        if (num > max) max = num
                    }
                    return max
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-3",
            courseId = "kotlin-dsa",
            title = "Time vs Space Complexity",
            content = "Time complexity is about speed; Space complexity is about memory usage.",
            type = LessonType.THEORY,
            order = 3,
            isCompleted = false,
            codeExample = """
                // O(n) Space - Creates a new list
                fun doubleList(list: List<Int>): List<Int> {
                    return list.map { it * 2 }
                }
                
                // O(1) Space - Modifies in place (if mutable)
                fun printDoubles(list: List<Int>) {
                    for (num in list) println(num * 2)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-4",
            courseId = "kotlin-dsa",
            title = "Practice: Complexity",
            content = "Write a function that sums an array. What is the complexity?",
            type = LessonType.CODE_PRACTICE,
            order = 4,
            isCompleted = false,
            codeExample = "fun sum(arr: IntArray): Int {\n    var total = 0\n    for (i in arr) total += i\n    return total\n}\n// Complexity is O(n)"
        ),
        Lesson(
            id = "kt-dsa-5",
            courseId = "kotlin-dsa",
            title = "Quiz: Big O",
            content = "Test your understanding of Algorithmic Complexity",
            type = LessonType.QUIZ,
            order = 5,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-dsa-quiz-1",
                question = "What is the time complexity of accessing an array element by index?",
                options = listOf("O(1)", "O(n)", "O(log n)", "O(n^2)"),
                correctAnswer = 0,
                explanation = "Array access is constant time O(1) because we know the memory address offset."
            )
        ),

        // SECTION 2: ARRAYS & STRINGS (Lessons 6-12)
        Lesson(
            id = "kt-dsa-6",
            courseId = "kotlin-dsa",
            title = "Kotlin Arrays",
            content = "Arrays are fixed-size sequences of elements. In Kotlin, use IntArray, Array<T>, etc.",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val arr = IntArray(5) { 0 } // [0, 0, 0, 0, 0]
                    arr[0] = 10
                    
                    val squares = Array(5) { i -> i * i }
                    println(squares.joinToString()) // 0, 1, 4, 9, 16
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-7",
            courseId = "kotlin-dsa",
            title = "Dynamic Arrays (ArrayList)",
            content = "Unlike arrays, ArrayLists can grow dynamically.",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val list = ArrayList<String>()
                    list.add("Kotlin")
                    list.add("Java")
                    list.removeAt(0)
                    println(list.size)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-8",
            courseId = "kotlin-dsa",
            title = "String Manipulation",
            content = "Strings in Kotlin are immutable. Use StringBuilder for heavy modifications.",
            type = LessonType.THEORY,
            order = 8,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val sb = StringBuilder()
                    sb.append("Hello")
                    sb.append(" ")
                    sb.append("World")
                    println(sb.toString())
                    println(sb.reverse().toString())
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-9",
            courseId = "kotlin-dsa",
            title = "Practice: Reverse String",
            content = "Implement a function to reverse a string manually (without .reverse()).",
            type = LessonType.CODE_PRACTICE,
            order = 9,
            isCompleted = false,
            codeExample = "fun reverse(s: String): String {\n    var res = \"\"\n    for(i in s.length-1 downTo 0) res += s[i]\n    return res\n}"
        ),
        Lesson(
            id = "kt-dsa-10",
            courseId = "kotlin-dsa",
            title = "Two Pointers Technique",
            content = "A common pattern for array/string problems (e.g., palindrome check).",
            type = LessonType.THEORY,
            order = 10,
            isCompleted = false,
            codeExample = """
                fun isPalindrome(s: String): Boolean {
                    var left = 0
                    var right = s.length - 1
                    while (left < right) {
                        if (s[left] != s[right]) return false
                        left++
                        right--
                    }
                    return true
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-11",
            courseId = "kotlin-dsa",
            title = "Sliding Window",
            content = "Efficiently find subarrays with specific properties.",
            type = LessonType.THEORY,
            order = 11,
            isCompleted = false,
            codeExample = """
                // Max sum of subarray of size k
                fun maxSum(arr: IntArray, k: Int): Int {
                    if (arr.size < k) return -1
                    var sum = 0
                    for (i in 0 until k) sum += arr[i]
                    var maxS = sum
                    for (i in k until arr.size) {
                        sum += arr[i] - arr[i-k]
                        if (sum > maxS) maxS = sum
                    }
                    return maxS
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-12",
            courseId = "kotlin-dsa",
            title = "Quiz: Arrays",
            content = "Arrays and Strings Knowledge Check",
            type = LessonType.QUIZ,
            order = 12,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-dsa-quiz-2",
                question = "StringBuilder is used because Strings are...?",
                options = listOf("Mutable", "Immutable", "Slow", "Thread-safe"),
                correctAnswer = 1,
                explanation = "Strings in Kotlin/Java are immutable; creating many modified copies is inefficient."
            )
        ),

        // SECTION 3: RECURSION & BACKTRACKING (Lessons 13-17)
        Lesson(
            id = "kt-dsa-13",
            courseId = "kotlin-dsa",
            title = "Recursion Basics",
            content = "A function defined in terms of itself.",
            type = LessonType.THEORY,
            order = 13,
            isCompleted = false,
            codeExample = """
                fun factorial(n: Int): Int {
                    if (n <= 1) return 1
                    return n * factorial(n - 1)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-14",
            courseId = "kotlin-dsa",
            title = "Fibonacci Sequence",
            content = "Classic recursion example. Note: Naive recursion is O(2^n).",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
                fun fib(n: Int): Int {
                    if (n <= 1) return n
                    return fib(n-1) + fib(n-2)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-15",
            courseId = "kotlin-dsa",
            title = "Practice: Recursion",
            content = "Write a recursive function to calculate power(x, n).",
            type = LessonType.CODE_PRACTICE,
            order = 15,
            isCompleted = false,
            codeExample = "fun power(x: Int, n: Int): Int {\n    if (n == 0) return 1\n    return x * power(x, n-1)\n}"
        ),
        Lesson(
            id = "kt-dsa-16",
            courseId = "kotlin-dsa",
            title = "Backtracking",
            content = "Solving problems by building candidates and abandoning them if they fail.",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = """
                // Conceptual: Generating permutations
                fun permute(str: String, l: Int, r: Int) {
                    if (l == r) println(str)
                    else {
                        for (i in l..r) {
                            // swap, recurse, swap back
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-17",
            courseId = "kotlin-dsa",
            title = "Quiz: Recursion",
            content = "Test your recursion knowledge",
            type = LessonType.QUIZ,
            order = 17,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-dsa-quiz-3",
                question = "What is the base case?",
                options = listOf("The loop start", "The condition to stop recursion", "The recursive call", "The initial input"),
                correctAnswer = 1,
                explanation = "The base case terminates the recursive calls preventing infinite loops."
            )
        ),

        // SECTION 4: SORTING OPERATIONS (Lessons 18-24)
        Lesson(
            id = "kt-dsa-18",
            courseId = "kotlin-dsa",
            title = "Bubble Sort",
            content = "Simple O(n^2) sorting algorithm that swaps adjacent elements.",
            type = LessonType.THEORY,
            order = 18,
            isCompleted = false,
            codeExample = """
                fun bubbleSort(arr: IntArray) {
                    val n = arr.size
                    for (i in 0 until n - 1) {
                        for (j in 0 until n - i - 1) {
                            if (arr[j] > arr[j + 1]) {
                                val temp = arr[j]
                                arr[j] = arr[j + 1]
                                arr[j + 1] = temp
                            }
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-19",
            courseId = "kotlin-dsa",
            title = "Selection Sort",
            content = "Selects the smallest element and places it at the front.",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = """
                fun selectionSort(arr: IntArray) {
                    val n = arr.size
                    for (i in 0 until n - 1) {
                        var minIdx = i
                        for (j in i + 1 until n) {
                            if (arr[j] < arr[minIdx]) minIdx = j
                        }
                        val temp = arr[minIdx]
                        arr[minIdx] = arr[i]
                        arr[i] = temp
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-20",
            courseId = "kotlin-dsa",
            title = "Insertion Sort",
            content = "Builds the sorted array one item at a time.",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = """
                fun insertionSort(arr: IntArray) {
                    for (i in 1 until arr.size) {
                        val key = arr[i]
                        var j = i - 1
                        while (j >= 0 && arr[j] > key) {
                            arr[j + 1] = arr[j]
                            j--
                        }
                        arr[j + 1] = key
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-21",
            courseId = "kotlin-dsa",
            title = "Merge Sort",
            content = "Divide and Conquer algorithm. O(n log n).",
            type = LessonType.THEORY,
            order = 21,
            isCompleted = false,
            codeExample = """
                fun mergeSort(arr: IntArray) {
                   // Splitting logic...
                   // Merging logic...
                   // Recursive calls
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-22",
            courseId = "kotlin-dsa",
            title = "Quick Sort",
            content = "Partitions array around a pivot. Fast in practice.",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = """
                fun quickSort(arr: IntArray, low: Int, high: Int) {
                    if (low < high) {
                        val pi = partition(arr, low, high)
                        quickSort(arr, low, pi - 1)
                        quickSort(arr, pi + 1, high)
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-23",
            courseId = "kotlin-dsa",
            title = "Kotlin Built-in Sort",
            content = "Don't reinvent the wheel. Use Kotlin's highly optimized .sort().",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val arr = intArrayOf(5, 2, 9, 1)
                    arr.sort() // Uses Dual-Pivot Quicksort
                    println(arr.joinToString())
                    
                    val list = mutableListOf(5, 2, 9)
                    list.sortDescending()
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-24",
            courseId = "kotlin-dsa",
            title = "Quiz: Sorting",
            content = "Sorting algorithms quiz",
            type = LessonType.QUIZ,
            order = 24,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-dsa-quiz-4",
                question = "Which sort is generally fastest in practice?",
                options = listOf("Bubble Sort", "Quick Sort", "Selection Sort", "Insertion Sort"),
                correctAnswer = 1,
                explanation = "Quick Sort is typically O(n log n) and very cache efficient."
            )
        ),

        // SECTION 5: SEARCHING (Lessons 25-27)
        Lesson(
            id = "kt-dsa-25",
            courseId = "kotlin-dsa",
            title = "Linear Search",
            content = "Scan every element. O(n).",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
                fun search(arr: IntArray, x: Int): Int {
                    for (i in arr.indices) {
                        if (arr[i] == x) return i
                    }
                    return -1
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-26",
            courseId = "kotlin-dsa",
            title = "Binary Search",
            content = "Divide range in half. Requires sorted array. O(log n).",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
                fun binarySearch(arr: IntArray, x: Int): Int {
                    var l = 0
                    var r = arr.size - 1
                    while (l <= r) {
                        val mid = l + (r - l) / 2
                        if (arr[mid] == x) return mid
                        if (arr[mid] < x) l = mid + 1
                        else r = mid - 1
                    }
                    return -1
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-27",
            courseId = "kotlin-dsa",
            title = "Practice: Binary Search",
            content = "Implement Binary Search on a sorted list.",
            type = LessonType.CODE_PRACTICE,
            order = 27,
            isCompleted = false,
            codeExample = "fun bs(arr: IntArray, target: Int): Int {\n    // Implementation\n    return -1\n}"
        ),

        // SECTION 6: LINKED LISTS (Lessons 28-33)
        Lesson(
            id = "kt-dsa-28",
            courseId = "kotlin-dsa",
            title = "Singly Linked List Node",
            content = "Creating a Node class in Kotlin.",
            type = LessonType.THEORY,
            order = 28,
            isCompleted = false,
            codeExample = """
                class ListNode(var `val`: Int) {
                    var next: ListNode? = null
                }
                
                fun main() {
                    val head = ListNode(1)
                    head.next = ListNode(2)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-29",
            courseId = "kotlin-dsa",
            title = "Traversing Linked List",
            content = "Iterating through nodes until next is null.",
            type = LessonType.THEORY,
            order = 29,
            isCompleted = false,
            codeExample = """
                fun printList(head: ListNode?) {
                    var curr = head
                    while (curr != null) {
                        print("${'$'}{curr.`val`} -> ")
                        curr = curr.next
                    }
                    println("null")
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-30",
            courseId = "kotlin-dsa",
            title = "Reverse Linked List",
            content = "Classic interview question: Reverse a linked list iteratively.",
            type = LessonType.THEORY,
            order = 30,
            isCompleted = false,
            codeExample = """
                fun reverse(head: ListNode?): ListNode? {
                    var prev: ListNode? = null
                    var curr = head
                    while (curr != null) {
                        val nextTemp = curr.next
                        curr.next = prev
                        prev = curr
                        curr = nextTemp
                    }
                    return prev
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-31",
            courseId = "kotlin-dsa",
            title = "Detect Cycle (Floyd's Algorithm)",
            content = "Tortoise and Hare technique.",
            type = LessonType.THEORY,
            order = 31,
            isCompleted = false,
            codeExample = """
                fun hasCycle(head: ListNode?): Boolean {
                    var slow = head
                    var fast = head
                    while (fast?.next != null) {
                        slow = slow?.next
                        fast = fast.next?.next
                        if (slow == fast) return true
                    }
                    return false
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-32",
            courseId = "kotlin-dsa",
            title = "Practice: Merge Lists",
            content = "Merge two sorted linked lists.",
            type = LessonType.CODE_PRACTICE,
            order = 32,
            isCompleted = false,
            codeExample = "fun merge(l1: ListNode?, l2: ListNode?): ListNode? {\n    // logic\n    return null\n}"
        ),
        Lesson(
            id = "kt-dsa-33",
            courseId = "kotlin-dsa",
            title = "Quiz: Linked Lists",
            content = "Linked List quiz",
            type = LessonType.QUIZ,
            order = 33,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-dsa-quiz-5",
                question = "What is the complexity of inserting at the Head?",
                options = listOf("O(1)", "O(n)", "O(log n)", "O(n^2)"),
                correctAnswer = 0,
                explanation = "Inserting at the head is just updating pointers, so O(1)."
            )
        ),

        // SECTION 7: STACKS & QUEUES (Lessons 34-39)
        Lesson(
            id = "kt-dsa-34",
            courseId = "kotlin-dsa",
            title = "Stack Implementation",
            content = "LIFO (Last In First Out). Can use ArrayDeque.",
            type = LessonType.THEORY,
            order = 34,
            isCompleted = false,
            codeExample = """
                import java.util.ArrayDeque
                
                fun main() {
                    val stack = ArrayDeque<Int>()
                    stack.push(10)
                    stack.push(20)
                    println(stack.pop()) // 20
                    println(stack.peek()) // 10
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-35",
            courseId = "kotlin-dsa",
            title = "Queue Implementation",
            content = "FIFO (First In First Out). Comparison of implementations.",
            type = LessonType.THEORY,
            order = 35,
            isCompleted = false,
            codeExample = """
                import java.util.LinkedList
                import java.util.Queue

                fun main() {
                    val queue: Queue<String> = LinkedList()
                    queue.offer("First")
                    queue.offer("Second")
                    println(queue.poll()) // First
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-36",
            courseId = "kotlin-dsa",
            title = "Valid Parentheses",
            content = "Use a stack to check balanced brackets ().",
            type = LessonType.THEORY,
            order = 36,
            isCompleted = false,
            codeExample = """
                fun isValid(s: String): Boolean {
                    val stack = java.util.ArrayDeque<Char>()
                    for (c in s) {
                        if (c == '(') stack.push(')')
                        else if (stack.isEmpty() || stack.pop() != c) return false
                    }
                    return stack.isEmpty()
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-37",
            courseId = "kotlin-dsa",
            title = "Practice: Stack",
            content = "Implement a custom Stack class using an ArrayList.",
            type = LessonType.CODE_PRACTICE,
            order = 37,
            isCompleted = false,
            codeExample = "class MyStack {\n    private val list = ArrayList<Int>()\n    fun push(x: Int) { list.add(x) }\n    fun pop(): Int? = if(list.isNotEmpty()) list.removeAt(list.size-1) else null\n}"
        ),
        Lesson(
            id = "kt-dsa-38",
            courseId = "kotlin-dsa",
            title = "Double Ended Queue (Deque)",
            content = "Supporting insertion/deletion at both ends.",
            type = LessonType.THEORY,
            order = 38,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val deque = java.util.ArrayDeque<Int>()
                    deque.addFirst(1)
                    deque.addLast(2)
                    println(deque.removeFirst())
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-39",
            courseId = "kotlin-dsa",
            title = "Quiz: Stacks/Queues",
            content = "Data structures quiz",
            type = LessonType.QUIZ,
            order = 39,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-dsa-quiz-6",
                question = "Which is LIFO?",
                options = listOf("Queue", "Stack", "List", "Tree"),
                correctAnswer = 1,
                explanation = "Stack is Last-In-First-Out."
            )
        ),

        // SECTION 8: TREES (Lessons 40-46)
        Lesson(
            id = "kt-dsa-40",
            courseId = "kotlin-dsa",
            title = "Binary Tree Basics",
            content = "Nodes with at most two children.",
            type = LessonType.THEORY,
            order = 40,
            isCompleted = false,
            codeExample = """
                class TreeNode(var `val`: Int) {
                    var left: TreeNode? = null
                    var right: TreeNode? = null
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-41",
            courseId = "kotlin-dsa",
            title = "Tree Traversal (DFS)",
            content = "Inorder, Preorder, Postorder.",
            type = LessonType.THEORY,
            order = 41,
            isCompleted = false,
            codeExample = """
                fun inorder(root: TreeNode?) {
                    if (root == null) return
                    inorder(root.left)
                    print(root.`val`)
                    inorder(root.right)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-42",
            courseId = "kotlin-dsa",
            title = "Level Order Traversal (BFS)",
            content = "Using a Queue to process level by level.",
            type = LessonType.THEORY,
            order = 42,
            isCompleted = false,
            codeExample = """
                fun levelOrder(root: TreeNode?) {
                    if (root == null) return
                    val q = java.util.LinkedList<TreeNode>()
                    q.offer(root)
                    while(!q.isEmpty()) {
                        val curr = q.poll()
                        print(curr.`val`)
                        if (curr.left != null) q.offer(curr.left)
                        if (curr.right != null) q.offer(curr.right)
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-43",
            courseId = "kotlin-dsa",
            title = "Binary Search Tree (BST)",
            content = "Left < Root < Right logic.",
            type = LessonType.THEORY,
            order = 43,
            isCompleted = false,
            codeExample = """
                fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
                    if (root == null || root.`val` == `val`) return root
                    if (`val` < root.`val`) return searchBST(root.left, `val`)
                    return searchBST(root.right, `val`)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-44",
            courseId = "kotlin-dsa",
            title = "Maximum Depth of Binary Tree",
            content = "Standard recursion problem.",
            type = LessonType.THEORY,
            order = 44,
            isCompleted = false,
            codeExample = """
                fun maxDepth(root: TreeNode?): Int {
                    if (root == null) return 0
                    val left = maxDepth(root.left)
                    val right = maxDepth(root.right)
                    return kotlin.math.max(left, right) + 1
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-45",
            courseId = "kotlin-dsa",
            title = "Practice: Invert Tree",
            content = "Invert a binary tree (swap left and right children).",
            type = LessonType.CODE_PRACTICE,
            order = 45,
            isCompleted = false,
            codeExample = "fun invertTree(root: TreeNode?): TreeNode? {\n    if (root == null) return null\n    val temp = root.left\n    root.left = invertTree(root.right)\n    root.right = invertTree(temp)\n    return root\n}"
        ),
        Lesson(
            id = "kt-dsa-46",
            courseId = "kotlin-dsa",
            title = "Quiz: Trees",
            content = "Tree Quiz",
            type = LessonType.QUIZ,
            order = 46,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-dsa-quiz-7",
                question = "In a BST, where are smaller values located?",
                options = listOf("Right Subtree", "Left Subtree", "Root", "Parent"),
                correctAnswer = 1,
                explanation = "Values smaller than the node are in the Left Subtree."
            )
        ),

        // SECTION 9: HEAP & HASHING (Lessons 47-53)
        Lesson(
            id = "kt-dsa-47",
            courseId = "kotlin-dsa",
            title = "Priority Queue (Heap)",
            content = "Using PriorityQueue in Kotlin.",
            type = LessonType.THEORY,
            order = 47,
            isCompleted = false,
            codeExample = """
                import java.util.PriorityQueue
                
                fun main() {
                    // Min Heap by default
                    val pq = PriorityQueue<Int>()
                    pq.add(10)
                    pq.add(5)
                    println(pq.poll()) // 5
                    
                    // Max Heap via comparator
                    val maxPQ = PriorityQueue<Int>(compareByDescending { it })
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-48",
            courseId = "kotlin-dsa",
            title = "Kth Largest Element",
            content = "Find the Kth largest using a Min Heap.",
            type = LessonType.THEORY,
            order = 48,
            isCompleted = false,
            codeExample = """
                fun findKthLargest(nums: IntArray, k: Int): Int {
                    val pq = java.util.PriorityQueue<Int>()
                    for (num in nums) {
                        pq.add(num)
                        if (pq.size > k) pq.poll()
                    }
                    return pq.peek()
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-49",
            courseId = "kotlin-dsa",
            title = "HashMap Internals",
            content = "How hashing works: collisions, buckets, load factor.",
            type = LessonType.THEORY,
            order = 49,
            isCompleted = false,
            codeExample = """
                // Kotlin's HashMap uses chaining for collisions
                // Efficiency: O(1) average for get/put
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-50",
            courseId = "kotlin-dsa",
            title = "Two Sum",
            content = "Classic problem: Find two numbers that add up to target.",
            type = LessonType.THEORY,
            order = 50,
            isCompleted = false,
            codeExample = """
                fun twoSum(nums: IntArray, target: Int): IntArray {
                    val map = HashMap<Int, Int>()
                    for (i in nums.indices) {
                        val complement = target - nums[i]
                        if (map.containsKey(complement)) {
                            return intArrayOf(map[complement]!!, i)
                        }
                        map[nums[i]] = i
                    }
                    return intArrayOf()
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-51",
            courseId = "kotlin-dsa",
            title = "HashSet Usage",
            content = "Handling duplicates efficiently.",
            type = LessonType.THEORY,
            order = 51,
            isCompleted = false,
            codeExample = """
                fun containsDuplicate(nums: IntArray): Boolean {
                    val set = HashSet<Int>()
                    for (num in nums) {
                        if (set.contains(num)) return true
                        set.add(num)
                    }
                    return false
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-52",
            courseId = "kotlin-dsa",
            title = "Practice: Hashing",
            content = "Find the first non-repeating character in a string.",
            type = LessonType.CODE_PRACTICE,
            order = 52,
            isCompleted = false,
            codeExample = "fun firstUniqChar(s: String): Int {\n    // Count frequencies map\n    return -1\n}"
        ),
        Lesson(
            id = "kt-dsa-53",
            courseId = "kotlin-dsa",
            title = "Quiz: Hashing",
            content = "Hash quiz",
            type = LessonType.QUIZ,
            order = 53,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-dsa-quiz-8",
                question = "What is average time complexity of HashMap lookup?",
                options = listOf("O(1)", "O(n)", "O(log n)", "O(n^2)"),
                correctAnswer = 0,
                explanation = "Good hash functions distribute keys evenly giving O(1)."
            )
        ),

        // SECTION 10: ADVANCED TOPICS (Lessons 54-60)
        Lesson(
            id = "kt-dsa-54",
            courseId = "kotlin-dsa",
            title = "Graphs Basics",
            content = "Nodes (vertices) and Edges. Adjacency List.",
            type = LessonType.THEORY,
            order = 54,
            isCompleted = false,
            codeExample = """
                // Adjacency List: Map<Int, List<Int>>
                val graph = mapOf(
                    1 to listOf(2, 3),
                    2 to listOf(1, 4),
                    3 to listOf(1),
                    4 to listOf(2)
                )
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-55",
            courseId = "kotlin-dsa",
            title = "Graph Traversal (BFS/DFS)",
            content = "Exploring graphs.",
            type = LessonType.THEORY,
            order = 55,
            isCompleted = false,
            codeExample = """
                fun bfs(graph: Map<Int, List<Int>>, start: Int) {
                    val visited = HashSet<Int>()
                    val queue = java.util.ArrayDeque<Int>()
                    queue.add(start)
                    visited.add(start)
                    while(!queue.isEmpty()) {
                        val node = queue.remove()
                        println(node)
                        graph[node]?.forEach { neighbor ->
                             if (!visited.contains(neighbor)) {
                                 visited.add(neighbor)
                                 queue.add(neighbor)
                             }
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-56",
            courseId = "kotlin-dsa",
            title = "Dynamic Programming: Memoization",
            content = "Top-down approach. Storing results of expensive calls.",
            type = LessonType.THEORY,
            order = 56,
            isCompleted = false,
            codeExample = """
                val memo = HashMap<Int, Int>()
                fun fib(n: Int): Int {
                    if (n <= 1) return n
                    if (memo.containsKey(n)) return memo[n]!!
                    val result = fib(n-1) + fib(n-2)
                    memo[n] = result
                    return result
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-57",
            courseId = "kotlin-dsa",
            title = "Dynamic Programming: Tabulation",
            content = "Bottom-up approach.",
            type = LessonType.THEORY,
            order = 57,
            isCompleted = false,
            codeExample = """
                fun fibTab(n: Int): Int {
                    if (n <= 1) return n
                    val dp = IntArray(n + 1)
                    dp[1] = 1
                    for (i in 2..n) {
                        dp[i] = dp[i-1] + dp[i-2]
                    }
                    return dp[n]
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-58",
            courseId = "kotlin-dsa",
            title = "Climbing Stairs",
            content = "Classic DP problem.",
            type = LessonType.THEORY,
            order = 58,
            isCompleted = false,
            codeExample = """
                fun climbStairs(n: Int): Int {
                    if (n <= 2) return n
                    var a = 1
                    var b = 2
                    for (i in 3..n) {
                        val temp = a + b
                        a = b
                        b = temp
                    }
                    return b
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-59",
            courseId = "kotlin-dsa",
            title = "Trie (Prefix Tree)",
            content = "Data structure for string prefix operations.",
            type = LessonType.THEORY,
            order = 59,
            isCompleted = false,
            codeExample = """
                class TrieNode {
                    val children = HashMap<Char, TrieNode>()
                    var isEnd = false
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-dsa-60",
            courseId = "kotlin-dsa",
            title = "Final Challenge: LRU Cache",
            content = "Implement a Least Recently Used (LRU) Cache mechanism using Hash Map and Linked List.",
            type = LessonType.CHALLENGE,
            order = 60,
            isCompleted = false,
            codeExample = """
                class LRUCache(val capacity: Int) {
                    // Use LinkedHashMap with accessOrder = true
                    val map = object : java.util.LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
                        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
                            return size > capacity
                        }
                    }
                    
                    fun get(key: Int): Int {
                        return map.getOrDefault(key, -1)
                    }
                    
                    fun put(key: Int, value: Int) {
                        map[key] = value
                    }
                }
                
                // Or implement using HashMap + Doubly Linked List manually
            """.trimIndent()
        )
    )
}
