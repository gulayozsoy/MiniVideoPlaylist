package com.example.miniplaylistv3.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miniplaylist.model.ChannelListResponse
import com.example.miniplaylist.model.Snippet
import com.example.miniplaylist.model.VideoId
import com.example.miniplaylistv3.R
import com.squareup.picasso.Picasso

class MainAdapter(var videoList: ArrayList<Snippet>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var onVideoClickListener: OnVideoClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = videoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.videoTitle.text = videoList[position].snippet.title

        Picasso.get().load(videoList[position].snippet.thumbnails.medium.url).into(holder.videoThumbnail)

        //itemview, list item'ın kendisi!
        holder.itemView.setOnClickListener{
            onVideoClickListener.onVideoClick(videoList[position].id)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val videoTitle = itemView.findViewById<TextView>(R.id.item_videotitle)
        val videoThumbnail = itemView.findViewById<ImageView>(R.id.item_thumbnail)
    }

    fun setOnVideoClickListener(onVideoClickListener: OnVideoClickListener) {
        //bu fonk altındaki ile class içindeki parametreleri eşitliyoruz, fragment ile bağlantı bu method'la kuruluyor
        this.onVideoClickListener = onVideoClickListener
    }

    interface OnVideoClickListener {
        fun onVideoClick(items: VideoId)
    }

}

