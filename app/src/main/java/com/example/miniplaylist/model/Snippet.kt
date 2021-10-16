package com.example.miniplaylist.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Snippet (
    @SerializedName("snippet")
    var snippet: PlaylistResult,

    @SerializedName("id")
    var id: VideoId

    ): Serializable