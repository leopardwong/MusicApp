package com.example.musicapp.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.musicapp.ui.itemView.BottomBarColor
import com.example.musicapp.ui.viewModel.MainViewModel

@Composable
fun BottomBarNavigation(navController: NavController,viewModel: MainViewModel){
   BottomNavigation(
      backgroundColor = BottomBarColor,
      contentColor = Color.White
   ) {
      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentRoute = navBackStackEntry?.destination?.route
      viewModel.bottomNavItems.forEach{ item ->
         BottomNavigationItem(
            icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
            label = { Text(
               text = item.title,
               fontSize = 9.sp,
               color = Color.White) },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true,
            selected = currentRoute == item.screenRoute,
            onClick = {
               navController.navigate(item.screenRoute) {

                  navController.graph.startDestinationRoute?.let { screen_route ->
                     popUpTo(screen_route) {
                        saveState = true
                     }
                  }
                  launchSingleTop = true
                  restoreState = true
               }
            }
         )
      }
   }
}