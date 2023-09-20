package com.example.musicapp.ui.itemView

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.musicapp.api.model.Results

@Composable
fun AlbumsItem(name:String,data:List<Results>){
   Text(
      text = name,
      style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
      modifier = Modifier
         .padding(start = 8.dp, end = 4.dp, bottom = 8.dp)
         .semantics { heading() }
   )
   if(data.isEmpty()){
      Skeleton(false)
   }else{
      LazyRow(
         modifier = Modifier.padding(horizontal = 8.dp)
      ) {
         items(data.size){
            HorizontalListItem(
               data[it].artworkUrl100?.replace("100x100", "500x500")?:"",
               data[it].collectionName?:"")
         }
      }
   }
}

