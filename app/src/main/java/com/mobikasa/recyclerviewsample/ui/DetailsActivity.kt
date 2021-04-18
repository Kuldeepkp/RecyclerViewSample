package com.mobikasa.recyclerviewsample.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mobikasa.recyclerviewsample.databinding.ActivityDetailsBinding
import com.mobikasa.recyclerviewsample.models.Result

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mResult = intent.extras?.getParcelable("DATA") as Result?
        mResult?.run {
            binding.artistName.text = artistName
            binding.collectionName.text = collectionName
            binding.trackName.text = trackName
            binding.collectionCensor.text = collectionCensoredName
            Glide.with(this@DetailsActivity).load(artworkUrl100).into(binding.detailImage)
            supportActionBar?.title = trackName
            binding.ArtistViewBtn.setOnClickListener {
                openWebView(artistViewUrl!!)
            }
            binding.TrackViewBtn.setOnClickListener {
                openWebView(trackViewUrl!!)
            }
        }

    }
    private fun openWebView(url : String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}