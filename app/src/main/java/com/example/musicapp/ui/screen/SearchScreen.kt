package com.example.musicapp.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.musicapp.api.model.Results
import com.example.musicapp.ui.itemView.SearchAppBar
import com.example.musicapp.ui.viewModel.SearchScreenViewModel
import de.jschamburger.compose.custompagerindicator.Pager

@Composable
fun SearchScreen(context: Context) {
   val viewModel = SearchScreenViewModel()
   var apiResponse by remember { mutableStateOf<List<Results>>(emptyList()) }
   Column(
      modifier = Modifier
         .fillMaxSize()
   ) {
      SearchAppBar(
         viewModel = viewModel,
         onSearchClicked = {
            apiResponse = it
         },
         onFilterClicked = {
            apiResponse.filter { it.collectionId == 1442968839}
         }
      )
      Pager(
         data = apiResponse,
         context = context)
   }
}