# 🚀 Android App Folder Structure & Dependency Selection

## 🎯 Goal of This Project

This repository demonstrates senior-level Jetpack Compose practices that interviewers explicitly look for when evaluating Android developers with strong Compose fundamentals.

### ✨ What this project showcases:

- ✅ Proper state hoisting
- ✅ Single UI state per screen
- ✅ Lifecycle-aware state collection
- ✅ Controlled recomposition
- ✅ Clean, Compose-centric folder structure
- ✅ Preview-driven development

---

## 📂 Project Structure (Compose-Centric)

```
app/
└── ui/
    ├── components/ -> Reusable, stateless composables
    │   └── PrimaryButton.kt
    ├── screens/ -> Screen-level composables only
    │   └── CounterScreen.kt
    ├── state/ -> Explicit UI state models
    │   └── CounterUiState.kt
    ├── theme/ -> Design system ownership
    │   ├── Color.kt
    │   ├── Theme.kt
    │   └── Type.kt
    └── MainActivity.kt
```

### ❓ Why this structure?

- **`components/`**: Reusable, stateless composables that can be used across multiple screens.
- **`screens/`**: Screen-level composables that are responsible for the overall layout of a single screen.
- **`state/`**: Explicit UI state models that represent the state of a screen.
- **`theme/`**: Design system ownership, including colors, typography, and shapes.

### ❌ This structure intentionally avoids:

- Business logic inside UI
- State scattered across composables
- Tight coupling between UI and logic

> This separation is critical for scalability and testability.

## 🧩 UI State Model (Single Source of Truth)

```kotlin
data class CounterUiState(
    val count: Int = 0,
    val isEven: Boolean = true
)
```

### 🤔 Why this matters:

- **One state object**: Predictable UI behavior and easier to test.
- **No hidden recomposition triggers**: The UI is only recomposed when the state changes.

## 🤖 ViewModel (State Holder, Not UI Logic)

```kotlin
class CounterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState

    fun increment() {
        val newCount = _uiState.value.count + 1
        _uiState.value = CounterUiState(
            count = newCount,
            isEven = newCount % 2 == 0
        )
    }

}
```

### 🔑 Key Points:

- The ViewModel owns the state.
- The UI is passive and only observes the state.
- No Compose imports inside the ViewModel.

> This separation is non-negotiable at a senior level.

## 📱 Stateless Screen (Compose Best Practice)

```kotlin
@Composable
fun CounterScreen(uiState: CounterUiState, onIncrement: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Count: ${uiState.count}",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = if (uiState.isEven) "Even Number" else "Odd Number",
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Increment",
            onClick = onIncrement
        )
    }
}
```

### 👍 Why this is correct:

- No `remember` or `mutableStateOf`.
- Fully driven by parameters, making it easy to preview and test.

> This is textbook Compose design.

## 🧱 Reusable Component (Stateless by Design)

```kotlin
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text)
    }
}
```

### 🌟 Senior Signal:

- No internal state or side effects.
- Reusable across multiple screens.

## 🔄 Lifecycle-Aware State Collection

```kotlin
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: CounterViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            CounterScreen(
                uiState = uiState,
                onIncrement = viewModel::increment
            )
        }
    }
}
```

### ⚡️ Why `collectAsStateWithLifecycle`?

- Prevents memory leaks by stopping collection in the background.
- Mandatory for production-ready Compose apps.

### 🖼️ Why previews matter:

- Faster UI iteration and no emulator dependency.
- Shows Compose fluency.
