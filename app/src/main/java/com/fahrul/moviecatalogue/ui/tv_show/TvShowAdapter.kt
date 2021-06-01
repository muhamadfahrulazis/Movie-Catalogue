package com.fahrul.moviecatalogue.ui.tv_show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fahrul.moviecatalogue.R
import com.fahrul.moviecatalogue.data.MovieEntity
import com.fahrul.moviecatalogue.databinding.ItemsTvShowBinding
import com.fahrul.moviecatalogue.ui.detail.DetailMovieActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val listTvShow = ArrayList<MovieEntity>()

    fun setTvShow(listTvShow : List<MovieEntity>?){
        if (listTvShow == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(listTvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsTvShowBinding = ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listTvShow[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTvShow.size

    class ViewHolder(private val binding: ItemsTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding){
                textTitle.text = movie.title
                textReleased.text = movie.released
                textDesc.text = movie.description
//                rating.text = movie.rating.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movie.movieId)
                    intent.putExtra(DetailMovieActivity.EXTRA_PAGE_NAME, "TV SHOW")
                    itemView.context.startActivity(intent)
                }

                val posterPath = itemView.resources.getString(R.string.base_path_image, movie.posterPath)
                Glide.with(itemView.context)
                    .load(posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_broken_image_black)
                    .into(imagePoster)
            }
        }

    }
}