package com.mobikasa.recyclerviewsample.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobikasa.recyclerviewsample.databinding.MainItemBinding
import com.mobikasa.recyclerviewsample.models.Result
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainAdapter(var mList: List<Result>, var mOnItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
        holder.itemView.setOnClickListener {
            mOnItemClickListener.onClick(mList[position])
        }

    }

    fun setData(list: List<Result>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (mList.isNotEmpty()) {
            return mList.size
        }
        return 0
    }

    class ViewHolder(private var itemBinding: MainItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(mResult: Result) {
            itemBinding.trackName.text = mResult.trackName
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val date = inputFormat.parse(mResult.releaseDate!!)
            itemBinding.yearRelease.text = "Release Year: ${outputFormat.format(date!!)}"
            itemBinding.collectionName.text =
                "Collection Price: ${mResult.collectionPrice}${mResult.currency}"
            Glide.with(itemView.context).load(mResult.artworkUrl100).into(itemBinding.imageSong)

            mResult.trackTimeMillis?.also {
                val time = String.format(
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(it.toLong()),
                    TimeUnit.MILLISECONDS.toSeconds(it.toLong()) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(it.toLong())
                    )
                )
                itemBinding.trackTime.text = "Duration: $time"
            }

        }

    }
    interface OnItemClickListener {
        fun onClick(mResult: Result)
    }
}
