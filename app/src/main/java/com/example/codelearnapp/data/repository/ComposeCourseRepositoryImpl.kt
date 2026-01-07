package com.example.codelearnapp.data.repository

import com.example.codelearnapp.domain.model.Lesson
import com.example.codelearnapp.domain.model.LessonType

object ComposeCourseRepositoryImpl {
    val composeLessons = listOf(
        // PART 1 — Foundations (Lessons 1–6)
        Lesson(
            id = "compose-1",
            courseId = "jetpack-compose",
            title = "What is Jetpack Compose?",
            content = "Jetpack Compose is Android's modern toolkit for building native UI without XML. It uses a declarative API, meaning you describe what your UI should look like for a given state, and Compose handles the updates.",
            type = LessonType.THEORY,
            order = 1,
            isCompleted = false,
            codeExample = """
                // Imperative (XML-style) logic:
                // textView.setText("Hello")
                // textView.setColor(Color.RED)

                // Declarative (Compose) logic:
                // Text(text = "Hello", color = Color.Red)
            """.trimIndent()
        ),
        Lesson(
            id = "compose-2",
            courseId = "jetpack-compose",
            title = "Composable Functions",
            content = "A Composable is a regular Kotlin function annotated with @Composable. It can emit UI elements. Composables can call other composables to build complex hierarchies.",
            type = LessonType.THEORY,
            order = 2,
            isCompleted = false,
            codeExample = """
                import androidx.compose.runtime.Composable
                import androidx.compose.material3.Text

                @Composable
                fun Greeting(name: String) {
                    Text(text = "Hello, ${'$'}name!")
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-3",
            courseId = "jetpack-compose",
            title = "Basic UI Elements",
            content = "Compose provides building blocks like Text and Button. @Preview lets you see your layout in Android Studio without running the app. Try changing the text below.",
            type = LessonType.CODE_PRACTICE,
            order = 3,
            isCompleted = false,
            codeExample = """
                import androidx.compose.material3.Text
                import androidx.compose.material3.Button
                import androidx.compose.runtime.Composable

                @Composable
                fun WelcomeScreen() {
                    Button(onClick = { /* Do something */ }) {
                         Text(text = "Click Me")
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-4",
            courseId = "jetpack-compose",
            title = "Layout Basics: Column & Row",
            content = "To arrange elements vertically, use Column. For horizontal arrangement, use Row. Box stacks elements on top of each other.",
            type = LessonType.THEORY,
            order = 4,
            isCompleted = false,
            codeExample = """
                @Composable
                fun MyLayout() {
                    Column {
                        Text("Top Item")
                        Row {
                            Text("Left")
                            Text("Right")
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-5",
            courseId = "jetpack-compose",
            title = "Modifiers",
            content = "Modifiers allow you to decorate or configure a composable. You can change size, background, padding, and make elements clickable. Order matters!",
            type = LessonType.CODE_PRACTICE,
            order = 5,
            isCompleted = false,
            codeExample = """
                import androidx.compose.foundation.background
                import androidx.compose.foundation.layout.padding
                import androidx.compose.ui.Modifier
                import androidx.compose.ui.graphics.Color
                import androidx.compose.ui.unit.dp

                @Composable
                fun StyledText() {
                    Text(
                        text = "Hello with Padding",
                        modifier = Modifier
                            .background(Color.Yellow)
                            .padding(16.dp)
                    )
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-6",
            courseId = "jetpack-compose",
            title = "Practice: Profile Card",
            content = "Build a simple Profile Card layout using Row, Column, and an Image placeholder (using Box or simple Text for now).",
            type = LessonType.CODE_PRACTICE,
            order = 6,
            isCompleted = false,
            codeExample = """
                @Composable
                fun ProfileCard() {
                    Row(modifier = Modifier.padding(8.dp)) {
                        // Image placeholder
                        Text("📷", modifier = Modifier.padding(end = 8.dp))
                        
                        Column {
                            Text("User Name")
                            Text("Android Developer")
                        }
                    }
                }
            """.trimIndent()
        ),

        // PART 2 — State & Recomposition (Lessons 7–12)
        Lesson(
            id = "compose-7",
            courseId = "jetpack-compose",
            title = "What is State?",
            content = "In Compose, UI is declarative and immutable. You don't update widgets; you update state, and the UI redraws ('recomposes') automatically. UI = f(State).",
            type = LessonType.THEORY,
            order = 7,
            isCompleted = false,
            codeExample = """
                // State is simply data that changes over time.
                // When 'count' changes, any UI reading it will update.
                data class CounterState(val count: Int)
            """.trimIndent()
        ),
        Lesson(
            id = "compose-8",
            courseId = "jetpack-compose",
            title = "remember & mutableStateOf",
            content = "To store state inside a Composable, use 'remember'. To make it observable (trigger updates), use 'mutableStateOf'. Without 'remember', state resets on every recomposition.",
            type = LessonType.CODE_PRACTICE,
            order = 8,
            isCompleted = false,
            codeExample = """
                import androidx.compose.runtime.*

                @Composable
                fun Counter() {
                    // 'by' requires: import androidx.compose.runtime.getValue / setValue
                    var count by remember { mutableStateOf(0) }

                    Button(onClick = { count++ }) {
                        Text("Count is ${'$'}count")
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-9",
            courseId = "jetpack-compose",
            title = "State Hoisting",
            content = "Pattern: Move state up to a parent composable to make the child stateless and reusable. Pass state down as parameters and events up as lambdas.",
            type = LessonType.THEORY,
            order = 9,
            isCompleted = false,
            codeExample = """
                @Composable
                fun CounterScreen() {
                    var count by remember { mutableStateOf(0) }
                    CounterButton(
                        count = count, 
                        onIncrement = { count++ }
                    )
                }

                @Composable
                fun CounterButton(count: Int, onIncrement: () -> Unit) {
                    Button(onClick = onIncrement) {
                        Text("Count: ${'$'}count")
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-10",
            courseId = "jetpack-compose",
            title = "Recomposition",
            content = "Recomposition is the process of calling your composable functions again with new data. Compose intelligently skips parts of the UI that haven't changed.",
            type = LessonType.THEORY,
            order = 10,
            isCompleted = false,
            codeExample = """
                @Composable
                fun EfficientList(names: List<String>) {
                    Column {
                        // This prints every time the Column recomposes
                        println("Column Recomposing")
                        
                        for (name in names) {
                            Text(name) // Only changed items recompose ideally
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-11",
            courseId = "jetpack-compose",
            title = "Common State Mistakes",
            content = "Creating state without 'remember' causes resets. Modifying state during the composition phase leads to infinite loops or crashes.",
            type = LessonType.THEORY,
            order = 11,
            isCompleted = false,
            codeExample = """
                @Composable
                fun BrokenCounter() {
                    // WRONG: Resets to 0 every time the function runs
                    var count = mutableStateOf(0) 

                    Button(onClick = { count.value++ }) {
                        Text("${'$'}{count.value}")
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-12",
            courseId = "jetpack-compose",
            title = "Practice: Counter & Toggle",
            content = "Build a UI with a counter and a separate toggle switch. Hoist the state so they share data or live in the same parent.",
            type = LessonType.CODE_PRACTICE,
            order = 12,
            isCompleted = false,
            codeExample = """
                @Composable
                fun CounterAndToggle() {
                    var count by remember { mutableStateOf(0) }
                    var isEnabled by remember { mutableStateOf(false) }
                    
                    Column {
                        if (isEnabled) {
                            Button(onClick = { count++ }) { Text("Count: ${'$'}count") }
                        }
                        Button(onClick = { isEnabled = !isEnabled }) {
                            Text(if (isEnabled) "Disable" else "Enable")
                        }
                    }
                }
            """.trimIndent()
        ),

        // PART 3 — UI & Styling (Lessons 13–18)
        Lesson(
            id = "compose-13",
            courseId = "jetpack-compose",
            title = "Material 3 Basics",
            content = "Material 3 is the standard design system. Use 'MaterialTheme' to wrap your app and components like 'Scaffold' for standard layouts.",
            type = LessonType.THEORY,
            order = 13,
            isCompleted = false,
            codeExample = """
                @Composable
                fun MyApp() {
                    MaterialTheme {
                        Scaffold(
                            floatingActionButton = { 
                                FloatingActionButton(onClick = {}) { Text("+") }
                            }
                        ) { padding ->
                            Text("Content", modifier = Modifier.padding(padding))
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-14",
            courseId = "jetpack-compose",
            title = "Theming & Colors",
            content = "Customize your app's look using ColorScheme. Support dark mode automatically by defining standard color tokens.",
            type = LessonType.THEORY,
            order = 14,
            isCompleted = false,
            codeExample = """
                val MyColors = lightColorScheme(
                    primary = Color.Blue,
                    onPrimary = Color.White
                )

                @Composable
                fun ThemedApp() {
                    MaterialTheme(colorScheme = MyColors) {
                        Button(onClick = {}) { Text("Primary Color Button") }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-15",
            courseId = "jetpack-compose",
            title = "Typography & Shapes",
            content = "Define your text styles (h1, bodyLarge) and component shapes (RoundedCornerShape) globally in your theme.",
            type = LessonType.CODE_PRACTICE,
            order = 15,
            isCompleted = false,
            codeExample = """
                Text(
                    text = "Headline",
                    style = MaterialTheme.typography.headlineLarge
                )
                
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Rounded Button")
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-16",
            courseId = "jetpack-compose",
            title = "Interactions",
            content = "Handle clicks, gestures, and ripples. Modifiers like .clickable make any element interactive. Use interactionSource for advanced states (pressed, hovered).",
            type = LessonType.THEORY,
            order = 16,
            isCompleted = false,
            codeExample = """
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Gray)
                        .clickable { println("Clicked!") }
                ) {
                    Text("Click Me", Modifier.align(Alignment.Center))
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-17",
            courseId = "jetpack-compose",
            title = "Lists: LazyColumn",
            content = "For long lists, avoid Column (which renders everything). Use LazyColumn to render only visible items vertically. Equivalent to RecyclerView.",
            type = LessonType.THEORY,
            order = 17,
            isCompleted = false,
            codeExample = """
                @Composable
                fun NameList(names: List<String>) {
                    LazyColumn {
                        items(names) { name ->
                            Text(text = name, modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-18",
            courseId = "jetpack-compose",
            title = "Practice: Settings Screen",
            content = "Create a scrollable settings screen using LazyColumn with different item types (headers, switches, specific options).",
            type = LessonType.CODE_PRACTICE,
            order = 18,
            isCompleted = false,
            codeExample = """
                @Composable
                fun SettingsScreen() {
                    LazyColumn {
                        item { Text("General", style = MaterialTheme.typography.titleLarge) }
                        items(3) { index ->
                            Text("Option ${'$'}index", Modifier.padding(16.dp))
                        }
                    }
                }
            """.trimIndent()
        ),

        // PART 4 — Real App Patterns (Lessons 19–24)
        Lesson(
            id = "compose-19",
            courseId = "jetpack-compose",
            title = "Navigation in Compose",
            content = "Use the Navigation component. Define a NavHost with composable destinations and a NavController to move between them.",
            type = LessonType.THEORY,
            order = 19,
            isCompleted = false,
            codeExample = """
                // val navController = rememberNavController()
                // NavHost(navController, startDestination = "home") {
                //     composable("home") { HomeScreen(navController) }
                //     composable("details") { DetailScreen() }
                // }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-20",
            courseId = "jetpack-compose",
            title = "Handling UI Events",
            content = "Events (clicks, inputs) should bubble up from UI to ViewModel. Use lambdas to pass events up. Maintain Unidirectional Data Flow.",
            type = LessonType.THEORY,
            order = 20,
            isCompleted = false,
            codeExample = """
                @Composable
                fun LoginButton(onClick: () -> Unit) {
                    Button(onClick = onClick) {
                        Text("Log In")
                    }
                }
                // Parent calls: LoginButton(onClick = { viewModel.login() })
            """.trimIndent()
        ),
        Lesson(
            id = "compose-21",
            courseId = "jetpack-compose",
            title = "ViewModel with Compose",
            content = "Access ViewModels using the viewModel() function. Observe state from the ViewModel to drive the UI.",
            type = LessonType.THEORY,
            order = 21,
            isCompleted = false,
            codeExample = """
                // class MyViewModel : ViewModel() { ... }
                
                @Composable
                fun Screen(viewModel: MyViewModel = viewModel()) {
                    // UI Logic here
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-22",
            courseId = "jetpack-compose",
            title = "StateFlow & Compose",
            content = "Use collectAsState() (or collectAsStateWithLifecycle) to convert StateFlow/LiveData from ViewModel into Compose State.",
            type = LessonType.CODE_PRACTICE,
            order = 22,
            isCompleted = false,
            codeExample = """
                @Composable
                fun UserScreen(viewModel: UserViewModel) {
                    // Converts Flow<User> to State<User>
                    val user by viewModel.userState.collectAsState()
                    
                    Text("User: ${'$'}{user.name}")
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-23",
            courseId = "jetpack-compose",
            title = "Side Effects",
            content = "Composables should be side-effect free. Use LaunchedEffect to run suspend functions (like API calls or navigation) safely when composition occurs.",
            type = LessonType.THEORY,
            order = 23,
            isCompleted = false,
            codeExample = """
                @Composable
                fun Loader(onTimeout: () -> Unit) {
                    // Runs once when entering composition
                    LaunchedEffect(Unit) {
                        delay(3000)
                        onTimeout()
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-24",
            courseId = "jetpack-compose",
            title = "Practice: Login Screen",
            content = "Combine everything: TextFields for input, Button for action, ViewModel for state/validation, and LaunchedEffect for navigation upon success.",
            type = LessonType.CODE_PRACTICE,
            order = 24,
            isCompleted = false,
            codeExample = """
                @Composable
                fun LoginScreen(viewModel: LoginViewModel) {
                    val state by viewModel.state.collectAsState()
                    
                    Column {
                         TextField(value = state.email, onValueChange = viewModel::onEmailChange)
                         Button(onClick = viewModel::login) { Text("Login") }
                    }
                }
            """.trimIndent()
        ),

        // PART 5 — Professional Level (Lessons 25–30)
        Lesson(
            id = "compose-25",
            courseId = "jetpack-compose",
            title = "Performance Optimization",
            content = "Avoid expensive calculations in the composition body. Use 'derivedStateOf' and ensure your data classes are 'Stable' to enable smart skipping.",
            type = LessonType.THEORY,
            order = 25,
            isCompleted = false,
            codeExample = """
                val listState = rememberLazyListState()
                // Only recomposes when the boolean changes, not on every scroll offset change
                val showButton by remember { 
                    derivedStateOf { listState.firstVisibleItemIndex > 0 } 
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-26",
            courseId = "jetpack-compose",
            title = "Debugging Compose",
            content = "Use the Layout Inspector in Android Studio 3.0+ to inspect the composition tree and semantic information. Look for high recomposition counts.",
            type = LessonType.THEORY,
            order = 26,
            isCompleted = false,
            codeExample = """
                // Debugging tip:
                // Log inside composable to see when it runs
                SideEffect { 
                    Log.d("Compose", "Recomposing MyComponent") 
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-27",
            courseId = "jetpack-compose",
            title = "Reusable Components (Slots)",
            content = "Build flexible components using the Slot API pattern. Accept composable lambdas (content: @Composable () -> Unit) instead of hardcoding children.",
            type = LessonType.CODE_PRACTICE,
            order = 27,
            isCompleted = false,
            codeExample = """
                @Composable
                fun CustomCard(
                    title: String,
                    content: @Composable () -> Unit // Slot
                ) {
                    Column {
                        Text(title, style = MaterialTheme.typography.titleMedium)
                        content() // Render the passed child composables
                    }
                }
            """.trimIndent()
        ),
        Lesson(
            id = "compose-28",
            courseId = "jetpack-compose",
            title = "UI Testing Basics",
            content = "Use composeTestRule to write UI tests. Select nodes via semantics (onNodeWithText) and perform actions (performClick) or assertions (assertIsDisplayed).",
            type = LessonType.THEORY,
            order = 28,
            isCompleted = false,
            codeExample = """
                // @get:Rule val composeTestRule = createComposeRule()
                // composeTestRule.onNodeWithText("Button").performClick()
                // composeTestRule.onNodeWithText("Success").assertIsDisplayed()
            """.trimIndent()
        ),
        Lesson(
            id = "compose-29",
            courseId = "jetpack-compose",
            title = "Architecture Best Practices",
            content = "Keep composables focusing on UI. Keep business logic in ViewModels. Use Repositories for data. Follow 'UI Layer' guide in official docs.",
            type = LessonType.THEORY,
            order = 29,
            isCompleted = false,
            codeExample = """
                // Project Structure:
                // - data/ (Repository, API)
                // - domain/ (UseCase, Model)
                // - ui/ (Composables, ViewModels, Themes)
            """.trimIndent()
        ),
        Lesson(
            id = "compose-30",
            courseId = "jetpack-compose",
            title = "Final Project: Real App Screen",
            content = "Build a professional Dashboard screen. It must include a list, proper theming, navigation, and robust state management. This is your graduation test!",
            type = LessonType.CHALLENGE,
            order = 30,
            isCompleted = false,
            codeExample = """
                // Challenge requirements:
                // 1. Fetch data (simulated)
                // 2. Show loading state
                // 3. Display list of items
                // 4. Handle item clicks
            """.trimIndent()
        )
    )
}
