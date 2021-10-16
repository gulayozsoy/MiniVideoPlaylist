package com.example.miniplaylist.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaylistResult(
    @SerializedName("title")
    var title: String,

    @SerializedName("thumbnails")
    var thumbnails: Thumbnails

)