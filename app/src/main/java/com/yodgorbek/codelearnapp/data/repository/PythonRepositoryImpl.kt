package com.yodgorbek.codelearnapp.data.repository

import com.yodgorbek.codelearnapp.domain.model.Lesson
import com.yodgorbek.codelearnapp.domain.model.LessonType
import com.yodgorbek.codelearnapp.domain.model.Quiz

object PythonRepositoryImpl {
    val pythonLessons = listOf(
        // Lesson 1-10
        Lesson(
            id = "py-1",
            courseId = "python-basics",
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
            title = "While Loops",
            content = "While loops continue executing as long as a condition is true.",
            type = LessonType.THEORY,
            order = 15,
            isCompleted = false,
            defaultInput = "quit",
            codeExample = """
                # Basic while loop
                count = 0
                while count < 5:
                    print(count)
                    count += 1

                # While with break
                while True:
                    user_input = "quit"
                    if user_input == "quit":
                        break
            """.trimIndent()
        ),
        Lesson(
            id = "py-16",
            courseId = "python-basics",
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
                with open("yodgorbek.txt", "r") as file:
                    content = file.read()
                    print(content)

                # Reading line by line
                with open("yodgorbek.txt", "r") as file:
                    for line in file:
                        print(line.strip())
            """.trimIndent()
        ),
        Lesson(
            id = "py-23",
            courseId = "python-basics",
            language = "python",
            title = "Exception Handling",
            content = "Handle errors gracefully using try-except blocks.",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            defaultInput = "5",
            codeExample = """
                # Basic exception handling
                try:
                    result = 10 / 0
                except ZeroDivisionError:
                    print("Cannot divide by zero!")

                # Multiple exceptions
                try:
                    number = 5
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
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
            language = "python",
            title = "Final Challenge: Python Project",
            content = "Create a simple calculator that can add, subtract, multiply, and divide.",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            defaultInput = "10\n+\n5",
            codeExample = """
                def calculator():
                    print("Simple Calculator")
                    print("Operations: +, -, *, /")

                    try:
                        num1 = 10.0
                        operation = "+"
                        num2 = 5.0

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
}
