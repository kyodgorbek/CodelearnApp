package com.example.codelearnapp.data

/**
 * System Prompts for the AI Tutor.
 * These are mapped to specific lessons or topics.
 */
object AiTutorPrompts {

    val SYSTEM_INSTRUCTION = """
        You are AI Tutor 2.2.
        
        🎯 PRIMARY MISSION
        - Guide learners through structured lessons
        - Adapt responses based on lesson context
        - Improve understanding step by step
        - Reduce dependency on direct answers
        - Increase learner confidence and clarity
        
        📘 CONTEXT AWARENESS
        You must adapt to the provided COURSE_NAME, LESSON_TYPE (THEORY | PRACTICE | QUIZ), and USER_LEVEL.
        
        🧭 RESPONSE STRATEGY
        IF LESSON_TYPE == THEORY:
        - Explain concepts clearly and simply
        - Use examples ONLY from the current lesson
        
        IF LESSON_TYPE == PRACTICE:
        - Analyze the learner’s approach
        - Identify conceptual mistakes
        - Ask guiding questions
        - DO NOT provide full solutions unless explicitly requested
        
        IF LESSON_TYPE == QUIZ:
        - NEVER reveal the correct answer
        - Explain why a choice might be incorrect
        - Encourage reasoning
        
        🪜 HINT ESCALATION LEVLES
        LEVEL 1: Gentle hint, conceptual guidance, no code.
        LEVEL 2: Deeper explanation, pseudocode.
        LEVEL 3: Full solution (ONLY if explicitly requested).
        
        ✨ BEHAVIOR
        - Behave like Gemini inside Google Docs (inline suggestions).
        - Suggest improvements without overwriting intent.
        
        🧠 PEDAGOGICAL RULES
        - Do NOT introduce concepts not yet taught.
        - Do NOT assume prior knowledge.
        - Encourage thinking, not copying.
        
        🚫 ABSOLUTE RESTRICTIONS
        - Do NOT hallucinate APIs.
        - Do NOT say "as an AI model".
        - Do NOT generate full solutions unless asked.
        
        🧑‍🏫 TONE
        - Calm, Patient, Encouraging, Human-like.
    """.trimIndent()

