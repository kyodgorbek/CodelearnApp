// data/repository/CourseRepositoryImpl.kt
package com.example.codelearnapp.data.repository

import com.example.codelearnapp.domain.model.Course
import com.example.codelearnapp.domain.model.CourseCategory
import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.model.LessonType
import com.example.codelearnapp.domain.model.Quiz
import com.example.codelearnapp.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class CourseRepositoryImpl : CourseRepository {

    private val coursesFlow = MutableStateFlow(getMockCourses())
    private val lessonsFlow = MutableStateFlow(getMockLessons())

    override fun getAllCourses(): Flow<List<Course>> = coursesFlow

    override fun getCourseById(id: String): Flow<Course?> =
        coursesFlow.map { courses -> courses.find { it.id == id } }

    override fun getLessonsByCourse(courseId: String): Flow<List<Lesson>> =
        lessonsFlow.map { lessons -> lessons.filter { it.courseId == courseId } }

    override fun getLessonById(lessonId: String): Flow<Lesson?> =
        lessonsFlow.map { lessons -> lessons.find { it.id == lessonId } }

    override suspend fun updateLessonProgress(lessonId: String, isCompleted: Boolean) {
        val updatedLessons = lessonsFlow.value.map { lesson ->
            if (lesson.id == lessonId) lesson.copy(isCompleted = isCompleted)
            else lesson
        }
        lessonsFlow.value = updatedLessons

        // Update course progress
        val lesson = lessonsFlow.value.find { it.id == lessonId }
        lesson?.let {
            val courseId = it.courseId
            val courseLessons = lessonsFlow.value.filter { l -> l.courseId == courseId }
            val completed = courseLessons.count { l -> l.isCompleted }
            val total = courseLessons.size
            val progress = completed.toFloat() / total.toFloat()

            val updatedCourses = coursesFlow.value.map { course ->
                if (course.id == courseId) {
                    course.copy(
                        completedLessons = completed,
                        progress = progress
                    )
                } else course
            }
            coursesFlow.value = updatedCourses
        }
    }

    private fun getMockCourses() = listOf(
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
        )
    )

    private fun getMockLessons(): List<Lesson> {
        return getPythonLessons() +
                getKotlinLessons() +
                getJavaLessons() +
                getJavaScriptLessons() +
                getSqlLessons()
    }

    // PYTHON LESSONS (30)
    private fun getPythonLessons() = listOf(
        // Lesson 1-10
        Lesson(
            id = "py-1",
            courseId = "python-basics",
            title = "Introduction to Python",
            content = "Python is a versatile, high-level programming language known for its simplicity and readability. It's used in web development, data science, AI, and more.",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
                # Your first Python program
                print("Hello, Python!")
                print("Welcome to coding!")
            """.trimIndent()
        ),
        Lesson(
            id = "py-2",
            courseId = "python-basics",
            title = "Variables and Data Types",
            content = "Variables store data. Python has several data types: strings, integers, floats, and booleans.",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
                # Different data types
                name = "Alice"          # String
                age = 25                # Integer
                height = 5.7            # Float
                is_student = True       # Boolean
                
                print(f"{name} is {age} years old")
            """.trimIndent()
        ),
        Lesson(
            id = "py-3",
            courseId = "python-basics",
            title = "Practice: Create Variables",
            content = "Create a variable called 'greeting' with the value 'Hello World' and print it.",
            type = LessonType.CODE_PRACTICE,
            order = 3,
            isCompleted = false,
            codeExample = "greeting = 'Hello World'\nprint(greeting)"
        ),
        Lesson(
            id = "py-4",
            courseId = "python-basics",
            title = "String Operations",
            content = "Strings can be concatenated, sliced, and formatted in various ways.",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = """
                # String operations
                first_name = "John"
                last_name = "Doe"
                full_name = first_name + " " + last_name
                
                # String formatting
                message = f"Hello, {full_name}!"
                print(message)
                
                # String slicing
                print(full_name[0:4])  # "John"
            """.trimIndent()
        ),
        Lesson(
            id = "py-5",
            courseId = "python-basics",
            title = "Quiz: Python Basics",
            content = "Test your understanding of Python fundamentals",
            type = LessonType.QUIZ,
            order = 5,
            isCompleted = false,
            quiz = Quiz(
                id = "py-quiz-1",
                question = "Which symbol is used for comments in Python?",
                options = listOf("#", "//", "/*", "--"),
                correctAnswer = 0,
                explanation = "Python uses # for single-line comments and ''' or \"\"\" for multi-line comments."
            )
        ),
        Lesson(
            id = "py-6",
            courseId = "python-basics",
            title = "Numbers and Math",
            content = "Python supports various mathematical operations and number types.",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
                # Basic math operations
                x = 10
                y = 3
                
                print(x + y)   # Addition: 13
                print(x - y)   # Subtraction: 7
                print(x * y)   # Multiplication: 30
                print(x / y)   # Division: 3.333...
                print(x // y)  # Floor division: 3
                print(x % y)   # Modulus: 1
                print(x ** y)  # Exponentiation: 1000
            """.trimIndent()
        ),
        Lesson(
            id = "py-7",
            courseId = "python-basics",
            title = "Lists",
            content = "Lists are ordered, mutable collections that can store multiple items.",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
                # Creating and using lists
                fruits = ["apple", "banana", "cherry"]
                numbers = [1, 2, 3, 4, 5]
                
                # Accessing elements
                print(fruits[0])  # "apple"
                print(fruits[-1]) # "cherry" (last item)
                
                # Adding items
                fruits.append("orange")
                
                # List length
                print(len(fruits))
            """.trimIndent()
        ),
        Lesson(
            id = "py-8",
            courseId = "python-basics",
            title = "List Operations",
            content = "Learn how to manipulate lists with various methods.",
            type = LessonType.THEORY,
            order = 8,
            isCompleted = false,
            codeExample = """
                numbers = [3, 1, 4, 1, 5, 9, 2, 6]
                
                # Sorting
                numbers.sort()
                print(numbers)  # [1, 1, 2, 3, 4, 5, 6, 9]
                
                # Reversing
                numbers.reverse()
                
                # Removing items
                numbers.remove(1)  # Removes first occurrence
                popped = numbers.pop()  # Removes and returns last item
            """.trimIndent()
        ),
        Lesson(
            id = "py-9",
            courseId = "python-basics",
            title = "Practice: Working with Lists",
            content = "Create a list of your favorite colors and add a new color to it.",
            type = LessonType.CODE_PRACTICE,
            order = 9,
            isCompleted = false,
            codeExample = "colors = ['red', 'blue', 'green']\ncolors.append('yellow')\nprint(colors)"
        ),
        Lesson(
            id = "py-10",
            courseId = "python-basics",
            title = "Quiz: Lists",
            content = "Test your knowledge of Python lists",
            type = LessonType.QUIZ,
            order = 10,
            isCompleted = false,
            quiz = Quiz(
                id = "py-quiz-2",
                question = "Which method adds an item to the end of a list?",
                options = listOf("add()", "append()", "insert()", "push()"),
                correctAnswer = 1,
                explanation = "The append() method adds an item to the end of a list."
            )
        ),
        // Lesson 11-20
        Lesson(
            id = "py-11",
            courseId = "python-basics",
            title = "Dictionaries",
            content = "Dictionaries store data in key-value pairs, allowing fast lookups.",
            type = LessonType.THEORY,
            order = 11,
            isCompleted = false,
            codeExample = """
                # Creating a dictionary
                person = {
                    "name": "Alice",
                    "age": 25,
                    "city": "New York"
                }
                
                # Accessing values
                print(person["name"])  # "Alice"
                print(person.get("age"))  # 25
                
                # Adding/updating values
                person["job"] = "Developer"
                person["age"] = 26
            """.trimIndent()
        ),
        Lesson(
            id = "py-12",
            courseId = "python-basics",
            title = "If Statements",
            content = "Conditional statements allow your code to make decisions.",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = """
                age = 18
                
                if age >= 18:
                    print("You are an adult")
                elif age >= 13:
                    print("You are a teenager")
                else:
                    print("You are a child")
                
                # Comparison operators: ==, !=, <, >, <=, >=
                # Logical operators: and, or, not
            """.trimIndent()
        ),
        Lesson(
            id = "py-13",
            courseId = "python-basics",
            title = "Practice: Conditionals",
            content = "Write code to check if a number is positive, negative, or zero.",
            type = LessonType.CODE_PRACTICE,
            order = 13,
            isCompleted = false,
            codeExample = "number = 10\nif number > 0:\n    print('Positive')\nelif number < 0:\n    print('Negative')\nelse:\n    print('Zero')"
        ),
        Lesson(
            id = "py-14",
            courseId = "python-basics",
            title = "For Loops",
            content = "Loops allow you to repeat code multiple times.",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
                # Loop through a list
                fruits = ["apple", "banana", "cherry"]
                for fruit in fruits:
                    print(fruit)
                
                # Loop with range
                for i in range(5):
                    print(i)  # 0, 1, 2, 3, 4
                
                # Loop with custom range
                for i in range(2, 10, 2):
                    print(i)  # 2, 4, 6, 8
            """.trimIndent()
        ),
        Lesson(
            id = "py-15",
            courseId = "python-basics",
            title = "While Loops",
            content = "While loops continue executing as long as a condition is true.",
            type = LessonType.THEORY,
            order = 15,
            isCompleted = false,
            codeExample = """
                # Basic while loop
                count = 0
                while count < 5:
                    print(count)
                    count += 1
                
                # While with break
                while True:
                    user_input = input("Enter 'quit' to exit: ")
                    if user_input == "quit":
                        break
            """.trimIndent()
        ),
        Lesson(
            id = "py-16",
            courseId = "python-basics",
            title = "Quiz: Loops",
            content = "Test your understanding of loops",
            type = LessonType.QUIZ,
            order = 16,
            isCompleted = false,
            quiz = Quiz(
                id = "py-quiz-3",
                question = "What does range(5) produce?",
                options = listOf("1, 2, 3, 4, 5", "0, 1, 2, 3, 4", "1, 2, 3, 4", "0, 1, 2, 3, 4, 5"),
                correctAnswer = 1,
                explanation = "range(5) produces numbers from 0 to 4 (5 is not included)."
            )
        ),
        Lesson(
            id = "py-17",
            courseId = "python-basics",
            title = "Functions",
            content = "Functions are reusable blocks of code that perform specific tasks.",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = """
                # Defining a function
                def greet(name):
                    return f"Hello, {name}!"
                
                # Calling a function
                message = greet("Alice")
                print(message)
                
                # Function with multiple parameters
                def add(a, b):
                    return a + b
                
                result = add(5, 3)
                print(result)  # 8
            """.trimIndent()
        ),
        Lesson(
            id = "py-18",
            courseId = "python-basics",
            title = "Function Parameters",
            content = "Learn about default parameters, keyword arguments, and more.",
            type = LessonType.THEORY,
            order = 18,
            isCompleted = false,
            codeExample = """
                # Default parameters
                def greet(name, greeting="Hello"):
                    return f"{greeting}, {name}!"
                
                print(greet("Alice"))  # "Hello, Alice!"
                print(greet("Bob", "Hi"))  # "Hi, Bob!"
                
                # Keyword arguments
                def describe_person(name, age, city):
                    print(f"{name}, {age}, from {city}")
                
                describe_person(age=25, name="Alice", city="NYC")
            """.trimIndent()
        ),
        Lesson(
            id = "py-19",
            courseId = "python-basics",
            title = "Practice: Create a Function",
            content = "Create a function that calculates the area of a rectangle.",
            type = LessonType.CODE_PRACTICE,
            order = 19,
            isCompleted = false,
            codeExample = "def calculate_area(width, height):\n    return width * height\n\nprint(calculate_area(5, 3))"
        ),
        Lesson(
            id = "py-20",
            courseId = "python-basics",
            title = "List Comprehensions",
            content = "A concise way to create lists based on existing lists.",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = """
                # Traditional way
                squares = []
                for i in range(10):
                    squares.append(i ** 2)
                
                # List comprehension
                squares = [i ** 2 for i in range(10)]
                
                # With condition
                even_squares = [i ** 2 for i in range(10) if i % 2 == 0]
                print(even_squares)  # [0, 4, 16, 36, 64]
            """.trimIndent()
        ),
        // Lesson 21-30
        Lesson(
            id = "py-21",
            courseId = "python-basics",
            title = "Quiz: Functions",
            content = "Test your knowledge of Python functions",
            type = LessonType.QUIZ,
            order = 21,
            isCompleted = false,
            quiz = Quiz(
                id = "py-quiz-4",
                question = "What keyword is used to define a function in Python?",
                options = listOf("function", "def", "func", "define"),
                correctAnswer = 1,
                explanation = "The 'def' keyword is used to define functions in Python."
            )
        ),
        Lesson(
            id = "py-22",
            courseId = "python-basics",
            title = "File Handling",
            content = "Learn how to read from and write to files in Python.",
            type = LessonType.THEORY,
            order = 22,
            isCompleted = false,
            codeExample = """
                # Writing to a file
                with open("example.txt", "w") as file:
                    file.write("Hello, World!")
                
                # Reading from a file
                with open("example.txt", "r") as file:
                    content = file.read()
                    print(content)
                
                # Reading line by line
                with open("example.txt", "r") as file:
                    for line in file:
                        print(line.strip())
            """.trimIndent()
        ),
        Lesson(
            id = "py-23",
            courseId = "python-basics",
            title = "Exception Handling",
            content = "Handle errors gracefully using try-except blocks.",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = """
                # Basic exception handling
                try:
                    result = 10 / 0
                except ZeroDivisionError:
                    print("Cannot divide by zero!")
                
                # Multiple exceptions
                try:
                    number = int(input("Enter a number: "))
                    result = 10 / number
                except ValueError:
                    print("Invalid input!")
                except ZeroDivisionError:
                    print("Cannot divide by zero!")
                finally:
                    print("Execution completed")
            """.trimIndent()
        ),
        Lesson(
            id = "py-24",
            courseId = "python-basics",
            title = "Classes and Objects",
            content = "Object-oriented programming allows you to create custom data types.",
            type = LessonType.THEORY,
            order = 24,
            isCompleted = false,
            codeExample = """
                # Defining a class
                class Dog:
                    def __init__(self, name, age):
                        self.name = name
                        self.age = age
                    
                    def bark(self):
                        return f"{self.name} says Woof!"
                
                # Creating objects
                my_dog = Dog("Buddy", 3)
                print(my_dog.bark())  # "Buddy says Woof!"
                print(my_dog.age)     # 3
            """.trimIndent()
        ),
        Lesson(
            id = "py-25",
            courseId = "python-basics",
            title = "Practice: Create a Class",
            content = "Create a Person class with name and age attributes.",
            type = LessonType.CODE_PRACTICE,
            order = 25,
            isCompleted = false,
            codeExample = "class Person:\n    def __init__(self, name, age):\n        self.name = name\n        self.age = age\n    \n    def introduce(self):\n        return f\"I'm {self.name}, {self.age} years old\"\n\nperson = Person(\"Alice\", 25)\nprint(person.introduce())"
        ),
        Lesson(
            id = "py-26",
            courseId = "python-basics",
            title = "Inheritance",
            content = "Classes can inherit attributes and methods from other classes.",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
                class Animal:
                    def __init__(self, name):
                        self.name = name
                    
                    def speak(self):
                        pass
                
                class Dog(Animal):
                    def speak(self):
                        return f"{self.name} says Woof!"
                
                class Cat(Animal):
                    def speak(self):
                        return f"{self.name} says Meow!"
                
                dog = Dog("Buddy")
                cat = Cat("Whiskers")
                print(dog.speak())
                print(cat.speak())
            """.trimIndent()
        ),
        Lesson(
            id = "py-27",
            courseId = "python-basics",
            title = "Quiz: OOP",
            content = "Test your understanding of object-oriented programming",
            type = LessonType.QUIZ,
            order = 27,
            isCompleted = false,
            quiz = Quiz(
                id = "py-quiz-5",
                question = "What method is called when an object is created?",
                options = listOf("__create__", "__init__", "__new__", "__start__"),
                correctAnswer = 1,
                explanation = "__init__ is the constructor method that initializes new objects."
            )
        ),
        Lesson(
            id = "py-28",
            courseId = "python-basics",
            title = "Modules and Imports",
            content = "Organize your code using modules and import functionality.",
            type = LessonType.THEORY,
            order = 28,
            isCompleted = false,
            codeExample = """
                # Importing entire module
                import math
                print(math.sqrt(16))  # 4.0
                
                # Importing specific functions
                from math import pi, sqrt
                print(pi)  # 3.14159...
                
                # Importing with alias
                import datetime as dt
                now = dt.datetime.now()
                print(now)
            """.trimIndent()
        ),
        Lesson(
            id = "py-29",
            courseId = "python-basics",
            title = "Lambda Functions",
            content = "Anonymous functions for simple operations.",
            type = LessonType.THEORY,
            order = 29,
            isCompleted = false,
            codeExample = """
                # Regular function
                def square(x):
                    return x ** 2
                
                # Lambda function
                square = lambda x: x ** 2
                print(square(5))  # 25
                
                # Lambda with multiple parameters
                add = lambda a, b: a + b
                print(add(3, 4))  # 7
                
                # Using lambda with map
                numbers = [1, 2, 3, 4, 5]
                squared = list(map(lambda x: x ** 2, numbers))
                print(squared)  # [1, 4, 9, 16, 25]
            """.trimIndent()
        ),
        Lesson(
            id = "py-30",
            courseId = "python-basics",
            title = "Final Challenge: Python Project",
            content = "Create a simple calculator that can add, subtract, multiply, and divide.",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            codeExample = """
                def calculator():
                    print("Simple Calculator")
                    print("Operations: +, -, *, /")
                    
                    try:
                        num1 = float(input("Enter first number: "))
                        operation = input("Enter operation: ")
                        num2 = float(input("Enter second number: "))
                        
                        if operation == '+':
                            result = num1 + num2
                        elif operation == '-':
                            result = num1 - num2
                        elif operation == '*':
                            result = num1 * num2
                        elif operation == '/':
                            if num2 == 0:
                                print("Error: Cannot divide by zero")
                                return
                            result = num1 / num2
                        else:
                            print("Invalid operation")
                            return
                        
                        print(f"Result: {result}")
                    except ValueError:
                        print("Invalid input")
                
                calculator()
            """.trimIndent()
        )
    )

    // KOTLIN LESSONS (30)
    private fun getKotlinLessons() = listOf(
        // Lesson 1-10
        Lesson(
            id = "kt-1",
            courseId = "kotlin-android",
            title = "Welcome to Kotlin",
            content = "Kotlin is a modern, concise programming language that runs on the JVM. It's the preferred language for Android development.",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
                fun main() {
                    println("Hello, Kotlin!")
                    println("Welcome to Android development")
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-2",
            courseId = "kotlin-android",
            title = "Variables: val vs var",
            content = "Kotlin has two types of variables: val (immutable) and var (mutable).",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val name = "Alice"  // Immutable (read-only)
                    var age = 25        // Mutable (can be changed)
                    
                    // name = "Bob"  // Error! val cannot be reassigned
                    age = 26  // OK! var can be reassigned
                    
                    println("${'$'}name is ${'$'}age years old")
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-3",
            courseId = "kotlin-android",
            title = "Data Types",
            content = "Kotlin has various data types: String, Int, Double, Boolean, etc.",
            type = LessonType.THEORY,
            order = 3,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val text: String = "Hello"
                    val number: Int = 42
                    val decimal: Double = 3.14
                    val isTrue: Boolean = true
                    val character: Char = 'A'
                    
                    // Type inference
                    val autoString = "Kotlin"  // Type inferred as String
                    val autoInt = 100          // Type inferred as Int
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-4",
            courseId = "kotlin-android",
            title = "Practice: Variables",
            content = "Create variables for a person's name, age, and city.",
            type = LessonType.CODE_PRACTICE,
            order = 4,
            isCompleted = false,
            codeExample = "fun main() {\n    val name = \"Alice\"\n    var age = 25\n    val city = \"New York\"\n    println(\"${'$'}name, ${'$'}age, ${'$'}city\")\n}"
        ),
        Lesson(
            id = "kt-5",
            courseId = "kotlin-android",
            title = "Quiz: Kotlin Basics",
            content = "Test your understanding of Kotlin fundamentals",
            type = LessonType.QUIZ,
            order = 5,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-quiz-1",
                question = "Which keyword is used for immutable variables in Kotlin?",
                options = listOf("var", "val", "const", "let"),
                correctAnswer = 1,
                explanation = "'val' declares read-only (immutable) variables in Kotlin."
            )
        ),
        Lesson(
            id = "kt-6",
            courseId = "kotlin-android",
            title = "String Templates",
            content = "Kotlin makes string interpolation easy with string templates.",
            type = LessonType.THEORY,
            order = 6,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val name = "Alice"
                    val age = 25
                    
                    // String template
                    println("Name: ${'$'}name")
                    
                    // Expression in template
                    println("Next year: ${'$'}{age + 1}")
                    
                    // Multi-line string
                    val message = ${"\"\"\""}
                        Hello, ${'$'}name!
                        You are ${'$'}age years old.
                    ${"\"\"\""}.trimIndent()
                    
                    println(message)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-7",
            courseId = "kotlin-android",
            title = "Null Safety",
            content = "Kotlin's type system eliminates null pointer exceptions.",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
                fun main() {
                    var name: String = "Alice"
                    // name = null  // Compilation error!
                    
                    var nullableName: String? = "Bob"
                    nullableName = null  // OK
                    
                    // Safe call operator
                    println(nullableName?.length)
                    
                    // Elvis operator
                    val length = nullableName?.length ?: 0
                    
                    // Not-null assertion
                    // val len = nullableName!!.length  // Throws exception if null
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-8",
            courseId = "kotlin-android",
            title = "Functions",
            content = "Functions in Kotlin are declared with the 'fun' keyword.",
            type = LessonType.THEORY,
            order = 8,
            isCompleted = false,
            codeExample = """
                // Basic function
                fun greet(name: String): String {
                    return "Hello, ${'$'}name!"
                }
                
                // Single-expression function
                fun add(a: Int, b: Int) = a + b
                
                // Function with default parameters
                fun greetWithTime(name: String, time: String = "day") {
                    println("Good ${'$'}time, ${'$'}name!")
                }
                
                fun main() {
                    println(greet("Alice"))
                    println(add(5, 3))
                    greetWithTime("Bob")
                    greetWithTime("Charlie", "evening")
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-9",
            courseId = "kotlin-android",
            title = "Practice: Functions",
            content = "Create a function that calculates the square of a number.",
            type = LessonType.CODE_PRACTICE,
            order = 9,
            isCompleted = false,
            codeExample = "fun square(n: Int): Int = n * n\n\nfun main() {\n    println(square(5))  // 25\n}"
        ),
        Lesson(
            id = "kt-10",
            courseId = "kotlin-android",
            title = "When Expression",
            content = "Kotlin's 'when' is a powerful replacement for switch statements.",
            type = LessonType.THEORY,
            order = 10,
            isCompleted = false,
            codeExample = """
                fun describe(x: Any) = when(x) {
                    1 -> "One"
                    2, 3 -> "Two or Three"
                    in 4..10 -> "Between 4 and 10"
                    is String -> "It's a string"
                    else -> "Unknown"
                }
                
                fun main() {
                    val day = 3
                    val dayName = when(day) {
                        1 -> "Monday"
                        2 -> "Tuesday"
                        3 -> "Wednesday"
                        else -> "Other day"
                    }
                    println(dayName)
                }
            """.trimIndent()
        ),
        // Lesson 11-20
        Lesson(
            id = "kt-11",
            courseId = "kotlin-android",
            title = "Quiz: Functions and When",
            content = "Test your knowledge of Kotlin functions",
            type = LessonType.QUIZ,
            order = 11,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-quiz-2",
                question = "What keyword is used to define functions in Kotlin?",
                options = listOf("function", "fun", "def", "func"),
                correctAnswer = 1,
                explanation = "The 'fun' keyword is used to define functions in Kotlin."
            )
        ),
        Lesson(
            id = "kt-12",
            courseId = "kotlin-android",
            title = "Collections: Lists",
            content = "Kotlin has powerful collection types: List, Set, and Map.",
            type = LessonType.THEORY,
            order = 12,
            isCompleted = false,
            codeExample = """
                fun main() {
                    // Immutable list
                    val fruits = listOf("Apple", "Banana", "Cherry")
                    println(fruits[0])  // "Apple"
                    
                    // Mutable list
                    val numbers = mutableListOf(1, 2, 3)
                    numbers.add(4)
                    numbers.remove(1)
                    
                    // List operations
                    println(fruits.size)
                    println(fruits.contains("Banana"))
                    println(fruits.first())
                    println(fruits.last())
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-13",
            courseId = "kotlin-android",
            title = "Collections: Maps",
            content = "Maps store key-value pairs for fast lookups.",
            type = LessonType.THEORY,
            order = 13,
            isCompleted = false,
            codeExample = """
                fun main() {
                    // Immutable map
                    val ages = mapOf(
                        "Alice" to 25,
                        "Bob" to 30,
                        "Charlie" to 35
                    )
                    println(ages["Alice"])  // 25
                    
                    // Mutable map
                    val scores = mutableMapOf<String, Int>()
                    scores["Alice"] = 95
                    scores["Bob"] = 87
                    
                    // Iterating
                    for ((name, score) in scores) {
                        println("${'$'}name: ${'$'}score")
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-14",
            courseId = "kotlin-android",
            title = "For Loops",
            content = "Iterate over ranges, collections, and more.",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
                fun main() {
                    // Range
                    for (i in 1..5) {
                        println(i)  // 1, 2, 3, 4, 5
                    }
                    
                    // Until (excludes last)
                    for (i in 1 until 5) {
                        println(i)  // 1, 2, 3, 4
                    }
                    
                    // Step
                    for (i in 0..10 step 2) {
                        println(i)  // 0, 2, 4, 6, 8, 10
                    }
                    
                    // Iterating list
                    val fruits = listOf("Apple", "Banana", "Cherry")
                    for (fruit in fruits) {
                        println(fruit)
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-15",
            courseId = "kotlin-android",
            title = "Practice: Loops",
            content = "Create a loop that prints numbers from 1 to 10.",
            type = LessonType.CODE_PRACTICE,
            order = 15,
            isCompleted = false,
            codeExample = "fun main() {\n    for (i in 1..10) {\n        println(i)\n    }\n}"
        ),
        Lesson(
            id = "kt-16",
            courseId = "kotlin-android",
            title = "Classes and Objects",
            content = "Define classes to create custom types.",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = """
                class Person(val name: String, var age: Int) {
                    fun greet() {
                        println("Hello, I'm ${'$'}name and I'm ${'$'}age years old")
                    }
                }
                
                fun main() {
                    val person = Person("Alice", 25)
                    person.greet()
                    
                    println(person.name)
                    person.age = 26
                    println(person.age)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-17",
            courseId = "kotlin-android",
            title = "Data Classes",
            content = "Data classes automatically generate useful methods.",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = """
                data class User(val name: String, val age: Int)
                
                fun main() {
                    val user1 = User("Alice", 25)
                    val user2 = User("Alice", 25)
                    
                    // Automatic equals()
                    println(user1 == user2)  // true
                    
                    // Automatic toString()
                    println(user1)  // User(name=Alice, age=25)
                    
                    // Copy with modifications
                    val user3 = user1.copy(age = 26)
                    println(user3)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-18",
            courseId = "kotlin-android",
            title = "Quiz: Classes",
            content = "Test your understanding of Kotlin classes",
            type = LessonType.QUIZ,
            order = 18,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-quiz-3",
                question = "What keyword creates a class with auto-generated methods?",
                options = listOf("class", "data class", "object", "sealed class"),
                correctAnswer = 1,
                explanation = "Data classes automatically generate equals(), hashCode(), toString(), and copy()."
            )
        ),
        Lesson(
            id = "kt-19",
            courseId = "kotlin-android",
            title = "Inheritance",
            content = "Classes can inherit from other classes.",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = """
                open class Animal(val name: String) {
                    open fun sound() {
                        println("Some sound")
                    }
                }
                
                class Dog(name: String) : Animal(name) {
                    override fun sound() {
                        println("${'$'}name says Woof!")
                    }
                }
                
                class Cat(name: String) : Animal(name) {
                    override fun sound() {
                        println("${'$'}name says Meow!")
                    }
                }
                
                fun main() {
                    val dog = Dog("Buddy")
                    val cat = Cat("Whiskers")
                    dog.sound()
                    cat.sound()
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-20",
            courseId = "kotlin-android",
            title = "Lambda Expressions",
            content = "Lambdas are anonymous functions for concise code.",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = """
                fun main() {
                    val numbers = listOf(1, 2, 3, 4, 5)
                    
                    // Lambda with map
                    val squared = numbers.map { it * it }
                    println(squared)  // [1, 4, 9, 16, 25]
                    
                    // Lambda with filter
                    val evens = numbers.filter { it % 2 == 0 }
                    println(evens)  // [2, 4]
                    
                    // Lambda variable
                    val sum: (Int, Int) -> Int = { a, b -> a + b }
                    println(sum(3, 4))  // 7
                }
            """.trimIndent()
        ),
        // Lesson 21-30
        Lesson(
            id = "kt-21",
            courseId = "kotlin-android",
            title = "Higher-Order Functions",
            content = "Functions that take functions as parameters or return functions.",
            type = LessonType.THEORY,
            order = 21,
            isCompleted = false,
            codeExample = """
                fun operate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
                    return operation(a, b)
                }
                
                fun main() {
                    val sum = operate(5, 3) { x, y -> x + y }
                    val product = operate(5, 3) { x, y -> x * y }
                    
                    println("Sum: ${'$'}sum")       // 8
                    println("Product: ${'$'}product")  // 15
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-22",
            courseId = "kotlin-android",
            title = "Practice: Lambdas",
            content = "Use filter and map to get squared even numbers.",
            type = LessonType.CODE_PRACTICE,
            order = 22,
            isCompleted = false,
            codeExample = "val numbers = listOf(1, 2, 3, 4, 5, 6)\nval result = numbers.filter { it % 2 == 0 }.map { it * it }\nprintln(result)  // [4, 16, 36]"
        ),
        Lesson(
            id = "kt-23",
            courseId = "kotlin-android",
            title = "Extension Functions",
            content = "Add new functions to existing classes without inheritance.",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = """
                // Extension function for String
                fun String.addExclamation(): String {
                    return this + "!"
                }
                
                // Extension function for Int
                fun Int.isEven(): Boolean {
                    return this % 2 == 0
                }
                
                fun main() {
                    val greeting = "Hello"
                    println(greeting.addExclamation())  // "Hello!"
                    
                    println(4.isEven())   // true
                    println(5.isEven())   // false
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-24",
            courseId = "kotlin-android",
            title = "Quiz: Advanced Kotlin",
            content = "Test your knowledge of lambdas and extensions",
            type = LessonType.QUIZ,
            order = 24,
            isCompleted = false,
            quiz = Quiz(
                id = "kt-quiz-4",
                question = "What allows you to add functions to existing classes?",
                options = listOf("Inheritance", "Extension functions", "Interfaces", "Abstract classes"),
                correctAnswer = 1,
                explanation = "Extension functions let you add new functions to existing classes."
            )
        ),
        Lesson(
            id = "kt-25",
            courseId = "kotlin-android",
            title = "Scope Functions: let, run, with",
            content = "Scope functions provide concise ways to work with objects.",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
                fun main() {
                    // let - useful for null checks
                    val name: String? = "Alice"
                    name?.let {
                        println("Name length: ${'$'}{it.length}")
                    }
                    
                    // run - execute block and return result
                    val result = "Hello".run {
                        println(this)
                        length
                    }
                    
                    // with - group function calls
                    val numbers = mutableListOf(1, 2, 3)
                    with(numbers) {
                        add(4)
                        add(5)
                        println(this)
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-26",
            courseId = "kotlin-android",
            title = "Sealed Classes",
            content = "Represent restricted class hierarchies.",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
                sealed class Result {
                    data class Success(val data: String) : Result()
                    data class Error(val message: String) : Result()
                    object Loading : Result()
                }
                
                fun handleResult(result: Result) {
                    when (result) {
                        is Result.Success -> println("Data: ${'$'}{result.data}")
                        is Result.Error -> println("Error: ${'$'}{result.message}")
                        Result.Loading -> println("Loading...")
                    }
                }
                
                fun main() {
                    handleResult(Result.Success("Hello"))
                    handleResult(Result.Error("Failed"))
                    handleResult(Result.Loading)
                }
            """.trimIndent()
        ),
        Lesson(
            id = "kt-27",
            courseId = "kotlin-android",
            title = "Coroutines Basics",
            content = "Write asynchronous code that looks synchronous.",
            type = LessonType.THEORY,
            order = 27,
            isCompleted = false,
            codeExample = """
                // Note: This requires coroutines dependency
                /*
                import kotlinx.coroutines.*
                
                fun main() = runBlocking {
                    // Launch a coroutine
                    launch {
                        delay(1000)
                        println("World!")
                    }
                    
                    println("Hello,")
                    delay(2000)
                }
                
                // Async/await
                suspend fun fetchData(): String {
                    delay(1000)
                    return "Data loaded"
                }
                */
            """.trimIndent()
        ),
        Lesson(
            id = "kt-28",
            courseId = "kotlin-android",
            title = "Practice: Coroutines",
            content = "Create a suspend function that simulates network call.",
            type = LessonType.CODE_PRACTICE,
            order = 28,
            isCompleted = false,
            codeExample = "/*\nsuspend fun loadUser(): String {\n    delay(1000)\n    return \"User data loaded\"\n}\n*/"
        ),
        Lesson(
            id = "kt-29",
            courseId = "kotlin-android",
            title = "Android Basics: Activities",
            content = "Activities are the entry points for Android apps.",
            type = LessonType.THEORY,
            order = 29,
            isCompleted = false,
            codeExample = """
                // Android Activity example
                /*
                class MainActivity : AppCompatActivity() {
                    override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                        setContent {
                            MyApp()
                        }
                    }
                }
                
                @Composable
                fun MyApp() {
                    Text("Hello, Android!")
                }
                */
            """.trimIndent()
        ),
        Lesson(
            id = "kt-30",
            courseId = "kotlin-android",
            title = "Final Challenge: Build an App",
            content = "Create a simple note-taking app with Jetpack Compose.",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            codeExample = """
                // Simple note-taking app structure
                /*
                @Composable
                fun NoteApp() {
                    var notes by remember { mutableStateOf(listOf<String>()) }
                    var newNote by remember { mutableStateOf("") }
                    
                    Column(modifier = Modifier.padding(16.dp)) {
                        TextField(
                            value = newNote,
                            onValueChange = { newNote = it },
                            label = { Text("New note") }
                        )
                        
                        Button(onClick = {
                            if (newNote.isNotBlank()) {
                                notes = notes + newNote
                                newNote = ""
                            }
                        }) {
                            Text("Add Note")
                        }
                        
                        LazyColumn {
                            items(notes) { note ->
                                Text(note)
                            }
                        }
                    }
                }
                */
            """.trimIndent()
        )
    )

    // JAVA LESSONS (30)
    private fun getJavaLessons() = listOf(
        // Lesson 1-10
        Lesson(
            id = "java-1",
            courseId = "java-programming",
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
            title = "Practice: Modern JS Features",
            content = "Use destructuring and spread operators.",
            type = LessonType.CODE_PRACTICE,
            order = 26,
            isCompleted = false,
            codeExample = "const user = {\n    id: 1,\n    name: \"Alice\",\n    email: \"alice@example.com\",\n    age: 25\n};\n\n// Destructure user object\nconst { name, email, ...otherInfo } = user;\nconsole.log(name, email, otherInfo);"
        ),
        Lesson(
            id = "js-27",
            courseId = "web-dev",
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
}