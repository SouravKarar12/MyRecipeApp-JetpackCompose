package com.example.myrecipeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RecipeRepository = RecipeRepository()) : ViewModel() {

            private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
            val uiState: StateFlow<RecipeUiState> = _uiState

            init {
                        fetchRecipes()
            }

            fun fetchRecipes() {
                        viewModelScope.launch {
                                    _uiState.value = RecipeUiState.Loading

                                    try {
                                                val response = repository.getRecipes()
                                                if (response.isSuccessful && response.body() != null) {
                                                            _uiState.value = RecipeUiState.Success(response.body()!!.recipes)
                                                }
                                                else {
                                                            _uiState.value = RecipeUiState.Error("Error : ${response.code()}")
                                                }

                                    } catch (e: Exception) {
                                                _uiState.value = RecipeUiState.Error("Exception occurred : ${e.localizedMessage}")
                                    }
                        }
            }
}
