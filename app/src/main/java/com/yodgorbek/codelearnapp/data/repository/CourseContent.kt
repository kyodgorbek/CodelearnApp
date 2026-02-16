package com.yodgorbek.codelearnapp.data.repository

// CourseContent is currently handled by CourseRepositoryImpl to avoid duplication.
import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.model.LessonType

object CourseContent {
    val javaDsaLessons = listOf(
        // PART 1: Java Algorithms & Logic (1-30)

        // Algorithm Basics
        Lesson("jd-1", "java-dsa", "What Is an Algorithm?", "An algorithm is a step-by-step procedure to solve a problem. It must be clear, finite, and effective.", LessonType.THEORY, 1, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        // Algorithm to add two numbers
                        int a = 5;
                        int b = 10;
                        int sum = a + b;
                        System.out.println("Sum is: " + sum);
                        System.out.println("Step 1: Start");
                        System.out.println("Step 2: Input a and b");
                        System.out.println("Step 3: Calculate sum");
                        System.out.println("Step 4: Output sum");
                        System.out.println("Step 5: Stop");
                    }
                }
            """.trimIndent()),
        Lesson("jd-2", "java-dsa", "Java Program Structure", "Understanding the class structure, main method, and how Java executes code.", LessonType.THEORY, 2, false,
            codeExample = """
                public class Main {
                    // entry point of the program
                    public static void main(String[] args) {
                        System.out.println("Class Name must match file name (in standard java)");
                        System.out.println("public static void main is required to run");
                    }
                }
            """.trimIndent()),
        Lesson("jd-3", "java-dsa", "Input & Output (Scanner)", "Learn how to print to the console and read user input using the Scanner class.", LessonType.CODE_PRACTICE, 3, false,
            codeExample = """
                import java.util.Scanner;
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Enter name (simulated):");
                        // In this environment, we simulate input or use hardcoded values for demo
                        String name = "CodeLearner";
                        System.out.println("Hello, " + name);
                        System.out.println("Output uses System.out.println()");
                    }
                }
            """.trimIndent()),
        Lesson("jd-4", "java-dsa", "Variables & Data Types", "Primitive types (int, double, boolean) vs Reference types (String).", LessonType.THEORY, 4, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int age = 25;
                        double price = 19.99;
                        boolean learning = true;
                        String topic = "DSA";

                        System.out.println("Age: " + age);
                        System.out.println("Price: $" + price);
                        System.out.println("Learning " + topic + ": " + learning);
                    }
                }
            """.trimIndent()),
        Lesson("jd-5", "java-dsa", "Operators in Algorithms", "Arithmetic (+,-,*,/,%), Relational (==,!=,>,<), and Logical (&&,||) operators.", LessonType.THEORY, 5, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int a = 10, b = 3;
                        System.out.println("Remainder: " + (a % b)); // Modulus
                        System.out.println("Is Equal: " + (a == b));
                        System.out.println("Logic: " + (a > 5 && b < 5));
                    }
                }
            """.trimIndent()),

        // Control Flow
        Lesson("jd-6", "java-dsa", "if / else Logic", "Making decisions in your code based on conditions.", LessonType.THEORY, 6, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int n = 10;
                        if (n > 0) {
                            System.out.println("Positive");
                        } else if (n < 0) {
                            System.out.println("Negative");
                        } else {
                            System.out.println("Zero");
                        }
                    }
                }
            """.trimIndent()),
        Lesson("jd-7", "java-dsa", "Practice: Odd or Even?", "Write logic to check if a number is odd or even using modulus.", LessonType.CODE_PRACTICE, 7, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int num = 17;
                        if (num % 2 == 0) System.out.println("Even");
                        else System.out.println("Odd");
                    }
                }
            """.trimIndent()),
        Lesson("jd-8", "java-dsa", "switch Case", "Selecting one of many code blocks to be executed.", LessonType.THEORY, 8, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int day = 3;
                        switch (day) {
                            case 1: System.out.println("Monday"); break;
                            case 2: System.out.println("Tuesday"); break;
                            case 3: System.out.println("Wednesday"); break;
                            default: System.out.println("Weekend");
                        }
                    }
                }
            """.trimIndent()),

        // Loops
        Lesson("jd-9", "java-dsa", "for Loop", "Repeating a block of code a known number of times.", LessonType.THEORY, 9, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        // Print numbers 1 to 5
                        for (int i = 1; i <= 5; i++) {
                            System.out.print(i + " ");
                        }
                    }
                }
            """.trimIndent()),
        Lesson("jd-10", "java-dsa", "while & do-while", "Loops that run as long as a condition is true.", LessonType.THEORY, 10, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int i = 5;
                        while(i > 0) {
                            System.out.print(i + " ");
                            i--;
                        }
                        System.out.println();

                        int j = 0;
                        do {
                            System.out.print("Run once ");
                        } while(j != 0);
                    }
                }
            """.trimIndent()),
        Lesson("jd-11", "java-dsa", "break & continue", "Controlling loop execution flow.", LessonType.THEORY, 11, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        for(int i=0; i<10; i++) {
                            if(i == 3) continue; // Skip 3
                            if(i == 6) break;    // Stop at 6
                            System.out.print(i + " ");
                        }
                    }
                }
            """.trimIndent()),

        // Number Algorithms
        Lesson("jd-12", "java-dsa", "Algorithm: Sum of Digits", "Calculate the sum of all digits in a number.", LessonType.CODE_PRACTICE, 12, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int n = 12345;
                        int sum = 0;
                        while(n != 0) {
                            sum += n % 10;
                            n /= 10;
                        }
                        System.out.println("Sum of digits: " + sum);
                    }
                }
            """.trimIndent()),
        Lesson("jd-13", "java-dsa", "Algorithm: Reverse a Number", "Reverse the order of digits in an integer.", LessonType.CODE_PRACTICE, 13, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int n = 9876;
                        int rev = 0;
                        while(n != 0) {
                            rev = rev * 10 + n % 10;
                            n /= 10;
                        }
                        System.out.println("Reversed: " + rev);
                    }
                }
            """.trimIndent()),
        Lesson("jd-14", "java-dsa", "Algorithm: Palindrome Number", "Check if a number reads the same backward as forward.", LessonType.CHALLENGE, 14, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int n = 121;
                        int temp = n, rev = 0;
                        while(temp != 0) {
                            rev = rev * 10 + temp % 10;
                            temp /= 10;
                        }
                        if(n == rev) System.out.println(n + " is Palindrome");
                        else System.out.println(n + " is Not Palindrome");
                    }
                }
            """.trimIndent()),
        Lesson("jd-15", "java-dsa", "Algorithm: Prime Number", "Check if a number has exactly two factors: 1 and itself.", LessonType.CODE_PRACTICE, 15, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int n = 17;
                        boolean isPrime = true;
                        if(n < 2) isPrime = false;
                        for(int i=2; i*i <= n; i++) {
                            if(n % i == 0) {
                                isPrime = false;
                                break;
                            }
                        }
                        System.out.println(n + " is Prime: " + isPrime);
                    }
                }
            """.trimIndent()),
        Lesson("jd-16", "java-dsa", "Algorithm: Factorial", "Calculate n! = n * (n-1) * ... * 1.", LessonType.CODE_PRACTICE, 16, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int n = 5;
                        long fact = 1;
                        for(int i=1; i<=n; i++) fact *= i;
                        System.out.println("Factorial of " + n + " is " + fact);
                    }
                }
            """.trimIndent()),
        Lesson("jd-17", "java-dsa", "Algorithm: Fibonacci Series", "Generate the sequence where each number is the sum of the two preceding ones.", LessonType.CODE_PRACTICE, 17, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int n = 10;
                        int a = 0, b = 1;
                        System.out.print(a + " " + b + " ");
                        for(int i=2; i<n; i++) {
                            int c = a + b;
                            System.out.print(c + " ");
                            a = b;
                            b = c;
                        }
                    }
                }
            """.trimIndent()),
        Lesson("jd-18", "java-dsa", "Algorithm: Count Digits", "Count how many digits are in a number.", LessonType.CODE_PRACTICE, 18, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int n = 54321;
                        int count = 0;
                        while(n != 0) {
                            n /= 10;
                            count++;
                        }
                        System.out.println("Digits: " + count);
                    }
                }
            """.trimIndent()),
        Lesson("jd-19", "java-dsa", "Algorithm: Power of a Number", "Calculate base raised to the power of exponent.", LessonType.CODE_PRACTICE, 19, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int base = 2, exp = 3;
                        int result = 1;
                        for(int i=0; i<exp; i++) result *= base;
                        System.out.println(base + "^" + exp + " = " + result);
                    }
                }
            """.trimIndent()),

        // Arrays & Strings
        Lesson("jd-20", "java-dsa", "Arrays Basics", "Declaring, initializing, and accessing arrays.", LessonType.THEORY, 20, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int[] arr = {10, 20, 30, 40, 50};
                        System.out.println("First element: " + arr[0]);
                        System.out.println("Size: " + arr.length);
                        for(int x : arr) System.out.print(x + " ");
                    }
                }
            """.trimIndent()),
        Lesson("jd-21", "java-dsa", "Find Max & Min Logic", "Algorithm to find the largest and smallest element in an array.", LessonType.CODE_PRACTICE, 21, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int[] arr = {5, 2, 9, 1, 7};
                        int max = arr[0], min = arr[0];
                        for(int i=1; i<arr.length; i++) {
                            if(arr[i] > max) max = arr[i];
                            if(arr[i] < min) min = arr[i];
                        }
                        System.out.println("Max: " + max + ", Min: " + min);
                    }
                }
            """.trimIndent()),
        Lesson("jd-22", "java-dsa", "Reverse an Array", "Swap elements from start and end to reverse an array in-place.", LessonType.CODE_PRACTICE, 22, false,
            codeExample = """
                import java.util.Arrays;
                public class Main {
                    public static void main(String[] args) {
                        int[] arr = {1, 2, 3, 4, 5};
                        int start = 0, end = arr.length-1;
                        while(start < end) {
                            int temp = arr[start];
                            arr[start] = arr[end];
                            arr[end] = temp;
                            start++; end--;
                        }
                        System.out.println(Arrays.toString(arr));
                    }
                }
            """.trimIndent()),
        Lesson("jd-23", "java-dsa", "Sum & Average", "Calculate the total sum and average value of array elements.", LessonType.CODE_PRACTICE, 23, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int[] arr = {10, 20, 30, 40, 50};
                        int sum = 0;
                        for(int x : arr) sum += x;
                        double avg = (double)sum / arr.length;
                        System.out.println("Sum: " + sum + ", Avg: " + avg);
                    }
                }
            """.trimIndent()),
        Lesson("jd-24", "java-dsa", "Linear Search", " Find an element's position in an array.", LessonType.THEORY, 24, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int[] arr = {10, 50, 30, 70, 80};
                        int target = 30;
                        int index = -1;
                        for(int i=0; i<arr.length; i++) {
                            if(arr[i] == target) {
                                index = i;
                                break;
                            }
                        }
                        System.out.println("Element found at index: " + index);
                    }
                }
            """.trimIndent()),
        Lesson("jd-25", "java-dsa", "Strings Basics", "String manipulation fundamentals in Java.", LessonType.THEORY, 25, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        String s = "CodeLearn";
                        System.out.println("Length: " + s.length());
                        System.out.println("Char at 0: " + s.charAt(0));
                        System.out.println("Substring: " + s.substring(4));
                    }
                }
            """.trimIndent()),
        Lesson("jd-26", "java-dsa", "Reverse String", "Algorithm to reverse a string.", LessonType.CODE_PRACTICE, 26, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        String s = "Java";
                        String rev = "";
                        for(int i=s.length()-1; i>=0; i--) {
                            rev += s.charAt(i);
                        }
                        System.out.println("Reversed: " + rev);
                    }
                }
            """.trimIndent()),
        Lesson("jd-27", "java-dsa", "Palindrome String", "Check if a string is a palindrome.", LessonType.CHALLENGE, 27, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        String s = "madam";
                        String rev = new StringBuilder(s).reverse().toString();
                        if(s.equals(rev)) System.out.println("Palindrome");
                        else System.out.println("Not Palindrome");
                    }
                }
            """.trimIndent()),
        Lesson("jd-28", "java-dsa", "Count Vowels", "Iterate through a string to count vowels.", LessonType.CODE_PRACTICE, 28, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        String s = "education";
                        int count = 0;
                        for(char c : s.toLowerCase().toCharArray()) {
                            if("aeiou".indexOf(c) != -1) count++;
                        }
                        System.out.println("Vowels: " + count);
                    }
                }
            """.trimIndent()),
        Lesson("jd-29", "java-dsa", "Challenge: Bubble Sort", "Implement Bubble Sort algorithm.", LessonType.CHALLENGE, 29, false,
            codeExample = """
                import java.util.Arrays;
                public class Main {
                    public static void main(String[] args) {
                        int[] arr = {64, 34, 25, 12, 22, 11, 90};
                        for(int i=0; i<arr.length-1; i++) {
                            for(int j=0; j<arr.length-i-1; j++) {
                                if(arr[j] > arr[j+1]) {
                                    int temp = arr[j];
                                    arr[j] = arr[j+1];
                                    arr[j+1] = temp;
                                }
                            }
                        }
                        System.out.println("Sorted: " + Arrays.toString(arr));
                    }
                }
            """.trimIndent()),
        Lesson("jd-30", "java-dsa", "Part 1 Wrap-up", "Review of Algorithms and Logic.", LessonType.THEORY, 30, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("You have completed Part 1: Logic & Basic Algorithms!");
                        System.out.println("Next up: Data Structures.");
                    }
                }
            """.trimIndent()),

        // PART 2: Java Data Structures (31-60)

        // Core DSA Concepts
        Lesson("jd-31", "java-dsa", "What Is a Data Structure?", "Ways to store and organize data efficiently.", LessonType.THEORY, 31, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Linear: Arrays, Stacks, Queues, Linked Lists");
                        System.out.println("Non-Linear: Trees, Graphs, HashMaps");
                    }
                }
            """.trimIndent()),
        Lesson("jd-32", "java-dsa", "Time & Space Complexity", "Introduction to Big-O notation.", LessonType.THEORY, 32, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        // O(1) - Constant time
                        int first = 10;

                        // O(n) - Linear time
                        for(int i=0; i<10; i++) {
                            // ...
                        }
                        System.out.println("Understanding efficiency is key!");
                    }
                }
            """.trimIndent()),
        Lesson("jd-33", "java-dsa", "Arrays vs ArrayList", "Fixed size vs Dynamic size.", LessonType.THEORY, 33, false,
            codeExample = """
                import java.util.ArrayList;
                public class Main {
                    public static void main(String[] args) {
                        // Array: Fixed size
                        int[] arr = new int[5];

                        // ArrayList: Dynamic size
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(10);
                        list.add(20);
                        System.out.println("List: " + list);
                    }
                }
            """.trimIndent()),

        // Stack
        Lesson("jd-34", "java-dsa", "Stack Concept", "LIFO (Last In, First Out) principle.", LessonType.THEORY, 34, false,
            codeExample = """
                import java.util.Stack;
                public class Main {
                    public static void main(String[] args) {
                        Stack<String> stack = new Stack<>();
                        stack.push("Dish 1");
                        stack.push("Dish 2");
                        System.out.println("Top: " + stack.peek());
                        System.out.println("Popped: " + stack.pop());
                    }
                }
            """.trimIndent()),
        Lesson("jd-35", "java-dsa", "Stack Implementation", "Implement a Stack using an Array.", LessonType.CODE_PRACTICE, 35, false,
            codeExample = """
                class MyStack {
                    int arr[] = new int[5];
                    int top = -1;
                    void push(int x) { arr[++top] = x; }
                    int pop() { return arr[top--]; }
                    int peek() { return arr[top]; }
                }
                public class Main {
                    public static void main(String[] args) {
                        MyStack s = new MyStack();
                        s.push(10); s.push(20);
                        System.out.println("Peeking: " + s.peek());
                    }
                }
            """.trimIndent()),
        Lesson("jd-36", "java-dsa", "Challenge: Valid Parentheses", "Use a Stack to check if parentheses are balanced.", LessonType.CHALLENGE, 36, false,
            codeExample = """
                import java.util.Stack;
                public class Main {
                    public static void main(String[] args) {
                        String s = "{[]()}";
                        Stack<Character> stack = new Stack<>();
                        for(char c : s.toCharArray()) {
                            if(c=='(' || c=='{' || c=='[') stack.push(c);
                            else {
                                if(stack.isEmpty()) return; // Invalid
                                char top = stack.pop();
                                // check matching logic here...
                            }
                        }
                        System.out.println("Balanced: " + stack.isEmpty());
                    }
                }
            """.trimIndent()),

        // Queue
        Lesson("jd-37", "java-dsa", "Queue Concept", "FIFO (First In, First Out) principle.", LessonType.THEORY, 37, false,
            codeExample = """
                import java.util.LinkedList;
                import java.util.Queue;
                public class Main {
                    public static void main(String[] args) {
                        Queue<String> q = new LinkedList<>();
                        q.add("Person 1");
                        q.add("Person 2");
                        System.out.println("First in line: " + q.peek());
                        System.out.println("Served: " + q.poll());
                    }
                }
            """.trimIndent()),
        Lesson("jd-38", "java-dsa", "Queue Implementation", "Building a Queue from scratch.", LessonType.CODE_PRACTICE, 38, false,
            codeExample = """
                // Conceptual implementation
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Queue uses logical front and rear pointers.");
                    }
                }
            """.trimIndent()),
        Lesson("jd-39", "java-dsa", "Circular Queue", "Using modulus to wrap around array indices.", LessonType.THEORY, 39, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("nextIndex = (currentIndex + 1) % capacity");
                    }
                }
            """.trimIndent()),
        Lesson("jd-40", "java-dsa", "Deque", "Double Ended Queue.", LessonType.THEORY, 40, false,
            codeExample = """
                import java.util.ArrayDeque;
                public class Main {
                    public static void main(String[] args) {
                        ArrayDeque<Integer> dq = new ArrayDeque<>();
                        dq.addFirst(1);
                        dq.addLast(2);
                        System.out.println(dq);
                    }
                }
            """.trimIndent()),

        // Linked Lists
        Lesson("jd-41", "java-dsa", "Linked List Concept", "Nodes and pointers.", LessonType.THEORY, 41, false,
            codeExample = """
                // Node class structure
                class Node {
                    int data;
                    Node next;
                    Node(int d) { data=d; next=null; }
                }
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Each node points to the next node.");
                    }
                }
            """.trimIndent()),
        Lesson("jd-42", "java-dsa", "Singly Linked List", "Creating a simple chain of nodes.", LessonType.CODE_PRACTICE, 42, false,
            codeExample = """
                class Node {
                    int data;
                    Node next;
                    Node(int d) { data = d; }
                }
                public class Main {
                    public static void main(String[] args) {
                        Node head = new Node(10);
                        head.next = new Node(20);
                        System.out.println(head.data + " -> " + head.next.data);
                    }
                }
            """.trimIndent()),
        Lesson("jd-43", "java-dsa", "Insert & Delete Nodes", "Algorithms to modify linked lists.", LessonType.THEORY, 43, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        // To Insert at head:
                        // newNode.next = head;
                        // head = newNode;
                        System.out.println("Pointer manipulation is key.");
                    }
                }
            """.trimIndent()),
        Lesson("jd-44", "java-dsa", "Challenge: Find Middle Node", "Using slow and fast pointer technique.", LessonType.CHALLENGE, 44, false,
            codeExample = """
                // Two pointer approach
                // fast moves 2 steps, slow moves 1 step
                // when fast reaches end, slow is at middle
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Tortoise and Hare Algorithm");
                    }
                }
            """.trimIndent()),

        // Hashing & Searching
        Lesson("jd-45", "java-dsa", "HashMap", "Key-Value pairs for O(1) lookups.", LessonType.THEORY, 45, false,
            codeExample = """
                import java.util.HashMap;
                public class Main {
                    public static void main(String[] args) {
                        HashMap<String, Integer> map = new HashMap<>();
                        map.put("Alice", 90);
                        map.put("Bob", 85);
                        System.out.println("Alice's score: " + map.get("Alice"));
                    }
                }
            """.trimIndent()),
        Lesson("jd-46", "java-dsa", "HashSet", "Collection of unique items.", LessonType.CODE_PRACTICE, 46, false,
            codeExample = """
                import java.util.HashSet;
                public class Main {
                    public static void main(String[] args) {
                        HashSet<Integer> set = new HashSet<>();
                        set.add(1);
                        set.add(1); // Duplicate ignored
                        System.out.println("Size: " + set.size());
                    }
                }
            """.trimIndent()),
        Lesson("jd-47", "java-dsa", "Practice: Frequency Count", "Count occurrences of characters/numbers.", LessonType.CODE_PRACTICE, 47, false,
            codeExample = """
                import java.util.HashMap;
                public class Main {
                    public static void main(String[] args) {
                        int[] arr = {1, 2, 2, 3, 1, 1};
                        HashMap<Integer, Integer> map = new HashMap<>();
                        for(int num : arr) {
                            map.put(num, map.getOrDefault(num, 0) + 1);
                        }
                        System.out.println(map);
                    }
                }
            """.trimIndent()),
        Lesson("jd-48", "java-dsa", "Binary Search", "Search in a sorted array in O(log n).", LessonType.THEORY, 48, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        int[] arr = {1, 3, 5, 7, 9};
                        int target = 5;
                        int low=0, high=arr.length-1;
                        while(low <= high) {
                            int mid = low + (high-low)/2;
                            if(arr[mid] == target) {
                                System.out.println("Found at " + mid);
                                break;
                            } else if(arr[mid] < target) low = mid + 1;
                            else high = mid - 1;
                        }
                    }
                }
            """.trimIndent()),

        // Sorting
        Lesson("jd-49", "java-dsa", "Selection Sort", "Repeatedly find minimum and move to start.", LessonType.THEORY, 49, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Time Complexity: O(n^2)");
                    }
                }
            """.trimIndent()),
        Lesson("jd-50", "java-dsa", "Insertion Sort", "Build sorted array one item at a time.", LessonType.THEORY, 50, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        // Good for small or nearly sorted arrays
                        System.out.println("Like sorting playing cards in hand.");
                    }
                }
            """.trimIndent()),
        Lesson("jd-51", "java-dsa", "Merge Sort Concept", "Divide and Conquer (O(n log n)).", LessonType.THEORY, 51, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Recursively split array in half, sort, then merge.");
                    }
                }
            """.trimIndent()),
        Lesson("jd-52", "java-dsa", "Quick Sort Concept", "Partitioning based on pivot.", LessonType.THEORY, 52, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Fast efficient sorting algorithm.");
                    }
                }
            """.trimIndent()),

        // Trees & Graphs
        Lesson("jd-53", "java-dsa", "Tree Concept", "Hierarchical data structure.", LessonType.THEORY, 53, false,
            codeExample = """
                class Node {
                    int data;
                    Node left, right;
                    Node(int item) { data = item; left=right=null; }
                }
                public class Main {
                    public static void main(String[] args) {
                        Node root = new Node(1);
                        root.left = new Node(2);
                        root.right = new Node(3);
                        System.out.println("Root: " + root.data);
                    }
                }
            """.trimIndent()),
        Lesson("jd-54", "java-dsa", "Binary Tree Traversal", "Inorder, Preorder, Postorder.", LessonType.THEORY, 54, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        // Inorder: Left -> Root -> Right
                        // Preorder: Root -> Left -> Right
                        // Postorder: Left -> Right -> Root
                        System.out.println("Traversal defines visitation order.");
                    }
                }
            """.trimIndent()),
        Lesson("jd-55", "java-dsa", "BFS (Breadth-First)", "Level order traversal using Queue.", LessonType.THEORY, 55, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Explore neighbors first.");
                    }
                }
            """.trimIndent()),
        Lesson("jd-56", "java-dsa", "DFS (Depth-First)", "Deep exploration using Stack/Recursion.", LessonType.THEORY, 56, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Go deep before going wide.");
                    }
                }
            """.trimIndent()),

        // Recursion
        Lesson("jd-57", "java-dsa", "Recursion Basics", "A function calling itself.", LessonType.THEORY, 57, false,
            codeExample = """
                public class Main {
                    static void printFun(int test) {
                        if (test < 1) return;
                        System.out.print(test + " ");
                        printFun(test - 1);
                    }
                    public static void main(String[] args) {
                        printFun(3); // 3 2 1
                    }
                }
            """.trimIndent()),
        Lesson("jd-58", "java-dsa", "Recursion Problems", "Solving towers of hanoi, etc.", LessonType.CODE_PRACTICE, 58, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        // Base case is crucial to avoid StackOverflow
                        System.out.println("Always define a base case.");
                    }
                }
            """.trimIndent()),

        // Final
        Lesson("jd-59", "java-dsa", "Final DSA Challenge", "Solve a complex algorithmic problem.", LessonType.CHALLENGE, 59, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        // Challenge: Group Anagrams
                        // Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
                        // Output: [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]
                        System.out.println("Good luck!");
                    }
                }
            """.trimIndent()),
        Lesson("jd-60", "java-dsa", "Certificate: Java DSA Beginner", "Congratulations on completing the course!", LessonType.THEORY, 60, false,
            codeExample = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("🎉 CONGRATULATIONS! 🎉");
                        System.out.println("You have mastered the basics of Java DSA.");
                        System.out.println("Keep practicing on LeetCode!");
                    }
                }
            """.trimIndent())
    )
}
