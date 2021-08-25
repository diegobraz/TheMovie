package com.example.themovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovie.databinding.TrendingItemBinding
import com.example.themovie.model.dto.Trending.Trending

class TrendingListAdapter(val onClickMovie: (trending:Trending) -> Any): ListAdapter<Trending, TrendingListAdapter.viewHolder>(
    TvDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingListAdapter.viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TrendingItemBinding.inflate(inflater, parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class viewHolder(
        private val binding: TrendingItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Trending) {

            binding.titleTv.text = item.original_name
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500${item.poster_path}")
                .into(binding.tvImage)
            onClickListener(item, binding)
        }

    }

    private fun onClickListener(trending: Trending, binding: TrendingItemBinding) {
        binding.tvImage.setOnClickListener {
            onClickMovie(trending)
        }


    }
}
    class TvDiffCallback: DiffUtil.ItemCallback<Trending>(){
        override fun areItemsTheSame(oldItem: Trending, newItem: Trending) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Trending, newItem: Trending) = oldItem.id == newItem.id
    }