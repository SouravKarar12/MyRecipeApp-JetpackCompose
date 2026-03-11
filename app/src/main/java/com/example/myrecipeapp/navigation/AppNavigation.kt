package com.example.myrecipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myrecipeapp.viewmodel.MainViewModel
import com.example.myrecipeapp.screens.RecipeDetailScreen
import com.example.myrecipeapp.screens.RecipeListScreen
import com.example.myrecipeapp.screens.RecipeInstructionsScreen

@Composable
fun AppNavigation(viewModel: MainViewModel) {

            val navController = rememberNavController()

            NavHost(
                        navController = navController,
                        startDestination = "list"
            ) {

                        // 1. List Screen
                        composable("list") {
                                    RecipeListScreen(navController, viewModel)
                        }

                        // 2. Detail Screen
                        composable("detail/{recipeId}") {
                                    val recipeId = it.arguments?.getString("recipeId")

                                    if (recipeId != null) {
                                                RecipeDetailScreen(
                                                            recipeId = recipeId,
                                                            viewModel = viewModel,
                                                            navController = navController
                                                )
                                    }
                        }

                        // 3. Instructions Screen
                        composable("instructions/{recipeId}") { backStackEntry ->
                                    val recipeId = backStackEntry.arguments?.getString("recipeId")

                                    if (recipeId != null) {
                                                RecipeInstructionsScreen(
                                                            recipeId = recipeId,
                                                            viewModel = viewModel
                                                )
                                    }
                        }
            }
}
