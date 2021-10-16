package com.example.miniplaylist.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ChannelListResponse(
    @SerializedName("items")
    var videoList: ArrayList<Snippet>

):Serializable