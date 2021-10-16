package com.example.miniplaylist.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoId (
    @SerializedName("videoId")
    var videoid: String

) :Serializable
