package com.fahrul.moviecatalogue.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fahrul.moviecatalogue.R
import com.fahrul.moviecatalogue.data.MovieEntity
import com.fahrul.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.fahrul.moviecatalogue.databinding.ContentDetailMovieBinding
import com.fahrul.moviecatalogue.ui.trailer.TrailerMovieActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import kotlin.math.abs

class DetailMovieActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    companion object{
       const val PERCENTAGE_TO_ANIMATE_IMAGE = 20
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        const val EXTRA_PAGE_NAME = "extra_page_name"
    }

    private var title = ""
    private var isImageShown = true
    private var mMaxScrollSize : Int = 0

    private lateinit var activityDetailMovieBinding : ActivityDetailMovieBinding
    private val detailMovieViewModel: DetailMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        val toolbar = activityDetailMovieBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val contentDetailBinding = activityDetailMovieBinding.contentDetail
        activityDetailMovieBinding.collapsingToolbar.title = " "
        activityDetailMovieBinding.collapsingToolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, R.color.white)
        )

        activityDetailMovieBinding.appbar.addOnOffsetChangedListener(this)

        val adapter = DetailMovieAdapter()
        val extras = intent.extras
        if (extras != null){
            val movieId = extras.getString(EXTRA_MOVIE_ID)
            val pageName = extras.getString(EXTRA_PAGE_NAME)
            if (movieId != null){

                detailMovieViewModel.setSelectedMovie(movieId)

                if (pageName.equals("TV SHOW")){
                    title = detailMovieViewModel.getTvShow().title
                    val genresTvShow = detailMovieViewModel.getGenresTvShow()
                    populateMovie(detailMovieViewModel.getTvShow(), contentDetailBinding)
                    adapter.setGenres(genresTvShow)
                }

                if (pageName.equals("MOVIE")){
                    title = detailMovieViewModel.getMovie().title
                    val genresMovie = detailMovieViewModel.getGenresMovie()
                    populateMovie(detailMovieViewModel.getMovie(), contentDetailBinding)
                    adapter.setGenres(genresMovie)
                }

            }
        }
        with(contentDetailBinding.rvGenres){
            isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(this@DetailMovieActivity)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun populateMovie(movie: MovieEntity, contentDetailBinding: ContentDetailMovieBinding) {
        activityDetailMovieBinding.title.text = movie.title
        contentDetailBinding.textRating.text = movie.rating.toString()
        contentDetailBinding.textDesc.text = movie.description
        contentDetailBinding.textReleased.text = movie.released

        val posterPath = resources.getString(R.string.base_path_image, movie.posterPath)
        val backdropPath = resources.getString(R.string.base_path_image, movie.backdropPath)

        Glide.with(this)
            .load(posterPath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_broken_image_black)
            .into(activityDetailMovieBinding.imagePoster)

        Glide.with(this)
            .load(backdropPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_broken_image_black)
            .into(activityDetailMovieBinding.imageToolbar)

        contentDetailBinding.btnFavorite.setOnClickListener {
            if (!movie.favorited){
                movie.favorited = true
                contentDetailBinding.btnFavorite.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_favorited_black, 0, 0,0
                )
                val snackBar = Snackbar.make(it, "You have favorites ${movie.title}",
                    Snackbar.LENGTH_LONG).setAction("Action", null)
                snackBar.show()
            }else{
                movie.favorited = false
                contentDetailBinding.btnFavorite.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_favorite_border_black, 0, 0, 0
                )
                val snackBar = Snackbar.make(it, "You have not favorites ${movie.title}",
                    Snackbar.LENGTH_LONG).setAction("Action", null)
                snackBar.show()
            }
        }

        contentDetailBinding.btnShare.setOnClickListener {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this movie now.")
                .setText(resources.getString(R.string.share_text, movie.title))
                .startChooser()
         }

        contentDetailBinding.btnTrailer.setOnClickListener {
            val intent = Intent(this, TrailerMovieActivity::class.java)
            intent.putExtra(TrailerMovieActivity.EXTRA_TRAILER_PATH, movie.trailerPath)
            startActivity(intent)
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (mMaxScrollSize == 0){
            mMaxScrollSize = activityDetailMovieBinding.appbar.totalScrollRange
        }

        val percentage = (abs(verticalOffset)) * 100 / mMaxScrollSize

        if (percentage >= PERCENTAGE_TO_ANIMATE_IMAGE && isImageShown){
            isImageShown = false

            activityDetailMovieBinding.cvDetailMovie
                .animate()
                .scaleY(0f)
                .scaleX(0f)
                .setDuration(200)
                .start()

            activityDetailMovieBinding.title
                .animate()
                .scaleY(0f)
                .scaleX(0f)
                .setDuration(200)
                .start()
            val toolbar = activityDetailMovieBinding.toolbar
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            activityDetailMovieBinding.collapsingToolbar.title = title
            activityDetailMovieBinding.collapsingToolbar.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white)
            )

        }

        if(percentage <= PERCENTAGE_TO_ANIMATE_IMAGE && !isImageShown){
            isImageShown = true
            activityDetailMovieBinding.cvDetailMovie
                .animate()
                .scaleY(1f)
                .scaleX(1f)
                .start()

            activityDetailMovieBinding.title
                .animate()
                .scaleY(1f)
                .scaleX(1f)
                .start()

            activityDetailMovieBinding.collapsingToolbar.title = ""
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}