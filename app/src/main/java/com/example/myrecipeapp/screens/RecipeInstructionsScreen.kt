package com.example.myrecipeapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myrecipeapp.ui.theme.AccentOrange
import com.example.myrecipeapp.ui.theme.DarkBackground
import com.example.myrecipeapp.ui.theme.PrimaryOrange
import com.example.myrecipeapp.ui.theme.SurfaceCard
import com.example.myrecipeapp.ui.theme.TextSilver
import com.example.myrecipeapp.viewmodel.MainViewModel
import com.example.myrecipeapp.viewmodel.RecipeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeInstructionsScreen(
            recipeId: String,
            viewModel: MainViewModel
) {
            val state by viewModel.uiState.collectAsState()

            val recipe = if (state is RecipeUiState.Success) {
                        val recipes = (state as RecipeUiState.Success).recipes
                        recipes.find { it.id == recipeId.toInt() }
            } else null

            // Safe call to ensure we only build the UI when the recipe is found
            recipe?.let { currentRecipe ->
                        Scaffold(
                                    containerColor = DarkBackground,
                                    topBar = {
                                                CenterAlignedTopAppBar(
                                                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                                                        containerColor = DarkBackground,
                                                                        titleContentColor = PrimaryOrange
                                                            ),
                                                            title = {
                                                                        Text(
                                                                                    text = "Recipe Instructions",
                                                                                    fontSize = 20.sp,
                                                                                    fontWeight = FontWeight.Bold
                                                                        )
                                                            }
                                                )
                                    }
                        ) { padding ->
                                    Column(
                                                modifier = Modifier
                                                            .padding(padding)
                                                            .fillMaxSize()
                                                            .padding(16.dp)
                                                            .verticalScroll(rememberScrollState()),
                                                verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {

                                                // Ingredients Card
                                                Card(
                                                            modifier = Modifier.fillMaxWidth(),
                                                            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                                                            shape = RoundedCornerShape(12.dp)
                                                ) {
                                                            Column(modifier = Modifier.padding(16.dp)) {
                                                                        Text(
                                                                                    text = "Ingredients :",
                                                                                    color = PrimaryOrange,
                                                                                    fontWeight = FontWeight.Bold,
                                                                                    fontSize = 18.sp
                                                                        )
                                                                        Spacer(modifier = Modifier.height(14.dp))

                                                                        currentRecipe.ingredients.forEach { ingredient ->
                                                                                    Row(modifier = Modifier.padding(vertical = 4.dp)) {
                                                                                                Text(text = "•  ", color = AccentOrange, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                                                                                                Text(text = ingredient, color = TextSilver, fontSize = 14.sp)
                                                                                    }
                                                                        }
                                                            }
                                                }

                                                Spacer(modifier = Modifier.height(12.dp))

                                                // Instructions Card
                                                Card(
                                                            modifier = Modifier.fillMaxWidth(),
                                                            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                                                            shape = RoundedCornerShape(12.dp)
                                                ) {
                                                            Column(modifier = Modifier.padding(16.dp)) {
                                                                        Text(
                                                                                    text = "Instructions :",
                                                                                    color = PrimaryOrange,
                                                                                    fontWeight = FontWeight.Bold,
                                                                                    fontSize = 18.sp
                                                                        )
                                                                        Spacer(modifier = Modifier.height(14.dp))

                                                                        currentRecipe.instructions.forEachIndexed { index, step ->
                                                                                    Row(modifier = Modifier.padding(vertical = 6.dp)) {
                                                                                                Text(
                                                                                                            text = "${index + 1}. ",
                                                                                                            color = AccentOrange,
                                                                                                            fontSize = 14.sp,
                                                                                                            modifier = Modifier.width(24.dp)
                                                                                                )
                                                                                                Text(
                                                                                                            text = step,
                                                                                                            color = TextSilver,
                                                                                                            fontSize = 14.sp,
                                                                                                            modifier = Modifier.weight(1f)
                                                                                                )
                                                                                    }
                                                                        }
                                                            }
                                                }
                                    }
                        }
            }
}
