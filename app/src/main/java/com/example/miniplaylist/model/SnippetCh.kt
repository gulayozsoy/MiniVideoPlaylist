package com.example.miniplaylist.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SnippetCh (
    @SerializedName("snippet")
    var snippet: PlaylistResult

    )