    val LESSON_PROMPTS = mapOf(
        // SECTION 1: CS FOUNDATIONS
        "kt-cs-1" to """
            Teach Lesson 1: What is Computer Science.
            Explain:
            - What computer science means
            - Problem-solving mindset
            - Real-life examples
            Rules:
            - Beginner friendly
            - No jargon
            - No heavy code
            End with:
            - One simple question for the student
        """.trimIndent(),

        "kt-cs-2" to """
            Teach Lesson 2: How computers think using binary.
            Explain:
            - Bits and bytes
            - Why computers use 0 and 1
            - Simple binary example
            End with:
            - Ask student to convert a small binary number
        """.trimIndent(),

        "kt-cs-3" to """
            Teach Lesson 3: Data representation.
            Explain:
            - How numbers, text, images, sound are stored
            - ASCII and Unicode (concept only)
            No code.
            End with:
            - One multiple-choice style question
        """.trimIndent(),

        "kt-cs-4" to """
            Teach Lesson 4: Algorithms.
            Explain:
            - Step-by-step thinking
            - Daily life algorithm examples
            Include:
            - Very simple Kotlin function example
            End with:
            - Ask student to describe an algorithm in words
        """.trimIndent(),

        "kt-cs-5" to """
            Teach Lesson 5: Time complexity (Big-O).
            Explain:
            - Fast vs slow solutions
            - Why efficiency matters
            No formulas.
            End with:
            - Ask which is faster: one loop or nested loops
        """.trimIndent(),

        // SECTION 2: KOTLIN BASICS
        "kt-cs-6" to """
            Teach Lesson 6: Kotlin program structure.
            Explain:
            - main() function
            - How programs start
            Include:
            - Simple Kotlin main example
            End with:
            - Ask student to print a message
        """.trimIndent(),

        "kt-cs-7" to """
            Teach Lesson 7: Variables and data types.
            Explain:
            - val vs var
            - Int, String, Boolean
            Include:
            - Simple variable example
            End with:
            - Ask student to store age in a variable
        """.trimIndent(),
        
        "kt-cs-8" to """
            Teach Lesson 8: Operators in Kotlin.
            Explain:
            - Arithmetic
            - Comparison
            - Logical operators
            End with:
            - Ask student to check if a number is even
        """.trimIndent(),

        "kt-cs-9" to """
            Teach Lesson 9: if/else conditions.
            Explain:
            - Decision making in programs
            Include:
            - Simple if/else Kotlin example
            End with:
            - Ask student to check pass/fail
        """.trimIndent(),

        "kt-cs-10" to """
            Teach Lesson 10: Loops.
            Explain:
            - for loop
            - while loop
            - repeat
            End with:
            - Ask student to print numbers 1 to 10
        """.trimIndent(),

        "kt-cs-11" to """
            Teach Lesson 11: Functions.
            Explain:
            - Why functions exist
            - Parameters and return values
            Include:
            - Simple sum function
            End with:
            - Ask student to write a max function
        """.trimIndent(),

        "kt-cs-12" to """
            Teach Lesson 12: Debugging.
            Explain:
            - Common mistakes
            - How to find bugs
            - Print debugging
            End with:
            - Ask student how to find an error
        """.trimIndent(),

        // SECTION 3: DATA STRUCTURES & ALGORITHMS
        "kt-cs-13" to """
            Teach Lesson 13: Arrays.
            Explain:
            - Indexing
            - Traversal
            End with:
            - Ask student to find the largest number
        """.trimIndent(),

        "kt-cs-14" to """
            Teach Lesson 14: Lists (MutableList).
            Explain:
            - Add and remove elements
            - Difference from arrays
            End with:
            - Ask student to remove duplicates
        """.trimIndent(),

        "kt-cs-15" to """
            Teach Lesson 15: Strings.
            Explain:
            - Characters
            - Length
            - String operations
            End with:
            - Ask student to reverse a string
        """.trimIndent(),

        "kt-cs-16" to """
            Teach Lesson 16: Searching algorithms.
            Explain:
            - Linear search
            - Binary search (concept only)
            End with:
            - Ask student to find a number in array
        """.trimIndent(),

        "kt-cs-17" to """
            Teach Lesson 17: Sorting.
            Explain:
            - Why sorting is needed
            - Bubble sort idea
            End with:
            - Ask student to sort numbers
        """.trimIndent(),

        "kt-cs-18" to """
            Teach Lesson 18: Recursion.
            Explain:
            - Function calling itself
            - Base case importance
            End with:
            - Ask student to calculate factorial
        """.trimIndent(),

        "kt-cs-19" to """
            Teach Lesson 19: HashMap.
            Explain:
            - Key-value pairs
            - Real-life examples
            End with:
            - Ask student to count frequencies
        """.trimIndent(),

        "kt-cs-20" to """
            Teach Lesson 20: Stack and Queue.
            Explain:
            - LIFO and FIFO
            - Real-life examples
            End with:
            - Ask student to validate parentheses
        """.trimIndent(),

        "kt-cs-21" to """
            Teach Lesson 21: Problem-solving patterns.
            Explain:
            - Two pointers
            - Counting patterns
            End with:
            - Ask which pattern to use for duplicates
        """.trimIndent(),

        "kt-cs-22" to """
            Teach Lesson 22: Algorithm thinking.
            Explain:
            - Breaking problems
            - Edge cases
            - Optimization
            End with:
            - Ask student how to approach a problem
        """.trimIndent(),

        // SECTION 4: REAL CS + PROJECT
        "kt-cs-23" to """
            Teach Lesson 23: Memory basics.
            Explain:
            - Stack vs heap
            - Variables and memory
            No code.
        """.trimIndent(),

        "kt-cs-24" to """
            Teach Lesson 24: Files and data.
            Explain:
            - Reading and writing data
            - Why files matter
        """.trimIndent(),

        "kt-cs-25" to """
            Teach Lesson 25: Databases.
            Explain:
            - Tables
            - Rows and columns
            - SQL concept
        """.trimIndent(),

        "kt-cs-26" to """
            Teach Lesson 26: Security basics.
            Explain:
            - Passwords
            - Hashing concept
            - Why plaintext is bad
        """.trimIndent(),

        "kt-cs-27" to """
            Teach Lesson 27: How apps work.
            Explain:
            - Client
            - Server
            - API
        """.trimIndent(),

        "kt-cs-28" to """
            Teach Lesson 28: Clean code.
            Explain:
            - Naming
            - Readability
            - Refactoring
        """.trimIndent(),

        "kt-cs-29" to """
            Teach Lesson 29: Git basics.
            Explain:
            - Version control
            - Commits
            - Collaboration
        """.trimIndent(),

        "kt-cs-30" to """
            Teach Lesson 30: Final project.
            Explain:
            - Project ideas
            - How to plan
            - How to apply everything learned
            Encourage the student strongly.
        """.trimIndent()
    )
}
