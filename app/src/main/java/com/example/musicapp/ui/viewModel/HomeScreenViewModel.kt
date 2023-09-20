package com.example.musicapp.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.R
import com.example.musicapp.api.model.Results
import com.example.musicapp.api.repository.ApiRepository
import com.example.musicapp.ui.model.LanguageItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenViewModel(context: Context):ViewModel() {

   val languageTextList = listOf(
      LanguageItem(context.getString(R.string.english),"en"),
      LanguageItem(context.getString(R.string.traChi),"zh"),
      LanguageItem(context.getString(R.string.simChi),"za"),
   )
   fun albumsResults(term:String,response: (List<Results>)->Unit){
      viewModelScope.launch {
         ApiRepository.getMusicData(term=term, entity = "album", limit = 5)
            .collectLatest {
               it.isSuccess?.let {
                  response(it.results)
               }
            }
      }
   }
}