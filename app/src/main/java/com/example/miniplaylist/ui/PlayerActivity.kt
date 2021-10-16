package com.example.miniplaylist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.miniplaylist.model.VideoId
import com.example.miniplaylist.util.Constants
import com.example.miniplaylistv3.R
import com.google.android.youtube.player.*

class PlayerActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {

    private val RecoveryDialogRequest = 1
    private lateinit var items: VideoId
    private  var videoId: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)


        if(intent.extras != null) {
            items = intent.extras!!.getSerializable(Constants.EXTRA_USER) as VideoId
            videoId = items.videoid
            Log.e("başarılı", videoId)

        }


        val youTubePlayerFragment = supportFragmentManager.findFragmentById(R.id.youtube_player_screen) as YouTubePlayerSupportFragment?

        youTubePlayerFragment?.initialize(Constants.API_KEY, this)


        }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, wasLoaded: Boolean) {
        if (!wasLoaded) {
            p1?.cueVideo(videoId)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        if (p1!!.isUserRecoverableError) {
            p1.getErrorDialog(this, RecoveryDialogRequest).show()
        } else {
            val error = String.format(
                "Problem on the initialization of YouTubePlayer (%1\$s)", p1.toString()
            )
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }

    }

}
