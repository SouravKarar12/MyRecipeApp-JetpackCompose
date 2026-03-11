🍽️ My Recipe App (Jetpack Compose) :-
A modern Android Recipe Application built using Kotlin, Jetpack Compose, MVVM Architecture, Retrofit, and StateFlow.
The app fetches recipe data from an API and displays it in a beautiful grid layout, allowing users to explore recipe details and cooking instructions.

📱 Features :-
🧾 Display recipes in a LazyVerticalGrid
🖼️ Show recipe images using Glide
📄 View recipe details
🥗 View ingredients list
👨‍🍳 Step-by-step cooking instructions
⏳ Loading state with circular progress indicator
❌ Error handling
🔄 Reactive UI using StateFlow
🧭 Navigation between screens

🏗️ Architecture:-
The project follows MVVM (Model-View-ViewModel) architecture.
UI (Compose Screens)
        ↓
ViewModel (StateFlow)
        ↓
Repository
        ↓
Retrofit API

Components:-
Model → API data classes
View → Jetpack Compose UI
ViewModel → Business logic & state management
Repository → Data source layer

🧰 Tech Stack :-
Kotlin
Jetpack Compose
MVVM Architecture
Retrofit
StateFlow
Coroutines
Navigation Compose
Glide (Compose Integration)
Material 3

📸 Screens :-
1️⃣ Recipe List Screen-
Displays all recipes in a grid layout.
Features:Banner section, LazyVerticalGrid, Image + title card layout

2️⃣ Recipe Detail Screen-
Shows:Recipe Image, Recipe Name, Cuisine, Difficulty, Cooking Time, Rating, Review Count

3️⃣ Instructions Screen-
Displays:Ingredients list, Step-by-step instructions

🔄 State Management-
The app uses StateFlow for reactive UI updates.
UI observes state using:- val state by viewModel.uiState.collectAsState()

🚀 Navigation :- Navigation is implemented using Navigation Compose.
RecipeListScreen
      ↓
RecipeDetailScreen
      ↓
RecipeInstructionsScreen
Example navigation:- navController.navigate("detail/${recipe.userId}")

API :- Recipes are fetched using Retrofit.
Example repository call: val response = repository.getRecipes()

⏳ Loading State :- A CircularProgressIndicator is shown while data is loading.

🛠️ How to Run :-
1️⃣ Clone the repository-  git clone https://github.com/your-username/recipe-app.git
2️⃣ Open project in Android Studio
3️⃣ Sync Gradle
4️⃣ Run the app on an emulator or physical device

📚 What I Learned :-
Jetpack Compose UI development
MVVM architecture
StateFlow state management
API integration with Retrofit
Navigation Compose
Building modern reactive Android apps

## 📥 Download APK :-
Download the latest APK from the Releases section:
https://github.com/SouravKarar12/MyRecipeApp-JetpackCompose/releases/tag/v1.0

👨‍💻 Author :-
Sourav Karar
Android Developer
Kotlin | Jetpack Compose | MVVM

⭐ If you like this project
Please star the repository ⭐ on GitHub!
