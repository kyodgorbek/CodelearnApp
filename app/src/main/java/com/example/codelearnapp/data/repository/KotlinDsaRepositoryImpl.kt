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

                fun main() {
                    val numbers = listOf(10, 5, 20, 8)
                    println("First: ${'$'}{getFirst(numbers)}")
                    println("Max: ${'$'}{findMax(numbers)}")
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

                fun main() {
                    val nums = listOf(1, 2, 3)
                    val doubled = doubleList(nums)
                    println("Doubled (New List): ${'$'}doubled")
                    
                    println("Printing Doubles (In-Place):")
                    printDoubles(nums)
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
            codeExample = """
                fun sum(arr: IntArray): Int {
                    var total = 0
                    for (i in arr) total += i
                    return total
                }
                
                fun main() {
                    val numbers = intArrayOf(1, 2, 3, 4, 5)
                    println("Sum: ${'$'}{sum(numbers)}") // Output: 15
                }
            """.trimIndent()
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
            codeExample = """
                fun reverse(s: String): String {
                    var res = ""
                    for(i in s.length-1 downTo 0) res += s[i]
                    return res
                }
                
                fun main() {
                    val input = "Kotlin"
                    println("Original: ${'$'}input")
                    println("Reversed: ${'$'}{reverse(input)}") // Output: niltoK
                }
            """.trimIndent()
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

                fun main() {
                    val word1 = "madam"
                    val word2 = "kotlin"
                    println("${'$'}word1 is palindrome? ${'$'}{isPalindrome(word1)}")
                    println("${'$'}word2 is palindrome? ${'$'}{isPalindrome(word2)}")
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

                fun main() {
                    val arr = intArrayOf(1, 4, 2, 10, 23, 3, 1, 0, 20)
                    val k = 4
                    println("Max sum of subarray size ${'$'}k: ${'$'}{maxSum(arr, k)}")
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

                fun main() {
                    val num = 5
                    println("Factorial of ${'$'}num is ${'$'}{factorial(num)}") // 120
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
            codeExample = """
                fun power(x: Int, n: Int): Int {
                    if (n == 0) return 1
                    return x * power(x, n - 1)
                }
                
                fun main() {
                    val base = 2
                    val exponent = 3
                    println("${'$'}base^${'$'}exponent = ${'$'}{power(base, exponent)}") // Output: 8
                }
            """.trimIndent()
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
                // Generating permutations using backtracking
                fun permute(str: String, l: Int, r: Int) {
                    if (l == r) {
                        println(str)
                    } else {
                        for (i in l..r) {
                            val chars = str.toCharArray()
        
                            // swap
                            val temp = chars[l]
                            chars[l] = chars[i]
                            chars[i] = temp
        
                            // recurse
                            permute(String(chars), l + 1, r)
                        }
                    }
                }
        
                fun main() {
                    println("Permutations of 'ABC':")
                    permute("ABC", 0, 2)
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

                fun main() {
                    val arr = intArrayOf(64, 34, 25, 12, 22, 11, 90)
                    bubbleSort(arr)
                    println("Sorted array: ${'$'}{arr.joinToString()}")
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

                fun main() {
                    val arr = intArrayOf(64, 25, 12, 22, 11)
                    selectionSort(arr)
                    println("Sorted array: ${'$'}{arr.joinToString()}")
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

                fun main() {
                    val arr = intArrayOf(12, 11, 13, 5, 6)
                    insertionSort(arr)
                    println("Sorted array: ${'$'}{arr.joinToString()}")
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
                fun mergeSort(arr: IntArray, left: Int, right: Int) {
                    if (left < right) {
                        val mid = (left + right) / 2
                        mergeSort(arr, left, mid)
                        mergeSort(arr, mid + 1, right)
                        merge(arr, left, mid, right)
                    }
                }

                fun merge(arr: IntArray, left: Int, mid: Int, right: Int) {
                    val n1 = mid - left + 1
                    val n2 = right - mid
                    val L = IntArray(n1)
                    val R = IntArray(n2)
                    for (i in 0 until n1) L[i] = arr[left + i]
                    for (j in 0 until n2) R[j] = arr[mid + 1 + j]

                    var i = 0; var j = 0; var k = left
                    while (i < n1 && j < n2) {
                        if (L[i] <= R[j]) { arr[k] = L[i]; i++ } 
                        else { arr[k] = R[j]; j++ }
                        k++
                    }
                    while (i < n1) { arr[k] = L[i]; i++; k++ }
                    while (j < n2) { arr[k] = R[j]; j++; k++ }
                }

                fun main() {
                    val arr = intArrayOf(38, 27, 43, 3, 9, 82, 10)
                    mergeSort(arr, 0, arr.size - 1)
                    println("Sorted array: ${'$'}{arr.joinToString()}")
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

                fun partition(arr: IntArray, low: Int, high: Int): Int {
                    val pivot = arr[high]
                    var i = (low - 1)
                    for (j in low until high) {
                        if (arr[j] <= pivot) {
                            i++
                            val temp = arr[i]; arr[i] = arr[j]; arr[j] = temp
                        }
                    }
                    val temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp
                    return i + 1
                }

                fun main() {
                    val arr = intArrayOf(10, 7, 8, 9, 1, 5)
                    quickSort(arr, 0, arr.size - 1)
                    println("Sorted array: ${'$'}{arr.joinToString()}")
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

                fun main() {
                    val arr = intArrayOf(2, 3, 4, 10, 40)
                    val x = 10
                    val result = search(arr, x)
                    println(if (result == -1) "Element not found" else "Element found at index ${'$'}result")
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

                fun main() {
                    val arr = intArrayOf(2, 3, 4, 10, 40)
                    val x = 10
                    val result = binarySearch(arr, x)
                    println(if (result == -1) "Element not found" else "Element found at index ${'$'}result")
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
            codeExample = """
                fun binarySearch(arr: IntArray, target: Int): Int {
                    var left = 0
                    var right = arr.size - 1
                    while (left <= right) {
                        val mid = left + (right - left) / 2
                        if (arr[mid] == target) return mid
                        if (arr[mid] < target) left = mid + 1
                        else right = mid - 1
                    }
                    return -1
                }
                
                fun main() {
                    val sorted = intArrayOf(1, 3, 5, 7, 9)
                    val target = 5
                    println("Index of ${'$'}target: ${'$'}{binarySearch(sorted, target)}") // Output: 2
                }
            """.trimIndent()
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
                class ListNode(var `val`: Int) {
                    var next: ListNode? = null
                }

                fun printList(head: ListNode?) {
                    var curr = head
                    while (curr != null) {
                        print("${'$'}{curr.`val`} -> ")
                        curr = curr.next
                    }
                    println("null")
                }

                fun main() {
                    val head = ListNode(1)
                    head.next = ListNode(2)
                    head.next?.next = ListNode(3)
                    printList(head)
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
                class ListNode(var `val`: Int) {
                    var next: ListNode? = null
                }

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

                fun printList(head: ListNode?) {
                    var curr = head
                    while (curr != null) {
                        print("${'$'}{curr.`val`} -> ")
                        curr = curr.next
                    }
                    println("null")
                }

                fun main() {
                    val head = ListNode(1)
                    head.next = ListNode(2)
                    head.next?.next = ListNode(3)
                    
                    println("Original:")
                    printList(head)
                    
                    val newHead = reverse(head)
                    println("Reversed:")
                    printList(newHead)
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
                class ListNode(var `val`: Int) {
                    var next: ListNode? = null
                }

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

                fun main() {
                    val head = ListNode(1)
                    val second = ListNode(2)
                    val third = ListNode(3)
                    head.next = second
                    second.next = third
                    third.next = second // Cycle!
                    
                    println("Has Cycle? ${'$'}{hasCycle(head)}")
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
            codeExample = """
                class ListNode(var `val`: Int) {
                    var next: ListNode? = null
                }
                
                fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
                    if (l1 == null) return l2
                    if (l2 == null) return l1
                    
                    if (l1.`val` < l2.`val`) {
                        l1.next = mergeTwoLists(l1.next, l2)
                        return l1
                    } else {
                        l2.next = mergeTwoLists(l1, l2.next)
                        return l2
                    }
                }
                
                fun main() {
                    // Create simple lists: 1->3 and 2->4
                    val l1 = ListNode(1).apply { next = ListNode(3) }
                    val l2 = ListNode(2).apply { next = ListNode(4) }
                    
                    var merged = mergeTwoLists(l1, l2)
                    print("Merged: ")
                    while(merged != null) {
                        print("${'$'}{merged.`val`} -> ")
                        merged = merged.next
                    }
                    println("null")
                }
            """.trimIndent()
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
            codeExample = """
                class MyStack {
                    private val list = java.util.ArrayList<Int>()
                    
                    fun push(x: Int) { 
                        list.add(x) 
                        println("Pushed: ${'$'}x")
                    }
                    
                    fun pop(): Int? {
                        if(list.isNotEmpty()) {
                            val item = list.removeAt(list.size-1)
                            println("Popped: ${'$'}item")
                            return item
                        }
                        return null
                    }
                }
                
                fun main() {
                    val stack = MyStack()
                    stack.push(10)
                    stack.push(20)
                    stack.pop()
                    stack.pop()
                }
            """.trimIndent()
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
                class TreeNode(var `val`: Int) {
                    var left: TreeNode? = null
                    var right: TreeNode? = null
                }

                fun inorder(root: TreeNode?) {
                    if (root == null) return
                    inorder(root.left)
                    print("${'$'}{root.`val`} ")
                    inorder(root.right)
                }

                fun preorder(root: TreeNode?) {
                    if (root == null) return
                    print("${'$'}{root.`val`} ")
                    preorder(root.left)
                    preorder(root.right)
                }

                fun postorder(root: TreeNode?) {
                    if (root == null) return
                    postorder(root.left)
                    postorder(root.right)
                    print("${'$'}{root.`val`} ")
                }

                fun main() {
                    //      1
                    //     / \
                    //    2   3
                    val root = TreeNode(1)
                    root.left = TreeNode(2)
                    root.right = TreeNode(3)

                    print("Inorder: ")
                    inorder(root)
                    println()

                    print("Preorder: ")
                    preorder(root)
                    println()

                    print("Postorder: ")
                    postorder(root)
                    println()
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
                class TreeNode(var `val`: Int) {
                    var left: TreeNode? = null
                    var right: TreeNode? = null
                }

                fun levelOrder(root: TreeNode?) {
                    if (root == null) return
                    val q = java.util.LinkedList<TreeNode>()
                    q.offer(root)
                    while(!q.isEmpty()) {
                        val curr = q.poll()
                        print("${'$'}{curr.`val`} ")
                        if (curr.left != null) q.offer(curr.left)
                        if (curr.right != null) q.offer(curr.right)
                    }
                }

                fun main() {
                    //      1
                    //     / \
                    //    2   3
                    //   / \
                    //  4   5
                    val root = TreeNode(1)
                    root.left = TreeNode(2)
                    root.right = TreeNode(3)
                    root.left?.left = TreeNode(4)
                    root.left?.right = TreeNode(5)

                    print("Level Order: ")
                    levelOrder(root) 
                    // Expected: 1 2 3 4 5
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
                class TreeNode(var `val`: Int) {
                    var left: TreeNode? = null
                    var right: TreeNode? = null
                }

                fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
                    if (root == null || root.`val` == `val`) return root
                    if (`val` < root.`val`) return searchBST(root.left, `val`)
                    return searchBST(root.right, `val`)
                }

                fun main() {
                    //      4
                    //     / \
                    //    2   7
                    //   / \
                    //  1   3
                    val root = TreeNode(4)
                    root.left = TreeNode(2)
                    root.right = TreeNode(7)
                    root.left?.left = TreeNode(1)
                    root.left?.right = TreeNode(3)

                    val target = 2
                    val result = searchBST(root, target)
                    println("Searching for ${'$'}target: ${'$'}{if (result != null) "Found" else "Not Found"}")

                    val missing = 5
                    val result2 = searchBST(root, missing)
                    println("Searching for ${'$'}missing: ${'$'}{if (result2 != null) "Found" else "Not Found"}")
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
                class TreeNode(var `val`: Int) {
                    var left: TreeNode? = null
                    var right: TreeNode? = null
                }

                fun maxDepth(root: TreeNode?): Int {
                    if (root == null) return 0
                    val left = maxDepth(root.left)
                    val right = maxDepth(root.right)
                    return kotlin.math.max(left, right) + 1
                }

                fun main() {
                    //      3
                    //     / \
                    //    9  20
                    //      /  \
                    //     15   7
                    val root = TreeNode(3)
                    root.left = TreeNode(9)
                    root.right = TreeNode(20)
                    root.right?.left = TreeNode(15)
                    root.right?.right = TreeNode(7)

                    println("Max Depth: ${'$'}{maxDepth(root)}") // Expected: 3
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
            codeExample = """
                class TreeNode(var `val`: Int) {
                    var left: TreeNode? = null
                    var right: TreeNode? = null
                }
                
                fun invertTree(root: TreeNode?): TreeNode? {
                    if (root == null) return null
                    val temp = root.left
                    root.left = invertTree(root.right)
                    root.right = invertTree(temp)
                    return root
                }
                
                fun printTree(root: TreeNode?) {
                    if(root == null) return
                    print("${'$'}{root.`val`} ")
                    printTree(root.left)
                    printTree(root.right)
                }

                fun main() {
                    // Tree: 1 -> (Left: 2, Right: 3)
                    val root = TreeNode(1)
                    root.left = TreeNode(2)
                    root.right = TreeNode(3)
                    
                    println("Original Pre-order:")
                    printTree(root)
                    println()
                    
                    invertTree(root)
                    
                    println("Inverted Pre-order:")
                    printTree(root)
                }
            """.trimIndent()
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
                    println("Min Heap:")
                    val pq = PriorityQueue<Int>()
                    pq.add(10)
                    pq.add(5)
                    pq.add(20)
                    while(!pq.isEmpty()) {
                        print("${'$'}{pq.poll()} ") // 5 10 20
                    }
                    println()
                    
                    // Max Heap via comparator
                    println("Max Heap:")
                    val maxPQ = PriorityQueue<Int>(compareByDescending { it })
                    maxPQ.add(10)
                    maxPQ.add(5)
                    maxPQ.add(20)
                    while(!maxPQ.isEmpty()) {
                        print("${'$'}{maxPQ.poll()} ") // 20 10 5
                    }
                    println()
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

                fun main() {
                    val nums = intArrayOf(3, 2, 1, 5, 6, 4)
                    val k = 2
                    println("The ${'$'}k-th largest element in ${'$'}{nums.joinToString()} is ${'$'}{findKthLargest(nums, k)}") // 5
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
                fun main() {
                    // Kotlin's HashMap uses chaining for collisions
                    val map = java.util.HashMap<String, Int>()
                    
                    // Adding keys that might collide (conceptual)
                    map["Aa"] = 1
                    map["BB"] = 2  // "Aa" and "BB" often have same hashCode in Java strings
                    
                    println("Map content: ${'$'}map")
                    println("Size: ${'$'}{map.size}")
                    println("Value for 'Aa': ${'$'}{map["Aa"]}")
                    
                    // Efficiency: O(1) average for get/put
                }
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

                fun main() {
                    val nums = intArrayOf(2, 7, 11, 15)
                    val target = 9
                    val result = twoSum(nums, target)
                    println("Indices finding ${'$'}target: ${'$'}{result.joinToString()}")
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

                fun main() {
                    val nums1 = intArrayOf(1, 2, 3, 1)
                    val nums2 = intArrayOf(1, 2, 3, 4)
                    println("Array 1 contains duplicate? ${'$'}{containsDuplicate(nums1)}")
                    println("Array 2 contains duplicate? ${'$'}{containsDuplicate(nums2)}")
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
            codeExample = """
                fun firstUniqChar(s: String): Int {
                    val count = HashMap<Char, Int>()
                    for (c in s) {
                        count[c] = count.getOrDefault(c, 0) + 1
                    }
                    for (i in s.indices) {
                        if (count[s[i]] == 1) return i
                    }
                    return -1
                }

                fun main() {
                    val s = "leetcode"
                    println("First unique index in '${'$'}s': ${'$'}{firstUniqChar(s)}") // 0 (l)
                    
                    val s2 = "loveleetcode"
                    println("First unique index in '${'$'}s2': ${'$'}{firstUniqChar(s2)}") // 2 (v)
                }
            """.trimIndent()
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
                fun main() {
                    // Adjacency List: Map<Int, List<Int>>
                    val graph = mapOf(
                        1 to listOf(2, 3),
                        2 to listOf(1, 4),
                        3 to listOf(1),
                        4 to listOf(2)
                    )
                    
                    println("Graph Adjacency List:")
                    for ((node, neighbors) in graph) {
                        println("${'$'}node -> ${'$'}neighbors")
                    }
                }
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
                    println("BFS Traversal starting from ${'$'}start:")
                    val visited = HashSet<Int>()
                    val queue = java.util.ArrayDeque<Int>()
                    queue.add(start)
                    visited.add(start)
                    while(!queue.isEmpty()) {
                        val node = queue.remove()
                        print("${'$'}node ")
                        graph[node]?.forEach { neighbor ->
                             if (!visited.contains(neighbor)) {
                                 visited.add(neighbor)
                                 queue.add(neighbor)
                             }
                        }
                    }
                    println()
                }

                fun dfs(graph: Map<Int, List<Int>>, start: Int, visited: HashSet<Int> = HashSet()) {
                    if (!visited.contains(start)) {
                        visited.add(start)
                        print("${'$'}start ")
                        graph[start]?.forEach { neighbor ->
                            dfs(graph, neighbor, visited)
                        }
                    }
                }

                fun main() {
                    val graph = mapOf(
                        0 to listOf(1, 2),
                        1 to listOf(2),
                        2 to listOf(0, 3),
                        3 to listOf(3)
                    )
                    
                    bfs(graph, 2)
                    
                    print("DFS Traversal starting from 2: ")
                    val visited = HashSet<Int>()
                    dfs(graph, 2, visited)
                    println()
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

                fun main() {
                    val n = 10
                    println("Fibonacci(${'$'}n) with Memoization: ${'$'}{fib(n)}")
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

                fun main() {
                    val n = 10
                    println("Fibonacci(${'$'}n) with Tabulation: ${'$'}{fibTab(n)}")
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

                fun main() {
                    val n = 5
                    println("Ways to climb ${'$'}n stairs: ${'$'}{climbStairs(n)}")
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

                class Trie {
                    val root = TrieNode()

                    fun insert(word: String) {
                        var curr = root
                        for (c in word) {
                            curr = curr.children.getOrPut(c) { TrieNode() }
                        }
                        curr.isEnd = true
                    }

                    fun search(word: String): Boolean {
                        var curr = root
                        for (c in word) {
                            curr = curr.children[c] ?: return false
                        }
                        return curr.isEnd
                    }
                    
                    fun startsWith(prefix: String): Boolean {
                        var curr = root
                        for (c in prefix) {
                            curr = curr.children[c] ?: return false
                        }
                        return true
                    }
                }

                fun main() {
                    val trie = Trie()
                    trie.insert("apple")
                    println("Inserted 'apple'")
                    
                    println("Search 'apple': ${'$'}{trie.search("apple")}")   // true
                    println("Search 'app': ${'$'}{trie.search("app")}")       // false
                    println("StartsWith 'app': ${'$'}{trie.startsWith("app")}") // true
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
                
                fun main() {
                    val cache = LRUCache(2)
                    
                    cache.put(1, 1)
                    cache.put(2, 2)
                    println("Get 1: ${'$'}{cache.get(1)}") // 1
                    
                    cache.put(3, 3) // Evicts 2
                    println("Get 2: ${'$'}{cache.get(2)}") // -1
                    
                    cache.put(4, 4) // Evicts 1
                    println("Get 1: ${'$'}{cache.get(1)}") // -1
                    println("Get 3: ${'$'}{cache.get(3)}") // 3
                    println("Get 4: ${'$'}{cache.get(4)}") // 4
                }
            """.trimIndent()
        )
    )
}