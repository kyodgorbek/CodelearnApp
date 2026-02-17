// data/repository/CourseRepositoryImpl.kt
package com.yodgorbek.codelearnapp.data.repository

import com.yodgorbek.codelearnapp.domain.model.Course
import com.yodgorbek.codelearnapp.domain.model.CourseCategory
import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.model.LessonType
import com.yodgorbek.codelearnapp.domain.model.Quiz
import com.yodgorbek.codelearnapp.domain.repository.CourseRepository
import com.yodgorbek.codelearnapp.data.repository.JavaDpLeetcodeRepositoryImpl
import com.yodgorbek.codelearnapp.data.repository.LeetcodePatternsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

object CourseRepositoryImpl {

    fun getMockCourses() = listOf(
        Course(
            id = "python-basics",
            title = "Python Basics",
            description = "Learn Python fundamentals from scratch",
            icon = "🐍",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.PYTHON
        ),
        Course(
            id = "kotlin-android",
            title = "Kotlin for Android",
            description = "Build modern Android apps with Kotlin",
            icon = "📱",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.KOTLIN
        ),
        Course(
            id = "java-programming",
            title = "Java Programming",
            description = "Master Java from beginner to advanced",
            icon = "☕",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.JAVA
        ),
        Course(
            id = "web-dev",
            title = "Web Development",
            description = "HTML, CSS & JavaScript essentials",
            icon = "🌐",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.JAVASCRIPT
        ),
        Course(
            id = "sql-basics",
            title = "SQL Mastery",
            description = "Master database queries and design",
            icon = "💾",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.SQL
        ),
        Course(
            id = "data-science",
            title = "Data Science Foundations",
            description = "Explore data analysis, visualization, and machine learning",
            icon = "📊",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.DATA_SCIENCE
        ),
        Course(
            id = "python-kids",
            title = "Python for Kids",
            description = "Fun and easy Python coding for children ages 5-12",
            icon = "🎈",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.FOR_KIDS
        ),
        Course(
            id = "java-dsa",
            title = "Java DSA for Beginners",
            description = "Master Algorithms and Data Structures with Java. 60 comprehensive lessons.",
            icon = "☕",
            progress = 0.0f,
            totalLessons = 60,
            completedLessons = 0,
            category = CourseCategory.JAVA_DSA
        ),

        Course(
            id = "kotlin-dsa",
            title = "Kotlin DSA",
            description = "Complete Data Structures & Algorithms in Kotlin. 60 Lessons.",
            icon = "🧩",
            progress = 0.0f,
            totalLessons = 60,
            completedLessons = 0,
            category = CourseCategory.KOTLIN_DSA
        ),
        Course(
            id = "jetpack-compose",
            title = "Jetpack Compose",
            description = "From Zero to Professional. 30 Comprehensive Lessons.",
            icon = "🎨",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.JETPACK_COMPOSE
        ),
        Course(
            id = "java-dp-patterns",
            title = "Java DP Patterns",
            description = "Master Dynamic Programming patterns in Java. 30 Comprehensive Lessons.",
            icon = "📊",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.JAVA_DSA
        ),
        Course(
            id = "leetcode-patterns",
            title = "LeetCode Patterns",
            description = "Master common LeetCode patterns for interviews. 30 Comprehensive Lessons.",
            icon = "🎯",
            progress = 0.0f,
            totalLessons = 30,
            completedLessons = 0,
            category = CourseCategory.JAVA_DSA
        )
    )

    fun getMockLessons(): List<Lesson> {
        return getPythonLessons() +
            getKotlinLessons() +
            getJavaLessons() +
            getJavaScriptLessons() +
            getSqlLessons() +
            getDataScienceLessons() +
            getPythonKidsLessons() +
            getJavaDsaLessons() +
            getKotlinDsaLessons() +
            getComposeLessons() +
            getJavaDpLessons() +
            getLeetcodePatternLessons()
    }

    // PYTHON LESSONS (30)
    private fun getPythonLessons() = PythonRepositoryImpl.pythonLessons

    // KOTLIN LESSONS (30)
    private fun getKotlinLessons() = KotlinCourseRepositoryImpl.kotlinLessons

    // KOTLIN DSA LESSONS (60)
    private fun getKotlinDsaLessons() = KotlinDsaRepositoryImpl.kotlinDsaLessons

    // COMPOSE LESSONS (30)
    private fun getComposeLessons() = ComposeCourseRepositoryImpl.composeLessons

