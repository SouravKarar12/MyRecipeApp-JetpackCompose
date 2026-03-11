package com.example.myrecipeapp.viewmodel

import com.example.myrecipeapp.network.Cooking

sealed class RecipeUiState {

            data object Loading : RecipeUiState()
            data class Success(val recipes: List<Cooking>) : RecipeUiState()
            data class Error(val message: String) : RecipeUiState()
}
