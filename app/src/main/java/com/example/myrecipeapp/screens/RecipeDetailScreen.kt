package com.example.myrecipeapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myrecipeapp.ui.theme.AccentOrange
import com.example.myrecipeapp.ui.theme.DarkBackground
import com.example.myrecipeapp.ui.theme.PrimaryOrange
import com.example.myrecipeapp.ui.theme.SurfaceCard
import com.example.myrecipeapp.ui.theme.TextSilver
import com.example.myrecipeapp.viewmodel.MainViewModel
import com.example.myrecipeapp.viewmodel.RecipeUiState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun RecipeDetailScreen(
            recipeId: String,
            viewModel: MainViewModel,
            navController: NavController
) {
            val state by viewModel.uiState.collectAsState()

            val recipe = if (state is RecipeUiState.Success) {
                        val recipes = (state as RecipeUiState.Success).recipes
                        recipes.find { it.id == recipeId.toInt() }
            } else null

            recipe?.let {
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
                                                                                    text = "Recipe Details",
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
                                                            .verticalScroll(rememberScrollState())
                                                            .padding(horizontal = 16.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                                GlideImage(
                                                            model = it.image,
                                                            contentDescription = it.name,
                                                            modifier = Modifier
                                                                        .fillMaxWidth()
                                                                        .height(250.dp)
                                                                        .clip(RoundedCornerShape(15.dp)),
                                                            contentScale = ContentScale.Crop
                                                )

                                                Spacer(modifier = Modifier.height(16.dp))

                                                Text(
                                                            text = it.name,
                                                            fontSize = 22.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            color = PrimaryOrange,
                                                            textAlign = TextAlign.Center
                                                )

                                                Spacer(modifier = Modifier.height(20.dp))

                                                Card(
                                                            modifier = Modifier.fillMaxWidth(),
                                                            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                                                            shape = RoundedCornerShape(12.dp)
                                                ) {
                                                            Column(
                                                                        modifier = Modifier.padding(16.dp),
                                                                        verticalArrangement = Arrangement.spacedBy(12.dp)
                                                            ) {
                                                                        DetailRow(label = "User ID :", value = "#${it.userId}")
                                                                        DetailRow(label = "Cuisine :", value = it.cuisine)
                                                                        DetailRow(label = "Difficulty :", value = it.difficulty)
                                                                        DetailRow(label = "Cooking Time :", value = "${it.cookTimeMinutes} minutes")
                                                                        DetailRow(label = "Rating :", value = "${it.rating} ★")
                                                                        DetailRow(label = "Reviews :", value = it.reviewCount.toString())
                                                            }
                                                }

                                                Spacer(modifier = Modifier.weight(1f))

                                                Button(
                                                            onClick = { navController.navigate("instructions/${it.id}") },
                                                            modifier = Modifier
                                                                        .fillMaxWidth()
                                                                        .padding(vertical = 16.dp)
                                                                        .height(50.dp),
                                                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                                                            shape = RoundedCornerShape(12.dp)
                                                ) {
                                                            Text(
                                                                        text = "SHOW INSTRUCTIONS",
                                                                        color = DarkBackground,
                                                                        fontWeight = FontWeight.Bold,
                                                                        fontSize = 16.sp
                                                            )
                                                }
                                    }
                        }
            }
}

@Composable
fun DetailRow(label: String, value: String) {
            Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                                    text = label,
                                    color = AccentOrange,
                                    modifier = Modifier.weight(1f),
                                    fontSize = 14.sp
                        )
                        Text(
                                    text = value,
                                    color = TextSilver,
                                    modifier = Modifier.weight(1.5f),
                                    fontSize = 14.sp
                        )
            }
}
