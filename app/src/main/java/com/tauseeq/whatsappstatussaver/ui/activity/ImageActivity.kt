package com.tauseeq.whatsappstatussaver.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tauseeq.whatsappstatussaver.R
import com.tauseeq.whatsappstatussaver.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.run {
            title = resources.getString(R.string.preview)
            setDisplayHomeAsUpEnabled(true)
        }

        val statusPath = intent!!.getStringExtra("ImagePath")

        Glide.with(this)
            .load(statusPath)
            .into(binding.statusImageView)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}