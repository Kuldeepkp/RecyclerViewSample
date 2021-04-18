package com.mobikasa.recyclerviewsample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mobikasa.recyclerviewsample.databinding.ActivityMainBinding
import com.mobikasa.recyclerviewsample.models.Result
import com.mobikasa.recyclerviewsample.services.ConnectivityManager
import com.mobikasa.recyclerviewsample.services.MainRepository
import com.mobikasa.recyclerviewsample.services.MainViewModelFactory
import com.mobikasa.recyclerviewsample.services.Status

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(MainRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter = MainAdapter(emptyList(), this)
        binding.mRecyclerView.adapter = mAdapter

        if (ConnectivityManager.isNetworkConnected(this)) {
            viewModel.mData.observe(this, {
                when (it.status) {

                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    Status.SUCCESS -> {
                        Log.d("DATA", "${it.data}")
                        binding.progressBar.visibility = View.GONE
                        mAdapter.setData(it.data!!.filter { lam ->
                            lam.trackName != null
                        })
                        binding.mRecyclerView.scrollToPosition(0)
                    }

                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                }
            })
        } else {
            Snackbar.make(
                binding.root,
                "Please Check your internet connection.",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onClick(mResult: Result) {
        val intent = Intent(this, DetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("DATA", mResult)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}

