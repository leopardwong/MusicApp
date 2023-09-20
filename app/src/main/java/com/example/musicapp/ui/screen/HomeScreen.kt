package com.example.musicapp.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.api.model.Results
import com.example.musicapp.ui.itemView.AlbumsItem
import com.example.musicapp.ui.itemView.BannerColor
import com.example.musicapp.ui.itemView.LanguageDropdownMenu
import com.example.musicapp.ui.itemView.TitleColor
import com.example.musicapp.ui.itemView.backgroundColor
import com.example.musicapp.ui.viewModel.HomeScreenViewModel

@Composable
fun HomeScreen(context: Context) {
   val viewModel = HomeScreenViewModel(context)
   var swiftResponse by remember { mutableStateOf<List<Results>>(emptyList()) }
   var jackResponse by remember { mutableStateOf<List<Results>>(emptyList()) }
   var michaelResponse by remember { mutableStateOf<List<Results>>(emptyList()) }
   var marsResponse by remember { mutableStateOf<List<Results>>(emptyList()) }

   LaunchedEffect(Unit) {
      viewModel.albumsResults("Taylor Swift") { result ->
         swiftResponse = result
      }
      viewModel.albumsResults("Jack Jackson") { result ->
         jackResponse = result
      }
      viewModel.albumsResults("michael jackson") { result ->
         michaelResponse = result
      }
      viewModel.albumsResults("Bruno Mars") { result ->
         marsResponse = result
      }
   }
   Box(
      modifier = Modifier
         .fillMaxSize()
         .background(
            backgroundColor
         )
   ){
      Column(
         modifier = Modifier
            .verticalScroll(rememberScrollState())
      ) {
         Row(modifier = Modifier.fillMaxWidth().background(BannerColor)) {
            Text(
               text = context.getString(R.string.home),
               style = MaterialTheme.typography.h5,
               modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
               color = Color.White,
               fontWeight  = FontWeight.Bold
            )
            Spacer(Modifier.weight(1f))
            LanguageDropdownMenu(context,viewModel.languageTextList)
         }
         Text(
            text = context.getString(R.string.albums),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            color = TitleColor,
            fontWeight  = FontWeight.Bold
         )
         AlbumsItem("Taylor Swift",swiftResponse)
         AlbumsItem("Jack Jackson",jackResponse)
         AlbumsItem("Michael Jackson",michaelResponse)
         AlbumsItem("Bruno Mars",marsResponse)
      }
   }
}




