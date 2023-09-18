package com.example.musicapp.ui.itemView

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ResultItem(song:String,artistName: String,imageUrl:String, isFavorite: Boolean,onFavoriteClick: () -> Unit){
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .padding(8.dp, 4.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
   ) {

      var textValue by remember { mutableStateOf("") }

      if (textValue.isNotBlank()) {
         val words = artistName.split(" ")
         if (words.size > 5) {
            textValue = words.take(5).joinToString(" ") + "..."
         }
      }
      AsyncImage(
         modifier = Modifier.align(Alignment.CenterVertically),
         model = imageUrl,
         contentDescription = null,
      )
      Column(
         modifier = Modifier
            .align(Alignment.CenterVertically),
      ) {
         Text(text = if(song.split(" ").size > 5)song.split(" ").take(6).joinToString(" ") + "..." else song,
            fontSize = 16.sp,
            color = Color.White
         )
         Text(text = if(artistName.split(" ").size > 5)artistName.split(", ").take(5).joinToString(", ") + "..." else artistName,
            fontSize = 12.sp,
            maxLines = 1,
            color = Color.White
         )
      }
      Spacer(Modifier.weight(1f))
      IconButton(
         onClick = { onFavoriteClick() },
         modifier = Modifier.size(24.dp).align(Alignment.CenterVertically),
      ) {
         Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            tint = Color.White

         )
      }
   }
}
