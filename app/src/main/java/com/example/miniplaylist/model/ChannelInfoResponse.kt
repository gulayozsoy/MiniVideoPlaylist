package com.example.miniplaylist.model

import com.google.gson.annotations.SerializedName

data class ChannelInfoResponse(
    @SerializedName("items")
    var infoList: ArrayList<SnippetCh>
)