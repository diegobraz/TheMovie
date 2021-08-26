package com.example.themovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovie.databinding.MovieProductionItemBinding
import com.example.themovie.model.dto.movieDetail.ProductionCompany


class ProductionAdapter(): ListAdapter<ProductionCompany, ProductionAdapter.viewHolder>(
ProductionCallback()
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieProductionItemBinding.inflate(inflater, parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class viewHolder(
        private val binding: MovieProductionItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item : ProductionCompany){

            binding.titleProduction.text = item.name
           Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500${item.logo_path}").into(binding.productionImage)

        }

    }


    class ProductionCallback : DiffUtil.ItemCallback<ProductionCompany>() {
        override fun areItemsTheSame(oldItem: ProductionCompany, newItem: ProductionCompany) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ProductionCompany, newItem: ProductionCompany) =
            oldItem.id == newItem.id


    }
}