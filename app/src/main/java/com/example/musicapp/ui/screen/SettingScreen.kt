package com.example.musicapp.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musicapp.ui.itemView.LanguageDropdownMenu

@Composable
fun SettingScreen(context:Context) {
   Column(modifier = Modifier.fillMaxSize()) {
      LanguageDropdownMenu(context)
   }
}