package de.jschamburger.compose.custompagerindicator


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_sample_app.helper.FileStorageHelper
import com.example.musicapp.api.model.Results
import com.example.musicapp.ui.itemView.ResultItem
import com.google.accompanist.pager.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(data:List<Results>,context:Context) {
   val pagerState = rememberPagerState()
   val itemsPerPage = 20
   val totalPages = (data.size + itemsPerPage - 1) / itemsPerPage

   var favorites by remember { mutableStateOf(emptyList<Results>()) }

   //ToDO
   val fileStorageHelper = FileStorageHelper(context)
   val file = File(context.getCacheDir(), "favorites")
   if(file.exists()){
      favorites = Gson().fromJson(FileStorageHelper(context).getData("favorites"), object : TypeToken<List<Results>>() {}.type)
   }else{
      fileStorageHelper.storeData("favorites",favorites)
   }

   Box(
      modifier =
      Modifier.fillMaxSize()
   ) {
      HorizontalPager(
         count = totalPages,
         state = pagerState,
         modifier = Modifier
            .fillMaxSize()
            .background(
               brush = Brush.linearGradient(
                  0.0f to Color(0xFF30095C),
                  500.0f to Color(0xFF0A0A0A),
                  start = Offset.Zero,
                  end = Offset.Infinite
               )
            )
      ) { currentPage  ->
         Box(
            modifier = Modifier.fillMaxSize()
            ) {
            val startIndex = currentPage * itemsPerPage
            val endIndex = minOf(startIndex + itemsPerPage, data.size)
            val currentPageData = data.subList(startIndex, endIndex)

            LazyColumn(
               modifier = Modifier.padding(vertical = 8.dp),
               verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
               currentPageData.forEach { item->
                  item {
                     ResultItem(
                        song = item.trackName?:"",
                        artistName = item.artistName?:"",
                        imageUrl = item.artworkUrl100?.replace("100x100", "200x200")?:"",
                        isFavorite = favorites.contains(item)
                     ){
                        //ToDO
                        if (favorites.contains(item)) {
                           favorites = favorites - item
                           fileStorageHelper.storeData("favorites",favorites)
                        } else {
                           favorites = favorites + item
                           fileStorageHelper.storeData("favorites",favorites)

                        }
                     }
                  }
               }
            }
//            Column(
//               modifier = Modifier
//                  .fillMaxSize()
//                  .verticalScroll(rememberScrollState())
//                  .padding(vertical = 8.dp)
//                  .background(color =  Color.Transparent),
//               verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//               currentPageData.forEach { item ->
//                  ResultItem(
//                     song = item.trackName?:"",
//                     artistName = item.artistName?:"",
//                     imageUrl = item.artworkUrl100?.replace("100x100", "200x200")?:"",
//                     isFavorite = favorites.contains(item)
//                  ){
//                     //ToDO
//                     if (favorites.contains(item)) {
//                        favorites = favorites - item
//                        fileStorageHelper.storeData("favorites",favorites)
//                     } else {
//                        favorites = favorites + item
//                        fileStorageHelper.storeData("favorites",favorites)
//
//                     }
//                  }
//               }
//            }
         }
      }
      Box(
         modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(end = 16.dp, bottom = 16.dp)
      ) {
         if(data.isNotEmpty()){
            HorizontalPagerIndicator(
               pagerState = pagerState,
               activeColor = Color.Blue,
               inactiveColor = Color.Gray,
            )
         }
      }
   }
}





