package com.example.myrecipeapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myrecipeapp.navigation.AppNavigation
import com.example.myrecipeapp.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

            override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)

                        val viewModel = MainViewModel()

                        setContent {
                                    AppNavigation(viewModel)
                        }
            }
}
