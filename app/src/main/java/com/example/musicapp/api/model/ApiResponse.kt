package com.example.musicapp.api.model

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("resultCount" ) var resultCount : Int? = null,
    @SerializedName("results") var results : List<Results>
)
data class Results (
    @SerializedName("kind") var kind: String? = null, //media type, for filter
    @SerializedName("collectionId") var collectionId:Int? = null,
    @SerializedName("trackId") var trackId: Int? = null,
    @SerializedName("artistName") var artistName: String? = null, //need
    @SerializedName("collectionName") var collectionName: String? = null,
    @SerializedName("trackName") var trackName : String? = null, //song name
//    @SerializedName("collectionCensoredName" ) var collectionCensoredName : String?  = null,
//    @SerializedName("trackCensoredName"      ) var trackCensoredName      : String?  = null,
//    @SerializedName("artistViewUrl"          ) var artistViewUrl          : String?  = null,
//    @SerializedName("collectionViewUrl"      ) var collectionViewUrl      : String?  = null,
//    @SerializedName("trackViewUrl"           ) var trackViewUrl           : String?  = null,
//    @SerializedName("previewUrl"             ) var previewUrl             : String?  = null,
    @SerializedName("artworkUrl100") var artworkUrl100: String? = null, //album  art
    @SerializedName("country") var country: String? = null,// for filter


)