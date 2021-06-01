package com.fahrul.moviecatalogue.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fahrul.moviecatalogue.R
import com.fahrul.moviecatalogue.data.MovieEntity
import com.fahrul.moviecatalogue.databinding.ItemsMoviesBinding
import com.fahrul.moviecatalogue.ui.detail.DetailMovieActivity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movieEntities : List<MovieEntity>?){
        if (movieEntities == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movieEntities)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val itemsMoviesBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(
            parent.context), parent, false)
        return MoviesViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MoviesViewHolder(private val binding: ItemsMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            with(binding){
                textTitle.text = movieEntity.title
                textReleased.text = movieEntity.released
                textRating.text = movieEntity.rating.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movieEntity.movieId)
                    intent.putExtra(DetailMovieActivity.EXTRA_PAGE_NAME, "MOVIE")
                    itemView.context.startActivity(intent)
                }

                val posterPath = itemView.resources.getString(R.string.base_path_image, movieEntity.posterPath)
                Glide.with(itemView.context)
                    .load(posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_broken_image_black)
                    .into(imagePoster)
            }
        }

    }
}