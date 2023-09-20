package com.example.musicapp.ui.itemView

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Skeleton(isVertical:Boolean) {

   val transition = rememberInfiniteTransition()
   val translateAnim by transition.animateFloat(
      initialValue = 100f,
      targetValue = 900f,
      animationSpec = infiniteRepeatable(
         tween(durationMillis = 2500, easing = LinearEasing),
         RepeatMode.Restart
      ), label = ""
   )

   val list = listOf(
      Color.LightGray.copy(alpha = 0.3f),
      Color.Transparent)

   val dpValue = translateAnim.dp
   if(isVertical){
      LazyColumn(
         modifier = Modifier.padding(vertical = 8.dp),
         verticalArrangement = Arrangement.spacedBy(8.dp),
      ){
         items(10){
            SkeletonSongItem(list, dpValue.value )
         }

      }
   }else{
      LazyRow(modifier = Modifier.padding(horizontal = 8.dp) ){
         items(3){
            SkeletonAlbumItem(list,dpValue.value)
         }
      }
   }
}

@Composable
fun SkeletonSongItem(lists: List<Color>, floatAnim: Float = 0f,) {

   val brush = Brush.horizontalGradient(lists, 0f, floatAnim)

   Row(modifier = Modifier.padding(8.dp)) {
      Spacer(
         modifier = Modifier
            .size(60.dp)
            .background(Color.LightGray.copy(alpha = 0.3f))
      )
      Column(modifier = Modifier) {
         Spacer(
            modifier = Modifier
               .fillMaxWidth()
               .height(30.dp)
               .padding(8.dp)
               .background(brush = brush)
         )
         Spacer(
            modifier = Modifier
               .fillMaxWidth()
               .height(30.dp)
               .padding(8.dp)
               .background(brush = brush)
         )
      }
   }
}

@Composable
fun SkeletonAlbumItem(lists: List<Color>, floatAnim: Float = 0f,) {
   val brush = Brush.verticalGradient(lists, 0f, floatAnim)
   Column(
      modifier = Modifier.padding(8.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),) {
      Spacer(
         modifier = Modifier
            .size(150.dp)
            .background(brush = brush)
      )
      Spacer(
         modifier = Modifier
            .width(90.dp)
            .height(20.dp)
            .background(brush = brush)
      )
   }
}


