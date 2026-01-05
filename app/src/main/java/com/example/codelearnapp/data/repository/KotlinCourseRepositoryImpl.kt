package com.example.codelearnapp.data.repository

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.model.LessonType
import com.example.codelearnapp.domain.model.Quiz

object KotlinCourseRepositoryImpl {
    val kotlinLessons = listOf(
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
                                text(note)
                            }
                        }
                    }
                }
                */
            """.trimIndent()
        )
    )
}
