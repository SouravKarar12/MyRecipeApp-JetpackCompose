package com.example.myrecipeapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myrecipeapp.network.Cooking
import com.example.myrecipeapp.ui.theme.PrimaryOrange
import com.example.myrecipeapp.ui.theme.SurfaceCard
import com.example.myrecipeapp.ui.theme.TextSilver

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeCard(
            recipe: Cooking,
            onClick: () -> Unit
) {
            Card(
                        modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onClick() },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceCard)
            ) {
                        Column {
                                    GlideImage(
                                                model = recipe.image,
                                                contentDescription = recipe.name,
                                                modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(140.dp),
                                                contentScale = ContentScale.Crop
                                    )

                                    Column(
                                                modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(12.dp)
                                    ) {
                                                Text(
                                                            text = recipe.name,
                                                            fontSize = 14.sp,
                                                            fontWeight = FontWeight.SemiBold,
                                                            color = PrimaryOrange,
                                                            maxLines = 2,
                                                            overflow = TextOverflow.Ellipsis
                                                )

                                                Spacer(modifier = Modifier.height(4.dp))

                                                Text(
                                                            text = "User ID: ${recipe.userId}",
                                                            fontSize = 12.sp,
                                                            fontWeight = FontWeight.Normal,
                                                            color = TextSilver
                                                )
                                    }
                        }
            }
}
