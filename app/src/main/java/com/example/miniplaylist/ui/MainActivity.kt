package com.example.miniplaylistv3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miniplaylist.data.ApiClient
import com.example.miniplaylist.model.ChannelInfoResponse
import com.example.miniplaylist.model.ChannelListResponse
import com.example.miniplaylist.model.Snippet
import com.example.miniplaylist.model.VideoId
import com.example.miniplaylist.ui.PlayerActivity
import com.example.miniplaylist.util.Constants
import com.example.miniplaylist.util.gone
import com.example.miniplaylist.util.visible
import com.example.miniplaylistv3.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainAdapter.OnVideoClickListener{

    private val apiService by lazy { ApiClient.getApiService() }
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(maintoolbar)

        recyclerview.layoutManager = LinearLayoutManager(this)
        //itemlar arası çizgi çekmek için:
        recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        progressbar.visible()
        recyclerview.gone()
        listPopularVideosOfAChannel()
        getChannelInfo()

    }

    private fun listPopularVideosOfAChannel() {
        apiService.listPopularVideosOfAChannel(Constants.CHANNEL_ID, Constants.API_KEY)
            .enqueue(object: Callback<ChannelListResponse> {
                override fun onFailure(call: Call<ChannelListResponse>, t: Throwable) {
                    //http request cevabı olumsuz olursa loga  hatayı yazdırsın
                    Log.e("listVideosFailure", t.message!!)
                }
                //onResponse: invoked for successfully received HTTP response.
                override fun onResponse(
                    call: Call<ChannelListResponse>, response: Response<ChannelListResponse>
                ) {
                    Log.d("listPopularVideos", "${response.body()?.videoList?.size}")
                    adapter = MainAdapter(response.body()?.videoList!!)
                    adapter.setOnVideoClickListener(this@MainActivity)
                    recyclerview.adapter = adapter
                    progressbar.gone()
                    recyclerview.visible()
                }
            })
    }

    private fun getChannelInfo() {

        apiService.getChannelInfo(Constants.CHANNEL_ID, Constants.API_KEY)
            .enqueue(object: Callback<ChannelInfoResponse> {
                override fun onFailure(call: Call<ChannelInfoResponse>, t: Throwable) {
                    //http request cevabı olumsuz olursa loga  hatayı yazdırsın
                    Log.e("ChannelInfoFailure", t.message!!)
                }
                //onResponse: invoked for successfully received HTTP response.
                override fun onResponse(
                    call: Call<ChannelInfoResponse>, response: Response<ChannelInfoResponse>) {
                    Log.d("ChannelInfo", "response.body()!!.infoList[0].snippet.title")
                    channeltitle.text = response.body()!!.infoList[0].snippet.title
                    Picasso.get().load(response.body()!!.infoList[0].snippet.thumbnails.medium.url).into(channelthumbnail)
                }
            })
    }

    override fun onVideoClick(items: VideoId) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra(Constants.EXTRA_USER, items)
        startActivity(intent)
    }

}
