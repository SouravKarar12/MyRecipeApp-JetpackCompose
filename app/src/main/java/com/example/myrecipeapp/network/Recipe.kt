package com.example.myrecipeapp.network

data class Recipe(
            val recipes: List<Cooking>,
            val total: Int
)

data class Cooking(
            val cookTimeMinutes: Int,
            val cuisine: String,
            val difficulty: String,
            val id: Int,
            val image: String,
            val ingredients: List<String>,
            val instructions: List<String>,
            val name: String,
            val rating: Double,
            val reviewCount: Int,
            val userId: Int
)
