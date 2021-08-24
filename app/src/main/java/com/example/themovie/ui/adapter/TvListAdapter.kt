package com.example.themovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovie.databinding.MovieItemBinding
import com.example.themovie.databinding.TvItemBinding
import com.example.themovie.model.dto.Movie
import com.example.themovie.model.dto.tv.Tv

class TvListAdapter(val onClickMovie: (tv:Tv) -> Any): ListAdapter<Tv, TvListAdapter.viewHolder>(
    TvDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvListAdapter.viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TvItemBinding.inflate(inflater, parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class viewHolder(
        private val binding: TvItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tv) {

            binding.titleTv.text = item.original_name
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500${item.poster_path}")
                .into(binding.tvImage)
            onClickListener(item, binding)
        }

    }

    private fun onClickListener(tv: Tv, binding: TvItemBinding) {
        binding.tvImage.setOnClickListener {
            onClickMovie(tv)
        }


    }
}
    class TvDiffCallback: DiffUtil.ItemCallback<Tv>(){
        override fun areItemsTheSame(oldItem: Tv, newItem: Tv) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Tv, newItem: Tv) = oldItem.id == newItem.id
    }