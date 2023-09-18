package com.example.musicapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.musicapp.helper.Utils.updateLocale
import com.example.musicapp.ui.screen.HomeScreen
import com.example.musicapp.ui.screen.MainScreen
import de.jschamburger.compose.custompagerindicator.Pager


class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      updateLocale(this)
      setContent {
         MainScreen(this)
      }
   }
}



