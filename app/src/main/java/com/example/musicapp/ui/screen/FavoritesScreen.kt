package com.example.musicapp.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_sample_app.helper.FileStorageHelper
import com.example.musicapp.R
import com.example.musicapp.api.model.Results
import com.example.musicapp.ui.itemView.BannerColor
import com.example.musicapp.ui.itemView.backgroundColor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.jschamburger.compose.custompagerindicator.Pager
import java.io.File

@Composable
fun FavoritesScreen(context: Context){
   var favorites by remember { mutableStateOf(emptyList<Results>()) }

   val fileStorageHelper = FileStorageHelper(context)
   val file = File(context.getCacheDir(), "favorites")
   if(file.exists()){
      favorites = Gson().fromJson(FileStorageHelper(context).getData("favorites"), object : TypeToken<List<Results>>() {}.type)
   }else{
      fileStorageHelper.storeData("favorites",favorites)
   }

   Column(
      modifier = Modifier
      .fillMaxSize()
         .background(backgroundColor)
   ) {
      Row(
         modifier = Modifier
            .fillMaxWidth()
            .background(BannerColor)
      ) {
         Text(
            text = context.getString(R.string.favorites),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
               .padding(vertical = 8.dp, horizontal = 16.dp),
            color = Color.White,
            fontWeight  = Bold
         )
      }
      Pager(
         data = favorites,
         context = context
      )
   }

}