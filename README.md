# Android App Folder Structure & Dependency Selection

🎯 Goal of This Project

This repository demonstrates senior-level Jetpack Compose practices that interviewers explicitly look for when evaluating Android developers with strong Compose fundamentals.

- What this project showcases
- Proper state hoisting
- Single UI state per screen
- Lifecycle-aware state collection
- Controlled recomposition
- Clean, Compose-centric folder structure
- Preview-driven development

---

📂  Project Structure (Compose-Centric)

```text
app/
 └── ui/
     ├── components/
     │   └── PrimaryButton.kt
     ├── screens/
     │   └── CounterScreen.kt
     ├── state/
     │   └── CounterUiState.kt
     ├── theme/
     │   ├── Color.kt
     │   ├── Theme.kt
     │   └── Type.kt
     └── MainActivity.kt
```

### Why this structure?

- components/ → reusable, stateless composables
- screens/ → screen-level composables only
- state/ → explicit UI state models
- theme/ → design system ownership

### This structure intentionally avoids

- ❌ Business logic inside UI
- ❌ State scattered across composables
- ❌ Tight coupling between UI and logic

This separation is critical for scalability and testability.

### 🧩 UI State Model (Single Source of Truth)

data class CounterUiState(

    val count: Int = 0,
    val isEven: Boolean = true
)
### Why this matters

- One state object → predictable UI behavior
- Easy to test
- No hidden recomposition triggers

#### ViewModel (State Holder, Not UI Logic)

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

### Key Points
- ViewModel owns state
- UI is passive
- No Compose imports inside ViewModel
- This separation is non-negotiable at senior level.

### Stateless Screen (Compose Best Practice)
### @Composable

fun CounterScreen(uiState: CounterUiState,onIncrement: () -> Unit) {

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

### Why this is correct

- No remember
- No mutableStateOf
- Fully driven by parameters
- Easy to preview & test
- This is textbook Compose design.

### Reusable Component (Stateless by Design)

@Composable

fun PrimaryButton(

    text: String,
    onClick: () -> Unit
) {

    Button(onClick = onClick) {
        Text(text)
    }
}

- Senior Signal
- No internal state
- No side effects
- Reusable across screens

### Lifecycle-Aware State Collection

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

### Why collectAsStateWithLifecycle

- Prevents memory leaks
- Stops collection in background
- Mandatory for production Compose

### Why previews matter

- Faster UI iteration
- No emulator dependency
- Shows Compose fluency





