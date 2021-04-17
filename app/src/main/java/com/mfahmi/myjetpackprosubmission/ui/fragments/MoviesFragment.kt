package com.mfahmi.myjetpackprosubmission.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.adapter.MoviesRecyclerviewAdapter
import com.mfahmi.myjetpackprosubmission.databinding.FragmentMoviesBinding
import com.mfahmi.myjetpackprosubmission.viewmodels.MovieViewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val binding: FragmentMoviesBinding by viewBinding()
    private val viewModel: MovieViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
        binding.rvMovies.adapter =
            MoviesRecyclerviewAdapter(requireContext(), viewModel.getMovies())
    }

}