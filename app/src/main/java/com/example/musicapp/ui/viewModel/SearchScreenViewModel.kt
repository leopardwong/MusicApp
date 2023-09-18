package com.example.musicapp.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_sample_app.helper.FileStorageHelper
import com.example.musicapp.api.model.Results
import com.example.musicapp.api.repository.ApiRepository.getMusicData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File

class SearchScreenViewModel(): ViewModel() {


   fun getiTunesApi(param:String, response: (List<Results>)->Unit){
      viewModelScope.launch {
         getMusicData(param)
            .collectLatest {
               it.isSuccess?.let {
                  response(it.results)
               }
            }
      }
   }
}