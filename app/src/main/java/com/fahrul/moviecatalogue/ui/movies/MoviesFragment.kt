package com.fahrul.moviecatalogue.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fahrul.moviecatalogue.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private lateinit var moviesBinding : FragmentMoviesBinding
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        moviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return moviesBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val movies = moviesViewModel.getMovies()
            val moviesAdapter = MoviesAdapter()
            moviesAdapter.setMovies(movies)
            with(moviesBinding.rvMovies){
                layoutManager = GridLayoutManager(activity, 2)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }

        }
    }

}