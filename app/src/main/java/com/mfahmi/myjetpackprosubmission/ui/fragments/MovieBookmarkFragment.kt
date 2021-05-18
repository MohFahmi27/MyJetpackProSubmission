package com.mfahmi.myjetpackprosubmission.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.FragmentMovieBookmarkBinding
import com.mfahmi.myjetpackprosubmission.di.Injection
import com.mfahmi.myjetpackprosubmission.ui.adapter.MoviesRecyclerviewAdapter
import com.mfahmi.myjetpackprosubmission.ui.viewmodels.BookmarkViewModel
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryBookmark
import com.mfahmi.myjetpackprosubmission.utils.setVisibility

class MovieBookmarkFragment : Fragment(R.layout.fragment_movie_bookmark) {
    private val binding: FragmentMovieBookmarkBinding by viewBinding()
    private val viewModel: BookmarkViewModel by activityViewModels {
        ViewModelFactoryBookmark(
            Injection.movieInjectRepository(requireContext()),
            Injection.tvShowInjectRepository(requireContext())
        )
    }
    private val adapter by lazy { MoviesRecyclerviewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            pgMovieFavorite.setVisibility(true)
            rvMoviesFavorite.adapter = adapter
            rvMoviesFavorite.layoutManager = LinearLayoutManager(requireContext())

            viewModel.getBookmarkMovie().observe(viewLifecycleOwner) {
                adapter.submitList(it)
                pgMovieFavorite.setVisibility(false)
            }
        }
    }

}