package com.example.miniplaylist.model

import com.google.gson.annotations.SerializedName

data class Thumbnails (
    @SerializedName("medium")
    var medium: Medium
)