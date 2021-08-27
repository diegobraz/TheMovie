package com.example.themovie.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovie.databinding.MovieItemBinding
import com.example.themovie.model.domain.movie.Movie
import com.example.themovie.utils.AppConstants


class MovieListAdapter(val onClickMovie: (movie: Movie) -> Any) :
    ListAdapter<Movie, MovieListAdapter.viewHolder>(
        DiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class viewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {

            binding.titleMovie.text = item.title
            Glide.with(binding.root).load(AppConstants.BASE_IMAGE + item.poster_path)
                .into(binding.movieImage)
            onClickListener(item, binding)
        }

    }

    private fun onClickListener(movie: Movie, binding: MovieItemBinding) {
        binding.movieImage.setOnClickListener {
            onClickMovie(movie)
        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id


}
