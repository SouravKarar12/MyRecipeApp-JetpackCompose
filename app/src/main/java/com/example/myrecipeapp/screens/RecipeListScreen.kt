package com.example.myrecipeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myrecipeapp.R
import com.example.myrecipeapp.ui.theme.DarkBackground
import com.example.myrecipeapp.ui.theme.PrimaryOrange
import com.example.myrecipeapp.ui.theme.TextSilver
import com.example.myrecipeapp.viewmodel.MainViewModel
import com.example.myrecipeapp.viewmodel.RecipeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
            navController: NavController,
            viewModel: MainViewModel
) {
            val state by viewModel.uiState.collectAsState()

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
                                                                        text = "Recipe App",
                                                                        fontSize = 20.sp,
                                                                        fontWeight = FontWeight.Bold
                                                            )
                                                }
                                    )
                        }
            ) { padding ->
                        Box(
                                    modifier = Modifier
                                                .padding(padding)
                                                .fillMaxSize()
                        ) {
                                    when (state) {
                                                is RecipeUiState.Loading -> {
                                                            CircularProgressIndicator(
                                                                        color = PrimaryOrange,
                                                                        modifier = Modifier.align(Alignment.Center)
                                                            )
                                                }
                                                is RecipeUiState.Success -> {
                                                            val recipes = (state as RecipeUiState.Success).recipes

                                                            LazyVerticalGrid(
                                                                        columns = GridCells.Fixed(2),
                                                                        modifier = Modifier.fillMaxSize(),
                                                                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                                                                        verticalArrangement = Arrangement.spacedBy(16.dp),
                                                                        contentPadding = PaddingValues(16.dp)
                                                            ) {

                                                                        // --- BANNER SECTION ---
                                                                        item(span = { GridItemSpan(maxLineSpan) }) {
                                                                                    Column(
                                                                                                modifier = Modifier
                                                                                                            .fillMaxWidth()
                                                                                                            .padding(bottom = 8.dp) // Extra spacing before the grid items start
                                                                                                            .clip(RoundedCornerShape(8.dp))
                                                                                    ) {
                                                                                                Image(
                                                                                                            painter = painterResource(id = R.drawable.banner),
                                                                                                            contentDescription = "Delicious food spread banner",
                                                                                                            modifier = Modifier
                                                                                                                        .fillMaxWidth()
                                                                                                                        .height(250.dp)
                                                                                                                        .clip(RoundedCornerShape(10.dp)),
                                                                                                            contentScale = ContentScale.Crop
                                                                                                )

                                                                                                Spacer(modifier = Modifier.height(18.dp))

                                                                                                Text(
                                                                                                            text = "Spice up your day with our handpicked culinary delights",
                                                                                                            fontSize = 15.sp,
                                                                                                            color = TextSilver,
                                                                                                            fontWeight = FontWeight.Medium,
                                                                                                            modifier = Modifier.padding(horizontal = 4.dp)
                                                                                                )

                                                                                                Spacer(modifier = Modifier.height(25.dp))
                                                                                    }
                                                                        }

                                                                        // --- RECIPE LIST SECTION ---
                                                                        items(recipes) { recipe ->
                                                                                    RecipeCard(
                                                                                                recipe = recipe,
                                                                                                onClick = {
                                                                                                            navController.navigate("detail/${recipe.id}")
                                                                                                }
                                                                                    )
                                                                        }
                                                            }
                                                }
                                                is RecipeUiState.Error -> {
                                                            val message = (state as RecipeUiState.Error).message
                                                            Text(
                                                                        text = message,
                                                                        fontSize = 16.sp,
                                                                        color = PrimaryOrange,
                                                                        modifier = Modifier.align(Alignment.Center)
                                                            )
                                                }
                                    }
                        }
            }
}
