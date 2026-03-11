package com.example.myrecipeapp.viewmodel

import com.example.myrecipeapp.network.Recipe
import com.example.myrecipeapp.network.RetrofitObject
import retrofit2.Response

class RecipeRepository {

            suspend fun getRecipes(): Response<Recipe> {
                        return RetrofitObject.api.getRecipes()
            }
}
