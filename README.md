# CodeLearn - Complete Mimo-Style Learning App 📚

A production-ready Android app with **150 interactive programming lessons** across **5 courses** (Python, Kotlin, Java, JavaScript, SQL).
Built with **Jetpack Compose**, **Clean Architecture**, and **MVI pattern**.

---

## 📱 Features

### 🎯 5 Complete Courses - 150 Lessons Total

* **Python Basics (30 lessons)** – Variables, data structures, OOP, file I/O
* **Kotlin for Android (30 lessons)** – Kotlin fundamentals, coroutines, Android development
* **Java Programming (30 lessons)** – Core Java, OOP, collections, streams
* **Web Development (30 lessons)** – HTML, CSS, JavaScript, APIs, async programming
* **SQL Mastery (30 lessons)** – Queries, joins, transactions, database design

### ✨ Interactive Learning

* 📖 Theory lessons with clear explanations
* 💻 Code examples with syntax highlighting
* ❓ Interactive quizzes with instant feedback
* ✍️ Practice exercises for hands-on learning
* 🏆 Progress tracking and completion status

---

## 🎗e Architecture

### Clean Architecture (3 Layers)

```
Presentation (UI + ViewModels)
↓
Domain (Use Cases + Models + Repository Interfaces)
↓
Data (Repository Implementations + Data Sources)
```

### MVI Pattern

```
User Action (Intent)
↓
ViewModel.handleIntent()
↓
Update State
↓
UI Recomposes
↓
[Optional] Side Effect (Navigation, Toast)
```

---

## 📦 Tech Stack

| Technology         | Version | Purpose              |
| ------------------ | ------- | -------------------- |
| Kotlin             | 1.9.22  | Programming language |
| Jetpack Compose    | 1.6.0   | Declarative UI       |
| Material 3         | Latest  | Design system        |
| Koin               | 3.5.3   | Dependency injection |
| Kotlin Flow        | 1.7.3   | Reactive streams     |
| Navigation Compose | 2.7.6   | Screen navigation    |
| Coroutines         | 1.7.3   | Async operations     |

---

## 📁 Project Structure

```
app/src/main/java/com/example/codelearnapp/
├── CodeLearnApp.kt        # Application class
├── MainActivity.kt        # Main entry point
├── domain/                # Business logic layer
│   ├── model/             # Data models
│   ├── repository/        # Repository interfaces
│   └── usecase/           # Use cases
├── data/                  # Data layer (repository implementations)
├── presentation/          # UI layer
│   ├── mvi/               # MVI base files
│   ├── home/              # Home screen
│   ├── coursedetail/      # Course detail screen
│   ├── lesson/            # Lesson screen
│   └── navigation/        # NavGraph
├── di/                    # Dependency injection (Koin)
└── ui/theme/              # Theme & styles
```

---

## 🚀 Setup Instructions

### Prerequisites

* **Android Studio Hedgehog (2023.1.1+)**
* **JDK 17**
* **Android SDK 34**
* **Minimum SDK 24 (Android 7.0+)**

### Steps

1. Clone the repository:

```bash
git clone https://github.com/yourusername/CodeLearnApp.git
```

2. Open in Android Studio
3. Sync Gradle:

```bash
./gradlew sync
```

4. Build project:

```bash
./gradlew clean build
```

5. Install & run on device/emulator:

```bash
./gradlew installDebug
```

---

## 🎮 How to Use

### Navigation Flow

```
Home Screen
↓ (tap course)
Course Detail Screen (list of lessons)
↓ (tap lesson)
Lesson Screen (learn & complete)
↓ (complete)
Back to Course Detail (progress updated)
```

### Learning Flow

* Browse Courses → Select Course → Take Lesson → Complete → Track Progress

---

## 📚 Course Content Summary

### Python Basics (30 Lessons)

* Variables, strings, numbers
* Lists, dictionaries, sets
* Control flow: if/else, loops
* Functions and lambdas
* OOP: classes, inheritance
* File I/O & exception handling
* Modules and list comprehensions

### Kotlin for Android (30 Lessons)

* Basics: val/var, null safety, types
* Functions & `when` expressions
* Collections, OOP, inheritance
* Lambdas, higher-order functions
* Extension & scope functions
* Coroutines and Android integration

