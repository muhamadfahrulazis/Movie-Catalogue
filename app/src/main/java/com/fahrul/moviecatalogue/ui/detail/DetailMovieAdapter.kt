package com.fahrul.moviecatalogue.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahrul.moviecatalogue.data.GenreEntity
import com.fahrul.moviecatalogue.databinding.ItemsGenresBinding

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.ViewHolder>() {

    private var listGenre = ArrayList<GenreEntity>()

    fun setGenres(genres : List<GenreEntity>?){
        if (genres == null) return
        this.listGenre.clear()
        this.listGenre.addAll(genres)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemsGenresBinding = ItemsGenresBinding.inflate(LayoutInflater.from(
            parent.context), parent, false)

        return ViewHolder(itemsGenresBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = listGenre[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int = listGenre.size

    class ViewHolder(private val binding : ItemsGenresBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: GenreEntity) {
            binding.textGenres.text = genre.title
            binding
        }

    }
}