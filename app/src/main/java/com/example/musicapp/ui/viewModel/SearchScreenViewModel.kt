package com.example.musicapp.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.R
import com.example.musicapp.api.model.Results
import com.example.musicapp.api.repository.ApiRepository.getMusicData
import com.example.musicapp.ui.model.CountryCode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchScreenViewModel(context:Context): ViewModel() {

   val countryList: List<CountryCode> = listOf(
      CountryCode(context.getString(R.string.usa), "US"),
      CountryCode(context.getString(R.string.ca), "ca"),
      CountryCode(context.getString(R.string.jp), "JP"),
      CountryCode(context.getString(R.string.cn), "CN"),
      CountryCode(context.getString(R.string.hk), "HK"),
   )

   fun musicSearch(term:String, country:String? = null, response: (List<Results>)->Unit){
      viewModelScope.launch {
         getMusicData(term = term, entity = "song", country = country)
            .collectLatest {
               it.isSuccess?.let {
                  response(it.results)
               }
               it.isOtherError?.let{
                  println("isOtherError: ${it.message}")
               }
               it.isBizError?.let{
                  println("isBizError: ${it.message}")
               }
            }
      }
   }
}