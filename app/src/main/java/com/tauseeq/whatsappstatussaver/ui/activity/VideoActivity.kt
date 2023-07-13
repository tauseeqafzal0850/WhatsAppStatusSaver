package com.tauseeq.whatsappstatussaver.ui.activity

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.tauseeq.whatsappstatussaver.R
import com.tauseeq.whatsappstatussaver.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    private  val binding by lazy { ActivityVideoBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.run {
            title = resources.getString(R.string.preview)
            setDisplayHomeAsUpEnabled(true)
        }

        val path = intent!!.getStringExtra("videoPath")
        binding.videoView.setVideoURI(Uri.parse(path))

        // create an object of media controller
        val mediaController = MediaController(this)
        // set media controller object for a video view
        binding.videoView.setMediaController(mediaController)
        binding.videoView.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}