package com.example.musicapp.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.musicapp.ui.screen.FavoritesScreen
import com.example.musicapp.ui.screen.HomeScreen
import com.example.musicapp.ui.screen.SearchScreen
import com.example.musicapp.ui.viewModel.MainViewModel

@Composable
fun NavigationGraph(navController: NavHostController,viewModel: MainViewModel,context:Context) {
   NavHost(
      navController,
      startDestination = "main"
   ) {
      navigation(startDestination = "home", route = "main"){
         viewModel.bottomNavItems.forEach {
            when(it.screenRoute){
               "home" -> composable(it.screenRoute) { HomeScreen(context) }
               "search" -> composable(it.screenRoute) { SearchScreen(context) }
               "favorites" -> composable(it.screenRoute) { FavoritesScreen(context) }
            }
         }
      }
   }
}