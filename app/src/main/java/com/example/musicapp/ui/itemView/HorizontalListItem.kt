package com.example.musicapp.ui.itemView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun HorizontalListItem(imgUrl:String,name:String) {
   Column(
      modifier =
      Modifier
         .padding(8.dp)
         .clickable(
            onClick = {
            })
   ) {
      AsyncImage(
         model = imgUrl,
         contentScale = ContentScale.Crop,
         contentDescription = null,
         modifier = Modifier,
      )
      Text(
         text = name,
         style = MaterialTheme.typography.body2,
         maxLines = 2,
         overflow = TextOverflow.Ellipsis,
         modifier = Modifier.padding(vertical = 8.dp).width(150.dp)
      )
   }
}
