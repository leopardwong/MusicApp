package com.example.musicapp.api.repository
import com.example.jetpack_compose_sample_app.api.ApiState
import com.example.jetpack_compose_sample_app.api.client.ApiClient
import com.example.jetpack_compose_sample_app.api.iTunesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart


object ApiRepository {
    val apiService: iTunesApi = ApiClient.createRetrofit("https://itunes.apple.com/").create(iTunesApi::class.java)
    suspend fun getMusicData(term:String) = flow {
        val result = apiService.searchMusic(term=term, mediaType = "music")
        emit(ApiState(isSuccess = result))
    }.flowOn(Dispatchers.IO).onStart {
        emit(ApiState(isLoading = true))
    }.catch {
        emit(ApiState(isOtherError = it))
    }

}
