package com.example.musicapp.ui.viewModel

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.lifecycle.ViewModel
import com.example.musicapp.R
import com.example.musicapp.ui.model.BottomNavItem

class MainViewModel(context: Context):ViewModel() {
   val bottomNavItems = listOf(
      BottomNavItem(context.getString(R.string.home), Icons.Default.Home,"home"),
      BottomNavItem(context.getString(R.string.search), Icons.Default.Search,"search"),
      BottomNavItem(context.getString(R.string.favorites), Icons.Default.LibraryMusic,"favorites"),
   )
}