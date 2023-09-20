package com.example.musicapp.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.musicapp.api.model.Results
import com.example.musicapp.ui.itemView.FilterBottomSheet
import com.example.musicapp.ui.itemView.SearchAppBar
import com.example.musicapp.ui.itemView.Skeleton
import com.example.musicapp.ui.itemView.backgroundColor
import com.example.musicapp.ui.viewModel.SearchScreenViewModel
import de.jschamburger.compose.custompagerindicator.Pager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(context: Context) {
   val viewModel = SearchScreenViewModel(context)
   var apiResponse by remember { mutableStateOf<List<Results>>(emptyList()) }
   var textValue by remember { mutableStateOf("") }

   val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
   val coroutineScope = rememberCoroutineScope()
   ModalBottomSheetLayout(
      modifier = Modifier.fillMaxSize(),
      sheetState = sheetState,
      sheetBackgroundColor = MaterialTheme.colorScheme.surface,
      sheetContent = {
         FilterBottomSheet(
            coroutineScope= coroutineScope,
            sheetState= sheetState,
            countryList = viewModel.countryList,
            context = context,
            countryName = {
               viewModel.musicSearch(term= textValue,country = it) {
                  apiResponse = it
               }
            })
         },
   ){
      Column(
         modifier = Modifier
            .fillMaxSize()
      ) {
         SearchAppBar(
            context = context,
            onSearchClicked = {
               textValue = it
               viewModel.musicSearch(textValue) {
                  apiResponse = it
               }
            },
            onFilterClicked = {
               coroutineScope.launch {
                  sheetState.show()
               }
            }
         )
         Box(
            modifier = Modifier
               .fillMaxSize()
               .background(
                  backgroundColor
               )){
            if(apiResponse.isEmpty()){
               Skeleton(true)
            }else{
               Pager(
                  data = apiResponse,
                  context = context
               )
            }
         }
      }

   }
}