### Java Programming (30 Lessons)

* Variables, operators, data types
* Control flow: if/else, switch, loops
* Methods, overloading
* Arrays & collections (ArrayList, HashMap)
* Classes, inheritance, interfaces
* Exception handling, file I/O

### Web Development (30 Lessons)

* JavaScript basics, functions
* Arrays, objects, DOM manipulation
* Async programming: callbacks, promises, async/await
* Classes & modules, Fetch API, local storage

### SQL Mastery (30 Lessons)

* SELECT, WHERE, ORDER BY
* INSERT, UPDATE, DELETE
* Aggregates, GROUP BY, JOINs
* Subqueries, CREATE/ALTER/DROP tables
* Indexes, views, transactions, normalization

---

## 🎨 Customization

### Adding New Lessons

Edit `CourseRepositoryImpl.kt`:

```kotlin
Lesson(
    id = "new-lesson-id",
    courseId = "python-basics",
    title = "Lesson Title",
    content = "Lesson explanation...",
    type = LessonType.THEORY, // or QUIZ, CODE_PRACTICE
    order = 31,
    isCompleted = false,
    codeExample = """
        # Your code example
        print("Hello!")
    """.trimIndent(),
    quiz = Quiz(...) // optional
)
```

### Adding New Courses

* Add to `getMockCourses()` in `CourseRepositoryImpl.kt`
* Create a lesson generator function (e.g., `getRubyLessons()`)
* Add to `getMockLessons()` return statement

### Changing Theme Colors

Edit `ui/theme/Theme.kt`:

```kotlin
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFYourColor),
    ...
)
```

---

## 🐛 Troubleshooting

### Gradle Sync Failed

```bash
./gradlew clean
File > Invalidate Caches > Restart
```

### Koin Injection Errors

* Verify `CodeLearnApp` in `AndroidManifest`
* Check all ViewModels in `AppModule.kt`
* Ensure Koin version 3.5.3

### Compose Preview Not Working

* Build > Clean Project > Rebuild > Invalidate caches

### Navigation Issues

* Ensure route strings match in `NavGraph`
* Pass correct arguments, verify `Screen.createRoute()`

---

## 📊 Code Statistics

* **Total Files:** 25+
* **Lines of Code:** ~3,000+
* **Courses:** 5
* **Lessons:** 150 (30 per course)
* **Quiz Questions:** 25+
* **Code Examples:** 150+
* **ViewModels:** 3
* **Use Cases:** 5

---

## 🚀 Future Enhancements

* Room Database - Persist progress
* User Authentication (Firebase)
* Search Functionality
* Bookmarks & Favorites
* Achievements & Leaderboards
* Code Execution in Practice Exercises
* Video Lessons & Offline Mode
* Dark Mode Toggle
* Multi-language Support

---

## 🤝 Contributing

Contributions are welcome!

1. Fork repository
2. Create feature branch:

```bash
git checkout -b feature/AmazingFeature
```

3. Commit changes:

```bash
git commit -m "Add AmazingFeature"
```

4. Push branch:

```bash
git push origin feature/AmazingFeature
```

5. Open Pull Request

---

## 📄 License

This project is for **educational purposes**. Feel free to use and modify.

---

## 🙏 Acknowledgments

* Mimo – Inspiration for interactive learning
* JetBrains – Kotlin & Android tools
* Google – Jetpack Compose & Android development
* Koin – Dependency Injection library

---

## 📧 Support

* Check this README
* Review code comments
* Create GitHub issue if needed

---

## 🎓 Learning Path

### Beginner Path

* Python Basics → Kotlin for Android → SQL Mastery

### Web Developer Path

* Web Development → SQL Mastery → Python Basics

### Android Developer Path

* Kotlin for Android → Java Programming → SQL Mastery

---

## 🌟 Quick Start Commands

```bash
# Clone/Download project
cd CodeLearnApp

# Open in Android Studio
studio .

# Build from command line
./gradlew clean build

# Install on device
./gradlew installDebug

# Run tests
./gradlew test
```

**Built with ❤️ for aspiring developers**
**Happy Learning! 🚀📱**
