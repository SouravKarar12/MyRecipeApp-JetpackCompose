package com.example.myrecipeapp.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

            @GET("recipes")
            suspend fun getRecipes(): Response<Recipe>
}
