package com.fahrul.moviecatalogue.ui.tv_show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahrul.moviecatalogue.databinding.FragmentTvShowBinding

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private val tvShowViewModel : TvShowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val tvShow = tvShowViewModel.getTvShow()
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setTvShow(tvShow)
            with(binding.rvTvShow){
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }

    }
}