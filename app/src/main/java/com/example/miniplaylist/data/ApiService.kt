package com.example.miniplaylist.data

import com.example.miniplaylist.model.ChannelInfoResponse
import com.example.miniplaylist.model.ChannelListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    //https://www.googleapis.com/youtube/v3/channels?part=snippet&id=UCnOxaDXBiBXg9Nn9hKWu6aw&key=[YOUR_API_KEY]
    //get Channel info

    @GET("channels?part=snippet")
    fun getChannelInfo(@Query("id") channelId: String, @Query("key") key:String): Call<ChannelInfoResponse>


    //https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCnOxaDXBiBXg9Nn9hKWu6aw&maxResults=50&order=rating&key=[YOUR_API_KEY]
    //get video playlist

    @GET("search?part=snippet&maxResults=50&order=rating")
    fun listPopularVideosOfAChannel(@Query("channelId") channelId: String, @Query("key") key:String): Call<ChannelListResponse>


}


