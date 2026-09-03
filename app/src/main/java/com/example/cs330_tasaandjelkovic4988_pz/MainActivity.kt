package com.example.cs330_tasaandjelkovic4988_pz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: KontaktiViewModel = hiltViewModel()

            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    KontaktListScreen(
                        viewModel = viewModel,
                        onAddClick = { navController.navigate("add") },
                        onEditClick = { kontakt ->
                            viewModel.postaviKontaktZaIzmenu(kontakt)
                            navController.navigate("edit")
                        }
                    )
                }
                composable("add") {
                    AddKontaktScreen(viewModel = viewModel, onDone = { navController.popBackStack() })
                }
                composable("edit") {
                    val kontakt by viewModel.kontaktZaIzmenu
                    kontakt?.let {
                        EditKontaktScreen(viewModel = viewModel, kontakt = it, onDone = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}