    // JAVA LESSONS (30)
    private fun getJavaLessons() = listOf(
        // Lesson 1-10
        Lesson(
            id = "java-1",
            courseId = "java-programming",
            language = "java",
            title = "Introduction to Java",
            content = "Java is a powerful, object-oriented programming language used for building enterprise applications, Android apps, and more.",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
                public class HelloWorld {
                    public static void main(String[] args) {
                        System.out.println("Hello, Java!");
                        System.out.println("Welcome to programming");
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-2",
            courseId = "java-programming",
            language = "java",
            title = "Variables and Data Types",
            content = "Java is statically typed, meaning you must declare variable types.",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
                public class Variables {
                    public static void main(String[] args) {
                        // Primitive types
                        int age = 25;
                        double height = 5.9;
                        boolean isStudent = true;
                        char grade = 'A';

                        // Reference type
                        String name = "Alice";

                        System.out.println(name + " is " + age + " years old");
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-3",
            courseId = "java-programming",
            language = "java",
            title = "Practice: Variables",
            content = "Create variables for a product's name, price, and quantity.",
            type = LessonType.CODE_PRACTICE,
            order = 3,
            isCompleted = false,
            codeExample = "String productName = \"Laptop\";\ndouble price = 999.99;\nint quantity = 5;\nSystem.out.println(productName + \": $\" + price + \" x\" + quantity);"
        ),
        Lesson(
            id = "java-4",
            courseId = "java-programming",
            language = "java",
            title = "Operators",
            content = "Java supports arithmetic, comparison, and logical operators.",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = """
                public class Operators {
                    public static void main(String[] args) {
                        // Arithmetic
                        int a = 10, b = 3;
                        System.out.println(a + b);   // 13
                        System.out.println(a - b);   // 7
                        System.out.println(a * b);   // 30
                        System.out.println(a / b);   // 3
                        System.out.println(a % b);   // 1

                        // Comparison
                        System.out.println(a > b);   // true
                        System.out.println(a == b);  // false

                        // Logical
                        boolean x = true, y = false;
                        System.out.println(x && y);  // false
                        System.out.println(x || y);  // true
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-5",
            courseId = "java-programming",
            language = "java",
            title = "Quiz: Java Basics",
            content = "Test your understanding of Java fundamentals",
            type = LessonType.QUIZ,
            order = 5,
            isCompleted = false,
            quiz = Quiz(
                id = "java-quiz-1",
                question = "Which method is the entry point of a Java program?",
                options = listOf("start()", "main()", "run()", "execute()"),
                correctAnswer = 1,
                explanation = "The main() method is the entry point of every Java application."
            )
        ),
        Lesson(
            id = "java-6",
            courseId = "java-programming",
            language = "java",
            title = "If-Else Statements",
            content = "Control flow with conditional statements.",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
                public class Conditionals {
                    public static void main(String[] args) {
                        int age = 18;

                        if (age >= 18) {
                            System.out.println("Adult");
                        } else if (age >= 13) {
                            System.out.println("Teenager");
                        } else {
                            System.out.println("Child");
                        }

                        // Ternary operator
                        String status = (age >= 18) ? "Adult" : "Minor";
                        System.out.println(status);
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-7",
            courseId = "java-programming",
            language = "java",
            title = "Switch Statement",
            content = "Handle multiple conditions efficiently.",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
                public class SwitchExample {
                    public static void main(String[] args) {
                        int day = 3;
                        String dayName;

                        switch (day) {
                            case 1:
                                dayName = "Monday";
                                break;
                            case 2:
                                dayName = "Tuesday";
                                break;
                            case 3:
                                dayName = "Wednesday";
                                break;
                            default:
                                dayName = "Other day";
                        }

                        System.out.println(dayName);
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-8",
            courseId = "java-programming",
            language = "java",
            title = "For Loop",
            content = "Iterate with for loops.",
            type = LessonType.THEORY,
            order = 8,
            isCompleted = false,
            codeExample = """
                public class ForLoop {
                    public static void main(String[] args) {
                        // Traditional for loop
                        for (int i = 0; i < 5; i++) {
                            System.out.println(i);
                        }

                        // Enhanced for loop (for-each)
                        String[] fruits = {"Apple", "Banana", "Cherry"};
                        for (String fruit : fruits) {
                            System.out.println(fruit);
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-9",
            courseId = "java-programming",
            language = "java",
            title = "While and Do-While Loops",
            content = "Loop while a condition is true.",
            type = LessonType.THEORY,
            order = 9,
            isCompleted = false,
            codeExample = """
                public class WhileLoop {
                    public static void main(String[] args) {
                        // While loop
                        int count = 0;
                        while (count < 5) {
                            System.out.println(count);
                            count++;
                        }

                        // Do-while loop (executes at least once)
                        int num = 0;
                        do {
                            System.out.println(num);
                            num++;
                        } while (num < 3);
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-10",
            courseId = "java-programming",
            language = "java",
            title = "Practice: Loops",
            content = "Print numbers from 1 to 10 using a for loop.",
            type = LessonType.CODE_PRACTICE,
            order = 10,
            isCompleted = false,
            codeExample = "for (int i = 1; i <= 10; i++) {\n    System.out.println(i);\n}"
        ),
        // Lesson 11-20
        Lesson(
            id = "java-11",
            courseId = "java-programming",
            language = "java",
            title = "Quiz: Control Flow",
            content = "Test your knowledge of loops and conditionals",
            type = LessonType.QUIZ,
            order = 11,
            isCompleted = false,
            quiz = Quiz(
                id = "java-quiz-2",
                question = "Which loop always executes at least once?",
                options = listOf("for", "while", "do-while", "foreach"),
                correctAnswer = 2,
                explanation = "The do-while loop executes the body before checking the condition."
            )
        ),
        Lesson(
            id = "java-12",
            courseId = "java-programming",
            language = "java",
            title = "Methods",
            content = "Methods are reusable blocks of code.",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = """
                public class Methods {
                    // Method with return value
                    public static int add(int a, int b) {
                        return a + b;
                    }

                    // Method without return value
                    public static void greet(String name) {
                        System.out.println("Hello, " + name + "!");
                    }

                    public static void main(String[] args) {
                        int sum = add(5, 3);
                        System.out.println("Sum: " + sum);

                        greet("Alice");
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-13",
            courseId = "java-programming",
            language = "java",
            title = "Method Overloading",
            content = "Multiple methods with the same name but different parameters.",
            type = LessonType.THEORY,
            order = 13,
            isCompleted = false,
            codeExample = """
                public class Overloading {
                    public static int add(int a, int b) {
                        return a + b;
                    }

                    public static double add(double a, double b) {
                        return a + b;
                    }

                    public static int add(int a, int b, int c) {
                        return a + b + c;
                    }

                    public static void main(String[] args) {
                        System.out.println(add(5, 3));        // 8
                        System.out.println(add(5.5, 3.2));    // 8.7
                        System.out.println(add(1, 2, 3));     // 6
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-14",
            courseId = "java-programming",
            language = "java",
            title = "Arrays",
            content = "Store multiple values in a single variable.",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
                public class Arrays {
                    public static void main(String[] args) {
                        // Declare and initialize
                        int[] numbers = {1, 2, 3, 4, 5};
                        String[] fruits = new String[3];
                        fruits[0] = "Apple";
                        fruits[1] = "Banana";
                        fruits[2] = "Cherry";

                        // Access elements
                        System.out.println(numbers[0]);  // 1
                        System.out.println(fruits[1]);   // "Banana"

                        // Array length
                        System.out.println(numbers.length);  // 5
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-15",
            courseId = "java-programming",
            language = "java",
            title = "Practice: Arrays",
            content = "Create an array of 5 numbers and print each element.",
            type = LessonType.CODE_PRACTICE,
            order = 15,
            isCompleted = false,
            codeExample = "int[] numbers = {1, 2, 3, 4, 5};\nfor (int num : numbers) {\n    System.out.println(num);\n}"
        ),
        Lesson(
            id = "java-16",
            courseId = "java-programming",
            language = "java",
            title = "Classes and Objects",
            content = "Object-oriented programming with classes.",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = """
                class Person {
                    String name;
                    int age;

                    // Constructor
                    public Person(String name, int age) {
                        this.name = name;
                        this.age = age;
                    }

                    // Method
                    public void introduce() {
                        System.out.println("I'm " + name + ", " + age + " years old");
                    }
                }

                public class Main {
                    public static void main(String[] args) {
                        Person person = new Person("Alice", 25);
                        person.introduce();

                        System.out.println("Name: " + person.name);
                        person.age = 26;
                        System.out.println("New age: " + person.age);
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-17",
            courseId = "java-programming",
            language = "java",
            title = "Constructors",
            content = "Special methods that initialize new objects.",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = """
                class Car {
                    String brand;
                    String model;
                    int year;

                    // Default constructor
                    public Car() {
                        brand = "Unknown";
                        model = "Unknown";
                        year = 0;
                    }

                    // Parameterized constructor
                    public Car(String brand, String model, int year) {
                        this.brand = brand;
                        this.model = model;
                        this.year = year;
                    }

                    // Copy constructor
                    public Car(Car other) {
                        this.brand = other.brand;
                        this.model = other.model;
                        this.year = other.year;
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-18",
            courseId = "java-programming",
            language = "java",
            title = "Quiz: OOP Basics",
            content = "Test your understanding of classes and objects",
            type = LessonType.QUIZ,
            order = 18,
            isCompleted = false,
            quiz = Quiz(
                id = "java-quiz-3",
                question = "Which keyword refers to the current object?",
                options = listOf("super", "this", "self", "current"),
                correctAnswer = 1,
                explanation = "The 'this' keyword refers to the current object instance."
            )
        ),
        Lesson(
            id = "java-19",
            courseId = "java-programming",
            language = "java",
            title = "Inheritance",
            content = "Create new classes based on existing ones.",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = """
                // Parent class
                class Animal {
                    String name;

                    public Animal(String name) {
                        this.name = name;
                    }

                    public void sound() {
                        System.out.println("Some sound");
                    }
                }

                // Child class
                class Dog extends Animal {
                    public Dog(String name) {
                        super(name);  // Call parent constructor
                    }

                    @Override
                    public void sound() {
                        System.out.println(name + " says Woof!");
                    }

                    // New method
                    public void wagTail() {
                        System.out.println(name + " is wagging tail");
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-20",
            courseId = "java-programming",
            language = "java",
            title = "Abstract Classes",
            content = "Classes that cannot be instantiated directly.",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = """
                abstract class Shape {
                    String color;

                    public Shape(String color) {
                        this.color = color;
                    }

                    // Abstract method (no implementation)
                    abstract double area();

                    // Concrete method
                    public void display() {
                        System.out.println("Color: " + color);
                        System.out.println("Area: " + area());
                    }
                }

                class Circle extends Shape {
                    double radius;

                    public Circle(String color, double radius) {
                        super(color);
                        this.radius = radius;
                    }

                    @Override
                    double area() {
                        return Math.PI * radius * radius;
                    }
                }
            """.trimIndent()
        ),
        // Lesson 21-30
        Lesson(
            id = "java-21",
            courseId = "java-programming",
            language = "java",
            title = "Practice: Inheritance",
            content = "Create a Rectangle class that extends Shape.",
            type = LessonType.CODE_PRACTICE,
            order = 21,
            isCompleted = false,
            codeExample = "class Rectangle extends Shape {\n    double width, height;\n    \n    public Rectangle(String color, double width, double height) {\n        super(color);\n        this.width = width;\n        this.height = height;\n    }\n    \n    @Override\n    double area() {\n        return width * height;\n    }\n}"
        ),
        Lesson(
            id = "java-22",
            courseId = "java-programming",
            language = "java",
            title = "Interfaces",
            content = "Define contracts that classes must implement.",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = """
                interface Drawable {
                    void draw();
                    void resize(double factor);
                }

                interface Colorable {
                    void setColor(String color);
                }

                class Circle implements Drawable, Colorable {
                    String color;

                    @Override
                    public void draw() {
                        System.out.println("Drawing circle");
                    }

                    @Override
                    public void resize(double factor) {
                        System.out.println("Resizing by " + factor);
                    }

                    @Override
                    public void setColor(String color) {
                        this.color = color;
                        System.out.println("Color set to " + color);
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-23",
            courseId = "java-programming",
            language = "java",
            title = "Quiz: Advanced OOP",
            content = "Test your understanding of inheritance and interfaces",
            type = LessonType.QUIZ,
            order = 23,
            isCompleted = false,
            quiz = Quiz(
                id = "java-quiz-4",
                question = "Which keyword is used to implement an interface?",
                options = listOf("extends", "implements", "inherits", "uses"),
                correctAnswer = 1,
                explanation = "The 'implements' keyword is used to implement interfaces."
            )
        ),
        Lesson(
            id = "java-24",
            courseId = "java-programming",
            language = "java",
            title = "Exception Handling",
            content = "Handle runtime errors gracefully.",
            type = LessonType.THEORY,
            order = 24,
            isCompleted = false,
            codeExample = """
                public class Exceptions {
                    public static void main(String[] args) {
                        try {
                            int result = 10 / 0;
                            System.out.println("Result: " + result);
                        } catch (ArithmeticException e) {
                            System.out.println("Cannot divide by zero!");
                        } finally {
                            System.out.println("This always executes");
                        }

                        // Multiple catch blocks
                        try {
                            int[] numbers = {1, 2, 3};
                            System.out.println(numbers[5]);

                            String str = null;
                            System.out.println(str.length());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Array index out of bounds");
                        } catch (NullPointerException e) {
                            System.out.println("Null pointer exception");
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-25",
            courseId = "java-programming",
            language = "java",
            title = "Collections Framework: ArrayList",
            content = "Dynamic arrays that can grow and shrink.",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
                import java.util.ArrayList;
                import java.util.Collections;

                public class ArrayLists {
                    public static void main(String[] args) {
                        ArrayList<String> fruits = new ArrayList<>();

                        // Adding elements
                        fruits.add("Apple");
                        fruits.add("Banana");
                        fruits.add("Cherry");

                        // Accessing elements
                        System.out.println(fruits.get(0));  // "Apple"

                        // Removing elements
                        fruits.remove(1);  // Removes "Banana"

                        // Size
                        System.out.println("Size: " + fruits.size());

                        // Sorting
                        Collections.sort(fruits);

                        // Iterating
                        for (String fruit : fruits) {
                            System.out.println(fruit);
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-26",
            courseId = "java-programming",
            language = "java",
            title = "Collections: HashMap",
            content = "Store key-value pairs for efficient lookups.",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
                import java.util.HashMap;
                import java.util.Map;

                public class HashMaps {
                    public static void main(String[] args) {
                        HashMap<String, Integer> scores = new HashMap<>();

                        // Adding entries
                        scores.put("Alice", 95);
                        scores.put("Bob", 87);
                        scores.put("Charlie", 92);

                        // Accessing values
                        System.out.println("Alice's score: " + scores.get("Alice"));

                        // Checking existence
                        System.out.println("Contains Bob? " + scores.containsKey("Bob"));

                        // Removing
                        scores.remove("Charlie");

                        // Iterating
                        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-27",
            courseId = "java-programming",
            language = "java",
            title = "Practice: Collections",
            content = "Create a HashMap to store phone numbers.",
            type = LessonType.CODE_PRACTICE,
            order = 27,
            isCompleted = false,
            codeExample = "HashMap<String, String> phoneBook = new HashMap<>();\nphoneBook.put(\"Alice\", \"123-456-7890\");\nphoneBook.put(\"Bob\", \"987-654-3210\");\nSystem.out.println(phoneBook.get(\"Alice\"));"
        ),
        Lesson(
            id = "java-28",
            courseId = "java-programming",
            language = "java",
            title = "File I/O",
            content = "Read from and write to files.",
            type = LessonType.THEORY,
            order = 28,
            isCompleted = false,
            codeExample = """
                import java.io.*;
                import java.nio.file.*;

                public class FileIO {
                    public static void main(String[] args) {
                        // Writing to a file
                        try {
                            FileWriter writer = new FileWriter("output.txt");
                            writer.write("Hello, Java!");
                            writer.close();
                            System.out.println("File written successfully");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Reading from a file
                        try {
                            String content = Files.readString(Paths.get("output.txt"));
                            System.out.println("File content: " + content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "java-29",
            courseId = "java-programming",
            language = "java",
            title = "Quiz: Advanced Java",
            content = "Test your understanding of collections and file I/O",
            type = LessonType.QUIZ,
            order = 29,
            isCompleted = false,
            quiz = Quiz(
                id = "java-quiz-5",
                question = "Which collection allows duplicate elements and maintains insertion order?",
                options = listOf("HashSet", "TreeSet", "ArrayList", "HashMap"),
                correctAnswer = 2,
                explanation = "ArrayList allows duplicates and maintains insertion order."
            )
        ),
        Lesson(
            id = "java-30",
            courseId = "java-programming",
            language = "java",
            title = "Final Challenge: Bank System",
            content = "Create a simple banking system with accounts and transactions.",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            codeExample = """
                class BankAccount {
                    private String accountNumber;
                    private double balance;

                    public BankAccount(String accountNumber, double initialBalance) {
                        this.accountNumber = accountNumber;
                        this.balance = initialBalance;
                    }

                    public void deposit(double amount) {
                        if (amount > 0) {
                            balance += amount;
                            System.out.println("Deposited: $" + amount);
                        }
                    }

                    public void withdraw(double amount) {
                        if (amount > 0 && amount <= balance) {
                            balance -= amount;
                            System.out.println("Withdrew: $" + amount);
                        } else {
                            System.out.println("Insufficient funds");
                        }
                    }

                    public double getBalance() {
                        return balance;
                    }
                }

                public class BankSystem {
                    public static void main(String[] args) {
                        BankAccount account = new BankAccount("123456", 1000);
                        account.deposit(500);
                        account.withdraw(200);
                        System.out.println("Balance: $" + account.getBalance());
                    }
                }
            """.trimIndent()
        )
    )

    // JAVASCRIPT LESSONS (30)
    private fun getJavaScriptLessons() = listOf(
        // Lesson 1-10
        Lesson(
            id = "js-1",
            courseId = "web-dev",
            language = "javascript",
            title = "Introduction to JavaScript",
            content = "JavaScript is a versatile programming language that makes web pages interactive.",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
                // Your first JavaScript code
                console.log("Hello, JavaScript!");
                alert("Welcome to web development!");

                // JavaScript can be embedded in HTML
                // <script>console.log("Running in browser")</script>
            """.trimIndent()
        ),
        Lesson(
            id = "js-2",
            courseId = "web-dev",
            language = "javascript",
            title = "Variables and Data Types",
            content = "JavaScript has dynamic typing - variables can hold any type of data.",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
                // Variables (let, const, var)
                let name = "Alice";  // Can be reassigned
                const age = 25;      // Cannot be reassigned
                var city = "NYC";    // Old way

                // Data types
                let isStudent = true;        // Boolean
                let score = 95.5;            // Number
                let colors = ["red", "blue"]; // Array
                let person = {name: "Bob"};   // Object
                let nothing = null;          // Null
                let notDefined;               // Undefined
            """.trimIndent()
        ),
        Lesson(
            id = "js-3",
            courseId = "web-dev",
            language = "javascript",
            title = "Practice: Variables",
            content = "Create variables for a shopping cart item.",
            type = LessonType.CODE_PRACTICE,
            order = 3,
            isCompleted = false,
            codeExample = "const itemName = \"Laptop\";\n" +
                "let price = 999.99;\n" +
                "let quantity = 2;\n" +
                "console.log(`${'$'}{itemName}: ${'$'}${'$'}{price} x ${'$'}{quantity}`);"
        ),
        Lesson(
            id = "js-4",
            courseId = "web-dev",
            language = "javascript",
            title = "Operators",
            content = "JavaScript has arithmetic, comparison, and logical operators.",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = """
                // Arithmetic
                let x = 10, y = 3;
                console.log(x + y);  // 13
                console.log(x - y);  // 7
                console.log(x * y);  // 30
                console.log(x / y);  // 3.333...
                console.log(x % y);  // 1

                // Comparison
                console.log(x > y);   // true
                console.log(x === y); // false (strict equality)
                console.log(x == "10"); // true (loose equality)

                // Logical
                console.log(true && false); // false
                console.log(true || false); // true
            """.trimIndent()
        ),
        Lesson(
            id = "js-5",
            courseId = "web-dev",
            language = "javascript",
            title = "Quiz: JavaScript Basics",
            content = "Test your understanding of JavaScript fundamentals",
            type = LessonType.QUIZ,
            order = 5,
            isCompleted = false,
            quiz = Quiz(
                id = "js-quiz-1",
                question = "Which keyword creates a constant variable in JavaScript?",
                options = listOf("let", "const", "var", "fixed"),
                correctAnswer = 1,
                explanation = "The 'const' keyword creates a constant that cannot be reassigned."
            )
        ),
        Lesson(
            id = "js-6",
            courseId = "web-dev",
            language = "javascript",
            title = "Conditional Statements",
            content = "Make decisions in your code with if/else statements.",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
                let age = 18;

                if (age >= 18) {
                    console.log("Adult");
                } else if (age >= 13) {
                    console.log("Teenager");
                } else {
                    console.log("Child");
                }

                // Ternary operator
                let status = age >= 18 ? "Adult" : "Minor";
                console.log(status);

                // Switch statement
                let day = 3;
                switch(day) {
                    case 1: console.log("Monday"); break;
                    case 2: console.log("Tuesday"); break;
                    case 3: console.log("Wednesday"); break;
                    default: console.log("Other day");
                }
            """.trimIndent()
        ),
        Lesson(
            id = "js-7",
            courseId = "web-dev",
            language = "javascript",
            title = "Loops",
            content = "Repeat code with different types of loops.",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
                // For loop
                for (let i = 0; i < 5; i++) {
                    console.log(i);
                }

                // While loop
                let count = 0;
                while (count < 3) {
                    console.log(count);
                    count++;
                }

                // Do-while loop
                let num = 0;
                do {
                    console.log(num);
                    num++;
                } while (num < 3);

                // For-of loop (arrays)
                let colors = ["red", "green", "blue"];
                for (let color of colors) {
                    console.log(color);
                }
            """.trimIndent()
        ),
        Lesson(
            id = "js-8",
            courseId = "web-dev",
            language = "javascript",
            title = "Practice: Loops",
            content = "Print numbers 1 to 5 using a for loop.",
            type = LessonType.CODE_PRACTICE,
            order = 8,
            isCompleted = false,
            codeExample = "for (let i = 1; i <= 5; i++) {\n    console.log(i);\n}"
        ),
        Lesson(
            id = "js-9",
            courseId = "web-dev",
            language = "javascript",
            title = "Functions",
            content = "Create reusable blocks of code with functions.",
            type = LessonType.THEORY,
            order = 9,
            isCompleted = false,
            codeExample = """
        // Function declaration
        function greet(name) {
            return `Hello, ${'$'}{name}!`;
        }

        // Function expression
        const add = function(a, b) {
            return a + b;
        };

        // Arrow function (ES6)
        const multiply = (a, b) => a * b;

        // Default parameters
        function sayHello(name = "Guest") {
            console.log(`Hello, ${'$'}{name}`);
        }

        console.log(greet("Alice"));  // "Hello, Alice!"
        console.log(add(5, 3));       // 8
        console.log(multiply(4, 2));  // 8
        sayHello();                   // "Hello, Guest"
        sayHello("Bob");              // "Hello, Bob"
    """.trimIndent()
        ),
        Lesson(
            id = "js-10",
            courseId = "web-dev",
            language = "javascript",
            title = "Quiz: Functions",
            content = "Test your understanding of JavaScript functions",
            type = LessonType.QUIZ,
            order = 10,
            isCompleted = false,
            quiz = Quiz(
                id = "js-quiz-2",
                question = "Which syntax creates an arrow function?",
                options = listOf("function() {}", "() => {}", "() -> {}", "arrow() {}"),
                correctAnswer = 1,
                explanation = "Arrow functions use the => syntax: (params) => { expression }."
            )
        ),
        // Lesson 11-20
        Lesson(
            id = "js-11",
            courseId = "web-dev",
            language = "javascript",
            title = "Arrays",
            content = "Store and manipulate collections of data.",
            type = LessonType.THEORY,
            order = 11,
            isCompleted = false,
            codeExample = """
                // Creating arrays
                let fruits = ["Apple", "Banana", "Cherry"];
                let numbers = [1, 2, 3, 4, 5];

                // Accessing elements
                console.log(fruits[0]);      // "Apple"
                console.log(fruits.length);  // 3

                // Adding/removing elements
                fruits.push("Orange");       // Add to end
                fruits.pop();                // Remove from end
                fruits.unshift("Mango");     // Add to beginning
                fruits.shift();              // Remove from beginning

                // Array methods
                let squared = numbers.map(n => n * n);
                let evens = numbers.filter(n => n % 2 === 0);
                let sum = numbers.reduce((total, n) => total + n, 0);
            """.trimIndent()
        ),
        Lesson(
            id = "js-12",
            courseId = "web-dev",
            language = "javascript",
            title = "Objects",
            content = "Store key-value pairs for complex data.",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = """
        // Object literal
        let person = {
            name: "Alice",
            age: 25,
            city: "New York",

            // Method
            greet() {
                return `Hello, I'm ${'$'}{this.name}`;
            }
        };

        // Accessing properties
        console.log(person.name);       // "Alice"
        console.log(person["age"]);     // 25
        console.log(person.greet());    // "Hello, I'm Alice"

        // Adding properties
        person.job = "Developer";

        // Object methods
        let keys = Object.keys(person);    // ["name", "age", ...]
        let values = Object.values(person);
        let entries = Object.entries(person);
    """.trimIndent()
        ),
        Lesson(
            id = "js-13",
            courseId = "web-dev",
            language = "javascript",
            title = "Practice: Objects",
            content = "Create an object for a book with title and author.",
            type = LessonType.CODE_PRACTICE,
            order = 13,
            isCompleted = false,
            codeExample = """
        const book = {
            title: "JavaScript Guide",
            author: "John Doe",
            year: 2023,

            getInfo() {
                return `${'$'}{this.title} by ${'$'}{this.author} (${ '$' }{this.year})`;
            }
        };

        console.log(book.getInfo());
    """.trimIndent()
        ),

        Lesson(
            id = "js-14",
            courseId = "web-dev",
            language = "javascript",
            title = "DOM Manipulation",
            content = "Interact with HTML elements using JavaScript.",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
                // HTML: <div id="app">Hello</div>
                // HTML: <button id="btn">Click me</button>

                // Get elements
                let app = document.getElementById("app");
                let buttons = document.querySelectorAll("button");

                // Modify content
                app.textContent = "Hello, World!";
                app.innerHTML = "<strong>Bold text</strong>";

                // Modify styles
                app.style.color = "blue";
                app.style.fontSize = "20px";

                // Add event listener
                document.getElementById("btn").addEventListener("click", function() {
                    alert("Button clicked!");
                });
            """.trimIndent()
        ),
        Lesson(
            id = "js-15",
            courseId = "web-dev",
            language = "javascript",
            title = "Quiz: Arrays and Objects",
            content = "Test your knowledge of data structures",
            type = LessonType.QUIZ,
            order = 15,
            isCompleted = false,
            quiz = Quiz(
                id = "js-quiz-3",
                question = "Which method adds elements to the end of an array?",
                options = listOf("push()", "pop()", "shift()", "unshift()"),
                correctAnswer = 0,
                explanation = "push() adds elements to the end, while unshift() adds to the beginning."
            )
        ),
        Lesson(
            id = "js-16",
            courseId = "web-dev",
            language = "javascript",
            title = "Events",
            content = "Handle user interactions like clicks and key presses.",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = """
        // Click event
        document.getElementById("myButton").addEventListener("click", function(event) {
            console.log("Button clicked!");
            console.log(event.target);  // The clicked element
        });

        // Form submission
        document.getElementById("myForm").addEventListener("submit", function(event) {
            event.preventDefault();  // Prevent page reload
            let input = document.getElementById("name").value;
            console.log("Submitted: " + input);
        });

        // Keyboard events
        document.addEventListener("keydown", function(event) {
            console.log("Key pressed: " + event.key);
            if (event.key === "Enter") {
                console.log("Enter key pressed!");
            }
        });

        // Mouse events
        document.addEventListener("mousemove", function(event) {
            console.log(`Mouse at: ${'$'}{event.clientX}, ${'$'}{event.clientY}`);
        });
    """.trimIndent()
        ),

        Lesson(
            id = "js-17",
            courseId = "web-dev",
            language = "javascript",
            title = "Async Programming: Callbacks",
            content = "Handle asynchronous operations with callback functions.",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = """
                // Simulating async operations
                function fetchData(callback) {
                    setTimeout(() => {
                        const data = { name: "Alice", age: 25 };
                        callback(data);
                    }, 1000);
                }

                // Using callback
                fetchData(function(data) {
                    console.log("Data received:", data);
                });

                // Callback hell (nested callbacks)
                function step1(callback) {
                    setTimeout(() => {
                        console.log("Step 1 complete");
                        callback();
                    }, 1000);
                }

                function step2(callback) {
                    setTimeout(() => {
                        console.log("Step 2 complete");
                        callback();
                    }, 1000);
                }

                step1(function() {
                    step2(function() {
                        console.log("All steps complete");
                    });
                });
            """.trimIndent()
        ),
        Lesson(
            id = "js-18",
            courseId = "web-dev",
            language = "javascript",
            title = "Promises",
            content = "Handle asynchronous operations more cleanly.",
            type = LessonType.THEORY,
            order = 18,
            isCompleted = false,
            codeExample = """
                // Creating a promise
                function fetchUser() {
                    return new Promise((resolve, reject) => {
                        setTimeout(() => {
                            const success = Math.random() > 0.5;
                            if (success) {
                                resolve({ name: "Alice", age: 25 });
                            } else {
                                reject("Failed to fetch user");
                            }
                        }, 1000);
                    });
                }

                // Using promises
                fetchUser()
                    .then(user => {
                        console.log("User:", user);
                        return user.name;
                    })
                    .then(name => {
                        console.log("Name:", name);
                    })
                    .catch(error => {
                        console.error("Error:", error);
                    })
                    .finally(() => {
                        console.log("Operation complete");
                    });
            """.trimIndent()
        ),
        Lesson(
            id = "js-19",
            courseId = "web-dev",
            language = "javascript",
            title = "Practice: Promises",
            content = "Create a promise that simulates loading data.",
            type = LessonType.CODE_PRACTICE,
            order = 19,
            isCompleted = false,
            codeExample = "function loadData() {\n    return new Promise((resolve) => {\n        setTimeout(() => {\n            resolve({ data: \"Sample data loaded\" });\n        }, 2000);\n    });\n}\n\nloadData().then(result => {\n    console.log(result.data);\n});"
        ),
        Lesson(
            id = "js-20",
            courseId = "web-dev",
            language = "javascript",
            title = "Async/Await",
            content = "Write asynchronous code that looks synchronous.",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = """
                // Using async/await
                async function getUserData() {
                    try {
                        console.log("Fetching user...");

                        // Simulate API call
                        let user = await new Promise(resolve => {
                            setTimeout(() => {
                                resolve({ name: "Alice", id: 1 });
                            }, 1000);
                        });

                        console.log("User fetched:", user);

                        // Simulate another API call
                        let posts = await new Promise(resolve => {
                            setTimeout(() => {
                                resolve(["Post 1", "Post 2"]);
                            }, 500);
                        });

                        console.log("Posts:", posts);
                        return { user, posts };

                    } catch (error) {
                        console.error("Error:", error);
                    }
                }

                // Calling async function
                getUserData().then(result => {
                    console.log("Final result:", result);
                });
            """.trimIndent()
        ),
        // Lesson 21-30
        Lesson(
            id = "js-21",
            courseId = "web-dev",
            language = "javascript",
            title = "Quiz: Async Programming",
            content = "Test your understanding of asynchronous JavaScript",
            type = LessonType.QUIZ,
            order = 21,
            isCompleted = false,
            quiz = Quiz(
                id = "js-quiz-4",
                question = "What keyword is used to declare an async function?",
                options = listOf("async", "await", "promise", "async/await"),
                correctAnswer = 0,
                explanation = "The 'async' keyword declares an asynchronous function."
            )
        ),
        Lesson(
            id = "js-22",
            courseId = "web-dev",
            language = "javascript",
            title = "Classes",
            content = "Object-oriented programming with ES6 classes.",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = """
        // Class definition
        class Person {
            constructor(name, age) {
                this.name = name;
                this.age = age;
            }

            // Method
            greet() {
                return `Hello, I'm ${'$'}{this.name}`;
            }

            // Getter
            get isAdult() {
                return this.age >= 18;
            }

            // Setter
            set newAge(age) {
                if (age > 0) {
                    this.age = age;
                }
            }
        }

        // Inheritance
        class Student extends Person {
            constructor(name, age, grade) {
                super(name, age);  // Call parent constructor
                this.grade = grade;
            }

            study() {
                return `${'$'}{this.name} is studying`;
            }
        }

        // Using classes
        let alice = new Person("Alice", 25);
        console.log(alice.greet());
        console.log(alice.isAdult);

        let bob = new Student("Bob", 20, "A");
        console.log(bob.study());
    """.trimIndent()
        ),

        Lesson(
            id = "js-23",
            courseId = "web-dev",
            language = "javascript",
            title = "Modules",
            content = "Organize code into reusable modules.",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = """
                // math.js (module file)
                export const PI = 3.14159;

                export function add(a, b) {
                    return a + b;
                }

                export function multiply(a, b) {
                    return a * b;
                }

                // Default export
                export default class Calculator {
                    static square(x) {
                        return x * x;
                    }
                }

                // main.js (using the module)
                /*
                import Calculator, { PI, add } from './math.js';

                console.log(PI);  // 3.14159
                console.log(add(5, 3));  // 8
                console.log(Calculator.square(4));  // 16
                */
            """.trimIndent()
        ),
        // <-- Closing parenthesis added here

        Lesson(
            id = "js-25",
            courseId = "web-dev",
            language = "javascript",
            title = "Spread and Rest Operators",
            content = "Work with arrays and function parameters more flexibly.",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
                // Spread operator (...)
                let numbers = [1, 2, 3];
                let moreNumbers = [...numbers, 4, 5];  // [1, 2, 3, 4, 5]

                let obj1 = { x: 1, y: 2 };
                let obj2 = { ...obj1, z: 3 };  // { x: 1, y: 2, z: 3 }

                // Function calls
                let nums = [5, 10, 15];
                console.log(Math.max(...nums));  // 15

                // Rest operator (...)
                function sum(...numbers) {
                    return numbers.reduce((total, n) => total + n, 0);
                }
                console.log(sum(1, 2, 3, 4));  // 10

                // Array destructuring with rest
                let [first, second, ...rest] = [1, 2, 3, 4, 5];
                console.log(first);  // 1
                console.log(rest);   // [3, 4, 5]
            """.trimIndent()
        ),
        Lesson(
            id = "js-26",
            courseId = "web-dev",
            language = "javascript",
            title = "Practice: Modern JS Features",
            content = "Use destructuring and spread operators.",
            type = LessonType.CODE_PRACTICE,
            order = 26,
            isCompleted = false,
            codeExample = "const user = {\n    id: 1,\n    name: \"Alice\",\n    email: \"alice@yodgorbek.com\",\n    age: 25\n};\n\n// Destructure user object\nconst { name, email, ...otherInfo } = user;\nconsole.log(name, email, otherInfo);"
        ),
        Lesson(
            id = "js-27",
            courseId = "web-dev",
            language = "javascript",
            title = "Local Storage",
            content = "Store data in the browser persistently.",
            type = LessonType.THEORY,
            order = 27,
            isCompleted = false,
            codeExample = """
                // Store data
                localStorage.setItem("username", "Alice");
                localStorage.setItem("score", "100");

                // Retrieve data
                let username = localStorage.getItem("username");
                let score = localStorage.getItem("score");
                console.log(username, score);

                // Remove data
                localStorage.removeItem("score");

                // Clear all data
                // localStorage.clear();

                // Storing objects (need to stringify)
                let user = { name: "Bob", age: 30 };
                localStorage.setItem("user", JSON.stringify(user));

                // Retrieving objects
                let storedUser = JSON.parse(localStorage.getItem("user"));
                console.log(storedUser.name);  // "Bob"

                // Session storage (cleared when browser closes)
                sessionStorage.setItem("token", "abc123");
                console.log(sessionStorage.getItem("token"));
            """.trimIndent()
        ),
        Lesson(
            id = "js-28",
            courseId = "web-dev",
            language = "javascript",
            title = "Fetch API",
            content = "Make HTTP requests to APIs.",
            type = LessonType.THEORY,
            order = 28,
            isCompleted = false,
            codeExample = """
                // Basic GET request
                fetch("https://jsonplaceholder.typicode.com/users")
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Network response was not ok");
                        }
                        return response.json();  // Parse JSON
                    })
                    .then(data => {
                        console.log("Users:", data);
                    })
                    .catch(error => {
                        console.error("Error:", error);
                    });

                // POST request
                fetch("https://jsonplaceholder.typicode.com/posts", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        title: "New Post",
                        body: "Post content",
                        userId: 1
                    })
                })
                .then(response => response.json())
                .then(data => console.log("Created:", data));

                // Using async/await
                async function fetchData() {
                    try {
                        let response = await fetch("https://jsonplaceholder.typicode.com/posts/1");
                        let data = await response.json();
                        console.log("Post:", data);
                    } catch (error) {
                        console.error("Error:", error);
                    }
                }
                fetchData();
            """.trimIndent()
        ),
        Lesson(
            id = "js-29",
            courseId = "web-dev",
            language = "javascript",
            title = "Quiz: Advanced JavaScript",
            content = "Test your knowledge of modern JavaScript features",
            type = LessonType.QUIZ,
            order = 29,
            isCompleted = false,
            quiz = Quiz(
                id = "js-quiz-5",
                question = "Which method is used to make HTTP requests in modern JavaScript?",
                options = listOf("XMLHttpRequest", "fetch()", "ajax()", "http.get()"),
                correctAnswer = 1,
                explanation = "The fetch() API is the modern way to make HTTP requests in JavaScript."
            )
        ),
        Lesson(
            id = "js-30",
            courseId = "web-dev",
            language = "javascript",
            title = "Final Challenge: Weather App",
            content = "Build a weather application using JavaScript and APIs.",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            codeExample = """
    // Weather app structure
    /*
    class WeatherApp {
        constructor(apiKey) {
            this.apiKey = apiKey;
            this.baseUrl = "https://api.openweathermap.org/data/2.5/weather";
        }

        async getWeather(city) {
            try {
                const url = `${'$'}{this.baseUrl}?q=${'$'}{city}&appid=${'$'}{this.apiKey}&units=metric`;
                const response = await fetch(url);

                if (!response.ok) {
                    throw new Error("City not found");
                }

                const data = await response.json();
                return {
                    city: data.name,
                    temp: data.main.temp,
                    description: data.weather[0].description,
                    humidity: data.main.humidity
                };
            } catch (error) {
                console.error("Error:", error.message);
                return null;
            }
        }

        displayWeather(weather) {
            if (!weather) return;

            const html = `
                <div class="weather-card">
                    <h2>${'$'}{weather.city}</h2>
                    <div class="temp">${'$'}{weather.temp}°C</div>
                    <div class="description">${'$'}{weather.description}</div>
                    <div class="humidity">Humidity: ${'$'}{weather.humidity}%</div>
                </div>
            `;

            document.getElementById("weather-container").innerHTML = html;
        }
    }

    // Usage
    const app = new WeatherApp("YOUR_API_KEY");
    app.getWeather("London").then(weather => {
        app.displayWeather(weather);
    });
    */
""".trimIndent()

        )
    )

    // SQL LESSONS (30)
    private fun getSqlLessons() = listOf(
        // Lesson 1-10
        Lesson(
            id = "sql-1",
            courseId = "sql-basics",
            language = "sql",
            title = "Introduction to SQL",
            content = "SQL (Structured Query Language) is used to manage and query relational databases.",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
                -- SQL is a declarative language
                -- We describe WHAT we want, not HOW to get it

                -- Basic SQL statement
                SELECT * FROM users;

                -- SQL keywords (case insensitive)
                -- SELECT, FROM, WHERE, ORDER BY, GROUP BY, etc.

                -- Comments in SQL
                /* Multi-line
                   comment */
                -- Single-line comment
            """.trimIndent()
        ),
        Lesson(
            id = "sql-2",
            courseId = "sql-basics",
            language = "sql",
            title = "SELECT Statement",
            content = "Retrieve data from database tables.",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
                -- Select all columns
                SELECT * FROM employees;

                -- Select specific columns
                SELECT name, age, department
                FROM employees;

                -- Select with calculated column
                SELECT name, salary * 12 AS annual_salary
                FROM employees;

                -- Select distinct values
                SELECT DISTINCT department FROM employees;

                -- Limit results
                SELECT * FROM products LIMIT 10;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-3",
            courseId = "sql-basics",
            language = "sql",
            title = "Practice: SELECT",
            content = "Write a SELECT statement to get employee names and salaries.",
            type = LessonType.CODE_PRACTICE,
            order = 3,
            isCompleted = false,
            codeExample = "SELECT name, salary FROM employees;"
        ),
        Lesson(
            id = "sql-4",
            courseId = "sql-basics",
            language = "sql",
            title = "WHERE Clause",
            content = "Filter results based on conditions.",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = """
                -- Basic WHERE clause
                SELECT * FROM employees WHERE age > 30;

                -- Multiple conditions
                SELECT * FROM employees
                WHERE department = 'Sales' AND salary > 50000;

                -- OR operator
                SELECT * FROM employees
                WHERE department = 'Sales' OR department = 'Marketing';

                -- IN operator
                SELECT * FROM employees
                WHERE department IN ('Sales', 'Marketing', 'HR');

                -- LIKE operator (pattern matching)
                SELECT * FROM employees
                WHERE name LIKE 'J%';  -- Names starting with J

                -- BETWEEN operator
                SELECT * FROM employees
                WHERE salary BETWEEN 40000 AND 60000;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-5",
            courseId = "sql-basics",
            language = "sql",
            title = "Quiz: SQL Basics",
            content = "Test your understanding of SQL fundamentals",
            type = LessonType.QUIZ,
            order = 5,
            isCompleted = false,
            quiz = Quiz(
                id = "sql-quiz-1",
                question = "Which clause is used to filter rows in SQL?",
                options = listOf("FILTER", "WHERE", "HAVING", "CONDITION"),
                correctAnswer = 1,
                explanation = "The WHERE clause is used to filter rows based on conditions."
            )
        ),
        Lesson(
            id = "sql-6",
            courseId = "sql-basics",
            language = "sql",
            title = "ORDER BY",
            content = "Sort query results.",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
                -- Sort by single column
                SELECT * FROM employees
                ORDER BY name;

                -- Sort descending
                SELECT * FROM employees
                ORDER BY salary DESC;

                -- Sort by multiple columns
                SELECT * FROM employees
                ORDER BY department, salary DESC;

                -- Combined with WHERE
                SELECT * FROM employees
                WHERE age > 25
                ORDER BY hire_date DESC;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-7",
            courseId = "sql-basics",
            language = "sql",
            title = "INSERT Statement",
            content = "Add new records to a table.",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
                -- Insert single record
                INSERT INTO employees (name, age, department, salary)
                VALUES ('Alice Johnson', 28, 'Engineering', 75000);

                -- Insert multiple records
                INSERT INTO employees (name, age, department, salary)
                VALUES
                    ('Bob Smith', 32, 'Sales', 60000),
                    ('Charlie Brown', 45, 'Management', 90000),
                    ('Diana Prince', 29, 'Marketing', 55000);

                -- Insert with default values
                INSERT INTO employees (name, department)
                VALUES ('Eve Davis', 'HR');
                -- Other columns get default or NULL values
            """.trimIndent()
        ),
        Lesson(
            id = "sql-8",
            courseId = "sql-basics",
            language = "sql",
            title = "UPDATE Statement",
            content = "Modify existing records.",
            type = LessonType.THEORY,
            order = 8,
            isCompleted = false,
            codeExample = """
                -- Update all records
                UPDATE employees
                SET salary = salary * 1.05;  -- Give everyone 5% raise

                -- Update with WHERE clause
                UPDATE employees
                SET department = 'Senior Engineering'
                WHERE name = 'Alice Johnson';

                -- Update multiple columns
                UPDATE employees
                SET salary = 80000, title = 'Senior Developer'
                WHERE id = 123;

                -- Using expressions
                UPDATE products
                SET price = price * 0.9  -- 10% discount
                WHERE category = 'Electronics';
            """.trimIndent()
        ),
        Lesson(
            id = "sql-9",
            courseId = "sql-basics",
            language = "sql",
            title = "DELETE Statement",
            content = "Remove records from a table.",
            type = LessonType.THEORY,
            order = 9,
            isCompleted = false,
            codeExample = """
                -- Delete specific records
                DELETE FROM employees
                WHERE id = 456;

                -- Delete with multiple conditions
                DELETE FROM employees
                WHERE department = 'Temp' AND hire_date < '2023-01-01';

                -- Delete all records (be careful!)
                -- DELETE FROM employees;

                -- Truncate table (faster, cannot rollback)
                -- TRUNCATE TABLE employees;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-10",
            courseId = "sql-basics",
            language = "sql",
            title = "Practice: CRUD Operations",
            content = "Insert a new product and update its price.",
            type = LessonType.CODE_PRACTICE,
            order = 10,
            isCompleted = false,
            codeExample = "INSERT INTO products (name, price, category) VALUES ('Laptop', 999.99, 'Electronics');\nUPDATE products SET price = 899.99 WHERE name = 'Laptop';"
        ),
        // Lesson 11-20
        Lesson(
            id = "sql-11",
            courseId = "sql-basics",
            language = "sql",
            title = "Quiz: CRUD Operations",
            content = "Test your knowledge of basic SQL operations",
            type = LessonType.QUIZ,
            order = 11,
            isCompleted = false,
            quiz = Quiz(
                id = "sql-quiz-2",
                question = "Which SQL statement modifies existing records?",
                options = listOf("INSERT", "UPDATE", "MODIFY", "ALTER"),
                correctAnswer = 1,
                explanation = "UPDATE modifies existing records, while INSERT adds new ones."
            )
        ),
        Lesson(
            id = "sql-12",
            courseId = "sql-basics",
            language = "sql",
            title = "Aggregate Functions",
            content = "Perform calculations on multiple rows.",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = """
                -- COUNT: count rows
                SELECT COUNT(*) FROM employees;
                SELECT COUNT(DISTINCT department) FROM employees;

                -- SUM: calculate total
                SELECT SUM(salary) AS total_payroll FROM employees;

                -- AVG: calculate average
                SELECT AVG(salary) AS average_salary FROM employees;

                -- MIN and MAX
                SELECT MIN(salary) AS lowest_salary FROM employees;
                SELECT MAX(salary) AS highest_salary FROM employees;

                -- Combined example
                SELECT
                    COUNT(*) AS employee_count,
                    AVG(salary) AS avg_salary,
                    MAX(salary) AS max_salary
                FROM employees;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-13",
            courseId = "sql-basics",
            language = "sql",
            title = "GROUP BY",
            content = "Group rows that have the same values.",
            type = LessonType.THEORY,
            order = 13,
            isCompleted = false,
            codeExample = """
                -- Group by single column
                SELECT department, COUNT(*) AS employee_count
                FROM employees
                GROUP BY department;

                -- Group by multiple columns
                SELECT department, title, AVG(salary) AS avg_salary
                FROM employees
                GROUP BY department, title;

                -- With aggregate functions
                SELECT
                    department,
                    COUNT(*) AS count,
                    AVG(salary) AS avg_salary,
                    SUM(salary) AS total_salary
                FROM employees
                GROUP BY department;

                -- Filter groups with HAVING
                SELECT department, AVG(salary) AS avg_salary
                FROM employees
                GROUP BY department
                HAVING AVG(salary) > 50000;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-14",
            courseId = "sql-basics",
            language = "sql",
            title = "Practice: Aggregations",
            content = "Find the average salary per department.",
            type = LessonType.CODE_PRACTICE,
            order = 14,
            isCompleted = false,
            codeExample = "SELECT department, AVG(salary) AS average_salary FROM employees GROUP BY department ORDER BY average_salary DESC;"
        ),
        Lesson(
            id = "sql-15",
            courseId = "sql-basics",
            language = "sql",
            title = "JOIN Operations",
            content = "Combine rows from two or more tables.",
            type = LessonType.THEORY,
            order = 15,
            isCompleted = false,
            codeExample = """
                -- INNER JOIN (default)
                SELECT employees.name, departments.department_name
                FROM employees
                INNER JOIN departments ON employees.department_id = departments.id;

                -- LEFT JOIN (all from left table, matching from right)
                SELECT employees.name, departments.department_name
                FROM employees
                LEFT JOIN departments ON employees.department_id = departments.id;

                -- RIGHT JOIN (all from right table, matching from left)
                SELECT employees.name, departments.department_name
                FROM employees
                RIGHT JOIN departments ON employees.department_id = departments.id;

                -- FULL OUTER JOIN (all from both tables)
                SELECT employees.name, departments.department_name
                FROM employees
                FULL OUTER JOIN departments ON employees.department_id = departments.id;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-16",
            courseId = "sql-basics",
            language = "sql",
            title = "Quiz: Joins and Aggregations",
            content = "Test your understanding of advanced SQL concepts",
            type = LessonType.QUIZ,
            order = 16,
            isCompleted = false,
            quiz = Quiz(
                id = "sql-quiz-3",
                question = "Which JOIN returns all rows from both tables?",
                options = listOf("INNER JOIN", "LEFT JOIN", "RIGHT JOIN", "FULL OUTER JOIN"),
                correctAnswer = 3,
                explanation = "FULL OUTER JOIN returns all rows from both tables, with NULLs where no match exists."
            )
        ),
        Lesson(
            id = "sql-17",
            courseId = "sql-basics",
            language = "sql",
            title = "Subqueries",
            content = "Nested queries within other queries.",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = """
                -- Subquery in WHERE clause
                SELECT name, salary
                FROM employees
                WHERE salary > (SELECT AVG(salary) FROM employees);

                -- Subquery in SELECT clause
                SELECT
                    name,
                    salary,
                    (SELECT AVG(salary) FROM employees) AS company_avg
                FROM employees;

                -- Subquery in FROM clause
                SELECT department, avg_salary
                FROM (
                    SELECT department, AVG(salary) AS avg_salary
                    FROM employees
                    GROUP BY department
                ) AS dept_stats
                WHERE avg_salary > 50000;

                -- IN operator with subquery
                SELECT name, department
                FROM employees
                WHERE department_id IN (
                    SELECT id FROM departments
                    WHERE location = 'New York'
                );
            """.trimIndent()
        ),
        Lesson(
            id = "sql-18",
            courseId = "sql-basics",
            language = "sql",
            title = "CREATE TABLE",
            content = "Create new database tables.",
            type = LessonType.THEORY,
            order = 18,
            isCompleted = false,
            codeExample = """
                -- Basic table creation
                CREATE TABLE employees (
                    id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    age INT,
                    department VARCHAR(50),
                    salary DECIMAL(10,2),
                    hire_date DATE DEFAULT CURRENT_DATE
                );

                -- With constraints
                CREATE TABLE products (
                    product_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(200) NOT NULL,
                    price DECIMAL(10,2) CHECK (price > 0),
                    category VARCHAR(50),
                    in_stock BOOLEAN DEFAULT TRUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );

                -- Create table from existing table
                CREATE TABLE employees_backup AS
                SELECT * FROM employees;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-19",
            courseId = "sql-basics",
            language = "sql",
            title = "ALTER TABLE",
            content = "Modify existing table structure.",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = """
                -- Add new column
                ALTER TABLE employees
                ADD email VARCHAR(100);

                -- Drop column
                ALTER TABLE employees
                DROP COLUMN age;

                -- Modify column
                ALTER TABLE employees
                MODIFY COLUMN salary DECIMAL(12,2);

                -- Rename column
                ALTER TABLE employees
                RENAME COLUMN department TO dept;

                -- Add constraint
                ALTER TABLE employees
                ADD CONSTRAINT chk_salary CHECK (salary > 0);

                -- Drop constraint
                ALTER TABLE employees
                DROP CONSTRAINT chk_salary;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-20",
            courseId = "sql-basics",
            language = "sql",
            title = "Practice: Table Operations",
            content = "Create a table for storing customer orders.",
            type = LessonType.CODE_PRACTICE,
            order = 20,
            isCompleted = false,
            codeExample = "CREATE TABLE orders (\n    order_id INT PRIMARY KEY AUTO_INCREMENT,\n    customer_id INT NOT NULL,\n    product_id INT NOT NULL,\n    quantity INT DEFAULT 1,\n    order_date DATE DEFAULT CURRENT_DATE,\n    total_amount DECIMAL(10,2),\n    status VARCHAR(20) DEFAULT 'Pending'\n);"
        ),
        // Lesson 21-30
        Lesson(
            id = "sql-21",
            courseId = "sql-basics",
            language = "sql",
            title = "Quiz: Table Operations",
            content = "Test your knowledge of table creation and modification",
            type = LessonType.QUIZ,
            order = 21,
            isCompleted = false,
            quiz = Quiz(
                id = "sql-quiz-4",
                question = "Which statement modifies the structure of an existing table?",
                options = listOf("UPDATE", "MODIFY", "ALTER", "CHANGE"),
                correctAnswer = 2,
                explanation = "ALTER TABLE is used to modify the structure of existing tables."
            )
        ),
        Lesson(
            id = "sql-22",
            courseId = "sql-basics",
            language = "sql",
            title = "Indexes",
            content = "Improve query performance with indexes.",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = """
                -- Create index
                CREATE INDEX idx_employee_name
                ON employees(name);

                -- Create unique index
                CREATE UNIQUE INDEX idx_employee_email
                ON employees(email);

                -- Create composite index
                CREATE INDEX idx_dept_salary
                ON employees(department, salary);

                -- Drop index
                DROP INDEX idx_employee_name ON employees;

                -- Show indexes
                SHOW INDEX FROM employees;

                /* When to use indexes:
                   - Columns frequently used in WHERE clauses
                   - Columns used in JOIN conditions
                   - Columns used in ORDER BY
                   Avoid on:
                   - Small tables
                   - Columns frequently updated
                   - Columns with few unique values
                */
            """.trimIndent()
        ),
        Lesson(
            id = "sql-23",
            courseId = "sql-basics",
            language = "sql",
            title = "Views",
            content = "Virtual tables based on query results.",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = """
                -- Create view
                CREATE VIEW employee_summary AS
                SELECT
                    department,
                    COUNT(*) AS employee_count,
                    AVG(salary) AS avg_salary,
                    SUM(salary) AS total_salary
                FROM employees
                GROUP BY department;

                -- Use view like a table
                SELECT * FROM employee_summary
                WHERE avg_salary > 50000;

                -- Create or replace view
                CREATE OR REPLACE VIEW high_paid_employees AS
                SELECT name, salary, department
                FROM employees
                WHERE salary > 80000;

                -- Drop view
                DROP VIEW employee_summary;

                /* Benefits of views:
                   - Simplify complex queries
                   - Provide security (hide sensitive columns)
                   - Ensure consistency
                */
            """.trimIndent()
        ),
        Lesson(
            id = "sql-24",
            courseId = "sql-basics",
            language = "sql",
            title = "Practice: Views and Indexes",
            content = "Create a view for active employees.",
            type = LessonType.CODE_PRACTICE,
            order = 24,
            isCompleted = false,
            codeExample = "CREATE VIEW active_employees AS\nSELECT id, name, department, salary\nFROM employees\nWHERE status = 'Active';\n\nCREATE INDEX idx_status ON employees(status);"
        ),
        Lesson(
            id = "sql-25",
            courseId = "sql-basics",
            language = "sql",
            title = "Transactions",
            content = "Group SQL statements into atomic operations.",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
                -- Start transaction
                START TRANSACTION;

                -- Multiple SQL statements
                UPDATE accounts
                SET balance = balance - 100
                WHERE account_id = 123;

                UPDATE accounts
                SET balance = balance + 100
                WHERE account_id = 456;

                -- Commit transaction (save changes)
                COMMIT;

                -- Or rollback (undo changes)
                -- ROLLBACK;

                -- Example with error handling
                START TRANSACTION;

                BEGIN
                    DECLARE EXIT HANDLER FOR SQLEXCEPTION
                    BEGIN
                        ROLLBACK;
                        SELECT 'Transaction failed' AS result;
                    END;

                    -- Your SQL statements here
                    INSERT INTO orders (customer_id, amount) VALUES (1, 99.99);
                    UPDATE inventory SET quantity = quantity - 1 WHERE product_id = 5;

                    COMMIT;
                    SELECT 'Transaction successful' AS result;
                END;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-26",
            courseId = "sql-basics",
            language = "sql",
            title = "Stored Procedures",
            content = "Reusable SQL code stored in the database.",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
                -- Create stored procedure
                DELIMITER //
                CREATE PROCEDURE GetEmployeeCount(IN dept_name VARCHAR(50))
                BEGIN
                    SELECT COUNT(*) AS employee_count
                    FROM employees
                    WHERE department = dept_name;
                END //
                DELIMITER ;

                -- Call stored procedure
                CALL GetEmployeeCount('Sales');

                -- Procedure with parameters
                DELIMITER //
                CREATE PROCEDURE UpdateSalary(
                    IN emp_id INT,
                    IN new_salary DECIMAL(10,2)
                )
                BEGIN
                    UPDATE employees
                    SET salary = new_salary
                    WHERE id = emp_id;
                END //
                DELIMITER ;

                -- Drop procedure
                DROP PROCEDURE IF EXISTS GetEmployeeCount;
            """.trimIndent()
        ),
        Lesson(
            id = "sql-27",
            courseId = "sql-basics",
            language = "sql",
            title = "Quiz: Advanced SQL",
            content = "Test your knowledge of transactions and procedures",
            type = LessonType.QUIZ,
            order = 27,
            isCompleted = false,
            quiz = Quiz(
                id = "sql-quiz-5",
                question = "Which statement makes transaction changes permanent?",
                options = listOf("SAVE", "COMMIT", "END", "COMPLETE"),
                correctAnswer = 1,
                explanation = "COMMIT makes all changes in the current transaction permanent."
            )
        ),
        Lesson(
            id = "sql-28",
            courseId = "sql-basics",
            language = "sql",
            title = "Database Normalization",
            content = "Organize data to reduce redundancy and improve integrity.",
            type = LessonType.THEORY,
            order = 28,
            isCompleted = false,
            codeExample = """
                /* Normalization Forms:
                   1NF: Atomic values, no repeating groups
                   2NF: No partial dependencies (all non-key attributes depend on whole primary key)
                   3NF: No transitive dependencies (non-key attributes depend only on primary key)
                */

                -- Example: Unnormalized table
                CREATE TABLE orders_unnormalized (
                    order_id INT,
                    customer_name VARCHAR(100),
                    customer_email VARCHAR(100),
                    product1 VARCHAR(100),
                    product2 VARCHAR(100),  -- Repeating group
                    product3 VARCHAR(100)
                );

                -- Normalized design
                CREATE TABLE customers (
                    customer_id INT PRIMARY KEY,
                    name VARCHAR(100),
                    email VARCHAR(100)
                );

                CREATE TABLE products (
                    product_id INT PRIMARY KEY,
                    name VARCHAR(100),
                    price DECIMAL(10,2)
                );

                CREATE TABLE orders (
                    order_id INT PRIMARY KEY,
                    customer_id INT,
                    order_date DATE,
                    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
                );

                CREATE TABLE order_items (
                    order_id INT,
                    product_id INT,
                    quantity INT,
                    PRIMARY KEY (order_id, product_id),
                    FOREIGN KEY (order_id) REFERENCES orders(order_id),
                    FOREIGN KEY (product_id) REFERENCES products(product_id)
                );
            """.trimIndent()
        ),
        Lesson(
            id = "sql-29",
            courseId = "sql-basics",
            language = "sql",
            title = "Practice: Database Design",
            content = "Design normalized tables for a library system.",
            type = LessonType.CODE_PRACTICE,
            order = 29,
            isCompleted = false,
            codeExample = "CREATE TABLE books (\n    book_id INT PRIMARY KEY,\n    title VARCHAR(200) NOT NULL,\n    author VARCHAR(100),\n    isbn VARCHAR(13) UNIQUE\n);\n\nCREATE TABLE members (\n    member_id INT PRIMARY KEY,\n    name VARCHAR(100) NOT NULL,\n    email VARCHAR(100) UNIQUE\n);\n\nCREATE TABLE loans (\n    loan_id INT PRIMARY KEY,\n    book_id INT,\n    member_id INT,\n    loan_date DATE,\n    return_date DATE,\n    FOREIGN KEY (book_id) REFERENCES books(book_id),\n    FOREIGN KEY (member_id) REFERENCES members(member_id)\n);"
        ),
        Lesson(
            id = "sql-30",
            courseId = "sql-basics",
            language = "sql",
            title = "Final Challenge: E-commerce Database",
            content = "Design and implement a complete e-commerce database.",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            codeExample = """
                -- Complete e-commerce database schema
                /*
                CREATE TABLE customers (
                    customer_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    phone VARCHAR(20),
                    address TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );

                CREATE TABLE products (
                    product_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(200) NOT NULL,
                    description TEXT,
                    price DECIMAL(10,2) NOT NULL,
                    stock_quantity INT DEFAULT 0,
                    category VARCHAR(50),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );

                CREATE TABLE orders (
                    order_id INT PRIMARY KEY AUTO_INCREMENT,
                    customer_id INT NOT NULL,
                    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    total_amount DECIMAL(10,2) NOT NULL,
                    status ENUM('Pending', 'Processing', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
                    shipping_address TEXT,
                    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
                );

                CREATE TABLE order_items (
                    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
                    order_id INT NOT NULL,
                    product_id INT NOT NULL,
                    quantity INT NOT NULL,
                    unit_price DECIMAL(10,2) NOT NULL,
                    FOREIGN KEY (order_id) REFERENCES orders(order_id),
                    FOREIGN KEY (product_id) REFERENCES products(product_id)
                );

                CREATE TABLE payments (
                    payment_id INT PRIMARY KEY AUTO_INCREMENT,
                    order_id INT NOT NULL,
                    amount DECIMAL(10,2) NOT NULL,
                    payment_method VARCHAR(50),
                    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    status ENUM('Pending', 'Completed', 'Failed') DEFAULT 'Pending',
                    FOREIGN KEY (order_id) REFERENCES orders(order_id)
                );

                -- Sample queries for the system
                -- 1. Get customer order history
                SELECT c.name, o.order_id, o.order_date, o.total_amount, o.status
                FROM customers c
                JOIN orders o ON c.customer_id = o.customer_id
                WHERE c.customer_id = 1
                ORDER BY o.order_date DESC;

                -- 2. Get popular products
                SELECT p.name, SUM(oi.quantity) AS total_sold
                FROM products p
                JOIN order_items oi ON p.product_id = oi.product_id
                GROUP BY p.product_id
                ORDER BY total_sold DESC
                LIMIT 10;

                -- 3. Calculate monthly revenue
                SELECT
                    DATE_FORMAT(order_date, '%Y-%m') AS month,
                    COUNT(*) AS order_count,
                    SUM(total_amount) AS revenue
                FROM orders
                GROUP BY DATE_FORMAT(order_date, '%Y-%m')
                ORDER BY month DESC;
                */
            """.trimIndent()
        )
    )

    // DATA SCIENCE LESSONS (30)
    private fun getDataScienceLessons() = listOf(
        // Lesson 1-10: Introduction & Python for DS
        Lesson(
            id = "ds-1",
            courseId = "data-science",
            language = "python",
            title = "What is Data Science?",
            content = "Data Science is the field of study that combines domain expertise, programming skills, and knowledge of mathematics and statistics to extract meaningful insights from data.",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
                # Data science lifecycle:
                # 1. Data Collection
                # 2. Data Cleaning
                # 3. Exploratory Data Analysis (EDA)
                # 4. Modeling
                # 5. Interpretation
                print("Welcome to Data Science!")
            """.trimIndent()
        ),
        Lesson(
            id = "ds-2",
            courseId = "data-science",
            language = "python",
            title = "Intro to NumPy",
            content = "NumPy is the fundamental package for scientific computing in Python. It provides a powerful N-dimensional array object.",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
                import numpy as np

                # Creating a NumPy array
                arr = np.array([1, 2, 3, 4, 5])
                print(arr)
                print(arr.mean())  # Calculate mean
            """.trimIndent()
        ),
        Lesson(
            id = "ds-3",
            courseId = "data-science",
            language = "python",
            title = "Practice: NumPy Arrays",
            content = "Create a NumPy array of 10 zeros and print it.",
            type = LessonType.CODE_PRACTICE,
            order = 3,
            isCompleted = false,
            codeExample = "import numpy as np\narr = np.zeros(10)\nprint(arr)"
        ),
        Lesson(
            id = "ds-4",
            courseId = "data-science",
            language = "python",
            title = "Intro to Pandas",
            content = "Pandas is a fast, powerful, flexible and easy to use open source data analysis and manipulation tool.",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = """
                import pandas as pd

                # Creating a DataFrame
                data = {
                    'Name': ['Alice', 'Bob', 'Charlie'],
                    'Age': [25, 30, 35],
                    'City': ['NY', 'SF', 'CHI']
                }
                df = pd.DataFrame(data)
                print(df.head())
            """.trimIndent()
        ),
        Lesson(
            id = "ds-5",
            courseId = "data-science",
            language = "python",
            title = "Quiz: Library Basics",
            content = "Test your knowledge of NumPy and Pandas",
            type = LessonType.QUIZ,
            order = 5,
            isCompleted = false,
            quiz = Quiz(
                id = "ds-quiz-1",
                question = "Which library is primary used for data manipulation and analysis?",
                options = listOf("NumPy", "Pandas", "Matplotlib", "Seaborn"),
                correctAnswer = 1,
                explanation = "Pandas is the primary library for data table (DataFrame) manipulation."
            )
        ),
        Lesson(
            id = "ds-6",
            courseId = "data-science",
            language = "python",
            title = "Pandas: Reading Data",
            content = "You can read data from various formats like CSV, Excel, and SQL using Pandas.",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
                import pandas as pd

                # Reading a CSV file
                # df = pd.read_csv('data.csv')

                # Display statistics
                # print(df.describe())
            """.trimIndent()
        ),
        Lesson(
            id = "ds-7",
            courseId = "data-science",
            language = "python",
            title = "Pandas: Selection & Filtering",
            content = "Learn how to select specific columns and filter rows based on conditions.",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
                import pandas as pd

                df = pd.DataFrame({
                    'A': [1, 2, 3],
                    'B': [4, 5, 6]
                })

                # Selecting a column
                col_a = df['A']

                # Filtering rows
                filtered = df[df['A'] > 1]
                print(filtered)
            """.trimIndent()
        ),
        Lesson(
            id = "ds-8",
            courseId = "data-science",
            language = "python",
            title = "Practice: Pandas Filtering",
            content = "Filter a DataFrame to only show rows where 'Age' is greater than 20.",
            type = LessonType.CODE_PRACTICE,
            order = 8,
            isCompleted = false,
            codeExample = "import pandas as pd\ndf = pd.DataFrame({'Age': [18, 22, 25]})\nprint(df[df['Age'] > 20])"
        ),
        Lesson(
            id = "ds-9",
            courseId = "data-science",
            language = "python",
            title = "Introduction to Matplotlib",
            content = "Matplotlib is a comprehensive library for creating static, animated, and interactive visualizations in Python.",
            type = LessonType.THEORY,
            order = 9,
            isCompleted = false,
            codeExample = """
                import matplotlib.pyplot as plt

                plt.plot([1, 2, 3, 4], [1, 4, 9, 16])
                plt.xlabel('X-axis')
                plt.ylabel('Y-axis')
                plt.show()
            """.trimIndent()
        ),
        Lesson(
            id = "ds-10",
            courseId = "data-science",
            language = "python",
            title = "Quiz: Visualization",
            content = "Test your knowledge of visualization basics",
            type = LessonType.QUIZ,
            order = 10,
            isCompleted = false,
            quiz = Quiz(
                id = "ds-quiz-2",
                question = "Which function is used to create a line plot in Matplotlib?",
                options = listOf("bar()", "scatter()", "plot()", "hist()"),
                correctAnswer = 2,
                explanation = "plt.plot() is used for line plots."
            )
        ),
        // Lesson 11-20: Data Cleaning & Analysis
        Lesson(
            id = "ds-11",
            courseId = "data-science",
            language = "python",
            title = "Data Cleaning: Missing Values",
            content = "Handling missing data is a crucial step in data science. You can drop them or fill them with values.",
            type = LessonType.THEORY,
            order = 11,
            isCompleted = false,
            codeExample = """
                import pandas as pd
                import numpy as np

                df = pd.DataFrame({'A': [1, np.nan, 3]})

                # Drop rows with NaN
                df_dropped = df.dropna()

                # Fill NaN with mean
                df_filled = df.fillna(df.mean())
                print(df_filled)
            """.trimIndent()
        ),
        Lesson(
            id = "ds-12",
            courseId = "data-science",
            language = "python",
            title = "Groupby Operations",
            content = "Split-apply-combine strategy for data analysis.",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = """
                import pandas as pd

                df = pd.DataFrame({
                    'Dept': ['Sales', 'IT', 'Sales'],
                    'Salary': [50000, 60000, 55000]
                })

                # Calculate mean salary per department
                print(df.groupby('Dept').mean())
            """.trimIndent()
        ),
        Lesson(
            id = "ds-13",
            courseId = "data-science",
            language = "python",
            title = "Practice: Groupby",
            content = "Calculate the sum of 'Sales' grouped by 'Region'.",
            type = LessonType.CODE_PRACTICE,
            order = 13,
            isCompleted = false,
            codeExample = "import pandas as pd\ndf = pd.DataFrame({'Region': ['N', 'S', 'N'], 'Sales': [100, 200, 150]})\nprint(df.groupby('Region').sum())"
        ),
        Lesson(
            id = "ds-14",
            courseId = "data-science",
            language = "python",
            title = "Intro to Seaborn",
            content = "Seaborn is a Python data visualization library based on matplotlib. It provides a high-level interface for drawing attractive and informative statistical graphics.",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
                import seaborn as sns
                import matplotlib.pyplot as plt

                # Load yodgorbek dataset
                tips = sns.load_dataset("tips")
                sns.boxplot(x="day", y="total_bill", data=tips)
                plt.show()
            """.trimIndent()
        ),
        Lesson(
            id = "ds-15",
            courseId = "data-science",
            language = "python",
            title = "Statistical Concepts",
            content = "Brief intro to Mean, Median, Mode, Variance, and Standard Deviation.",
            type = LessonType.THEORY,
            order = 15,
            isCompleted = false,
            codeExample = """
                import numpy as np
                data = [1, 2, 2, 3, 4]

                print(f"Mean: {np.mean(data)}")
                print(f"StdDev: {np.std(data)}")
            """.trimIndent()
        ),
        Lesson(
            id = "ds-16",
            courseId = "data-science",
            language = "python",
            title = "Correlation",
            content = "Correlation measures how closely two variables change together.",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = """
                import pandas as pd
                df = pd.DataFrame({
                    'X': [1, 2, 3, 4],
                    'Y': [2, 4, 6, 8]
                })
                print(df.corr())
            """.trimIndent()
        ),
        Lesson(
            id = "ds-17",
            courseId = "data-science",
            language = "python",
            title = "Quiz: Statistics",
            content = "Test your statistical understanding",
            type = LessonType.QUIZ,
            order = 17,
            isCompleted = false,
            quiz = Quiz(
                id = "ds-quiz-3",
                question = "What is the middle value in a sorted dataset called?",
                options = listOf("Mean", "Median", "Mode", "Variance"),
                correctAnswer = 1,
                explanation = "The Median is the middle value of a sorted dataset."
            )
        ),
        Lesson(
            id = "ds-18",
            courseId = "data-science",
            language = "python",
            title = "Scikit-Learn Basics",
            content = "Scikit-learn is a simple and efficient tool for predictive data analysis.",
            type = LessonType.THEORY,
            order = 18,
            isCompleted = false,
            codeExample = """
                # Generic Scikit-Learn structure:
                # 1. Choose model class
                # 2. Instantiate model
                # 3. model.fit(X, y)
                # 4. model.predict(X_test)
            """.trimIndent()
        ),
        Lesson(
            id = "ds-19",
            courseId = "data-science",
            language = "python",
            title = "Linear Regression",
            content = "Predicting a continuous value based on independent variables.",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = """
                from sklearn.linear_model import LinearRegression
                import numpy as np

                X = np.array([[1], [2], [3]])
                y = np.array([2, 4, 6])

                model = LinearRegression()
                model.fit(X, y)
                print(model.predict([[4]])) # Expected: [8]
            """.trimIndent()
        ),
        Lesson(
            id = "ds-20",
            courseId = "data-science",
            language = "python",
            title = "Practice: Linear Regression",
            content = "Fit a linear regression model to predict y from X.",
            type = LessonType.CODE_PRACTICE,
            order = 20,
            isCompleted = false,
            codeExample = "from sklearn.linear_model import LinearRegression\nimport numpy as np\nX = np.array([[1], [2]])\ny = np.array([3, 5])\nmodel = LinearRegression().fit(X, y)\nprint(model.coef_)"
        ),
        // Lesson 21-30: Machine Learning & Beyond
        Lesson(
            id = "ds-21",
            courseId = "data-science",
            language = "python",
            title = "Classification & Logistics Regression",
            content = "Predicting a categorical label (e.g., spam vs. not spam).",
            type = LessonType.THEORY,
            order = 21,
            isCompleted = false,
            codeExample = """
                from sklearn.linear_model import LogisticRegression
                # Similar fit/predict pattern
            """.trimIndent()
        ),
        Lesson(
            id = "ds-22",
            courseId = "data-science",
            language = "python",
            title = "Evaluation Metrics: Classification",
            content = "Accuracy, Precision, Recall, and F1-Score.",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = """
                from sklearn.metrics import accuracy_score
                # accuracy_score(y_true, y_pred)
            """.trimIndent()
        ),
        Lesson(
            id = "ds-23",
            courseId = "data-science",
            language = "python",
            title = "Quiz: Machine Learning",
            content = "Test your ML basics",
            type = LessonType.QUIZ,
            order = 23,
            isCompleted = false,
            quiz = Quiz(
                id = "ds-quiz-4",
                question = "Linear Regression is used for predicting...",
                options = listOf("Categories", "Continuous values", "Images", "Text"),
                correctAnswer = 1,
                explanation = "Linear Regression is a regression technique for continuous output."
            )
        ),
        Lesson(
            id = "ds-24",
            courseId = "data-science",
            language = "python",
            title = "K-Means Clustering",
            content = "Unsupervised learning method to group data into K clusters.",
            type = LessonType.THEORY,
            order = 24,
            isCompleted = false,
            codeExample = """
                from sklearn.cluster import KMeans
                import numpy as np

                X = np.array([[1, 2], [1, 4], [1, 0], [10, 2], [10, 4], [10, 0]])
                kmeans = KMeans(n_clusters=2).fit(X)
                print(kmeans.labels_)
            """.trimIndent()
        ),
        Lesson(
            id = "ds-25",
            courseId = "data-science",
            language = "python",
            title = "Feature Engineering",
            content = "The process of creating new features or transforming existing ones to improve model performance.",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
                # Examples:
                # - Scaling (Standardization/Normalization)
                # - One-Hot Encoding for categorical data
                # - Handling Date/Time features
            """.trimIndent()
        ),
        Lesson(
            id = "ds-26",
            courseId = "data-science",
            language = "python",
            title = "Decision Trees",
            content = "A flowchart-like structure used for both classification and regression.",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
                from sklearn.tree import DecisionTreeClassifier
                # clf = DecisionTreeClassifier().fit(X, y)
            """.trimIndent()
        ),
        Lesson(
            id = "ds-27",
            courseId = "data-science",
            language = "python",
            title = "Random Forest",
            content = "An ensemble learning method that operates by constructing a multitude of decision trees.",
            type = LessonType.THEORY,
            order = 27,
            isCompleted = false,
            codeExample = """
                from sklearn.ensemble import RandomForestClassifier
                # forest = RandomForestClassifier().fit(X, y)
            """.trimIndent()
        ),
        Lesson(
            id = "ds-28",
            courseId = "data-science",
            language = "python",
            title = "Practice: Random Forest",
            content = "Instantiate a Random Forest Classifier with 100 estimators.",
            type = LessonType.CODE_PRACTICE,
            order = 28,
            isCompleted = false,
            codeExample = "from sklearn.ensemble import RandomForestClassifier\nclf = RandomForestClassifier(n_estimators=100)\nprint('Model Created')"
        ),
        Lesson(
            id = "ds-29",
            courseId = "data-science",
            language = "python",
            title = "Introduction to Deep Learning",
            content = "A subset of machine learning based on artificial neural networks.",
            type = LessonType.THEORY,
            order = 29,
            isCompleted = false,
            codeExample = """
                # Key libraries:
                # - TensorFlow / Keras
                # - PyTorch
            """.trimIndent()
        ),
        Lesson(
            id = "ds-30",
            courseId = "data-science",
            language = "python",
            title = "Final Challenge: Titanic Analysis",
            content = "Analyze the Titanic dataset to find patterns in survivor demographics.",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            codeExample = """
                import pandas as pd
                # Analyze survivor rates by class and gender
                # df.groupby(['Pclass', 'Sex'])['Survived'].mean()
                print("Final Challenge: Analyze survival rates!")
            """.trimIndent()
        )
    )

    private fun getPythonKidsLessons() = listOf(
        Lesson(
            id = "pk-1",
            courseId = "python-kids",
            language = "python",
            title = "Hello World! 👋",
            content = "Welcome to coding! Let's make the computer say 'Hello' to you. It's like magic!",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = "print('Hello World!')"
        ),
        Lesson(
            id = "pk-2",
            courseId = "python-kids",
            language = "python",
            title = "Chatting with Python 💬",
            content = "You can print anything you want! Try printing your name or your favorite food.",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = "print('I love pizza! 🍕')"
        ),
        Lesson(
            id = "pk-3",
            courseId = "python-kids",
            language = "python",
            title = "Counting Apples 🍎",
            content = "Computers are great at counting. Let's see how many apples we have.",
            type = LessonType.THEORY,
            order = 3,
            isCompleted = false,
            codeExample = "print(5)"
        ),
        Lesson(
            id = "pk-4",
            courseId = "python-kids",
            language = "python",
            title = "Adding Toys 🧸",
            content = "If you have 2 teddy bears and you get 3 more, how many do you have? Python knows!",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = "print(2 + 3)"
        ),
        Lesson(
            id = "pk-5",
            courseId = "python-kids",
            language = "python",
            title = "Magic Boxes (Variables) 📦",
            content = "Variables are like magic boxes. You can put things inside them and give them a name.",
            type = LessonType.THEORY,
            order = 5,
            isCompleted = false,
            codeExample = "box = 'My Secret Toy'\nprint(box)"
        ),
        Lesson(
            id = "pk-6",
            courseId = "python-kids",
            language = "python",
            title = "Naming Your Boxes 🏷️",
            content = "Give your boxes fun names like 'apples' or 'name'.",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = "apples = 10\nprint(apples)"
        ),
        Lesson(
            id = "pk-7",
            courseId = "python-kids",
            language = "python",
            title = "Fun Words (Strings) ✍️",
            content = "Words in coding are called 'strings'. They always go inside quotes like 'this'.",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = "word = 'Superstar! ⭐'\nprint(word)"
        ),
        Lesson(
            id = "pk-8",
            courseId = "python-kids",
            language = "python",
            defaultInput = "Kid Coder",
            title = "Talking Back 🖐️",
            content = "You can tell the computer something using 'input'. It's like the computer is listening!",
            type = LessonType.THEORY,
            order = 8,
            isCompleted = false,
            codeExample = "name = input('What is your name? ')\nprint('Hi ' + name)"
        ),
        Lesson(
            id = "pk-9",
            courseId = "python-kids",
            language = "python",
            title = "Quiz: Basic Talk ❓",
            content = "Do you remember how to print?",
            type = LessonType.QUIZ,
            order = 9,
            isCompleted = false,
            quiz = Quiz(
                id = "pk-quiz-1",
                question = "How do you make Python talk?",
                options = listOf("talk()", "say()", "print()", "shout()"),
                correctAnswer = 2,
                explanation = "We use print() to show text on the screen!"
            )
        ),
        Lesson(
            id = "pk-10",
            courseId = "python-kids",
            language = "python",
            title = "Practice: My Name is... ✍️",
            content = "Create a variable with your name and print it.",
            type = LessonType.CODE_PRACTICE,
            order = 10,
            isCompleted = false,
            codeExample = "my_name = 'Kid Coder'\nprint(my_name)"
        ),
        Lesson(
            id = "pk-11",
            courseId = "python-kids",
            language = "python",
            title = "True or False? ✅❌",
            content = "Sometimes things are True and sometimes they are False. Like: Is the sky green? False!",
            type = LessonType.THEORY,
            order = 11,
            isCompleted = false,
            codeExample = "is_sunny = True\nis_raining = False\nprint(is_sunny)"
        ),
        Lesson(
            id = "pk-12",
            courseId = "python-kids",
            language = "python",
            title = "Making Choices (If) 🤔",
            content = "If it's sunny, we go outside! 'if' helps the computer make choices.",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = "is_sunny = True\nif is_sunny:\n    print('Go play outside! 🌞')"
        ),
        Lesson(
            id = "pk-13",
            courseId = "python-kids",
            language = "python",
            title = "Otherwise... (Else) ⛈️",
            content = "If it's not sunny, we stay inside. We use 'else' for the other choice.",
            type = LessonType.THEORY,
            order = 13,
            isCompleted = false,
            codeExample = "is_sunny = False\nif is_sunny:\n    print('Go play!')\nelse:\n    print('Read a book! 📖')"
        ),
        Lesson(
            id = "pk-14",
            courseId = "python-kids",
            language = "python",
            title = "Too Many Choices? (Elif) 🌈",
            content = "What if it's raining, or snowing, or windy? 'elif' helps with more choices.",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = "weather = 'snowing'\nif weather == 'sunny':\n    print('Beach time!')\nelif weather == 'snowing':\n    print('Build a snowman! ☃️')"
        ),
        Lesson(
            id = "pk-15",
            courseId = "python-kids",
            language = "python",
            title = "Which is Bigger? ⚖️",
            content = "Let's compare numbers. Is 10 bigger than 5? Yes!",
            type = LessonType.THEORY,
            order = 15,
            isCompleted = false,
            codeExample = "if 10 > 5:\n    print('10 is bigger! 🐘')"
        ),
        Lesson(
            id = "pk-16",
            courseId = "python-kids",
            language = "python",
            title = "My Toy Chest (Lists) 📦📦",
            content = "A list is a big box that holds many things. Like all your favorite toys!",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = "toys = ['Car', 'Doll', 'Ball']\nprint(toys)"
        ),
        Lesson(
            id = "pk-17",
            courseId = "python-kids",
            language = "python",
            title = "Adding More Toys ➕",
            content = "Got a new toy? Add it to your list with 'append'!",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = "toys = ['Car', 'Doll']\ntoys.append('Robot')\nprint(toys)"
        ),
        Lesson(
            id = "pk-18",
            courseId = "python-kids",
            language = "python",
            title = "Taking Toys Out ➖",
            content = "Don't want a toy anymore? Remove it from your list!",
            type = LessonType.THEORY,
            order = 18,
            isCompleted = false,
            codeExample = "toys = ['Car', 'Doll']\ntoys.remove('Car')\nprint(toys)"
        ),
        Lesson(
            id = "pk-19",
            courseId = "python-kids",
            language = "python",
            title = "Repeat After Me 🔄",
            content = "Loops help the computer do the same thing over and over. Like jumping 10 times!",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = "for i in range(3):\n    print('Jump! 🦘')"
        ),
        Lesson(
            id = "pk-20",
            courseId = "python-kids",
            language = "python",
            title = "Counting Your Toys 🔟",
            content = "Let's use a loop to count everything in our list.",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = "toys = ['Car', 'Doll', 'Ball']\nfor toy in toys:\n    print('I have a ' + toy)"
        ),
        Lesson(
            id = "pk-21",
            courseId = "python-kids",
            language = "python",
            title = "Until We Finish (While) ⏳",
            content = "While you still have cookies, keep eating! A 'while' loop keeps going until it's done.",
            type = LessonType.THEORY,
            order = 21,
            isCompleted = false,
            codeExample = "cookies = 3\nwhile cookies > 0:\n    print('Eating a cookie... 🍪')\n    cookies = cookies - 1\nprint('All gone!')"
        ),
        Lesson(
            id = "pk-22",
            courseId = "python-kids",
            language = "python",
            title = "Magic Spells (Functions) ✨",
            content = "Functions are like magic spells. You name them and use them whenever you want.",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = "def say_magic():\n    print('Abracadabra! ✨')\n\nsay_magic()"
        ),
        Lesson(
            id = "pk-23",
            courseId = "python-kids",
            language = "python",
            title = "Custom Spells 🪄",
            content = "You can give your spells information to make them do different things.",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = "def say_hi(name):\n    print('Hi ' + name + '! 👋')\n\nsay_hi('Kid')"
        ),
        Lesson(
            id = "pk-24",
            courseId = "python-kids",
            language = "python",
            title = "Magical Results (Return) 🎁",
            content = "Some spells give you something back! Like a present.",
            type = LessonType.THEORY,
            order = 24,
            isCompleted = false,
            codeExample = "def double_number(number):\n    return number * 2\n\nresult = double_number(5)\nprint(result)"
        ),
        Lesson(
            id = "pk-25",
            courseId = "python-kids",
            language = "python",
            title = "Oops! Fixing Bugs 🐛",
            content = "Errors are just 'bugs'. We find them and fix them. It's like a puzzle!",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = "# Try dividing by zero\ntry:\n    print(10 / 0)\nexcept:\n    print('Oops! Can not do that! 🚫')"
        ),
        Lesson(
            id = "pk-26",
            courseId = "python-kids",
            language = "python",
            title = "Secret Notes (Comments) 📝",
            content = "Use # to write notes to yourself. The computer will ignore them!",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = "# This is a secret note\nprint('Hello!') # Computer only sees this"
        ),
        Lesson(
            id = "pk-27",
            courseId = "python-kids",
            language = "python",
            title = "Quiz: Master Coder 🏆",
            content = "Are you ready to be a master?",
            type = LessonType.QUIZ,
            order = 27,
            isCompleted = false,
            quiz = Quiz(
                id = "pk-quiz-2",
                question = "What is a 'bug' in coding?",
                options = listOf("A real spider", "An error in the code", "A small robot", "A feature"),
                correctAnswer = 1,
                explanation = "A bug is just a mistake in the code that we need to fix!"
            )
        ),
        Lesson(
            id = "pk-28",
            courseId = "python-kids",
            language = "python",
            title = "Practice: Robot Friend 🤖",
            content = "Make a robot that says your favorite color.",
            type = LessonType.CODE_PRACTICE,
            order = 28,
            isCompleted = false,
            codeExample = "color = 'Blue'\nprint('Robot says: My favorite color is ' + color)"
        ),
        Lesson(
            id = "pk-29",
            courseId = "python-kids",
            language = "python",
            title = "Practice: Fruit List 🍎🍌",
            content = "Create a list of 3 fruits and print the first one.",
            type = LessonType.CODE_PRACTICE,
            order = 29,
            isCompleted = false,
            codeExample = "fruits = ['Apple', 'Banana', 'Orange']\nprint(fruits[0])"
        ),
        Lesson(
            id = "pk-30",
            courseId = "python-kids",
            language = "python",
            title = "Final: Superhero Generator 🦸‍♂️",
            content = "Let's combine everything! Make a superhero name generator.",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            codeExample = """
                def make_super(name):
                    return 'Super ' + name

                my_name = 'Coder'
                print('Your Superhero Name is: ' + make_super(my_name) + '! ⚡')
            """.trimIndent()
        )
    )

    private fun getJavaDsaLessons() = CourseContent.javaDsaLessons

    private fun getJavaDpLessons() = JavaDpLeetcodeRepositoryImpl.javaDpLessons

    private fun getLeetcodePatternLessons() = LeetcodePatternsRepositoryImpl.leetcodePatternLessons
}
