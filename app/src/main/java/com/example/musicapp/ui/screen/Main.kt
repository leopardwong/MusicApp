package com.example.musicapp.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.ui.model.BottomNavItem
import com.example.musicapp.ui.navigation.NavigationGraph
import com.example.musicapp.ui.navigation.BottomBarNavigation
import com.example.musicapp.ui.viewModel.MainViewModel

@Composable
fun MainScreen(context:Context) {
   val viewMode = MainViewModel(context)
   val navController = rememberNavController()
   Scaffold(
      bottomBar = {
         BottomBarNavigation(
            navController = navController,
            viewModel = viewMode)
      }
   ) {
      Box(modifier = Modifier.padding(it)) {
         NavigationGraph(
            navController = navController,
            viewModel = viewMode,
            context = context,
         )
      }
   }
}






